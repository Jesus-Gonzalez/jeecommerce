package controladores.servlets.usuario;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.RecordarmeHelper;
import modelos.SesionUsuario;
import utils.CookiesUtils;
import utils.HashMapCookie;

@WebServlet("/usuarios/desconectar")
public class DesconectarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void borraCookiesRecordar(HashMapCookie<String, String> mapaCookies)
	{
		mapaCookies.remove("recordar");
		mapaCookies.remove("muid");
		mapaCookies.remove("token");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		
		if (session == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		hlpRecordarme.comprobarCookieRecordarme(request, response, session);
		
		Connection conexion = (Connection) session.getAttribute("conexion");
		SesionUsuario sesion = (SesionUsuario) session.getAttribute("sesion");
		
		if (sesion == null || sesion.usuario == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		CookiesUtils cu = new CookiesUtils();
		HashMapCookie<String, String> mapaCookies = cu.createCookieMapFromArray("jeecommerce.data", request.getCookies());
		String cookieRecordar = mapaCookies.get("recordar");
		
		if (cookieRecordar != null && cookieRecordar.equals("true"))
		{
			borraCookiesRecordar(mapaCookies);
			
			Cookie c = new Cookie("jeecommerce.data", mapaCookies.toString());
			c.setMaxAge(365 * 24 * 60 * 60);
			c.setPath("/");
			
			response.addCookie(c);
		}
		
		session.invalidate();
		
		response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

}
