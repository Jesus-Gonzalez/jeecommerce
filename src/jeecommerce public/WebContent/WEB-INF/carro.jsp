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

		<div id="wrap-carro" class="container">
			
			<h1><i class="fa fa-shopping-cart"></i> Carro</h1>

			<table id="tabla-carro" class="table table-striped table-hover">
				<thead id="thead-carro">
					<tr>
						<th>Artículo</th>
						<th>Cantidad</th>
						<th>Total</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				
				<tbody id="tbody-carro">

					<tr id="item-1">
						<td class="item-carro-nombre">Alfalfa Secada al Sol <small class="precio">(30 €)</small></td>
						<td class="item-carro-cantidad">
							<div class="input-group input-group-cantidad">
								<input type="number" id="item-1-cantidad" value="10" min="1" placeholder="Cantidad" class="form-control">
								
								<span class="input-group-btn">
									<button type="button" class="btn btn-warning">
										<i class="fa fa-refresh"></i>
									</button>
								</span>
							</div>
						</td>
						<td class="item-carro-total">300 €</td>
						<td class="item-carro-eliminar">
							<button class="eliminar-producto btn btn-md btn-danger">
								<i class="fa fa-times"></i>
							</button>
						</td>
					</tr>
				
					<tr id="item-1">
						<td class="item-carro-nombre">Pipas de Girasol <small class="precio">(50 €)</small></td>
						<td class="item-carro-cantidad">
							<div class="input-group input-group-cantidad">
								<input type="number" id="item-1-cantidad" value="5" min="1" placeholder="Cantidad" class="form-control">
								
								<span class="input-group-btn">
									<button type="button" class="btn btn-warning">
										<i class="fa fa-refresh"></i>
									</button>
								</span>
							</div>
						</td>
						<td class="item-carro-total">250 €</td>
						<td class="item-carro-eliminar">
							<button class="eliminar-producto btn btn-md btn-danger">
								<i class="fa fa-times"></i>
							</button>
						</td>
					</tr>
				
					<tr id="item-1">
						<td class="item-carro-nombre">Recogida de Alfalfa <small class="precio">(500 €)</small></td>
						<td class="item-carro-cantidad">
							<div class="input-group input-group-cantidad">
								<input type="number" id="item-1-cantidad" value="1" min="1" placeholder="Cantidad" class="form-control">
								
								<span class="input-group-btn">
									<button type="button" class="btn btn-warning">
										<i class="fa fa-refresh"></i>
									</button>
								</span>
							</div>
						</td>
						<td class="item-carro-total">500 €</td>
						<td class="item-carro-eliminar">
							<button class="eliminar-producto btn btn-md btn-danger">
								<i class="fa fa-times"></i>
							</button>
						</td>
					</tr>
				</tbody>
			</table>

			<div id="caja-carro" class="col-sm-4 col-xs-10">
				<h3><i class="fa fa-money"></i> Total: <strong id="precio-total">1050 €</strong></h3>
				
				<div id="caja-carro-botones">
					<a href="login.html" class="btn btn-lg btn-success">
						<i class="fa fa-shopping-cart"></i> Realizar compra
					</a>

					<a href="index.html" class="btn btn-md btn-warning">
						<i class="fa fa-times-circle"></i> Cancelar compra
					</a>
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