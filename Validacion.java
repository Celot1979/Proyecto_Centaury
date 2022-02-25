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
/*
 * 	    1º Se revisa caracter porcaracter si es letra o número.
 * 
 * 		2º En el caso que alguno de los caracteres sea un número, automaticamente
 * 		se solicita al usuario - mediante nua ventana emergente - que debe modificar
 * 		lo valores introduccidos.
 * 
 * 		3º Se implemeta en el archivo - Modificar_Registros- la llamada al método opciones
 * 		para que le de la posibilidad al usaurio de realizar otra modificación en los registros
 */
public class Validacion {
	public Validacion() {
		try {
			connection= DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha conectado");
		}
	}
	
	public static void Nombre(JTable table,String x, DefaultTableModel modelo) {
		table = table;
		x = x;
		modelo = modelo;
		isNumeric = true;
		contador =1;
		opcion = opcion;
		int fila = table.getSelectedRow();
		

		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);

			// Si no está entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				isNumeric = true;
				x = " ";
				modelo.setValueAt(x, fila, 1);
				JOptionPane.showMessageDialog(null, "Hay un bug. No se puede modificar el registro porque no se admite números!",
						"Atención!", JOptionPane.ERROR_MESSAGE);
				x = JOptionPane.showInputDialog("Introducce el nombre sin números ni caracteres: ");
				modelo.setValueAt(x, fila, 1);
				int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
				String sql2 ="Update registro SET  nombre = '"+x+"'  WHERE id = '"+id+"'";
				PreparedStatement actu;
				try {
					actu = connection.prepareStatement(sql2);
					actu.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modificado en la validación");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Fallo de validación de número");
				}

			}else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '){
				isNumeric = false;

			}
		}
		if(isNumeric == true) {
			
		}else {
			
			while(isNumeric == false && contador2 == 1) {
				nombre = table.getValueAt(fila, 1).toString();
				Opcion();
				contador2 ++;
			}
		}
	    
		
	}
	

	public static void Apellido(JTable table,String y,DefaultTableModel modelo) {
		table = table;
		y = y;
		modelo = modelo;
		isNumeric = true;
		contador =1;
		opcion = opcion;
		
		int fila = table.getSelectedRow();
		

		for (int i = 0; i < y.length(); i++) {
			char c = y.charAt(i);

			// Si no está entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				isNumeric = true;
				y = " ";
				modelo.setValueAt(y, fila, 2);
				JOptionPane.showMessageDialog(null, "No se admite números!!!!","Atención!", JOptionPane.ERROR_MESSAGE);
				y = JOptionPane.showInputDialog("Introducce el apellido sin números ni caracteres: ");
				modelo.setValueAt(y, fila, 2);
				int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
				String sql2 ="Update registro SET  apellido = '"+y+"'  WHERE id = '"+id+"'";
				PreparedStatement actu;
				try {
					actu = connection.prepareStatement(sql2);
					actu.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modificado en la validación");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Fallo de validación de número método apellido");
				}

			}else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '){
				isNumeric = false;

			}
		}
		if(isNumeric == true) {
			System.out.println("Número");




		}else {
			//System.out.println("Letra");
			while(isNumeric == false && contador2 == 1) {
				apellido = table.getValueAt(fila, 2).toString();
				//Opcion();
				contador2 ++;
				
			}
		}
	    
		
	}
//	Método para volver a preguntar al usario si desea modificar algún campo de otro o el mismo registr
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
