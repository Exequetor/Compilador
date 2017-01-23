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
	//private String patchType = "null";
	private Integer tabsNum = 0;
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
		Atributo temp, bloque_sentencias = null;
		for (int i = 0 ; i < 3 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("bloque_sentencias"))
				bloque_sentencias = new Atributo (temp);
		}
		str = bloque_sentencias.getTrad();
		temporales.push(new Atributo ("programa", str));
		return str;
	}
	public String rule2 () {
		String str = new String ();
		Atributo temp, lista_sentencias = null;
		for (int i = 0 ; i < 3 ; i++) {
			temp = temporales.pop();
			if (temp.getName().equals("lista_sentencias"))
				lista_sentencias = new Atributo (temp);
		}
		str = lista_sentencias.getTrad();
		temporales.push(new Atributo ("bloque_sentencias", str));
		return str;
	}
	public String rule3 () {
		String str = new String ();
		for (int i = 0 ; i < 5 ; i++) {
			temporales.pop();
		}
		str = Eps();
		temporales.push(new Atributo ("includes", Eps()));
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
		
		return str;
	}
	public String rule5 () {
		String str = new String ();
		for (int i = 0 ; i < 4 ; i++) {
			temporales.pop();
		}
		str = Eps();
		temporales.push(new Atributo ("header"));
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
		
		return str;
	}
	public String rule7 () {
		String str = new String ();
		int tabs = tabsNum;
		Atributo temp, literal = null, more_args = null, lista_sentencias = null, printf = null;
		for (int i = 0 ; i < 6 ; i++) {
			temp = temporales.pop();
			switch (temp.getName()) {
			case "printf":
				printf = new Atributo (temp);
				break;
			case "literal":
				literal = new Atributo (temp);
				break;
			case "more_args":
				more_args = new Atributo (temp);
				break;
			case "lista_sentencias":
				lista_sentencias = new Atributo (temp);
			}
		}
		while (tabs-- > 0)
			str += "\n";
		str += "print(" + literal.getTrad() + more_args.getTrad() + ")\n" + lista_sentencias.getTrad();
		temporales.push(new Atributo ("lista_sentencias", str));
		return str;
	}
	public String rule8 () {
		String str = new String ();
		
		return str;
	}
	public String rule9 () {
		String str = new String ();
		
		return str;
	}
	public String rule10 () {
		String str = new String ();
		
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
		str = Eps();
		temporales.push(new Atributo ("lista_sentencias", str));
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
		str = Eps();
		temporales.push(new Atributo ("more_args", str));
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
