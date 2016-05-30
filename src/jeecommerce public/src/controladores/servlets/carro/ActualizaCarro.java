package controladores.servlets.carro;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		JsonObject jsonRespuesta = new JsonObject();
		SesionUsuario sesion = (SesionUsuario) request.getSession().getAttribute("usuario");
		Articulo articulo;
		
		switch (operacion)
		{
			case "delete":
				articulo = sesion.carro.articulos.get(idArticulo);
				sesion.carro.total = sesion.carro.total.subtract( articulo.precio.multiply(BigDecimal.valueOf(articulo.cantidad)) );
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
				
				articulo = sesion.carro.articulos.get(idArticulo);
				sesion.carro.total = sesion.carro.total.add( articulo.precio.multiply(BigDecimal.valueOf(cantidad - articulo.cantidad)) );
				
				articulo.cantidad = cantidad;
				sesion.carro.articulos.put(idArticulo, articulo);
				
				jsonRespuesta.addProperty("subtotal", articulo.precio.multiply(BigDecimal.valueOf(articulo.cantidad)).multiply(BigDecimal.valueOf(100)).intValue());
				
				break;
				
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
		}
		
		jsonRespuesta.addProperty("exito", true);
		jsonRespuesta.addProperty("total", sesion.carro.total);
		
		response.getWriter().write(jsonRespuesta.toString());
	}

}
