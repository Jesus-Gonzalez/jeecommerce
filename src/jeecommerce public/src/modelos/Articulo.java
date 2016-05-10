package modelos;

import java.math.BigDecimal;

public class Articulo
{
	public long	artid,
				catid;
	
	public String 	codArticulo,
					nombre,
					descripcion,
					imagen,
					nombreCategoria;
	
	public int cantidad;
	public BigDecimal precio;
}
