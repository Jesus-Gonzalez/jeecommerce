<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

<div class="container-fluid">

  <%@ include file="/WEB-INF/sidebar.jsp" %>

  <section class="col-sm-9 col-md-10">
    <h1>Vista General del Sitio</h1>

    <p>Bienvenido al área de administración.</p>

    <p>Puede realizar las siguientes acciones:</p>

    <ul class="fa-ul">
      <li><i class="fa-li fa-fw fa-gears"></i><a href="configuracion">Configurar y ajustar el sitio web.</a></li>
      <li><i class="fa-li fa-fw fa-shopping-cart"></i><a href="productos">Añadir y gestionar productos del sitio.</a></li>
      <li><i class="fa-li fa-fw fa-money"></i><a href="bancos">Añadir y gestionar los bancos disponibles para transferencia bancaria.</a></li>
      <li><i class="fa-li fa-fw fa-phone"></i><a href="contacto">Añadir y gestionar formas de contacto disponibles.</a></li>
      <li><i class="fa-li fa-fw fa-map-marker"></i><a href="pedidos">Gestionar pedidos.</a></li>
      <li><i class="fa-li fa-fw fa-user"></i><a href="usuarios">Gestionar usuarios.</a></li>
      <li><i class="fa-li fa-fw fa-users"></i><a href="administradores">Gestionar administradores del sitio.</a></li>
    </ul>

  </section>

</div>

<%@ include file="/WEB-INF/footer.jsp" %>
