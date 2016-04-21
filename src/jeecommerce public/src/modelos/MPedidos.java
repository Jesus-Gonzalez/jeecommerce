package modelos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MPedidos
{

	public long pid,
				uid;
	
	public byte estado,
				formaPago;
	
	public BigDecimal importe;
	
	
	public long fecha;
	
	public String observaciones;
	
	private Connection conexion;
	private ResultSet rs;
	
	public MPedidos(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public boolean getProximoPedido()
	{
		try
		{
			if (rs.next())
			{
				pid = rs.getLong("pid");
				uid = rs.getLong("uid");
				estado = rs.getByte("estado");
				formaPago = rs.getByte("forma_pago");
				fecha = rs.getLong("fecha");
				importe = rs.getBigDecimal("importe");
				observaciones = rs.getString("observaciones");
				
				return true;
			}

		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MPedidos:getProximoPedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-740);
		}
		
		return false;
	}
	
	public void getPedidoByPid(long pid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM pedidos WHERE pid = ?");
			ps.setLong(1, pid);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getProximoComentario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-741);	
		}
	}
	
	public void getPedidosByUid(long uid, int offset, int numItems)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM pedidos WHERE uid = ? LIMIT ? OFFSET ?");
			ps.setLong(1, uid);
			ps.setInt(2, numItems);
			ps.setInt(3, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getPedidosByUid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-742);
		}
	}
	
	public void getPedidosByEstado(byte estado, int offset, int numItems)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM pedidos WHERE estado = ? LIMIT ? OFFSET ?");
			ps.setByte(1, estado);
			ps.setInt(2, numItems);
			ps.setInt(3, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getPedidosByEstado()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-743);
		}
	}
	
	public void getPedidosByFormaPago(byte formaPago, int offset, int numItems)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM pedidos WHERE forma_pago = ? LIMIT ? OFFSET ?");
			ps.setByte(1, formaPago);
			ps.setInt(2, numItems);
			ps.setInt(3, offset);
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:getPedidosByFormaPago()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-744);
		}
	}
	
	public long crearPedido(long uid, long fecha, byte estado, byte formaPago, BigDecimal importe, String observaciones)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO pedidos (uid, fecha, estado, forma_pago, importe, observaciones) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, uid);
			ps.setTimestamp(2, new Timestamp(fecha));
			ps.setByte(3, estado);
			ps.setByte(4, formaPago);
			ps.setBigDecimal(5, importe);
			ps.setString(6, observaciones);
			
			if ( ps.executeUpdate() > 0 )
			{
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getLong(1);
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:crearPedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-746);
		}
		
		return -1;
	}
	
	public boolean actualizaPedido(long pid, long uid, long fecha, byte estado, byte formaPago, BigDecimal importe, String observaciones)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE pedidos SET uid = ?, fecha = ?, estado = ?, forma_pago = ?, importe = ?, observaciones = ? WHERE pid = ?");
			ps.setLong(1, uid);
			ps.setTimestamp(2, new Timestamp(fecha));
			ps.setByte(3, estado);
			ps.setByte(4, formaPago);
			ps.setBigDecimal(5, importe);
			ps.setString(6, observaciones);
			ps.setLong(7, pid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:actualizaPedido()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-747);
		}
		
		return false;
	}
	
	public boolean actualizaPedido()
	{
		return actualizaPedido(pid, uid, fecha, estado, formaPago, importe, observaciones);
	}

	public boolean eliminaPedidoByPid(long pid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM pedidos WHERE pid = ?");
			ps.setLong(1, pid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:eliminaPedidoByPid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-747);
		}
		
		return false;		
	}
	
	public boolean eliminaPedidoByPid()
	{
		return eliminaPedidoByPid(pid);
	}
	

	public boolean eliminaPedidosByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM pedidos WHERE uid = ?");
			ps.setLong(1, uid);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:eliminaPedidoByUid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-748);
		}
		
		return false;		
	}
	
	public boolean eliminaPedidoByUid()
	{
		return eliminaPedidosByUid(uid);
	}
	
	
	public boolean eliminaPedidosByEstado(byte estado)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM pedidos WHERE estado = ?");
			ps.setByte(1, estado);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MComentarios:eliminaPedidoByUid()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-749);
		}
		
		return false;		
	}
}
