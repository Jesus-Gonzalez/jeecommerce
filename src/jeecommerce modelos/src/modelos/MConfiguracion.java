package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MConfiguracion
{
	public String parametro; // PK
	public String valor;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MConfiguracion(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoParametro()
	{
		try
		{
			if (rs.next())
			{
				parametro = rs.getString("parametro");
				valor = rs.getString("valor");
				return true;
			}
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MUsuarios:getProximoParametro()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-1150);
		}
		
		return false;
	}
	
	public void getParametro(String parametro)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM private.configuracion WHERE parametro = ?");
			ps.setString(1, parametro);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MConfiguracion:getParametro()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-1151);
		}
	}
	
	public boolean creaParametro(String parametro, String valor)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO private.configuracion (parametro, valor) VALUES (?, ?)");
			ps.setString(1, parametro);
			ps.setString(2, valor);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MConfiguracion:creaParametro()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-1152);
		}
		
		return false;
	}
	
	public boolean actualizaParametro(String parametro, String valor)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE private.configuracion SET valor = ? WHERE parametro = ?");
			ps.setString(1, valor);
			ps.setString(2, parametro);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MConfiguracion:actualizaParametro()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-1153);
		}
		
		return false;
	}
	
	public boolean eliminaParametro(String parametro)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM private.configuracion WHERE parametro = ?");
			ps.setString(1, parametro);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			System.err.println("Error SQL -> MConfiguracion:eliminaParametro()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-1154);
		}
		
		return false;
	}
}
