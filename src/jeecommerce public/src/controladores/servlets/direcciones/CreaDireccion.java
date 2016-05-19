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
import com.google.gson.stream.JsonReader;

import modelos.MDirecciones;
import modelos.SesionUsuario;

@WebServlet("/direccion/crea")
public class CreaDireccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		if (sesion.estado != SesionUsuario.LOGUEADO)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "No logueado");
			return;
		}
		
		JsonObject jsonDireccion = new JsonParser().parse(new JsonReader(request.getReader())).getAsJsonObject();
		
		if (jsonDireccion == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se han recibido datos");
			return;
		}
		
		String	nombre = jsonDireccion.get("nombre").getAsString(),
				direccion = jsonDireccion.get("direccion").getAsString(),
				localidad = jsonDireccion.get("localidad").getAsString(),
				codigoPostal = jsonDireccion.get("codpostal").getAsString(),
				telefono = jsonDireccion.get("telefono").getAsString();
		
		if (nombre == null || direccion == null || localidad == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Campos obligatorios");
			return;
		}
		
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		long did = mdlDirecciones.creaDireccion(sesion.usuario.uid, nombre, direccion, localidad, codigoPostal, telefono);
		
		JsonObject jsonRespuesta = new JsonObject();
		
		if (did != -1)
		{
			jsonRespuesta.addProperty("did", did);

		} else {
			
			jsonRespuesta.addProperty("error", true);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRespuesta.toString());
		return;
	}

}
