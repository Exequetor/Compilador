package sintactico;
import java.util.*;

public class Nodo {
	public String letra;
	public String tipo = "";
	
	public Nodo(String cadena, Vector<String> noTerminales){
		letra = cadena;
		
		for(int i=0; i < noTerminales.size(); i++){
			if(letra.equals(noTerminales.get(i))){
				tipo = "noTerminal";
			}
		}
		if(letra.equals("Eps")){
			tipo = "Epsilon";
		}else if(tipo.equals("")){
			tipo = "Terminal";
		}
	}
	
	public Nodo(String elemento, String conjunto){
		letra = elemento;
		tipo = conjunto;
	}
	
	public Nodo (Nodo nod) {
		letra = nod.letra;
		tipo = nod.tipo;
	}
}
