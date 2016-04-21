package controladores.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.CategoriasHelper;

@WebServlet("/categorias/get")
public class GetCategorias
extends HttpServlet
{

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{		
		Connection conexion = (Connection) request.getSession().getAttribute("conexion");
		
		if (conexion == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
				
		// Las categorías raíces tienen la ID 1
//		JsonArray jsonCategorias = CategoriasHelper.getCategoriasRecursive(conexion, 1);
//		response.getWriter().write(jsonCategorias.toString());
		
		// Minimizando creación de referencias a objetos
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write( CategoriasHelper.getCategoriasRecursive(conexion, 1).toString() );
		
		return;
	}
}
