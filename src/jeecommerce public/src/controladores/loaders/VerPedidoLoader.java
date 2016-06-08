package controladores.loaders;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Articulo;
import modelos.Direccion;
import modelos.MArticulos;
import modelos.MDirecciones;
import modelos.MPedidos;
import modelos.MPedidosDetalle;
import modelos.Pedido;
import modelos.SesionUsuario;

@WebServlet("/ver-pedido.html")
public class VerPedidoLoader extends HttpServlet {
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
		
		long pid = -1;
		
		try
		{
			pid = Long.parseLong(request.getParameter("pid"));
			
		} catch (NumberFormatException x) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "PID No válido");
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		MPedidos mdlPedidos = new MPedidos(conexion);
		MPedidosDetalle mdlPedidoDetalle = new MPedidosDetalle(conexion);
		MArticulos mdlArticulos = new MArticulos(conexion);
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		
		Pedido pedido = new Pedido();
		Articulo articulo;
		
		mdlPedidos.getPedidoByPid(pid);
		if (!mdlPedidos.getProximoPedido())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha encontrado el pedidoc con PID: " + pid);
			return;
			
		} else if (mdlPedidos.uid != sesion.usuario.uid) {
			
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "No tiene permisos para ver los pedidos de otros usuarios");
			return;
		}
		
		pedido = new Pedido();
		pedido.pid = mdlPedidos.pid;
		pedido.uid = mdlPedidos.uid;
		pedido.did = mdlPedidos.did;
		pedido.estado = mdlPedidos.estado;
		pedido.formaPago = mdlPedidos.formaPago;
		pedido.importe = mdlPedidos.importe;
		pedido.fecha = mdlPedidos.fecha;
		pedido.observaciones = mdlPedidos.observaciones;
		pedido.importe = mdlPedidos.importe;
		pedido.articulos = new LinkedList<Articulo>();
		
		mdlDirecciones.getDireccionByDid(mdlPedidos.did);
		if (!mdlDirecciones.getProximaDireccion())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha encontrado la dirección: " + mdlPedidos.did);
			return;
		}
		
		Direccion direccion = new Direccion();
		direccion.nombre = mdlDirecciones.nombre;
		direccion.direccion = mdlDirecciones.direccion;
		direccion.localidad = mdlDirecciones.localidad;
		direccion.codigoPostal = mdlDirecciones.codigoPostal;
		direccion.telefono = mdlDirecciones.telefono;
		
		mdlPedidoDetalle.getDetallesByPid(pid);
		
		while (mdlPedidoDetalle.getProximoDetalle())
		{
			mdlArticulos.getArticuloByArtId(mdlPedidoDetalle.aid);
			
			if (!mdlArticulos.getProximoArticulo())
			{
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encuentra Artículo con ID " + mdlPedidoDetalle.aid);
				return;
			}
			
			articulo = new Articulo();
			articulo.cantidad = mdlPedidoDetalle.cantidad;
			articulo.nombre = mdlArticulos.nombre;
			articulo.precio = mdlArticulos.precio;
			
			pedido.articulos.add(articulo);
		}
		
		request.setAttribute("direccion", direccion);
		request.setAttribute("pedido", pedido);
		
		request.getRequestDispatcher("/WEB-INF/ver-pedido.jsp").forward(request, response);
		return;
	}

}
