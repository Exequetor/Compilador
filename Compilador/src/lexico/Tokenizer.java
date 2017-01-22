package lexico;

import java.util.Vector;

public class Tokenizer extends Automata{
	private Vector <String> programa;
	private Vector <String> vectorSimbolos;
	private Tabla automata;
	private Vector <String> fullAlf = new Vector <String> ();
	private TiraTokens tira, tablaErrores, tablaSimbolos;
	
	public Tokenizer (Vector <String> prog, Vector <String> vectorReservadas, Vector <String> vectorSimbolos) {
		this.vectorSimbolos = vectorSimbolos;
		
		String expresion;
		GeneradorExpresion generador = new GeneradorExpresion (vectorReservadas);
		expresion = generador.getExpresion();
		
		Thompson thompson = new Thompson (expresion, true);
		Subconjuntos cerradura;
		thompson.start();
		cerradura = new Subconjuntos (thompson.getTabla());
		
		automata = cerradura.start();
		for (int i = 48 ; i <= 57 ; i++)
			fullAlf.add(Character.toString((char)i));
		for (int i = 65 ; i <= 90 ; i++)
			fullAlf.add(Character.toString((char)i));
		for (int i = 97 ; i <= 122 ; i++)
			fullAlf.add(Character.toString((char)i));
		fullAlf.add(Character.toString('.'));
		//Inicialización de tabla de tokens
		tira = new TiraTokens ("tokens");
		//Inicialización de tabla de simbolos
		tablaSimbolos = new TiraTokens ("simbolos");
		//Inicialización de la tabla de errores
		tablaErrores = new TiraTokens ("errores");
		programa = prog;
		//System.out.println(alfabeto);
	}
	
	public TiraTokens getTiraTokens () {
		String lexema = new String ();
		for (int i = 0 ; i < programa.size() ; i++) {
			
			for (int j = 0 ; j < programa.get(i).length() ; j++) {
				boolean flag = false;
				String renglon = programa.get(i);
				if (existeEnElAlfabeto(Character.toString(renglon.charAt(j)))) {
					lexema = lexema.concat(Character.toString(renglon.charAt(j)));
				} else {
					if (lexema.length() != 0) {
						Token token = new Token ();
						token.setLinea(i+1);
						token.setLexema(lexema);
						token.setToken(startAutomataLexico (lexema, automata));
						tira.insertar(token);
						lexema = new String ();
						flag = true;
					}
				}
				if (esSimbolo (Character.toString(renglon.charAt(j)))) {
					Token token = new Token ();
					token.setLinea(i+1);
					token.setLexema(Character.toString(renglon.charAt(j)));
					token.setToken(Character.toString(renglon.charAt(j)));
					tira.insertar(token);
					lexema = new String ();
					flag = true;
				}
				if (renglon.charAt(j) == '"') {
					Token token = new Token ();
					token.setLinea(i+1);
					lexema = lexema.concat(Character.toString('"'));
					j++;
					for (; j < renglon.length() && renglon.charAt(j) != '"'; j++) {
						lexema = lexema.concat(Character.toString(renglon.charAt(j)));
					}
								lexema = lexema.concat(Character.toString('"'));
					token.setLexema(lexema);
					token.setToken("literal");
					tira.insertar(token);
					lexema = new String ();
					flag = true;
				}
				if (renglon.charAt(j) == '\'') {
					Token token = new Token ();
					token.setLinea(i+1);
					lexema = lexema.concat(Character.toString('\''));
					j++;
					for (; j < renglon.length() && renglon.charAt(j) != '\''; j++) {
						lexema = lexema.concat(Character.toString(renglon.charAt(j)));
					}
								lexema = lexema.concat(Character.toString('\''));
					token.setLexema(lexema);
					token.setToken("literalc");
					tira.insertar(token);
					lexema = new String ();
					flag = true;
				}
				if (lexema.length() != 0 && j+1 == renglon.length() && !flag) {
					Token token = new Token ();
					token.setLinea(i+1);
					token.setLexema(lexema);
					token.setToken(startAutomataLexico (lexema, automata));
					tira.insertar(token);
					lexema = new String ();
					flag = true;
				}
				if (!existeEnElAlfabeto(Character.toString(renglon.charAt(j))) && renglon.charAt(j) != '\t' && !flag && renglon.charAt(j) != ' ' && renglon.charAt(j) != '\n' && renglon.charAt(j) != '\r' ) {
					Token errorToken = new Token ();
					errorToken.setLinea(i+1);
					errorToken.setToken("Símbolo indefinido: " + Character.toString(renglon.charAt(j)));
					errorToken.setLexema("");
					tablaErrores.insertar(errorToken);
					lexema = new String ();
					flag = true;
				}
				if (flag)
					lexema = new String ();
			}
		}
		for (int i = 0 ; i < tira.size() ; i++) {
			if (tira.getVector().get(i).getToken().equals("id")) {
				boolean bandera = true;
				Token tempToken = new Token ();
				for (int j = 0 ; j < tablaSimbolos.size() && bandera; j++) {
					if (tira.getVector().get(i).getLexema().equals(tablaSimbolos.getVector().get(j).getToken()))
						bandera = false;
				}
				if (bandera) {
					tempToken.setId(tablaSimbolos.size());
					tempToken.setLinea(tira.getVector().get(i).getLinea());
					tempToken.setToken(tira.getVector().get(i).getLexema());
					tablaSimbolos.insertar(tempToken);
				}
			}
					
		}
		return tira;	
	}
	public TiraTokens getTira () {
		return tira;
	}
	public boolean esSimbolo (String simbolo) {
		boolean flag = false;
		for (int i = 0 ; i < vectorSimbolos.size() && !flag; i++) {
			if (vectorSimbolos.get(i).equals(simbolo))
				flag = true;
		}
		
		return flag;
	}
	
	public TiraTokens getTablaSimbolos () {
		return tablaSimbolos;
	}
	
	public TiraTokens getTablaErrores () {
		return tablaErrores;
	}
	
	public boolean existeEnElAlfabeto (String caracter) {
		boolean flag = false;
		for (int i = 0 ; i < fullAlf.size() && !flag ; i++) {
			if (fullAlf.get(i).equals(caracter))
				flag = true;
		}
		
		return flag;
	}
	
}
