package controladores.servlets.direcciones;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelos.MDirecciones;
import modelos.SesionUsuario;

@WebServlet("/editar-direccion")
public class EditarDireccion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();

		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.usuario == null || sesion.estado == SesionUsuario.CONECTADO)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		JsonObject datos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		if (!datos.has("did") ||
			!datos.has("nombre") ||
			!datos.has("direccion") ||
			!datos.has("localidad") ||
			!datos.has("codpostal") ||
			!datos.has("telefono"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incorrectos");
			return;
		}
		
		long did = -1;
		try
		{
			did = datos.get("did").getAsLong();
			
		} catch (ClassCastException x) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID Dirección no correcto");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		mdlDirecciones.getDireccionByDid(did);
		
		if (!mdlDirecciones.getProximaDireccion())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "ID Dirección no encontrada");
			return;
		}
		
		if (mdlDirecciones.uid != sesion.usuario.uid)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuario propietario de dirección no coincide");
			return;
		}
		
		mdlDirecciones.did = did;
		mdlDirecciones.nombre = datos.get("nombre").getAsString();
		mdlDirecciones.direccion = datos.get("direccion").getAsString();
		mdlDirecciones.localidad = datos.get("localidad").getAsString();
		mdlDirecciones.codigoPostal = datos.get("codpostal").getAsString();
		mdlDirecciones.telefono = datos.get("telefono").getAsString();
		
		mdlDirecciones.actualizaDireccion();
		
		JsonObject respuesta = new JsonObject();
		
		respuesta.addProperty("exito", true);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(respuesta.toString());
		return;
	}

}
