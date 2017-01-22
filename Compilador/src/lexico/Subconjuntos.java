package lexico;

import java.util.*;

public class Subconjuntos {
	private final String LETRA = "Letra";
	Vector <Tabla> vectorTabla = new Vector <Tabla> ();
	
	private Vector <String> alfabeto = new Vector <String> ();
	private Vector <Integer> vectorInteger = new Vector <Integer> ();
	private Tabla tablaReferencia;
	
	private int estadoFinalThompson;
	private boolean existeLetras = false;
	
	Tabla tablaFinal = new Tabla (alfabeto);
	
	public Subconjuntos (Tabla tabla) {
		vectorInteger.trimToSize();
		tablaReferencia = tabla;
		for (int i = 1 ; i < tabla.getAlfabeto().size() ; i++) {
			switch (tabla.getAlfabeto().get(i)) {
				case herramientas.Reservadas.LETRAS:
					alfabeto.addElement(LETRA);
					break;
				case herramientas.Reservadas.DIGITOS:
					alfabeto.addElement("Digito");
					break;
				default:
					alfabeto.addElement(tabla.getAlfabeto().get(i));
			}
		}
		
		//System.out.println(alfabeto);
	}
	
	public Tabla start () {
		int contEstado = 0;
		Vector <Integer> vect = new Vector <Integer> ();
		vect.addElement(0);
		estadoFinalThompson = tablaReferencia.getFinal();
		
		Tabla tabla = new Tabla (alfabeto);
		Cerradura cerradura = new Cerradura (vect, tablaReferencia);
		vectorInteger = cerradura.start();
		Estado estado = generaEstado(vectorInteger);
		estado.setId(contEstado++);
		estado.setInicio(true);
		tabla.insEst(estado);
		
		String alfabetoTemp;

		while (existeNoMarcado(tabla)) {
			int indexEstado = -1;
			for(int i = 0 ; i < tabla.size() && indexEstado == -1; i++) {
				
				if (!tabla.getVector().get(i).getMarcado()) {
					indexEstado = i;
				}
			}
			tabla.setMarcado(indexEstado, true);
			estado = tabla.getVector().get(indexEstado);
			alfabetoTemp = new String ();
			for (int k = 0 ; k < estado.getVectorInteger().size() ; k++) {
				Estado estTemp = tablaReferencia.getVector().get(estado.getVectorInteger().get(k));
				if (estTemp.getT1() != null && estTemp.getT1().getStep().equals(herramientas.Reservadas.LETRAS)) {
					existeLetras = true;
					alfabetoTemp = alfabetoTemp.concat("L ");
				}
				if (estTemp.getT2() != null && estTemp.getT2().getStep().equals(herramientas.Reservadas.LETRAS)) {
					existeLetras = true;
					alfabetoTemp = alfabetoTemp.concat("L ");
				}
			}
			for (int l = 0 ; l < estado.getVectorInteger().size() && existeLetras == true; l++) {
				Trans trans1 = tablaReferencia.getEstado(estado.getVectorInteger().get(l)).getT1();
				Trans trans2 = tablaReferencia.getEstado(estado.getVectorInteger().get(l)).getT2();
				for (int y = 0 ; y < alfabeto.size() ; y++) {
					if (trans1 != null && trans1.getStep().equals(alfabeto.get(y)))
						alfabetoTemp = alfabetoTemp.concat("-" + alfabeto.get(y));
					if (trans2 != null && trans2.getStep().equals(alfabeto.get(y)))
						alfabetoTemp = alfabetoTemp.concat("-" + alfabeto.get(y));
				}
			}
			if (existeLetras)
				alfabeto.addElement(alfabetoTemp);
			existeLetras = false;
			for (int i = 0 ; i < alfabeto.size() ; i++) {
				cerradura = new Cerradura (move(tabla.getVector().get(indexEstado), alfabeto.elementAt(i)), tablaReferencia);
				Vector <Integer> vectorTemp = cerradura.start();
				//System.out.println(vectorTemp);
				estado = generaEstado (vectorTemp);
				boolean existe = false;
				int indexExiste = -1;
				for (int j = 0 ; j < tabla.size() && existe == false; j++) {
					if (estado.getVectorInteger().equals(tabla.v.get(j).getVectorInteger())) {
						existe = true;
						indexExiste = j;
					}
				}
				if (existe) {
					estado.setId(indexExiste);
				}
				else {
					estado.setId(contEstado++);
					if (estado.getVectorInteger().contains(estadoFinalThompson))
						estado.setFin(true);
					tabla.insEst(estado);
				}
				// Param (tabla a modificar, index en la tabla, caracter de transicion, estado al que va)
				Estado estTemp = tabla.v.get(indexEstado);
				Trans transTemp = new Trans (alfabeto.elementAt(i), estado.getId());
				
				estTemp.insertaTransicion(transTemp);
				tabla.v.setElementAt(estTemp, indexEstado);
				//tabla.imprime();
			}
		}		
		return tabla;
	}
	
		
	public Estado generaEstado (Vector <Integer> vector) {
		Estado estado = new Estado ();
		
		estado.setVectorInteger(vector);
		
		return estado;
	}
	
	public Vector <Integer> move (Estado estado, String letra) {
		Vector <Integer> vector = new Vector <Integer> ();
		vector.trimToSize();
		for (int i = 0 ; i < estado.getVectorInteger().size() ; i++) {
			Trans trans1 = tablaReferencia.getEstado(estado.getVectorInteger().get(i)).getT1();
			Trans trans2 = tablaReferencia.getEstado(estado.getVectorInteger().get(i)).getT2();
			if (trans1 != null && trans1.getStep().equals(letra))
				vector.addElement(trans1.getNext());
			if (trans2 != null && trans2.getStep().equals(letra))
				vector.addElement(trans2.getNext());
		}
		Collections.sort(vector);
		
		return vector;
	}
	
	public boolean existeNoMarcado (Tabla tab) {
		boolean flag = false;
		for (int i = 0 ; i < tab.size() && flag == false; i++)
			if (tab.getVector().get(i).getMarcado() == false)
				flag = true;
		return flag;
	}
	
}
