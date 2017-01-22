package sintactico;
import java.util.*;

public class Primeros {
	private Vector<String> noTerminales = new Vector<String>();
	private Vector<ArrayList<Nodo>> gramatica = new Vector<ArrayList<Nodo>>();
	private ArrayList<Nodo> primeros = new ArrayList<Nodo>();
	public Conjuntos conjuntos;
	public int repetido = 0;

	
	public Primeros(String g, boolean ejecutarAlgoritmo) {
		//System.out.println(g);
		//crea el vector de no terminales
		creaVector(g);
		//crea la matriz con la gramatica
		creaGramatica(g);
		//System.out.println(gramatica);
		if (ejecutarAlgoritmo)
		for(int i=0; i<noTerminales.size() ; i++){
			algoritmoPrimeros(noTerminales.get(i), noTerminales.get(i));
		}
		//imprimeVector(primeros);
		
		@SuppressWarnings("unused")
		Conjuntos conjuntos = new Conjuntos(primeros, noTerminales);
	}
	
	public void creaGramatica(String cadena){
		String temp = "";
		Nodo tmp;
		ArrayList<Nodo> temporal = new ArrayList<Nodo>(); //ArrayList de nodos (filas de la matriz)
		boolean flag;
		//System.out.printf("\n");
		for(int i=0; i<cadena.length(); i++) {
			while(cadena.charAt(i) != '\n' && cadena.charAt(i) != '\r') {
				flag = true;
				while (cadena.charAt(i) != ' ' && cadena.charAt(i) != '\n' && cadena.charAt(i) != '\r') {
					temp = temp + cadena.charAt(i);
					flag = false;
					i++;
				}
				if (cadena.charAt(i) == '\n' || cadena.charAt(i) == '\r')
					flag = true;
				if (flag) {
					//System.out.println("Temp = " + temp);
					tmp = new Nodo(temp, noTerminales); // Va creando los nodos
					temporal.add(tmp); //Va creando la regla gramatical (fila)
					temp = new String();
					if (cadena.charAt(i) != '\n' && cadena.charAt(i) != '\r') 
						i++;
				}
			}
			gramatica.addElement(temporal); //Mete la fila en la matriz
			temporal = new ArrayList<Nodo>(); //Reinicia la fila para meter una nueva regla gramatical
			temp = new String(); 			 
		}
		//imprimeVector(temporal);
		//System.out.println(gramatica);
		//imprimeGramatica(gramatica);
	}

	public void algoritmoPrimeros(String p, String deQuien){
		Nodo primero;
		boolean next;
		
		for(int i=0; i<gramatica.size(); i++){ //Recorre los renglones de la gramatica
			next = true;
			if(p.equals(gramatica.get(i).get(0).letra)){ //Comprueba que la regla coincida con los primeros que buscamos
				for(int j=1; j<gramatica.get(i).size() && next==true; j++){ //Recorre la regla de produccion
					String letra = gramatica.get(i).get(j).letra;
					String tipo = gramatica.get(i).get(j).tipo;
					if(p.equals(letra)){ //Si la regla de produccion se llama a si misma
						next = false;
					}else{
						if(tipo.equals("Terminal")){ //Si es un terminal
							primero = new Nodo(letra, deQuien);
							boolean flag = true;
							for (int k = 0 ; k < primeros.size() && flag ; k++) {
								if (primeros.get(k).tipo.equals(deQuien) && primeros.get(k).letra.equals(primero.letra))
									flag = false;
							}
							if (flag)
								primeros.add(primero);
							next = false;
						}else if(tipo.equals("noTerminal")){ //Si es un no terminal entra a la recursividad
							algoritmoPrimeros(letra, deQuien);
							next = false;
						}else if(tipo.equals("Epsilon")){ //Si hay epsilon lo mete y sigue con el programa
							primero = new Nodo(letra, deQuien);
							boolean flag = true;
							for (int k = 0 ; k < primeros.size() && flag ; k++) {
								if (primeros.get(k).tipo.equals(deQuien) && primeros.get(k).letra.equals(primero.letra))
									flag = false;
							}
							if (flag)
								primeros.add(primero);
							next = true;
						}
					}
				}
			}
		}
	}

	public void creaVector(String cadena){
		String temp = "";
		Boolean bandera = true;
		Boolean bandera2 = true;
		
		//recorre la cadena
		for(int i=0; i<cadena.length(); i++){
			if(cadena.charAt(i)==' ' && bandera==true){//si va despues de un salto de linea y antes de un espacio
				for(int j=0; j<noTerminales.size(); j++){
					if(temp.equals(noTerminales.get(j))){ //Verifica que no este repetido
						bandera2 = false;
					}
				}
				if(bandera2 == true)
					noTerminales.addElement(temp); 
				bandera = false;
				bandera2 = true;
			}else if(cadena.charAt(i) == ' '){
				temp = "";
			}else if(cadena.charAt(i) == '\n'){
				temp = "";
				bandera = true;
			}else{
				temp = temp + cadena.charAt(i);
			}
		}
		//System.out.println(noTerminales);
	}
	
	public Vector <String> getNoTerminales () {
		return noTerminales;
	}
	
	public ArrayList <Nodo> getPrimeros() {
		return primeros;
	}
	
	public Vector<ArrayList<Nodo>> getGramatica () {
		return gramatica;
	}
	
	public void imprimeGramatica(Vector<ArrayList<Nodo>> gramatica){
		for(int i=0; i<gramatica.size(); i++){
			
			for(int j=0; j<gramatica.get(i).size(); j++){
				System.out.println(gramatica.get(i).get(j).letra);
				System.out.println(gramatica.get(i).get(j).tipo);
				System.out.printf("\n");
			}
			System.out.printf("\n");
			System.out.println("Fin regla");
		}
		
	}
	
	public void imprimeVector(ArrayList<Nodo> g){
		for(int i= 0; i<g.size(); i++){
			System.out.println(g.get(i).letra);
			System.out.println(g.get(i).tipo);
			System.out.printf("\n");
		}
	}
	public String toString () {
		String string = new String ();
		string = string.concat("Tabla del algoritmo de primeros\r\n");
		for (int i = 0 ; i < noTerminales.size() ; i++) {
			string = string.concat("P(" + noTerminales.get(i) + ") = { ");
			for (int j = 0 ; j < primeros.size() ; j++){
				if (primeros.get(j).tipo.equals(noTerminales.get(i))) {
					string = string.concat(primeros.get(j).letra + " ");
				}
			}
			string = string.concat("}\r\n");
		}
		string = string.concat("\n");
		
		return string;
	}
}