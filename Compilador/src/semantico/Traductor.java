package semantico;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import herramientas.Utility;
import lexico.Token;
import sintactico.AnalizadorSintactico;
import sintactico.Atributo;

public class Traductor {
	private Map <Integer, Atributo[]> history;
	private AnalizadorSintactico pilaAS;
	private ASPython acciones;
	private Stack <Token> entradaFull;
	private String salida;
	private Stack <Token> temp;
	private boolean succes = false;
	public Traductor (Vector <String> prog, Vector <String> vectorReservadas, Vector <String> vectorSimbolos, String grammar) {
		acciones = new ASPython (grammar);
		pilaAS = new AnalizadorSintactico (prog, vectorReservadas, vectorSimbolos, grammar);
		history = pilaAS.getPointerHistorial();
		entradaFull = pilaAS.getEntradaFull();
		temp = pilaAS.getEntradaFull();
		if (pilaAS.getSuccesStatus())
			start();
		else {
			String message = "No se puede traducir debido a un error sint\u00E1ctico.";
			JFrame container = new JFrame ("container");
			System.out.println(message);
			JOptionPane.showMessageDialog(container, message, "Estado de compilaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void start() {
		Atributo pilaField;
		Atributo entradaField;
		Atributo salidaField;
		Integer index, rule = new Integer (0);
		ArrayList <String> numArray;
		String pilaStrStream;
		
		for (Map.Entry<Integer, Atributo[]> historial : history.entrySet()) {
			index = historial.getKey();
			pilaField = historial.getValue()[0];
			entradaField = new Atributo ();
			salidaField = historial.getValue()[2];
			//Pila stream mod
			numArray = Utility.tokenizeNumbersFromString(pilaField.getName());
			numArray.remove(0);
			pilaStrStream = "0";
			//System.out.println(numArray);
			//Entrada stream mod
			String str = " ";
			for (int i = temp.size() - 1 ; i >= 0 ; i--) {
				String token = temp.get(i).getToken();	
				if (token.equals("id") || token.equals("nint"))
					str += token + "." + temp.get(i).getLexema();
				else
					str += token;
				str += " ";
			}
			for (int i = 0 ; i < numArray.size() ; i++) {
				if (i < acciones.getTempStack().size())
					if (isIgnorable(acciones.getTempStack().get(i).getName()))
						pilaStrStream += acciones.getTempStack().get(i).getName();
					else
						pilaStrStream += acciones.getTempStack().get(i).getName() + ".\"" + acciones.getTempStack().get(i).getTrad() + "\"";
				pilaStrStream += numArray.get(i);
			}
			entradaField.setName(str);
			if (salidaField.getName().startsWith("R")) {
				rule = Integer.parseInt(getRule(salidaField.getName()));
				switch (rule) {
				case 1:
					salidaField.setTrad(acciones.rule1());
					salida = salidaField.getTrad();
					succes = true;
					break;
				case 2:
					salidaField.setTrad(acciones.rule2());
					break;
				case 3:
					salidaField.setTrad(acciones.rule3());
					break;
				case 4:
					salidaField.setTrad(acciones.rule4());
					break;
				case 5:
					salidaField.setTrad(acciones.rule5());
					break;
				case 6:
					salidaField.setTrad(acciones.rule6());
					break;
				case 7:
					salidaField.setTrad(acciones.rule7());
					break;
				case 8:
					salidaField.setTrad(acciones.rule8());
					break;
				case 9:
					salidaField.setTrad(acciones.rule9());
					break;
				case 10:
					salidaField.setTrad(acciones.rule10());
					break;
				case 11:
					salidaField.setTrad(acciones.rule11());
					break;
				case 12:
					salidaField.setTrad(acciones.rule12());
					break;
				case 13:
					salidaField.setTrad(acciones.rule13());
					break;
				case 14:
					salidaField.setTrad(acciones.rule14());
					break;
				case 15:
					salidaField.setTrad(acciones.rule15());
					break;
				case 16:
					salidaField.setTrad(acciones.rule16());
					break;
				case 17:
					salidaField.setTrad(acciones.rule17());
					break;
				case 18:
					salidaField.setTrad(acciones.rule18());
					break;
				case 19:
					salidaField.setTrad(acciones.rule19());
					break;
				case 20:
					salidaField.setTrad(acciones.rule20());
					break;
				case 21:
					salidaField.setTrad(acciones.rule21());
					break;
				case 22:
					salidaField.setTrad(acciones.rule22());
					break;
				case 23:
					salidaField.setTrad(acciones.rule23());
					break;
				case 24:
					salidaField.setTrad(acciones.rule24());
					break;
				case 25:
					salidaField.setTrad(acciones.rule25());
					break;
				case 26:
					salidaField.setTrad(acciones.rule26());
					break;
				case 27:
					salidaField.setTrad(acciones.rule27());
					break;
				case 28:
					salidaField.setTrad(acciones.rule28());
					break;
				case 29:
					salidaField.setTrad(acciones.rule29());
					break;
				case 30:
					salidaField.setTrad(acciones.rule30());
					break;
				case 31:
					salidaField.setTrad(acciones.rule31());
					break;
				case 32:
					salidaField.setTrad(acciones.rule32());
					break;
				case 33:
					salidaField.setTrad(acciones.rule33());
					break;
				case 34:
					salidaField.setTrad(acciones.rule34());
					break;
				case 35:
					salidaField.setTrad(acciones.rule35());
					break;
				case 36:
					salidaField.setTrad(acciones.rule36());
					break;
				case 37:
					salidaField.setTrad(acciones.rule37());
					break;
				case 38:
					salidaField.setTrad(acciones.rule38());
					break;
				case 39:
					salidaField.setTrad(acciones.rule39());
					break;
				case 40:
					salidaField.setTrad(acciones.rule40());
					break;
				case 41:
					salidaField.setTrad(acciones.rule41());
					break;
				case 42:
					salidaField.setTrad(acciones.rule42());
					break;
				case 43:
					salidaField.setTrad(acciones.rule43());
					break;
				case 44:
					salidaField.setTrad(acciones.rule44());
					break;
				case 45:
					salidaField.setTrad(acciones.rule45());
					break;
				case 46:
					salidaField.setTrad(acciones.rule46());
					break;
				case 47:
					salidaField.setTrad(acciones.rule47());
					break;
				case 48:
					salidaField.setTrad(acciones.rule48());
					break;
				case 49:
					salidaField.setTrad(acciones.rule49());
					break;
				case 50:
					salidaField.setTrad(acciones.rule50());
					break;
				case 51:
					salidaField.setTrad(acciones.rule51());
					break;
				case 52:
					salidaField.setTrad(acciones.rule52());
					break;
				case 53:
					salidaField.setTrad(acciones.rule53());
					break;
				case 54:
					salidaField.setTrad(acciones.rule54());
					break;
				case 55:
					salidaField.setTrad(acciones.rule55());
					break;
				case 56:
					salidaField.setTrad(acciones.rule56());
					break;
				case 57:
					salidaField.setTrad(acciones.rule57());
					break;
				case 58:
					salidaField.setTrad(acciones.rule58());
					break;
				case 59:
					salidaField.setTrad(acciones.rule59());
					break;
				case 60:
					salidaField.setTrad(acciones.rule60());
					break;
				case 61:
					salidaField.setTrad(acciones.rule61());
					break;
				case 62:
					salidaField.setTrad(acciones.rule62());
					break;
				case 63:
					salidaField.setTrad(acciones.rule63());
					break;
				case 64:
					salidaField.setTrad(acciones.rule64());
					break;
				case 65:
					salidaField.setTrad(acciones.rule65());
					break;
				case 66:
					salidaField.setTrad(acciones.rule66());
					break;
				case 67:
					salidaField.setTrad(acciones.rule67());
					break;
				case 68:
					salidaField.setTrad(acciones.rule68());
					break;
				case 69:
					salidaField.setTrad(acciones.rule69());
					break;
				case 70:
					salidaField.setTrad(acciones.rule70());
					break;
				case 71:
					salidaField.setTrad(acciones.rule71());
					break;
				case 72:
					salidaField.setTrad(acciones.rule72());
					break;
				case 73:
					salidaField.setTrad(acciones.rule73());
					break;
				case 74:
					salidaField.setTrad(acciones.rule74());
					break;
				case 75:
					salidaField.setTrad(acciones.rule75());
					break;
				case 76:
					salidaField.setTrad(acciones.rule76());
					break;
				case 77:
					salidaField.setTrad(acciones.rule77());
					break;
				case 78:
					salidaField.setTrad(acciones.rule78());
					break;
				case 79:
					salidaField.setTrad(acciones.rule79());
					break;
				case 80:
					salidaField.setTrad(acciones.rule80());
					break;
				case 81:
					salidaField.setTrad(acciones.rule81());
					break;
				case 82:
					salidaField.setTrad(acciones.rule82());
					break;
				case 83:
					salidaField.setTrad(acciones.rule83());
					break;
				case 84:
					salidaField.setTrad(acciones.rule84());
					break;
				case 85:
					salidaField.setTrad(acciones.rule85());
					break;
				case 86:
					salidaField.setTrad(acciones.rule86());
					break;
				case 87:
					salidaField.setTrad(acciones.rule87());
					break;
				case 88:
					salidaField.setTrad(acciones.rule88());
					break;
				case 89:
					salidaField.setTrad(acciones.rule89());
					break;
				case 90:
					salidaField.setTrad(acciones.rule90());
					break;
				case 91:
					salidaField.setTrad(acciones.rule91());
					break;
				case 92:
					salidaField.setTrad(acciones.rule92());
					break;
				case 93:
					salidaField.setTrad(acciones.rule93());
					break;
				case 94:
					salidaField.setTrad(acciones.rule94());
					break;
				case 95:
					salidaField.setTrad(acciones.rule95());
					break;
				case 96:
					salidaField.setTrad(acciones.rule96());
					break;
				case 97:
					salidaField.setTrad(acciones.rule97());
					break;
				}
				
				//System.out.println("Rule to execute -> " + rule + "  " + acciones);
				//salidaField.setName("R" + rule + "  {" + nonTerminals.get(rule - 1) + ".trad:= \"" + salidaField.getTrad() + "\"}");
			} else if (salidaField.getName().startsWith("D")) {
				acciones.push(new Atributo (entradaFull.pop()));
				temp.pop();
				//System.out.println("Displacement -> " + getRule(salidaField.getName()) + "  " + acciones);
			}
			pilaField.setName(pilaStrStream);
			history.put(index, new Atributo [] {pilaField, entradaField, salidaField});
		}
	}
	public void showTable () {
		pilaAS.showTable();
	}
	public String getTraduccion () {
		return salida;
	}
	private boolean isIgnorable (String str) {
		boolean flag = false;
		if (str.equals("int") || str.equals("float") || str.equals("char") || str.equals("[") || str.equals("]") || str.equals("*") || str.equals(","))
			flag = true;
		return flag;
	}
	public boolean isSuccesfulTrad () {
		return succes;
	}
	private String getRule (String field) {
		String str = new String ();
		boolean flag = true;
		for (int i = 1 ; i < field.length() && flag ; i++) {
			if (field.charAt(i) != ' ')
				str += field.charAt(i);
			else
				flag = false;
		}
		return str;
	}
}
