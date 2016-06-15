package modelos;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 
 * Clase contenedora del carrito de compra
 * 
 * @author jesus
 *
 */
public class Carro
{
	/**
	 * Mapa de artículos en el cual la clave es el id del artículo y el valor es el artículo en sí
	 */
	public HashMap<Long, Articulo> articulos = new HashMap<Long, Articulo>();
	
	/**
	 * Cuantía total de los productos en el carro
	 */
	public BigDecimal total = new BigDecimal(0);
}