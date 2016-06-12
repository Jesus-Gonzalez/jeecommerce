<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>¿Dónde estamos? | Servicios Agrícolas "El Pena"</title>

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
						<li><a href="catalogo.html">Productos</a></li>
						
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-shopping-cart"></i> Carro <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Configuración</a></li>
								<li><a href="#">Cerrar sesión</a></li>
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

		<div class="container">

			<h1><i class="fa fa-map"></i> ¿Dónde estamos?</h1>
		
			<div class="row">
				<section id="datos-donde-estamos" class="col-sm-5 col-sm-offset-1 col-xs-12">
					<h3><i class="fa fa-globe"></i> ¿Dónde nos puede encontrar?</h3>
					<p>Estamos en:</p>
					<address>
						Calle Ejemplo nº 1<br>
						Piso 2, Puerta 4<br>
						El Puerto de Santa María, 11500<br>
						Cádiz<br>
						España
					</address>
				</section>

				<div class="col-sm-5 col-offset-1 col-xs-12">
					<h3><i class="fa fa-map-marker"></i> Situación en el Mapa</h3>
					<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3203.2410340666856!2d-6.2353365855632195!3d36.59650348684064!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd0dcfde136c81c9%3A0x931a09f6c9b3f546!2sReal+Plaza+de+Toros+de+El+Puerto+de+Santa+Mar%C3%ADa!5e0!3m2!1ses!2ses!4v1455630043264" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
				</div>
			</div>	
			
			<h2><i class="fa fa-video-camera"></i> Vídeo explicativo de la empresa</h2>

			<div class="row">
				<div class="col-xs-12">

					<audio id="audio-video-empresa" src="audio/mp3/Cow-SoundBible.com-868293659.mp3" autobuffer></audio>

					<video id="video-empresa" src="video/343376220.mp4" autobuffer controls poster="video/343376220.png"></video>
				
				</div>
			</div>

		</div>

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