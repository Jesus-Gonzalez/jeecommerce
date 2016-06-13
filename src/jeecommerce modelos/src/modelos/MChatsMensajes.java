package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MChatsMensajes
{
	public long chmid,
				chid;
	
	public boolean esUsuario;
	
	public String mensaje;
	
	public long fecha;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MChatsMensajes(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoChatMensaje()
	{
		try
		{
			if (rs.next())
			{
				chmid = rs.getLong("chmid");
				chid = rs.getLong("chid");
				esUsuario = rs.getBoolean("es_usuario");
				mensaje = rs.getString("mensaje");
				fecha = rs.getTimestamp("fecha").getTime();
				
				return false;
			}

		} catch (SQLException x) {
				
			System.err.println("Error SQL -> MChatsMensajes:getProximoChatMensaje()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-86450);
		}
		
		return false;
	}
	
	public void getMensajesByChid(long chid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM chats.chats_mensajes WHERE chid = ?");
			ps.setLong(1, chid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:getMensajesByChid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-86451);
		}
	}
	
	public boolean insertaChatMensaje(long chid, boolean esUsuario, String mensaje, long fecha)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO chats.chats_mensajes (chid, esUsuario, mensaje, fecha) VALUES (?, ?, ?, ?)");
			ps.setLong(1, chid);
			ps.setBoolean(2, esUsuario);
			ps.setString(3, mensaje);
			ps.setTimestamp(4, new Timestamp(fecha));
				
			return ps.executeUpdate() > 0;

		} catch (SQLException x) {
				
			System.err.println("Error SQL -> MChatsMensajes:getProximoChatMensaje()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-86452);
		}
		
		return false;		
	}
}
