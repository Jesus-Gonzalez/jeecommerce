<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="modelos.Comentario" %>
<%@ page import="helpers.ColorsHelper" %>

<%

List<Comentario> lstComentarios = (List<Comentario>) request.getAttribute("comentarios.lista");
boolean hayComentarios = lstComentarios.size() > 0;

%>

<%@ include file="/WEB-INF/header.jsp" %>

<script src='https://www.google.com/recaptcha/api.js'></script>

		<article id="producto" class="container" role="main" data-ng-controller="productoController">
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<img src="img/productos/<%= request.getAttribute("articulo.imagen") %>" alt="Alfalfa Packs" class="img-responsive img-thumbnail">
				</div>

				<div class="col-sm-8 col-xs-12 producto">
					<h1 class="titulo"><%= request.getAttribute("articulo.nombre") %></h1>
					<p><strong>Categoría:</strong> <%= request.getAttribute("categoria.nombre") %></p>
					<p class="descripcion"><%= request.getAttribute("articulo.descripcion") %></p>
					<p><strong>Precio:</strong> <%= request.getAttribute("articulo.precio") %> €</p>
					<div class="row">
						<div class="form-group col-sm-6 col-xs-12">
							<div class="input-group">
								<div class="input-group-addon">Cantidad</div>

								<input type="number" id="txt-cantidad-anadir" value="1" placeholder="Cantidad" class="form-control">

								<div class="input-group-btn">
									<button class="btn btn-success" data-ng-click="anadirAlCarro(<%= request.getAttribute("articulo.artid") %>)">Añadir <i class="fa fa-cart-plus"></i></button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>

		<aside id="comentarios" class="container" data-ng-controller="comentariosController">
			<h2>Comentarios</h2>
			<div class="row comentario">
				<div class="col-xs-2">
					<span class="img-responsive img-circle img-thumbnail img-anonimo"></span>
				</div>

				<div class="col-xs-10">
					<div class="col-xs-8 textarea-comentario">
						<textarea id="txtarea-comentario" class="form-control" rows="5" required data-ng-model="contenido"></textarea>
					</div>

					<div class="clearfix"></div>

					<div class="form-group">
					  <label>Captcha</label>
						<div id="google-recaptcha-comentarios" class="g-recaptcha" data-sitekey="6Le6PCITAAAAAACYTtrokN0z8iA9Iz3gEDQBxcqr"></div>
					  <p class="help-block">Introduzca el captcha para enviar su comentario.</p>
					</div>

					<div class="clearfix"></div>

					<div class="wrap-btn-comentar">
						<button id="btn-comentar" class="btn btn-block btn-md btn-primary" data-ng-click="comentar(<%= request.getAttribute("articulo.artid") %>)">Enviar Comentario <span class="glyphicon glyphicon-send"></span></button>
						<p class="help-block">Sus datos serán privados</p>
					</div>
				</div>
			</div>

			<div class="row comentario" data-ng-repeat="comentario in comentarios">
				<div class="col-xs-2">
					<span class="img-responsive img-circle img-thumbnail img-anonimo" style="background-color:#<%= ColorsHelper.generaColorHexadecimal() %>"></span>
				</div>

				<div class="col-xs-10">
					<h4 class="autor-comentario">Fecha: <small class="fecha-comentario">{{ comentario.fecha }}</small></h4>
					<p>{{ comentario.contenido | lowercase }}</p> <%-- El comentario en minúsculas, para evitar comentarios en mayúsculas (al igual que en PcComponentes.com) --%>
				</div>
			</div>

			<% if (!hayComentarios) { %>

			<input type="hidden" id="hidden-hay-comentarios" value="false">

			<div id="wrapper-sin-comentarios" class="row">
				<div id="sin-comentarios" class="alert alert-danger">
					<h1><span class="glyphicon glyphicon-alert"></span> No se han hecho comentarios</h1>
					<p>Sea el primero en realizar un comentario</p>
				</div>
			</div>

			<% } else { %>

			<% for (Comentario comentario : lstComentarios) { %>

			<div class="row comentario">
				<div class="col-xs-2">
					<span class="img-responsive img-circle img-thumbnail img-anonimo" style="background-color:#<%= ColorsHelper.generaColorHexadecimal() %>"></span>
				</div>

				<div class="col-xs-10">
					<h4 class="autor-comentario">Fecha: <small class="fecha-comentario"><%= new Date(comentario.fecha) %></small></h4>
					<p><%= comentario.contenido.toLowerCase()  %></p> <%-- El comentario en minúsculas, para evitar comentarios en mayúsculas (al igual que en PcComponentes.com) --%>
				</div>
			</div>

			<% } %>
			<% } %>
		</aside>

<%@ include file="/WEB-INF/footer.jsp" %>
