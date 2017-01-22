package lexico;

import java.util.*;

public class TiraTokens {
	
	private String tipoTabla;
	private Vector <Token> vectorToken;
	
public TiraTokens(String tabla){
	tipoTabla = tabla;
	vectorToken=new Vector <Token>();
	vectorToken.trimToSize();
}
public TiraTokens(Token t, String tabla){
	tipoTabla = tabla;
	vectorToken=new Vector <Token>();
	vectorToken.trimToSize();
	insertar(t);
}
public void insertar(Token t){
	vectorToken.addElement(t);
}

 public void eliminar( 	Token t){
	 for(int i=0;i<vectorToken.size();i++)
		 if(vectorToken.get(i).equals(t))
			 vectorToken.removeElementAt(i);	 
 }
 
 public void eliminar(int ind){
			 vectorToken.removeElementAt(ind);	 
 }
 public boolean isNull(){
	 boolean flag=false;
	 if (vectorToken.size()==0)
		 flag=true;
	 return flag;
 }
 
 public void setVector(Vector <Token> v){
	 vectorToken=v;
 }
 public Vector <Token> getVector(){
	 return vectorToken;
 }
 
 public Token getToken (int index) {
	 return new Token (vectorToken.get(index));
 }
 
 public int size(){
	 return vectorToken.size();
 }
 
 public String toString(){
	 	String string = new String ();
	 	if (tipoTabla.equals("tokens")) {
	 		string="Tira de tokens\r\nLínea\tToken\tLexema\r\n";
	 		for(int i=0;i<vectorToken.size();i++)
				string=string.concat(vectorToken.get(i).toString()+"\r\n");
	 	}
	 	if (tipoTabla.equals("errores")) {
	 		string="Tabla de errores\r\nLínea\tDescripción\r\n";
	 		for(int i=0;i<vectorToken.size();i++)
				string=string.concat(vectorToken.get(i).toString()+"\r\n");
	 	}
	 	if (tipoTabla.equals("simbolos")) {
	 		string="Tabla de símbolos\r\nID\tLínea\tNombre\r\n";
	 		for(int i=0;i<vectorToken.size();i++) {
	 			string = string.concat(vectorToken.get(i).getId() + "\t");
				string=string.concat(vectorToken.get(i).toString()+"\r\n");
	 		}
	 	}
		
		return string;
	}
}
