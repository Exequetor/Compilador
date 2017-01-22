package interfaz;

import sintactico.Siguientes;

public class VentanaSiguientes {
	private VentanaGeneral ventana;
	private Siguientes siguientes;
	
	public VentanaSiguientes(String expresion){
		ventana = new VentanaGeneral("Primeros y siguientes");
		
		siguientes = new Siguientes (expresion);
		
		ventana.txp.setText(siguientes.toString());
	}
}