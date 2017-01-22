package interfaz;


import sintactico.TAS;

public class VentanaTAS {
	private VentanaGeneral ventana;
	private TAS tas;
	
	public VentanaTAS (String expresion) {
		ventana = new VentanaGeneral("Tabla de An\u00e1lisis Sint\u00e1ctico");
		
		tas = new TAS(expresion);
		
		ventana.txp.setText(tas.toString());
	}
}
