package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MPedidosDetalle
{
	public long pid,
				aid;
	
	public int cantidad;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MPedidosDetalle(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoDetalle()
	{
		try
		{
			if (rs.next())
			{
				pid = rs.getLong("pid");
				aid = rs.getLong("aid");
				cantidad = rs.getInt("cantidad");
				
				return true;
			}

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidosDetalle:getProximoDetalle()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-840);
		}
		
		return false;
	}
	
	public void getDetallesByPid(long pid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM pedidos_detalles WHERE pid = ?");
			ps.setLong(1, pid);
			
			rs = ps.executeQuery();

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidosDetalle:getDetallesByPid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-841);	
		}
	}
	
	public boolean creaDetallePedido(long pid, long aid, int cantidad)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO pedidos_detalles (pid, artid, cantidad) VALUES (?, ?, ?)");
			ps.setLong(1, pid);
			ps.setLong(2, aid);
			ps.setInt(3, cantidad);
			
			return ps.executeUpdate() > 0;

		} catch (SQLException x) {

			System.err.println("Error SQL -> MPedidosDetalle:creaDetallePedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-842);
		}
		
		return false;
	}
	
	public boolean actualizaDetallePedido(long pid, long aid, int cantidad)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE pedidos_detalles SET pid = ?, artid = ?, cantidad = ? WHERE pid = ? AND artid = ?");
			ps.setLong(1, pid);
			ps.setLong(2, aid);
			ps.setInt(3, cantidad);
			ps.setLong(4, pid);
			ps.setLong(5, aid);
			
			return ps.executeUpdate() > 0;

		} catch (SQLException x) {

			System.err.println("Error SQL -> MPedidosDetalle:actualizaDetallePedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-843);
		}
		
		return false;		
	}
	
	public boolean actualizaDetallePedido()
	{
		return actualizaDetallePedido(pid, aid, cantidad);
	}
	
	public boolean eliminaDetallePedido(long pid, long aid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM pedidos_detalles WHERE pid = ? AND artid = ?");
			ps.setLong(1, pid);
			ps.setLong(2, aid);
			
			return ps.executeUpdate() > 0;

		} catch (SQLException x) {

			System.err.println("Error SQL -> MPedidosDetalle:eliminaDetallePedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-844);
		}
		
		return false;		
	}
	
	public boolean eliminaDetallePedido()
	{
		return eliminaDetallePedido(pid, aid);
	}
}
