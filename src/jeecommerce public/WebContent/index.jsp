<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	// Si se entra a index, sólo redireccionamos al servlet de inicio y ya está
	// Usaremos extensiones HTML para los servlets, para así "camuflar" la tecnología por parte del servidor
	// Nota: No obstante, se deben combinar otros métodos para camuflar efectivamente la tecnología que se está usando.
	response.sendRedirect("index.html");

%>