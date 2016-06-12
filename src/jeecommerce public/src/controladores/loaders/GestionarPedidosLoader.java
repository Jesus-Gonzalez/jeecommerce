package controladores.loaders;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.MConfiguracion;
import modelos.MPedidos;
import modelos.Pedido;
import modelos.SesionUsuario;

@WebServlet("/gestionar-pedidos.html")
public class GestionarPedidosLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || sesion.usuario == null || sesion.estado == SesionUsuario.CONECTADO)
		{
			response.sendRedirect(request.getContextPath() + "/catalogo.html");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		MConfiguracion mdlConfiguracion = new MConfiguracion(conexion);
		mdlConfiguracion.getParametro("NumItemsGestionPedidos");
		mdlConfiguracion.getProximoParametro();
		
		int numItems = -1;
		int offset = 0;
		
		try
		{
			numItems = 20;
			
			String pagina = request.getParameter("pagina");
			if (pagina == null)
				pagina = "1";
			
			offset = Integer.parseInt(pagina) - 1;
			offset *= numItems;
			
		} catch (ClassCastException | NumberFormatException x) {
			
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor. Probablemente parámetro página mal establecido o no se ha leído valor de base de datos.");
		}
		
		
		MPedidos mdlPedidos = new MPedidos(conexion);		
		mdlPedidos.getPedidosByUid(sesion.usuario.uid, offset, numItems);
		List<Pedido> lstPedidos = new LinkedList<Pedido>();
		Pedido pedido;
		
		while (mdlPedidos.getProximoPedido())
		{
			pedido = new Pedido();
			pedido.pid = mdlPedidos.pid;
			pedido.uid = mdlPedidos.uid;
			pedido.did = mdlPedidos.did;
			pedido.estado = mdlPedidos.estado;
			pedido.formaPago = mdlPedidos.formaPago;
			pedido.importe = mdlPedidos.importe;
			pedido.fecha = mdlPedidos.fecha;
			pedido.observaciones = mdlPedidos.observaciones;
			
			lstPedidos.add(pedido);
		}
		
		request.setAttribute("lista.pedidos", lstPedidos);
		
		request.getRequestDispatcher("/WEB-INF/gestionar-pedidos.jsp").forward(request, response);
	}

}
