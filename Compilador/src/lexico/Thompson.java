package lexico;
/*
 * @since 0.1
 */
import java.util.*;

import lexico.Estado;
import lexico.Tabla;

public class Thompson {
	private String exp, tempString; 
	//private static char[] tempCharArray = new char [100];
	
	private final String epsilon = herramientas.Reservadas.EPSILON;
	private final Vector <Character> reservados = new Vector <Character> ();
	
	private Tabla tabFinal;
	
	private Vector <String> alfabeto = new Vector <String> ();
	private Vector <Tabla> vectorTabla = new Vector <Tabla> (); 
	private Stack <String> stackString = new Stack <String> ();
	private Stack <Tabla> stackTabla = new Stack <Tabla> ();
	
	private Scanner in = new Scanner (System.in);
	
	
	private Vector <String> vectorString= new Vector <String> ();
	
	
	private boolean conInterfaz;
	private String expDeInterfaz;
	public Thompson(String expresion, boolean conInterfaz) {
		expDeInterfaz = expresion;
		this.conInterfaz = conInterfaz;
	}
	
	public String start (){
		reservados.add('|');
		reservados.add('*');
		reservados.add('(');
		reservados.add(')');
		reservados.add('+');
		reservados.add('?');
		
		if (conInterfaz != true)
			exp=in.next();
		else
			exp = expDeInterfaz;
		
		alfabeto.addElement(epsilon);
		for (int i = 0 ; i < exp.length() ; i++)
			if (!reserv(exp.charAt(i)) && !existe(Character.toString(exp.charAt(i))))
				alfabeto.add(Character.toString(exp.charAt(i)));

		vectorString.trimToSize();
		vectorTabla.trimToSize();
		tempString = new String();
		
		vectorString.addElement("(");
		boolean flagRead = false;
		for (int i = 0 ; i < exp.length() ; i++) {
			if (exp.charAt(i) != '|' && !reserv(exp.charAt(i))) {
				
				tempString = tempString.concat(Character.toString(exp.charAt(i)));
				
			}
			else {
				
				if (exp.charAt(i) == '(')
					flagRead = true;
				
				if (exp.charAt(i) == ')')
					flagRead = false;
				
				if (tempString.length() != 0) {
					vectorString.addElement(tempString);
				}
				
				if (exp.charAt(i) != '|')
					vectorString.addElement(Character.toString(exp.charAt(i)));
				else {
					if (!flagRead) {
						vectorString.addElement(")");
						vectorString.addElement("|");
						vectorString.addElement("(");
					} else
						vectorString.addElement(Character.toString(exp.charAt(i)));
				}
					
				tempString = new String ();
				
				
				
				
			}
		}
		
		//System.out.println(tempCharArray);
		if (tempString.length() != 0)
			vectorString.addElement(tempString);
		
		vectorString.addElement(")");
	
		for (int i = 0; i < vectorString.size() ; i++) {
			
			if (!reserv(vectorString.get(i).charAt(0))) {
				stackTabla.push(concatBasic(vectorString.get(i)));
			}
			else {
				if (vectorString.get(i).charAt(0) == '|') {
					stackString.push(vectorString.get(i));
				}
				else {
					switch (vectorString.get(i).charAt(0)) {
					case '(': 
							 
							 i++;
							 
							 Vector <String> vectStrTemp = new Vector <String> ();
							 vectStrTemp.trimToSize();
							 int contParentesis = 0;
							 while (vectorString.get(i).charAt(0) != ')' || contParentesis != 0) {
								
								vectStrTemp.addElement(vectorString.get(i));
								if (vectorString.get(i).charAt(0) == '(')
									contParentesis++;
								if (vectorString.get(i).charAt(0) == ')')
									contParentesis--;
								i++;
							 }
							 stackTabla.push(concatenaOr(vectStrTemp));
							 
					break;
				}
			}
		}
			
		}
		Stack <Tabla> tempStackT = new Stack <Tabla> ();
		while (!stackTabla.isEmpty()) {
			tempStackT.push(stackTabla.pop());
			
		}
		stackTabla = tempStackT;
		Tabla tablaFinal = new Tabla (alfabeto);
		while (!stackString.isEmpty()) {
			    stackString.pop();
				stackTabla.push(union(stackTabla.pop(),stackTabla.pop()));
		}
		while (stackTabla.size() >= 2) {
			stackTabla.push(concatBasic(stackTabla.pop(), stackTabla.pop()));
		}
		
		tablaFinal = stackTabla.pop();
		tabFinal = tablaFinal;
		String stringReturn = new String (tablaFinal.toString());
		
		if (!conInterfaz)
			System.out.println(stringReturn);
		
		return stringReturn;	
	}
	
	public Tabla concatenaOr (Vector <String > expTemp) {
		Stack <Tabla> stackTablaTemp = new Stack <Tabla> ();
		Stack <Tabla> stackTablaAux = new Stack <Tabla> ();
		Stack <String> stackStringTemp = new Stack <String> ();
		Boolean flag2 = false;
		for (int i = 0; i < expTemp.size() ; i++) {
					if (!reserv(expTemp.get(i).charAt(0))) {
						stackTablaTemp.push(concatBasic(expTemp.get(i)));
					}
					else {
						
						switch (expTemp.get(i).charAt(0)) {
							case '|': stackStringTemp.push("|");
							break;
								 
							case '(': 
								 
								 if (i > 0)
									 if (!reserv(expTemp.get(i-1).charAt(0)))
										 flag2 =true;
								 i++;
								 
								 Vector <String> vectStrTemp = new Vector <String> ();
								 vectStrTemp.trimToSize();
								 int contParentesisTemp = 0;
								 while ((expTemp.get(i).charAt(0) != ')' || contParentesisTemp != 0) && i < expTemp.size()) {
									
									vectStrTemp.addElement(expTemp.get(i));
									if (expTemp.get(i).charAt(0) == '(')
										contParentesisTemp++;
									if (expTemp.get(i).charAt(0) == ')')
										contParentesisTemp--;
									i++;
								 }
								 //vectStrTemp.addElement(")");
								 stackTablaTemp.push(concatenaOr(vectStrTemp));
								 
						break;
						case '*':
								//i++;
								stackTablaTemp.push(kleene(stackTablaTemp.pop()));
								if (flag2) {
									
								 	stackTablaTemp.push(concatBasicInverted(stackTablaTemp.pop(),stackTablaTemp.pop()));
								 	flag2 = false;
								 }
						break;
						
						case '+' :
							stackTablaTemp.push(cerraduraPositiva(stackTablaTemp.pop()));
							if (flag2) {
								
							 	stackTablaTemp.push(concatBasicInverted(stackTablaTemp.pop(),stackTablaTemp.pop()));
							 	flag2 = false;
							 }
						break;
						case '?' : 
							stackTablaTemp.push(opcional(stackTablaTemp.pop()));
							if (flag2) {
								
							 	stackTablaTemp.push(concatBasicInverted(stackTablaTemp.pop(),stackTablaTemp.pop()));
							 	flag2 = false;
							 }
						}
						
				}			
		}
		
		while (!stackTablaTemp.isEmpty())
			stackTablaAux.push(stackTablaTemp.pop());
		stackTablaTemp = stackTablaAux;
		
		while (!stackStringTemp.isEmpty()) {
			stackStringTemp.pop();
			stackTablaTemp.push(union (stackTablaTemp.pop(),stackTablaTemp.pop()));
		}
		while (stackTablaTemp.size() >= 2) {
			stackTablaTemp.push(concatBasic(stackTablaTemp.pop(), stackTablaTemp.pop()));
		}
		return stackTablaTemp.pop();
	}
	
	public Tabla concatBasic (String ex) {
		Tabla tab = new Tabla (alfabeto);
		for (int i = 0 ; i < ex.length(); i++) {
			Trans trans = new Trans (Character.toString(ex.charAt(i)), i+1);
			Estado nodo = new Estado (i, trans, false, false);
			tab.insEst(nodo);
		}
		
		Estado nodo = new Estado (ex.length(), null, false, true);
		tab.insEst(nodo);
		return tab;
	}
	public Tabla concatBasic (Tabla t1, Tabla t2) {
		Tabla tab = new Tabla (alfabeto);
		int tabTam = 0;
		
		tabTam = t1.size()-1;
		
		t2.addCont(tabTam);
		
		t1.v.setElementAt(t2.v.get(0), t1.v.size()-1);
		t2.destruirNodoInicial();
		
		tab = tab.concat(t1);
		tab = tab.concat(t2);
		
		return tab;
	}
	
	public Tabla concatBasicInverted (Tabla t2, Tabla t1) {
		Tabla tab = new Tabla (alfabeto);
		int tabTam = 0;
		
		tabTam = t1.size()-1;
		
		t2.addCont(tabTam);
		t1.v.setElementAt(t2.v.get(0), t1.v.size()-1);
		t2.destruirNodoInicial();
		
		tab = tab.concat(t1);
		tab = tab.concat(t2);
		
		return tab;
	}
	
	public Tabla union (Tabla t1, Tabla t2) {
		Tabla tab = new Tabla (alfabeto);
		
		t1.addCont(1);
		t2.addCont(t1.size()+1);
		
		Trans trans1 = new Trans (epsilon, t1.getVector().get(0).getId());
		Trans trans2 = new Trans (epsilon, t2.getVector().get(0).getId());
		Estado nodo = new Estado (0, trans1, trans2, true, false);
		
		tab.insEst(nodo);
		
		int tablaTam = t1.size() + t2.size() + 1;
		
		t1.setTransLastNode(new Trans (epsilon, tablaTam));
		t2.setTransLastNode(new Trans (epsilon, tablaTam));
		
		t1.resetState();
		t2.resetState();
		
		tab = tab.concat(t1);
		tab = tab.concat(t2);
		
		nodo = new Estado (tablaTam, null, false, true);

		tab.insEst(nodo);
		
		return tab;
	}
	
	public Tabla kleene (Tabla t1) {
		Tabla tab = new Tabla (alfabeto);
		
		int tablaTam = t1.size() + 1;
		
		t1.addCont(1);
		
		Trans trans1 = new Trans (epsilon, t1.getVector().get(0).getId());
		Trans trans2 = new Trans (epsilon, tablaTam);
		Estado nodo = new Estado (0, trans1, trans2, true, false);
		tab.insEst(nodo);
		
		t1.setTransLastNode(new Trans (epsilon, tablaTam), new Trans (epsilon, t1.getVector().get(0).getId()));
		
		t1.resetState();
		
		tab = tab.concat(t1);
		
		nodo = new Estado (tablaTam, null, false, true);
		
		tab.insEst(nodo);		
		
		return tab;
	}
	
	public Tabla cerraduraPositiva (Tabla t1) {
		Tabla tab = new Tabla (alfabeto);
		
		int tablaTam = t1.size() + 1;
		
		t1.addCont(1);
		
		Trans trans1 = new Trans (epsilon, t1.getVector().get(0).getId());

		Estado nodo = new Estado (0, trans1, true, false);
		tab.insEst(nodo);
		
		t1.setTransLastNode(new Trans (epsilon, tablaTam), new Trans (epsilon, t1.getVector().get(0).getId()));
		
		t1.resetState();
		
		tab = tab.concat(t1);
		
		nodo = new Estado (tablaTam, null, false, true);
		
		tab.insEst(nodo);		
		
		return tab;
	}
	
	public Tabla opcional (Tabla t1) {
		Tabla tab = new Tabla (alfabeto);
		
		int tablaTam = t1.size() + 1;
		
		t1.addCont(1);
		
		Trans trans1 = new Trans (epsilon, t1.getVector().get(0).getId());
		Trans trans2 = new Trans (epsilon, tablaTam);
		Estado nodo = new Estado (0, trans1, trans2, true, false);
		tab.insEst(nodo);
		
		t1.setTransLastNode(new Trans (epsilon, tablaTam), null);
		
		t1.resetState();
		
		tab = tab.concat(t1);
		
		nodo = new Estado (tablaTam, null, false, true);
		
		tab.insEst(nodo);		
		
		return tab;
	}
	/*
	public Tabla cerraduraPositiva (Tabla t1) {
		Tabla tab = new Tabla (alfabeto)
				
	}
	*/
	public boolean reserv (char c) {
		boolean flag = false;
		for (int i = 0; i < reservados.size() && flag == false; i++)
			if (c == reservados.get(i)) 
				flag = true;
		return flag;
			
	}
	
	public Tabla getTabla () {
		return tabFinal;
	}
	
	public boolean existe(String a){
		boolean aux=false;
		for(int i=0;i<alfabeto.size();i++){
			if (alfabeto.get(i).equals(a))
				aux=true;		
			}
		return aux;
	}
}

