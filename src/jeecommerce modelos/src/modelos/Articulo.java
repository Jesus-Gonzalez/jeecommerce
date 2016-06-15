package modelos;

import java.math.BigDecimal;

/**
 * 
 * Clase contenedora para un artículo
 * 
 * @author jesus
 *
 */
public class Articulo
{
	public long	artid,
				catid;
	
	public String 	nombre,
					descripcion,
					imagen,
					nombreCategoria;
	
	public int cantidad;
	public BigDecimal precio;
	
	public boolean destacado;
}
