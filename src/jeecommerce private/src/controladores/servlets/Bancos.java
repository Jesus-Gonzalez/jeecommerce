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

import modelos.Articulo;
import modelos.Banco;
import modelos.MBancos;
import modelos.SesionUsuario;

@WebServlet("/bancos")
public class Bancos extends HttpServlet {
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
		
		Connection conexion = (Connection) hs.getAttribute("conexion");
		MBancos mdlBancos = new MBancos(conexion);
		
		List<Banco> lstBancos = new LinkedList<Banco>();
		Banco banco; 
		
		mdlBancos.getBancos();
		
		while (mdlBancos.getProximoBanco())
		{
			banco = new Banco();
			banco.bid = mdlBancos.bid;
			banco.nombre = mdlBancos.nombre;
			banco.numero = mdlBancos.numero;
			banco.activo = mdlBancos.activo;
			
			lstBancos.add(banco);
		}
		
		request.setAttribute("seccion", "dashboard");
		
		request.setAttribute("lista.bancos", lstBancos);
		
		request.getRequestDispatcher("/WEB-INF/bancos.jsp").forward(request, response);
		
		return;
	}
}

