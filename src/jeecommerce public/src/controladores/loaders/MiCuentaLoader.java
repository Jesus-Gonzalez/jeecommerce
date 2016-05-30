package controladores.loaders;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Direccion;
import modelos.MDirecciones;
import modelos.SesionUsuario;

/**
 * Servlet implementation class MiCuentaLoader
 */
@WebServlet("/mi-cuenta.html")
public class MiCuentaLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.estado != SesionUsuario.LOGUEADO)
		{
			response.sendRedirect("/catalogo.html");
			return;
		}
		
		if (sesion.estado == SesionUsuario.LOGUEADO)
		{
			request.setAttribute("usuario.object", sesion.usuario);
			
			Connection conexion = (Connection) hs.getAttribute("conexion");
			
			MDirecciones mdlDirecciones = new MDirecciones(conexion);
			mdlDirecciones.getDireccionesByUid(sesion.usuario.uid);

			List<Direccion> lstDirecciones = new LinkedList<Direccion>();
			Direccion direccion;
			
			while (mdlDirecciones.getProximaDireccion())
			{
				direccion = new Direccion();
				
				direccion.did = mdlDirecciones.did;
				direccion.nombre = mdlDirecciones.nombre;
				direccion.direccion = mdlDirecciones.direccion;
				direccion.localidad = mdlDirecciones.localidad;
				direccion.codigoPostal = mdlDirecciones.codigoPostal;
				direccion.telefono = mdlDirecciones.telefono;
				
				lstDirecciones.add(direccion);
			}
	
			request.setAttribute("lista.direcciones", lstDirecciones);
			
			request.getRequestDispatcher("/WEB-INF/mi-cuenta.jsp").forward(request, response);
		}
		
		
	}

}
