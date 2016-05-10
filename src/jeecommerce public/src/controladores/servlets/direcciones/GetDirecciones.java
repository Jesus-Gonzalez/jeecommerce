package controladores.servlets.direcciones;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import modelos.MDirecciones;
import modelos.SesionUsuario;

/**
 * Servlet implementation class GetDirecciones
 */
@WebServlet("/direcciones/get")
public class GetDirecciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "No logueado");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		
		mdlDirecciones.getDireccionesByUid(sesion.usuario.uid);
		
		JsonArray jsonDirecciones = new JsonArray();
		JsonObject jsonDireccion;
		
		while (mdlDirecciones.getProximaDireccion())
		{
			jsonDireccion = new JsonObject();
			
			jsonDireccion.addProperty("did", mdlDirecciones.did);
			jsonDireccion.addProperty("nombre", mdlDirecciones.nombre);
			jsonDireccion.addProperty("direccion", mdlDirecciones.direccion);
			jsonDireccion.addProperty("localidad", mdlDirecciones.localidad);
			jsonDireccion.addProperty("provincia", mdlDirecciones.provincia);
			jsonDireccion.addProperty("codpostal", mdlDirecciones.codigoPostal);
			jsonDireccion.addProperty("telefono", mdlDirecciones.telefono);
			
			jsonDirecciones.add(jsonDireccion);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonDirecciones.toString());
		return;
	}
}
