package lexico;

import java.util.Vector;


/*
 * La clase abstracta autómata contiene métodos para recorrer autómatas los cuales reciben un lexema y la tabla de transiciones.
 * Métodos:
 * startAutomataLexico: Recorre la tabla de transiciones en busca de coincidencias
 */

public abstract class Automata {
	public String startAutomataLexico (String lexema, Tabla tablaTransiciones) {
		String tokenizer = new String ();
		int token = 0;
		Vector <Trans> vectorTransTemp;
		for (int i = 0 ; i < lexema.length() ; i++) {
			vectorTransTemp = tablaTransiciones.getVector().get(token).getVector();
			for (int j = 0 ; j < vectorTransTemp.size() ; j++) {
				if (vectorTransTemp.get(j).getStep().equals(Character.toString(lexema.charAt(i)))) {
					token = vectorTransTemp.get(j).getNext();
					tokenizer = tokenizer.concat(vectorTransTemp.get(j).getStep());
				}
			}
		}
		if (tokenizer.length() == 0)
			tokenizer = lexema;
		if (tablaTransiciones.getVector().get(token).esFin() && !esNumero(tokenizer)) {
			;//El token es una plabra reservada, y se regresa el lexema como token
		} else {
			if (!esNumero (tokenizer))
				tokenizer = "id";
			else {
				int contPuntos = 0;
				for (int i = 0 ; i < tokenizer.length() ; i++) {
					if (tokenizer.charAt(i) == '.')
						contPuntos++;
				}
				if (contPuntos == 0)
					tokenizer = "nint";
				else if (contPuntos == 1)
					tokenizer = "nfloat";
				else
					tokenizer = "BadNum";
			}
		}
			
		return tokenizer;
	}
	

	private boolean esNumero (String tokenizer) {
		boolean flag = true;
		for (int i = 0 ; i < tokenizer.length() && flag; i++) {
			if (tokenizer.charAt(i) == '0' ||
				tokenizer.charAt(i) == '1' ||
				tokenizer.charAt(i) == '2' || 
				tokenizer.charAt(i) == '3' ||
				tokenizer.charAt(i) == '4' ||
				tokenizer.charAt(i) == '5' ||
				tokenizer.charAt(i) == '6' ||
				tokenizer.charAt(i) == '7' ||
				tokenizer.charAt(i) == '8' ||
				tokenizer.charAt(i) == '9' ||
				tokenizer.charAt(i) == '.'   );
			else
				flag = false;
		}
		return flag;
	}
}
