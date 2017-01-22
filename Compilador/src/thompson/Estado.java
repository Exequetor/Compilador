package thompson;
/*
 * @since 0.1
 */
import java.util.*;

public class Estado {
	private final String epsilon = library.Reservadas.EPSILON;
	
	private int id;
	private boolean inicio=false, fin=false;
	private Trans T1, T2;
	private Vector <Trans> vectorTransiciones= new Vector <Trans> ();
	private Vector <Integer> vectorInteger = new Vector <Integer> ();
	private boolean marcado = false;
	
	
	public Estado () {
		id = -1;
		T1 = T2 = null;
		marcado = false;
	}
	
	public Estado(Estado E){
		id=E.getId();
		T1 = E.getT1();
		T2 = E.getT2();
		inicio=E.esInicio();
		fin=E.esFin();
		
		vectorTransiciones.trimToSize();
		vectorTransiciones = E.getVector();
	}
	
	public Estado (int i, Trans T1, Trans T2, boolean start, boolean end){
		id=i;
		this.T1 = T1;
		this.T2 = T2;
		inicio=start;
		fin=end;
		
		vectorTransiciones.trimToSize();
		
	}
	
	public Estado (int i, Trans T1, boolean start, boolean end){
		id=i;
		this.T1 = T1;
		this.T2 = null;
		inicio=start;
		fin=end;
		
		vectorTransiciones.trimToSize();
		
	}
	
	public Estado (int i, boolean start, boolean end, Vector <Trans> vectorTransiciones) {
		this.vectorTransiciones.trimToSize();
		
		id = i;
		T1 = null;
		T2 = null;
		this.vectorTransiciones = vectorTransiciones;
		inicio = start;
		fin = end;
	}
	
	public void setVectorInteger (Vector <Integer> vector) {
		vectorInteger = vector;
	}
	
	public Vector <Integer> getVectorInteger () {
		return vectorInteger;
	}
	
	public void setVectorIntegerElement (int element, int index) {
		vectorInteger.setElementAt(element, index);
	}
	
	public void addCont (int cont) {
		id = id + cont;
		if (T1 != null)
			T1.addCont(cont);
		if (T2 != null)
			T2.addCont(cont);
		
		if (vectorTransiciones != null && vectorTransiciones.size() != 0)
			for (int i = 0 ; i < vectorTransiciones.size() ; i++) {
				vectorTransiciones.get(i).addCont(cont);
			}
	}
	
	public boolean tieneEpsilon () {
		boolean flag = false;
		if ((T1 != null && T1.getStep() == epsilon )|| (T2 != null && T2.getStep() == epsilon))
			flag = true;
		else
			for (int i = 0 ; i < vectorTransiciones.size() && flag == false; i++)
				if (vectorTransiciones.get(i).getStep() == epsilon)
					flag = true;
		
		return flag;
	}
	
	public Vector <Integer> retornaTransEpsilon () {
		Vector <Integer> vector = new Vector <Integer> ();
		vector.trimToSize();
		if (T1 != null && T1.getStep() == epsilon)
			vector.addElement(T1.getNext());
		if (T2 != null && T2.getStep() == epsilon)
			vector.addElement(T2.getNext());
		return vector;
	}
	
	public boolean getMarcado () {
		return marcado;
	}
	
	public void setMarcado (boolean flag) {
		marcado = flag;
	}
	
	public Trans getT1() {
		return T1;
	}
	
	public Trans getT2() {
		return T2;
	}
	
	public int getId (){
		return id;
	}
	
	/*
	 * Retorna el vector de transiciones del estado.
	 */
	public Vector <Trans> getVector() {
		return vectorTransiciones;
	}
	
	/*
	 * Remplaza el vector actual del estado por el vector asignado en el parámetro.
	 */
	
	public void setVector (Vector <Trans> vect) {
		this.vectorTransiciones = vect;
	}
	
	/*
	 * Inserta transicion al vector de transiciones.
	 */
	
	public void insertaTransicion (Trans trans) {
		vectorTransiciones.add(trans);
	}
	
	public Vector <Integer> getVectorID () {
		Vector <Integer> vector = new Vector <Integer> ();
		vector.trimToSize();
		for (int i = 0 ; i < vectorTransiciones.size() ; i++) {
			vector.addElement(vectorTransiciones.get(i).getNext());
		}
		Collections.sort(vector);
		return vector;
	}
	
	public void clearEstados () {
		vectorTransiciones.clear();
		vectorTransiciones.trimToSize();
	}
	
	public int size() {
		int tam = vectorTransiciones.size();
	
		return tam;
	}
	
	public boolean esInicio () {
		return inicio;
	}
	
	public boolean esFin () {
		return fin;
	}
	
	public void setId (int i) {
		id=i;
	}
	
	public void setInicio (boolean i){
		inicio=i;
	}
	
	public void setFin (boolean f){
		fin=f;
	}
	
	public void setT1 (Trans t1) {
		T1 = t1;
	}
	
	public void setT2 (Trans t2) {
		T2 = t2;
	}
	
	public void makeStandardState () {
		inicio = false;
		fin = false;
	}
	
	public void imprime() {
		//System.out.println("ID: " + id + "T1: " + T1.getStep() + ", " + T1.getNext());
		System.out.println(vectorInteger);
	}
	
}
