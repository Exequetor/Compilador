package herramientas;

import java.io.FileWriter;

public class GuardarArchivo {
	private String nombreArchivo, fileBuffer;
	
	public GuardarArchivo (String nombreArchivo, String fileBuffer) {
		this.nombreArchivo = nombreArchivo;
		this.fileBuffer = fileBuffer;
	}
	
	public void guarda () {
		FileWriter fichero = null;
        try
        {
            fichero = new FileWriter(System.getProperty("user.dir") + "/" + nombreArchivo);

            fichero.write(fileBuffer);
			fichero.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
}
