<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<p><strong>fa-carNombre:</strong> <%= direccion.nombre %></p>
					<p><strong><i class="fa fa-phone"></i> Teléfono:</strong> <%= direccion.telefono %></p>
					<div>
						<strong><i class="fa fa-fw fa-map-marker"></i> Ubicación:</strong>
						<p><%= direccion.direccion %></p>
						<p><%= direccion.localidad %> (<%= direccion.codigoPostal %>) - <%= direccion.provincia %></p>
					</div>
				</div>

			<% } %>

			<% } %>

			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" data-parent="#crear-direccion-form" href="javascript:void(0)">
			          <i class="fa fa-plus"></i> Crear Dirección
			        </a>
			      </h4>
			    </div>
			    <div id="crear-direccion-form" class="panel-collapse collapse">
						<div class="panel-body">
							<form id="form-crear-direccion">
								<!-- Nombre -->
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
								  <input type="text" id="input-nombre" class="form-control" placeholder="Nombre de persona a quien se envía el pedido">
								</div>

								<!-- Dirección -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-road"></i></span>
								  <input type="text" id="input-direccion" class="form-control" placeholder="Dirección donde se envía">
								</div>

								<!-- Localidad -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
								  <input type="text" id="input-localidad" class="form-control" placeholder="Localidad del envío">
								</div>

								<!-- Código Postal -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-map"></i></span>
								  <input type="number" id="input-codigo-postal" class="form-control" placeholder="Código postal de la localidad del envío">
								</div>

								<!-- Provincia -->
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-map-o"></i></span>
								  <input type="text" id="input-provincia" class="form-control" placeholder="Provincia de la localidad del envío">
								</div>

								<!-- Teléfono -->
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
								  <input type="tel" id="input-telefono" class="form-control" placeholder="(Opcional) Teléfono de contacto">
								</div>

								<button type="button" id="btn-anadir-direccion"><span class="glyphicon glyphicon-plus"></span> Añadir Dirección</button>
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
				        <a data-toggle="collapse" data-parent="#slc-transferencia" href="#">
				          Transferencia Bancaria
				        </a>
				      </h4>
				    </div>
				    <div id="slc-transferencia" class="panel-collapse collapse in">
				      <div class="panel-body">
				        <input type="radio" name="forma-pago" value="transferencia"> Pagar con transferencia bancaria
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#slc-" href="#">
									Metálico
				        </a>
				      </h4>
				    </div>
				    <div id="" class="panel-collapse collapse">
				      <div class="panel-body">
								<input type="radio" name="forma-pago" value="metalico"> Pagar en metálico
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#" href="#">
									Contrarrembolso
				        </a>
				      </h4>
				    </div>
				    <div id="" class="panel-collapse collapse">
				      <div class="panel-body">
								<input type="radio" name="forma-pago" value="contrarrembolso"> Pagar contrarrembolso
				      </div>
				    </div>
				  </div>

					<div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#" href="#">
									Tarjeta de Crédito/Débito
				        </a>
				      </h4>
				    </div>
				    <div id="" class="panel-collapse collapse">
				      <div class="panel-body">
								<input type="text" id="input-dueno" placeholder="Dueño de la tarjeta">
									<br>
								<input type="text" id="numero-tarjeta" placeholder="Número de la tarjeta">
									<br>
								<input type="text" id="mes-caducidad" placeholder="Mes">
								<input type="text" id="mes-caducidad" placeholder="Año">
									<br>
								<input type="number" name="codigo-seguridad" placeholder="Código Seguridad">
				      </div>
				    </div>
				  </div>
				</div>

				<button type="button" id="btn-submit-forma-pago">Realizar el Pago</button>
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

<%@ include file="/WEB-INF/footer.jsp" %>
