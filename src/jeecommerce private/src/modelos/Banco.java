package modelos;

public class Banco
{
	public long bid;
	
	public String	nombre,
					numero;
	
	public boolean activo;
	
	public Banco(long bid, String nombre, String numero, boolean activo)
	{
		this.bid = bid;
		this.nombre = nombre;
		this.numero = numero;
		this.activo = activo;
	}
}
