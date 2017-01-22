package interfaz;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class VentanaNombres extends JFrame{
	private JLabel nombre1;
	//private JLabel nombre2;
	private JLabel nombre3;
	private JLabel nombre4;
	private JLabel nombre5;
	
	public VentanaNombres(){
		super("Integrantes del Equipo");
		
		//Crea los nombre
		nombre1 = new JLabel("Arreola Mota Walfred Emmanuel",SwingConstants.CENTER);
		nombre1.setFont(new java.awt.Font("Helvetica", 0, 28));
		nombre1.setForeground(new Color(0,128,255));
		add(nombre1);
		/*
		nombre2 = new JLabel("Garc�a Cervantes Oscar Daniel",SwingConstants.CENTER);
		nombre2.setFont(new java.awt.Font("Helvetica", 0, 28));
		nombre2.setForeground(new Color(0,128,255));
		add(nombre2);
		*/
		nombre3 = new JLabel("Hern\u00e1ndez Montellano Carlos",SwingConstants.CENTER);
		nombre3.setFont(new java.awt.Font("Helvetica", 0, 28));
		nombre3.setForeground(new Color(0,128,255));
		add(nombre3);
		nombre4 = new JLabel("Mart\u00ednez Robles Liz Velia",SwingConstants.CENTER);
		nombre4.setFont(new java.awt.Font("Helvetica", 0, 28));
		nombre4.setForeground(new Color(0,128,255));
		add(nombre4);
		nombre5 = new JLabel("Miguel S\u00e1nchez Itzel Mariela",SwingConstants.CENTER);
		nombre5.setFont(new java.awt.Font("Helvetica", 0, 28));
		nombre5.setForeground(new Color(0,128,255));
		add(nombre5);
		
		//Formato de la ventana
		this.getContentPane().setBackground(Color.BLACK);
		setLayout(new GridLayout(5,1));
		setBounds(500,250,450,250);
		setResizable(false);
	}
}
