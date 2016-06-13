package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MChats
{
	public long chid;
	
	public String 	uuid, // Unique User ID
					nombre,
					email,
					asunto;
	
	public long fecha;
	
	public byte estado;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MChats(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoChat()
	{
		try
		{
			if (rs.next())
			{
				chid = rs.getLong("chid");
				uuid = rs.getString("uuid");
				nombre = rs.getString("nombre");
				email = rs.getString("email");
				asunto = rs.getString("asunto");
				fecha = rs.getTimestamp("fecha").getTime();
				estado = rs.getByte("estado");
				
				return true;
			}
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MChats:getProximoChat()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85450);
		}
		
		return false;
	}
	
	public boolean getChatByChid(long chid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM chats.chats WHERE chid = ?");
			ps.setLong(1, chid);
			
			rs = ps.executeQuery();
			
			return getProximoChat();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:getChatByChid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85455);
		}
		
		return false;
	}
	
	public void getLatestChats(int limit, int offset)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM chats.chats ORDER BY chid ASC LIMIT ? OFFSET ?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:getProximoChat()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85456);
		}
	}
	
	public long crearChat(String uuid, String nombre, String email, String asunto, long fecha, byte estado)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO chats.chats (uuid, nombre, email, asunto, fecha, estado) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, uuid);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.setString(4, asunto);
			ps.setTimestamp(5, new Timestamp(fecha));
			ps.setByte(6, estado);
			
			if (ps.executeUpdate() > 0)
			{
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getLong(1);
			}
			
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:crearChat()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85451);
		}
		
		return -1;
	}
	
	public boolean actualizaChat(long chid, String uuid, String nombre, String email, String asunto, long fecha, byte estado)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE chats.chats SET uuid = ?, nombre = ?, email = ?, asunto = ?, fecha = ?, estado = ? WHERE chid = ?");
			ps.setString(1, uuid);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.setString(4, asunto);
			ps.setTimestamp(5, new Timestamp(fecha));
			ps.setByte(6, estado);
			ps.setLong(7, chid);
			
			return ps.executeUpdate() > 0;
			
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:actualizaChat()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85452);
		}
		
		return false;
	}
	
	public boolean actualizaChat()
	{
		return actualizaChat(chid, uuid, nombre, email, asunto, fecha, estado);
	}
	
	public boolean eliminaChatByChid(long chid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM chats.chats WHERE chid = ?");
			ps.setLong(1, chid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:eliminaChatByChid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85453);
		}
		
		return false;
	}
	
	
	public boolean eliminaChatByChid()
	{
		return eliminaChatByChid(chid);
	}
	
	public boolean eliminaChatByUuid(String uuid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM chats.chats WHERE uuid = ?");
			ps.setString(1, uuid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MChats:eliminaChatByUuid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-85454);
		}
		
		return false;
	}
	
	
	public boolean eliminaChatByUuid()
	{
		return eliminaChatByUuid();
	}
}
