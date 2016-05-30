package controladores.loaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.SesionUsuario;

@WebServlet("/login")
public class LoginLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		SesionUsuario sesion = (SesionUsuario) request.getSession().getAttribute("usuario");
		
		if (sesion.estado == SesionUsuario.LOGUEADO)
		{
			String referrer = (String) request.getAttribute("referrer");
			
			if (referrer == null)
				request.getRequestDispatcher("/WEB-INF/catalogo.jsp").forward(request, response);
			else
				request.getRequestDispatcher(referrer).forward(request, response);
			return;

		} else {
			
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		
	}
}
