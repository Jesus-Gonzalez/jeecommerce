package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

public class MUsuarios
{
	public long uid;

	public
		String 	nombre,
				contrasena,
				correo,
				ip;
	public
		boolean activado;
	
	public long fechaRegistro,
				fechaConexion;
	
	public Integer[] datosCompras = new Integer[3]; // { jugadas, ganadas, perdidas, empatadas }
	
	private Connection conexion;
	private ResultSet rs;
	
	public MUsuarios(Connection conexion, Usuario usuario)
	{
		this(usuario);
		this.conexion = conexion;
	}
	
	public MUsuarios(Connection conexion)
	{
		this.conexion = conexion;
	}
	
	public MUsuarios(Usuario usuario)
	{
		uid = usuario.uid;
		nombre = usuario.nombre;
		contrasena = usuario.contrasena;
		correo = usuario.correo;
		activado = usuario.activado;
	}
	
	public boolean getProximoUsuario()
	{
		try
		{
			if (rs.next())
			{
				uid = rs.getLong("uid");
				nombre = rs.getString("nombre");
				contrasena = rs.getString("contrasena");
				correo = rs.getString("correo");
				activado = rs.getBoolean("activado");

				fechaRegistro = rs.getTimestamp("fecha_registro").getTime();
				fechaConexion = rs.getTimestamp("fecha_registro").getTime();
				
				ip = rs.getString("direccion_ip");

				datosCompras = rs.getArray("datos_compras") != null ? (Integer[]) rs.getArray("datos_compras").getArray() : null;
				
				
				return true;
				
			} else {
				
				return false;
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:getPrimerUsuario()");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-150);	
		}
		
		return false;
	}
	
	public void getUsuarioByUid(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE uid = ?");
			ps.setLong(1, uid);
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:getUsuarioByUid(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-151);	
		}
	}
	
	public boolean existeUsuario(String nombre)
	{
		getUsuarioByNombre(nombre);
		return getProximoUsuario();
	}
	
	public void getUsuarioByNombre(String nombre)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:getUsuarioByNombre(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-151);	
		}
	}
	
	public boolean existeCorreo(String correo)
	{
		getUsuarioByCorreo(correo);
		return getProximoUsuario();
	}
	
	public void getUsuarioByCorreo(String correo)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE correo = ?");
			ps.setString(1, correo);
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:getUsuarioByCorreo(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-152);	
		}
	}
	
	public long registrarUsuario(String nombre, String contrasena, String correo)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (nombre, contrasena, correo, activado, fecha_registro) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombre);
			ps.setString(2, contrasena);
			ps.setString(3, correo);
			ps.setBoolean(4, true);
			ps.setTimestamp(5, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
			{
				return rs.getLong(1);
				
			} else {
				
				return -1;
				
			}
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:registrarUsuario(String, String, String, String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-102);
			
		}
		
		return -1;
	}
	
	public void buscarUsuario(String busqueda)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre LIKE ? OR correo LIKE ?", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "%" + busqueda + "%");
			ps.setString(2, "%" + busqueda + "%");
			
			rs = ps.executeQuery();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:buscarUsuario(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-103);
			
		}
	}
	
	public boolean identificarUsuario(String nombre, String contrasena)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?");
			ps.setString(1, nombre);
			ps.setString(2, contrasena);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
			
		} catch (SQLException x) {
		
			System.err.println("Error SQL -> MUsuarios:buscarUsuario(String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-104);
			
		}
		
		return false;
	}
	
	public void actualizaUsuario()
	{
		actualizaUsuarioByUid(uid, nombre, contrasena, correo, activado);
	}
	
	public void actualizaUsuarioByUid(long uid, String nombre, String contrasena, String correo, boolean activado)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET nombre = ?, contrasena = ?, correo = ?, activado = ? WHERE uid = ?");
			ps.setString(1, nombre);
			ps.setString(2, contrasena);
			ps.setString(3, correo);
			ps.setBoolean(4, activado);
			ps.setLong(5, uid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:actualizaUsuarioByUid(long, String, String, String, String, boolean)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-105);			
		}
	}
	
	public void actualizarDatosCompras(long uid, Integer[] datosNuevos)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET datos_compras = ? WHERE uid = ?");
			
			// Crear el array e introducirlo en la consulta
			ps.setArray(1, conexion.createArrayOf("integer", datosNuevos));
			
			ps.setLong(2, uid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			x.printStackTrace();
			System.exit(-106);
		}
	}
	
	public void actualizarFechaConexion()
	{
		actualizarFechaConexion(uid, fechaConexion);
	}
	
	public void actualizarFechaConexion(long uid, long fechaConexion)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET fecha_conexion = ? WHERE uid = ?");
			
			ps.setTimestamp(1, new Timestamp(fechaConexion));
			ps.setLong(2, uid);
			ps.executeUpdate();
			
		} catch (SQLException x) {
			
			System.err.println("Error SQL -> MUsuarios:actualizarFechaConexion(long, long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-107);
		}
	}
	
	public void actualizarDireccionIp()
	{
		actualizarDireccionIp(uid, ip);
	}
	
	public void actualizarDireccionIp(long uid, String ip)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET direccion_ip = inet(?) WHERE uid = ?");
			
			ps.setString(1, ip);
			ps.setLong(2, uid);
			ps.executeUpdate();
			
		} catch (SQLException x) {

			System.err.println("Error SQL -> MUsuarios:actualizaDireccionIp(long, String)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-108);
		}
	}
	
	public void eliminarUsuario()
	{
		eliminarUsuario(uid);
	}
	
	public void eliminarUsuario(long uid)
	{
		try
		{
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM usuarios WHERE uid = ?");
			
			ps.setLong(1, uid);
			
			ps.executeUpdate();
			
		} catch (SQLException x) {
	
			System.err.println("Error SQL -> MUsuarios:eliminarUsuarioUsuario(long)");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
			System.exit(-109);
		}
	}
}
