<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es" data-ng-app="jeecommerce.private">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Área de Administración de Tienda Electrónica</title>

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
		<link rel="stylesheet" href="css/main.min.css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

    <meta name="robots" content="noindex;nofollow">
	</head>

	<body>

    <div id="login-container" class="container">
      <form data-ng-controller="loginController" data-ng-submit="identificarse()">
        <div class="form-group">
          <label for="usuario">Nombre de Administrador</label>
          <input type="text" class="form-control" id="usuario" placeholder="Nombre de Administrador" data-ng-model="nombre">
          <p class="help-block">Nombre de usuario del administrador.</p>
        </div>

        <div class="form-group">
          <label for="contrasena">Contraseña</label>
          <input type="password" class="form-control" id="contrasena" placeholder="Contraseña" data-ng-model="contrasena">
          <p class="help-block">Contraseña del administrador.</p>
        </div>

        <button type="submit" class="btn btn-primary">Identificarse <i class="fa fa-sign-in"></i></button>
      </form>
    </div>

		<script charset="utf-8">

			// ES6
			// const ABS_PATH = '<%= request.getContextPath() %>';
			var ABS_PATH = '<%= request.getContextPath() %>';

		</script>

		<!-- jQuery -->
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- Angular -->
		<script src="js/angular.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- AlertifyJS -->
		<script src="js/alertify.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- Custom Angular Scripts -->
		<script defer src="js/angular/app.js" charset="utf-8"></script>
		<script defer src="js/angular/login.js" charset="utf-8"></script>

  </body>

</html>
