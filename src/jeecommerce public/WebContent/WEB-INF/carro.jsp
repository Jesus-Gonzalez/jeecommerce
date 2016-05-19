<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="modelos.Carro" %>
<%@ page import="modelos.Articulo" %>

<%@ include file="/WEB-INF/header.jsp" %>

<%

	Carro carro = (Carro) request.getAttribute("carro");

%>

		<div id="wrap-carro" class="container">

			<h1><i class="fa fa-shopping-cart"></i> Carro</h1>

			<% if (carro == null || carro.articulos == null || carro.articulos.size() == 0) { %>

			<div class="alert alert-success">
				<h1><i class="fa fa-times"></i> Sin productos en el carro</h1>
				<p>Usted no ha añadido productos al carro.</p>
				<p>Puede empezar a <a href="catalogo.html">añadir artículos</a> ahora</p>
			</div>

			<% } else { %>

			<table id="tabla-carro" class="table table-striped table-hover">
				<thead id="thead-carro">
					<tr>
						<th>Artículo</th>
						<th>Cantidad</th>
						<th>Total</th>
						<th>Eliminar</th>
					</tr>
				</thead>

				<tbody id="tbody-carro">
					<% for (Articulo articulo : carro.articulos.values()) { %>
					<tr class="producto" data-artid="<%= articulo.artid %>">
						<td class="item-carro-nombre"><%= articulo.nombre %> <small class="precio">(<%= articulo.precio %> €)</small></td>
						<td class="item-carro-cantidad">
							<div class="input-group input-group-cantidad">
								<input type="number" id="item-1-cantidad" value="10" min="1" value="<%= articulo.cantidad %>" placeholder="Cantidad" class="form-control">

								<span class="input-group-btn">
									<button class="actualizar-cantidad-producto" type="button" class="btn btn-warning">
										<i class="fa fa-refresh"></i>
									</button>
								</span>
							</div>
						</td>
						<td class="item-carro-total"><%= articulo.precio.multiply(BigDecimal.valueOf(articulo.cantidad)) %> €</td>
						<td class="item-carro-eliminar">
							<button class="eliminar-producto btn btn-md btn-danger">
								<i class="fa fa-times"></i>
							</button>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>

			<div id="caja-carro" class="col-sm-4 col-xs-10">
				<h3><i class="fa fa-money"></i> Total: <strong id="precio-total"><%= carro.total %> €</strong></h3>

				<div id="caja-carro-botones">
					<a href="comprar.html" class="btn btn-lg btn-success">
						<i class="fa fa-shopping-cart"></i> Realizar compra
					</a>

					<a href="comprar.html?cancelar=true" class="btn btn-md btn-warning">
						<i class="fa fa-times-circle"></i> Cancelar compra
					</a>
				</div>
			</div>
			<% } %>
		</div>

<%@ include file="/WEB-INF/footer.jsp" %>
