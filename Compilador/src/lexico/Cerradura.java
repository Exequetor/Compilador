package lexico;

import java.util.*;


public class Cerradura {

	private Vector <Integer> vectorInteger = new Vector <Integer> ();
	private Stack <Integer> stackID = new Stack <Integer> ();
	
	private int temp;
	
	private Tabla tabla;
	
	public Cerradura (Vector <Integer> vector, Tabla tabla) {
		vectorInteger = vector;
		for (int i = 0 ; i < vector.size() ; i++)
			stackID.push(vector.get(i));
		this.tabla = tabla;
	}
	
	public Vector <Integer> start () {
		while (!stackID.isEmpty()) {
			temp = stackID.pop();
			Estado estado;
			estado = tabla.getVector().get(temp);
			if (estado.tieneEpsilon()) {
				Vector <Integer> vectorIntTemp;
				vectorIntTemp = estado.retornaTransEpsilon();
				for (int i = 0 ; i < vectorIntTemp.size() ; i++) {
					if (!existe(vectorInteger, vectorIntTemp.get(i))) {
						stackID.push(vectorIntTemp.get(i));
						vectorInteger.addElement(vectorIntTemp.get(i));
					}
				}
			}
				
		}
		Collections.sort(vectorInteger);
		return vectorInteger;
		
	}	
	
	public boolean existe (Vector <Integer> vector, int entero) {
		boolean flag = false;
		
			for (int i = 0 ; i < vector.size() && flag == false; i++)
				if (vector.get(i) == entero)
					flag = true;
			
		return flag;
	}
	
	public String toString() {
		return vectorInteger.toString();
	}
}
