package interfaz;

import java.util.Vector;

import sintactico.AbrirPrograma;
import sintactico.AnalizadorSintactico;

public class VentanaAnalisisSintactico {
	private Vector <String> vSimbolos;
	private Vector <String> vReservadas;
	
	public VentanaAnalisisSintactico (AbrirPrograma codigoFuente, String simbolos, String reservadas, String gramatica){
		//ventana = new VentanaGeneral("Analizador Sint\u00e1ctico");
		
		vSimbolos = creaVector(simbolos);
		vReservadas = creaVector(reservadas);
	
		AnalizadorSintactico analizador = new AnalizadorSintactico (codigoFuente.getPrograma(), vReservadas, vSimbolos, gramatica);
		//Aqui llama al componente que muestra la salida
		/*
		 * Notita secreta: Si quieres ver la ventana de salida que siempre mostramos, 
		 * quita el comentario de la instrucción "ventana = new VentanaGeneral("Analizador Sint\u00e1ctico");" y "ventana.txp.setText(analizador.toString());"
		 * Si quieres desactivar la nueva tabla, comenta la instrucción "analizador.showTable()"
		 */
		//ventana.txp.setText(analizador.toString());
		analizador.showTable();
		
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
