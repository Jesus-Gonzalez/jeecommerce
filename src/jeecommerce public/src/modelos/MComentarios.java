package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MComentarios
{

	public long cid,
				uid,
				artid;
	
	public String contenido;
	
	public long fecha;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MComentarios(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoComentario()
	{
		try
		{
			if (rs.next())
			{
				cid = rs.getLong("cid");
				uid = rs.getLong("uid");
				artid = rs.getLong("artid");
				contenido = rs.getString("contenido");
				fecha = rs.getTimestamp("fecha").getTime();
				
				return true;
			}

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-640);
		}
		
		return false;
	}
	
	public void getComentariosByArtId(long artid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM comentarios WHERE artid = ?");
			ps.setLong(1, artid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-641);	
		}
	}
	
	public void getComentariosByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM comentarios WHERE uid = ?");
			ps.setLong(1, uid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-642);
		}
	}
	
	public void getComentariosByCid(long cid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM comentarios WHERE cid = ?");
			ps.setLong(1, cid);
				
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-643);
		}
	}
	
	public boolean actualizaComentario(long cid, long uid, long artid, String contenido, long fecha)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE comentarios SET uid = ?, artid = ?, contenido = ?, fecha = ? WHERE cid = ?");
			ps.setLong(1, uid);
			ps.setLong(2, artid);
			ps.setString(3, contenido);
			ps.setTimestamp(4, new Timestamp(fecha));
			ps.setLong(5, cid);

			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-644);
		}
		
		return false;
	}
	
	public boolean actualizaComentario()
	{
		return actualizaComentario(cid, uid, artid, contenido, fecha);
	}
	
	public long creaComentario(long uid, long artid, String contenido, long fecha)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO comentarios (uid, artid, contenido, fecha) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, uid);
			ps.setLong(2, artid);
			ps.setString(3, contenido);
			ps.setTimestamp(4, new Timestamp(fecha));

			if (ps.executeUpdate() > 0)
			{
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getLong(1);
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-645);
		}

		return -1;
	}
	
	public boolean eliminarComentarioByCid(long cid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM comentarios WHERE cid = ?");
			ps.setLong(1, cid);

			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-646);
		}

		return false;
	}
	
	public boolean eliminarComentario()
	{
		return eliminarComentarioByCid(cid);
	}
	
	public boolean eliminarComentarioByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM comentarios WHERE uid = ?");
			ps.setLong(1, uid);

			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-647);
		}

		return false;
	}
}
