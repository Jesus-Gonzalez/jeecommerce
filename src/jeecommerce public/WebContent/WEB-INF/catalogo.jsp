<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es" data-ng-app="jeecommerce">
	
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Servicios Agrícolas "El Pena"</title>

		<!-- Fuentes -->
		<link href='https://fonts.googleapis.com/css?family=Poiret+One|Ubuntu:400,300' rel='stylesheet' type='text/css'>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/less/main.css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		
		<nav id="header-navbar" class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse-menu">
						<span class="sr-only">Desplegar menú</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="catalogo.html"><abbr title="Servicios Agrícolas">SA</abbr> <q>El Pena</q></a>
				</div>
		
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse nav-collapse-menu">
					<ul class="nav navbar-nav">
						<li class="active"><a href="catalogo.html">Productos</a></li>
						
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-shopping-cart"></i> Carro <b class="caret"></b></a>
							<ul class="dropdown-menu header-dropdown">
								<li><p class="navbar-text"><strong>16</strong> producto en el carro</p></li>
								<li><p class="navbar-text">Total: <strong>1,500 €</strong></p></li>
								<li><a href="carro.html" class="btn btn-xs btn-primary">Ver carro</a></li>
							</ul>
						</li>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Jesús González <b class="caret"></b></a>
							<ul class="dropdown-menu header-dropdown">
								<li><a href="configuracion.html">Configuración</a></li>
								<li><a href="index.html" class="btn btn-xs btn-danger">Salir</a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div>
		</nav>

		<header class="container-fluid">
			<div id="index-carousel-destacados" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#index-carousel-destacados" data-slide-to="0" class=""></li>
					<li data-target="#index-carousel-destacados" data-slide-to="1" class=""></li>
					<li data-target="#index-carousel-destacados" data-slide-to="2" class="active"></li>
				</ol>
				<div class="carousel-inner productos">
					<div class="item">
						<div class="spacer"></div>
						<div class="container">
							<div class="carousel-caption">
								<img src="img/alfalfa.jpg" alt="ejemplo" class="img-thumbnail">
								<h1>Alfalfa secada al sol.</h1>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Et consequatur labore repudiandae accusantium blanditiis nam odit. Alias autem eaque, suscipit laboriosam quae, incidunt, temporibus sit, similique provident obcaecati amet sunt!</p>
								<p><a href="producto.html" class="btn btn-lg btn-default" href="#" role="button">Ver más <i class="fa fa-eye"></i></a></p>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="spacer"></div>
						<div class="container">
							<div class="carousel-caption">
								<img src="img/girasol.jpg" alt="ejemplo" class="img-thumbnail">
								<h1>Pipas de Girasol.</h1>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quas aspernatur, ipsa eum soluta praesentium aut, dignissimos est adipisci omnis commodi aliquid exercitationem et corrupti velit a similique accusantium dolore optio!.</p>
								<p><a href="producto.html" class="btn btn-lg btn-default" href="#" role="button">Ver más <i class="fa fa-eye"></i></a></p>
							</div>
						</div>
					</div>
					<div class="item active">
						<div class="spacer"></div>
						<div class="container">
							<div class="carousel-caption">
								<img src="img/alfalfa.jpg" alt="ejemplo" class="img-thumbnail">
								<h1>Producto de ejemplo</h1>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
								<p><a href="producto.html" class="btn btn-lg btn-default" href="#" role="button">Ver más <i class="fa fa-eye"></i></a></p>
							</div>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#index-carousel-destacados" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
				<a class="right carousel-control" href="#index-carousel-destacados" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</header>

		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2">
					<div class="caja-menu" data-ng-controller="categoriasController as catCtrl">
						<h3>Categorías</h3>
						<ul id="list-categorias">
						</ul>
					</div>
				</div>

				<section id="lista-productos" class="col-sm-10" data-ng-controller="catalogoController as catalogoCtrl">
					<h1>Lista de Productos</h1>
					<p class="text-danger" data-ng-hide="productos.length > 0">No se han encontrado productos según su criterio</p>
					<div class="col-sm-3 col-xs-12 caja-producto" data-ng-repeat="producto in productos">
					<a data-ng-href="{{ ABS_PATH }}/producto.html?artid={{ producto.artid }}"><h1>{{ producto.nombre }}</h1></a>
						
						<div class="fa-stack fa-lg icono" data-ng-show="producto.stock === 0">
							<i class="fa fa-square fa-stack-2x"></i>
							<i class="fa fa-exclamation fa-stack-1x text-danger"></i>
						</div>
						
						<div class="fa-stack fa-lg icono" data-ng-show="producto.stock != 0 && producto.fechaCreacion < fechaActual - 259200000">
							<i class="fa fa-circle fa-stack-2x"></i>
							<i class="fa fa-check fa-stack-1x text-success"></i>
						</div>						

						<a data-ng-href="{{ ABS_PATH }}/producto.html?artid={{ producto.artid }}">
							<img data-ng-src="img/productos/{{ producto.imagen }}" alt="Alfalfa Packs" class="img-responsive img-thumbnail">
							<h5 class="titulo-producto">{{ producto.nombre }} <small class="text-danger" data-ng-show="producto.stock === 0">(Agotado!)</small> <small class="text-success" data-ng-show="producto.stock != 0 && producto.fechaCreacion < fechaActual - 259200000">(Nuevo!)</small></h5>
						</a>
						<!-- Más tarde será un botón -->
						<!-- <button class="btn btn-md btn-success">Añadir <i class="fa fa-cart-plus"></i></button> -->
						<a href="carro.html" class="btn btn-md btn-success disabled">Añadir <i class="fa fa-cart-plus"></i></a>
						<a data-ng-show="producto.stock === 0" data-toggle="modal" href="#popup-avisarme" class="btn btn-md btn-warning"><i class="fa fa-phone"></i> Avísame</a>
					</div>
				</section>
			</div>
		</div>

		<div class="text-center">
			<ul class="pagination" id="paginacion-productos" data-ng-controller="paginacionController as pagCtrl">
				<li class="active" data-ng-repeat="i in [] | range: paginas + 1"><a href="javascript:void(0)">{{ i+1 }}</a></li>
			</ul>
		</div>

		<hr>

		<footer class="container" id="footer">
			<div id="footer-cajas-container" class="row">
				<div class="col-sm-3 col-xs-12 col-xs-centered footer-caja">
					<h3>Contacto</h3>
					<ul class="lista-footer">
						<li><i class="fa fa-phone fa-fw"></i> 555-123-321</li>
						<li><i class="fa fa-whatsapp fa-fw"></i> 555-123-321</li>
						<li><i class="fa fa-envelope-o fa-fw"></i> <a href="mailto:proyecto@final.daw">proyecto@final.daw</a></li></ul>
				</div>
				
				<div class="col-sm-3 col-xs-12 col-xs-centered footer-caja">
					<h3>Redes Sociales</h3>
					<ul class="lista-footer">
						<li><i class="fa fa-facebook fa-fw"></i> <a target="_blank" href="https://facebook.com">Facebook</a></li>
						<li><i class="fa fa-twitter fa-fw"></i> <a target="_blank" href="https://twitter.com">Twitter</a></li>
						<li><i class="fa fa-google-plus fa-fw"></i> <a target="_blank" href="https://plus.google.com">Google +</a></li>
						<li><i class="fa fa-linkedin fa-fw"></i> <a target="_blank" href="https://linkedin.com">LinkedIn</a></li>
						<li><i class="fa fa-instagram fa-fw"></i> <a target="_blank" href="https://instagram.com">Instagram</a></li>
					</ul>
				</div>

				<div class="col-sm-3 col-xs-12 col-xs-centered footer-caja">
					<h3>Sponsors</h3>
					<ul class="lista-footer">
						<li><i class="fa fa-apple fa-fw"></i> <a target="_blank" href="https://apple.com">Apple</a></li>
						<li><i class="fa fa-google fa-fw"></i> <a target="_blank" href="https://google.es">Google</a></li>
						<li><i class="fa fa-paypal fa-fw"></i> <a target="_blank" href="https://paypal.com">Paypal</a></li>
						<li><i class="fa fa-linux fa-fw"></i> <a target="_blank" href="http://www.linuxfoundation.com">Linux</a></li>
						<li><i class="fa fa-amazon fa-fw"></i> <a target="_blank" href="https://amazon.es">Amazon</a></li>
					</ul>
				</div>

				<div class="col-sm-3 col-xs-12 col-xs-centered footer-caja">
					<h3>Empresa</h3>
					<ul class="lista-footer">
						<li><i class="fa fa-map-marker fa-fw"></i> <a href="donde-estamos.html">¿Dónde estamos?</a></li>
						<li><i class="fa fa-newspaper-o fa-fw"></i> <a href="prensa.html">Prensa</a></li>
						<li><i class="fa fa-space-shuttle fa-fw"></i> <a href="trabaja.html">Trabaja con nosotros</a></li>
					</ul>
				</div>
			</div>
		</footer>

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

		<!-- jQuery -->
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Angular -->
 		<script src="js/angular.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Scripts propios -->
 		<script type="text/javascript" charset="utf-8">
 			var ABS_PATH = "<%= request.getContextPath() %>";
 		</script>
 		<script src="js/scripts.js" type="text/javascript" charset="utf-8"></script>
 		<script src="js/angular/app.js" type="text/javascript" charset="utf-8"></script>
 		<script src="js/angular/catalogo.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>