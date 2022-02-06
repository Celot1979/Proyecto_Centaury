package Aplicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

public class Mostrar extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
    private JTextArea contenido;
    private JTextField cid;
    private JTable table_1;


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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Mostrar() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1200,400,800,600);
		Aplicacion.LaminaImagen_Fondo_Mostrar laminaImagen_Fondo_Mostrar = new LaminaImagen_Fondo_Mostrar();
		getContentPane().add(laminaImagen_Fondo_Mostrar);
		laminaImagen_Fondo_Mostrar.setLayout(null);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 23, 760,150);
		laminaImagen_Fondo_Mostrar.add(scrollPane);
		PreparedStatement stml =null;
		ResultSet resultados = null;
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Dialog", Font.BOLD, 17));
		table.setForeground(new Color(255, 140, 0));
		
		table.setModel(modelo);
		
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Móvil");
		modelo.addColumn("Fijo");
		modelo.addColumn("Anotación");
		
		String sql = "SELECT nombre,apellido,movil,fijo,anotacion FROM registro";
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
		
		
		scrollPane.setViewportView(table);
		
		/*JTextArea textArea = new JTextArea();
		textArea.setBounds(60, 93, 294, 260);
		laminaImagen_Fondo_Mostrar.add(textArea);*/
		/*try {
			DefaultTableModel modelo = new DefaultTableModel();
			table = new JTable();
			table.setModel(modelo);
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "SELECT nombre,apellido,movil,fijo,anotacion FROM registro";
			connection= DriverManager.getConnection(url, user, pass);
			ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			ResultSetMetaData rsMd = rs.getMetaData();
			
			int cantidadColumnas = rsMd.getColumnCount();
			
			modelo.addColumn("ID");
			modelo.addColumn("Nombre");
			modelo.addColumn("Apellido");
			modelo.addColumn("Móvil");
			modelo.addColumn("Fijo");
			modelo.addColumn("Anotación");
			
			while(rs.next()) {
				Object[] filas = new Object[cantidadColumnas];
				for (int i = 0; i < cantidadColumnas;i++) {
					filas[i] = rs.getObject(i+1);
				}
				modelo.addRow(filas);
			}
			
		}catch(SQLException ex) {
			System.out.println(ex.toString());
		}*/
		
		
	}
}
class LaminaImagen_Fondo_Mostrar extends JPanel{

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		File miImagen = new File("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/Fondo_Registros.png");

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


