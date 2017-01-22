package sintactico;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import herramientas.Gramatica;
import herramientas.PSTabla;
import lexico.TiraTokens;
import lexico.Token;
import lexico.Tokenizer;

public class AnalizadorSintactico {
	private int n=0;
	private static final String PESOS = "$";				//Constante que define el s�mbolo de pesos ("$").
	private PSTabla gramatica;						//Objeto que contendr� la gram�tica.
	private Tokenizer analizadorLexico;				//Objeto que hace el an�lisis l�xico de un programa y genera una tira de tokens.
	private TiraTokens tiraTokens;					//Objeto que contiene la tira de tokens generado por el analizador l�xico.
	//private ColeccionCanonica canonica;				//Objeto que contiene la informaci�n de la colecci�n can�nica.
	//private ArrayList <EstadosCanonica> coleccionCanonica; //Contiene los estados de la coleccion can�nica.
	private TAS tablaSintactico;					//Objeto que contiene la informaci�n de la TAS.
	private Stack <Token> entrada;					/*Objeto tipo pila que contiene los tokens contenidos en la tira de tokens m�s "$".
													La pila se inicializa con el primer token en el top de la pila y con "$" en el
												    fondo de la pula. Esto es para poder hacer un uso m�s natural del push y pop.*/
	private boolean succes = false;
	private boolean flag=false,flag2=false;
	private String[][]  mat;
	private Stack <Atributo> pila;				//Pila que reperesentar� la pila del an�lisis sint�ctico.
	private Stack <Token> entradaFull;
	private Map<Integer, Atributo[]> historial;		//(TEMP) Mapa que contiene el historial de las operaciones del algoritmo.
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	@SuppressWarnings("unchecked")
	public AnalizadorSintactico (Vector <String> prog, Vector <String> vectorReservadas, Vector <String> vectorSimbolos, String exp) {
		gramatica = Gramatica.leeGramatica(exp); //Se lee la gram�tica
		analizadorLexico = new Tokenizer (prog, vectorReservadas, vectorSimbolos); //Se crea el bojeto del analizador l�xico.
		//canonica = new ColeccionCanonica (exp); //Se calcula la colecci�n can�nica
		//coleccionCanonica = new ArrayList <EstadosCanonica> (canonica.coleccionCanonica); //Se extrae la informaci�n de los estados de la colecci�n can�nica
		tablaSintactico = new TAS (exp); //Se crea la instancia del objeto y se genera la tabla de an�lisis sint�ctico.
		analizadorLexico.getTiraTokens(); //Se genera la tira de tokens del an�lisis l�xico.
		tiraTokens = analizadorLexico.getTira(); //Se obtiene la tira de tokens del analizador l�xico.
		entrada = new Stack <Token> (); //Se hace la instancia de una nueva pila para la entrada.
		pila = new Stack <Atributo> (); //Se hace la instancia de una nueva pila para la pila del an�lisis sint�ctico.
		historial = new HashMap<Integer, Atributo[]> ();
		pila.add(new Atributo ("0")); //Se inserta el primer elemento de la pila-
		entrada.add(new Token (PESOS, PESOS));
		//System.out.println(tablaSintactico);
		for (int i = tiraTokens.size() - 1 ; i >= 0 ; i--) { //For que insertar� a la pila los tokens de la tira de tokens.
			entrada.add(tiraTokens.getToken(i)); //Se a�ade el token que est� en la posici�n i de la tira de tokens.
		}
		entradaFull = (Stack <Token>) entrada.clone();
		//System.out.println(gramatica.get(3).noTerminal +"\t"+gramatica.get(3).getConjunto());
		//historial.put(0, new String[] {getPilaString(), getEntradaString(), "ola k ase"}); //Se ingesa la primera operaci�n al historial para poder imprimirse.
		//busca_accion("id","10");
		//System.out.println(tiraTokens);
		for (int k=0;!flag;k++){
			n=k;
			//System.out.println(pila);
			String a=pila.peek().getName();
			String b=entrada.peek().getToken();
			String aux="",aux2="";
			String num="";
			//System.out.println(b + " " + a);
			aux=busca_accion(b,a);
			//System.out.println(a+"\t"+b+"\t"+aux);
			if(aux.startsWith("D"))
				aux2="D";
			if(aux.startsWith("R"))
				aux2="R";
			if(aux.startsWith("A"))
				aux2="A";
			if(aux.startsWith("~"))
				aux2="outro";
			
			switch(aux2){
			case "D":
				historial.put(k, new Atributo[] {new Atributo (getPilaString()), new Atributo (getEntradaString()), new Atributo (aux)}); //Se ingresa la primera operaci�n al historial para poder imprimirse.
				//flag=true;
				Token tokenTemp = entrada.pop();
				pila.push(new Atributo (tokenTemp.getToken(), tokenTemp.getLexema()));
				for(int m=1;m<aux.length();m++)
					num+=aux.charAt(m);
				//System.out.println(num+"\t");
				pila.push(new Atributo (num));
				break;
			case "R":
				String ruleStr = new String ();
				int ruleNum;
				for (int i = 1 ; i < aux.length(); i++) {
						ruleStr += aux.charAt(i);
				}
				ruleNum = Integer.parseInt(ruleStr);
				historial.put(k, new Atributo[] {new Atributo (getPilaString()), new Atributo (getEntradaString()), new Atributo (aux+"   \t   "+gramatica.get(ruleNum-1).getNoTerminal()+"->"+gramatica.get(ruleNum-1).strConjuntoNonComa())});
				int tam=2*gramatica.get(ruleNum - 1).getConjunto().size();
				if(gramatica.get(ruleNum - 1).getConjunto().contains("Eps") && gramatica.get(ruleNum - 1).getConjunto().size()==1){
					tam=0;
				}
				for(int m=0;m<tam;m++)
					pila.pop();
				String ant=pila.peek().getName();
				int x = ruleNum - 1;
				pila.push(new Atributo (gramatica.get(x).getNoTerminal()));
				String nue=pila.peek().getName();
				pila.push(new Atributo (busca_accion(nue,ant)));
				//flag=true;
				break;
			case "A":
				historial.put(k, new Atributo[] {new Atributo (getPilaString()), new Atributo (getEntradaString()), new Atributo (aux)});
				succes = true;
				flag=true;
				break;
			default:{
				flag=true;
				flag2=true;}
			}			
		}
		
		if(flag2){
			String pop="",esperados="";
			mat=tablaSintactico.tabla_matriz();
			//System.out.print("Error de Sintaxis, se esperaba: ");
			for(int i=1;i<mat[0].length;i++){
				//System.out.println(mat[Integer.parseInt(pila.peek())+1][i]);
				pop=mat[Integer.parseInt(pila.peek().getName())+1][i];
				if(!pop.equals("~") && Gramatica.retornaTerminales(gramatica).contains(mat[0][i])) {
					switch (mat[0][i]) {
					case "nint":
						esperados += "entero ";
						break;
					case "nfloat":
						esperados += "flotante ";
						break;
					default:
						esperados += mat[0][i] + " ";
					}
				}
			}
			historial.put(n, new Atributo[] {new Atributo (getPilaString()), new Atributo (getEntradaString()), new Atributo ("Error de Sintaxis, se esperaba: "+esperados)});
		}
		
	}
	
	public String getPilaString () {				//Esta m�todo crea el string que representa la pila acual.
		String str = new String ();
		for (int i = 0 ; i < pila.size() ; i++) {	//For para recorrer toda la pila actual.
			str = str + new String (pila.get(i).getName());	//Se concatenan los elementos de la pila.
		}
		return str;
	}
	
	public String getEntradaString () {					//Este m�todo crea el string de la pila de la entrada actual.
		String str = new String ();
		for (int i = entrada.size() - 1 ; i >= 0 ; i--) {	//For para recorrer toda la pila de la entrada.
			str = str + new String (entrada.get(i).getToken()) + " ";	//Se concatenan los elementos de la pila de la entrada.
		}
		return str;
	}
	//m�todo que busca la acci�n (D#,R#, ACEPTACION o error) entre el tope de la pila y el tope de la entrada
	public String busca_accion(String en,String pi){
		mat=tablaSintactico.tabla_matriz();
		//matrix(mat);
		
		boolean band=false;
		int x=0,y=0;
		for(int i=1;i<mat[0].length && !band;i++){
			if(mat[0][i].equals(en)){
				x=i;
				band=true;
			}
		}
		y=Integer.parseInt(pi)+1;
		//System.out.println("("+y+","+x+")");
		return mat[y][x];
	}
	
	//Imprime la matriz que guarda los datos de la tabla
	public void matrix(String [][] t){
	     for (int i = 0 ; i < tablaSintactico.size_edo()+1; i++) {
	         for (int j = 0 ; j < tablaSintactico.tabla_size() +1 ; j++) {
	            System.out.print(t[i][j]+"\t\t");
	         }
	         System.out.println();
	     }
	}
	
	private void alignRight(JTable table, int column) {
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
	    table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);
	}
	
	/*
	 * No le hagas mucho caso a este m�todo. Este m�todo construye una tabla para mostrarla en lugar de nuestra
	 * t�pida ventana de texto para mostrar la salida. Dejar� este m�todo en visible cuando se ejecute para que
	 * la cheques a ver si te gusta :3 . Dejar� desactivada la ventana que siempre mostramos, a�n as�, est� implementada
	 * en la interfaz, solo quita el comentario (El toString no est� muy bien implementado para la ventana que siempre usamos
	 * por eso decid� hacer la tabla).
	 * Si quieres puedes usar esta tabla con los HashMaps. Si quieres desactivarla ve a la clase VentanaAnalisisSintactico y comenta
	 * La instrucci�n "analizador.showTable();", a�n as�, estar�a nice que mostraramos este algoritmo con esta tabla,
	 * pero eso implica que debes aprender a usar HashMaps, por eso puse el mapa como (TEMP), por si quieres crear
	 * Tu propia estructura, y bueno, tendr�as que modificar el m�todo toString(). Como sea, Good Luck!
	 */
	public void showTable () { 
		JFrame ventanaTabla = new JFrame ("Pila del an\u00e1lisis sint\u00e1ctico");
		JTable tabla;
		JScrollPane scroll;
		String header[] = {"Pila", "Entrada", "Salida"};
		String datos[][] = new String [historial.size()][3];
		//datos[0][0] = "Pila"; datos[0][1] = "Entrada"; datos[0][2] = "Salida";
		for (int i = 0 ; i < historial.size() ; i++)
			for (int j = 0 ; j < 3 ; j++)
				datos[i][j] = historial.get(i)[j].getName();
		tabla = new JTable (datos, header);
		TableColumnModel columnModel = tabla.getColumnModel();
		
		int widthData = 0;
	    for (int column = 0; column < tabla.getColumnCount(); column++) {
	    	int width = 100; // Min width
	    	int maxWidth = 100;
	        for (int row = 0; row < tabla.getRowCount(); row++) {
	            TableCellRenderer renderer = tabla.getCellRenderer(row, column);
	            Component comp = tabla.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	            if (maxWidth < width)
	            	maxWidth = width;
	        }
	        widthData += maxWidth;
	        columnModel.getColumn(column).setPreferredWidth(maxWidth);
	    }
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tabla.setEnabled(false);
	    alignRight(tabla, 1);
	    scroll = new JScrollPane (tabla);
	    scroll.setPreferredSize(new Dimension (widthData + 4, (tabla.getRowCount() + 1) * 16 + 10));
		ventanaTabla.add(scroll);
		ventanaTabla.pack();
		ventanaTabla.setLocation(screenSize.width/2 - (ventanaTabla.getWidth()/2), screenSize.height/2 - (ventanaTabla.getHeight()/2));
		if (ventanaTabla.getHeight() >= screenSize.height)
			ventanaTabla.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//ventanaTabla.setMaximumSize(new Dimension ((int)Constants.SCREEN_SIZE.getWidth(), (int)Constants.SCREEN_SIZE.getHeight()));
		//ventanaTabla.setSize(new Dimension (widthData, (tabla.getRowCount()+1) * 100));
		ventanaTabla.setVisible(true);
	}
	public Map <Integer, Atributo []> getHistorial () {
		return new HashMap <Integer, Atributo []> (historial);
	}
	public Map <Integer, Atributo []> getPointerHistorial () {
		return historial;
	}
	@SuppressWarnings("unchecked")
	public Stack <Token> getEntradaFull () {
		return (Stack <Token>) entradaFull.clone();
	}
	public boolean getSuccesStatus () {
		return succes;
	}
	@Override
	public String toString () {
		String str = new String ();
		str = str + "Pila\t\tEntrada\t\tSalida\n";
		for (Map.Entry<Integer, Atributo[]> historial : historial.entrySet()) {
			for (Atributo campos : historial.getValue())
				str = str + campos.getName() + "\t\t";
			str = str + "\n\r";
		}
		return str;
	}
}
