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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import helpers.RecordarmeHelper;
import modelos.MActivaciones;
import modelos.MUsuarios;
import modelos.SesionUsuario;
import utils.Cifrado;
import utils.Correo;

@WebServlet("/usuarios/perfil")
public class ProcesarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		
		if (session == null)
			return;
		
		Connection conexion = (Connection) session.getAttribute("conexion");
		SesionUsuario s = (SesionUsuario) session.getAttribute("sesion");

		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		hlpRecordarme.comprobarCookieRecordarme(request, response, request.getSession());
		
		if (s.estado == SesionUsuario.CONECTADO || s.usuario == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		JsonObject 	json = new JsonObject(),
					jsonUsuario = new JsonObject();
		
		MUsuarios mdlUsuarios = new MUsuarios(conexion);
		
		mdlUsuarios.getUsuarioByUid(s.usuario.uid);
		
		if (mdlUsuarios.getProximoUsuario())
		{
			jsonUsuario.addProperty("nombre", mdlUsuarios.nombre);
			jsonUsuario.addProperty("correo", mdlUsuarios.correo);
			jsonUsuario.addProperty("pais", mdlUsuarios.pais.toUpperCase());
			json.add("usuario", jsonUsuario);
			json.addProperty("success", true);
			
			response.getWriter().write(json.toString());
			
		} else {
			
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		hlpRecordarme.comprobarCookieRecordarme(request, response, session);

		Connection conexion = (Connection) session.getAttribute("conexion");
		SesionUsuario s = (SesionUsuario) session.getAttribute("sesion");
		
		if (s.estado == SesionUsuario.CONECTADO || s.usuario == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject jsonRequest = new JsonObject();
		JsonElement jsonTipo;
		String tipoRequest;

		jsonRequest = parser.parse(new JsonReader(request.getReader())).getAsJsonObject();
		
		if ( (jsonTipo = jsonRequest.get("tipo")) == null || (tipoRequest = jsonTipo.getAsString()) == null )
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		switch (tipoRequest)
		{
			case "modificar":
				
				try
				{
					JsonElement elementPerfil = jsonRequest.get("perfil");
					
					JsonObject jsonPerfil;
					
					if (elementPerfil != null)
					{
						jsonPerfil = elementPerfil.getAsJsonObject();
						
						if (jsonPerfil.has("correo") && jsonPerfil.has("pais"))
						{
							MUsuarios mdlUsuarios = new MUsuarios(conexion);
						
							JsonObject 	json = new JsonObject(),
										jsonCorreo = new JsonObject(),
										jsonContrasena = new JsonObject(),
										jsonErrores = new JsonObject();
							
							String 	correo = jsonPerfil.get("correo").getAsString().trim(),
									pais = jsonPerfil.get("pais").getAsString(),
									newContrasena, oldContrasena, confirmContrasena;
							
							boolean error = false,
									modificadoCorreo = false,
									modificadoContrasena = false;
							
							if (correo.isEmpty())
							{
								error = true;
								jsonCorreo.addProperty("vacio", true);
								jsonErrores.add("correo", jsonCorreo);
							}
								else if (! correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
							{
								error = true;
								jsonCorreo.addProperty("formato", true);
								jsonErrores.add("correo", jsonCorreo);
							}
								else if (! s.usuario.correo.equals(correo))
							{
							
								modificadoCorreo = true;
								s.usuario.correo = correo;
							}
							
							if (pais.isEmpty() || pais.length() > 2)
							{
								error = true;
								jsonErrores.addProperty("pais", true);
							
							} else {
								
								s.usuario.correo = pais.toLowerCase();
							}
							
							if (jsonPerfil.has("oldContrasena") && jsonPerfil.has("newContrasena") && jsonPerfil.has("confirmContrasena"))
							{
								oldContrasena = jsonPerfil.get("oldContrasena").getAsString();
								newContrasena = jsonPerfil.get("newContrasena").getAsString();
								confirmContrasena = jsonPerfil.get("confirmContrasena").getAsString();
							
								if (!newContrasena.isEmpty())
								{
									
									if (!oldContrasena.equals(s.usuario.contrasena))
									{
										error = true;
										jsonContrasena.addProperty("oldEquals", true);
										jsonErrores.add("contrasena", jsonErrores);
									} 
									
									if (newContrasena.isEmpty())
									{
										error = true;
										jsonContrasena.addProperty("vacio", true);
										jsonErrores.add("contrasena", jsonContrasena);
									
									} else if (newContrasena.length() < 6) {
										
										error = true;
										jsonContrasena.addProperty("underflow", true);
										jsonErrores.add("contrasena", jsonContrasena);
									
									} else if (newContrasena.length() > 50) {
										
										
										error = true;
										jsonContrasena.addProperty("overflow", true);
										jsonErrores.add("contrasena", jsonContrasena);
										
									} else if (!newContrasena.equals(confirmContrasena)) {
										
										error = true;
										jsonContrasena.addProperty("newEquals", true);
										jsonErrores.add("contrasena", jsonErrores);
										
									} else {
										
										modificadoContrasena = true;
										s.usuario.contrasena = newContrasena;
									}
								}
							}
							
							if (error)
							{
								json.add("error", jsonErrores);
								
							} else {
																
								if (modificadoCorreo || modificadoContrasena)
								{
									MActivaciones mdlActivaciones = new MActivaciones(conexion);
									String clave = new Cifrado().creaClaveActivacion(correo);
									// 10 días en milisegundos = 10 * 24 * 60 * 60 * 1000 = 864000000
									long fechaLimite = Calendar.getInstance().getTimeInMillis() + 864000000L;
									long aid = mdlActivaciones.registraActivacion(s.usuario.uid, clave, fechaLimite);
									
									s.usuario.activado = false;
									
									Correo utlCorreo = new Correo(s.usuario.correo);
									
									String protocolo = request.getScheme();
									int port = request.getServerPort();
									String strPort = "";
									
									if (! ((port == 80 && protocolo.equals("http")) ||  (port == 443 && protocolo.equals("https"))) )
										strPort = ":" + port;
									
									String url = protocolo + "://" + request.getServerName() + strPort + request.getContextPath();
									utlCorreo.enviarCorreoActivacion(url, clave, aid);
									
									json.addProperty("activacion", true);
									
									mdlUsuarios = new MUsuarios(conexion, s.usuario);

									mdlUsuarios.actualizaUsuario();
									
									session.invalidate();
									
								} else {
								
									mdlUsuarios = new MUsuarios(conexion, s.usuario);
									mdlUsuarios.actualizaUsuario();
								}
								
								
								json.addProperty("success", true);
								
							}
							
							response.getWriter().write(json.toString());
							
							return;
							
						}
					}
					
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
					
				} catch (ClassCastException x) {
					
					x.printStackTrace();
					
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
					
				} catch (JsonParseException x) {
					
					x.printStackTrace();

					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				

			
			default:
				
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
		}
	}

}
