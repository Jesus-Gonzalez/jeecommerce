package modelos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MArticulos
{
	
	public long artid,
				catid;
	
	public BigDecimal precio;
	
	public String 	nombre,
					descripcion,
					imagen;
	
	public int 	minimo,
				stock;
	
	public long fechaCreacion;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MArticulos(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoArticulo()
	{
		try
		{
			if (rs.next())
			{
				artid = rs.getLong("artid");
				catid = rs.getLong("catid");

				nombre = rs.getString("nombre");
				descripcion = rs.getString("descripcion");
				imagen = rs.getString("imagen");
				
				precio = rs.getBigDecimal("precio");
				
				minimo = rs.getInt("minimo");
				stock = rs.getInt("stock");
				
				fechaCreacion = rs.getTimestamp("fecha_creacion").getTime();
				
				return true;
				
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:getPrimerUsuario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-440);
		}
		
		return false;
	}
	
	public void getArticuloByArtId(long artid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM articulos WHERE artid = ?");
			ps.setLong(1, artid);
			
			rs = ps.executeQuery();
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:getArticuloByArtId()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-441);
		}	
	}
	
	public void getArticulosByCatId(long catid, int limit, int offset)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM articulos WHERE catid = ? LIMIT ? OFFSET ?");
			ps.setLong(1, catid);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			
			rs = ps.executeQuery();
			
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:getArticulosByCatid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-450);
		}
	}
	
	public void getLatestArticulos(int limit, int offset)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM articulos ORDER BY artid DESC LIMIT ? OFFSET ?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
		
			System.err.println("Error SQL -> MArticulos:getLatestArticulos()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-451);
		}
	}
	
	public void buscarArticulos(String criterio, int limit, int offset)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM articulo WHERE nombre LIKE ? OR descripcion LIKE ? LIMIT ? OFFSET ?");
			ps.setString(1, "%" + criterio + "%");
			ps.setString(2, "%" + criterio + "%");
			ps.setInt(3, limit);
			ps.setInt(4, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:buscaArticulos()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-452);
		}
	}
	
	public long creaArticulo(long catid, BigDecimal precio, String nombre, String descripcion, String imagen, int stock, int minimo, long fechaCreacion)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO articulos (catid, precio, nombre, descripcion, imagen, stock, minimo, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, catid);
			ps.setBigDecimal(2, precio);
			ps.setString(3, nombre);
			ps.setString(4, descripcion);
			ps.setString(5, imagen);
			ps.setInt(6, stock);
			ps.setInt(7, minimo);
			ps.setTimestamp(8, new Timestamp(fechaCreacion));
			
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
			{
				return rs.getLong(1);
				
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:creaArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-441);
		}
		
		return -1;
	}
	
	public long creaArticulo()
	{
		return creaArticulo(catid, precio, nombre, descripcion, imagen, stock, minimo, fechaCreacion);
	}
	
	public boolean actualizaArticulo(long artid, long catid, BigDecimal precio, String nombre, String descripcion, String imagen, int stock, int minimo, long fechaCreacion)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE articulos SET catid = ?, precio = ?, nombre = ?, descripcion = ?, imagen = ?, stock = ?, minimo = ?, fecha_creacion = ? WHERE artid = ?");
			
			ps.setLong(1, catid);
			ps.setBigDecimal(2, precio);
			ps.setString(3, nombre);
			ps.setString(4, descripcion);
			ps.setString(5, imagen);
			ps.setInt(6, stock);
			ps.setInt(7, minimo);
			ps.setTimestamp(8, new Timestamp(fechaCreacion));
			ps.setLong(9, artid);
			
			int filasAfectadas = ps.executeUpdate();
			
			return filasAfectadas > 0; 
		
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:actualizaArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-442);
		}
		
		return false;
	}
	
	public boolean actualizaArticulo()
	{
		return actualizaArticulo(artid, catid, precio, nombre, descripcion, imagen, stock, minimo, fechaCreacion);
	}
	
	public boolean eliminaArticulo(long artid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM articulos WHERE artid = ?");
			
			ps.setLong(1, artid);
			
			int filasAfectadas = ps.executeUpdate();
			
			return filasAfectadas > 0; 
		
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:actualizaArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-442);
		}
		
		return false;
	}
	
	public boolean eliminaArticulo()
	{
		return eliminaArticulo(artid);
	}
	
	public int countProductosByCatId(long catid)
	{
		try
		{
			String sqlQuery = (catid == -1) ? "SELECT COUNT(*) AS total FROM articulos" : "SELECT COUNT(*) AS total FROM articulos WHERE catid = ?";
			
			PreparedStatement ps = conexion.prepareStatement(sqlQuery);
			
			if (catid != -1)
			{
				ps.setLong(1, catid);
			}
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
				return rs.getInt("total");
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MArticulos:actualizaArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-442);
		}
		
		return 0;
	}
}
