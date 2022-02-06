package Aplicacion;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import java.awt.Graphics;

import java.awt.Image;

import java.io.File;

import java.io.IOException;


import javax.imageio.ImageIO;

import javax.swing.JFrame;

import javax.swing.JLabel;

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
					e.printStackTrace();
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

		setBounds(400,400,800,600);

		Aplicacion.LaminaImagen laminaImagen = new LaminaImagen();

		getContentPane().add(laminaImagen);

		laminaImagen.setLayout(null);

		

//		Etiquetas en la página princial

		LB1 = new JLabel("Agenda Personalizada");

		LB1.setForeground(new Color(169, 169, 169));

		LB1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		LB1.setBounds(427, 18, 326, 41);

		

		laminaImagen.add(LB1);

		

		

		LB2 = new JLabel("La tecnología en tu mano, fácil e intuitiva");

		LB2.setForeground(new Color(192, 192, 192));

		LB2.setBounds(462, 71, 270, 41);

		laminaImagen.add(LB2);

		

		btn1 = new JButton("Registrar Contacto");

		btn1.setHorizontalAlignment(SwingConstants.LEFT);

		btn1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		btn1.setBackground(new Color(240, 255, 255));

		btn1.setForeground(new Color(255, 140, 0));

		btn1.setBounds(472, 106, 247, 48);

		ImageIcon Imagen_boton = new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png");

		btn1.setIcon(Imagen_boton);

		Acciones_Botones uno = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn1.addActionListener(uno);

		laminaImagen.add(btn1);

		

		btn2 = new JButton("Mostrar Contactos");

		btn2.setHorizontalAlignment(SwingConstants.LEFT);

		Acciones_Botones nuevo = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);
		btn2.addActionListener(nuevo);

		btn2.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn2.setForeground(new Color(255, 140, 0));

		btn2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		btn2.setBackground(new Color(240, 255, 255));

		btn2.setBounds(472, 170, 247, 48);

		btn1.setIcon(Imagen_boton);

		laminaImagen.add(btn2);

		

		btn3 = new JButton("Modificar  Contactos");

		btn3.setHorizontalAlignment(SwingConstants.LEFT);

		btn3.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn3.setForeground(new Color(255, 140, 0));

		btn3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		btn3.setBackground(new Color(240, 255, 255));

		btn3.setBounds(472, 240, 247, 48);

		laminaImagen.add(btn3);

		

		btn4 = new JButton("Borrar Contactos");

		btn4.setHorizontalAlignment(SwingConstants.LEFT);

		btn4.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn4.setForeground(new Color(255, 140, 0));

		btn4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		btn4.setBackground(new Color(240, 255, 255));

		btn4.setBounds(472, 310, 247, 48);

		laminaImagen.add(btn4);

		

		btn5 = new JButton("SALIR");

		btn5.setHorizontalAlignment(SwingConstants.LEFT);

		btn5.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));

		btn5.setForeground(new Color(255, 140, 0));

		btn5.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		btn5.setBackground(new Color(240, 255, 255));

		btn5.setBounds(472, 380, 247, 48);

		Acciones_Botones cinco = new Acciones_Botones(btn1,btn2,btn3,btn4,btn5);

		btn5.addActionListener(cinco);

		laminaImagen.add(btn5);

		

		

		

	}

}

class LaminaImagen extends JPanel{

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		File miImagen = new File("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/Img2_2.png");

		try {

			imagen = ImageIO.read(miImagen);

		} catch (IOException e) {

			// TODO Auto-generated catch block

			//e.printStackTrace();

			System.out.println("Un eerrorr");

		}

		g.drawImage(imagen,0,0, null);

		//g.copyArea(50, 50, 128, 128, 350, 250);// Copiar y pgar una imagen

		

		

	}

	private Image imagen;

}



