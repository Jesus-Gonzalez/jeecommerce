package controladores.servlets.pedidos;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import modelos.Articulo;
import modelos.Carro;
import modelos.MPedidos;
import modelos.MPedidosDetalle;
import modelos.SesionUsuario;
import utils.Correo;

@WebServlet("/pagar")
public class Pagar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession hs = request.getSession();
		SesionUsuario sesion = (SesionUsuario) hs.getAttribute("usuario");
		
		if (sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO || sesion.carro.articulos.size() == 0)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
		
		JsonObject jsonDatos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		String formaPago = jsonDatos.get("formaPago").getAsString(),
				strDid = jsonDatos.get("did").getAsString();
		
		if (formaPago == null || strDid == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Forma de pago no establecida.");
			return;
		}
		
		long did = -1;
		
		try
		{
			did = Long.parseLong(strDid);

		} catch (NumberFormatException x) {
			
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID Dirección no es un número");
			return;
			
		}
		
		
		// Las demás formas de pago: transferencia, metálico y contrarrembolso, no se procesan
		
		JsonObject jsonRespuesta = new JsonObject();
		byte fldFormaPago = -1;
		
		
		switch (formaPago)
		{
			case "tarjeta":
				
				Stripe.apiKey = "sk_test_6YxNZaAlxoCr9J8oK6PY0J6U";
				
				if (!jsonDatos.has("token"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha establecido token");
					return;
				}
				
				String tkn = jsonDatos.get("token").getAsString();
				if (tkn == null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token de tarjeta no establecido");
					return;
				}
		
				try {
					
					Map<String, Object> chargeParams = new HashMap<String, Object>();
					chargeParams.put("amount", sesion.carro.total.multiply(BigDecimal.valueOf(100)).intValue());
					chargeParams.put("currency", "eur");
					chargeParams.put("source", tkn);
					chargeParams.put("description", "Email: " + sesion.usuario.correo + " - UID: " + sesion.usuario.uid + " - Fecha: " + Calendar.getInstance().getTime() + " - Cantidad: " + sesion.carro.total);

					Charge cargo = Charge.create(chargeParams);
					
					jsonRespuesta.addProperty("pagado", cargo.getPaid());
					
				} catch (CardException x) {
				
					jsonRespuesta.addProperty("error", "card.exception");
					jsonRespuesta.addProperty("errorCode", x.getCode());
					jsonRespuesta.addProperty("errorMessage", x.getMessage());

				} catch (RateLimitException x) {
				
					jsonRespuesta.addProperty("error", "rate.limit");

				} catch (InvalidRequestException x) {
				
					jsonRespuesta.addProperty("error", "bad.params");

				} catch (AuthenticationException x) {
				
					jsonRespuesta.addProperty("error", "stripe.auth.error");
				
				} catch (APIConnectionException x) {
					
					jsonRespuesta.addProperty("error", "comm.error");
				
				} catch (StripeException x) {
				
					jsonRespuesta.addProperty("error", "generic.error");
					jsonRespuesta.addProperty("errorMessage", x.getMessage());
				}				
			
				fldFormaPago = 2;
			
				break;
				
			case "metalico":
				
				fldFormaPago = 4;
				
				break;
				
			case "contrarrembolso":
				
				fldFormaPago = 0;
				
				break;
				
			case "transferencia":
				
				fldFormaPago = 1;
				
				break;
				
			default:

				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nada que hacer");
				return;
		}

		Connection conexion = (Connection) hs.getAttribute("conexion");
		MPedidos mdlPedidos = new MPedidos(conexion);
		// TODO: Insertar observaciones
		
		long pid = mdlPedidos.crearPedido(sesion.usuario.uid, did, Calendar.getInstance().getTimeInMillis(), (byte)1, fldFormaPago, sesion.carro.total, "");
		jsonRespuesta.addProperty("pid", pid);
		
		// Crea detalle de pedido
		MPedidosDetalle mdlPedidosDetalle = new MPedidosDetalle(conexion);
		for (Articulo articulo : sesion.carro.articulos.values())
			mdlPedidosDetalle.creaDetallePedido(pid, articulo.artid, articulo.cantidad);
		
		// Reinicializar carro
		sesion.carro = new Carro();
		
		// Enviar correo asíncronamente indicando que ha realizado un pedido
		System.out.println("Creating thread");
		final String fnlCorreo = sesion.usuario.correo;
		final long fnlPid = pid;
		new Thread()
		{
			@Override
			public void run() {
				System.out.println("Running thread");
				Correo utlCorreo = new Correo(fnlCorreo);
				utlCorreo.enviarPedidoRealizado(fnlPid);
				System.out.println("Thread run");
			}
		}.start();
		System.out.println("Thread created");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRespuesta.toString());
		return;
	}

}
