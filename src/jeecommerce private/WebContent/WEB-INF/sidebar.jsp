<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%
	String seccion = (String) request.getAttribute("seccion");

	if (seccion == null)
		seccion = "";

	String claseActive = " class='active'";
%>

<aside class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li <%=seccion.equals("dashboard") ? claseActive : ""%>><a
			href="dashboard">Vista General</a></li>
		<li <%=seccion.equals("configuracion") ? claseActive : ""%>><a
			href="configuracion">Configuración</a></li>
		<li <%=seccion.equals("productos") ? claseActive : ""%>><a
			href="productos">Productos</a></li>
		<li <%=seccion.equals("bancos") ? claseActive : ""%>><a
			href="bancos">Bancos</a></li>
		<li <%=seccion.equals("contacto") ? claseActive : ""%>><a
			href="contacto">Contacto</a></li>
	</ul>

	<ul class="nav nav-sidebar">
		<li <%=seccion.equals("pedidos") ? claseActive : ""%>><a
			href="pedidos">Pedidos</a></li>
		<li <%=seccion.equals("usuarios") ? claseActive : ""%>><a
			href="usuarios">Usuarios</a></li>
	</ul>
</aside>
