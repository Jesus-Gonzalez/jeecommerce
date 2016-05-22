<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelos.Direccion" %>
<%@ page import="modelos.Banco" %>

<%@ include file="/WEB-INF/header.jsp" %>

<%
	List<Direccion> lstDirecciones = (List<Direccion>) request.getAttribute("lista.direcciones");
	List<Banco> lstBancos = (List<Banco>) request.getAttribute("lista.bancos");
%>

  <div class="container" id="pasos-wrapper" data-ng-controller="pedidoController">
    <h1 class="paso-actual"><i class="fa fa-map"></i> Direcciones</h1>
    <div id="direcciones-wrapper">
			<% if (lstDirecciones.size() > 0) { %>

			<% for (Direccion direccion : lstDirecciones) { %>

				<div class="direccion" data-ng-click="seleccionarDireccion(<%= direccion.did %>)">
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

			<div class="direccion" data-ng-click="seleccionarDireccion(direccion.did)" data-ng-repeat="direccion in direcciones">
				<p><strong><i class="fa fa-fw fa-user"></i> Nombre:</strong> {{ direccion.nombre }}</p>
				<p><strong><i class="fa fa-fw fa-phone"></i> Teléfono:</strong> {{ direccion.telefono }}</p>
				<div>
					<strong><i class="fa fa-fw fa-map-marker"></i> Ubicación:</strong>
					<p>{{ direccion.direccion }}</p>
					<p>{{ direccion.localidad }} ({{ direccion.codigoPostal }})</p>
				</div>
			</div>

			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a id="crear-direccion-panel" data-toggle="collapse" data-parent="#crear-direccion-form" href="#crear-direccion-form">
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

								<button type="button" id="btn-anadir-direccion" class="btn btn-primary" data-ng-click="anadirDireccion()"><span class="glyphicon glyphicon-plus"></span> Añadir Dirección</button>
							</form>
			      </div>
			    </div>
			  </div>
			</div>
    </div>

    <h1><i class="fa fa-credit-card"></i> Forma de Pago</h1>
    <div id="pago-wrapper" class="hidden">

			<button id="btn-volver-a-direcciones" type="button" name="button" class="btn btn-warning" data-ng-click="returnToDirecciones()">Volver al paso anterior</button>

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
								<input id="numero-tarjeta" data-ng-model="numeroTarjeta" class="form-control" type="number" id="numero-tarjeta" placeholder="Número de la tarjeta">
									<br>
								<select id="mes-caducidad-tarjeta" data-ng-model="mesCaducidadTarjeta" class='form-control'>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select>


								<select id="ano-caducidad-tarjeta" data-ng-model="anoCaducidadTarjeta" class='form-control'>
								  <option value="2016">2016</option>
								  <option value="2017">2017</option>
								  <option value="2018">2018</option>
								  <option value="2019">2019</option>
								  <option value="2020">2020</option>
								  <option value="2021">2021</option>
								  <option value="2022">2022</option>
								  <option value="2023">2023</option>
								  <option value="2024">2024</option>
								  <option value="2025">2025</option>
								  <option value="2026">2026</option>
								  <option value="2027">2027</option>
								  <option value="2028">2028</option>
								  <option value="2029">2029</option>
								  <option value="2030">2030</option>
								  <option value="2031">2031</option>
								  <option value="2032">2032</option>
									<option value="2033">2033</option>
								</select>
									<br>
								<input id="cvc-tarjeta" data-ng-model="cvc" type="number" name="codigo-seguridad" class="form-control" placeholder="Código Seguridad">
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
		<p>
			Su ID de pedido es: <strong class="pid-identificador"></strong>
			<br>
			Guarde el ID de pedido como referencia cuando se ponga en contacto.
		</p>
		<div class="hidden" id="forma-pago-transferencia">
			<p>Puede realizar el pago a cualquiera de las siguientes cuentas bancarias:</p>
			<div class="bancos">
				<% for (Banco banco : lstBancos) { %>
				<p><strong><i class="fa fa-bank"></i> <%= banco.nombre %>: </strong> <%= banco.numero %></p>
				<% } %>
			</div>

			Escriba como concepto el identificador del pedido: <strong class="pid-identificador"></strong>
		</div>

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
