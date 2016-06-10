<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelos.Articulo" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="helpers.CategoriasHelper" %>

<%
	List<Articulo> lstProductos = (List<Articulo>) request.getAttribute("lista.articulos");
  	Connection conexion = (Connection) request.getSession().getAttribute("conexion");
%>

<%@ include file="/WEB-INF/header.jsp" %>

<div class="container-fluid" data-ng-controller="productosController">

  <%@ include file="/WEB-INF/sidebar.jsp" %>

    <div id="form-producto">
      <form>
        <div class="form-group">
          <label for="nombre-producto">Nombre producto</label>
          <input type="text" class="form-control" id="nombre-producto" placeholder="eg. Sal para cultivo">
          <p class="help-block">Nombre del producto.</p>
        </div>
      </form>
    </div>

    <h1>Gestión de Productos</h1>
    <h2>Acciones <i class="fa fa-play"></i></h2>
    <button class="btn btn-success" data-ng-click="showCrearProducto()"><i class="fa fa-plus"></i> Crear Producto</button>

    <h2>Productos</h2>
    <div class="filtro">
      <form data-ng-submit="buscarProducto">
        <span>Buscar producto: <input type="search" data-ng-model="campoBusqueda"></span>
        <button type="submit">Buscar <i class="fa fa-search-plus"></i></button>
      </form>
    </div>

	<% if (lstProductos.size() == 0) { %>

	<% } else { %>

		<table id="tabla-productos" class="table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Editar</th>
        <th>Borrar</th>
			</tr>
		</thead>

		<tbody>
			<% for (Articulo articulo : lstProductos) { %>
				<tr>
					<td><%= articulo.artid %></td>
					<td><%= articulo.nombre %></td>
					<td><%= articulo.precio %></td>
          <td><button class="btn btn-primary" type="button" data-ng-click="editarProducto(<%= articulo.artid %>)">Editar <i class="fa fa-edit"></i></button></td>
          <td><button class="btn btn-danger" type="button" data-ng-click="borrarProducto(<%= articulo.artid %>)">Borrar <i class="fa fa-times"></i></button></td>
				</tr>
			<% } %>
		</tbody>


		</table>
	    <%
	      /* Pagination */
	      int pagina = (int) request.getAttribute("pagina");
	      int count = (int) request.getAttribute("count");
	      int paginas = (int) Math.ceil(new Double(count) / 20);

	      String criterio = request.getParameter("criterio");

	      String url = request.getContextPath() + "/productos";
	      if (criterio != null)
	      {
	        url += "?criterio=" + criterio + "&pagina=";
	      } else {
	        url += "?pagina=";
	      }
	    %>

	    <ul class="pagination">
	      <% for (int i=1; i < paginas; i++) { %>
	      <li<%= i == pagina ? " class='active'" : "" %>><a href="<%= url + i %>"><%= i %></a></li>
	      <% } %>
	    </ul>
    <% } %>
</div>

<script charset="utf-8">
<%
	String jsonCategorias = CategoriasHelper.getCategoriasRecursive(conexion, 1).toString();
%>
  var categorias = <%= jsonCategorias %>;
</script>
<%@ include file="/WEB-INF/footer.jsp" %>
