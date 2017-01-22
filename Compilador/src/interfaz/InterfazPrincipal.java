package interfaz;
/*
 * @since 0.1
 */
import javax.swing.*;

import herramientas.AbrirArchivo;
import lexico.AbrirCodigo;
import sintactico.AbrirPrograma;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class InterfazPrincipal extends JFrame implements ActionListener {
	private BackgroundMenuBar menuBar;
	private JMenu analizadorLexico;
	private JMenu acercaDe;
	private JMenu analizadorSintactico;
	private JMenu semantico;
	private JMenuItem conjuntos;
	private JMenuItem thompsom;
	private JMenuItem equipo;
	private JMenuItem lexico;
	private JMenuItem primeros;
	private JMenuItem canonica;
	private JMenuItem analisisSintactico;
    private JMenuItem tas;
    private JMenuItem traduccionSemantica;
	private PanelFondo fondo;
	
	public InterfazPrincipal(){
		super("Compilador");
		setLayout(null);
		
		//Crea el menu
		menuBar = new BackgroundMenuBar();
		menuBar.setColor(Color.BLACK);
		setJMenuBar(menuBar);
		analizadorLexico = new JMenu("Analizador L\u00e9xico");
		analizadorLexico.setBackground(Color.BLACK);
		analizadorLexico.setForeground(new Color(0,128,255));
		analizadorLexico.getPopupMenu().setBackground(Color.BLACK);
		menuBar.add(analizadorLexico);
		acercaDe = new JMenu("Acerca de");
		acercaDe.setForeground(new Color(0,128,255));
		acercaDe.getPopupMenu().setBackground(Color.BLACK);
		analizadorSintactico = new JMenu("Analizador Sint\u00e1ctico");
		analizadorSintactico.setBackground(Color.BLACK);
		analizadorSintactico.setForeground(new Color(0,128,255));
		analizadorSintactico.getPopupMenu().setBackground(Color.BLACK);
		semantico = new JMenu ("Sem\u00e1ntico");
		semantico.setBackground(Color.BLACK);
		semantico.setForeground(new Color (0, 128, 255));
		semantico.getPopupMenu().setBackground(Color.BLACK);
		menuBar.add(analizadorSintactico);
		menuBar.add(semantico);
		menuBar.add(acercaDe);
		thompsom = new JMenuItem("Algoritmo de Thompson");
		thompsom.addActionListener(this);
		thompsom.setBackground(Color.BLACK);
		thompsom.setForeground(new Color(0,128,255));
		analizadorLexico.add(thompsom);
		conjuntos = new JMenuItem("Algoritmo de Subconjuntos");
		conjuntos.addActionListener(this);
		conjuntos.setBackground(Color.BLACK);
		conjuntos.setForeground(new Color(0,128,255));
		analizadorLexico.add(conjuntos);
		lexico = new JMenuItem("Analizador L\u00e9xico");
		lexico.addActionListener(this);
		lexico.setBackground(Color.BLACK);
		lexico.setForeground(new Color(0,128,255));
		analizadorLexico.add(lexico);
		primeros = new JMenuItem("Primeros y Siguientes");
		primeros.addActionListener(this);
		primeros.setBackground(Color.BLACK);
		primeros.setForeground(new Color(0,128,255));
		analizadorSintactico.add(primeros);
		canonica = new JMenuItem("Colecci\u00f3n Can\u00f3nica");
		canonica.addActionListener(this);
		canonica.setBackground(Color.BLACK);
		canonica.setForeground(new Color(0,128,255));
		analizadorSintactico.add(canonica);
        tas = new JMenuItem("Tabla de An\u00e1lisis Sint\u00e1ctico");
		tas.addActionListener(this);
        tas.setBackground(Color.BLACK);
		tas.setForeground(new Color(0,128,255));
		analizadorSintactico.add(tas);
		analisisSintactico = new JMenuItem("Pila de An\u00e1lisis Sint\u00e1ctico LR");
		analisisSintactico.addActionListener(this);
		analisisSintactico.setBackground(Color.BLACK);
		analisisSintactico.setForeground(new Color(0,128,255));
		traduccionSemantica = new JMenuItem ("Traductor");
		traduccionSemantica.addActionListener(this);
		traduccionSemantica.setBackground(Color.BLACK);
		traduccionSemantica.setForeground(new Color (0,128,255));
		semantico.add(traduccionSemantica);
		analizadorSintactico.add(analisisSintactico);
		equipo = new JMenuItem("Integrantes del Equipo");
		equipo.setBackground(Color.BLACK);
		equipo.setForeground(new Color(0,128,255));
		equipo.addActionListener(this);
		acercaDe.add(equipo);
		
		//Coloca el fondo
		fondo = new PanelFondo();
		add(fondo,BorderLayout.CENTER);
		fondo.repaint();
		
		//Carateristicas de la ventana
		setBounds(300,120,850,600);
		setResizable(false);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e){

	    if(e.getSource() == thompsom){
    		Entrada ventanaEntrada = new Entrada(1);
	    }
	    if(e.getSource() == equipo){
	    	VentanaNombres ventanaNombre = new VentanaNombres();
	    }
	    if(e.getSource() == conjuntos){
	    	Entrada ventanaEntrada = new Entrada(2);
	    }
	    if(e.getSource() == lexico){
	    	Abrir abrirReservadas = new Abrir("Archivo de palabras reservadas");	    	
	    	if(abrirReservadas.respuesta == JFileChooser.APPROVE_OPTION){
	    		Abrir abrirSimbolos = new Abrir("Archivo de s\u00edmbolos");
	    		if(abrirSimbolos.respuesta == JFileChooser.APPROVE_OPTION){
	    			AbrirCodigo codigoFuente = new AbrirCodigo ("Archivo de c\u00f3digo fuente");
			    	if(codigoFuente.respuesta == JFileChooser.APPROVE_OPTION){
			    		VentanaLexico ventanaLexico = new VentanaLexico(codigoFuente, abrirSimbolos.temp, abrirReservadas.temp);
			    	}
	    		}
	    	}
	    }
	    if(e.getSource() == primeros){
	    	Abrir gramatica = new Abrir("Abrir gram\u00e1tica");
	    	if(gramatica.respuesta == JFileChooser.APPROVE_OPTION){
	    		VentanaSiguientes ventaPrimeros = new VentanaSiguientes(gramatica.temp);
	    	}
	    }
	    if(e.getSource() == canonica){
	    	Abrir gramatica = new Abrir("Abrir gram\u00e1tica");
	    	if(gramatica.respuesta == JFileChooser.APPROVE_OPTION){
	    		VentanaCanonica ventaPrimeros = new VentanaCanonica(gramatica.temp);
	    	}
	    }
        if(e.getSource() == tas){
	    Abrir gramatica = new Abrir("Abrir gram\u00e1tica");
	    	if(gramatica.respuesta == JFileChooser.APPROVE_OPTION){
	    		VentanaTAS ventaTAS = new VentanaTAS(gramatica.temp);
	    	}
	    }
        if(e.getSource() == analisisSintactico){
	    	AbrirArchivo abrirReservadas = new AbrirArchivo ("Reservadas.txt");	    	
		    if(abrirReservadas.getFlag()){
		    	AbrirArchivo abrirSimbolos = new AbrirArchivo ("Simbolos.txt");
		    	if(abrirSimbolos.getFlag()){
		    		Abrir abrirGramatica = new Abrir ("Abrir gram\u00e1tica");
		    		if (abrirGramatica.respuesta == JFileChooser.APPROVE_OPTION) {
			    		AbrirPrograma codigoFuente = new AbrirPrograma ("Archivo de c\u00f3digo fuente");
					   	if(codigoFuente.respuesta == JFileChooser.APPROVE_OPTION){
					   		VentanaAnalisisSintactico ventanaAnalisisSintactico = new VentanaAnalisisSintactico(codigoFuente, abrirSimbolos.getExpresion(), abrirReservadas.getExpresion(), abrirGramatica.temp);
					   	}
		    		}
		    	}
		    }
	    }
        if (e.getSource() == traduccionSemantica) {
        	AbrirArchivo abrirReservadas = new AbrirArchivo ("Reservadas.txt");	    	
		    if(abrirReservadas.getFlag()){
		    	AbrirArchivo abrirSimbolos = new AbrirArchivo ("Simbolos.txt");
		    	if(abrirSimbolos.getFlag()){
		    		Abrir abrirGramatica = new Abrir ("Abrir gram\u00e1tica");
		    		if (abrirGramatica.respuesta == JFileChooser.APPROVE_OPTION) {
		    			AbrirPrograma accionesSemanticas = new AbrirPrograma ("Abrir Acciones Semanticas", "", "");
		    			if (accionesSemanticas.respuesta == JFileChooser.APPROVE_OPTION) {
				    		AbrirPrograma codigoFuente = new AbrirPrograma ("Archivo de c\u00f3digo fuente");
						   	if(codigoFuente.respuesta == JFileChooser.APPROVE_OPTION){
						   		VentanaTraduccion ventanaTraduccion = new VentanaTraduccion (codigoFuente, abrirSimbolos.getExpresion(), abrirReservadas.getExpresion(), abrirGramatica.temp, codigoFuente.getArchiveName());
						   	}
			    		}
		    		}
		    	}
		    }
        }
	}
}

