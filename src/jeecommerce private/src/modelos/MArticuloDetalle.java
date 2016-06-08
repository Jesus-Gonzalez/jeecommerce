package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MArticuloDetalle
{
	public Articulo articulo;
	public Object[] categoria;
	public Comentario comentario;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MArticuloDetalle(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoComentario()
	{
		try
		{
			if (rs.next())
			{
				comentario = new Comentario();
				
				comentario.cid = rs.getLong("cid");
				comentario.uid = rs.getLong("uid");
				comentario.contenido = rs.getString("contenido");
				comentario.fecha = rs.getTimestamp("fecha").getTime();
				
				return true;
			}

		} catch (SQLException x) {
			System.err.println("Error SQL -> MArticulos:getProximoArticulo()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-44044);
		}
		
		return false;
	}
	
	public boolean getArticuloDetalleByArtId(long artid)
	{
		try
		{
			conexion.setAutoCommit(false);
			
			// Procedure call.
			PreparedStatement ps = conexion.prepareStatement("SELECT get_articulo_detalle(?)");
			ps.setLong(1, artid);
			rs = ps.executeQuery();
			
			if (rs.next())
			{
				ResultSet rsArticulos = (ResultSet) rs.getObject(1);
				
				if (rsArticulos.next())
				{
					articulo = new Articulo();
					
					articulo.artid = artid;
					articulo.catid = rsArticulos.getLong("catid");
					articulo.nombre = rsArticulos.getString("nombre");
					articulo.descripcion = rsArticulos.getString("descripcion");
					articulo.precio = rsArticulos.getBigDecimal("precio");
					articulo.imagen = rsArticulos.getString("imagen");
					
					if (rs.next())
					{
						ResultSet rsCategoria = (ResultSet) rs.getObject(1);
						
						if (rsCategoria.next())
						{
							categoria = new Object[2];
							
							articulo.nombreCategoria = rsCategoria.getString("nombre");
							
							if (rs.next())
							{
								rs = (ResultSet) rs.getObject(1); 
							}
						}
					}
					
					return true;
				}
			}
			
		} catch (SQLException x) {

			System.err.println("Error SQL -> MArticuloDetalle:getArticuloDetalleByArtId()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-44045);
			
		} finally {
			try
			{
				
				conexion.setAutoCommit(true);
				
			} catch (SQLException x) {
				
				System.err.println("Error SQL -> MArticuloDetalle:getArticuloDetalleByArtId()@AutoCommit(true)");
				System.err.println("Mensaje de error -> " + x.getMessage());
				
				x.printStackTrace();
			
				System.exit(-44057);
						
			}
		}
		
		return false;
	}
}
