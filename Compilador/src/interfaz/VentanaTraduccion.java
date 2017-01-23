package interfaz;

import java.util.Vector;

import herramientas.GuardarArchivo;
import semantico.TraductorPascal;
import sintactico.AbrirPrograma;

public class VentanaTraduccion {
	public static final String PASCAL = "pascal";
	public static final String PYTHON = "python";
	private Vector <String> vSimbolos;
	private Vector <String> vReservadas;
	private AbrirPrograma codigoFuente;
	private String gramatica;
	private String archiveName;
	public VentanaTraduccion (AbrirPrograma codigoFuente, String simbolos, String reservadas, String gramatica, String archiveName, String language) {	
		vSimbolos = creaVector(simbolos);
		vReservadas = creaVector(reservadas);
		this.codigoFuente = codigoFuente;
		this.gramatica = gramatica;
		this.archiveName = archiveName;
		switch (language) {
		case PASCAL:
			transPascal();
			break;
		case PYTHON:
			transPython();
			break;
		}
	}
	
	private void transPascal () {
		TraductorPascal traduccion = new TraductorPascal (codigoFuente.getPrograma(), vReservadas, vSimbolos, gramatica);
		traduccion.showTable();
		if (traduccion.isSuccesfulTrad()) {
			GuardarArchivo archivoTraduccion = new GuardarArchivo ("Traducci\u00F3n_" + archiveName + ".txt", traduccion.getTraduccion());
			archivoTraduccion.guarda();
			System.out.println(traduccion.getTraduccion());
		}
	}
	private void transPython () {
		TraductorPascal traduccion = new TraductorPascal (codigoFuente.getPrograma(), vReservadas, vSimbolos, gramatica);
		traduccion.showTable();
		if (traduccion.isSuccesfulTrad()) {
			GuardarArchivo archivoTraduccion = new GuardarArchivo ("Traducci\u00F3n_" + archiveName + ".txt", traduccion.getTraduccion());
			archivoTraduccion.guarda();
			System.out.println(traduccion.getTraduccion());
		}
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
