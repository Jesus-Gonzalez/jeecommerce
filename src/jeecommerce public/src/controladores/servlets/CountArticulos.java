package controladores.servlets;

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

import modelos.MArticulos;
import modelos.MConfiguracion;

/**
 * Servlet implementation class CountArticulos
 */
@WebServlet("/articulos/get/paginas")
public class CountArticulos
extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession sesion = request.getSession();
		Connection conexion = (Connection) sesion.getAttribute("conexion");
		
		JsonObject datos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		if (conexion == null || datos == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		// Obtener numer de elementos por página de la base de datos
		int numElementosPorPagina = -1;
		long categoria = -1;
		
		try
		{
			MConfiguracion mdlConfiguracion = new MConfiguracion(conexion);
			mdlConfiguracion.getParametro("NumElementosPorPagina");
			
			if (mdlConfiguracion.getProximoParametro())
			{
				 numElementosPorPagina = Integer.parseInt(mdlConfiguracion.valor);
			}
			else
			{
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

			categoria = datos.get("categoria").getAsInt();
		}
		 catch (ClassCastException x)
		{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return; 
		}
		
		MArticulos mdlArticulos = new MArticulos(conexion);
		
		int numProductos = mdlArticulos.countProductosByCatId(categoria);
		
		JsonObject respuesta = new JsonObject();
		
		respuesta.addProperty("paginas", numProductos / numElementosPorPagina);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(respuesta.toString());
	}

}
