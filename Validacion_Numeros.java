package Aplicacion;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Validacion_Numeros {
	public Validacion_Numeros() {
		try {
			connection= DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha conectado");
		}
	}
	
	public static void Movil(JTable table,String a, DefaultTableModel modelo) {
		table = table;
		a = a;
		modelo = modelo;
		isNumeric = true;
		contador =1;
		cantidad = 9;
		
		opcion = opcion;
		int fila = table.getSelectedRow();
		if (a.length() < cantidad || a.length() > cantidad) {
			a = " ";
			modelo.setValueAt(a, fila, 3);
			a = JOptionPane.showInputDialog("Introducce el número correcto: ");
			modelo.setValueAt(a, fila,3);
			int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
			String sql3 ="Update registro SET   movil = '"+a+"'  WHERE id = '"+id+"'";
			PreparedStatement actu;
			try {
				actu = connection.prepareStatement(sql3);
				actu.executeUpdate();
				JOptionPane.showMessageDialog(null, "Modificado en la validación");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Fallo de validación de número en el método del móvil");
			}
		}
		
		if(a.length() == cantidad) {
			for (int i = 0; i < a.length(); i++) {
				char c = a.charAt(i);
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '){
					isNumeric = true;
					a = " ";
					modelo.setValueAt(a, fila, 3);
					a = JOptionPane.showInputDialog("Introducce el número de móvil: ");
					modelo.setValueAt(a, fila, 3);
					int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
					String sql3 ="Update registro SET  movil = '"+a+"'  WHERE id = '"+id+"'";
					PreparedStatement actu;
					try {
						actu = connection.prepareStatement(sql3);
						actu.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modificado en la validación");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Fallo de validación de número");
					}
				}
				
			}
			
			
		}
		
	}
	
	public static void Fijo(JTable table,String t, DefaultTableModel modelo) {
		table = table;
		t = t;
		modelo = modelo;
		isNumeric = true;
		contador =1;
		cantidad = 9;
		
		opcion = opcion;
		int fila = table.getSelectedRow();
		if (t.length() < cantidad || t.length() > cantidad) {
			t = " ";
			modelo.setValueAt(t, fila, 4);
			t = JOptionPane.showInputDialog("Introducce el número correcto: ");
			modelo.setValueAt(t, fila,4);
			int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
			String sql4 ="Update registro SET   fijo = '"+t+"'  WHERE id = '"+id+"'";
			PreparedStatement actu;
			try {
				actu = connection.prepareStatement(sql4);
				actu.executeUpdate();
				JOptionPane.showMessageDialog(null, "Modificado en la validación");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Fallo de validación de número en el método del móvil");
			}
		}
		
		if(t.length() == cantidad) {
			for (int i = 0; i < t.length(); i++) {
				char c = t.charAt(i);
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '){
					isNumeric = true;
					t = " ";
					modelo.setValueAt(t, fila, 4);
					t = JOptionPane.showInputDialog("Introducce el número de móvil: ");
					modelo.setValueAt(t, fila, 4);
					int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
					String sql4 ="Update registro SET  fijo = '"+t+"'  WHERE id = '"+id+"'";
					PreparedStatement actu;
					try {
						actu = connection.prepareStatement(sql4);
						actu.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modificado en la validación");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Fallo de validación de número");
					}
				}
				
			}
			
			
		}
		
	}
	
	public static void Opcion() {
		int Opcion2 = JOptionPane.showConfirmDialog(null, "Deseas modificar otro registro");
		
		if ( Opcion2 ==0) {
			System.out.println("Continuamos");
			
		}else if(Opcion2 == 1) {
			//dispose();
			Modificar_Registros frame = new Modificar_Registros();
			frame.setVisible(true);
			frame.dispose();
			
		}
	}
	
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
	private static JTable table;
	private  static String convertir;
	private  static JPanel contentPane;
	private  static JTextField textField,textField_1,textField_2,textField_3,textField_4,textBuscar,ValidarNombre;
	private static String x,y,remplazar,tub, nombre, apellido,movil,fijo,anotacion,opcion,cadena;
	private static boolean isNumeric;
	private static  int contador, columna,contador2,cantidad;
	private static  DefaultTableModel modelo;
	private static KeyEvent evento;
}
