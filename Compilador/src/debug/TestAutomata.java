package debug;

import java.util.Vector;

import lexico.*;

public class TestAutomata extends Automata{
	public TestAutomata () {
		Vector <String> vectorReservadas = new Vector <String> (), vectorSimbolos = new Vector <String> ();
	
		vectorReservadas.add("if");
		vectorReservadas.add("while");
		vectorReservadas.add("int");
		vectorReservadas.add("char");
		vectorReservadas.add("float");
		vectorReservadas.add("double");
		vectorReservadas.add("long");
		vectorReservadas.add("do");
		vectorReservadas.add("switch");
		vectorReservadas.add("case");
		vectorReservadas.add("break");
		vectorReservadas.add("include");
		vectorReservadas.add("else");
		
		vectorSimbolos.add("(");
		vectorSimbolos.add(")");
		vectorSimbolos.add("=");
		vectorSimbolos.add("!");
		vectorSimbolos.add("\"");
		vectorSimbolos.add("#");
		vectorSimbolos.add("+");
		vectorSimbolos.add("-");
		vectorSimbolos.add("{");
		vectorSimbolos.add("}");
		vectorSimbolos.add("*");
		vectorSimbolos.add("/");
		vectorSimbolos.add("\\");
				
		GeneradorExpresion generador = new GeneradorExpresion (vectorReservadas);
		String expresion = generador.getExpresion();
		System.out.println(generador.getExpresion());
		DebugTablaSubconjuntos initDebug = new DebugTablaSubconjuntos (expresion);
		Tabla tablaTransiciones = initDebug.getTabla();
		
				//Aqui se ingresa el lexema que se quiere
		String lexema = new String ("float"); //Ingresar el lexemaz
		//Aqui se simula la salida del lexema
		System.out.println("Token\tLexema");
		System.out.print(startAutomataLexico(lexema, tablaTransiciones) + "\t" + lexema);
	}
}
