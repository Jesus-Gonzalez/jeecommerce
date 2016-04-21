package controladores.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import helpers.RecordarmeHelper;
import modelos.MUsuarios;
import utils.Correo;

@WebServlet("/usuarios/olvido")
public class UsuariosOlvido extends HttpServlet {
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
		
		PrintWriter out = response.getWriter();
		
		Connection conexion = (Connection) sesion.getAttribute("conexion");
		
		RecordarmeHelper hlpRecordarme = new RecordarmeHelper();
		if (hlpRecordarme.comprobarCookieRecordarme(request, response, sesion))
		{
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JsonObject 	json = new JsonObject(), jsonErrores = new JsonObject(), jsonRequest;
		JsonElement reqElement = new JsonParser().parse(request.getReader());

		if (reqElement.isJsonObject())
		{
			jsonRequest = reqElement.getAsJsonObject();
			
			if (jsonRequest.has("tipo") && jsonRequest.has("email"))
			{
				String 	correo = jsonRequest.get("email").getAsString(),
						tipo = jsonRequest.get("tipo").getAsString();
				
				MUsuarios mdlUsuarios = new MUsuarios(conexion);
				
				mdlUsuarios.getUsuarioByCorreo(correo);
				
				if (mdlUsuarios.getProximoUsuario())
				{
					Correo utlCorreo = new Correo(correo);
					
					if (tipo.equals("usuario"))
					{
						utlCorreo.enviarNombreDeUsuario(mdlUsuarios.nombre);
						
					} else if (tipo.equals("contrasena")) {
						
						utlCorreo.enviarContrasena(mdlUsuarios.contrasena);
						
					} else {
						
						response.sendError(HttpServletResponse.SC_FORBIDDEN);
						return;
					}
					
					json.addProperty("success", true);
					out.write(json.toString());
					return;
					
				} else {
					
					jsonErrores.addProperty("existe", true);
					json.add("error", jsonErrores);
					out.write(json.toString());
					return;
				}
				
			}
		}
		
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);		
	}

}
