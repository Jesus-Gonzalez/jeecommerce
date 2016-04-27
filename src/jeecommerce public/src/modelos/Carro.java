package modelos;

import java.math.BigDecimal;
import java.util.HashMap;

public class Carro
{
	public HashMap<Long, Articulo> articulos = new HashMap<Long, Articulo>();
	public BigDecimal total = new BigDecimal(0);
}