package interfaz;

import java.io.*;

import thompson.*;

public class  TrabajaObjetos {
	public void guardar(Tabla tabla){
		File f = new File("tabla.obj");
		FileOutputStream fos = null;
		ObjectOutputStream escribirObjeto = null;
		
		try{
			fos = new FileOutputStream(f);
			escribirObjeto = new ObjectOutputStream(fos);
			
			escribirObjeto.writeObject(tabla);
		}catch(Exception e){ }
		finally{
			try{
				if(escribirObjeto != null)
					escribirObjeto.close();
			}catch(Exception ex){ }
		}
	}
	
	public Tabla leer(){
		File f = new File("tabla.obj");
		FileInputStream fis = null;
		ObjectInputStream leerObjeto = null;
		Tabla tabla;
		
		try{
			fis = new FileInputStream(f);
			leerObjeto = new ObjectInputStream(fis);
			tabla = (Tabla)leerObjeto.readObject();
			return tabla;
		}catch(Exception e){ }
		finally{
			try{
				if(leerObjeto != null)
					leerObjeto.close();
			}catch(Exception ex){ }
		}
		tabla = null;
		return tabla;
	}
}
