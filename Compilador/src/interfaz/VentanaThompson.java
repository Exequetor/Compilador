package interfaz;

import lexico.Thompson;

public class VentanaThompson{
	private VentanaGeneral ventana;
	public String tabla;
	
	public VentanaThompson(String expresion){	
		//Crea la ventana
		ventana = new VentanaGeneral("Algoritmo de Thompson");
		
		//Llama al algoritmo de thompson y guarda la cadena
		Thompson thompson=new Thompson(expresion, true);
    	
		//Muestra la cadena
		ventana.txp.setText(thompson.start());
		
		tabla = thompson.start();
	}
	
	public void visible(Boolean op){
		ventana.esVisible(op);
	}
}
