<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelos.Direccion" %>

<%@ include file="/WEB-INF/header.jsp" %>

<%
	List<Direccion> lstDirecciones = (List<Direccion>) request.getAttribute("lista.direcciones");
%>

  <div class="container" id="pasos-wrapper" ng-controller="pedidoController">
    <h1 class="paso-actual"><i class="fa fa-map"></i> Direcciones</h1>
    <div id="direcciones-wrapper">
			<% if (lstDirecciones.size() > 0) { %>

			<% for (Direccion direccion : lstDirecciones) { %>

				<div data-did="<%= direccion.did %>" class="direccion">
				<input type="radio" class="slc-direccion" name="direccion" value="<%= direccion.did %>">
					<p><strong><i class="fa fa-fw fa-user"></i> Nombre:</strong> <%= direccion.nombre %></p>
					<p><strong><i class="fa fa-fw fa-phone"></i> Teléfono:</strong> <%= direccion.telefono %></p>
					<div>
						<strong><i class="fa fa-fw fa-map-marker"></i> Ubicación:</strong>
						<p><%= direccion.direccion %></p>
						<p><%= direccion.localidad %> (<%= direccion.codigoPostal %>)</p>
					</div>
				</div>

			<% } %>

			<% } %>

			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" data-parent="#crear-direccion-form" href="#crear-direccion-form">
			          <i class="fa fa-plus"></i> Crear Dirección
			        </a>
			      </h4>
			    </div>
					<div id="crear-direccion-form" class="panel-collapse collapse">
						<div class="panel-body">
							<form id="form-crear-direccion">
								<!-- Nombre -->
								<div class="input-group">
									<span class="input-group-addon"><span class="fa fa-fw fa-user"></span></span>
								  <input data-ng-model="nombre" type="text" id="input-nombre" class="form-control" placeholder="Nombre de persona a quien se envía el pedido">
								</div>

								<!-- Dirección -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-fw fa-road"></i></span>
								  <input data-ng-model="direccion" type="text" id="input-direccion" class="form-control" placeholder="Dirección donde se envía">
								</div>

								<!-- Localidad -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-fw fa-map-marker"></i></span>
								  <input data-ng-model="localidad" type="text" id="input-localidad" class="form-control" placeholder="Localidad del envío">
								</div>

								<!-- Código Postal -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-fw fa-map-o"></i></span>
								  <input data-ng-model="codpostal" type="number" id="input-codigo-postal" class="form-control" placeholder="Código postal de la localidad del envío">
								</div>

								<!-- Teléfono -->
								<div class="input-group">
									<span class="input-group-addon"><span class="fa fa-fw fa-phone"></span></span>
								  <input data-ng-model="telefono" type="tel" id="input-telefono" class="form-control" placeholder="(Opcional) Teléfono de contacto">
								</div>

								<button type="button" id="btn-anadir-direccion" class="btn btn-primary" ng-click="anadirDireccion()"><span class="glyphicon glyphicon-plus"></span> Añadir Dirección</button>
							</form>
			      </div>
			    </div>
			  </div>
			</div>
    </div>

    <h1><i class="fa fa-credit-card"></i> Forma de Pago</h1>
    <div id="pago-wrapper" class="hidden">

			<form id="form-forma-pago">
				<div class="panel-group" id="panel-forma-pago">
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#panel-forma-pago" href="#slc-transferencia">
				          Transferencia Bancaria
				        </a>
				      </h4>
				    </div>
				    <div id="slc-transferencia" class="panel-collapse collapse">
				      <div class="panel-body">
				        <input type="radio" name="forma-pago" value="transferencia"> Pagar con transferencia bancaria
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#panel-forma-pago" href="#slc-metalico">
									Metálico
				        </a>
				      </h4>
				    </div>
				    <div id="slc-metalico" class="panel-collapse collapse">
				      <div class="panel-body">
								<input type="radio" name="forma-pago" value="metalico"> Pagar en metálico
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#panel-forma-pago" href="#slc-contrarrembolso">
									Contrarrembolso
				        </a>
				      </h4>
				    </div>
				    <div id="slc-contrarrembolso" class="panel-collapse collapse">
				      <div class="panel-body">
								<input type="radio" name="forma-pago" value="contrarrembolso"> Pagar contrarrembolso
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#panel-forma-pago" href="#slc-tarjeta">
									Tarjeta de Crédito/Débito
				        </a>
				      </h4>
				    </div>
				    <div id="slc-tarjeta" class="panel-collapse collapse in">
				      <div class="panel-body">
								<input data-ng-model="numeroTarjeta" type="number" id="numero-tarjeta" placeholder="Número de la tarjeta">
									<br>
								<input data-ng-model="mesCaducidadTarjeta" type="number" id="mes-caducidad" placeholder="Mes">
								<input data-ng-model="anoCaducidadTarjeta" type="number" id="ano-caducidad" placeholder="Año">
									<br>
								<input data-ng-model="cvc" type="number" name="codigo-seguridad" placeholder="Código Seguridad">
				      </div>
				    </div>
				  </div>
				</div>

				<button data-ng-click="pagar()" type="button" id="btn-submit-forma-pago" class="btn btn-success"><i class="fa fa-money"></i> Realizar el Pago</button>
			</form>


    </div>
  </div>

	<div id="pedido-confirmado-wrapper" class="container hidden">
		<h1 class="text-success">Pedido Realizado! <i class="fa fa-check"></i></h1>
		<p>
			Su pedido ha sido realizado con éxito. En breve se realizará su envío y nos pondremos en contacto con usted en caso de ser necesario.
		</p>
		<p class="hidden" id="forma-pago-transferencia">
			Puede realizar el pago a cualquiera de las siguientes cuentas bancarias:
			<div class="bancos">
				<p><strong><i class="fa fa-bank"></i> BBVA: </strong> 11455881428845688658</p>
				<p><strong><i class="fa fa-bank"></i> Banco Santander: </strong> 451154458745878458</p>
			</div>

			Escriba como concepto el identificador del pedido: <span class="identificador-pedido"></span>
		</p>

		<p class="hidden" id="forma-pago-metalico">
			Usted realizará el pago en metálico.
			Nos pondremos en contacto con usted para concretar la entrega.
		</p>
		<p class="hidden" id="forma-pago-tarjeta">
			Su pedido se ha pagado satisfactoriamente.
		</p>
	</div>

<!-- Stripe.JS -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
<script type="text/javascript">
  Stripe.setPublishableKey('pk_test_eL3o8HZknknKswKMESgfYBXA');
</script>
<%@ include file="/WEB-INF/footer.jsp" %>
