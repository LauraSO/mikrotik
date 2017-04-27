package tp.utn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import tp.utn.ann.Column;
import tp.utn.ann.Relation;
import tp.utn.ann.Table;
import tp.utn.ann.Id;

public class FuncionesAnnotation {
	
	public static <T> String obtenerNombreDeTabla(Class<T> dtoClass) {
	
	String nombreTabla="";
	final Table anotacionObtenida = dtoClass.getAnnotation(Table.class);
	if (anotacionObtenida != null) {
	 nombreTabla = anotacionObtenida.name();
	}
	
	return nombreTabla;
	}
	
	public static String obtenerID(Class<?> dtoClass){
		
		String nombreAtributo = null;
		final Field[] variables = dtoClass.getDeclaredFields();
			
		for (final Field variable : variables) {

			final Id anotacionObtenida = variable.getAnnotation(Id.class);
			
			if (anotacionObtenida != null) {
				final Column anotacionObtenida2 = variable.getAnnotation(Column.class);
				nombreAtributo = anotacionObtenida2.name();
				
			}
			
		}
		return nombreAtributo;
	}
	
	public static String obtenerFk(Class<?> dtoClass, Class<?> dtoClass2 ){
		
		String nombreTabla = null;
		String fkId = null;
		
		final Field[] variables = dtoClass.getDeclaredFields();
			
		for (final Field variable : variables) {

			final Column anotacionObtenida = variable.getAnnotation(Column.class);
			
			if(anotacionObtenida  != null) {
				
			nombreTabla=obtenerNombreDeTabla(variable.getType()); 
			
			
			if (nombreTabla != null && dtoClass2 == variable.getType() ) {
				fkId = anotacionObtenida.name();
				
			}
			
		}
		}
			
		return fkId;
	}
	

	
	
	public static void obtenerCampos(Class<?> dtoClass, Query queryObjet ) { 
		
	
	String nombreDeTabla1=obtenerNombreDeTabla(dtoClass);

	final Field[] variables = dtoClass.getDeclaredFields();
	
	String atributosAux="";
	String nombreDeTabla="";
	
	for (final Field variable : variables) {

		final Column anotacionObtenida = variable.getAnnotation(Column.class);
		
		nombreDeTabla=obtenerNombreDeTabla(variable.getType()); 

		if (anotacionObtenida != null) {
				
			String nombreAtributo = anotacionObtenida.name();
								
			atributosAux = nombreDeTabla1+"."+nombreAtributo;
			
			queryObjet.campos.add(atributosAux);
			
			if(nombreDeTabla!="") {
				
				String nombreAtributo1 = obtenerFk(dtoClass, variable.getType()); 
				String nombreAtributo2 = obtenerID(variable.getType());
				atributosAux ="INNER JOIN "+ nombreDeTabla + " " + nombreDeTabla ; 
				atributosAux +=" ON "+ nombreDeTabla1+"."+nombreAtributo1 + "=" + nombreDeTabla +"."+ nombreAtributo2;
				queryObjet.joins.add(atributosAux);
				
				obtenerCampos(variable.getType(), queryObjet);
			
				}
			
			}
		
		
		}	
	
	
	
}

	
}


/*final Relation anotacionObtenida2 = variable.getAnnotation(Relation.class);
if (anotacionObtenida2 != null) {
	String nombreAtributo1 = obtenerID(dtoClass); 
	String nombreAtributo2 = obtenerFk(anotacionObtenida2.type(), dtoClass);
	nombreDeTabla=obtenerNombreDeTabla(anotacionObtenida2.type());
	
	atributosAux ="INNER JOIN "+ nombreDeTabla + " " + nombreDeTabla ; 
	atributosAux +=" ON "+ nombreDeTabla1+"."+nombreAtributo1 + "=" + nombreDeTabla +"."+ nombreAtributo2;
	queryObjet.joins.add(atributosAux);

*/