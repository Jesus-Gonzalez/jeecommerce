<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.Pedido" %>
<%@ page import="modelos.Direccion" %>
<%@ page import="modelos.Articulo" %>
<%@ page import="helpers.PedidosHelper" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>

<%

Pedido pedido = (Pedido) request.getAttribute("pedido");
Direccion direccion = (Direccion) request.getAttribute("direccion");

%>


<%@ include file="header.jsp" %>

<div class="container">
     <h2>Datos del Pedido</h2>
     <p><strong>ID Pedido:</strong> <%= pedido.pid %></p>
     <p><strong>Fecha:</strong> <%= new Date(pedido.fecha) %></p>
     <p><strong>Estado:</strong> <%= PedidosHelper.getStrEstado(pedido.estado) %></p>
     <p><strong>Forma de Pago:</strong> <%= PedidosHelper.getStrFormaPago(pedido.formaPago) %></p>
     <p><strong>Total:</strong> <%= pedido.importe %> €</p>
     <hr/>
     <h2>Dirección</h2>
     <p><strong>Nombre:</strong> <%= direccion.nombre %></p>
     <p><strong>Dirección:</strong> <%= direccion.direccion %></p>
     <p><strong>Localidad:</strong> <%= direccion.localidad %></p>
     <p><strong>Código Postal:</strong> <%= direccion.codigoPostal %></p>
     <p><strong>Teléfono:</strong> <%= direccion.telefono %></p>

	<table id="table-articulos" class="table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Cantidad</th>
				<th>Precio</th>
				<th>Subtotal</th>
			</tr>
		</thead>

		<tbody>
			<% for (Articulo articulo : pedido.articulos) { %>

			<tr>
				<td class="table-articulos-nombre"><%= articulo.nombre %></td>
				<td class="table-articulos-cantidad"><%= articulo.cantidad %></td>
				<td class="table-articulos-precio"><%= articulo.precio %></td>
				<td class="table-articulos-total"><%= articulo.precio.multiply(BigDecimal.valueOf(articulo.cantidad)) %></td>
			</tr>

			<% } %>
		</tbody>

	</table>
</div>

<%@ include file="footer.jsp" %>
