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


		<div id="wrap-configuracion" class="container">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab-perfil" data-toggle="tab">Perfil</a></li>
					<li><a href="#tab-cuenta" data-toggle="tab">Cuenta</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="tab-perfil">
						<form id="perfil" class="col-sm-4 col-xs-12">
							
							<div class="form-group">

								<div class="campo">
									<label for="imagen">Imagen de Perfil:</label>
									
									<div class="container">

										<img src="img/autor.png" alt="Foto perfil" height="150" width="150" class="img-responsive img-thumbnail">

										<div class="campo">
											<input type="file" id="imagen" name="" value="" placeholder="">
										</div>
									</div>
								</div>

								<div class="campo">
									<label for="nombre">Nombre y Apellidos:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-user"></span>
										</div>

										<input type="text" id="nombre" class="form-control" value="Jesús González Jaén" placeholder="Nombre y Apellidos">
									</div>
								</div>

								<div class="campo">

									<label for="direccion">Dirección:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-globe"></i>
										</div>

										<input type="text" id="direccion" value="Calle la Plaza nº 1" placeholder="Dirección" class="form-control">
									</div>

								</div>

								<div class="campo">
									
									<label for="poblacion">Población:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-globe"></i>
										</div>

										<input type="text" id="poblacion" value="El Puerto de Santa María" placeholder="Ciudad/Población" class="form-control">
									</div>

								</div>
								
								<div class="campo">
									<label for="provincia">Provincia:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-globe"></i>
										</div>

										<input type="text" id="provincia" value="Cádiz" placeholder="Provincia" class="form-control">
									</div>
								</div>

								<div class="campo">
									<label for="codigo-postal">Código Postal:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-map-marker"></span>
										</div>

										<input type="text" id="codigo-postal" value="11500" placeholder="Código Postal" class="form-control">
									</div>
								</div>

								<div class="campo">
									<label for="telefono">Teléfono:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-phone"></i>
										</div>

										<input type="tel" id="telefono" value="612345678" placeholder="Teléfono de contacto" class="form-control">
									</div>
								</div>

								<div class="campo">
									<label for="telefono">WhatsApp:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-whatsapp"></i>
										</div>

										<input type="tel" id="telefono" value="612345678" placeholder="WhatsApp (555-123-456)" class="form-control">
									</div>
								</div>

								<div class="campo">
									<button name="" value="" class="btn btn-lg btn-success">Guardar <i class="fa fa-save"></i></button>
								</div>
							</div>
						</form>
					</div>

					<div class="tab-pane" id="tab-cuenta">
						<form id="cuenta" class="col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="campo">
									<label for="email">Dirección de Correo Electrónico:</label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-at"></i>
										</div>
										
										<input type="email" id="email" value="nombre@empresa.es" placeholder="nombre@gmail.com" class="form-control">
									</div>
								</div>
								
								<div class="campo">
									<label for="contrasena">Contraseña actual:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-lock"></i>
										</div>
										
										<input type="password" id="contrasena" placeholder="Contraseña actual" class="form-control">
									</div>
								</div>

								<small class="text-warning">Debe introducir su contraseña actual si va a cambiar su dirección de correo electrónico o su contraseña.</small>

								<div class="campo">
									<label for="nueva-contrasena">Nueva contraseña:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-lock"></i>
										</div>
										
										<input type="password" id="nueva-contrasena" placeholder="Nueva contraseña" class="form-control">
									</div>
								</div>
								
								<div class="campo">
									<label for="confirmar-contrasena">Confirmar nueva contraseña:</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-lock"></i>
										</div>
										
										<input type="password" id="confirmar-contrasena" placeholder="Confirmar contraseña" class="form-control">
									</div>
								</div>

								<div class="campo">
									<button name="" value="" class="btn btn-lg btn-success">Guardar <i class="fa fa-save"></i></button>
								</div>
							</div>
						</form>
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