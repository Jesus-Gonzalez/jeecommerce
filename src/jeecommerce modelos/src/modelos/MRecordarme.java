package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MRecordarme
{
	private Connection conexion;
	private ResultSet rs;
	
	public
		String 	id,
				token,
				salt;
	
	public
		long uid;

	
	public MRecordarme(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoRecordarme()
	{
		try
		{
			if (rs.next())
			{
				id = rs.getString("id");
				token = rs.getString("token");
				salt = rs.getString("salt");
				uid = rs.getLong("uid");
				
				return true;
				
			} else {
				
				return false;
			}

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MRecordarme:getProximoRecordarme(void)");
			System.err.println("Mensaje de error -> " + x.getMessage());
						
			x.printStackTrace();
			System.exit(-5000);
		}
		
		return false;
	}
	
	public boolean buscaRecordarmeByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT id, token, salt, uid FROM recordarme WHERE uid = ?");
			ps.setLong(1, uid);
			
			rs = ps.executeQuery();
			
			return getProximoRecordarme();
			
		} catch (SQLException x) {

			System.err.println("Error SQL -> MRecordarme:buscaRecordarmeByUid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-5001);
		}
		
		return false;
	}
	
	public boolean buscaRecordarmeById(String id)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT id, token, salt, uid FROM recordarme WHERE id = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			return getProximoRecordarme();
			
		} catch (SQLException x) {
	
			System.err.println("Error SQL -> MRecordarme:buscaRecordarmeById(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-5002);
		}
		
		return false;
	}
	
	public void crearRecordarme(String id, String token, String salt, long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO recordarme (id, token, salt, uid) VALUES (?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setString(2, token);
			ps.setString(3, salt);
			ps.setLong(4, uid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MRecordarme:crearRecordarme(String, String, String long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-5003);
		}
	}
	
	public void crearRecordarme()
	{
		crearRecordarme(id, token, salt, uid);
	}
	
	public void actualizaRecordarme(String id, String token, String salt, long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE recordarme SET token = ?, salt = ?, uid = ? WHERE id = ?");
			ps.setString(1, token);
			ps.setString(2, salt);
			ps.setLong(3, uid);
			ps.setString(4, id);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MRecordarme:actualizaRecordarme(String, String, String, long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
				
			x.printStackTrace();
			System.exit(-5004);
		}
	}
	
	public void actualizaRecordarme()
	{
		actualizaRecordarme(id, token, salt, uid);
	}
	
	public void borrarRecordarme(String id)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM recordarme WHERE id = ?");
			ps.setString(1, id);
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MRecordarme:borrarRecordarme(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-5005);
		}
	}
	
	public void borrarRecordarme()
	{
		borrarRecordarme(id);
	}
}
