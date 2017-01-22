package semantico;

import java.util.ArrayList;
import java.util.Stack;

import herramientas.Gramatica;
import herramientas.PSTabla;
import sintactico.Atributo;
/*
 * Esta clase contiene las acciones semánticas de la gramática
 * especificada. En este caso, la vista en clase para la entrega
 * del 16 de enero 2017.
 * Cuando se invoca cada regla se retorna la traducción de la pila.
 * 
 * Rule number = 10
 * Rules Eps = 2;
 * Total rules = 8;
 * 
 * Grammar
 * 1] S T V V' L ;
 * 2] L , V V' L
 * 3] L Eps
 * 4] V * V
 * 5] V id
 * 6] V' [ nint ] V'
 * 7] V' Eps
 * 8] T char
 * 9] T float
 * 10] T int
 */
public class ASTeacher {
	/*
	 * Raw non terminal grammar rules
	 */
	protected ArrayList<String> nonTerminals; 
	private Stack <Atributo> temporales;
	private String patchType = "null";
	public ASTeacher (String grammar) {
		nonTerminals = new ArrayList <String> ();
		temporales = new Stack <Atributo> ();
		PSTabla tempTabla = Gramatica.leeGramatica(grammar);
		for (int i = 0 ; i < tempTabla.size() ; i++) {
			nonTerminals.add(tempTabla.get(i).getNoTerminal());
		}
		//System.out.println(nonTerminals);
	}
	public String rule1 () {
		String str = new String ();
		Atributo temp, T = new Atributo (), V = new Atributo (), Vprim = new Atributo (), L = new Atributo (), S = new Atributo ("S");
		for (int i = 0 ; i < 5 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("T"))
				T = new Atributo (temp);
			if (temp.getName().equals("V"))
				V = new Atributo (temp);
			if (temp.getName().equals("V'"))
				Vprim = new Atributo (temp);
			if (temp.getName().equals("L"))
				L = new Atributo (temp);
		}
		if (!Vprim.getTrad().equals(Eps()))
			S.setTemp("array [" + Vprim.getTrad());
		else
			S.setTemp(Vprim.getTrad());
		str = "var " + V.getTrad() + ":" + S.getTemp() + V.getTemp() + T.getTrad() + ";\r\n    " + L.getTrad();
		S.setTrad(str);
		temporales.push(S);
		return str;
	}
	public String rule2 () {
		String str;
		Atributo temp, V = new Atributo (), Vprim = new Atributo (), L = new Atributo ();
		for (int i = 0 ; i < 4 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("V"))
				V = new Atributo (temp);
			if (temp.getName().equals("V'"))
				Vprim = new Atributo (temp);
			if (temp.getName().equals("L"))
				L = new Atributo (temp);
		}
		if (!Vprim.getTrad().equals(Eps())) 
			L.setTemp("array [" + Vprim.getTrad());
		else
			L.setTemp(Vprim.getTrad());
		str = V.getTrad() + ":" + L.getTemp() + V.getTemp() + patchType + ";\r\n    " + L.getTrad();
		temporales.push(new Atributo ("L", str, L.getTemp()));
		return str;
	}
	public String rule3 () {
		String str = new String ();
		str = Eps();
		temporales.push(new Atributo ("L", Eps()));
		return str;
	}
	public String rule4 () {
		String str = new String ();
		Atributo temp, V = new Atributo ();
		for (int i = 0 ; i < 2 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("V"))
				V = new Atributo (temp);
		}
		str = V.getTrad();
		temporales.push(new Atributo ("V", str, "pointer of " + V.getTemp()));
		return str;
	}
	public String rule5 () {
		String str = new String ();
		Atributo temp, id = new Atributo ();
		for (int i = 0 ; i < 1 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("id"))
				id = new Atributo (temp); 
		}
		str = id.getTrad();
		temporales.push (new Atributo ("V", str, Eps()));
		return str;
	}
	public String rule6 () {
		String str = new String ();
		Atributo temp, nint = new Atributo (), Vprim = new Atributo ();
		for (int i = 0 ; i < 4 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("nint"))
				nint = new Atributo (temp); 
			if (temp.getName().equals("V'"))
				Vprim = new Atributo (temp);
		}
		if (Vprim.getTrad().equals(Eps()))
			str = "0.." + (Integer.parseInt(nint.getTrad()) - 1) + "] of ";
		else
			str = "0.." + (Integer.parseInt(nint.getTrad()) - 1)  + ", " + Vprim.getTrad();
		temporales.push (new Atributo ("V'", str));
		return str;
	}
	public String rule7 () {
		String str = new String ();
		str = Eps();
		temporales.push(new Atributo ("V'", str));
		return str;
	}
	public String rule8 () {
		String str = new String ();
		temporales.pop();
		str = "char";
		patchType = str;
		temporales.push(new Atributo ("T", str));
		return str;
	}
	public String rule9 () {
		String str = new String ();
		temporales.pop();
		str = "real";
		patchType = str;
		temporales.push(new Atributo ("T", str));
		return str;
	}
	public String rule10 () {
		String str = new String ();
		temporales.pop();
		str = "integer";
		patchType = str;
		temporales.push(new Atributo ("T", str));
		return str;
	}
	public void push (Atributo element) {
		temporales.push(element);
	}
	@SuppressWarnings("unchecked")
	public Stack <Atributo> getTempStack () {
		return (Stack <Atributo>) temporales.clone();
	}
	public  String Eps () {
		return "";
	}
	@Override
	public String toString () {
		return temporales.toString();
	}
}
