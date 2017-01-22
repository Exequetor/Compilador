package interfaz;
/*
 * @since 0.1
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

@SuppressWarnings("serial")
public class Abrir extends JFileChooser{
	public int respuesta;
	private FileReader fr = null;
    private BufferedReader br = null;
    public String temp = new String();
	
    public Abrir (String nombreVentana) {
    	this(nombreVentana, "Archivos TXT", "txt");
    }
    
	public Abrir(String nombreVentana, String filterName, String extension){
		//Configuraciones de la ventana de diálogo
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
        	String texto = "";
         try{
        	fr = new FileReader(ruta);
        	br = new BufferedReader(fr);
        	while((texto = br.readLine()) != null){
        		//cambiar para mandar al algoritmo
        		//System.out.println(texto);
        		temp= temp + texto + '\n';
        	}
        	fr.close();
        	br.close();
        	//System.out.println(temp);
         }catch(Exception e1){
        	 System.out.println("Error al leer");
         }
       }
	}
}
