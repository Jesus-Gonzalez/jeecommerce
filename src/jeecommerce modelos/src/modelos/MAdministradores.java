package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MAdministradores
{
	public long adminid;
	public String nombre,
				  contrasena;
	
	private ResultSet rs;
	private Connection conexion;
	
	public MAdministradores(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoAdministrador()
	{
		try
		{
			if (rs.next())
			{
				adminid = rs.getLong("adminid");
				nombre = rs.getString("nombre");
				contrasena = rs.getString("contrasena");
				
				return true;
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:getProximoAdministrador()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4440);
		}
		
		return false;
	}
	
	public boolean autenticaAdministrador(String nombre, String contrasena)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM private.administradores WHERE nombre = ? AND contrasena = ?");
			ps.setString(1, nombre);
			ps.setString(2, contrasena);
			
			rs = ps.executeQuery();
			
			return getProximoAdministrador();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:autenticaAdministrador()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4441);
		}
		
		return false;
	}
	
	public void getAdministradores()
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM private.administradores");
			
			ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:getAdministradores()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4442);
		}
	}
	
	public void getAdministradorByAdminId(long adminid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM private.administradores WHERE adminid = ?");
			ps.setLong(1, adminid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:getADministradorByAdminId()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4443);
		}
	}
	
	public boolean creaAdministrador(String nombre, String contrasena)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO private.administradores (nombre, contrasena) VALUES (?, ?)"); 
			ps.setString(1, nombre);
			ps.setString(2, contrasena);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:creaAdministrador()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4444);
		}
		
		return false;
	}
	
	
	public boolean actualizaAdministrador(long adminid, String nombre, String contrasena)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE private.administradores SET adminid = ?, nombre = ?, contrasena = ? WHERE adminid = ?");

			ps.setLong(1, adminid);
			ps.setString(2, nombre);
			ps.setString(3, contrasena);
			ps.setLong(4, adminid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:actualizaAdministrador()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4445);
		}
		
		return false;
	}
	
	public boolean actualizaAdministrador()
	{
		return actualizaAdministrador(adminid, nombre, contrasena);
	}
	
	public boolean borraAdministrador(long adminid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM private.administradores WHERE adminid = ?");
			ps.setLong(1, adminid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MAdministradores:borraAdministrador()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-4446);
		}
		
		return false;
	}
	
	public boolean borraAdministrador()
	{
		return borraAdministrador(adminid);
	}
}

