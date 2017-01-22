package debug;

import conjuntos.Subconjuntos;
import thompson.*;


public class DebugTablaSubconjuntos {
	private String expresion;
	
	public DebugTablaSubconjuntos (String exp) {
		expresion = exp;
	}
	
	public Tabla getTabla () {
		Thompson thompson = new Thompson (expresion, true);
		Subconjuntos cerradura;
		thompson.start();
		
		cerradura = new Subconjuntos (thompson.getTabla());
		return cerradura.start();
	}
}
