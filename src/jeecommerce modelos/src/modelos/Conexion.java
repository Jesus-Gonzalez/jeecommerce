package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
	private Connection conexion;
	
	private String hostname, database, username, password;
	private int port;
	
	
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
	
	public Conexion(String hostname, int port, String database, String username, String password)
	{
		this();
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		this.conexion = creaConexion();
	}
	
	public Conexion(String hostname, String database, String username, String password)
	{
		this(hostname, 5432, database, username, password);
	}
	
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

	public Connection getConexion()
	{
		return conexion;
	}
}
