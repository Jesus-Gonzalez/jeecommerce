<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.Banco" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>

<%
	List<Banco> lstBancos = (List<Banco>) request.getAttribute("lista.bancos");
  Connection conexion = (Connection) request.getSession().getAttribute("conexion");
%>

<%@ include file="/WEB-INF/header.jsp" %>

<div class="container-fluid" data-ng-controller="bancosController">

  <%@ include file="/WEB-INF/sidebar.jsp" %>

    <div id="form-banco">
      <form>
        <div id="form-group-id-banco" class="form-group">
					<label for="id-banco">ID de Banco</label>
          <input type="text" class="form-control" id="id-banco" placeholder="eg. ID de Banco" readonly data-ng-model="bid">
          <p class="help-block">ID del banco a modificar.</p>
        </div>

        <div class="form-group">
          <label for="nombre-banco">Nombre Banco</label>
          <input type="text" class="form-control" id="nombre-banco" placeholder="eg. BBVA" data-ng-model="nombre">
          <p class="help-block">Nombre del banco a mostrar.</p>
        </div>

				<div class="form-group">
          <label for="numero-banco">Numero del banco</label>
          <input type="text" class="form-control" id="numero-banco" placeholder="eg. 1020 3040 5060 7080 9000" data-ng-model="numero">
          <p class="help-block">Número del banco a mostrar.</p>
        </div>

				<div class="form-group">
          <label for="activo-banco">Activo</label>
          <input type="checkbox" value="true" id="activo-banco" data-ng-model="activo">
          <p class="help-block">Indica si el banco es visible.</p>
        </div>
      </form>
    </div>

    <h1>Gestión de Bancos</h1>
    <h2>Acciones <i class="fa fa-play"></i></h2>
    <button class="btn btn-success" data-ng-click="showCrearBanco()"><i class="fa fa-plus"></i> Crear Banco</button>

    <h2>Bancos</h2>

		<div id="sin-bancos" class="alert alert-warning" <%= (lstBancos.size() > 0) ? "style='display:none'" : "" %>>
			<h3>No hay bancos <small>Empieza a añadir</small></h3>
			<p>No hay bancos añadidos. Empieza ahora a añadir bancos al sitio.</p>
		</div>

		<table id="tabla-bancos" class="table-striped" <%= (lstBancos.size() == 0) ? "style='display:none'" : "" %>>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Número</th>
				<th>Activo</th>
        <th>Editar</th>
        <th>Borrar</th>
			</tr>
		</thead>

		<tbody>
			<% for (Banco banco : lstBancos) { %>
				<tr data-bid="<%= banco.bid %>">
					<td class="bid"><%= banco.bid %></td>
					<td class="nombre"><%= banco.nombre %></td>
					<td class="numero"><%= banco.numero %></td>
					<td class="activo"><%= banco.activo ? "Activo" : "No Activo" %></td>
          <td class="btn-editar"><button class="btn btn-primary" type="button" data-ng-click="showEditarBanco(<%= banco.bid %>)">Editar <i class="fa fa-edit"></i></button></td>
          <td class="btn-borrar"><button class="btn btn-danger" type="button" data-ng-click="confirmBorrarBanco(<%= banco.bid %>)">Borrar <i class="fa fa-times"></i></button></td>
				</tr>
			<% } %>
			<tr data-bid="{{ banco.bid }}" data-ng-repeat="banco in bancos">
				<td class="bid">{{ banco.bid }}</td>
				<td class="nombre">{{ banco.nombre }}</td>
				<td class="numero">{{ banco.numero }}</td>
				<td class="activo">{{ banco.activo ? "Activo" : "No Activo" }}</td>
				<td class="btn-editar"><button class="btn btn-primary" type="button" data-ng-click="showEditarBanco(banco.bid)">Editar <i class="fa fa-edit"></i></button></td>
				<td class="btn-borrar"><button class="btn btn-danger" type="button" data-ng-click="confirmBorrarBanco(banco.bid)">Borrar <i class="fa fa-times"></i></button></td>
			</tr>
		</tbody>


		</table>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>
