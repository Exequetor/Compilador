package lexico;
/*
 * @since 0.1
 */
import java.util.*;

public class Tabla {
	public Vector <Estado> v = new Vector<Estado>();
	private Vector <String> alfabeto = new Vector <String> ();
	private String epsilon = herramientas.Reservadas.EPSILON;
	
	public Tabla (Vector <String> alf) {
		alfabeto = alf;
		
	}
	
	public Tabla (Tabla tab) {
		v = tab.getVector();
		alfabeto = tab.getAlfabeto();
	}
	
	public void insEst (Estado nodo) {
		v.add(nodo);
	}
	
	public Vector<Estado> getTabla () {
		return v;
	}
	
	public void clear () {
		v.clear();
	}
	
	public Vector <String> getAlfabeto () {
		return alfabeto;
	}
	
	public void addCont (int cont) {
		for (int i = 0 ; i < v.size() ; i ++) {
			v.get(i).addCont(cont);
		}
	}
	
	/*
	 * Retorna la cantidad de estados en la tabla
	 */
	public int size() {
		return v.size();
	}
	
	
	/*
	 * Vuelve los nodos de inicio y de fin a nodos normales. No afecta a los métodos getInicio ni getFin
	 */
	public void resetState () {
		v.firstElement().setFin(false);
		v.lastElement().setFin(false);
	}
	
	/*
	 * Retorna el index del primer estado el cual siempre es el inicial.
	 */
	public int getInicio () {
		return 0;
	}
	
	/*
	 * Retorna el index del último elemento.
	 */
	public int getFinal () {
		return v.size()-1;
	}
	
	public void setMarcado (int index, boolean valor) {
		Estado estado = v.get(index);
		estado.setMarcado(valor);
		v.setElementAt(estado, index);
	}
	
	public Vector <Estado> getVector () {
		return v;
	}
	
	public void setVector (Vector <Estado> vector) {
		v = vector;
	}
	
	public void destruirNodoInicial () {
		v.removeElementAt(0);
	}
	
	public Tabla concat(Tabla tabla) {
		Tabla tab = new Tabla (alfabeto);
		for (int i = 0; i < v.size() ; i++)
			tab.insEst((v.get(i)));
		for (int i = 0; i < tabla.getVector().size() ; i++)
			tab.insEst(tabla.getVector().get(i));
		
		return tab;
	}
	
	public Estado getEstado (int id) {
		Estado estado = null;
		try {
			for (int i = 0 ; i < v.size() ; i++)
				if (v.get(i).getId() == id)
					estado = v.get(i);
		} catch (Exception e1) {
			System.out.println("No se encontro el estado en el vector :(");
		}
		
		
		return estado;
	}
	
	/*
	 * @deprecated
	 */
	
	
	
	public void imprime () {
		System.out.println(this.toString());
	}
	/*
	 * Solo para la primera transición
	 */
	public void setTransLastNode (Trans nextEstado) {
		Estado nodo = v.lastElement();
		nodo.setT1(nextEstado);
		v.setElementAt(nodo, v.size()-1);
	}
	
	public void setTransLastNode (Trans nextEstado1, Trans nextEstado2) {
		Estado nodo = v.lastElement();
		nodo.setT1(nextEstado1);
		nodo.setT2(nextEstado2);
		v.setElementAt(nodo, v.size()-1);
	}
	
	public void setAlfabeto (Vector <String> alf) {
		alfabeto = alf;
	}

	public String toString () {
		boolean flag;
		String string = new String("Estados");
		
		for (int i = 0 ; i < alfabeto.size() ; i++)
			if (alfabeto.get(i) != epsilon)
				string = string.concat(("\t" + alfabeto.get(i)));
			else
				string = string.concat("\tEps");
		string = string.concat("\r\n");
		for (int i = 0 ; i < v.size() ; i++) {
			if (v.get(i).getVector().size() > 0) {
				string = string.concat((v.get(i).getId()) + "");
				if (v.get(i).esFin())
					string = string.concat(" f");
				string = string.concat("\t");
			}
			else
				string = string.concat(v.get(i).getId()+"\t");
			for (int j = 0 ; j < alfabeto.size() ; j++) {
				
				if (v.get(i).getVector().size() > 0) {
					for (int k = 0 ; k < v.get(i).getVector().size() ; k++) {
						if (v.get(i).getVector().get(k).getStep().equals(alfabeto.get(j))) {
							string = string.concat((v.get(i).getVector().get(k).getNext()) + "\t");						
						}
					}
						
				} else {
				
					flag = false;
					if (v.get(i).getT1()!=null && v.get(i).esFin() == false)
						if (v.get(i).getT1().getStep().equals(alfabeto.get(j))) {
							if (v.get(i).getT2() == null)
								
								string = string.concat(v.get(i).getT1().getNext()+"\t");
							else
								string = string.concat(v.get(i).getT1().getNext()+"");
							flag = true;
						}
					if (v.get(i).getT2()!=null && v.get(i).esFin() == false)
						if (v.get(i).getT2().getStep().equals(alfabeto.get(j))) {
							string = string.concat(", " + v.get(i).getT2().getNext()+"\t");
							flag = true;
						}
					if(flag == false) 
						string = string.concat("~\t");
				}
			}
			string = string.concat("\r\n");
		}
		return string;
	}
}
