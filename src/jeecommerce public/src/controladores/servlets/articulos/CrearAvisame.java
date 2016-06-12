package controladores.servlets.articulos;

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

import modelos.MAvisame;
import modelos.SesionUsuario;

@WebServlet("/avisame/crear")
public class CrearAvisame extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		
		JsonObject datos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		if (!datos.has("artid") ||
			!datos.has("correo"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de artículo no establecido");
			return;
		}

		String correo = datos.get("correo").getAsString();
		
		long artid = -1;
		
		try
		{
			artid = datos.get("artid").getAsLong();
			
		} catch (ClassCastException x) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de artículo no es numérico");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MAvisame mdlAvisame = new MAvisame(conexion);
		
		JsonObject respuesta = new JsonObject();
		
		if (mdlAvisame.crearAvisame(artid, correo))
		{
			respuesta.addProperty("exito", true);

		} else {

			respuesta.addProperty("error", true);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(respuesta.toString());
	}

}
