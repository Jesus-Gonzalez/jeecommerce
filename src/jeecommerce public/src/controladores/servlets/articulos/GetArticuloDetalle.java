package controladores.servlets.articulos;

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

import modelos.Comentario;
import modelos.MArticuloDetalle;

/**
 * Servlet implementation class GetArticuloDetalle
 */
@WebServlet("/articulos/get/detalle")
public class GetArticuloDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		Long artid = -1L;

		try
		{
			artid = Long.parseLong(request.getParameter("artid"));
		} catch (NumberFormatException x) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado un ID de artículo");
			return;
		}
		
		HttpSession session = request.getSession();
		
		if (session == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Sesión no inicializada");
			return;
		}
		
		Connection conexion = (Connection) session.getAttribute("conexion");
		
		if (conexion == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Conexión no inicializada");
			return;
		}
		
		MArticuloDetalle mdlArticuloDetalle = new MArticuloDetalle(conexion);
		
		if (!mdlArticuloDetalle.getArticuloDetalleByArtId(artid))
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Artículo con ID: " + artid + " no encontrado");
			return;
		}
		
		request.setAttribute("articulo.artid", mdlArticuloDetalle.articulo.artid);
		request.setAttribute("articulo.catid", mdlArticuloDetalle.articulo.catid);
		request.setAttribute("articulo.nombre", mdlArticuloDetalle.articulo.nombre);
		request.setAttribute("articulo.descripcion", mdlArticuloDetalle.articulo.descripcion);
		request.setAttribute("articulo.imagen", mdlArticuloDetalle.articulo.imagen);
		request.setAttribute("articulo.precio", mdlArticuloDetalle.articulo.precio);
		request.setAttribute("categoria.nombre", mdlArticuloDetalle.articulo.nombreCategoria);
		
		List<Comentario> lstComentarios = new LinkedList<Comentario>();		
		while (mdlArticuloDetalle.getProximoComentario())
		{
			lstComentarios.add(mdlArticuloDetalle.comentario);
		}
		
		request.setAttribute("comentarios.lista", lstComentarios);
		
		request.getRequestDispatcher("/WEB-INF/producto.jsp").forward(request, response);
		return;
	}

}
