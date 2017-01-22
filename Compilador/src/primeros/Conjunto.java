package primeros;

import java.util.*;

@SuppressWarnings("serial")
public class Conjunto extends Vector<Conjuntos> {
	public Vector<Conjuntos> conjuntos = new Vector<Conjuntos>();

	public Conjunto(){
		conjuntos = new Vector<Conjuntos>();
	}
	
	public Vector<Conjuntos> getConjuntos(){
		return conjuntos;
	}
}
