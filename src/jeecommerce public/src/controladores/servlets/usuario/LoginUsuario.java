package controladores.servlets.usuario;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import helpers.RecordarmeHelper;
import modelos.MRecordarme;
import modelos.MUsuarios;
import modelos.SesionUsuario;
import modelos.Usuario;
import utils.Cifrado;
import utils.CookiesUtils;
import utils.HashMapCookie;

@WebServlet("/usuarios/login")
public class LoginUsuario extends HttpServlet {
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
		
		// Comprobar cookie recordarme
		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		if (hlpRecordarme.comprobarCookieRecordarme(request, response, sesion))
		{
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		Connection conexion = (Connection) sesion.getAttribute("conexion");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String 	correo = request.getParameter("email"),
				contrasena = request.getParameter("contrasena"),
				path = getServletContext().getContextPath();
		
		if (correo == null || contrasena == null || path == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
			
		} else {
			
			correo = correo.trim();
			contrasena = contrasena.trim();
		}
		
		boolean error = false;
		
		JsonObject	json 			= new JsonObject(),
					jsonErrores 	= new JsonObject(),
					jsonUsuario 	= new JsonObject(),
					jsonContrasena 	= new JsonObject(),
					jsonCuenta		= new JsonObject();

		MUsuarios mdlUsuarios = new MUsuarios(conexion);
		SesionUsuario s = (SesionUsuario) sesion.getAttribute("usuario");
		
		boolean recordarme = (request.getParameter("recordar") != null) ? request.getParameter("recordar").equals("true") : false;
		MRecordarme mdlRecordarme = new MRecordarme(conexion);
		
		Cifrado cifrado = new Cifrado();
		Random r = new Random();
		
		HashMapCookie<String, String> mapaCookies = new CookiesUtils().createCookieMapFromArray("jeecommerce.data", request.getCookies());
		Cookie c;
		
		if (correo.isEmpty())
		{
			error = true;
			jsonUsuario.addProperty("vacio", true);
			jsonErrores.add("usuario", jsonUsuario);
			
		} else if (correo.length() > 30) {
			
			error = true;
			jsonUsuario.addProperty("overflow", true);
			jsonErrores.add("usuario", jsonUsuario);
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
		}
		
		// No existen errores: comprobar datos de inicio y si está activada la cuenta
		if ( !error )
		{			
			mdlUsuarios.getUsuarioByCorreo(correo);
			
			// Existe?
			if ( mdlUsuarios.getProximoUsuario() )
			{
				// Esta activado?
				if (mdlUsuarios.activado)
				{
					// Contraseña correcta?
					if (mdlUsuarios.correo.equals(correo) && mdlUsuarios.contrasena.equals(contrasena))
					{
						s.usuario = new Usuario(mdlUsuarios);
						s.usuario.ip = request.getRemoteAddr();
						s.usuario.fechaConexion = Calendar.getInstance().getTimeInMillis();
						
						s.estado = SesionUsuario.LOGUEADO;
						
						sesion.setAttribute("usuario", s);
						
						// Actualizar el usuario de la base de datos, indicando la dir. IP y la ultima fecha de conexión
						mdlUsuarios.actualizarDireccionIp(s.usuario.uid, s.usuario.ip);
						mdlUsuarios.actualizarFechaConexion(s.usuario.uid, s.usuario.fechaConexion);
						
						if (recordarme)
						{							
							String numAleatorio = String.valueOf(r.nextInt(Integer.MAX_VALUE));
							
							mdlRecordarme.id 	= cifrado.hash("SHA-256", r.nextInt(Integer.MAX_VALUE) + correo + r.nextInt(Integer.MAX_VALUE));
							mdlRecordarme.salt 	= cifrado.hash("SHA-256", r.nextInt(Integer.MAX_VALUE) + correo + r.nextInt(Integer.MAX_VALUE));
							mdlRecordarme.token = cifrado.hash("SHA-256", mdlRecordarme.salt + numAleatorio);
							mdlRecordarme.uid 	= mdlUsuarios.uid;
							
							// Si se crea en la BD la entrada para recordarme correctamente
							mdlRecordarme.crearRecordarme();

							mapaCookies.put("recordar", "true");
							mapaCookies.put("muid", mdlRecordarme.id); // MUID = Machine Unique ID
							mapaCookies.put("token", numAleatorio);
							
							c = new Cookie("jeecommerce.data", mapaCookies.toString());
							c.setMaxAge(365 * 24 * 60 * 60);
							c.setPath("/");

							response.addCookie(c);
							
						} else { // El usuario no desea que lo recuerden. Entonces se borra de la BD la id y token
							
							if (mdlRecordarme.buscaRecordarmeByUid(mdlUsuarios.uid))
							{
								mdlRecordarme.borrarRecordarme();
							}
						}
						
						json.addProperty("success", true);
						
					// Contraseña incorrecta
					} else {
						
						error = true;
						
						jsonUsuario.addProperty("contrasena", true);
						jsonErrores.add("usuario", jsonUsuario);
					}
						
				// No activado
				} else {
					
					error = true;
					
					jsonCuenta.addProperty("activado", true);
					jsonErrores.add("cuenta", jsonCuenta);
				}
				
			// No existe
			} else {
				
				error = true;
				
				jsonUsuario.addProperty("existe", true);
				jsonErrores.add("usuario", jsonUsuario);
			}
		
			if (error)
			{
				json.add("error", jsonErrores);
			}
		
		// Existen errores, añadir al objeto devuelto la pila de errores
		} else {
			
			json.add("error", jsonErrores);
		}
		
		response.getWriter().write(json.toString());

	}

}
