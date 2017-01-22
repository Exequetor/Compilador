package semantico;

import java.util.ArrayList;
import java.util.Stack;

import herramientas.Gramatica;
import herramientas.PSTabla;
import sintactico.Atributo;

public class ASPython {
	/*
	 * Raw non terminal grammar rules
	 */
	protected ArrayList<String> nonTerminals; 
	private Stack <Atributo> temporales;
	private String patchType = "null";
	public ASPython (String grammar) {
		nonTerminals = new ArrayList <String> ();
		temporales = new Stack <Atributo> ();
		PSTabla tempTabla = Gramatica.leeGramatica(grammar);
		for (int i = 0 ; i < tempTabla.size() ; i++) {
			nonTerminals.add(tempTabla.get(i).getNoTerminal());
		}
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
	public String rule11 () {
		String str = new String ();
		
		return str;
	}
	public String rule12 () {
		String str = new String ();
		
		return str;
	}
	public String rule13 () {
		String str = new String ();
		
		return str;
	}
	public String rule14 () {
		String str = new String ();
		
		return str;
	}
	public String rule15 () {
		String str = new String ();
		
		return str;
	}
	public String rule16 () {
		String str = new String ();
		
		return str;
	}
	public String rule17 () {
		String str = new String ();
		
		return str;
	}
	public String rule18 () {
		String str = new String ();
		
		return str;
	}
	public String rule19 () {
		String str = new String ();
		
		return str;
	}
	public String rule20 () {
		String str = new String ();
		
		return str;
	}
	public String rule21 () {
		String str = new String ();
		
		return str;
	}
	public String rule22 () {
		String str = new String ();
		
		return str;
	}
	public String rule23 () {
		String str = new String ();
		
		return str;
	}
	public String rule24 () {
		String str = new String ();
		
		return str;
	}
	public String rule25 () {
		String str = new String ();
		
		return str;
	}
	public String rule26 () {
		String str = new String ();
		
		return str;
	}
	public String rule27 () {
		String str = new String ();
		
		return str;
	}
	public String rule28 () {
		String str = new String ();
		
		return str;
	}
	public String rule29 () {
		String str = new String ();
		
		return str;
	}
	public String rule30 () {
		String str = new String ();
		
		return str;
	}
	public String rule31 () {
		String str = new String ();
		
		return str;
	}
	public String rule32 () {
		String str = new String ();
		
		return str;
	}
	public String rule33 () {
		String str = new String ();
		
		return str;
	}
	public String rule34 () {
		String str = new String ();
		
		return str;
	}
	public String rule35 () {
		String str = new String ();
		
		return str;
	}
	public String rule36 () {
		String str = new String ();
		
		return str;
	}
	public String rule37 () {
		String str = new String ();
		
		return str;
	}
	public String rule38 () {
		String str = new String ();
		
		return str;
	}
	public String rule39 () {
		String str = new String ();
		
		return str;
	}
	public String rule40 () {
		String str = new String ();
		
		return str;
	}
	public String rule41 () {
		String str = new String ();
		
		return str;
	}
	public String rule42 () {
		String str = new String ();
		
		return str;
	}
	public String rule43 () {
		String str = new String ();
		
		return str;
	}
	public String rule44 () {
		String str = new String ();
		
		return str;
	}
	public String rule45 () {
		String str = new String ();
		
		return str;
	}
	public String rule46 () {
		String str = new String ();
		
		return str;
	}
	public String rule47 () {
		String str = new String ();
		
		return str;
	}
	public String rule48 () {
		String str = new String ();
		
		return str;
	}
	public String rule49 () {
		String str = new String ();
		
		return str;
	}
	public String rule50 () {
		String str = new String ();
		
		return str;
	}
	public String rule51 () {
		String str = new String ();
		
		return str;
	}
	public String rule52 () {
		String str = new String ();
		
		return str;
	}
	public String rule53 () {
		String str = new String ();
		
		return str;
	}
	public String rule54 () {
		String str = new String ();
		
		return str;
	}
	public String rule55 () {
		String str = new String ();
		
		return str;
	}
	public String rule56 () {
		String str = new String ();
		
		return str;
	}
	public String rule57 () {
		String str = new String ();
		
		return str;
	}
	public String rule58 () {
		String str = new String ();
		
		return str;
	}
	public String rule59 () {
		String str = new String ();
		
		return str;
	}
	public String rule60 () {
		String str = new String ();
		
		return str;
	}
	public String rule61 () {
		String str = new String ();
		
		return str;
	}
	public String rule62 () {
		String str = new String ();
		
		return str;
	}
	public String rule63 () {
		String str = new String ();
		
		return str;
	}
	public String rule64 () {
		String str = new String ();
		
		return str;
	}
	public String rule65 () {
		String str = new String ();
		
		return str;
	}
	public String rule66 () {
		String str = new String ();
		
		return str;
	}
	public String rule67 () {
		String str = new String ();
		
		return str;
	}
	public String rule68 () {
		String str = new String ();
		
		return str;
	}
	public String rule69 () {
		String str = new String ();
		
		return str;
	}
	public String rule70 () {
		String str = new String ();
		
		return str;
	}
	public String rule71 () {
		String str = new String ();
		
		return str;
	}
	public String rule72 () {
		String str = new String ();
		
		return str;
	}
	public String rule73 () {
		String str = new String ();
		
		return str;
	}
	public String rule74 () {
		String str = new String ();
		
		return str;
	}
	public String rule75 () {
		String str = new String ();
		
		return str;
	}
	public String rule76 () {
		String str = new String ();
		
		return str;
	}
	public String rule77 () {
		String str = new String ();
		
		return str;
	}
	public String rule78 () {
		String str = new String ();
		
		return str;
	}
	public String rule79 () {
		String str = new String ();
		
		return str;
	}
	public String rule80 () {
		String str = new String ();
		
		return str;
	}
	public String rule81 () {
		String str = new String ();
		
		return str;
	}
	public String rule82 () {
		String str = new String ();
		
		return str;
	}
	public String rule83 () {
		String str = new String ();
		
		return str;
	}
	public String rule84 () {
		String str = new String ();
		
		return str;
	}
	public String rule85 () {
		String str = new String ();
		
		return str;
	}
	public String rule86 () {
		String str = new String ();
		
		return str;
	}
	public String rule87 () {
		String str = new String ();
		
		return str;
	}
	public String rule88 () {
		String str = new String ();
		
		return str;
	}
	public String rule89 () {
		String str = new String ();
		
		return str;
	}
	public String rule90 () {
		String str = new String ();
		
		return str;
	}
	public String rule91 () {
		String str = new String ();
		
		return str;
	}
	public String rule92 () {
		String str = new String ();
		
		return str;
	}
	public String rule93 () {
		String str = new String ();
		
		return str;
	}
	public String rule94 () {
		String str = new String ();
		
		return str;
	}
	public String rule95 () {
		String str = new String ();
		
		return str;
	}
	public String rule96 () {
		String str = new String ();
		
		return str;
	}
	public String rule97 () {
		String str = new String ();
		
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
