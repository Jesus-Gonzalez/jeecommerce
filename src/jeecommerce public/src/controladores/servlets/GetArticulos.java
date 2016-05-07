package controladores.servlets;

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
import com.google.gson.JsonParser;

import modelos.MArticulos;
import modelos.MConfiguracion;

/**
 * Servlet implementation class GetArticulos
 */
@WebServlet("/articulos/get")
public class GetArticulos extends HttpServlet {
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
		int pagina = -1;
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

			pagina = datos.get("pagina").getAsInt();
			categoria = datos.get("categoria").getAsInt();
		}
		 catch (ClassCastException x)
		{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return; 
		}
		
		MArticulos mdlArticulos = new MArticulos(conexion);
		
		
		if (categoria == -1)
		{
			mdlArticulos.getLatestArticulos(numElementosPorPagina, ((pagina - 1) * numElementosPorPagina));
			
		} else {
			
			mdlArticulos.getArticulosByCatId(categoria, numElementosPorPagina, (pagina - 1) * numElementosPorPagina);
		}
		
		JsonArray jsonArticulos = new JsonArray();
		JsonObject jsonArticulo;
		
		while (mdlArticulos.getProximoArticulo())
		{
			jsonArticulo = new JsonObject();
			
			
			jsonArticulo.addProperty("artid", mdlArticulos.artid);
			jsonArticulo.addProperty("nombre", mdlArticulos.nombre);
			jsonArticulo.addProperty("precio", mdlArticulos.precio);
			jsonArticulo.addProperty("imagen", mdlArticulos.imagen);
			jsonArticulo.addProperty("stock", mdlArticulos.stock);
			jsonArticulo.addProperty("fechaCreacion", mdlArticulos.fechaCreacion);
			
			jsonArticulos.add(jsonArticulo);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonArticulos.toString());
		return;
	}

}
