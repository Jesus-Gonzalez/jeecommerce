package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo
{
	private Message mensaje;

	private String correo;
	
	public Correo(String correo)
	{
		this.correo = correo;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mailgun.org");
		props.put("mail.smtp.port", "587");

		Session sesion = Session.getInstance(props,
							  new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {
									return new PasswordAuthentication("postmaster@sandbox37e14fb025c04887818258cb2c0e7c8c.mailgun.org",
																	  "__PRIVATE__");
								}
							  });
		
		mensaje = new MimeMessage(sesion);
	}
	
	public void enviarCorreoHtml(String html, String from, String titulo)
	{
		try
		{
			mensaje.setFrom(new InternetAddress(from));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
			mensaje.setSubject(titulo);
			mensaje.setContent(html, "text/html; charset=utf-8");
			
			Transport.send(mensaje);
			
		} catch (MessagingException x) {
			
			System.err.println("Error Envío Email -> Correo:enviarCorreoHtml(String, String, String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-750);
			
		}
	}
	
	public void enviarCorreoActivacion(String url, String clave, long aid)
	{	
		String html = 	"<h1>Java EE Commerce</h1>" +
						"<h2>Activación de Cuenta</h2>" +
						"<p>Ha recibido este correo electrónico para activar su cuenta en 'Java EE Commerce'</p>" +
						"<p>Una vez acceda al enlace siguiente, podrá empezar a desafiar a otros usuarios.</p>" +
						"<p><a href='" + url + "/activacion.jsp?id=" + aid + "&clave=" + clave + "'>Click aquí para activar su cuenta</a></p>" +
						"<p>En caso de no disponer de un cliente de correo que soporte formato HTML, copie y pegue la siguiente dirección en la barra de navegación de su navegador web:</p>" +
						"<p>" + url + "/activacion.jsp?id=" + aid + "&clave=" + clave + "</p>" +
						"<br>" +
						"<p><strong>Java EE Commerce</strong></p>";
	
		enviarCorreoHtml(html, "activaciones@jeecommerce", "Java EE Commerce - Activar Cuenta");
		
	}
	
	public void enviarCorreoAviso()
	{	
		// TODO URL
		String html = 	"<h1>Java EE Commerce</h1>" +
						"<h2>Activación de Cuenta</h2>" +
						"<p>Le recordamos que debe activar su cuenta en el sitio.</p>" +
						"<p>Dentro de 3 días, si no ha activado su cuenta, procederemos a eliminarla por completo del sitio.</p>" +
						"<p>Nota: <i>Si no ha recibido la clave de activación, puede solicitar una clave nueva desde el sitio web.</i></p>" +						
						"<br>" +
						"<p><strong>Java EE Commerce</strong></p>";
	
		enviarCorreoHtml(html, "activaciones@jeecommerce", "Java EE Commerce - Aviso de Activación de Cuenta");
	}
	
	public void enviarCorreoCuentaEliminada()
	{
		String html = 	"<h1>Java EE Commerce</h1>" +
				"<h2>Cuenta Eliminada</h2>" +
				"<p>Su cuenta ha sido eliminada del sitio.</p>" +
				"<p>Si lo desea, puede volver a registrarse, pero recuerde activar su cuenta completamente.</p>" +						
				"<br>" +
				"<p><strong>Java EE Commerce</strong></p>";

		enviarCorreoHtml(html, "activaciones@jeecommerce", "Java EE Commerce - Aviso de Activación de Cuenta");
	}
	
	public void enviarNombreDeUsuario(String nombre)
	{
		String html = 	"<h1>Java EE Commerce</h1>" +
				"<h2>Recordar Nombre</h2>" +
				"<p>Usted ha solicitado recuperar su nombre.</p>" +
				"<p>Su nombre de usuario en el sitio es: <strong>" + nombre + "</strong>.</p>" +						
				"<br>" +
				"<p><strong>Java EE Commerce</strong></p>";

		enviarCorreoHtml(html, "activaciones@jeecommerce", "Java EE Commerce - Recuperación de Nombre Usuario");
	}
	
	public void enviarContrasena(String contrasena)
	{
		String html = 	"<h1>Java EE Commerce</h1>" +
				"<h2>Recordar Contraseña</h2>" +
				"<p>Usted ha solicitado recuperar su contraseña.</p>" +
				"<p>Su contraseña en el sitio es: <strong>" + contrasena + "</strong>.</p>" +						
				"<br>" +
				"<p><strong>Java EE Commerce</strong></p>";

		enviarCorreoHtml(html, "activaciones@jeecommerce", "Java EE Commerce - Recuperación de Contraseña");
	}
	
	public void enviarPedidoRealizado(long pid)
	{
		String html = 	"<h1>Java EE Commerce</h1>" +
				"<h2>Pedido realizado</h2>" +
				"<p>Usted ha realizado un pedido en Java EE Commerce.</p>" +
				"<p>En caso de ser necesario, nos pondremos en contacto con usted.</p>" +
				"<p>Por favor, guarde este email y su identificador de pedido: <strong>" + pid + "</strong> para futuras referencias.</p>" +						
				"<br>" +
				"<p><strong>Java EE Commerce</strong></p>";

		enviarCorreoHtml(html, "pedidos@jeecommerce", "Java EE Commerce - Pedido Realizado");
	}
	
	public void enviarCorreoBienvenida(String nombre)
	{
		String html = 	"<h1>Java EE Commerce</h1>" +
				"<h2>Bienvenido a Java EE Commerce</h2>" +
				"<p>Hola " + nombre + ",</p>" +
				"<p>Usted se ha registrado en Java EE Commerce. Esperamos que tenga la mejor experiencia en nuestro sitio.</p>" +
				"<br>" +
				"<p><strong>Java EE Commerce</strong></p>";

		enviarCorreoHtml(html, "bienvenido@jeecommerce", "Bienvenido a Java EE Commerce");
	}
}
