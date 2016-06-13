package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MActivaciones
{
	public
		long aid,
			 uid;
	
	public
		String clave;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MActivaciones(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximaActivacion()
	{
		try
		{
			if (rs.next())
			{
				aid = rs.getLong("aid");
				uid = rs.getLong("uid");
				clave = rs.getString("clave");
				
				return true;
				
			} else {
				
				return false;
			}
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:getProximaActivacion()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-200);
		}
		
		return false;
	}
	
	public boolean getActivacionByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM activaciones WHERE uid = ?");
			
			rs = ps.executeQuery();
			
			return getProximaActivacion();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:getActivacionByUid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-201);
		}
		
		return false;
	}
	
	public boolean getActivacionByAid(long aid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM activaciones WHERE aid = ?");
			ps.setLong(1, aid);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				this.aid = rs.getLong("aid");
				uid = rs.getLong("uid");
//				intentos = rs.getInt("intentos");
//				fechaEnvio = rs.getLong("fecha_envio");
				clave = rs.getString("clave");
				
				return true;
				
			} else {
				
				return false;
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:getActivacionByAid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-202);
		}
		
		return false;
	}
	
	public long registraActivacion(long uid, String clave, long fechaLimite)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO activaciones (uid, clave, fecha_limite) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, uid);
			ps.setString(2, clave);
			ps.setTimestamp(3, new Timestamp(fechaLimite));
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return rs.getLong(1);
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:registraActivacion(long, String, long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-203);
		}
		
		return -1L;
	}
	
	public void borraActivacion()
	{
		borraActivacionByAid(aid);
	}
	
	public void borraActivacionByAid(long aid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM activaciones WHERE aid = ?");
			ps.setLong(1, aid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:borraActivacionByAid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-204);
		}
	}
	
	public void actualizarActivacion()
	{
		actualizarActivacion(aid, uid, clave);
	}
	
	public void actualizarActivacion(long aid, long uid, String clave)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE activaciones SET aid = ?, uid = ?, clave = ? WHERE aid = ?");
			ps.setLong(1, aid);
			ps.setLong(2, uid);
			ps.setString(3, clave);
			ps.setLong(4, aid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MActivaciones:actualizarActivacion(long, long, String, long, boolean)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-206);
		}
	}
}
