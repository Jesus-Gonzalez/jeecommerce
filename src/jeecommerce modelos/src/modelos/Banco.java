package modelos;

/**
 * 
 * Clase contenedora para un banco
 * 
 * @author jesus
 *
 */
public class Banco
{
	public long bid;
	
	public String	nombre,
					numero;
	
	public boolean activo;
	
	public Banco() {}
	
	public Banco(long bid, String nombre, String numero, boolean activo)
	{
		this.bid = bid;
		this.nombre = nombre;
		this.numero = numero;
		this.activo = activo;
	}
}
