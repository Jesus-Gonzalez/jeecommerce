package controladores.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelos.MBancos;
import modelos.SesionUsuario;

@WebServlet("/bancos/crud")
public class BancosCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || !sesion.esAdministrador)
		{
			response.sendRedirect("login");
			return;
		}
		
		JsonObject datos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		if (!datos.has("accion") ||
			!datos.has("nombre") ||
			!datos.has("numero") ||
			!datos.has("activo"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Algún dato no introducido.");
			return;
		}
		
		String accion = datos.get("accion").getAsString(),
			   nombre = datos.get("nombre").getAsString(),
			   numero = datos.get("numero").getAsString();
		boolean activo = datos.get("activo").getAsBoolean();
		
		if (accion == null || accion.isEmpty() ||
			nombre == null || nombre.isEmpty() ||
			numero == null || numero.isEmpty())
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Algún dato nulo o vacío.");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MBancos mdlBancos = new MBancos(conexion);
		
		long bid;
		
		JsonObject respuesta = new JsonObject();
		
		switch (accion)
		{
			case "crear":
				
				if ((bid = mdlBancos.creaBanco(nombre, numero, activo)) != -1)
				{
					
					respuesta.addProperty("exito", true);
					respuesta.addProperty("bid", bid);

				} else {

					respuesta.addProperty("error", true);
				}
				
				break;
			
			case "actualizar":
				
				if (!datos.has("bid"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BID no establecido.");
					return;
				}
				
				bid = -1;
				
				try
				{
					bid = datos.get("bid").getAsLong();
					
				} catch (ClassCastException x) {
					
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BID no es numérico.");
					return;
				}
				
				if (mdlBancos.actualizaBanco(bid, nombre, numero, activo))
				{
					respuesta.addProperty("exito", true);

				} else {

					respuesta.addProperty("error", true);
				}
				
				break;
			
			case "borrar":
				
				try
				{
					bid = datos.get("bid").getAsLong();
					
				} catch (ClassCastException x) {
					
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BID no es numérico.");
					return;
				}
				
				if (mdlBancos.borraBancoByBid(bid))
				{
					respuesta.addProperty("exito", true);

				} else {

					respuesta.addProperty("error", true);
				}
				
				break;
				
			case "get":

				try
				{
					bid = datos.get("bid").getAsLong();
					
				} catch (ClassCastException x) {
					
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BID no es numérico.");
					return;
				}
				
				if (mdlBancos.getBancoByBid(bid))
				{
					
					respuesta.addProperty("exito", true);
					respuesta.addProperty("nombre", mdlBancos.nombre);
					respuesta.addProperty("numero", mdlBancos.numero);
					respuesta.addProperty("activo", mdlBancos.activo);

				} else {

					respuesta.addProperty("error", true);
				}
				
				break;
		}
		
		response.setContentType("applcation/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(respuesta.toString());
	}

}
