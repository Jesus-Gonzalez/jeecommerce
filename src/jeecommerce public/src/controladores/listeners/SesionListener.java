package controladores.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import modelos.Carro;
import modelos.Conexion;
import modelos.SesionUsuario;

/**
 * 
 * Listener propio para la sesión
 * 
 * @author jesus
 *
 */
public class SesionListener implements HttpSessionListener {

  @Override
  /**
   * Método que se llama cuando se crea una sesión por primera vez
   */
  public synchronized void sessionCreated(HttpSessionEvent hse)
  {
	  // Se obtiene la sesión
	  HttpSession sesion = hse.getSession();
	  // Se establece el tiempo que la sesión estará activa
	  sesion.setMaxInactiveInterval(7200);
	  
	  // Se obtiene el Contexto del servlet
	  ServletContext context = sesion.getServletContext();
	  // Se obtiene el número de usuarios conectados
	  Integer numConectados = (Integer) context.getAttribute("usuarios.conectados");

	  // Crea conexión y almacena el objeto en la sesión del usuario
	  Conexion conexion = new Conexion(	context.getInitParameter("dbHostname"),
			  							context.getInitParameter("dbDatabase"),
			  							context.getInitParameter("dbUsername"),
			  							context.getInitParameter("dbPassword"));

	  // Se crea un objeto SesionUsuario y se inicializan sus atributos
	  SesionUsuario session = new SesionUsuario();
	  session.estado = SesionUsuario.CONECTADO;
	  session.carro = new Carro();

	  // Se establece el objeto SesionUsuario en la sesión
	  sesion.setAttribute("usuario", session);
	  // Se establece la conexión en la sesión del usuario
	  sesion.setAttribute("conexion", conexion.creaConexion());
	  
	  // Si el número de usuarios conectados es nulo, se inicializa a 0
	  numConectados = (numConectados != null) ? numConectados : 0;
	  // Se establece en el contexto de la aplicación el número de usuario conectados incrementado
	  context.setAttribute("usuarios.conectados", ++numConectados);
  }
	  
  @Override
  /**
   * Método que es llamado cuando una sesión es destruida/finalizada
   */
  public synchronized void sessionDestroyed(HttpSessionEvent hse)
  {
	  // Se obtiene el objeto de sesión
	  HttpSession sesion = hse.getSession();
	  // Se obtiene el contexto de la aplicación
	  ServletContext context = sesion.getServletContext();
	  
//	  SesionUsuario s = (SesionUsuario) sesion.getAttribute("sesion");
	  
	  // Comprobar si se realizó conexión a la base de datos y cerrar la conexión limpiamente para liberar recursos
	  Connection conexion = (Connection) sesion.getAttribute("conexion");
	  
	  // Si la conexión no es nula
	  if (conexion != null)
	  {
		  try
	  	  {
			  // se cierra la conexión
			  conexion.close();
		
		  // Si sucede una excepción cerrando la conexión, se muestra un mensaje de depuración
	  	  } catch (SQLException x) {
	  		
	  		  System.err.println("Error SQL - SesionListener:sessionDestroyed:conexion.close()");
	  	  }
	  }
	  
	  // Se decrementa el número de usuarios conectados al sitio
	  int numConectados = (int) context.getAttribute("usuarios.conectados");
	  context.setAttribute("usuarios.conectados", --numConectados);
  }	
}