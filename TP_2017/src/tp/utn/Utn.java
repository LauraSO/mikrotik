package tp.utn;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

public class Utn
{
	// Retorna: el SQL correspondiente a la clase dtoClass acotado por xql
	public static <T> String _query(Class<T> dtoClass, String xql)
	{
			
			Query queryObjet = new Query();
			
			queryObjet.tabla= FuncionesAnnotation.obtenerNombreDeTabla(dtoClass);
			
			FuncionesAnnotation.obtenerCampos(dtoClass, queryObjet);
			
			String sql = "";
			sql +="SELECT ";
			
			for (final String campo : queryObjet.campos){
				
			sql += campo + ", ";
			
			}
			
			
			sql +="FROM "+ queryObjet.tabla+ " " +queryObjet.tabla.charAt(0)+" ";
				
			for (final String join : queryObjet.joins){
				
				sql += "\n";
				sql += join + " ";
				
				}
			
			sql += "\n";
			
			sql +="WHERE "+ xql;
			
			return sql;
		
	}
	
	// Invoca a: _query para obtener el SQL que se debe ejecutar
	// Retorna: una lista de objetos de tipo T
	public static <T> List<T> query(Connection con, Class<T> dtoClass, String xql, Object... args)
	{
		return null;
	}
	
	// Retorna: una fila identificada por id o null si no existe
	// Invoca a: query
	private static <T> T find(Connection con, Class<T> dtoClass, Object id)
	{
		return null;
	}
	
	// Retorna: una todasa las filas de la tabla representada por dtoClass
	// Invoca a: query
	private static <T> List<T> findAll(Connection con, Class<T> dtoClass)
	{
		return null;
	}

	// Retorna: el SQL correspondiente a la clase dtoClass acotado por xql
	public static <T> String _update(Class<T> dtoClass, String xql)
	{
		return null;
	}
	
	// Invoca a: _update para obtener el SQL que se debe ejecutar
	// Retorna: la cantidad de filas afectadas luego de ejecutar el SQL
	public static int update(Connection con, Class<?> dtoClass, String xql, Object... args)
	{
		return 0;
	}

	// Invoca a: update 
	// Que hace?: actualiza todos los campos de la fila identificada por el id de dto
	// Retorna: Cuantas filas resultaron modificadas (deberia: ser 1 o 0) 
	public static int update(Connection con, Object dto)
	{
		return 0;
	}
	
	// Retorna: el SQL correspondiente a la clase dtoClass acotado por xql
	public static String _delete(Class<?> dtoClass, String xql)
	{
		return null;
	}
	
	// Invoca a: _delete para obtener el SQL que se debe ejecutar
	// Retorna: la cantidad de filas afectadas luego de ejecutar el SQL
	public static int delete(Connection con, Class<?> dtoClass, String xql, Object... args)
	{
		return 0;
	}

	// Retorna la cantidad de filas afectadas al eliminar la fila identificada por id
    // (deberia ser: 1 o 0)
	// Invoca a: delete
	public static int delete(Connection con, Class<?> dtoClass, Object id)
	{
		return 0;
	}

	// Retorna: el SQL correspondiente a la clase dtoClass
	public static String _insert(Class<?> dtoClass)
	{
		return null;
	}
	
	// Invoca a: _insert para obtener el SQL que se debe ejecutar
	// Retorna: la cantidad de filas afectadas luego de ejecutar el SQL
	public static int insert(Connection con, Object dto)
	{
		return 0;
	}
}
