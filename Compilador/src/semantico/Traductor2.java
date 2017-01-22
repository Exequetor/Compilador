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

public class Traductor2 {
	private Map <Integer, Atributo[]> history;
	private AnalizadorSintactico pilaAS;
	private ASPython acciones;
	private Stack <Token> entradaFull;
	private String salida;
	private Stack <Token> temp;
	private boolean succes = false;
	public Traductor2 (Vector <String> prog, Vector <String> vectorReservadas, Vector <String> vectorSimbolos, String grammar) {
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
	private getPosRulesInStream () {
		for
	}
}
