package interfaz;


import canonico.ColeccionCanonica;

public class VentanaCanonica {
	private VentanaGeneral ventana;
	private ColeccionCanonica coleccionCanonica;
	
	public VentanaCanonica (String expresion) {
		ventana = new VentanaGeneral("Colecci\u00f3n Can\u00f3nica");
		
		coleccionCanonica = new ColeccionCanonica(expresion);
		
		ventana.txp.setText(coleccionCanonica.toString());
	}
}
