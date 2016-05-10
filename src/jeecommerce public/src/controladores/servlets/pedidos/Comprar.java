package controladores.servlets.pedidos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.SesionUsuario;

/**
 * Servlet implementation class RealizarCompra
 */
@WebServlet("/comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO)
		{
			request.setAttribute("referrer", "/comprar");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("paso", 1);
		request.getRequestDispatcher("/WEB-INF/comprar.jsp").forward(request, response);
		return;
	}
}
