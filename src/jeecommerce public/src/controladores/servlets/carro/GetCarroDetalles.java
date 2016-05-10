package controladores.servlets.carro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import modelos.Articulo;
import modelos.SesionUsuario;

/**
 * Servlet implementation class GetCarroDetalles
 */
@WebServlet("/carro/get/detalles")
public class GetCarroDetalles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		JsonObject jsonCarro = new JsonObject();
		
		JsonArray jsonArticulos = new JsonArray();
		JsonObject jsonArticulo;
		
		if (sesion.carro.articulos.size() != 0)
		{
			for (Articulo articulo : sesion.carro.articulos.values())
			{
				jsonArticulo = new JsonObject();
				jsonArticulo.addProperty("artid", articulo.artid);
				jsonArticulo.addProperty("nombre", articulo.nombre);
				jsonArticulo.addProperty("cantidad", articulo.cantidad);
				
				jsonArticulos.add(jsonArticulo);
			}
		}
		
		jsonCarro.add("articulos", jsonArticulos);
		jsonCarro.addProperty("total", sesion.carro.total);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonCarro.toString());
		return;
	}
}
