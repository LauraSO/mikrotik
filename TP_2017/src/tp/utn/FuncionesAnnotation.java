package tp.utn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import tp.utn.ann.Column;
import tp.utn.ann.Table;

public class FuncionesAnnotation {
	
	public static <T> String obtenerNombreDeTabla(Class<T> dtoClass) {
	
	String nombreTabla="";
	final Annotation anotacionObtenida = dtoClass.getAnnotation(Table.class);
	if (anotacionObtenida != null && anotacionObtenida instanceof Table) {
	final Table anotacionTable = (Table) anotacionObtenida;
	 nombreTabla = anotacionTable.name();
	}
	
	return nombreTabla;
	}
	
	
	public static void obtenerCampos(Class<?> dtoClass, Query queryObjet ) { 
		
	
	String nombreDeTabla1=obtenerNombreDeTabla(dtoClass);

	final Field[] variables = dtoClass.getDeclaredFields();
	
	String atributosAux="";
	String nombreDeTabla="";
	
	for (final Field variable : variables) {

		final Annotation anotacionObtenida = variable.getAnnotation(Column.class);
		
		nombreDeTabla=obtenerNombreDeTabla(variable.getType()); 

		if (anotacionObtenida != null && anotacionObtenida instanceof Column) {
				
		final Column anotacionColumn = (Column) anotacionObtenida;
			String nombreAtributo = anotacionColumn.name();
								
			atributosAux = nombreDeTabla1+"."+nombreAtributo;
			
			queryObjet.campos.add(atributosAux);
			
			if(nombreDeTabla!="") {
				
				atributosAux ="INNER JOIN "+ nombreDeTabla + " " + nombreDeTabla ; 
				atributosAux +=" ON "+ nombreDeTabla+"."+nombreAtributo + "=" + nombreDeTabla +"."+ nombreAtributo;
				queryObjet.joins.add(atributosAux);
				
				obtenerCampos(variable.getType(), queryObjet);
				
				
				}
			
			}
		
		}	
	
	
	
}

	
}