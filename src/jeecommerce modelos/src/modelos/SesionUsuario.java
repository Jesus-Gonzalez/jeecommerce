package modelos;


/**
 * 
 * Clase contenedora de la sesión iniciada por el usuario 
 * 
 * @author jesus
 *
 */
public class SesionUsuario
{
	/**
	 * Constantes que indican el estado de la sesión
	 */
	public static final byte
							CONECTADO = 0,
							LOGUEADO = 1;
	
	/**
	 * Estado de la sesión
	 */
	public byte estado;
	/**
	 * Objeto Usuario con los datos referentes al usuario
	 */
	public Usuario usuario;
	/**
	 * Objeto carro con los artículos del usuario
	 */
	public Carro carro;
	
	/**
	 * Indica si el usuario es administrador o no del sitio
	 */
	public boolean esAdministrador = false;
}
