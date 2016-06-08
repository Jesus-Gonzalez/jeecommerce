<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.Direccion" %>

<%

	Direccion direccion = (Direccion) request.getAttribute("direccion");

%>

<%@ include file="/WEB-INF/header.jsp" %>

<div class="container">
	<div class="col-lg-md-5 col-sm-7">
		<div id="panel-editar-direccion" class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Edición de dirección</h3>
		  </div>
		  <div class="panel-body">
				<form data-ng-controller="editarDireccionController">

					<!-- Nombre -->
					<div class="input-group">
						<span class="input-group-addon"><span class="fa fa-fw fa-user"></span></span>
					  <input type="text" id="input-nombre" class="form-control" placeholder="Nombre de persona a quien se envía el pedido" value="<%= direccion.nombre %>">
					</div>

					<!-- Dirección -->
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-fw fa-road"></i></span>
					  <input type="text" id="input-direccion" class="form-control" placeholder="Dirección donde se envía" value="<%= direccion.direccion %>">
					</div>

					<!-- Localidad -->
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-fw fa-map-marker"></i></span>
					  <input type="text" id="input-localidad" class="form-control" placeholder="Localidad del envío" value="<%= direccion.localidad %>">
					</div>

					<!-- Código Postal -->
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-fw fa-map-o"></i></span>
					  <input type="number" id="input-codigo-postal" class="form-control" placeholder="Código postal de la localidad del envío" value="<%= direccion.codigoPostal %>">
					</div>

					<!-- Teléfono -->
					<div class="input-group">
						<span class="input-group-addon"><span class="fa fa-fw fa-phone"></span></span>
					  <input type="tel" id="input-telefono" class="form-control" placeholder="(Opcional) Teléfono de contacto" value="<%= direccion.telefono %>">
					</div>

					<button type="button" id="btn-edita-direccion" class="btn btn-primary" data-ng-click="editarDireccion(<%= direccion.did %>)"><span class="glyphicon glyphicon-plus"></span> Editar Dirección</button>
				</form>
		  </div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>
