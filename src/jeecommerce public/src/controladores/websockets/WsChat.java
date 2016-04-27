package controladores.websockets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelos.Chat;
import modelos.MChats;
import modelos.MChatsMensajes;
import modelos.SesionChat;
import modelos.SesionUsuario;
import utils.Cifrado;

@ServerEndpoint(value = "/chat", configurator = WsChatEndpointConfig.class)
public class WsChat
{
	public static Set<Session> sesionesChat = new HashSet<Session>();
	public static Set<Session> sesionesChatAdmin = new HashSet<Session>();
	
	@OnOpen
	public void open(Session sesion, EndpointConfig config)
	{
		JsonObject jsonRespuesta = new JsonObject();
		
		Basic remote = sesion.getBasicRemote();
		
		Map<String,Object> propiedades = sesion.getUserProperties();
		
		SesionChat sesionChat = new SesionChat();
		
		try
		{
			
			if (sesionesChat.contains(sesion) || sesionesChatAdmin.contains(sesion))
			{
				
				jsonRespuesta.addProperty("reconectado", true);

			} else {

				HttpSession s = (HttpSession) propiedades.get("sesion");
				SesionUsuario usrSesion = (SesionUsuario) s.getAttribute("usuario");
				
				if (usrSesion.esAdministrador)
				{

					sesionesChatAdmin.add(sesion);
					sesionChat.esAdministrador = true;
					
				} else {

					sesionesChat.add(sesion);
					
					propiedades.put("uuid", new Cifrado().hash("SHA256", new Long(Calendar.getInstance().getTimeInMillis()).toString() + new Random().nextInt(Integer.MAX_VALUE)));
					sesionChat.esAdministrador = false;
				}
				
				jsonRespuesta.addProperty("conectado", true);
			}
			
			propiedades.put("sesion", sesionChat);
			remote.sendText(jsonRespuesta.toString());

		} catch (IOException x) {
			
			System.err.println("Error de entrada/salida @WsChat:open(Session, EndpointConfig)");
			System.err.println("Fecha: " + Calendar.getInstance().getTime());
			System.err.println("Traza:");
			x.printStackTrace();
		}
	}
	
	@OnClose
	public void close(Session sesion, EndpointConfig config)
	{
		try
		{
			if (sesionesChat.contains(sesion))
			{
				sesionesChat.remove(sesion);
			}
			
			JsonObject jsonRespuesta = new JsonObject();
			jsonRespuesta.addProperty("desconectado", true);
			sesion.getBasicRemote().sendText(jsonRespuesta.toString());

		} catch (IOException x) {
			
			System.err.println("Error de entrada/salida @WsChat:close(Session, EndpointConfig)");
			System.err.println("Fecha: " + Calendar.getInstance().getTime());
			System.err.println("Traza:");
			x.printStackTrace();
		}
	}
	
	@OnError
	public void error(Throwable exception)
	{
		System.err.println("Error @WsChat:error(Throwable)");
		System.err.println("Fecha: " + Calendar.getInstance().getTime());
		System.err.println("Traza:");
		exception.printStackTrace();
	}
	
	@OnMessage
	public void procesarMensaje(String mensaje, Session sesion)
	{
		try
		{
			JsonObject datos = new JsonParser().parse(mensaje).getAsJsonObject();
			JsonObject respuesta = new JsonObject();
			
			if (datos == null)
			{
				respuesta.addProperty("error", true);
				respuesta.addProperty("code", 1);
				respuesta.addProperty("errorMsg", "Datos no establecidos");
				
				sesion.getBasicRemote().sendText(respuesta.toString());
				
				return;
			}
			
			String accion = datos.get("accion").getAsString();
			
			if (accion == null || accion.isEmpty())
			{
				respuesta.addProperty("error", true);
				respuesta.addProperty("code", 2);
				respuesta.addProperty("errorMsg", "Accion no establecida");
				
				sesion.getBasicRemote().sendText(respuesta.toString());
				
				return;
			}
			
			Map<String,Object> propiedades = sesion.getUserProperties();
			SesionChat sesionChat = (SesionChat) propiedades.get("sesion");
			Basic remote = sesion.getBasicRemote();
			
			HttpSession s = (HttpSession) propiedades.get("sesion");
			Connection conexion = (Connection) s.getAttribute("conexion");
			
			MChats mdlChats;
			MChatsMensajes mdlChatsMensajes;
			
			long nowMillis = Calendar.getInstance().getTimeInMillis();
			
			switch (accion)
			{
				// Caso crear chat
				case "crear":
					
					if (sesionChat == null)
					{
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 4);
						respuesta.addProperty("errorMsg", "Sesion chat es nulo");
						
						remote.sendText(respuesta.toString());
						
					} else if (sesionChat.chat != null) {
						
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 5);
						respuesta.addProperty("errorMsg", "Chat ya existente");
						
						remote.sendText(respuesta.toString());
					}
					
					
					try
					{
						String 	nombre = datos.get("nombre").getAsString(),
								email = datos.get("email").getAsString(),
								asunto = datos.get("asunto").getAsString();
						
						mdlChats = new MChats(conexion);
						long chid = mdlChats.crearChat((String) propiedades.get("uuid"), nombre, email, asunto, nowMillis, (byte) 0);
						
						Chat chat = new Chat();
						chat.chid = chid;
						chat.nombre = nombre;
						chat.email = email;
						chat.asunto = asunto;
						chat.fecha = nowMillis;
						chat.estado = (byte) 0;
						
						sesionChat.chat = chat;
						
					} catch (NullPointerException x) {
						
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 6);
						respuesta.addProperty("errorMsg", "Algunos datos recibidos para crear el chat son nulos");
						
						remote.sendText(respuesta.toString());
					}
					
					break;
					
				case "mensaje":
					
					if (sesionChat == null)
					{
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 7);
						respuesta.addProperty("errorMsg", "Sesion chat es nulo");
						
						remote.sendText(respuesta.toString());
					}
					
					try
					{
						String 	msgChat = datos.get("mensaje").getAsString();
						long chid = datos.get("chid").getAsLong();
						
						mdlChatsMensajes = new MChatsMensajes(conexion);
						
						mdlChatsMensajes.insertaChatMensaje(sesionChat.chat.chid, true, msgChat, nowMillis);
						
						Iterator<Session> it;
						
						respuesta.addProperty("chid", chid);
						respuesta.addProperty("mensaje", msgChat);
						respuesta.addProperty("admin", sesionChat.esAdministrador);
						
						if (sesionChat.esAdministrador)
						{
							it = sesionesChat.iterator();
							Session z;
							SesionChat zc;
							
							while (it.hasNext())
							{
								z = it.next();
								zc = ((SesionChat) z.getUserProperties().get("sesion"));
								
								if ( zc != null && zc.chat.chid == chid )
								{	
									z.getBasicRemote().sendText(respuesta.toString());
								}
							}

						} else {
							
							it = sesionesChatAdmin.iterator();
							
							while (it.hasNext())
							{
								it.next().getBasicRemote().sendText(respuesta.toString());
							}
						}
						
					} catch (NullPointerException x) {
					
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 8);
						respuesta.addProperty("errorMsg", "Algún dato es nulo @mensaje");
						
						remote.sendText(respuesta.toString());
					}
					
					break;
				
				case "getMensajes":
					
					if (sesionChat == null)
					{
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 9);
						respuesta.addProperty("errorMsg", "Sesion chat es nulo");
						
						remote.sendText(respuesta.toString());
					}
					
					try
					{
						long chid = datos.get("chid").getAsLong();
					
						if (!sesionChat.esAdministrador && ((sesionChat.chat != null && sesionChat.chat.chid != chid) || sesionChat.chat == null))
						{
							respuesta.addProperty("error", true);
							respuesta.addProperty("code", 10);
							respuesta.addProperty("errorMsg", "Prohibido. No tiene permisos");
						}
						
						mdlChatsMensajes = new MChatsMensajes(conexion);
						
						mdlChatsMensajes.getMensajesByChid(chid);
						
						JsonArray mensajes = new JsonArray();
						JsonObject _mensaje;
						
						while (mdlChatsMensajes.getProximoChatMensaje())
						{
							_mensaje = new JsonObject();
							_mensaje.addProperty("admin", ! mdlChatsMensajes.esUsuario);
							_mensaje.addProperty("fecha", mdlChatsMensajes.fecha);
							_mensaje.addProperty("mensaje", mdlChatsMensajes.mensaje);
							
							mensajes.add(_mensaje);
						}
						
						sesion.getBasicRemote().sendText(mensajes.toString());
					
					} catch (NullPointerException x) {
						
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 11);
						respuesta.addProperty("errorMsg", "Algún dato es nulo @mensaje");
						
						remote.sendText(respuesta.toString());
					}
					
					break;
					
				case "desconectar":
					
					if (sesionChat == null)
					{
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 12);
						respuesta.addProperty("errorMsg", "Sesion chat es nulo");
						
						remote.sendText(respuesta.toString());
					}
					
					try
					{
						long chid = datos.get("chid").getAsLong();  
					
						mdlChats = new MChats(conexion);
						
						if (mdlChats.getChatByChid(chid))
						{
							mdlChats.estado = (byte) 2; // finalizado
							mdlChats.actualizaChat();
						}
						
						// Destruir variables de sesión de chat
						sesionChat.chat = null;
						sesionChat = null;
						
						respuesta.addProperty("exito", true);
						
					} catch (NullPointerException x) {
						respuesta.addProperty("error", true);
						respuesta.addProperty("code", 13);
						respuesta.addProperty("errorMsg", "chid es nulo");
						
						remote.sendText(respuesta.toString());
					}
					
					break;
			}

		} catch (IOException x) {
			
			System.err.println("Error de entrada/salida @WsChat:procesarMensaje(String, Session)");
			System.err.println("Fecha: " + Calendar.getInstance().getTime());
			System.err.println("Traza:");
			x.printStackTrace();
		}
	}
}
