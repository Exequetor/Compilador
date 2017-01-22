package lexico;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.Vector;

@SuppressWarnings("serial")
public class AbrirCodigo extends JFileChooser{
	public int respuesta;
	private FileReader fr = null;
    private BufferedReader br = null;
    public String temp = new String();
    private  Vector <String> Leer = new Vector <String> ();
    private String texto=new String ();

    public AbrirCodigo (String nombreVentana) {
    	this(nombreVentana, "C\u00F3digo fuente en C", "c");
    }
	public AbrirCodigo (String nombreVentana, String filterName, String extension){
		setDialogTitle(nombreVentana);
		FileNameExtensionFilter filter = new FileNameExtensionFilter (filterName, extension);
		setFileFilter(filter);
		//ruta del directorio
		File  aqui = new File(".").getAbsoluteFile();
		setCurrentDirectory(aqui);
		respuesta = showOpenDialog(this);
	if(respuesta == JFileChooser.APPROVE_OPTION){
        	File archivoElegido = getSelectedFile();
        	//Obtiene la ruta del archivo seleccionado
        	String ruta = archivoElegido.getAbsolutePath();
        	//Convierte el archivo en una cadena
        	 texto = "";
         try{
        	fr = new FileReader(ruta);
        	br = new BufferedReader(fr);
        	boolean blockFlag = false;
        	while((texto= br.readLine())!=null){
        		boolean flag = true;
        		//Elimina comentarios
        		String temporalText = new String ();
        		
        		for (int i = 0 ; i < texto.length() && flag; i++) {
        			if (texto.charAt(i) == '/' && i < texto.length()-1 &&texto.charAt(i+1) == '/') {
        				flag = false;
        			} else {
        				if (texto.charAt(i) == '/' && i < texto.length()-1 &&texto.charAt(i+1) == '*')
        					blockFlag = true;
        				if (!blockFlag)
        					temporalText = temporalText.concat(Character.toString(texto.charAt(i)));
        				if (texto.charAt(i) == '*' && i < texto.length()-1 &&texto.charAt(i+1) == '/') {
        					blockFlag = false;
        					i += 1;
        				}
        			}
        		}
				texto = temporalText;
        		Leer.addElement(texto);
          	}
    		br.close();
    		fr.close();
         }catch(Exception e1){
         		System.out.println("Error al abrir el código fuente");
         		e1.printStackTrace();
         } 
    }    
}
	
	public String toString(){
		String string=new String();
		for(int i=0;i<Leer.size();i++)
			string=string.concat(Leer.get(i)+"\r");
		return string;
	}
	public Vector <String> getPrograma () {
		return Leer;
	}
}