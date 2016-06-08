package controladores.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.SesionUsuario;

@WebServlet("/dashboard")
public class Dashboard
extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{	
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");

		if (sesion != null && sesion.esAdministrador && sesion.estado == SesionUsuario.LOGUEADO)
		{
			request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("login");
		return;
	}
}