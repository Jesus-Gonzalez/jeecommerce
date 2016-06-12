<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.SesionUsuario" %>

<%

	SesionUsuario sesion = (SesionUsuario) session.getAttribute("usuario");

	if (sesion == null || !sesion.esAdministrador)
	{
		response.sendRedirect("login");
		
	} else {
		
		response.sendRedirect("dashboard");
	}

%>