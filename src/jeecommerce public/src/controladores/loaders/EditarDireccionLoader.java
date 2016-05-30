package controladores.loaders;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Direccion;
import modelos.MDirecciones;
import modelos.SesionUsuario;

@WebServlet("/editar-direccion.html")
public class EditarDireccionLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession hs = request.getSession();
		
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.estado != SesionUsuario.LOGUEADO || sesion.usuario == null)
		{
			response.sendRedirect(request.getContextPath() + "/catalogo.html");
			return;
		}
		
		long did = -1;
		
		try
		{
			did = Long.parseLong(request.getParameter("did"));
			
		} catch (Exception x){}
		
		if (did == -1)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No ha especificado la ID de dirección");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		
		mdlDirecciones.getDireccionByDid(did);
		
		if (mdlDirecciones.getProximaDireccion())
		{
			Direccion direccion = new Direccion();
			direccion.did = mdlDirecciones.did;
			direccion.nombre = mdlDirecciones.nombre;
			direccion.direccion = mdlDirecciones.direccion;
			direccion.localidad = mdlDirecciones.localidad;
			direccion.codigoPostal = mdlDirecciones.codigoPostal;
			direccion.telefono = mdlDirecciones.telefono;
			
			request.setAttribute("direccion", direccion);
			
			request.getRequestDispatcher("/WEB-INF/editar-direccion.jsp").forward(request, response);
			return;
			
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No existe la dirección con DID: " + did);
			return;
		}
	}

}
