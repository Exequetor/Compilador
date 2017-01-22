package lexico;

import java.util.Vector;

/*
 * Esta clase genera la expresion regular a partir del vector de strings que contiene las palabras reservadas
 */
public class GeneradorExpresion {
	private Vector <String> vectorReservadas;
	private String expresion;
	
	public GeneradorExpresion (Vector <String> vectorR) {
		vectorReservadas = vectorR;		
		this.expresion = generaExpresion();
	}
	
	public String generaExpresion () {
		String expresion = new String ();
		for (int i = 0 ; i < vectorReservadas.size() ; i++) {
			expresion = expresion.concat(vectorReservadas.get(i) + "|");
		}
		expresion = expresion.concat("(0|1|2|3|4|5|6|7|8|9)+|(0|1|2|3|4|5|6|7|8|9)*.(0|1|2|3|4|5|6|7|8|9)+");
		
		return expresion;
	}
	
	public Vector <String> getVectorReservadas () {
		return vectorReservadas;
	}
	
	public String getExpresion () {
		return expresion;
	}
}
