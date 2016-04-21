package controladores.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import helpers.RecordarmeHelper;
import modelos.MActivaciones;
import modelos.MUsuarios;
import utils.Cifrado;
import utils.Correo;

@WebServlet("/usuarios/crear")
public class CrearUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession sesion = request.getSession(false);
		
		if (sesion == null)
			return;
		
		Connection conexion = (Connection) sesion.getAttribute("conexion");
		
		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		if (hlpRecordarme.comprobarCookieRecordarme(request, response, sesion))
		{
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		boolean error = false;
		
		JsonObject json 		  = new JsonObject(), // JSON principal que se devuelve
				   jsonErrores    = new JsonObject(),
				   jsonUsuario    = new JsonObject(),
				   jsonCorreo  	  = new JsonObject(),
				   jsonContrasena = new JsonObject();
		
		String 	usuario = request.getParameter("usuario"),
				contrasena = request.getParameter("contrasena"),
				contrasenaConfirmar = request.getParameter("contrasenaConfirmar"),
				correo = request.getParameter("correo"),
				pais = request.getParameter("pais");
		
		if (usuario == null || contrasena == null || contrasenaConfirmar == null || correo == null || pais == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
			
		} else {
			
			usuario = usuario.trim();
			contrasena = contrasena.trim();
			contrasenaConfirmar = contrasenaConfirmar.trim();
			correo = correo.trim();
			pais = pais.toLowerCase().trim();
		}
		
		MUsuarios mdlUsuarios = new MUsuarios(conexion);
		MActivaciones mdlActivaciones = new MActivaciones(conexion);
		
		if (usuario.isEmpty())
		{
			error = true;
			jsonUsuario.addProperty("vacio", true);
			jsonErrores.add("usuario", jsonUsuario);
		
		}
			else if (usuario.length() > 30)
		{
			error = true;
			jsonUsuario.addProperty("overflow", true);
			jsonErrores.add("usuario", jsonUsuario);
			
		}
			else if (usuario.length() < 5)
		{
				error = true;
				jsonUsuario.addProperty("underflow", true);
				jsonErrores.add("usuario", jsonUsuario);
		}
			else if (!usuario.matches("^[a-zA-Z0-9]+$"))
		{
				error = true;
				jsonUsuario.addProperty("formato", true);
				jsonErrores.add("usuario", jsonUsuario);
		}
			else if (mdlUsuarios.existeUsuario(usuario))
		{
			error = true;
			jsonUsuario.addProperty("existe", true);
			jsonErrores.add("usuario", jsonUsuario);
		}
		
		if (correo.isEmpty())
		{
			error = true;
			jsonCorreo.addProperty("vacio", true);
			jsonErrores.add("correo", jsonCorreo);
		}
			else if (!correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			error = true;
			jsonCorreo.addProperty("formato", true);
			jsonErrores.add("correo", jsonCorreo);
		}
			else if (mdlUsuarios.existeCorreo(correo))
		{
			error = true;
			jsonCorreo.addProperty("existe", true);
			jsonErrores.add("correo", jsonCorreo);
		}
		
		if (contrasena.isEmpty())
		{
			error = true;
			jsonContrasena.addProperty("vacio", true);
			jsonErrores.add("contrasena", jsonContrasena);
		
		} else if (contrasena.length() < 6) {
			
			error = true;
			jsonContrasena.addProperty("underflow", true);
			jsonErrores.add("contrasena", jsonContrasena);
		
		} else if (contrasena.length() > 50) {
			
			
			error = true;
			jsonContrasena.addProperty("overflow", true);
			jsonErrores.add("contrasena", jsonContrasena);
			
		} else if (!contrasenaConfirmar.equals(contrasena)) {
			
			error = true;
			jsonContrasena.addProperty("equals", true);
			jsonErrores.add("contrasena", jsonContrasena);
		}
		
		if (pais.isEmpty() || pais.length() > 2)
		{
			error = true;
			jsonErrores.addProperty("pais", true);
		}
		
		if (error)
		{
			json.add("error", jsonErrores);
		
		} else {
			
			long uid = mdlUsuarios.registrarUsuario(usuario, contrasena, correo, pais);
			
			if (uid == -1)
			{
				
				json.addProperty("errorRegistrando", true);
			
			} else {
				
				String clave = new Cifrado().creaClaveActivacion(correo);
				// 10 días en milisegundos = 10 * 24 * 60 * 60 * 1000 = 864000000
				long fechaLimite = Calendar.getInstance().getTimeInMillis() + 864000000L;
				long aid = mdlActivaciones.registraActivacion(uid, clave, fechaLimite);
				
				Correo utilCorreo = new Correo(correo);
				
				String protocolo = request.getScheme();
				int port = request.getServerPort();
				String strPort = "";
				
				if (! ((port == 80 && protocolo.equals("http")) ||  (port == 443 && protocolo.equals("https"))) )
					strPort = ":" + port;
				
				String url = protocolo + "://" + request.getServerName() + strPort + request.getContextPath();
				utilCorreo.enviarCorreoActivacion(url, clave, aid);
				
				json.addProperty("success", true);
			}
		}

		response.getWriter().write(json.toString());
		
	}

}

