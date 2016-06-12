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
		<link rel="stylesheet" href="css/less/main.css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>

		<div id="portada-header" class="jumbotron">
			<div class="background-image"></div>
			<div class="container contenido">
				<h1><span class="glyphicon glyphicon-leaf"></span> Servicios Agrícolas</h1>
				<h2><q>El Pena</q></h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
				<a href="donde-estamos.jsp" class="btn btn-lg btn-default">¿Quiénes somos? <span class="glyphicon glyphicon-user"></span></a>
			</div>
		</div>

		<div id="intro-main-container" class="container">
			<div class="row caja">
				<div class="col-sm-2 col-xs-12">
					<img src="img/alfalfa-packs.jpg" alt="Packs Alfalfa" class="img-responsive img-circle img-thumbnail">
				</div>
				<div class="col-sm-8 col-xs-12">
					<h2 class="text-success">Productos <i class="fa fa-bolt"></i></h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium quo quibusdam dolore animi cumque. A voluptates voluptatem, ratione beatae aliquid numquam magni doloremque veniam tempora modi nihil tenetur cupiditate distinctio.</p>
				</div>
			</div>

			<div class="row caja">
				<div class="col-sm-2 col-xs-12">
					<img src="img/tractor.jpg" alt="Tractor en el campo" class="img-responsive img-circle img-thumbnail">
				</div>
				<div class="col-sm-8 col-xs-12">
					<h2 class="text-primary">Servicios <i class="fa fa-spin fa-gear"></i></h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium quo quibusdam dolore animi cumque. A voluptates voluptatem, ratione beatae aliquid numquam magni doloremque veniam tempora modi nihil tenetur cupiditate distinctio.</p>
				</div>
			</div>

			<div class="row text-center">
				<a href="catalogo.html" class="btn btn-lg btn-primary" id="btn-catalogo">Ver Catálogo <span class="glyphicon glyphicon-shopping-cart"></span></a>
			</div>

			<div class="row caja">
				<div class="col-sm-2 col-xs-12">
					<img src="img/alfalfa.jpg" alt="Tractor en el campo" class="img-responsive img-circle img-thumbnail">
				</div>

				<div class="col-sm-8 col-xs-12">
					<h2 class="text-warning">Trato al Cliente Personalizado <i class="fa fa-briefcase"></i></h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium quo quibusdam dolore animi cumque. A voluptates voluptatem, ratione beatae aliquid numquam magni doloremque veniam tempora modi nihil tenetur cupiditate distinctio.</p>
				</div>

				<div class="col-sm-2 col-xs-12">
					<h4>Formas de Contacto</h4>

					<ul class="fa-ul col-xs-12">
						<li><i class="fa fa-li fa-envelope"></i> nombre@empresa.es</li>
						<li><i class="fa fa-li fa-phone"></i> 555-123-456</li>
						<li><i class="fa fa-li fa-whatsapp"></i> 612345678</li>
						<li><i class="fa fa-li fa-skype"></i> nombre.empresa</li>
					</ul>
				</div>
			</div>

			<h2>Testimonios</h2>
			<div id="testimonios-react"></div>
			<%-- <div class="row intro-textos">
				<section class="col-sm-4 col-xs-12 portada-caja destacado">
					<div class="img-testimonio text-center">
						<img src="img/autor.png" alt="Foto Jesús González" class="img-circle" width="128" height="128">
					</div>
					<h2>Lorem Ipsum <i class="fa fa-fighter-jet"></i></h2>
					<p></p>
				</section>

				<section class="col-sm-4 col-xs-12 portada-caja destacado">
					<div class="img-testimonio text-center">
						<img src="img/amigo-freebsd.png" alt="Foto Jesús González" class="img-circle" width="128" height="128">
					</div>
					<h2>Lorem Ipsum <i class="fa fa-rocket"></i></h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias repellendus quod, iusto illo hic accusamus repellat corporis dolor aperiam culpa officiis tempora deserunt, fugit ab!</p>
				</section>

				<section class="col-sm-4 col-xs-12 portada-caja destacado">
					<div class="img-testimonio text-center">
						<img src="img/amigo.png" alt="Foto Jesús González" class="img-circle" width="128" height="128">
					</div>
					<h2>Lorem Ipsum <i class="fa fa-university"></i></h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis praesentium explicabo officia doloremque rem quis expedita blanditiis, pariatur ex omnis, laboriosam mollitia eos maiores beatae nostrum reprehenderit. Necessitatibus expedita, aut.</p>
				</section>
			</div> --%>
		</div>

		<div class="spacer"></div>

		<!-- jQuery -->
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.isVisible.js" type="text/javascript" charset="utf-8"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Angular -->
 		<script src="js/angular.min.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Scripts propios -->
		<script src="js/scripts.js" type="text/javascript" charset="utf-8"></script>
 		<!-- Scripts portada -->
		<script src="js/scripts-portada.js" type="text/javascript" charset="utf-8"></script>
		<!-- ReactJS -->
		<script src="js/react.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/react-dom.min.js" type="text/javascript" charset="utf-8"></script>
 		<script src="js/react-testimonios.min.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
