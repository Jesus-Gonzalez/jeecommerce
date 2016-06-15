package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Clase propia para realizar las conexiones a la base de datos
 * 
 * @author jesus
 *
 */
public class Conexion
{
	// Nótese que los atributos aquí presentes son inmutables
	// y únicamente se establecen en el momento de la construcción del objeto
	private Connection conexion;
	
	private String hostname, database, username, password;
	private int port;
	
	/**
	 * Constructor por defecto
	 * Sólo inicializa el driver de PostgreSQL
	 */
	public Conexion()
	{
		try
		{
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException x) {
			
			System.err.println("JDBC para PostgreSQL no encontrado.");
			System.exit(-1000);
		}
	}
	
	/**
	 * Constructor sobrecargado
	 * 
	 * @param hostname
	 * Nombre del equipo donde se encuentra el servicio de PostgreSQL
	 * @param port
	 * Puerto donde escucha el servicio de PostgreSQL
	 * @param database
	 * Nombre de la base de datos
	 * @param username
	 * Nombre del usuario autorizado de PostgreSQL
	 * @param password
	 * Contraseña para el usuario autorizado de PostgreSQL
	 */
	public Conexion(String hostname, int port, String database, String username, String password)
	{
		// Se llama al constructor por defecto para cargar el driver
		this();
		// Se establecen los atributos de conexión de la clase según los parámetros recibidos
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		// Se crea la conexión
		this.conexion = creaConexion();
	}
	
	/**
	 * Constructor sobrecargado que establece el puerto por defecto de PostgreSQL
	 * @param hostname
	 * Nombre del equipo donde está el servicio de PostgreSQL
	 * @param database
	 * Nombre de la base de datos
	 * @param username
	 * Nombre del usuario autorizado de PostgreSQL
	 * @param password
	 * Contraseña del usuario autorizado de PostgreSQL
	 */
	public Conexion(String hostname, String database, String username, String password)
	{
		this(hostname, 5432, database, username, password);
	}
	
	/**
	 * Método que crea una conexión a la base de datos y devuelve la conexión que se ha realizado
	 * como un objeto de tipo Connection
	 * @return
	 * Objeto tipo Connection que apunta a la conexión realizada a la base de datos PostgreSQL
	 */
	public Connection creaConexion()
	{
		try
		{
			return DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + port + "/" + database , username, password);
		
		} catch (SQLException x) {
			
			System.err.println("Problema creando la conexión. Conexion:creaConexion(void)");
			x.printStackTrace();
			System.exit(-1002);
		}
		
		return null;
	}

	/**
	 * Método público que devuelve el atributo conexión
	 * @return
	 * Atributo conexión de la clase (objeto tipo Connection que apunta a la conexión realizada)
	 */
	public Connection getConexion()
	{
		return conexion;
	}
}
