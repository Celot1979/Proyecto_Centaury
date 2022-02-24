package Aplicacion;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import java.io.File;

import java.io.IOException;


import javax.imageio.ImageIO;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import java.awt.Color;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.util.Timer;


import javax.swing.ImageIcon;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Empezar extends JFrame {

	private JPanel contentPane;

	private JLabel  LB1,LB2;

	private JButton btn1,btn2,btn3,btn4,btn5,BRegistros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empezar frame = new Empezar();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error en el sistema!","Atención!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**

	* Create the frame.

	* @throws InterruptedException 

	* En el método comiemza se genera todo el código del JFrame principal.

	*/

	public Empezar() throws InterruptedException {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(400,400,1920,1080);

		Aplicacion.LaminaImagen laminaImagen = new LaminaImagen();

		getContentPane().add(laminaImagen);

		laminaImagen.setLayout(null);

		

//		Etiquetas en la página princial

		LB1 = new JLabel("Agenda Personalizada");

		LB1.setForeground(new Color(169, 169, 169));

		LB1.setFont(new Font("Garuda", Font.PLAIN, 40));

		LB1.setBounds(1013, 37, 464, 58);

		

		laminaImagen.add(LB1);

		

		

		LB2 = new JLabel("La tecnología en tu mano, fácil e intuitiva");
		LB2.setFont(new Font("Dialog", Font.BOLD, 25));

		LB2.setForeground(new Color(192, 192, 192));

		LB2.setBounds(904, 107, 726, 41);

		laminaImagen.add(LB2);

		

		btn1 = new JButton("Registrar Contacto");

		btn1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));

		btn1.setBackground(new Color(240, 255, 255));

		btn1.setForeground(new Color(255, 140, 0));

		btn1.setBounds(914, 160, 589, 76);

		ImageIcon Imagen_boton = new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png");

		btn1.setIcon(Imagen_boton);

		Acciones_Botones uno = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn1.addActionListener(uno);

		laminaImagen.add(btn1);

		

		btn2 = new JButton("Mostrar Contactos");

		Acciones_Botones nuevo = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);
		btn2.addActionListener(nuevo);

		btn2.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn2.setForeground(new Color(255, 140, 0));

		btn2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));

		btn2.setBackground(new Color(240, 255, 255));

		btn2.setBounds(914, 257, 589, 76);

		btn1.setIcon(Imagen_boton);

		laminaImagen.add(btn2);

		

		btn3 = new JButton("Modificar  Contactos");
		Acciones_Botones tres = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn3.addActionListener(tres);

		laminaImagen.add(btn3);

		btn3.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn3.setForeground(new Color(255, 140, 0));

		btn3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));

		btn3.setBackground(new Color(240, 255, 255));

		btn3.setBounds(914, 354, 589, 76);

		laminaImagen.add(btn3);

		

		btn4 = new JButton("Borrar Contactos");

		btn4.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn4.setForeground(new Color(255, 140, 0));

		btn4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));

		btn4.setBackground(new Color(240, 255, 255));

		btn4.setBounds(914, 451, 589, 76);
		Acciones_Botones cuatro = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn4.addActionListener(cuatro);

		laminaImagen.add(btn4);

		

		btn5 = new JButton("SALIR");

		btn5.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn5.setForeground(new Color(255, 140, 0));

		btn5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));

		btn5.setBackground(new Color(240, 255, 255));

		btn5.setBounds(914, 548, 589, 76);

		Acciones_Botones cinco = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn5.addActionListener(cinco);

		laminaImagen.add(btn5);

		

		

		

	}

}

class LaminaImagen extends JPanel{

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		File miImagen = new File("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/Fondo_Pantalla_Completa_Portatil.png");

		try {

			imagen = ImageIO.read(miImagen);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha cargado la imagen - LaminaImagen - JPanel!","Atención!!", JOptionPane.ERROR_MESSAGE);

		}

		g.drawImage(imagen,0,0, null);

		//g.copyArea(50, 50, 128, 128, 350, 250);// Copiar y pgar una imagen

		

		

	}

	private Image imagen;

}



