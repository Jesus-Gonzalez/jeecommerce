package helpers;

public class PedidosHelper
{
	public static String getStrFormaPago(byte formaPago)
	{
		switch (formaPago)
		{
			case 0:
				return "Contrarrembolso";
			
			case 1:
				return "Transferencia";
			
			case 2:
				return "Tarjeta de Crédito";
				
			case 4:
				return "Metálico";
				
			default:
				return null;
		}
	}
	
	public static String getStrEstado(byte estado)
	{
		switch (estado)
		{
			case 0:
				return "Creado";
			
			case 1:
				return "Procesado";
				
			case 2:
				return "Enviado";
				
			case 3:
				return "Completado";
				
			case 4:
				return "Error";
			
			case 5:
				return "Devuelto";
				
			default:
				return null;
		}
	}
}
