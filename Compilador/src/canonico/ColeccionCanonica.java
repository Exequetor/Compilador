package canonico;

import herramientas.*;
import java.util.ArrayList;

public class ColeccionCanonica {
	public PSTabla gramatica;
	public ArrayList <EstadosCanonica> coleccionCanonica;
        private ArrayList <EstadosCanonica> coleccionFull;
	public EstadosCanonica estados;
	public ArrayList <String> noTerminales;
	public static final String PUNTO = Character.toString(((char) 176));
	
	public ColeccionCanonica (String expresion) {
		gramatica = Gramatica.leeGramaticaNoEps (expresion, 1);
		//System.out.println(gramatica);
		coleccionCanonica = new ArrayList <EstadosCanonica> ();
        coleccionFull = new ArrayList <EstadosCanonica> ();
		gramaticaAumentada ();
		noTerminales = Gramatica.retornaNoTerminales(gramatica);
		//System.out.println(noTerminales);
		algoritmoCanonico ();
		gramatica = Gramatica.leeGramatica(expresion, 1);
		gramaticaAumentada ();
		//System.out.println (this);
	}
	
	private void gramaticaAumentada () {
		PSTupla tupla = new PSTupla (gramatica.get(1).noTerminal + "'");
		tupla.add(gramatica.get(1).noTerminal);
		tupla.add("$");
		gramatica.setTupla(tupla, 0);
	}
	
        public ArrayList <EstadosCanonica> getColeccionFull () {
            return new ArrayList <EstadosCanonica> (coleccionFull);
        }
        
	private void algoritmoCanonico () {
		int contEstados = 0;
		EstadosCanonica estadoCero = new EstadosCanonica (contEstados++);
		PSTupla coleccionICero = new PSTupla (gramatica.get(0));
		coleccionICero.setConjunto (setPunto (coleccionICero.getConjunto()));
		estadoCero.add(coleccionICero);
		coleccionCanonica.add(cerradura (estadoCero));
                coleccionFull.add(new EstadosCanonica (cerradura (estadoCero)));
		for (int i = 0 ; i < coleccionCanonica.size() ; i++) {
			EstadosCanonica estado = new EstadosCanonica (coleccionCanonica.get(i));
			//System.out.println(estado.size());
			for (int j = 0 ; j < estado.size() ; j++) {
				for (int k = 0 ; k < estado.get(j).size() - 1; k++) {
					//System.out.println("Entra for");
					if (estado.get(j).getConjunto().get(k).contains(PUNTO)) {
						String expresion = estado.get(j).getConjunto().get(k + 1);
						EstadosCanonica newEstado;
						if (!expresion.equals("$"))
							 newEstado = new EstadosCanonica (irA (estado, expresion));						
						else {
							 newEstado = new EstadosCanonica ("Ir_a (I" + i + ", $)");
							 newEstado.setAceptacion (true);
						}
						if (!contieneEstado (coleccionCanonica, newEstado))
							coleccionCanonica.add(new EstadosCanonica (newEstado));				
                        if (!newEstado.getAceptacion()) {
                            if (!contieneEstado (coleccionFull, newEstado))
                                coleccionFull.add(new EstadosCanonica (newEstado));
                            else {
                                for (int index = 0 ; index < coleccionCanonica.size() ; index++) {
                                    //System.out.println(index + " " + newEstado.equals(coleccionCanonica.get(index), false));
                                    if (newEstado.equals(coleccionCanonica.get(index), false)) {
                                        newEstado.setEstado(coleccionCanonica.get(index).getEstado());
                                        //System.out.println(newEstado.getEstado());
                                        coleccionFull.add(new EstadosCanonica (newEstado));
                                    }
                                }
                            }
                        }
					}
				}	
			}
		}
	}
	
	private EstadosCanonica irA (EstadosCanonica estado, String expresion) {
		estado = new EstadosCanonica (estado);
		EstadosCanonica irACerradura = new EstadosCanonica (coleccionCanonica.size() - numEstadosAceptacion());
		for (int i = 0 ; i < estado.size() ; i++) {
			for (int j = 0 ; j < estado.get(i).size() - 1 ; j++) {
				if (estado.get(i).getConjunto().get(j).contains(PUNTO) && estado.get(i).getConjunto().get(j + 1).contains(expresion)) {
					irACerradura.add(muevePunto(estado.get(i)));
				}
			}
		}
		//System.out.println(irACerradura);
		EstadosCanonica newEstado = new EstadosCanonica (cerradura(irACerradura));
		//System.out.println(estado.getEstado() + " " + expresion);
		newEstado.setIrA(expresion);
		newEstado.setIEstado(estado.getEstado());
                //System.out.println (newEstado.getIEstado() + " " + newEstado.getIrA());
		return newEstado;
		
	}
	
	private boolean contieneEstado(ArrayList<EstadosCanonica> list,EstadosCanonica estado) {
		boolean flag = false;
                list = new ArrayList <EstadosCanonica> (list);
		for (int i = 0 ; i < list.size() && !flag; i++) {
			if (list.get(i).size() == estado.size()) {
				flag = true;
				for (int j = 0 ; j < list.get(i).size() && flag; j++) {
					flag = false;
					for (int k = 0 ; k < estado.size() && !flag ; k++) {
						if (list.get(i).getVector().get(j).equals(estado.getVector().get(k))) {
							//System.out.println(true);
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}
	
	private EstadosCanonica cerradura (EstadosCanonica estado) {
		estado = new EstadosCanonica (estado);
		EstadosCanonica J = estado;
		for (int i = 0 ; i < J.size() ; i++) {
			for (int index = 0 ; index < J.getVector().get(i).size() - 1; index++) {
				String actualElement = J.getVector().get(i).getConjunto().get(index);
				String elementAfterPoint = J.getVector().get(i).getConjunto().get(index+1);
				//System.out.println (estado);
				//System.out.println(actualElement + " " + elementAfterPoint + " " + (boolean)(actualElement == PUNTO) + " " + (boolean)(noTerminales.contains(elementAfterPoint)));
				if (actualElement == PUNTO && noTerminales.contains(elementAfterPoint)) {
					//System.out.println(elementAfterPoint);
					String noTerminal = elementAfterPoint;
					if (!elementAfterPoint.equals(J.getVector().get(i).noTerminal) || index != 0) {
						//System.out.println("No Terminal: " + elementAfterPoint);
						for (int j = 0 ; j < gramatica.size() ; j++) {		
							if (gramatica.get(j).noTerminal.equals(noTerminal)) {
								PSTupla newElement = new PSTupla (gramatica.get(j));
								newElement.setConjunto(setPunto (newElement.getConjunto()));
								J.add(newElement);
							}
						}
					}
				}
			}			
		}
		return J;
	}
	
	private PSTupla muevePunto (PSTupla conjunto) {
		conjunto = new PSTupla (conjunto);
		int i = 0;
		String aux = new String ();
		while (!conjunto.get(i).equals(PUNTO)) i++;
		aux = PUNTO;
		conjunto.set(i, conjunto.get(i+1));
		conjunto.set(i+1, aux);
		return conjunto;
	}
	
	private ArrayList <String> setPunto (ArrayList <String> conjunto) {
		conjunto = new ArrayList <String> (conjunto);
		ArrayList <String> vectorStr = new ArrayList <String> ();
		vectorStr.add(PUNTO);
		for (int i = 0 ; i < conjunto.size() ; i++) {
			vectorStr.add(conjunto.get(i));
		}
		return vectorStr;
	}
	
	/*private ArrayList <PSTupla> unionPSTupla (ArrayList <PSTupla> vecString1, ArrayList <PSTupla> vecString2) {
		ArrayList <PSTupla> vecRes = vecString1;
		for (int i = 0 ; i < vecString2.size() ; i++) {
			if (!vecString1.contains(vecString2.get(i)))
				vecString1.add(vecString2.get(i));
		}
		return vecRes;
	}*/
	
	private int numEstadosAceptacion () {
		int cont = 0;
		for (int i = 0 ; i < coleccionCanonica.size() ; i++) {
			if (coleccionCanonica.get(i).getAceptacion())
				cont++;
		}
		return cont;
	}
	
	public String toString () {
		String str = new String ();
		
		str = gramatica.toString() + "Colección Canónica\n";
		
		for (int i = 0 ; i < coleccionCanonica.size() ; i++) {
			str = str.concat(coleccionCanonica.get(i).toString() + "\n");
		}
		
		return str;
	}
}
