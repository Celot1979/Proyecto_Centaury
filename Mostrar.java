package Aplicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/***
 * Archivo creado para mostrar una JTable con los registros efectuados hasta el momento.
 * 
 * Métodos habituales de busqueda y mostrar.
 ***/
public class Mostrar extends JFrame {
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
	private JTable table;
	private String convertir;
	private String[] columnNames = {"id","nombre","apellido","movil","fijo","anotacion"};
	private JTextField textBuscar;
	private String str ;
    private String firstLtr ;
    private String restLtrs;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mostrar frame = new Mostrar();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "REVISAR !","Error fatal en archivo Mostrar!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */

	public Mostrar() throws SQLException {
		connection= DriverManager.getConnection(url, user, pass);
		PreparedStatement stml = connection.prepareStatement("SELECT * FROM registro order by id");
		ResultSet resultados = stml.executeQuery();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1200,400,1000,600);
//		Se crea una nueva clase con una lámina nueva dentro del paquete Aplicación para crear una nuevo archivo con una clase que contenga una nueva lámina. 
//		Esa lámina tiene un tamaño de 800 de ancho que ayudará en ciertas partes del programa
		Aplicacion.Fondo_Amplio laminaImagen_Fondo_Mostrar = new Fondo_Amplio();
		getContentPane().add(laminaImagen_Fondo_Mostrar);
		laminaImagen_Fondo_Mostrar.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 300, 900, 250);
		laminaImagen_Fondo_Mostrar.add(scrollPane);
		PreparedStatement stml1 =null;
		ResultSet resultados1 = null;
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Dialog", Font.BOLD, 16));
		table.setForeground(new Color(255, 140, 0));
		
		table.setModel(modelo);
		modelo.addColumn("ID");		
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Móvil");
		modelo.addColumn("Fijo");
		modelo.addColumn("Anotación");
		String sql = "SELECT ID,nombre,apellido,movil,fijo,anotacion FROM registro";
		connection= DriverManager.getConnection(url, user, pass);
		PreparedStatement stml11  =connection.prepareStatement(sql);
		resultados = stml11.executeQuery();
		ResultSetMetaData rsMd = resultados.getMetaData();
		int cantidadColumnas = rsMd.getColumnCount();
		
		while(resultados.next()) {
			Object[] filas = new Object[cantidadColumnas];
			for (int i = 0; i < cantidadColumnas;i++) {
				filas[i] = resultados.getObject(i+1);
			}
			modelo.addRow(filas);
		}
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(224, 255, 255));
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(33, 98, 889, 122);
		laminaImagen_Fondo_Mostrar.add(panel);
		panel.setLayout(null);
		
		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				Proboca que siempre que se meta una 1º letra se vuelva mayúscula
				 str = textBuscar.getText();
			     firstLtr = str.substring(0, 1);
			     restLtrs = str.substring(1, str.length());
			      
			     firstLtr = firstLtr.toUpperCase();
			     str = firstLtr + restLtrs;
			     System.out.println(str);
				Buscar(str);
				
			}
		});
		textBuscar.setForeground(new Color(255, 165, 0));
		textBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textBuscar.setBounds(44, 29, 209, 40);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnAtras.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		btnAtras.setForeground(new Color(255, 140, 0));
		btnAtras.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnAtras.setBounds(33, 25, 117, 29);
		laminaImagen_Fondo_Mostrar.add(btnAtras);
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------	
//--------------------------------------------- MÉTODOS ACCIONES --------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void Buscar(String nombre) {
		try {
			connection= DriverManager.getConnection(url, user, pass);
			DefaultTableModel modelo = new DefaultTableModel();
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
				JOptionPane.showMessageDialog(null, "REVISAR !","No se ha podido buscar el registro. Revisé archivo Mostrar- Buscar!!", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "REVISAR !","No se ha podido buscar el registro. Revisé archivo Mostrar- Buscar!!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
