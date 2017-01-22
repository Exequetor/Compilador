package primeros;
import java.util.*;

public class Conjuntos {
	private Vector<String> conjunto = new Vector<String>();
	private String de;
	
	public Conjuntos(ArrayList<Nodo> primeros, Vector<String> noTerminales){
		Conjunto conjuntos = new Conjunto();
		Vector<String> conjunto1 = new Vector<String>();
		String g = new String();
		Conjuntos temp = new Conjuntos();
		boolean bandera = true;
		
		for(int i=0; i<noTerminales.size(); i++){
			for(int j=0; j<primeros.size(); j++){
				if(noTerminales.get(i).equals(primeros.get(j).tipo)){
					if(!conjunto1.contains(primeros.get(j).letra)){
						conjunto1.add(primeros.get(j).letra);
						g = noTerminales.get(i);
						bandera = true;
					}else
						bandera = false;
				}
			}
			if(bandera == true){
				temp = new Conjuntos(conjunto1, g);
				conjuntos.add(temp);
				conjunto1 = new Vector<String>();
				g = new String();
				temp = new Conjuntos();
			}
		}
	}
	
	public Conjuntos(Vector<String> conjunto1, String g){
		conjunto = conjunto1;
		de = g;
	}
	
	public Conjuntos(){
		conjunto = new Vector<String>();
		de = new String();
	}
	
	public void imprimeConjuntos(Conjunto conjuntos){
		for(int i=0; i<conjuntos.size(); i++){
			System.out.println(conjuntos.get(i).conjunto);
			System.out.println(conjuntos.get(i).de);
			System.out.printf("\n");
		}
	}
}
