package Aplicacion;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class Borrar_Registros extends JFrame {

	
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
	private JTable table;
	private String convertir;
	private String[] columnNames = {"id","nombre","apellido","movil","fijo","anotacion"};
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textBuscar;
	private String str;
	private String firstLtr;
	private String restLtrs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrar_Registros frame = new Borrar_Registros();
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
	public Borrar_Registros() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1200,400,1000,600);
		
		Aplicacion.Fondo_Amplio laminaImagen_Fondo_Mostrar = new Fondo_Amplio();
		
		
		getContentPane().add(laminaImagen_Fondo_Mostrar);
		laminaImagen_Fondo_Mostrar.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 200, 922, 300);
		laminaImagen_Fondo_Mostrar.add(scrollPane_1);
		
		mostrar();
		Buscar("");
		
		
		scrollPane_1.setColumnHeaderView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(48, 84, 922, 86);
		laminaImagen_Fondo_Mostrar.add(panel);
		
		JButton btnModificar = new JButton("Borrar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar_Datos();
				Borrar_Registros frame2 = new Borrar_Registros();
				frame2.setVisible(true);
				
				
				int Opcion2 = JOptionPane.showConfirmDialog(null, "Deseas borrar otro registro");
				
				if ( Opcion2 ==0) {
					System.out.println("Continuamos");
				}else if(Opcion2 == 1) {
					dispose();
					Borrar_Registros frame = new Borrar_Registros();
					frame.setVisible(true);
					frame.dispose();
					frame2.setVisible(true);
					frame2.dispose();
					
				}
				
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
		btnModificar.setBounds(428, 23, 129, 37);
		panel.add(btnModificar);
		
		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 str = textBuscar.getText();
			     firstLtr = str.substring(0, 1);
			     restLtrs = str.substring(1, str.length());
			      
			     firstLtr = firstLtr.toUpperCase();
			     str = firstLtr + restLtrs;
			     System.out.println(str);
				Buscar(str);
				
				

				
			}
		});
		textBuscar.setForeground(new Color(255, 140, 0));
		textBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textBuscar.setBounds(170, 26, 160, 30);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizado Listado");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		btnActualizar.setForeground(new Color(255, 140, 0));
		btnActualizar.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		btnActualizar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnActualizar.setBounds(598, 29, 239, 29);
		
		
		panel.add(btnActualizar);
		
		JButton btnAtrasBorrar = new JButton("Atrás");
		btnAtrasBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Borrar_Registros frame = new Borrar_Registros();
				Borrar_Registros frame2 = new Borrar_Registros();
				
				frame.setVisible(true);
				frame.dispose();
				frame2.setVisible(true);
				frame2.dispose();
			}
		});
		btnAtrasBorrar.setIcon(new ImageIcon("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/BT1.png"));
		btnAtrasBorrar.setForeground(new Color(255, 140, 0));
		btnAtrasBorrar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnAtrasBorrar.setBounds(48, 22, 117, 29);
		laminaImagen_Fondo_Mostrar.add(btnAtrasBorrar);
	
		
	}
	
	
public  void mostrar() {
		
		PreparedStatement stml =null;
		ResultSet resultados = null;
		DefaultTableModel modelo = new DefaultTableModel();
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
			e.printStackTrace();
		}
		
		
		
	}
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
			mostrar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
    }
public void Borrar_Datos() {
	int fila = table.getSelectedRow();
	//int id = Integer.parseInt( this.table.getValueAt(fila, 0).toString());
	String valor = table.getValueAt(fila, 0).toString();
	
	
	/*String nombre = table.getValueAt(fila, 1).toString();
	String apellido = table.getValueAt(fila, 2).toString();
	String movil = table.getValueAt(fila, 3).toString();
	String fijo = table.getValueAt(fila, 4).toString();
	String anotacion = table.getValueAt(fila, 5).toString();*/
	try {
		String sql4 ="DELETE FROM registro WHERE id = '" +valor+"'";
		PreparedStatement elimi = connection.prepareStatement(sql4);
		elimi.executeUpdate();
		JOptionPane.showMessageDialog(null, "Se ha borrado con éxito el registro");
		
	}catch (Exception e) {
		System.out.println("No se ha podido borrar el registro");
	}
	
}
}

