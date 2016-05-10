package controladores.loaders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticuloLoader
 */
@WebServlet("/producto.html")
public class ArticuloLoader
extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		request.getRequestDispatcher("/articulos/get/detalle").forward(request, response);
	}

}
