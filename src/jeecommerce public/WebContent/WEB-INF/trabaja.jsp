<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Proyecto Final</title>

		<!-- Fuentes -->
		<link href='https://fonts.googleapis.com/css?family=Poiret+One|Ubuntu:400,300' rel='stylesheet' type='text/css'>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/flag-icon.min.css">
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
								<li><a href="perfil.html">Mi perfil</a></li>
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
			<div id="jumbotron-trabajo" class="jumbotron">
				<div class="container">
					<h2><i class="fa fa-bank"></i> Trabajo en Servicios Agrícolas <q>El Pena</q></h2>
					<p>Encuentra tu trabajo de ensueño entre nuestras filas</p>
					<p>
						<a class="btn btn-success btn-lg" data-toggle="modal" data-target="#modal-curriculum"><i class="fa fa-send-o"></i> Enviar CV</a>
					</p>
				</div>
			</div>
			
			<h1>Puestos de trabajo ofertados</h1>

			<div id="trabaja-panel-trabajos" class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" href="#trabajos-es"><span class="flag-icon flag-icon-es"></span> España</a>
			      </h4>
			    </div>
			    <div id="trabajos-es" class="panel-collapse collapse">
			      <div class="panel-body">
					<ul class="lista-trabajos-disponibles">
						<li>Trabajo ofertado de ejemplo (<em>2 puestos</em>)</li>
						<li>Otro trabajo de ejemplo (<em>5 puestos</em>)</li>
					</ul>
			      </div>
			    </div>


				<!-- Ejemplo para mostrar el estilo del acordeón únicamente -->
			    <div class="panel-heading">
			    	<h4 class="panel-title"><a data-toggle="collapse" href="#trabajos-us"><span class="flag-icon flag-icon-us"></span> Estados Unidos</a></h4>
			    </div>

			    <div id="trabajos-us" class="panel-collapse collapse">
			    	<div class="panel-body">
						<h5 class="text-danger">No hay trabajos disponible en Estados Unidos <span class="flag-icon flag-icon-us"></span></h5>
			    	</div>
			    </div>

				<!-- Ejemplo para mostrar el estilo del acordeón únicamente -->
			    <div class="panel-heading">
			    	<h4 class="panel-title"><a data-toggle="collapse" href="#trabajos-de"><span class="flag-icon flag-icon-de"></span> Alemania</a></h4>
			    </div>

			    <div id="trabajos-de" class="panel-collapse collapse">
			    	<div class="panel-body">
			    		<h5 class="text-danger">No hay trabajos disponible en Alemania <span class="flag-icon flag-icon-de"></span></h5>
			    	</div>
			    </div>

				<!-- Ejemplo para mostrar el estilo del acordeón únicamente -->
			    <div class="panel-heading">
			    	<h4 class="panel-title"><a data-toggle="collapse" href="#trabajos-fr"><span class="flag-icon flag-icon-fr"></span> Francia</a></h4>
			    </div>

			    <div id="trabajos-fr" class="panel-collapse collapse">
			    	<div class="panel-body">
			    		<h5 class="text-danger">No hay trabajos disponible en Francia <span class="flag-icon flag-icon-fr"></span></h5>
			    	</div>
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

		<!-- Modal CV -->
		<div class="modal fade" id="modal-curriculum" tabindex="-1" role="dialog" aria-labelledby="modalCurriculum" ng-app="proyectoFinal" ng-controller="cvCtrl">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="modalCurriculum">Enviar mi CV</h4>
		      </div>
		      <form id="form-trabaja-cv" ng-submit="enviarCurriculum($event)" ng-model="cv.form">
		      	<div class="modal-body">
		      		<div id="trabaja-cv-exito">
		      			<h2 class="text-success">Envio Exitoso</h2>
		      			<p class="text-success">Ha enviado su CV con éxito.</p>

		      			<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal">Cerrar</button>
						</div>
		      		</div>

		      		<div id="trabaja-datos-cv">
			        	<div class="form-group">
			        		<label for="nombre">Nombre Completo</label>
			        		<input id="nombre" class="form-control" type="text" placeholder="Nombre y Apellidos" ng-model="cv.nombre">
			        	</div>

			        	<div class="form-group">
			        		<label for="correo">Correo Electrónico</label>
			        		<input type="email" class="form-control" placeholder="ejemplo@gmail.com" ng-model="cv.correo">
			        	</div>
						
						<div class="form-group">
							<label for="file-cv">Subir CV</label>
							<input type="file" class="form-control" placeholder="Mi archivo de CV">
						</div>
				      	
				      	<div class="modal-footer">
					        <button class="btn btn-default" data-dismiss="modal">Cerrar</button>
					        <input type="submit" value="Enviar CV" type="button" class="btn btn-primary">
				      	</div>
				      </div>
			      </div>
		      </form>
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
 		<script src="js/scripts.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Angular CV -->
 		<script src="js/angular/trabaja.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>