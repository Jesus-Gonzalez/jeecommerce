package modelos;

import java.math.BigDecimal;
import java.util.List;

public class Pedido
{
	public long pid,
				did,
				uid;
	
	public byte estado;
	
	public byte formaPago;
	
	public long fecha;
	
	public BigDecimal importe;
	
	public String observaciones;
	
	public List<Articulo> articulos;
}
