package app;

import modelos.Conexion;
import modelos.MBienvenida;
import utils.Correo;

public class Main
{
	// DB Constantes
	public static String DB_HOSTNAME = "localhost",
						 DB_DATABASE = "jeecommerce",
						 DB_USERNAME = "commerce",
						 DB_PASSWORD = "commerce";
	
	public static void main(String[] args)
	{
		Conexion conexion = new Conexion("localhost", "jeecommerce", "commerce", "commerce");
		
		MBienvenida mdlBienvenida = new MBienvenida(conexion.creaConexion());
		
		mdlBienvenida.getUsuariosParaDarBienvenida();
		
		Correo utlCorreo;
		
		// Enviar mensaje de bienvenida a nuevos usuarios
		while (mdlBienvenida.getProximaBienvenida())
		{
			utlCorreo = new Correo(mdlBienvenida.correo);
			utlCorreo.enviarCorreoBienvenida(mdlBienvenida.nombre);
		}
		
		// Borrar los usuarios que ya han sido bienvenidos
		mdlBienvenida.eliminarUsuariosYaBienvenidos();
		
		// Salimos de la aplicación con código 0
		System.exit(0);
	}
}