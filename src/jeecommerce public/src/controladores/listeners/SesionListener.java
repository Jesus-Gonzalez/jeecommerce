package controladores.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import modelos.Conexion;
import modelos.SesionUsuario;

public class SesionListener implements HttpSessionListener {

  @Override
  public synchronized void sessionCreated(HttpSessionEvent hse)
  {
	  HttpSession sesion = hse.getSession();
	  sesion.setMaxInactiveInterval(7200);
	  
	  ServletContext context = sesion.getServletContext();
	  Integer numConectados = (Integer) context.getAttribute("usuarios.conectados");

//		   Crea conexión y almacena el objeto en la sesión del usuario
	  Conexion conexion = new Conexion("localhost", "db", "user", "password");

	  SesionUsuario session = new SesionUsuario();
	  session.estado = SesionUsuario.CONECTADO;

	  sesion.setAttribute("usuario", session);
	  sesion.setAttribute("conexion", conexion.creaConexion());
	  
	  
	  numConectados = (numConectados != null) ? numConectados : 0;
	  context.setAttribute("usuarios.conectados", ++numConectados);
  }
	  
  @Override
  public synchronized void sessionDestroyed(HttpSessionEvent hse)
  {
	  HttpSession sesion = hse.getSession();
	  ServletContext context = sesion.getServletContext();
	  
	  SesionUsuario s = (SesionUsuario) sesion.getAttribute("sesion");
	  
	  // Comprobar si se realizó conexión a la base de datos y cerrar la conexión limpiamente para liberar recursos
	  Connection conexion = (Connection) sesion.getAttribute("conexion");
	  
	  if (conexion != null)
	  {
		  try
	  	  {
			  conexion.close();
			  
	  	  } catch (SQLException x) {
	  		
	  		  System.err.println("Error SQL - SesionListener:sessionDestroyed:conexion.close()");
	  	  }
	  }
	  
	  int numConectados = (int) context.getAttribute("usuarios.conectados");
	  context.setAttribute("usuarios.conectados", --numConectados);
  }	
}