package herramientas;

import java.util.ArrayList;
/*
 * La clase PSTabla representa una gram�tica completa. Su estructura est� representada por dos atributos: tipoTabla y vector
 * tipoTabla: Es el nombre con el que se identifica la tabla, ya que no solo se ocupa para gram�ticas. Tambi�n afecta en la manera
 * 			  en la que la tabla se representa como string. Este atributo es de tipo string y es nesesario para todos los constructores.
 * vector: Contie todas las PSTupla que representar�n la gram�tica completa, ya que cada PSTupla representa una regla de producci�n.
 * @author: Exe
 * @version: Colecci�n Can�nica
 */
public class PSTabla {
	public String tipoTabla;
	private ArrayList <PSTupla> vector;
	/*
	 * Constructor que recibe una PSTapla para crear una nueva tabla con las mismas caracter�sticas que recibe como par�metro.
	 */
    public PSTabla (PSTabla tab){
        tipoTabla = new String (tab.tipoTabla);
        vector = new ArrayList <PSTupla> (tab.getVector());
    }
    /*
     * Constructor con string que indica el tipo de tabla que ser�. Se puede enviar cualquier nombre.
     */
	public PSTabla (String tipo) {
		tipoTabla = tipo;
		vector = new ArrayList <PSTupla> ();
	}
	/*
	 * Retorna la tupla que existe en el arreglo en la posici�n indicada por el index.
	 */
	public PSTupla get (int index) {
		return new PSTupla (vector.get(index));
	}
    /*
     * Retorna la primera PSTupla que se encuentra en el vector con el noTerminal indicado por la entrada.
     * @author: Liz Velia
     */
    public PSTupla getNT (String entrada) {
         for(int i=0;i<vector.size();i++){
             if(vector.get(i).getNoTerminal().toString().equals(entrada.toString()))
             return (vector.get(i));
            }
         return new PSTupla (vector.get(0));
	}
    /*
     * Retorna la primera PSTupla que en su noTerminal tenga un s�mbolo terminal, indicado por su entrada.
     * @Liz Velia  
     */
    public PSTupla getT (String entra) {
        for(int i=0;i<vector.size();i++){
            if(vector.get(i).noTerminal.toString().equals(entra.toString()))
            return (vector.get(i));
        }
        return new PSTupla (vector.get(0));
	}
	/*
	 * Retorna la cantidad de PSTupla que existen en el vector.
	 */
	public int size() {
		return vector.size();
	}
	/*
	 * Retorna una copia del vector que contiene las PSTuplas
	 */
	public ArrayList <PSTupla> getVector () {
		return new ArrayList <PSTupla> (vector);
	}
	/*
	 * Se destruye el vector de PSTupla actual y se sustituye por un nuevo vector indicado por el par�metro de entrada.
	 */
	public void setVector (ArrayList <PSTupla> vectorTupla) {
		vector = new ArrayList <PSTupla> (vectorTupla);
	}
	/*
	 * Busca en la gram�tica si existe el noTerminal indicado en el par�metro. En el caso de encontrar el noTerminal dentro de la gram�tica,
	 * se retorna verdadero. En caso de no encontrar el simbolo ingresado como noTerminal, se retornar� un false.
	 */
	public boolean existeNoTerminal (String str) {
		boolean flag = false;
		if (vector.size() > 0) {
			for (int i = 0 ; i < vector.size() && !flag; i++) {
				if (vector.get(i).noTerminal.equals(str))
					flag = true;
			}
		}
		return flag;
	}
	/*
	 * Se agrega una nueva PSTupla al vector (si se usa para gram�tica, esta acci�n significa ingresar una nueva regla de producci�n).
	 */
	public void add (PSTupla tupla) {
		vector.add(tupla);
	}
	/*
	 * Se sustituye una PSTupla existente en el vector, en la posici�n indicada por el index.
	 */
	public void setTupla (PSTupla tupla, int index) {
		vector.set(index, tupla);
	}
	/*
	 * Se sustituye el noTerminal de la PSTupla existente en el vector, en la posici�n indicada por el index.
	 */
	public void setTupla (String noTerminal, int index) {
		PSTupla tupla = new PSTupla (noTerminal, vector.get(index).getConjunto());
		vector.set(index, tupla);
	}
	/*
	 * Se sustituye el noTerminal y el conjunto de la PSTupla existente en el vector, en la posici�n indicada por el index.
	 */
	public void setTupla (String noTerminal, ArrayList <String> conjunto, int index) {
		PSTupla tupla = new PSTupla (noTerminal, conjunto);
		vector.set(index, tupla);
	}
	/*
	 * Agrega un nuevo elemento al conjunto de una PSTupla existente en el vector, en la posici�n indicada por indexOfTupla.
	 */
	public void addElementToTupla (String element, int indexOfTupla) {
		PSTupla tupla = new PSTupla (vector.get(indexOfTupla).noTerminal);
		tupla.add(element);
		vector.set(indexOfTupla, tupla);
	}
	public String toString () {
		String string = new String ();
		if (!tipoTabla.equals("Gramatica"))
			string = "Tabla de " + tipoTabla + "\r\n";
		else
			string = "Tabla de Gram�tica\r\n";
		for (int i = 0 ; i < vector.size() ; i++) {
			switch (tipoTabla) {
			case "Primeros":
				string = string.concat("P" + vector.get(i).toString() + "\r\n");
				break;
			case "Siguientes":
				string = string.concat("S" + vector.get(i).toString() + "\r\n");
				break;
			case "Gramatica":
				string = string.concat(get(i).noTerminal + " -> ");
				for (int j = 0 ; j < get(i).getConjunto().size() ; j++) {
					string = string.concat(get(i).getConjunto().get(j) + " ");
				}
				string = string.concat("\r\n");
				break;
			default:
				string = string.concat(vector.get(i).toString() + "\r\n");
			}
				
		}
		string = string.concat("\r\n");
		return string;
	}
}
