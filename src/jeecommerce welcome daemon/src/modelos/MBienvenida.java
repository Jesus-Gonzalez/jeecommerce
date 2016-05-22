package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MBienvenida
{
	public long uid,
				bid;
	
	public boolean enviado;
	
	public String correo,
				  nombre;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MBienvenida(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximaBienvenida()
	{
		try
		{
			if (rs.next())
			{
				uid = rs.getLong("uid");
				bid = rs.getLong("bid");
				nombre = rs.getString("nombre");
				correo = rs.getString("correo");
				enviado = rs.getBoolean("enviado");

				return true;
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQLException -> MBienvenida:getProximaBienvenida()");
			System.err.println("Mensaje de error: " + x.getMessage());
			System.err.println("Traza del error:");
			x.printStackTrace();
			
			System.exit(-101010);
			
		}
		
		return false;
	}
	
	public void getUsuariosParaDarBienvenida()
	{
		try
		{
			
			// Inner Join para obtener correo del usuario
			PreparedStatement ps = conexion.prepareStatement("SELECT bid, bienvenida.uid, nombre, correo, enviado FROM bienvenida INNER JOIN usuarios ON bienvenida.uid = usuarios.uid WHERE enviado = ?");
			ps.setBoolean(1, false);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQLException -> MBienvenida:getProximaBienvenida()");
			System.err.println("Mensaje de error: " + x.getMessage());
			System.err.println("Traza del error:");
			x.printStackTrace();
			
			System.exit(-101011);
		}
	}
	
	public boolean eliminarUsuariosYaBienvenidos()
	{
		try
		{
			
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM bienvenida WHERE enviado = ?");
			ps.setBoolean(1, true);
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQLException -> MBienvenida:getProximaBienvenida()");
			System.err.println("Mensaje de error: " + x.getMessage());
			System.err.println("Traza del error:");
			x.printStackTrace();
			
			System.exit(-101011);
		}
		
		return false;
	}
}
