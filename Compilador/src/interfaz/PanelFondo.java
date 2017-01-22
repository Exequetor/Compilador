package interfaz;
/*
 * @since 0.1
 */
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelFondo extends JPanel{
	JLabel titulo;
	public PanelFondo(){
		titulo = new JLabel("Compilador", SwingConstants.CENTER);
		titulo.setFont(new java.awt.Font("Tahoma", 0, 64));
		titulo.setForeground(Color.WHITE);
		add(titulo);
		setSize(850,600);
	}
	
	public void paint(Graphics g){
		Dimension height = getSize();
		ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		g.drawImage(fondo.getImage(),0,0,height.width,height.height,this);
		setOpaque(false);
		super.paint(g);
	}
}
