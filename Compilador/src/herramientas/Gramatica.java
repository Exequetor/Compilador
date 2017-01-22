package herramientas;

import java.util.ArrayList;
import primeros.Primeros;



//Nota: Esta clase depende de la lectura de la gram�tica de primeros. Es un c�digo no �ptimo.
public class Gramatica {
	
	public static PSTabla leeGramatica (String expresion, int offset) {
		PSTabla gramatica = new PSTabla ("Gramatica");
		Primeros prim = new Primeros (expresion, false);
		if (offset > 0)
			for (int i = 0 ; i < offset ; i++) {
				PSTupla tupla = new PSTupla ();
				gramatica.add(tupla);
			}
		for (int i = 0 ; i < prim.getGramatica().size() ; i++) {
			PSTupla tupla = new PSTupla ();
			for (int j = 0 ; j < prim.getGramatica().get(i).size() ; j++) {
				if (j > 0)
					tupla.add(prim.getGramatica().get(i).get(j).letra);
				else
					tupla.noTerminal = prim.getGramatica().get(i).get(j).letra;
			}
			gramatica.add(tupla);
		}
		//System.out.println(gramatica);
		return gramatica;
	}
	
	public static PSTabla leeGramaticaNoEps (String expresion, int offset) {
		PSTabla gramatica = new PSTabla ("Gramatica");
		Primeros prim = new Primeros (expresion, false);
		if (offset > 0)
			for (int i = 0 ; i < offset ; i++) {
				PSTupla tupla = new PSTupla ();
				gramatica.add(tupla);
			}
		for (int i = 0 ; i < prim.getGramatica().size() ; i++) {
			PSTupla tupla = new PSTupla ();
			for (int j = 0 ; j < prim.getGramatica().get(i).size() ; j++) {
				if (j > 0) {
					//System.out.println(prim.getGramatica().get(i).get(j).letra);
					if (!prim.getGramatica().get(i).get(j).letra.equals("Eps"))
						tupla.add(prim.getGramatica().get(i).get(j).letra);
				} else
					tupla.noTerminal = prim.getGramatica().get(i).get(j).letra;
			}
			gramatica.add(tupla);
		}
		//System.out.println(gramatica);
		return gramatica;
	}
	
	public static PSTabla leeGramatica (String expresion) {
		return leeGramatica (expresion, 0);
	}
	
	public static ArrayList <String> retornaNoTerminales (PSTabla g) {
		ArrayList <String> vec = new ArrayList <String> ();
		for (int i = 0 ; i < g.size() ; i++) {
                    if (!vec.contains(g.get(i).noTerminal))
			vec.add(g.get(i).noTerminal);
		}
		return vec;
	}
        
        public static ArrayList <String> retornaTerminales (PSTabla g) {
            ArrayList <String> terminales = new ArrayList <String> ();
            ArrayList <String> noTerminales = retornaNoTerminales(g);
            for (int i = 0 ; i < g.size() ; i++)
                for (int j = 0 ; j < g.get(i).getConjunto().size() ; j++) {
                    String caracter = g.get(i).getConjunto().get(j);
                    if (!noTerminales.contains(caracter)) {
                        if (!terminales.contains(caracter)) {
                            terminales.add(caracter);
                        }
                    }
                }
            return terminales;
        }
	
	public static boolean existeNoTerminal (ArrayList <String> noTerminales, String str) {
		boolean flag = false;
		if (noTerminales.contains(str))
			flag = true;
		return flag;
	}
	
}