package modelos;

public class SesionUsuario
{
	public static final byte
							CONECTADO = 0,
							LOGUEADO = 1;
	
	public byte estado;
	public Usuario usuario;
	public Carro carro;
	
	public boolean esAdministrador = false;
}
