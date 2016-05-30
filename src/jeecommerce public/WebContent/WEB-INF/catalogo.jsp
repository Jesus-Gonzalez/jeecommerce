<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-3">
					<div class="caja-menu" data-ng-controller="categoriasController as catCtrl">
						<h3>Categorías</h3>
						<ul id="list-categorias">
						</ul>
					</div>
				</div>

				<section id="lista-productos" class="col-sm-9" data-ng-controller="catalogoController as catalogoCtrl">
					<h1>Lista de Productos</h1>
					<p class="text-danger" data-ng-hide="productos.length > 0">No se han encontrado productos según su criterio</p>
					<div class="col-sm-3 col-xs-12 caja-producto" data-ng-repeat="producto in productos">

						<div class="fa-stack fa-lg icono" data-ng-show="producto.destacado">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-star fa-stack-1x text-warning"></i>
						</div>

						<div class="fa-stack fa-lg icono" data-ng-show="!producto.destacado && producto.stock === 0">
							<i class="fa fa-square fa-stack-2x"></i>
							<i class="fa fa-exclamation fa-stack-1x text-danger"></i>
						</div>

						<div class="fa-stack fa-lg icono" data-ng-show="!producto.destacado && producto.stock != 0 && producto.fechaCreacion < fechaActual - 259200000">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-check fa-stack-1x text-success"></i>
						</div>

						<a data-ng-href="{{ ABS_PATH }}/producto.html?artid={{ producto.artid }}">
							<img data-ng-src="img/productos/{{ producto.imagen }}" alt="Alfalfa Packs" class="img-responsive img-thumbnail">
							<h5 class="titulo-producto">{{ producto.nombre }} <small class="text-danger" data-ng-show="producto.stock === 0">(Agotado!)</small> <small class="text-success" data-ng-show="producto.stock != 0 && producto.fechaCreacion < fechaActual - 259200000">(Nuevo!)</small></h5>
						</a>
						<!-- Más tarde será un botón -->
						<!-- <button class="btn btn-md btn-success">Añadir <i class="fa fa-cart-plus"></i></button> -->
						<button class="btn btn-md btn-success" data-ng-click="anadirAlCarro(producto.artid, 1)">Añadir <i class="fa fa-cart-plus"></i></button>
						<a data-ng-show="producto.stock === 0" data-toggle="modal" href="#popup-avisarme" class="btn btn-md btn-warning"><i class="fa fa-phone"></i> Avísame</a>
					</div>
				</section>
			</div>
		</div>

		<div class="text-center">
			<ul class="pagination" id="paginacion-productos" data-ng-controller="paginacionController as pagCtrl">
				<li data-ng-class="{ active: pagina == i }" data-ng-click="irToPagina(i)" data-ng-repeat="i in [] | range: paginas"><a href="javascript:void(0)">{{ i+1 }}</a></li>
			</ul>
		</div>

		<div class="modal fade" id="popup-avisarme">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Avisarme cuando hayan nuevas unidades</h4>
					</div>

					<div class="modal-body">
						<p>Introduzca su dirección de correo electrónico y le enviaremos un mensaje cuando se actualice el stock y haya unidades disponibles.</p>

						<form>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-at"></i></span>

								<input type="email" class="form-control" id="email-avisarme" placeholder="nombre@empresa.es">
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<button type="button" class="btn btn-primary">Avísame</button>
					</div>
				</div>
			</div>
		</div>

<%@ include file="/WEB-INF/footer.jsp" %>
