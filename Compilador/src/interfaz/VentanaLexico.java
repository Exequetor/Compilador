package interfaz;

import java.util.*;

import herramientas.GuardarArchivo;
import lexico.AbrirCodigo;
import lexico.Tokenizer;

public class VentanaLexico {
	private VentanaGeneral ventana;
	private Vector <String> vSimbolos;
	private Vector <String> vReservadas;
	
	public VentanaLexico(AbrirCodigo codigoFuente, String simbolos, String reservadas){
		//crea la ventana
		ventana = new VentanaGeneral("Analizador L\u00e9xico");
		
		vSimbolos = creaVector(simbolos);
		vReservadas = creaVector(reservadas);
		//System.out.println(codigoFuente.toString());
		//Aqui va la llamada al algoritmo
		Tokenizer analizadorLexico = new Tokenizer (codigoFuente.getPrograma(), vReservadas, vSimbolos);
		//Aqui llama al componente que muestra la salida
		ventana.txp.setText(analizadorLexico.getTiraTokens().toString());
		//System.out.println(analizadorLexico.getTablaErrores());
		//System.out.println(analizadorLexico.getTablaSimbolos());
		
        GuardarArchivo archivoSimbolos = new GuardarArchivo ("Tabla_Simbolos.txt", analizadorLexico.getTablaSimbolos().toString());
        GuardarArchivo archivoErrores = new GuardarArchivo ("Tabla_Errores.txt", analizadorLexico.getTablaErrores().toString());
        archivoSimbolos.guarda();
		archivoErrores.guarda();
	}
	
	public Vector <String> creaVector(String cadena){
		Vector <String> vector = new Vector <String>();
		String temp = "";
		
		for(int i=0; i<cadena.length(); i++){
			if(cadena.charAt(i) == '\n'){
				vector.addElement(temp);
				temp = "";
			}else{
				temp = temp + cadena.charAt(i);
			}
		}
		return vector;
	}
}
