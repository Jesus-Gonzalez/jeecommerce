package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MCategorias
{
	
	public long catid,
				supercat;
	
	public String 	nombre,
					descripcion;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MCategorias(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximaCategoria()
	{
		try
		{
			if (rs.next())
			{
				catid = rs.getLong("catid");
				supercat = rs.getLong("supercat");

				nombre = rs.getString("nombre");
				descripcion = rs.getString("descripcion");
				
				return true;
				
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MCategorias:getProximaCategoria()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-540);
		}
		
		return false;
	}
	
	public void getCategoriasByCatId(long catid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM categorias WHERE catid = ?");
			ps.setLong(1, catid);
			
			rs = ps.executeQuery();
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:getArticuloByArtId()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-541);
		}	
	}
	
	public void getCategoriasBySuperCat(long supercat)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM categorias WHERE supercat = ?");
			ps.setLong(1, supercat);
			
			rs = ps.executeQuery();
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:getArticuloByArtId()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-542);
		}	
	}
	
	public long creaCategoria(long supercat, String nombre, String descripcion)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO categorias (supercat, nombre, descripcion) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, supercat);
			ps.setString(2, nombre);
			ps.setString(3, descripcion);
			
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
			{
				return rs.getLong(1);	
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MCategorias:creaCategoria()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-543);
		}
		
		return -1;
	}
	
	public long creaCategoria()
	{
		return creaCategoria(supercat, nombre, descripcion);
	}
	
	public boolean actualizaCategoria(long catid, long supercat, String nombre, String descripcion)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE categorias SET catid = ?, supercat = ?, nombre = ?, descripcion = ? WHERE catid = ?");
			
			ps.setLong(1, catid);
			ps.setLong(2, supercat);
			ps.setString(3, nombre);
			ps.setString(4, descripcion);
			ps.setLong(5, catid);
			
			int filasAfectadas = ps.executeUpdate();
			
			return filasAfectadas > 0; 
		
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MCategorias:actualizaCategoria()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-544);
		}
		
		return false;
	}
	
	public boolean actualizaCategoria()
	{
		return actualizaCategoria(catid, supercat, nombre, descripcion);
	}
	
	public boolean eliminaCategoria(long catid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM categoria WHERE catid = ?");
			
			ps.setLong(1, catid);
			
			int filasAfectadas = ps.executeUpdate();
			
			return filasAfectadas > 0; 
		
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:actualizaArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-545);
		}
		
		return false;
	}
	
	public boolean eliminaCategoria()
	{
		return eliminaCategoria(catid);
	}
}
