package helpers;

import java.sql.Connection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import modelos.MCategorias;

public class CategoriasHelper
{
	public static JsonArray getCategoriasRecursive(Connection conexion, long catid)
	{
		MCategorias mdlCategorias = new MCategorias(conexion);
		
		mdlCategorias.getCategoriasBySuperCat(catid);
		
		JsonArray jsonCategorias = new JsonArray(); // Helper categorias
		JsonObject jsonCategoria;
		JsonArray jsonSubcategorias;
		
		while (mdlCategorias.getProximaCategoria())
		{
			jsonCategoria = new JsonObject();
			jsonCategoria.addProperty("catid", mdlCategorias.catid);
			
			jsonSubcategorias = CategoriasHelper.getCategoriasRecursive(conexion, mdlCategorias.catid);
			jsonCategoria.add("subcategorias", jsonSubcategorias);
			
			jsonCategorias.add(jsonCategoria);
		}
		
		return jsonCategorias;
	}
}
