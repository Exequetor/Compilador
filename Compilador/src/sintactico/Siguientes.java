package sintactico;

import java.util.ArrayList;

import herramientas.PSTabla;
import herramientas.PSTupla;
import herramientas.Gramatica;

public class Siguientes {
	PSTabla gramatica;
	PSTabla primeros;
	PSTabla siguientes;
	String expresion;
	
	public Siguientes (String expresion) {
		this.gramatica = new PSTabla ("Gramatica");
		this.primeros = new PSTabla ("Primeros");
		this.siguientes = new PSTabla ("Siguientes");
		this.expresion = expresion;
		//Algoritmo de primeros y conversión de datos
		Primeros primeros = new Primeros (expresion, true);
		//System.out.println(primeros);
		normalizarPrimeros (primeros);
		//Lectura de gramática
		gramatica = Gramatica.leeGramatica (expresion);
		//Algoritmo de siguientes
		algoritmoDeSiguientes();
		//System.out.println(gramatica);
		//System.out.println(siguientesDe("E"));
	}
	
	private void algoritmoDeSiguientes () {
		for (int i = 0 ; i < gramatica.size() ; i++) {
			if (!siguientes.existeNoTerminal(gramatica.getVector().get(i).noTerminal)) {
				PSTupla tupla = new PSTupla (gramatica.getVector().get(i).noTerminal);
				siguientes.add(tupla);
			}
		}
		siguientes.addElementToTupla("$", 0);
		//Primera pasada para agregar los primeros.
		for (int margen = 0 ; margen < 1000 ; margen++) {
			ArrayList <String> conjunto;
			for (int i = 0 ; i < siguientes.size() ; i++) {
				String noTerminal = siguientes.getVector().get(i).noTerminal;
				conjunto = siguientes.getVector().get(i).getConjunto();
				for (int j = 0 ; j < gramatica.size() ; j ++) {
					if (gramatica.getVector().get(j).getConjunto().contains(noTerminal)) {
						ArrayList <String> betas = retornaBetas (gramatica.getVector().get(j), noTerminal);
						boolean seEncontroCaso = false;
						//System.out.println(noTerminal + " " + betas);
						if (betas.size() == 0) {
							conjunto = unionVectoresString (conjunto, siguientesDe (gramatica.getVector().get(j).noTerminal));
							seEncontroCaso = true;
							//System.out.println(gramatica.vector.get(j).noTerminal + " Sig " + siguientesDe(gramatica.vector.get(j).noTerminal));
						}
						ArrayList <String> vecPrim = new ArrayList<String> ();
						boolean flag = true;
						vecPrim.add("Eps");
						//System.out.println(betas);
						for (int k = 0 ; k < betas.size() && flag; k++) {
							vecPrim = unionVectoresStringEps (vecPrim, getPrimerosConEps(betas.get(k)));
							//System.out.println(vecPrim);
							flag = false;
							if (vecPrim.contains("Eps")) {
									flag = true;
							}
						}
						if (!seEncontroCaso)
						if (vecPrim.contains("Eps")) {
							conjunto = unionVectoresString (conjunto, vecPrim);
							conjunto.remove("Eps");
							conjunto = unionVectoresString (conjunto, siguientesDe (gramatica.getVector().get(j).noTerminal));
						} else {
							conjunto = unionVectoresString (conjunto, vecPrim);
						}
					}
				}
				siguientes.setTupla(noTerminal, conjunto, i);
			}
		}
		//Segunda pasada para el caso de primeros con épsilon
		//System.out.println(siguientes);
	}
	
	
	private void normalizarPrimeros (Primeros prim) {
		String noTerminal;
		for (int i = 0 ; i < prim.getNoTerminales().size() ; i++) {
			noTerminal = new String ();
			noTerminal = prim.getNoTerminales().get(i);
			PSTupla tupla = new PSTupla (noTerminal);
			for (int j = 0 ; j < prim.getPrimeros().size() ; j++) {
				if (prim.getPrimeros().get(j).tipo.equals(noTerminal)) {
					tupla.add(prim.getPrimeros().get(j).letra);
				}
			}
			primeros.add(tupla);
		}
	}
	
	public ArrayList <String> retornaBetas (PSTupla tupla, String noTerminal) {
		ArrayList <String> vector = new ArrayList <String> ();
		boolean flag = true;
		for (int i = 0 ; flag && i < tupla.size(); i++) {
			if ( tupla.getConjunto().get(i).equals(noTerminal) && i < tupla.size() - 1 ) {
				for (int j = i + 1 ;flag && j < tupla.size() ; j ++) {
					vector.add(tupla.getConjunto().get(j));
				}
			}
		}
		return vector;
	}
	
	public static ArrayList <String> unionVectoresString (ArrayList <String> vecString1, ArrayList <String> vecString2) {
		ArrayList <String> vecRes = vecString1;
		for (int i = 0 ; i < vecString2.size() ; i++) {
			if (!vecString1.contains(vecString2.get(i)))
				vecString1.add(vecString2.get(i));
		}
		return vecRes;
	}
	
	public ArrayList <String> unionVectoresStringEps (ArrayList <String> vecString1, ArrayList <String> vecString2) {
		ArrayList <String> vecRes = new ArrayList <String> (vecString1);
		vecRes.remove("Eps");
		for (int i = 0 ; i < vecString2.size() ; i++) {
			if (!vecString1.contains(vecString2.get(i)) && !vecString2.get(i).equals("Eps"))
				vecRes.add(vecString2.get(i));
		}
		//System.out.println(vecString1 + " " + vecString2 + " = " + vecRes);
		if (vecString1.contains("Eps") && vecString2.contains("Eps"))
			vecRes.add("Eps");
		return vecRes;
	}
	
	public ArrayList <String> getPrimerosSinEps (String noTerminal) {
		//boolean flag = true;
		ArrayList <String> vec = new ArrayList <String> ();
		if (!esNoTerminal (noTerminal)) {
			vec.add(noTerminal);
		} else
			for (int i = 0 ; i < primeros.getVector().size() ; i++) {
				if (primeros.getVector().get(i).noTerminal.equals(noTerminal)) {
					for (int j = 0 ; j < primeros.getVector().get(i).getConjunto().size() ; j++) {
						if (!primeros.getVector().get(i).getConjunto().get(j).equals("Eps"))
							vec.add(primeros.getVector().get(i).getConjunto().get(j));
					}
				}
			}
		return vec;
	}
	
	public ArrayList <String> getPrimerosConEps (String noTerminal) {
		//boolean flag = true;
		ArrayList <String> vec = new ArrayList <String> ();
		if (!esNoTerminal (noTerminal)) {
			vec.add(noTerminal);
		} else
			for (int i = 0 ; i < primeros.getVector().size() ; i++) {
				if (primeros.getVector().get(i).noTerminal.equals(noTerminal)) {
					for (int j = 0 ; j < primeros.getVector().get(i).getConjunto().size() ; j++) {
						vec.add(primeros.getVector().get(i).getConjunto().get(j));
					}
				}
			}
		return vec;
	}
	
	public ArrayList <String> siguientesDe (String noTerminal) {
		ArrayList <String> vec = new ArrayList <String> ();
		boolean flag = true;
		for (int i = 0 ; flag && i < siguientes.size() ; i++) {
			if (siguientes.getVector().get(i).noTerminal.equals(noTerminal)) {
				vec = siguientes.getVector().get(i).getConjunto();
				flag = false;
			}
		}
		return vec;
	}
	
	public boolean esNoTerminal (String token) {
		boolean flag = false;
		if (primeros.existeNoTerminal(token))
			flag = true;
		return flag;
	}
	
	public String toString () {
		String str = primeros.toString();
		str = str + siguientes.toString();
		return str;
	}
	
}
