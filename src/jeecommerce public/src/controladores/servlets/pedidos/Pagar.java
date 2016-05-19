package controladores.servlets.pedidos;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import modelos.SesionUsuario;

@WebServlet("/pagar")
public class Pagar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SesionUsuario sesion = (SesionUsuario) request.getSession().getAttribute("usuario");
		
		if (sesion.usuario == null || sesion.estado != SesionUsuario.LOGUEADO || sesion.carro.articulos.size() == 0)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
		
		JsonObject jsonDatos = new JsonParser().parse(request.getReader()).getAsJsonObject();
		
		String formaPago = jsonDatos.get("formaPago").getAsString();
		
		if (formaPago == null)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Forma de pago no establecida.");
			return;
		}
		
		// Las demás formas de pago: transferencia, metálico y contrarrembolso, no se procesan
		
		switch (formaPago)
		{
			case "tarjeta":
				
				Stripe.apiKey = "sk_test_6YxNZaAlxoCr9J8oK6PY0J6U";
				
				String tkn = jsonDatos.get("token").getAsString();
				if (tkn == null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token de tarjeta no establecido");
					return;
				}
		
				JsonObject jsonRespuesta = new JsonObject();
				
				try {
					
					Map<String, Object> chargeParams = new HashMap<String, Object>();
					chargeParams.put("amount", sesion.carro.total.intValue() * 100);
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
				
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(jsonRespuesta.toString());
				return;
		}
		
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nada que hacer");
		return;
	}

}
