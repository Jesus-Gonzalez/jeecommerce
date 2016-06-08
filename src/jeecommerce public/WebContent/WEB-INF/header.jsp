<%@page import="java.math.BigDecimal"%>
<%@page import="modelos.Carro"%>
<%@page import="modelos.Articulo"%>
<%@page import="modelos.SesionUsuario"%>
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
    <!-- Font Awesome -->
		<link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- AlertifyJS -->
    <link rel="stylesheet" href="css/alertify/alertify.min.css" />
    <link rel="stylesheet" href="css/alertify/themes/default.min.css" />
    <!-- Main custom CSS -->
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

					<%
						int countProductosCarro = 0;
						BigDecimal total = BigDecimal.ZERO;

						SesionUsuario sesion = (SesionUsuario) session.getAttribute("usuario");

						if (sesion.carro != null)
						{
							for (Articulo articulo : sesion.carro.articulos.values())
							{
								countProductosCarro += articulo.cantidad;
								total = total.add(articulo.precio.multiply(BigDecimal.valueOf(articulo.cantidad)));
							}
						}

					%>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-shopping-cart"></i> Carro <b class="caret"></b></a>
							<ul class="dropdown-menu header-dropdown">
								<li><p class="navbar-text"><strong id="lbl-carro-count"><%= countProductosCarro %></strong> producto en el carro</p></li>
								<li><p class="navbar-text">Total: <strong><span id="lbl-carro-total"><%= total.toString() %></span> €</strong></p></li>
								<li><a href="carro.html" class="btn btn-xs btn-primary">Ver carro</a></li>
							</ul>
						</li>

					<%

						boolean estaLogueado = sesion.usuario != null && sesion.estado == SesionUsuario.LOGUEADO;

						String nombre = estaLogueado ? sesion.usuario.nombre : "Anónimo";

					%>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> <%= nombre %> <%= estaLogueado ? "<b class=\"caret\">" : "" %></b></a>
							<ul class="dropdown-menu header-dropdown">
							<% if (estaLogueado) { %>
								<li><a href="mi-cuenta.html">Mi Cuenta</a></li>
								<li><a href="gestionar-pedidos.html">Gestionar Pedidos</a></li>
								<li><a href="salir.html" class="btn btn-xs btn-danger">Salir</a></li>
							<% } else { %>
								<li><a href="login" class="btn btn-xs btn-success">Registrarse</a></li>
								<li><a href="login" class="btn btn-xs btn-info">Identificarse</a></li>
							<% } %>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
