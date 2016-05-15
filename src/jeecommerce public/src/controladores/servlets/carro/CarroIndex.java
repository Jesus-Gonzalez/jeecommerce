package controladores.servlets.carro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.SesionUsuario;

@WebServlet("/carro/index")
public class CarroIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		HttpSession hs = request.getSession();
		
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		request.setAttribute("carro", sesion.carro);
		
		request.getRequestDispatcher("/WEB-INF/carro.jsp").forward(request, response);
	}

}
