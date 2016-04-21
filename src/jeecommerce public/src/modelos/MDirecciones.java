package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MDirecciones
{
	public long did,
				uid;
	
	public String 	direccion,
					localidad,
					codigoPostal,
					provincia,
					nombre,
					telefono;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MDirecciones(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximaDireccion()
	{
		try
		{
			if (rs.next())
			{
				did = rs.getLong("did");
				uid = rs.getLong("uid");
				direccion = rs.getString("direccion");
				localidad = rs.getString("localidad");
				codigoPostal = rs.getString("codigo_postal");
				provincia = rs.getString("provincia");
				nombre = rs.getString("nombre");
				telefono = rs.getString("telefono");
				
				return true;
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:getProximaDireccion()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-740);
		}
		
		return false;
	}
	
	
	public void getDireccionesByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM direcciones WHERE uid = ?");
			ps.setLong(1, uid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:getDireccionesByUid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-741);
		}
	}
	
	public void getDireccionByDid(long did)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM direcciones WHERE did = ?");
			ps.setLong(1, did);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:getDireccionByDid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-742);
		}
	}
	
	public boolean eliminaDireccion(long did)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM direcciones WHERE did = ?");
			ps.setLong(1, did);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:eliminaDireccion()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-743);
		}
		
		return false;
	}
	
	public boolean eliminaDireccion()
	{
		return eliminaDireccion(did);
	}
	
	public boolean actualizaDireccion(long did, long uid, String direccion, String localidad, String codigoPostal, String provincia, String nombre, String telefono)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE direcciones SET uid = ?, direccion = ?, localidad = ?, codigo_postal = ?, provincia = ?, nombre = ?, telefono = ? WHERE did = ?");
			ps.setLong(1, uid);
			ps.setString(2, direccion);
			ps.setString(3, localidad);
			ps.setString(4, codigoPostal);
			ps.setString(5, provincia);
			ps.setString(6, nombre);
			ps.setString(7, telefono);
			ps.setLong(8, did);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:actualizaDireccion()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-744);
		}
		
		return false;
	}
	
	public boolean actualizaDireccion()
	{
		return actualizaDireccion(did, uid, direccion, localidad, codigoPostal, provincia, nombre, telefono);
	}
}