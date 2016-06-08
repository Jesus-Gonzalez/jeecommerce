<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.Pedido" %>
<%@ page import="helpers.PedidosHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>

<%

List<Pedido> lstPedidos = (List<Pedido>) request.getAttribute("lista.pedidos");

%>

<%@ include file="header.jsp" %>

<div class="container">
  <h1>Gestión de Pedidos</h1>

  <% if (lstPedidos.size() == 0) { %>

	<div class="alert alert-warning">
		<h1>Sin Pedidos</h1>
		<p>Usted no ha creado pedidos por el momento.</p>
		<p><a href="<%= request.getContextPath() %>/catalogo.html">Empiece a añadir productos y crea un pedido ahora</a></p>
	</div>

  <% } else { %>

    <table id="table-gestionar-pedidos" class="table-striped">
    <thead>
    	<tr>
    		<th>ID Pedido</th>
    		<th>Fecha</th>
    		<th>Importe</th>
    		<th>Estado</th>
    		<th>Forma Pago</th>
    		<th>Ver</th>
    	</tr>
    </thead>
    
    <% for (Pedido pedido : lstPedidos) { %>
      <tr>
        <td class="codigo-pedido"><%= pedido.pid %></td>
        <td class="fecha"><%= new Date(pedido.fecha) %></td>
        <td class="importe"><%= pedido.importe.toString() %></td>
        <td class="estado"><%= PedidosHelper.getStrEstado(pedido.estado) %></td>
        <td class="formaPago"><%= PedidosHelper.getStrFormaPago(pedido.formaPago) %></td>
        <td class="ver"><a role="button" class="btn btn-primary" href="ver-pedido.html?pid=<%= pedido.pid %>">Ver Pedido <i class="fa fa-play-circle"></i></a></td>
      </tr>
     <% } %>
    </table>

  <% } %>
</div>


<%@ include file="footer.jsp" %>
