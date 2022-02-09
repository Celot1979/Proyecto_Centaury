package Aplicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Registros extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre,textApellido,textMovil,textFijo;
	private JTextArea textArea;
	private String nombre,apellido,movil,fijo,anotacion;
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registros frame = new Registros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**

	* Create the frame.

	*/

	public Registros() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1200,400,400,600);
		Aplicacion.LaminaImagen_Fondo laminaImagen_Fondo = new LaminaImagen_Fondo();
		getContentPane().add(laminaImagen_Fondo);
		laminaImagen_Fondo.setLayout(null);

		JLabel lbl1_Registros = new JLabel("Nombre");
		lbl1_Registros.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1_Registros.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/WindowsBuilder_CRUD/src/Imagenes/BT1.png"));
		lbl1_Registros.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl1_Registros.setBounds(40, 44, 138, 29);
		lbl1_Registros.setForeground(new Color(255, 140, 0));
		laminaImagen_Fondo.add(lbl1_Registros);

		
		JLabel lbl1_Registros_2 = new JLabel("Apellido");
		lbl1_Registros_2.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/WindowsBuilder_CRUD/src/Imagenes/BT1.png"));
		lbl1_Registros_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1_Registros_2.setForeground(new Color(255, 140, 0));
		lbl1_Registros_2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl1_Registros_2.setBounds(40, 114, 138, 29);
		laminaImagen_Fondo.add(lbl1_Registros_2);


		JLabel lbl1_Registros_2_1 = new JLabel("Móvil");
		lbl1_Registros_2_1.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/WindowsBuilder_CRUD/src/Imagenes/BT1.png"));
		lbl1_Registros_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1_Registros_2_1.setForeground(new Color(255, 140, 0));
		lbl1_Registros_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl1_Registros_2_1.setBounds(40, 184, 138, 29);
		laminaImagen_Fondo.add(lbl1_Registros_2_1);


		JLabel lbl1_Registros_2_2 = new JLabel("Fijo");
		lbl1_Registros_2_2.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/WindowsBuilder_CRUD/src/Imagenes/BT1.png"));
		lbl1_Registros_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1_Registros_2_2.setForeground(new Color(255, 140, 0));
		lbl1_Registros_2_2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl1_Registros_2_2.setBounds(40, 254, 138, 29);
		laminaImagen_Fondo.add(lbl1_Registros_2_2);

		JLabel lbl1_Registros_3 = new JLabel("Anotación");
		lbl1_Registros_3.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/WindowsBuilder_CRUD/src/Imagenes/BT1.png"));
		lbl1_Registros_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbl1_Registros_3.setForeground(new Color(255, 140, 0));
		lbl1_Registros_3.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl1_Registros_3.setBounds(40, 324, 138, 29);
		laminaImagen_Fondo.add(lbl1_Registros_3);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 364, 294, 134);
		laminaImagen_Fondo.add(scrollPane);

		textArea = new JTextArea();
		textArea.setForeground(new Color(218, 165, 32));
		scrollPane.setViewportView(textArea);
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != KeyEvent.VK_SPACE)) e.consume();
			}
		});
		textNombre.setForeground(new Color(218, 165, 32));
		textNombre.setHorizontalAlignment(SwingConstants.LEFT);
		textNombre.setBounds(214, 44, 130, 26);
		laminaImagen_Fondo.add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')&& (c != KeyEvent.VK_SPACE)) e.consume();
			}
		});
		textApellido.setForeground(new Color(218, 165, 32));
		textApellido.setColumns(10);
		textApellido.setBounds(214, 114, 130, 26);
		laminaImagen_Fondo.add(textApellido);

		textMovil = new JTextField();
//		El método de escucha para saber que se presiona, nos sirve para validar que el usuario no presione más que números o letras; dependiendo de lo que se necesite
		textMovil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(textMovil.getText().length() > 8) {
					JOptionPane.showMessageDialog(null, "Número de móvil invalidado por exceso de digitos");
					textMovil.setText("");

				}else if (textMovil.getText().length() <= 9){
					if(c < '0' || c > '9') {
						e.consume();
					}
				}
				
			}
		});
		textMovil.setForeground(new Color(218, 165, 32));
		textMovil.setColumns(10);
		textMovil.setBounds(214, 184, 130, 26);
		laminaImagen_Fondo.add(textMovil);

		textFijo = new JTextField();
		textFijo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(textFijo .getText().length() > 8) {
					JOptionPane.showMessageDialog(null, "Número de móvil invalidado por exceso de digitos");
					textFijo .setText("");

				}else if (textFijo .getText().length() <= 9){
					if(c < '0' || c > '9') {
						e.consume();
					}
				}
			}
		});
		textFijo.setForeground(new Color(218, 165, 32));
		textFijo.setColumns(10);
		textFijo.setBounds(214, 257, 130, 26);
		laminaImagen_Fondo.add(textFijo);

		JButton btn1_Registrar = new JButton("Registrar");
		btn1_Registrar.setBounds(60, 510, 117, 29);
		btn1_Registrar.addActionListener(new ActionListener() {


			@Override

			public void actionPerformed(ActionEvent e) {
				Base_Datos.initConnection();
				String nom = textNombre.getText();
				String ape = textApellido.getText();
				String mov = textMovil.getText();
				String fij = textFijo.getText();
				String cano = textArea .getText();
				System.out.println("Procesado Registro");
				Base_Datos.Crear(nom, ape, mov, fij, cano);
				
				
			}

		});

		laminaImagen_Fondo.add(btn1_Registrar);
		
		JButton btnNewButtonBorrar = new JButton("Borrar ");
		btnNewButtonBorrar.setBounds(189, 510, 117, 29);
		btnNewButtonBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textNombre.setText("");
				textApellido.setText("");
				textMovil.setText("");
				textFijo.setText("");
				textArea.setText("");
				
			}
			
		});
		laminaImagen_Fondo.add(btnNewButtonBorrar);


	}
}

class LaminaImagen_Fondo extends JPanel{

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		File miImagen = new File("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/Fondo_Registros.png");

		try {

			imagen = ImageIO.read(miImagen);

		} catch (IOException e) {

			// TODO Auto-generated catch block

			//e.printStackTrace();

			System.out.println("Un error");

		}

		g.drawImage(imagen,0,0, null);

		//g.copyArea(50, 50, 128, 128, 350, 250);// Copiar y pgar una imagen



	}

	private Image imagen;

}

