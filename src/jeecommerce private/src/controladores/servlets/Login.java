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

import modelos.MAdministradores;
import modelos.SesionUsuario;
import modelos.Usuario;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion != null && sesion.esAdministrador && sesion.estado == SesionUsuario.LOGUEADO)
		{
			response.sendRedirect("dashboard");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");

		if (sesion != null && sesion.esAdministrador && sesion.estado == SesionUsuario.LOGUEADO)
		{
			request.getRequestDispatcher("dashboard").forward(request, response);
			return;
		}
		
		JsonObject datos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		if (!datos.has("nombre") || !datos.has("contrasena"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado ni nombre de usuario ni contraseña para el administrador.");
			return;
		}
		
		JsonObject respuesta = new JsonObject();
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MAdministradores mdlAdministradores = new MAdministradores(conexion);
		if (!mdlAdministradores.autenticaAdministrador(datos.get("nombre").getAsString(), datos.get("contrasena").getAsString()))
		{
			respuesta.addProperty("error", true);
			
		} else {
			
			respuesta.addProperty("exito", true);
			
			sesion = new SesionUsuario();
			sesion.esAdministrador = true;
			sesion.estado = SesionUsuario.LOGUEADO;
			
			sesion.usuario = new Usuario();
			sesion.usuario.uid = mdlAdministradores.adminid;
			
			hs.setAttribute("usuario", sesion);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(respuesta.toString());
	}

}
