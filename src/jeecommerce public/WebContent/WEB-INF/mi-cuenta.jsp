<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelos.Usuario" %>
<%@ page import="modelos.Direccion" %>

<%@ include file="/WEB-INF/header.jsp" %>

<%
  Usuario usuario = (Usuario) request.getAttribute("usuario.object");
  List<Direccion> lstDirecciones = (List<Direccion>) request.getAttribute("lista.direcciones");
%>

<section class="container" id="wrapper-cuenta" data-ng-controller="cuentaController" data-ng-init="nombre = '<%= usuario.nombre %>'; correo = '<%= usuario.correo %>'">
  <h1 class="text-info">Gestión de Mi Cuenta</h1>

  <div class="row">
    <div class="col-lg-5">
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-lock"></i> Datos de Seguridad</h3>
        </div>
        <div class="panel-body">
          <p><strong><i class="fa fa-fw fa-calendar"></i> Fecha de última conexión: </strong> <span><%= usuario.fechaConexion %></span></p>
          <p><strong><i class="fa fa-fw fa-code"></i> Dirección IP de la última conexión: </strong> <span><%= usuario.ip %></span></p>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-lg-7">

      <div class="alert alert-warning" data-ng-show="cuentaNecesitaVerificacion">
        <h2><i class="fa fa-lock"></i> Cuenta Pendiente de Activación</h2>
        <p>
          Ya que ha cambiado su correo electrónico, ahora debe <strong>activar su cuenta</strong>.
          Por favor acceda a su correo electrónico y acceda al enlace.
          Gracias.
        </p>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-user"></i> Mis Datos Personales</h3>
        </div>
        <div class="panel-body">
          <form name="frmPerfil">
            <input class="input-formulario form-control" type="text" id="nombre" placeholder="Nombre" ng-value="nombre" data-ng-model="nombre" readonly>
            <input class="input-formulario form-control" type="email" id="email" placeholder="Email" ng-value="correo" data-ng-pattern="/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i" data-ng-model="email">
            <p>
              Introduzca la contraseña actual únicamente si va a cambiar su contraseña.
              <p class="text-info">Mínimo 6 caracteres</p>
            </p>
            <p class="text-warning">
              Nota: Si cambia su contraseña, recibirá un correo de confirmación a el correo electrónico que haya establecido.
            </p>
            <input class="input-formulario form-control" type="password" id="contrasenaActual" placeholder="Contraseña actual" data-ng-pattern="/^.{6,}$/" data-ng-model="contrasenaActual">
            <input class="input-formulario form-control" type="password" id="contrasenaNueva" placeholder="Nueva contraseña" data-ng-pattern="/^.{6,}$/" data-ng-model="contrasenaNueva">
            <input class="input-formulario form-control" type="password" id="contrasenaNuevaConfirmar" placeholder="Confirmar nueva contraseña" data-ng-pattern="contrasenaNueva" data-ng-model="contrasenaNuevaConfirmar">

            <button type="button" class="btn btn-md btn-primary" data-ng-click="modificarPerfil()">Modificar Perfil <i class="fa fa-save"></i></button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <h2><i class="fa fa-map-marker"></i> Gestionar Direcciones</h2>

  <p>
    Haga click sobre una dirección que desee modificar.
  </p>
  <p>
    <strong>IMPORTANTE: Tenga en cuenta que cuando modifique una dirección, sus pedidos realizados apuntarán a esta nueva direccion.</strong>
  </p>
  <div class='container-fluid'>
    <% if (lstDirecciones.size() == 0) { %>
      <div class="alert alert-info">
        <h2>No hay direcciones registradas</h2>
        <p>
          No tiene direcciones registradas en esta cuenta.
        </p>
      </div>
    <% } else { %>
      <% for (Direccion direccion : lstDirecciones) { %>
          <div class="direccion" data-ng-click="modificarDireccion(<%= direccion.did %>)">
            <p><strong><i class="fa fa-fw fa-user"></i> Nombre:</strong> <%= direccion.nombre %></p>
            <p><strong><i class="fa fa-fw fa-phone"></i> Teléfono:</strong> <%= direccion.telefono %></p>
            <div>
              <strong><i class="fa fa-fw fa-map-marker"></i> Ubicación:</strong>
              <p><%= direccion.direccion %></p>
              <p><%= direccion.localidad %> (<%= direccion.codigoPostal %>)</p>
            </div>
        </div>
      <% } %>
    <% } %>
  </div>
</section>

<%@ include file="/WEB-INF/footer.jsp" %>
