
package sintactico;


import canonico.ColeccionCanonica;
import canonico.EstadosCanonica;
import herramientas.Gramatica;
import herramientas.PSTabla;
import herramientas.PSTupla;
import java.util.ArrayList;
import siguientes.Siguientes;



public final class TAS {
    public PSTabla tabla;
    Siguientes siguientes;
    ColeccionCanonica canonica;
    PSTabla gramatica;
    ArrayList<String> noterminal;
    ArrayList<String> terminal;
    ArrayList<EstadosCanonica> edo, edoFull;
    private String [][] datos;
   
   public TAS (String exp){
    siguientes =new Siguientes( exp);
    canonica=new ColeccionCanonica(exp);
    gramatica=new PSTabla(Gramatica.leeGramaticaNoEps(exp, 0));
    noterminal = Gramatica.retornaNoTerminales(gramatica);
    terminal = Gramatica.retornaTerminales(gramatica);
    terminal.add(0, "$");
    //System.out.println(terminal);
    tabla=new PSTabla("TA");
    edo=canonica.coleccionCanonica;
    edoFull = canonica.getColeccionFull();
    //System.out.println (edoFull);
    ArrayList <EstadosCanonica> edoAux = new ArrayList <EstadosCanonica> ();
    for (int i = 0 ; i < edo.size() ; i++) {
        //System.out.println (i + " " + edo.get(i).getAceptacion());
        if (!edo.get(i).getAceptacion())
            edoAux.add(edo.get(i));
    }
    edo = new ArrayList <EstadosCanonica> (edoAux);
    //System.out.println(edo);
        
    //System.out.println(canonica);
    //System.out.println("No Terminales: " + noterminal);
    //System.out.println("Terminales: " + terminal);
    rellenar();
    irAA();
    //System.out.println(siguientes);
      //System.out.println(canonica);
    //System.out.println(tabla);
   }
   
    public void rellenar() {
        //System.out.println("Entrar");
        for(int i=0;i<terminal.size();i++){
            PSTupla tupla=new PSTupla(terminal.get(i));
            for(Integer j=0;j<edo.size() ;j++)
                tupla.add("~");
            tabla.add(tupla);
            
        }
        for(int i=0;i<noterminal.size();i++){
            PSTupla tupla=new PSTupla(noterminal.get(i));
            for(Integer j=0;j<edo.size() ;j++)
                tupla.add("~");
            tabla.add(tupla);
        }
    }
    public void irAA(){
       //recorre los estados de la coleccion canonica
       for(int i=0;i<edo.size();i++){
           //System.out.println(edo.get(i).getVector());
           //recorre cada subconjunto de cada estado de la coleccion canonica
           for (int j=0;j<edo.get(i).getVector().size();j++){
               //obtiene el tamaño de los caracteres que tiene un subconjunto para ubicar a "º"
               int posicion= edo.get(i).getVector().get(j).size();
               //verifica si el ultimo caracter del subconjunto es "º"
               if(edo.get(i).getVector().get(j).get(posicion-1).equals(Character.toString((char) 176))){
                    //Numero del estado al que corresponde el subconjunto System.out.println(edo.get(i).getEstado());
                    int estado = edo.get(i).getEstado();
                    //Subconjunto donde se encontrò a "º" System.out.println(edo.get(i).getVector().get(j));
                    //obtener el numero de regla igual al subconjunto que tiene el punto
                    String regla="R"+regla(edo.get(i).getVector().get(j));
                    //System.out.println(regla);
                    
                    //Obtine el estado NT del cual sacar los siguientes  System.out.println(edo.get(i).getVector().get(j).getNoTerminal());
                    String EstadoNT=edo.get(i).getVector().get(j).getNoTerminal();
                    //se obtienen los siguientes del Estado NT System.out.println(siguientes.siguientesDe(EstadoNT));
                    //recorrer los siguientes del EstadoNT
                    for(int k=0;k<siguientes.siguientesDe(EstadoNT).size();k++){
                           //tabla[estado][siguientes.siguientesDe(EstadoNT).get(k)]=regla;
                           //Se obtienen cada siguiente
                           String sig=siguientes.siguientesDe(EstadoNT).get(k);
                           //System.out.println("insertar: ["+estado+", "+sig+"] ="+regla);
                           //System.out.println(tabla.getNT(sig));
                           tabla.getNT(sig).set(estado,regla);
                           //System.out.println(tabla.getNT(sig));
                    }
               }
   
           }
       } 
       for(int i = 0 ; i <edoFull.size(); i++){ 
            String encontrado=edoFull.get(i).getIrA();
             int esta=edoFull.get(i).getIEstado();
            if(terminal.contains(edoFull.get(i).getIrA())){ 
           
            
            //tabla.getNT(edo.get(i).getIrA()).;
               
               
                String u="D"+edoFull.get(i).getEstado();
                //System.out.println(i + " " + "terminal:: " + esta + " " + encontrado);
           tabla.getNT(encontrado).set(esta,u);
           
        } 
            else{
            
            //System.out.println(i + " " + "no terminal:: "+edoFull.get(i).getIrA());
            Integer uN=edoFull.get(i).getEstado();
            tabla.getT(encontrado).set(esta, uN.toString());
            
        }
        String x="$";
        String y="Aceptaci\u00f3n";
        tabla.getNT(x).set(1, y);
    }
    }
    public String toString () {
        String str = new String ();
        str = "Edo";
        for (int i = 0 ; i < tabla.size() ; i++) {
            str = str + "\t" + tabla.get(i).getNoTerminal();
        }
        str = str + "\n\r";
        for (int i = 0 ; i < edo.size() ; i++) {
            str = str + i;
            for (int j = 0 ; j < tabla.size() ; j++) {
                str = str + "\t" + tabla.get(j).get(i);
            }
            str = str + "\n\r";
        }
        return str;
    }

    public int regla(PSTupla entrada){
        //hacer copia al subconjunto con el punto al final
        PSTupla copia=entrada;
        //quitar el punto del subconjunto copia
        copia.pop(entrada.size()-1);
        //buscar regla igual subconjunto con punto
        for(int i=0;i<gramatica.size();i++)
            //compara copia con el subconjuto de la gramatica si son iguales devuelve el numero de regla de la gramatica 
            if(gramatica.get(i).toString().equals(copia.toString())){
                return i + 1;
            
        }
        return 0;
    }
    //matriz que guarda la tabla, para una f�cil b�squeda al momento de realizar el analisis sintactico
    public String[][] tabla_matriz(){
     datos=new String[edo.size()+1][tabla.size()+1];
    
     datos[0][0]="Edo";
     for (int i =0 ; i <tabla.size(); i++){
    	 //System.out.println(tabla.get(i).getNoTerminal());
         datos[0][i+1]=tabla.get(i).getNoTerminal();}
     for (int i = 0 ; i < edo.size(); i++){
    	// System.out.println(i);
         datos[i+1][0]=""+i;}
     for (int i = 0 ; i < edo.size(); i++) {
         for (int j = 0 ; j < tabla.size() ; j++) {
        	 //System.out.println(tabla.get(j).get(i));
            datos[i+1][j+1]=tabla.get(j).get(i);
         }
     }
     return datos;
     }    
    
    public int size_edo(){return edo.size();}
    public int tabla_size(){return tabla.size();}
}

    
   
    
    
   // public void accion(edo a ,String exp){}
   

    
        
