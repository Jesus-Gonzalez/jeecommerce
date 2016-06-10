package controladores.servlets.comentarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import modelos.MArticulos;
import modelos.MComentarios;
import modelos.SesionUsuario;

@WebServlet(asyncSupported = true, urlPatterns = { "/comentarios/crear/async" })
public class CreaComentarioAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		JsonObject jsonComentario = new JsonParser().parse(new JsonReader(request.getReader())).getAsJsonObject();
		
		if (jsonComentario == null ||
			!jsonComentario.has("artid") ||
			!jsonComentario.has("contenido") ||
			!jsonComentario.has("captchaResponse"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se han recibido datos");
			return;
		}

		// Empieza Comprobación Captcha
		
		// URL
		// https://www.google.com/recaptcha/api/siteverify
		// Secret
		// 6Le6PCITAAAAAEMfDZx9V3cs6dvRxsQ0PH0ObHeL
		
		// La comprobación del captcha se hará de forma asíncrona para evitar bloqueos
		final String captchaResponse = jsonComentario.get("captchaResponse").getAsString();
		final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		
		try
		{
           httpClient.start();
			
           final HttpPost recaptchaRequest = new HttpPost("https://www.google.com/recaptcha/api/siteverify");
           final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(2);
           
           postParameters.add(new BasicNameValuePair("secret", "6Le6PCITAAAAAEMfDZx9V3cs6dvRxsQ0PH0ObHeL"));
           postParameters.add(new BasicNameValuePair("response", captchaResponse));

           recaptchaRequest.setEntity(new UrlEncodedFormEntity(postParameters));
           
           final Future<HttpResponse> future = httpClient.execute(recaptchaRequest, null);
           final HttpResponse recaptchaResponse = future.get();
           
           final HttpEntity entity = recaptchaResponse.getEntity();
           
           final JsonObject jsonRecaptchaResponse = new JsonParser().parse( new BufferedReader(new InputStreamReader(entity.getContent())) ).getAsJsonObject();
           
           if (jsonRecaptchaResponse.has("success") && jsonRecaptchaResponse.get("success").getAsBoolean() == false)
           {
        	   response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error captcha");
        	   return;
           }
        }
		catch (ExecutionException | InterruptedException x)
		{
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Error en la solicitud a Recaptcha-Google");
			return;
		}
		finally
		{
			httpClient.close();
		}
		
		
		// Termina Comprobación Captcha
		
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		Connection conexion = (Connection) hs.getAttribute("conexion");
		
		long artid = -1;
		
		try
		{
			artid = jsonComentario.get("artid").getAsLong();
			
		} catch (NumberFormatException x) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se han recibido datos");
			return;
		}
		
		// Comprobar si el artículo existe, ya que sino, en caso contrario, tendríamos un fallo al añadir el comentario ya que "artid" no existiría
		MArticulos mdlArticulos = new MArticulos(conexion);
		mdlArticulos.getArticuloByArtId(artid);
		if (!mdlArticulos.getProximoArticulo())
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Campos obligatorios");
			return;
		}
		
		mdlArticulos = null;
		
		String contenido = jsonComentario.get("contenido").getAsString();
		
		MComentarios mdlComentarios = new MComentarios(conexion);
		
		
		// uid: -1 es comentario anónimo
		long uid = -1;
		
		if (sesion != null && sesion.usuario != null && sesion.estado == SesionUsuario.LOGUEADO)
		{
			uid = sesion.usuario.uid;
		}
		
		long cid = mdlComentarios.creaComentario(uid, artid, contenido, Calendar.getInstance().getTimeInMillis());
		
		JsonObject jsonRespuesta = new JsonObject();
		
		if (cid != -1)
		{
			jsonRespuesta.addProperty("exito", true);

		} else {
			
			jsonRespuesta.addProperty("error", true);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRespuesta.toString());
		return;
	}

}
