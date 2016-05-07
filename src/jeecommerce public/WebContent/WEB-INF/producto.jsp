<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
	
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
		<link rel="stylesheet" href="css/less/main.min.css">

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
				<!-- Brand and toggle get grouped for better mobile display -->
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

		<article id="producto" class="container" role="main">
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<img src="img/alfalfa-packs.jpg" alt="Alfalfa Packs" class="img-responsive img-thumbnail">
					<p class="valoracion"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half"></i><i class="fa fa-star-o"></i></p>
				</div>
				
				<div class="col-sm-8 col-xs-12 producto">
					<h1 class="titulo">Alfalfa Secada al Sol (1kg)</h1>
					<p class="descripcion">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magni aliquam facere corporis corrupti! Necessitatibus nam voluptates officiis! Architecto iste porro minima ratione molestiae iusto maiores nihil dolorum. Quasi, tenetur, quae.</p>
					<div class="row">
						<div class="form-group col-sm-6 col-xs-12">
							<div class="input-group">
								<div class="input-group-addon">Cantidad</div>
								
								<input type="number" id="txt-cantidad-anadir" value="1" placeholder="Cantidad" class="form-control">

								<div class="input-group-btn">
									<button class="btn btn-success">Añadir <i class="fa fa-cart-plus"></i></button>
								</div>
							</div>
						</div>
					</div>
					
					<div id="msg-anade-success" class="clearfix">
						<p class="text-success">¡Producto añadido al carro con éxito!</p>
					</div>

				</div>
			</div>
		</article>

		<aside id="comentarios" class="container">
			<h2>Comentarios</h2>
			<div class="row comentario">
				<div class="col-xs-2">
					<span class="img-responsive img-circle img-thumbnail img-anonimo">
						<i class="fa fa-5x fa-user-secret"></i>
					</span>
				</div>

				<div class="col-xs-10">
					<div class="col-xs-8 textarea-comentario">
						<textarea id="txtarea-comentario" class="form-control" rows="5" required></textarea>
					</div>
					<div class="wrap-btn-comentar">
						<button id="btn-comentar" class="btn btn-block btn-md btn-primary">Enviar Comentario <span class="glyphicon glyphicon-send"></span></button>
					</div>
				</div>
			</div>

			<div class="row comentario">
				<div class="col-xs-2">
					<img src="img/autor.png" alt="Foto Jesús González" class="img-responsive img-circle img-thumbnail">
				</div>

				<div class="col-xs-10">
					<h4 class="autor-comentario">Jesús González Jaén <small class="fecha-comentario">29 Febrero 2016</small></h4>
					<p>¡Hola Mundo!</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque dignissimos rem delectus placeat provident temporibus, sunt impedit! Natus, non expedita vero mollitia dicta, cupiditate veniam odio. Maiores illum laboriosam, illo.</p>
				</div>
			</div>

			<div class="row comentario">
				<div class="col-xs-2">
					<img src="img/amigo.png" alt="Foto Tux" class="img-responsive img-circle img-thumbnail">
				</div>

				<div class="col-xs-10">
					<h4 class="autor-comentario">GNU/Tux <small class="fecha-comentario">32 Enero 2016</small></h4>
					<p>¡Hola Mundo!</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					<p>Sint facere molestias numquam, modi, incidunt commodi perspiciatis similique assumenda voluptatem nostrum beatae quos aperiam tempore doloremque itaque necessitatibus adipisci facilis earum!</p>
				</div>
			</div>
		</aside>


		<hr>

		<footer class="container" id="footer">
			<div id="footer-cajas-container" class="row">
				<div class="col-sm-3 col-xs-12 col-xs-centered footer-caja">
					<h3>Contacto</h3>
					<ul class="lista-footer">
						<li><i class="fa fa-phone fa-fw"></i> 555-123-321</li>
						<li><i class="fa fa-whatsapp fa-fw"></i> 555-123-321</li>
						<li><i class="fa fa-envelope-o fa-fw"></i> <a href="mailto:proyecto@final.daw">proyecto@final.daw</a></li>
					</ul>
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

		<!-- jQuery -->
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Angular -->
 		<script src="js/angular.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Scripts propios -->
 		<script src="js/scripts.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>