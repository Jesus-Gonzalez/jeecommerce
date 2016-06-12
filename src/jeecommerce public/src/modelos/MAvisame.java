package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MAvisame
{
	public long aid;
	public boolean avisado;
	public String correo;
	
	private Connection conexion;
//	private ResultSet rs;
	
	public MAvisame (Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean crearAvisame(long artid, String correo)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO avisos_stock (artid, correo) VALUES (?, ?)");
			ps.setLong(1, artid);
			ps.setString(2, correo);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			x.printStackTrace();
			System.exit(-111010);
			// TODO: Proper error handle
		}
		
		return false;
	}
	
//	TODO CODE Avisame methods
//	public boolean getProximoAvisame()
//	{
//		try
//		{
//			if (rs.next())
//			{
//				// 
//			}
//		} catch (SQLException x) {
//			// TODO: Muestra errores
//		}
//	}
//	etc...
}
