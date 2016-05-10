package controladores.servlets.comentarios;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/comentarios/crear")
public class CreaComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	// TODO: Completar servlet de creación de comentarios
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException
//	{
//		HttpSession hs = request.getSession();
//		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("sesion");
//		Connection conexion = (Connection) hs.getAttribute("conexion");
//		
//		JsonObject jsonComentario = new JsonParser().parse(new JsonReader(request.getReader())).getAsJsonObject();
//		
//		if (jsonComentario == null)
//		{
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se han recibido datos");
//			return;
//		}
//		
//		long artid = -1;
//		
//		try
//		{
//			artid = jsonComentario.get("artid").getAsLong();
//		}
//		
//		String	nombre = jsonComentario.get("contenido").getAsString(),
//				direccion = jsonComentario.get("direccion").getAsString(),
//				localidad = jsonComentario.get("localidad").getAsString(),
//				provincia = jsonComentario.get("provincia").getAsString(),
//				codigoPostal = jsonComentario.get("codpostal").getAsString(),
//				telefono = jsonComentario.get("telefono").getAsString();
//		
//		if (artid == -1 || nombre == null || direccion == null || localidad == null)
//		{
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Campos obligatorios");
//			return;
//		}
//		
//		MComentarios mdlComentarios = new MComentarios(conexion);
//		
//		long cid = mdlComentarios.creaComentario(-1, artid, contenido, fecha);
//		
//		JsonObject jsonRespuesta = new JsonObject();
//		
//		if (did != -1)
//		{
//			jsonRespuesta.addProperty("did", did);
//
//		} else {
//			
//			jsonRespuesta.addProperty("error", true);
//		}
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().write(jsonRespuesta.toString());
//		return;
//	}

}
