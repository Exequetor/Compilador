package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class VentanaGeneral extends JFrame implements ActionListener{
	private BackgroundMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem guardar;
	private JLabel salida;
	public JScrollPane scroll;
	private JScrollPane scrollPane;
	public JTextArea txp;
	public VentanaGeneral(String nombre){
		super(nombre);
		
		//crea el menu
				menuBar = new BackgroundMenuBar();
				menuBar.setColor(Color.BLACK);
				setJMenuBar(menuBar);
				archivo = new JMenu("Archivo");
				archivo.setBackground(Color.BLACK);
				archivo.getPopupMenu().setBackground(Color.BLACK);
				archivo.setForeground(new Color(0,128,255));
				menuBar.add(archivo);
				guardar = new JMenuItem("Guardar");
				guardar.addActionListener(this);
				guardar.setBackground(Color.BLACK);
				guardar.setForeground(new Color(0,128,255));
				archivo.add(guardar);
				getContentPane().setLayout(new BorderLayout(0, 0));
				//jsp.setVisible(true);
				
				this.getContentPane().setBackground(Color.BLACK);
				
				scrollPane = new JScrollPane();
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				
				txp = new JTextArea();
				scrollPane.setViewportView(txp);
				
				//crea el encabezado
				salida = new JLabel("Salida", SwingConstants.CENTER);
				salida.setFont(new java.awt.Font("Helvetica",0,20));
				salida.setForeground(new Color(0,128,255));
				getContentPane().add(salida, BorderLayout.NORTH);
				setBounds(0,0,600,600);
				setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
				//setResizable(false);
				setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardar) {
        	try{
        		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
        		file.showSaveDialog(this);
        		File guarda = file.getSelectedFile();
        		
        		if(guarda != null){
        			FileWriter save = new FileWriter(guarda+".txt");
        			save.write(txp.getText());
        			save.close();
        		}
        	}catch(IOException ex){
        		System.out.println(ex);
        	}
        }
    }
	
	public void esVisible(Boolean op){
		setVisible(op);
	}
}
