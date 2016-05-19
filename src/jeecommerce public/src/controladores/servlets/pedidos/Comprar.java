package controladores.servlets.pedidos;

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

import modelos.Carro;
import modelos.Direccion;
import modelos.MDirecciones;
import modelos.SesionUsuario;

/**
 * Servlet implementation class RealizarCompra
 */
@WebServlet("/comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		String cancelar = request.getParameter("cancelar");
		if (cancelar != null && cancelar.equals("true"))
		{
			sesion.carro = new Carro();
			response.sendRedirect("/catalogo.html");
		}
		
		if (sesion.carro == null || sesion.carro.articulos == null || sesion.carro.articulos.size() == 0)
		{
			request.getRequestDispatcher("/catalogo.html").forward(request, response);
			return;
		}
		
		if (sesion == null || sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO)
		{
			request.setAttribute("referrer", "/comprar");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		List<Direccion> lstDirecciones = new LinkedList<Direccion>();
		Direccion direccion;
		MDirecciones mdlDirecciones = new MDirecciones(conexion);
		mdlDirecciones.getDireccionesByUid(sesion.usuario.uid);
		
		while (mdlDirecciones.getProximaDireccion())
		{
			direccion = new Direccion();
			direccion.did = mdlDirecciones.did;
			direccion.uid = mdlDirecciones.uid;
			direccion.nombre = mdlDirecciones.nombre;
			direccion.direccion = mdlDirecciones.direccion;
			direccion.localidad = mdlDirecciones.localidad;
			direccion.codigoPostal = mdlDirecciones.codigoPostal;
			direccion.telefono = mdlDirecciones.telefono;
			
			lstDirecciones.add(direccion);
		}
		
		request.setAttribute("lista.direcciones", lstDirecciones);
		
		request.getRequestDispatcher("/WEB-INF/comprar.jsp").forward(request, response);
		return;
	}
}
