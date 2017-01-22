package canonico;

import herramientas.PSTabla;
/*
 * Esta clase representa un solo estado de la colección canónica. Su estructura representa el número de estado y su conjunto con sus reglas de
 * producción. Esta clase es una herencia de PSTabla, por lo que se pueden usar los métodos de PSTabla.
 * Los atributos que representan esta estructura son: estado, iEstado, aceptacion, irA.
 * estado: Representa el número de estado.
 * iEstado: Representa el estado de la función Ir_A con el que fue creado el estado actual. Esto es lo siguiente:
 * 			Ir_A (I_j, D) = Este estado		 *donde j es iEstado.
 * aceptacion: Si es falso, el estado no es de aceptación. Si es verdadero, el estao se toma como aceptación.
 * irA: Representa el símbolo con el que se invocó la función IR_A con el que fue creado el estado actual. Esto es lo siguiente:
 * 			Ir_A (I_j, D) = Este estado		 *donde D es irA.
 * @author: Exe
 * @version: Colección Canónica
 */
public class EstadosCanonica extends PSTabla {
	private int estado, iEstado;
	private boolean aceptacion = false;
    private String irA;
	/*
	 * Constructor de copia donde se crea un nuevo objeto con las características del objeto que se envía como parámetro.
	 */
	public EstadosCanonica (EstadosCanonica estado) {
		super(estado.tipoTabla);
                irA = estado.getIrA();
                iEstado = estado.getIEstado();
                aceptacion = estado.getAceptacion();
		this.estado = estado.getEstado();
		setVector(estado.getVector());
	}
	/*
	 * Constructor que debe usarse UNICAMENTE en colección canónica. Este constructor hace que el estado se convierta
	 * automáticamente en aceptación.
	 */
	public EstadosCanonica(String rawStr) {
		super(rawStr);
                iEstado = 1;
                irA = "$";
		this.estado = -1;
	}
	/*
	 * Constructor donde se le envía el número de estado que será.
	 */
	public EstadosCanonica(int estado) {
		super("I_" + estado);
                iEstado = 0;
                irA = new String ();
		this.estado = estado;
	}
	/*
	 * Se sustituye el iEstado actual por el parámetro integer.
	 */
    public void setIEstado (int integer) {
        this.iEstado = integer;
    }
    /*
     * Se retorna el iEstado.
     */
    public int getIEstado () {
        return this.iEstado;
    }
    /*
     * Se sustituye el irA actual por el parámetro str.
     */
    public void setIrA (String str) {
        this.irA = str;
    }
    /*
     * Se retorna el irA.
     */
    public String getIrA () {
        return new String (this.irA);
    }
    /*
     * Se retorna el estado.
     */
	public int getEstado () {
		return estado;
	}
	/*
	 * Se sustituye el estado actual por el parámetro est.
	 */
	public void setEstado (int est) {
            this.tipoTabla = "I_" + est;
            this.estado = est;
	}
	
	/*
	 * Retorna el valor de aceptación.
	 */
	public boolean getAceptacion () {
		return aceptacion;
	}
	/*
	 * Se setea la aceptación por el parametro flag
	 */
	public void setAceptacion (boolean flag) {
		this.aceptacion = flag;
	}
	/*
	 * Compara el estado de referencia con el parámetro de entrada. Si son iguales se retorna un true, si no, false.
	 */
	public boolean equals(EstadosCanonica estado) {
		boolean flag = false;
			if (this.size() == estado.size()) {
				flag = true;
				for (int j = 0 ; j < this.size() && flag; j++) {
					flag = false;
					for (int k = 0 ; k < estado.size() && !flag ; k++) {
						//System.out.println(coleccionCanonica.get(i).getVector().get(j) + " " + estado.getVector().get(k));
						if (this.getVector().get(j).equals(estado.getVector().get(k))) {
							//System.out.println(true);
							flag = true;
						}
					}
				}
			}
		return flag;
	}
	/*
	 * Compara el estado de referencia con el parámetro de entrada pero sin comparar terminales.
	 */
        public boolean equals(EstadosCanonica estado, boolean conTerminal) {
		boolean flag = false;
                    if (conTerminal)
                        flag = equals (estado);
                    else
                        if (this.size() == estado.size()) {
				flag = true;
				for (int j = 0 ; j < this.size() && flag; j++) {
					flag = false;
					for (int k = 0 ; k < estado.size() && !flag ; k++) {
						//System.out.println(coleccionCanonica.get(i).getVector().get(j) + " " + estado.getVector().get(k));
						if (this.getVector().get(j).equals(estado.getVector().get(k), false)) {
							//System.out.println(true);
							flag = true;
						}
					}
				}
			}
		return flag;
	}
	public String toString () {
		String str = super.tipoTabla + " = { ";
		if (aceptacion)
			str = str.concat("ACEPTACION ");
		for (int i = 0 ; i < size() ; i++) {
			str = str.concat("[ " + get(i).noTerminal + " -> ");
			for (int j = 0 ; j < get(i).size() ; j++) {
				str = str.concat (get(i).getConjunto().get(j) + " ");
			}
			str = str.concat(" ] ");
		}
		str = str.concat("}");
		return str;
	}
}
