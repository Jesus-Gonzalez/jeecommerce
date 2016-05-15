package controladores.servlets.usuario;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import helpers.RecordarmeHelper;
import modelos.MUsuarios;

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
		
		boolean error = false;
		
		JsonObject json 		  = new JsonObject(), // JSON principal que se devuelve
				   jsonErrores    = new JsonObject(),
				   jsonUsuario    = new JsonObject(),
				   jsonCorreo  	  = new JsonObject(),
				   jsonContrasena = new JsonObject();
		
		String 	usuario = request.getParameter("nombre"),
				contrasena = request.getParameter("contrasena"),
				contrasenaConfirmar = request.getParameter("contrasenaConfirmar"),
				correo = request.getParameter("correo");
		
		if (usuario == null || contrasena == null || contrasenaConfirmar == null || correo == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
			
		} else {
			
			usuario = usuario.trim();
			contrasena = contrasena.trim();
			contrasenaConfirmar = contrasenaConfirmar.trim();
			correo = correo.trim();
		}
		
		MUsuarios mdlUsuarios = new MUsuarios(conexion);
		
		if (usuario.isEmpty())
		{
			error = true;
			jsonUsuario.addProperty("vacio", true);
			jsonErrores.add("usuario", jsonUsuario);
		
		}
			else if (usuario.length() > 123)
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
		
		} else if (contrasena.length() > 78) {
			
			
			error = true;
			jsonContrasena.addProperty("overflow", true);
			jsonErrores.add("contrasena", jsonContrasena);
			
		} else if (!contrasenaConfirmar.equals(contrasena)) {
			
			error = true;
			jsonContrasena.addProperty("equals", true);
			jsonErrores.add("contrasena", jsonContrasena);
		}
			
		if (error)
		{
			json.add("error", jsonErrores);
		
		} else {
			
			long uid = mdlUsuarios.registrarUsuario(usuario, contrasena, correo);
			
			if (uid == -1)
			{
				
				json.addProperty("errorRegistrando", true);
			} else {
				json.addProperty("exito", true);
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
	}

}

