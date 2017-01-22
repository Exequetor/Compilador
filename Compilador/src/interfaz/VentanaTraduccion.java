package interfaz;

import java.util.Vector;

import herramientas.GuardarArchivo;
import semantico.Traductor;
import sintactico.AbrirPrograma;

public class VentanaTraduccion {
	private Vector <String> vSimbolos;
	private Vector <String> vReservadas;
	public VentanaTraduccion (AbrirPrograma codigoFuente, String simbolos, String reservadas, String gramatica, String archiveName) {	
		vSimbolos = creaVector(simbolos);
		vReservadas = creaVector(reservadas);
	
		Traductor traduccion = new Traductor (codigoFuente.getPrograma(), vReservadas, vSimbolos, gramatica);
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
