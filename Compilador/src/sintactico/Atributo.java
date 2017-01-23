package sintactico;

import lexico.Token;

/*
 * Estructura para la pila
 */
public class Atributo {
	private String name;
	private String trad;
	private String temp = "[Default temp]";
	
	public Atributo () {
		name = new String ();
		trad = new String ();
	}
	
	public Atributo (String name, String trad) {
		this.name = name;
		this.trad = trad;
	}
	
	public Atributo (String name) {
		this (name, "");
	}
	
	public Atributo (Token lexicToken) {
		this (lexicToken.getToken(), lexicToken.getLexema());
	}
	public Atributo (Atributo atrib) {
		this (atrib.name, atrib.trad, atrib.temp);
	}
	public Atributo (String name, String trad, String tmp) {
		this.name = name;
		this.trad = trad;
		temp = tmp;
	}
	/*
	 * Getters & setters
	 */
	public void setName (String name) {
		this.name = name;
	}
	public void setTrad (String trad) {
		this.trad = trad;
	}
	public String getName () {
		return new String (name);
	}
	public String getTrad () {
		return new String (trad);
	}
	public void setTemp (String t) {
		temp = t;
	}
	public String getTemp () {
		return temp;
	}
	@Override
	public String toString () {
		String str = "(" + name;
		if (!trad.equals("") && trad != null)
			str += " | " + trad;
		if (!temp.equals("[Default temp]"))
			str += " $ " + temp;
		str += ")";
		//str = "(" + name + " | " + trad + "|" + temp + ")";
		return str;
		//return "(" + name + " | " + trad + ")";
	}
}