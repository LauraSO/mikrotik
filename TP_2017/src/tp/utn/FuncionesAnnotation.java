package tp.utn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import tp.utn.ann.Column;
import tp.utn.ann.Table;

public class FuncionesAnnotation {
	
	public static <T> String obtenerNombreDeTabla(Class<T> dtoClass) {
	
	
	final Annotation anotacionObtenida = dtoClass.getAnnotation(Table.class);
	final Table anotacionTable = (Table) anotacionObtenida;
	String nombreTabla = anotacionTable.name();

	return nombreTabla;
	}
	
	
	public static <T> String  obtenerCampos(Class<T> dtoClass) { 
		
	
	
	final Field[] variables = dtoClass.getDeclaredFields();
	
	String atributosAux="";
	String atributos="";
	
	for (final Field variable : variables) {

		final Annotation anotacionObtenida = variable.getAnnotation(Column.class);

		if (anotacionObtenida != null && anotacionObtenida instanceof Column) {
			final Column anotacionColumn = (Column) anotacionObtenida;

			String nombreAtributo = anotacionColumn.name();
			
			
			atributosAux += nombreAtributo + ", ";
			
			atributos = atributosAux.substring(0, atributosAux.length()-2); 
				
		}
		
		
		}
	
	return atributos;
}

	
}