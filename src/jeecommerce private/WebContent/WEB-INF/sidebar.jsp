<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%

String seccion = (String) request.getAttribute("seccion");

%>

<aside class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
    <li<%= seccion.equals("dashboard") ? " class='active'" : "" %>><a href="dashboard">Vista General</a></li>
    <li<%= seccion.equals("configuracion") ? " class='active'" : "" %>><a href="configuracion">Configuración</a></li>
    <li<%= seccion.equals("productos") ? " class='active'" : "" %>><a href="productos">Productos</a></li>
    <li<%= seccion.equals("bancos") ? " class='active'" : "" %>><a href="bancos">Bancos</a></li>
    <li<%= seccion.equals("contacto") ? " class='active'" : "" %>><a href="contacto">Contacto</a></li>
  </ul>

  <ul class="nav nav-sidebar">
    <li<%= seccion.equals("pedidos") ? " class='active'" : "" %>><a href="pedidos">Pedidos</a></li>
    <li<%= seccion.equals("usuarios") ? " class='active'" : "" %>><a href="usuarios">Usuarios</a></li>
  </ul>
</aside>
