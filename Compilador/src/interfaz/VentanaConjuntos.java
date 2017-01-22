package interfaz;

import lexico.Subconjuntos;
import lexico.Thompson;

public class VentanaConjuntos {
	private VentanaGeneral ventana;
	
	public VentanaConjuntos(String automata){
		//crea la ventana
		ventana = new VentanaGeneral("Algoritmo de Subconjuntos");
		
		//Aqui va la llamada al algoritmo
		Thompson thompson = new Thompson (automata, true);
		Subconjuntos cerradura;
		thompson.start();
		
		cerradura = new Subconjuntos (thompson.getTabla());
		
		//Aqui se llama al componente que muestra la salida
		ventana.txp.setText(cerradura.start().toString());
		
		
	}
}
