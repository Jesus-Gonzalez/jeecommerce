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
 * Servlet que carga la vista de activaciones
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
		// Se establece el título de la página
		request.setAttribute("titulo-pagina", "Activación - Servicios Agrícolas");
		// Se establece a qué vista se va a redirigir
		request.setAttribute("forward", "index.jsp");
		
		// Se obtiene el parámetro acción, clave e id de activación
		String accion = request.getParameter("accion");
		String clave = request.getParameter("clave");
		String strAid = request.getParameter("id");
		
		// Se establece un flag en caso que la acción sea reenviar
		boolean isReenviar = (accion != null && accion.equals("reenviar"));

		// Si la acción no es reenviar y, la clave o el código de activación es nulo
		if (!isReenviar && (clave == null || strAid == null))
		{
			// se envía error 403
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		// Se establece una variable de contexto en la petición si se va a reenviar la clave de activación
		request.setAttribute("isReenviar", isReenviar);
		
		int error = 0;

		// Si la acción no es reenviar
		if ( !isReenviar )
		{
			// Se obtiene la conexión
			Connection conexion = (Connection) request.getSession().getAttribute("conexion");
			// Se crean modelos para acceso a tablas
			MUsuarios mdlUsuarios = new MUsuarios(conexion);
			MActivaciones mdlActivaciones = new MActivaciones(conexion);

			try
			{
				// Se obtiene el código de activación como long
				long aid = Long.parseLong(strAid);

				// Obtenemos la activación, y si existe
				if (mdlActivaciones.getActivacionByAid(aid))
				{
					// Obtenemos el usuario según su ID de usuario
					mdlUsuarios.getUsuarioByUid(mdlActivaciones.uid);

					// Si el usuario existe
					if (mdlUsuarios.getProximoUsuario())
					{
						// Si el usuario no tiene la cuenta activada
						if (!mdlUsuarios.activado)
						{
							// Si la clave de activación es igual a la clave de activación por parámetro
							if (mdlActivaciones.clave.equals(clave))
							{
								// Establecemos el usuario como activado
								mdlUsuarios.activado = true;
								// Actualizamos el usuario
								mdlUsuarios.actualizaUsuario();

								// Se borra la activación
								mdlActivaciones.borraActivacion();

					// Sino, se establecen errores
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
				
			// Si ocurre una excepción cuando parseamos "aid"
			} catch (NumberFormatException x) {

				// Se devuelve error 400, petición mal creada
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			// Establecemos el código de error en el contexto de la petición (request)
			request.setAttribute("error.code", error);
		}
		
		// Redirigimos la petición a la vista /WEB-INF/activacion.jsp
		request.getRequestDispatcher("/WEB-INF/activacion.jsp").forward(request, response);
	}

}
