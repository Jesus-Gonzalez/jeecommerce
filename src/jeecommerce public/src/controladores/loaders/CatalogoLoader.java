package controladores.loaders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/catalogo.html")
public class CatalogoLoader
extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/catalogo.jsp").forward(request, response);
	}

}
