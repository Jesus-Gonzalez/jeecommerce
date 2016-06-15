package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 * Modelo de activaciones
 * 
 * @author jesus
 *
 */
public class MActivaciones
{
	public
		long aid,
			 uid;
	
	public
		String clave;
	
	private Connection conexion;
	private ResultSet rs;
	
	/**
	 * Constructor único para el modelo
	 * 
	 * @param conexion
	 * Objeto de tipo Connection con una conexión activa a la base de datos
	 */
	public MActivaciones(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	/**
	 * 
	 * Método que obtiene la próxima activación y establece los datos en los atributos públicos de la clase actual
	 * 
	 * @return
	 * true si existe una activación próxima
	 * false en caso contrario
	 */
	public boolean getProximaActivacion()
	{
		try
		{
			// Si existe otro registro
			if (rs.next())
			{
				// Se establecen los atributos públicos de la clase según los datos leídos de la base de datos
				aid = rs.getLong("aid");
				uid = rs.getLong("uid");
				clave = rs.getString("clave");
				
				// Se devuelve true
				return true;
			} else {
				// TODO: Importante: ¡CERRAR SIEMPRE LOS RECURSOS EN TODOS LOS MODELOS!
				rs.close();
			}
			
		// Si ocurre una excepción SQL se para la ejecución de la aplicación y se muestran mensajes de depuración
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:getProximaActivacion()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-200);
		}
		
		// Se devuelve false si no existe otro registro
		return false;
	}
	
	/**
	 * Método que obtiene una activación según el ID de usuario
	 * 
	 * @param uid
	 * ID del usuario a obtener la activación
	 * @return
	 * True si existe la activación
	 * False en caso contrario
	 */
	public boolean getActivacionByUid(long uid)
	{
		try
		{
			// Se crea un PreparedStatement para prevenir ataques SQL Injection activos
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM activaciones WHERE uid = ?");
			
			// Se ejecuta la sentencia y se establece el resultado en la propiedad ResultSet privada de la clase
			rs = ps.executeQuery();
		
			// Devuelve el resultado de mover al próximo registro y establecer los atributos de la clase
			return getProximaActivacion();
			
		// Si sucede algún error
		} catch (SQLException x) {
			
			// Mostrar mensajes de depuración y salir de la aplicación
			System.err.println("Error SQL -> MActivaciones:getActivacionByUid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-201);
		}
		
		// Se devuelve false en cualquier caso que no esté previsto (aunque nunca se va a llegar a este punto)
		return false;
	}
	
	/**
	 * Método que obtiene activación por el ID de activación
	 * 
	 * @param aid
	 * ID de la activación
	 * @return
	 * True si existe tal activación
	 * False en caso contrario
	 */
	public boolean getActivacionByAid(long aid)
	{
		try
		{
			// Se crea la sentencia y se establecen parámetros
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM activaciones WHERE aid = ?");
			ps.setLong(1, aid);
			
			// Se ejecuta la sentencia
			ResultSet rs = ps.executeQuery();
			
			// Si ha devuelto registros
			if (rs.next())
			{
				// Se establecen los datos leídos en atributos de la clase 
				this.aid = rs.getLong("aid");
				uid = rs.getLong("uid");
//				intentos = rs.getInt("intentos");
//				fechaEnvio = rs.getLong("fecha_envio");
				clave = rs.getString("clave");
				
				// Se cierra el ResultSet
				rs.close();
				
				// Se devuelve true
				return true;
			
			// Si no se han devuelto registros
			} else {
				
				// Se cierra el ResultSet
				rs.close();
				
				// y se devuelve false
				return false;
			}
			
		// Si ocurre una excepción se lanzan mensajes de depuración y se sale de la aplicación
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:getActivacionByAid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-202);
		}
		
		// En cualquier otro caso se devuelve false (nunca se debe de llegar aquí)
		return false;
	}
	
	
	/**
	 * 
	 * Método que registra una activación para un usuario
	 * 
	 * @param uid
	 * ID del usuario
	 * @param clave
	 * Clave de la activación
	 * @param fechaLimite
	 * Fecha límite para que la cuenta sea activada
	 * @return
	 * ID de publicación como número long (64bits)
	 */
	public long registraActivacion(long uid, String clave, long fechaLimite)
	{
		try
		{
			// Se crea la sentencia y se establecen parámetros
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO activaciones (uid, clave, fecha_limite) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, uid);
			ps.setString(2, clave);
			ps.setTimestamp(3, new Timestamp(fechaLimite));
			
			// Se ejecuta la inserción
			if (ps.executeUpdate() > 0)
			{
				// Se obtienen las claves generadas y se mueve un registro hacia delante
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
			
				// Se devuelve la clave generada para la activación
				return rs.getLong(1);
			}
			
		// Si ocurre una excepción se lanzan mensajes de depuración y se sale de la aplicación
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:registraActivacion(long, String, long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-203);
		}
		
		// Si no se ha insertado con éxito la activación se devuelve -1
		return -1L;
	}
	
	/**
	 * Método que borra una activación según el código de activación de la clase modelo en sí misma
	 */
	public void borraActivacion()
	{
		// Llama al método pasando el aid de la clase actual
		borraActivacionByAid(aid);
	}
	
	/**
	 * Método que borra una activación según un ID de activación
	 * 
	 * @param aid
	 * Código de activación
	 */
	public void borraActivacionByAid(long aid)
	{
		try
		{
			// Se crea la sentencia y se establecen parámetros
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM activaciones WHERE aid = ?");
			ps.setLong(1, aid);
			
			// Se ejecuta la sentencia
			ps.executeUpdate();
		
		// Si suede una excepción se lanzan mensajes de depuración y se sale de la aplicación
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:borraActivacionByAid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-204);
		}
	}
	
	/**
	 * Método que actualiza una activación según los valores del objeto actual
	 */
	public void actualizarActivacion()
	{
		// Llama al método actualizar pasando como valores los atributos del objeto actual
		actualizarActivacion(aid, uid, clave);
	}
	
	/**
	 * 
	 * Método que actualiza una activación en la base de datos
	 * 
	 * @param aid
	 * Código de activación
	 * @param uid
	 * Código del usuario
	 * @param clave
	 * Clave de la activación
	 */
	public void actualizarActivacion(long aid, long uid, String clave)
	{
		try
		{
			// Se crea la sentencia y se establecen parámetros
			PreparedStatement ps = conexion.prepareStatement("UPDATE activaciones SET aid = ?, uid = ?, clave = ? WHERE aid = ?");
			ps.setLong(1, aid);
			ps.setLong(2, uid);
			ps.setString(3, clave);
			ps.setLong(4, aid);
			
			// Se ejecuta la sentencia
			ps.executeUpdate();
			
		// Si sucede alguna excepción se lanzan mensajes de depuración y se sale de la aplicación
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:actualizarActivacion(long, long, String, long, boolean)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-206);
		}
	}
}
