package herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AbrirArchivo {
	private String ruta;
	private FileReader fr = null;
    private BufferedReader br = null;
    private String temp = new String();
    private Boolean flag = false; //Indica si el archivo se abrió correctamente.
	
	public AbrirArchivo (String nombreArchivo){
        String texto = "";
         try{
        	ruta = System.getProperty("user.dir") + "\\" + nombreArchivo;
        	File file  = new File (ruta);
        	fr = new FileReader(file);
        	br = new BufferedReader(fr);
        	while((texto = br.readLine()) != null){
        		//cambiar para mandar al algoritmo
        		//System.out.println(texto);
        		temp= temp + texto + '\n';
        	}        	
        	fr.close();
        	br.close();
        	//System.out.println(temp);
        	flag = true;
         }catch(Exception e1) {
        	 JFrame ventanaError = new JFrame ("Error");
        	 JOptionPane.showMessageDialog(ventanaError,
     			    "Ha ocurrido un error al intentar abrir el archivo " + nombreArchivo + " .\n\r",
     			    "Error",
     			    JOptionPane.ERROR_MESSAGE);
        	 System.out.println("Ha ocurrido un error al intentar abrir el archivo " + nombreArchivo + " .\n\r");
         }
	}
	public String getExpresion () {
		return temp;
	}
	public boolean getFlag () {
		return flag;
	}
}
