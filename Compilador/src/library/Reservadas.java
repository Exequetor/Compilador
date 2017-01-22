package library;

public abstract class Reservadas {
	//----------------Constantes-------------------------
	public static final String EPSILON = "Eps";
	public static final String LETRAS = "¡";
	public static final String DIGITOS = "#";
	
	//----------------Palabras reservadas---------------- @Deprecated
	public static final String[] ARRAY_RESERVADAS = {
			"if",
			"for",
			"while",
			"int",
			"char",
			"float",
			"double",
			"long",
			"do",
			"switch",
			"case",
			"break",
			"include",
			"else",
			"void",
			"null",
			"struct",
			"default",
			"return",
			"bool",		
	};
	
	//----------------Métodos de Herramienta----------------
	// Antes que nada, si se quieren utilizar los métodos de esta clase abstracta, hay que heredar esta clase abstracta en la clase
	// en la que se desean usar los métodos. Para hacer esto se pone el extend Reservadas, sin olvidar que se debe importar el paquete library
	// en la clase anfitriona.
	
	/*
	 * Este método regresa true si el string que se le ingresó es una palabra reservada.
	 */
	public static boolean existeReservada (String tokenizer) {
		boolean flag = false;
		for (int i = 0 ; i < ARRAY_RESERVADAS.length && !flag; i++) {
			if (tokenizer.equals(ARRAY_RESERVADAS[i]))
				flag = true;
		}
		return flag;
	}
}
