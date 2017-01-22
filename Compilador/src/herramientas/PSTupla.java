package herramientas;

import java.util.ArrayList;

/*
 * La clase PSTupla representa una regla de prodoccuón de una gramática. Contiene dos atributos: noTerminal y conjunto.
 * noTerminal: Representa el símbolo no terminal que está a la izquierda de la flecha de una regla de producción. Este símbolo es un string.
 * conjunto: Representa el conjunto que se representa al lado derecho de la flecha de una regla de producción. Este conjunto es un arreglo
 * 			 de Strings.
 * @author: Exe
 * @version: Primeros y Sigientes.
 */

public class PSTupla {
	public String noTerminal;
	private ArrayList <String> conjunto;
	
	/*
	 * Constructor vacío.
	 */
	public PSTupla () {
		noTerminal = new String ();
		conjunto = new ArrayList <String> ();
	}
	
	/*
	 * Constructor recibiendo un tipo tupla.
	 */
	public PSTupla (PSTupla tupla) {
		noTerminal = tupla.getNoTerminal();
		conjunto = tupla.getConjunto();
	}
	
	/*
	 * Constructor con no terminal y vector del conjunto vacío.
	 */
	public PSTupla (String noTerm) {
		noTerminal = noTerm;
		conjunto = new ArrayList <String> ();
	}
	/*
	 * Constructor con noTerminal y un array de strings.
	 */
	public PSTupla (String noTerminal, ArrayList <String> conjunto) {
		this.noTerminal = noTerminal;
		this.conjunto = conjunto;
	}
	/*
	 * Añade un nuevo elemento al conjunto de la regla de producción.
	 */
	public void add (String element) {
		this.conjunto.add(element);
	}
    /*
     * Remueve un elemento específico del conjunto (remove). 
     * @author: Liz Velia    
     * @version: TAS
     */
    public void pop (int index) {
		this.conjunto.remove(index);
	}
	/*
	 * Sustituye el conjunto actual, por el conjunto que se pasa en el parámetro.
	 */
	public void setConjunto (ArrayList <String> array) {
		conjunto = new ArrayList <String> (array);
	}
	/*
	 * Retorna la cantidad de elementos que existen en el conjunto.
	 */
	public int size () {
		return conjunto.size();
	}
	/*
	 * Retorna el noTerminal de la regla de producción para la tupla.
	 */
	public String getNoTerminal () {
		return noTerminal;
	}
	/*
	 * Retorna todo el conjunto en un arreglo de strings.
	 */
	public ArrayList <String> getConjunto () {
		return new ArrayList <String> (conjunto);
	}
	public String strConjuntoNonComa () {
		String str = new String ();
		str = "[";
		for (int i = 0 ; i < conjunto.size() ; i++)
			str += " " + conjunto.get(i);
		str += " ]";
		return str;
	}
	/*
	 * Retorna el elemento que existe en el conjunto de la posición indicada por el index.
	 */
	public String get (int index) {
		return new String (conjunto.get(index));
	}
	/*
	 * Sustituye un elemento en el conjunto en la posición indicada por el index.
	 */
	public void set (int index, String str) {
		conjunto.set(index, str);
	}
	/*
	 * Compara la tupla referenciada con la tupla del argumento. Si las tuplas son iguales la función retorna true, en caso contrario se retorna false.
	 */
	public boolean equals (PSTupla tupla) {
		boolean flag = false;
		if (noTerminal == tupla.getNoTerminal()) {
			//System.out.println("NoTermina: True");
			if (size() == tupla.size()) {
				//System.out.println("Tamano: true");
				flag = true;
				for (int i = 0 ; i < size() && flag; i++) {
					if (!conjunto.get(i).equals(tupla.getConjunto().get(i)))
						flag = false;
				}
				//System.out.println("flag final: " + flag);
			}
		}
               
		return flag;
	}
	/*
	 * Compara dos tuplas sin importarle si el terminal es igual o no, por lo que solo se comparan los conjuntos.
	 */
    public boolean equals (PSTupla tupla, boolean conTerminal) {
        boolean flag = false;
        if (conTerminal)
                flag = equals (tupla);
            else
                if (size() == tupla.size()) {
                	//System.out.println("Tamano: true");
                	flag = true;
                	for (int i = 0 ; i < size() && flag; i++) {
                		if (!conjunto.get(i).equals(tupla.getConjunto().get(i)))
                			flag = false;
                	}
				//System.out.println("flag final: " + flag);
                }
		return flag;
    }
	
	public String toString () {
		String string = new String ();
		string = string.concat("(" + noTerminal + ") = { ");
		for (int i = 0 ; i < conjunto.size() ; i++) {
			string = string.concat(conjunto.get(i) + " ");
		}
		string = string.concat(" }");
		return string;
	}
}
