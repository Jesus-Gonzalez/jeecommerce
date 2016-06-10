package controladores.servlets;

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

import helpers.CategoriasHelper;
import modelos.Articulo;
import modelos.MArticulos;
import modelos.MCategorias;
import modelos.SesionUsuario;

@WebServlet("/productos")
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion == null || !sesion.esAdministrador)
		{
			response.sendRedirect("login");
			return;
		}
		
		String criterio = request.getParameter("criterio");
		String categoria = request.getParameter("categoria");
		String strPagina = request.getParameter("pagina");
		int pagina = 0;
		int offset = 0;
		int count = 0;
		
		if (strPagina != null)
		{
			try
			{	
				pagina = Integer.parseInt(strPagina);
				offset = 20 * (pagina - 1);
			} catch (NumberFormatException x) {
				
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Página no es un número de 64 bits / 8 bytes");
				return;
			}	
		}
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MArticulos mdlArticulos = new MArticulos(conexion);
		
		List<Articulo> lstArticulos = new LinkedList<Articulo>();
		Articulo articulo; 
		
		if (criterio != null)
		{
			count = mdlArticulos.countProductosByCriterio(criterio);
			
			mdlArticulos.buscarArticulos(criterio, 20, offset);

		} else if (categoria != null) {
			
			long catid = -1;
			
			try
			{
				catid = Long.parseLong(categoria);
				
			} catch (NumberFormatException x) {
				
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoría no es un número de 8 bytes / 64 bits");
				return;
				
			}
			
			count = mdlArticulos.countProductosByCatId(catid);
			
			mdlArticulos.getArticulosByCatId(catid, 20, offset);
			
		} else {
			
			count = mdlArticulos.countLatestProductos();
			
			mdlArticulos.getLatestArticulos(20, offset);
		}
		
		while (mdlArticulos.getProximoArticulo())
		{
			articulo = new Articulo();
			articulo.artid = mdlArticulos.artid;
			articulo.catid = mdlArticulos.catid;
			articulo.nombre = mdlArticulos.nombre;
			articulo.precio = mdlArticulos.precio;
			
			lstArticulos.add(articulo);
		}
		
		request.setAttribute("seccion", "dashboard");
		
		request.setAttribute("lista.articulos", lstArticulos);
		request.setAttribute("pagina", pagina);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("/WEB-INF/productos.jsp").forward(request, response);
		
		return;
	}
}

