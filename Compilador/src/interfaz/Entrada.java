package interfaz;
/*
 * @since 0.1
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Entrada extends JFrame implements ActionListener{
	private JButton abrir;
	private JButton aceptar;
	private JTextField entrada;
	private JLabel texto;
	private int opcion;
	
	public Entrada(int op){
		super("Entrada");
		setLayout(null);
		opcion = op;
		
		texto = new JLabel("Ingrese su expresi\u00f3n:");
		texto.setBounds(25,0,180,40);
		texto.setFont(new java.awt.Font("Helvetica", 0, 16));
		texto.setForeground(new Color(0,128,255));
		add(texto);
		entrada = new JTextField();
		entrada.setBounds(20, 40, 460, 25);
		add(entrada);
		abrir = new JButton("Leer archivo");
		abrir.addActionListener(this);
		abrir.setBounds(300, 10, 150, 30);
		add(abrir);
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(200, 72, 100, 30);
		add(aceptar);

		this.getContentPane().setBackground(Color.BLACK);
		setBounds(400,300,500,130);
		setResizable(false);
		setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == abrir){
			Abrir abrir = new Abrir("Abrir expresi\u00f3n");
			if(abrir.respuesta == JFileChooser.APPROVE_OPTION){
				//Muestra la cadena en el área de texto
				entrada.setText(abrir.temp);
			}
		}
		if(e.getSource() == aceptar){
			//Guarda la expresión del área de texto en una cadena
			String expresion = entrada.getText();
			if(opcion == 1){
				VentanaThompson ventanaThompson = new VentanaThompson(expresion);
				dispose();
			}
			if(opcion == 2){
	    		//Envia la expresion a la vetana de conjuntos
	    		VentanaConjuntos ventanaConjuntos = new VentanaConjuntos(expresion);
	    		dispose();
			}
		}
	}
}
