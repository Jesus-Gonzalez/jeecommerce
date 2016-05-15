<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

		<section id="autenticacion" class="container" role="main">
			<div class="row">
				<div class="col-sm-6 col-xs-12">
					<div class="panel panel-success" data-ng-controller="registroController">
						<div class="panel-heading">
							<h3 class="panel-title"><i class="fa fa-user-plus"></i> Registro</h3>
						</div>
						<div class="panel-body">
							<form id="registro">
								<div class="col-xs-10 col-xs-offset-1 text-center">
									<p class="text-primary text-center">¿No está registrado?</p>

									<div class="input-group campo">
										<input id="nombre" type="text" class="form-control" placeholder="Nombre Completo">
										<div class="input-group-addon"><i class="fa fa-user"></i></div>
									</div>

									<div class="input-group campo">
										<input id="email" type="email" class="form-control" placeholder="nombre@gmail.com">
										<div class="input-group-addon"><i class="fa fa-at"></i></div>
									</div>

									<div class="input-group campo">
										<input id="contrasena" type="password" class="form-control" placeholder="Contraseña">
										<div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
									</div>

									<div class="input-group campo">
										<input id="recontrasena" type="password" class="form-control" placeholder="Repita Contraseña">
										<div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
									</div>
									<button id="btn-registrar" type="submit" name="registrar" value="Registrarse" class="btn btn-lg btn-success campo" role="button">Registrarse <i class="fa fa-user-plus"></i></button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="col-sm-6 col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><i class="fa fa-users"></i> Identificarse</h3>
						</div>
						<div class="panel-body">
							<form id="registro">
								<div class="col-xs-10 col-xs-offset-1 text-center">
									<p class="text-success text-center">¿Ya está registrado?</p>

									<div class="input-group campo">
										<input type="email" class="form-control" placeholder="nombre@gmail.com">
										<div class="input-group-addon"><i class="fa fa-at"></i></div>
									</div>

									<div class="input-group campo">
										<input type="password" class="form-control" placeholder="Contraseña">
										<div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
									</div>

									<button id="btn-identificarse" name="entrar" class="btn btn-lg btn-primary campo" role="button">Identificarse <span class="glyphicon glyphicon-send"></span></button>

									<span class="input-group-btn">
										<button type="button" class="btn btn-sm btn-warning campo"><span class="glyphicon glyphicon-lock"></span> ¡Olvidé mi contraseña!</button>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>

<%@ include file="/WEB-INF/footer.jsp" %>
