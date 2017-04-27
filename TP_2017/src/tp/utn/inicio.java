package tp.utn;


import tp.utn.demo.domain.Direccion;
import tp.utn.demo.domain.Persona;
import tp.utn.Utn;

public class inicio {
	
	public static void main(String[] args) {

	
		
		//String xql ="id_persona=1";
		
		//String query = Utn._query(Persona.class, xql);
		
		//System.out.println (query);
		
		
		
		String xql ="id_persona=1";
				
		String query = Utn._query(Direccion.class, xql);
		
		System.out.println (query);
		
		
		
	
}
}	
	

