package controladores.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import modelos.Articulo;
import modelos.SesionUsuario;

/**
 * Servlet implementation class ActualizaCarro
 */
@WebServlet("/carro/update")
public class ActualizaCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// Obtener datos que envía el usuario
		JsonObject jsonAccion = new JsonParser().parse(new JsonReader(request.getReader())).getAsJsonObject();
		String operacion = jsonAccion.get("operacion").getAsString();
		long idArticulo = -1;
		
		try
		{
			idArticulo = jsonAccion.get("artid").getAsLong();

		} catch (Exception x) {	}
		
		if (operacion == null || idArticulo == -1)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		SesionUsuario sesion = (SesionUsuario) request.getSession().getAttribute("usuario");
		
		switch (operacion)
		{
			case "delete":
				sesion.carro.articulos.remove(idArticulo);
				break;
				
			case "update":
				
				int cantidad = -1;
				
				try
				{
					cantidad = jsonAccion.get("cantidad").getAsInt();

				} catch (Exception x) {

					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				
				Articulo articulo = sesion.carro.articulos.get(idArticulo);
				articulo.cantidad = cantidad;
				sesion.carro.articulos.put(idArticulo, articulo);
				break;
				
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
		}
		
		JsonObject jsonRespuesta = new JsonObject();
		jsonRespuesta.addProperty("exito", true);
		
		response.getWriter().write(jsonRespuesta.toString());
	}

}
