package lexico;

public class Token {
private int id;
private int linea;
private String token;
private String lexema;

public Token(){
	id=-1;
	linea=-1;
	token="";
	lexema="" ;
}
public Token(Token token){
	this(token.id,token.linea, token.token, token.lexema);
}
public Token(int id,int linea,String token, String lexema){
	setId(id);
	setLinea(linea);
	setToken(token);
	setLexema(lexema);
}

public Token (String token, String lexema) {
	this (-1, -1, token, lexema);
}

public void setId(int i){this.id=i;}
public void setLinea(int l){this.linea=l;}
public void setToken(String s){this.token= s;}
public void setLexema(String lex){this.lexema=lex;}

public int getId(){return id;}
public int getLinea(){return linea;}
public String getToken(){return token;}
public String getLexema(){return lexema;}

public boolean isNull(){
	boolean flag=false;
	if(id==-1 && linea==-1)
		flag=true;
	return flag;
}

public String toString(){
	return linea+"\t"+token+"\t"+lexema;
}
}
