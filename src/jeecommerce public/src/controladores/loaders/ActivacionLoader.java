package controladores.loaders;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.MActivaciones;
import modelos.MUsuarios;

/**
 * Servlet implementation class ActivacionLoader
 */
@WebServlet("/activacion.html")
public class ActivacionLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{


		request.setAttribute("titulo-pagina", "Activación - Servicios Agrícolas");
		request.setAttribute("forward", "index.jsp");
		
		String accion = request.getParameter("accion");
		String clave = request.getParameter("clave");
		String strAid = request.getParameter("id");
		
		boolean isReenviar = (accion != null && accion.equals("reenviar"));

		if (!isReenviar && (clave == null || strAid == null))
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		request.setAttribute("isReenviar", isReenviar);
		
		int error = 0;

		if ( !isReenviar )
		{
			Connection conexion = (Connection) request.getSession().getAttribute("conexion");
			MUsuarios mdlUsuarios = new MUsuarios(conexion);
			MActivaciones mdlActivaciones = new MActivaciones(conexion);

			try
			{
				long aid = Long.parseLong(strAid);

				if (mdlActivaciones.getActivacionByAid(aid))
				{
					mdlUsuarios.getUsuarioByUid(mdlActivaciones.uid);

					if (mdlUsuarios.getProximoUsuario())
					{
						if (!mdlUsuarios.activado)
						{
							if (mdlActivaciones.clave.equals(clave))
							{
								mdlUsuarios.activado = true;
								mdlUsuarios.actualizaUsuario();

								mdlActivaciones.borraActivacion();

							} else {

								error = 4; // La clave no coincide
							}

						} else {

							error = 3; // Cuenta ya activada
						}

					} else {
						error = 2; // No existe usuario
					}

				} else {
					error = 1; // No existe dicha activacion
				}

			} catch (NumberFormatException x) {

				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			request.setAttribute("error.code", error);
		}
		
		request.getRequestDispatcher("/WEB-INF/activacion.jsp").forward(request, response);
	}

}
