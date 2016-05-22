package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MBancos
{
	public long bid;
	
	public String 	nombre,
					numero;
	
	public boolean activo;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MBancos(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoBanco()
	{
		try
		{
			if (rs.next())
			{
				bid = rs.getLong("bid");
				nombre = rs.getString("nombre");
				numero = rs.getString("numero");
				activo = rs.getBoolean("activo");
				
				return true;
			}

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MBancos:getProximoBanco()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-83830);
		}
		
		return false;
	}
	
	public void getBancos()
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM private.bancos");
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MBancos:getBancos()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-83831);
			
		}
	}
	
	public boolean creaBanco(String nombre, String numero, boolean activo)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO private.bancos (nombre, numero, activo) VALUES (?, ?, ?)"); 
			ps.setString(1, nombre);
			ps.setString(2, numero);
			ps.setBoolean(3, activo);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MBancos:creaBanco()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-83832);
			
		}
		
		return false;
	}
	
	public boolean actualizaBanco(long bid, String nombre, String numero, boolean activo)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE private.bancos SET nombre = ?, numero = ?, activo = ? WHERE bid = ?");
			ps.setString(1, nombre);
			ps.setString(2, numero);
			ps.setBoolean(3, activo);
			ps.setLong(4, bid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MBancos:actualizaBanco()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-83833);
		}
		
		return false;
	}
	
	public boolean actualizaBanco()
	{
		return actualizaBanco(bid, nombre, numero, activo);
	}
	
	public boolean borraBancoByBid(long bid)
	{
		try
		{
			
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM private.bancos WHERE bid = ?");
			ps.setLong(1, bid);
		
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MBancos:borraBanco()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-83834);	
		}
		
		return false;
	}
	
	public boolean borraBanco()
	{
		return borraBancoByBid(bid);
	}
}
