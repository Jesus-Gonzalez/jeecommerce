package helpers;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.MRecordarme;
import modelos.MUsuarios;
import modelos.SesionUsuario;
import modelos.Usuario;
import utils.Cifrado;
import utils.CookiesUtils;
import utils.HashMapCookie;

public class RecordarmeHelper
{
	public Cookie crearCookiePersistente(String key, String value)
	{
		Cookie c = new Cookie(key, value);
		c.setMaxAge(31536000);
		c.setPath("/");
		
		return c;
	}
	
	public boolean comprobarCookieRecordarme(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		// Suponemos que el usuario no está identificado
		boolean identificado = false;
		
		SesionUsuario sesion = (SesionUsuario) session.getAttribute("sesion");

		// Existe el objeto 'sesion' en la sesión actual?
		if (sesion == null)
		{
			sesion = new SesionUsuario();
			sesion.estado = SesionUsuario.CONECTADO;
			session.setAttribute("sesion", sesion);
		}
		
		// Está el usuario logueado?
		if (sesion.estado != SesionUsuario.LOGUEADO)
		{
			HashMapCookie<String, String> mapaCookies = new CookiesUtils().createCookieMapFromArray("jeecommerce.data", request.getCookies());
			
			// Existe la cookie 'jeecommerce.data'
			if (!mapaCookies.isEmpty())
			{
				String 	recordar = mapaCookies.get("recordar"),
						identificador = mapaCookies.get("muid"),
						token = mapaCookies.get("token");

				// Existe las (sub)cookies del sistema que recuerda sesiones?
				// Están todos los atributos definidos y no están vacíos?
				if (recordar != null && recordar.equals("true") &&
					identificador != null && !identificador.isEmpty() &&
					token != null && !token.isEmpty())
				{
					Connection conexion = (Connection) session.getAttribute("conexion");
					
					MRecordarme mdlRecordarme = new MRecordarme(conexion);
					
					// Encuentra el identificador de recordarme (MUID)
					if (mdlRecordarme.buscaRecordarmeById(identificador))
					{
						Cifrado cifrado = new Cifrado();
						
						// Concuerda el token de la base de datos con el hash generado?
						if (cifrado.hash("SHA-256", mdlRecordarme.salt + token).equals(mdlRecordarme.token))
						{
							// Almacenar en sesión el usuario
							MUsuarios mdlUsuarios = new MUsuarios(conexion);
							mdlUsuarios.getUsuarioByUid(mdlRecordarme.uid);
							mdlUsuarios.getProximoUsuario();							
							
							Usuario usuario = new Usuario(mdlUsuarios);
							
							sesion.estado = SesionUsuario.LOGUEADO;
							sesion.usuario = usuario;
							
							identificado = true;
							
							// Generar un nuevo token // @Deprecated by Juan
//								Random r = new Random();
//								int numAleatorio = r.nextInt(Integer.MAX_VALUE);
//								
//								mdlRecordarme.token = cifrado.hash("SHA-256", mdlRecordarme.salt + numAleatorio);
//								mdlRecordarme.actualizaRecordarme();
//								
//								mapaCookies.put("token", String.valueOf(numAleatorio));
							
						} else {
							// Si no es el token correcto: Borrar por seguridad
							mdlRecordarme.borrarRecordarme();
						
							// Borrar además las cookies de recordar
							borraCookiesRecordar(mapaCookies);
							response.addCookie(crearCookiePersistente("jeecommerce.data", mapaCookies.toString()));
						}
						
					} else {
						// Si no existe el identificador: borrar cookies
						borraCookiesRecordar(mapaCookies);
						response.addCookie(crearCookiePersistente("jeecommerce.data", mapaCookies.toString()));
					}
					
				} // Si (sub)cookie recordar no está creada: no hacer nada
				
			} // Cookie 'jeecommerce.data' no creada: no hacer nada
			
		} else { // Ya logueado: devolver true
			
			identificado = true;
		}
		
		return identificado;
	}
	
	public void borraCookiesRecordar(HashMapCookie<String, String> mapaCookies)
	{
		mapaCookies.remove("recordar");
		mapaCookies.remove("muid");
		mapaCookies.remove("token");
	}
}
