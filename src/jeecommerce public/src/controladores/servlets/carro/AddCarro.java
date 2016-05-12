package controladores.servlets.carro;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import modelos.Articulo;
import modelos.Carro;
import modelos.MArticulos;
import modelos.SesionUsuario;

/**
 * Servlet implementation class AddCarro
 */
@WebServlet("/carro/add")
public class AddCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		if (sesion == null)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"La sesión no ha sido abierta");
			return;
		}
		
		Carro carro = sesion.carro;
		
		if (carro == null)
			carro = new Carro();
		
		JsonObject jsonArticulo = new JsonParser().parse(new JsonReader(request.getReader())).getAsJsonObject();
		
//		public long	artid,
//					catid;
//		
//		public String 	nombre,
//						descripcion,
//						imagen,
//						nombreCategoria;
//		
//		public int cantidad;
//		public BigDecimal precio;
		
		long artid = -1;
		int cantidad = -1;
		
		try
		{
			artid = jsonArticulo.get("artid").getAsLong();
			cantidad = jsonArticulo.get("cantidad").getAsInt();
			
		} catch (ClassCastException x) {
			
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los datos enviados son incorrectos");
			return;
		}
		
		if (artid == -1 || cantidad == -1 || cantidad == 0)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los datos enviados son incorrectos");
			return;
		}
		
		MArticulos mdlArticulos = new MArticulos(conexion);
		mdlArticulos.getArticuloByArtId(artid);
		
		if (!mdlArticulos.getProximoArticulo())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha encontrado un artículo con el ID: " + artid);
			return;
		}
		
		Articulo articulo;
		
		if (carro.articulos.containsKey(artid))
		{
			articulo = carro.articulos.get(artid);
			articulo.cantidad += cantidad;
			
		} else {
		
			articulo = new Articulo();
			
			articulo.artid = artid;
			articulo.cantidad = cantidad;
			articulo.catid = mdlArticulos.catid;
			articulo.nombre = mdlArticulos.nombre;
			articulo.descripcion = mdlArticulos.descripcion;
			articulo.precio = mdlArticulos.precio;
			
			carro.articulos.put(artid, articulo);
		}
		
		carro.total = carro.total.add(articulo.precio.multiply(BigDecimal.valueOf(cantidad)));
		
		int countArticulos = 0;
		
		for (Articulo _art : carro.articulos.values())
		{
			countArticulos += _art.cantidad;
		}
		
		JsonObject jsonRespuesta = new JsonObject();
		
		jsonRespuesta.addProperty("exito", true);
		jsonRespuesta.addProperty("total", carro.total.toString());
		jsonRespuesta.addProperty("countArticulos", countArticulos);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRespuesta.toString());
	}

}
