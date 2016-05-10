package controladores.servlets.pedidos;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.MPedidos;
import modelos.SesionUsuario;

/**
 * Servlet implementation class FinPedido
 */
@WebServlet("/pedido/fin")
public class FinalizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO || sesion.carro == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso no autorizado");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		MPedidos mdlPedidos = new MPedidos(conexion);
		
		String strFormaPago = request.getParameter("formaPago");
		
		if (strFormaPago == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Forma de pago no establecida");
			return;
		}
		
		byte formaPago = -1;
		
		switch (strFormaPago)
		{
		
			case "contrarrembolso":
				formaPago = 0;
				break;
		
			case "transferencia":
				formaPago = 1;
				break;
			
			case "tarjeta":
				formaPago = 2;
				break;

			case "metalico":
				formaPago = 4;
				break;
		}
		
		request.setAttribute("autorizado", true);
		request.setAttribute("pid", mdlPedidos.crearPedido(sesion.usuario.uid, Calendar.getInstance().getTimeInMillis(), (byte)0, formaPago, sesion.carro.total, request.getParameter("observaciones")));
		
		request.getRequestDispatcher("/WEB-INF/pedido-fin.jsp").forward(request, response);
		
		return;
		
	}

}
