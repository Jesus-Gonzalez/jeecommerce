package controladores.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CookiesUtils;
import utils.HashMapCookie;

@WebServlet("/rechazarcookie")
public class RechazaCookies
extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
//		Borrar cookie de usuario acepta
		HashMapCookie<String, String> mapaCookies = new CookiesUtils().createCookieMapFromArray("jeecommerce.data", request.getCookies());
		mapaCookies.remove("cookies.acepta");

//		Recrear cookie
		Cookie c = new Cookie("jeecommerce.data", mapaCookies.toString());
		c.setPath("/");
		c.setMaxAge(31536000);
//		Reemplazarla
		response.addCookie(c);
		
//		Redireccionar al sitio de política europea de cookies
		response.sendRedirect("http://europa.eu/cookies/index_es.htm");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

}
