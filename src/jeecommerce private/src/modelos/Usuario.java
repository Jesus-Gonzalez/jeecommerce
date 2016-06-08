package modelos;

public class Usuario
{
	public long uid;
	
	public String	nombre,
					contrasena,
					correo;
	
	public boolean activado;
	
	public String ip;
	public long fechaConexion;
	
	public Usuario() { }
	
	public Usuario(MUsuarios mdlUsuarios)
	{
		uid = mdlUsuarios.uid;
		nombre = mdlUsuarios.nombre;
		contrasena = mdlUsuarios.contrasena;
		correo = mdlUsuarios.correo;
		activado = mdlUsuarios.activado;
		ip = mdlUsuarios.ip;
		fechaConexion = mdlUsuarios.fechaConexion;
	}
}
