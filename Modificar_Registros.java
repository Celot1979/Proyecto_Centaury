package Aplicacion;
/**
 * Este archivo es el encargado junto con su clase, métodos y archivos asociados a realizar 
 * modificaciones parciales o totales de los registros introduccidos.
 * 
 * Utilizando la lógica llevada acabo hasta ahora, se ha ido usando los métodos que buscan o muestran.
 * El único nuevo es el de actualizar_Datos que es el que realiza las modificaciones.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Modificar_Registros extends JFrame {
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
	private JTable table;
	private String convertir;
	private JPanel contentPane;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textBuscar;
	private String str ;
	private String firstLtr ;
	private String restLtrs;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Registros frame = new Modificar_Registros();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Hay un bug. No se ha podido abrir la ventana!",
						      "Atención!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Modificar_Registros() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1200,400,1000,600);
		
		Aplicacion.Fondo_Amplio laminaImagen_Fondo_Mostrar = new Fondo_Amplio();
		getContentPane().add(laminaImagen_Fondo_Mostrar);
		laminaImagen_Fondo_Mostrar.setLayout(null);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 200, 922, 120);
		laminaImagen_Fondo_Mostrar.add(scrollPane_1);
		
		mostrar();
		Buscar("");
		
		
		scrollPane_1.setColumnHeaderView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(48, 67, 922, 104);
		laminaImagen_Fondo_Mostrar.add(panel);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					actualizarDatos();

			}
			
		});
		
		panel.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		lblBuscar.setForeground(new Color(255, 140, 0));
		lblBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBuscar.setBounds(36, 29, 116, 25);
		panel.add(lblBuscar);
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnModificar.setForeground(new Color(255, 140, 0));
		btnModificar.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		btnModificar.setBounds(428, 23, 160, 37);
		panel.add(btnModificar);
		
		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					str = textBuscar.getText();
				     firstLtr = str.substring(0, 1);
				     restLtrs = str.substring(1, str.length());
				      
				     firstLtr = firstLtr.toUpperCase();
				     str = firstLtr + restLtrs;
				     System.out.println(str);
					Buscar(str);
				}catch(StringIndexOutOfBoundsException t){
					System.out.println("");
				}
				 
			}
		});
		textBuscar.setForeground(new Color(255, 140, 0));
		textBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textBuscar.setBounds(170, 26, 160, 30);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnArasModi = new JButton("Atrás");
		btnArasModi.setBounds(48, 6, 117, 29);
		laminaImagen_Fondo_Mostrar.add(btnArasModi);
		btnArasModi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnArasModi.setIcon(new ImageIcon("//home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		btnArasModi.setForeground(new Color(255, 140, 0));
		btnArasModi.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel lblNewLabel1 = new JLabel("1º Selecione con el curso (Doble clic) el campo que desea modificar.");
		lblNewLabel1.setBounds(48, 394, 548, 15);
		laminaImagen_Fondo_Mostrar.add(lblNewLabel1);
		
		JLabel lblNewLabel = new JLabel("2º Modifique el campo. Pulse  ENTER");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(48, 421, 491, 15);
		laminaImagen_Fondo_Mostrar.add(lblNewLabel);
		
		JLabel lblPresioneElBotn = new JLabel("3º Presione el botón MODIFICAR");
		lblPresioneElBotn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblPresioneElBotn.setBounds(48, 448, 491, 15);
		laminaImagen_Fondo_Mostrar.add(lblPresioneElBotn);
		
		
		
		
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------	
//--------------------------------------------- MÉTODOS ACCIONES --------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public  void mostrar() {
		
		PreparedStatement stml =null;
		ResultSet resultados = null;
		modelo = new DefaultTableModel();
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Dialog", Font.BOLD, 17));
		table.setForeground(new Color(255, 140, 0));
		table.setModel(modelo);
		
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Móvil");
		modelo.addColumn("Fijo");
		modelo.addColumn("Anotación");
		
		String sql = "SELECT id,nombre,apellido,movil,fijo,anotacion FROM registro";
		try {
			connection= DriverManager.getConnection(url, user, pass);
			PreparedStatement stml1 =connection.prepareStatement(sql);
			resultados = stml1.executeQuery();
			ResultSetMetaData rsMd = resultados.getMetaData();
			int cantidadColumnas = rsMd.getColumnCount();
			
			while(resultados.next()) {
				Object[] filas = new Object[cantidadColumnas];
				for (int i = 0; i < cantidadColumnas;i++) {
					filas[i] = resultados.getObject(i+1);
				}
				modelo.addRow(filas);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Hay un bug. No se ha podido mostrar el contenido!",
				      "Atención!", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
	public void actualizarDatos() {
		int fila = table.getSelectedRow();
		int id = Integer.parseInt( this.table.getValueAt(fila, 0).toString());
		control = false;
/*	
 * 		Se crean 2 nuevos archivos:
 * 		
 * 		1º Validación de las celdas de la JTable que contienen caracteres de letras.
 * 		2º Validación de las celdas de la JTable que contienen caracteres numéricos.
 */
		nombre = table.getValueAt(fila, 1).toString();
		//validar_Texto(nombre,modelo);
		Validacion Validacion_Texto = new Validacion();
		Validacion_Texto.Nombre(table,nombre,modelo);
		nombre = table.getValueAt(fila, 1).toString();
		
		
//		
		apellido = table.getValueAt(fila, 2).toString();
		Validacion Validacion_Texto2 = new Validacion();
		Validacion_Texto2.Apellido(table,apellido,modelo);
		apellido = table.getValueAt(fila, 2).toString();
		
		
		movil = table.getValueAt(fila, 3).toString();
		Validacion_Numeros V_movil = new Validacion_Numeros();
		V_movil.Movil(table,movil,modelo);
		movil = table.getValueAt(fila, 3).toString();
		
		
		
		fijo = table.getValueAt(fila, 4).toString();
		Validacion_Numeros V_fijo = new Validacion_Numeros();
		V_fijo.Fijo(table,fijo,modelo);
		fijo = table.getValueAt(fila, 4).toString();
		
		String anotacion = table.getValueAt(fila, 5).toString();
		
		try {
			String sql2 ="Update registro SET  nombre = '"+nombre+"',apellido = '"+apellido+"',movil = '"+movil+"',fijo = '"+fijo+"',anotacion = '"+anotacion+"' WHERE id = '"+id+"'";
			PreparedStatement actu = connection.prepareStatement(sql2);
			actu.executeUpdate();
			JOptionPane.showMessageDialog(null, "Modificado con éxito");
			
			mostrar();
			control = true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hay un bug. No se ha podido modificar el registro!",
				      "Atención!", JOptionPane.ERROR_MESSAGE);
		}
		
		if(control == true) {
			int Opcion = JOptionPane.showConfirmDialog(null, "Deseas modificar otro registro");
			
			if ( Opcion ==0) {
				
				try {
					Modificar_Registros Modificar2 = new Modificar_Registros();
					Modificar2.setVisible(false);
					dispose();
					Modificar2.setVisible(true);
					
				}catch (ArrayIndexOutOfBoundsException et) {
					System.out.println("Error");
				}
			}else if(Opcion == 1) {
				dispose();
				System.out.println("Continuamos");
				
			}
		}
		
		
	}
	
	public void Buscar(String nombre) {
		try {
			connection= DriverManager.getConnection(url, user, pass);
			modelo = new DefaultTableModel();
			modelo.addColumn("ID");
			modelo.addColumn("Nombre");
			modelo.addColumn("Apellido");
			modelo.addColumn("Móvil");
			modelo.addColumn("Fijo");
			modelo.addColumn("Anotación");
			table.setModel(modelo);
			
			String sql3 =" ";
			
			if(nombre.equals(" ")) {
				sql3 = "SELECT * FROM registro";
			}else {
				sql3 = "SELECT * FROM registro WHERE nombre like '%"+nombre+"%' ";
			}
			
			String usuarios[] = new String[6];
			try {
				Statement set = connection.createStatement();
				ResultSet resul = set.executeQuery(sql3);
				while(resul.next()){
					usuarios[0] = resul.getString(1);
					usuarios[1] = resul.getString(2);
					usuarios[2] = resul.getString(3);
					usuarios[3] = resul.getString(4);
					usuarios[4] = resul.getString(5);
					usuarios[5] = resul.getString(6);
					modelo.addRow(usuarios);
				}
				
				table.setModel(modelo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Hay un bug. No se ha podido buscar el registro!",
					      "Atención!", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Hay un bug. No se ha podido buscar el registro!",
				      "Atención!", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	
	
	
	
	private String x,remplazar,tub, nombre, apellido,movil,fijo,anotacion;
	private boolean isNumeric, control;
	private int contador, columna;
	private DefaultTableModel modelo;
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
