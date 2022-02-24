package Aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Base_Datos {
	public static void initConnection() {
		try {
			connection= DriverManager.getConnection(url, user, pass);
			System.out.println("Conexion con éxito");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "REVISAR CONEXIÓN!","SIN CONEXIÓN!!", JOptionPane.ERROR_MESSAGE);
			System.out.println("REVISAR: Conexión sin exito");
		}
	}

//      Crear registro con BBDD
		public static void Crear(String nombre, String apellido, String movil, String fijo,String anotacion) {
			PreparedStatement stml;
			try {
				stml = connection.prepareStatement("INSERT INTO registro(nombre, apellido,movil,fijo,anotacion) VALUES(?,?,?,?,?)");
				stml.setString(1, nombre);
				stml.setString(2, apellido);
				stml.setString(3, movil);
				stml.setString(4, fijo);
				stml.setString(5, anotacion);
				stml.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error en la creación del registro!","REGISTRONO CREADO!!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
//          Leer los registro
		
			public static  void leerRegistros(JTextArea contenido) throws SQLException {
				PreparedStatement stml = connection.prepareStatement("SELECT * FROM registro order by id");
				ResultSet resultados = stml.executeQuery();
				while(resultados.next()) {
					long id = resultados.getLong("id");
					String nombre = resultados.getString("nombre");
					String apellido = resultados.getString("apellido");
					String movil = resultados.getString("movil");
					String fijo = resultados.getString("fijo");
					String anotacion = resultados.getString("anotacion");
				
					try {
						contenido.append("\n" + id + " | " + nombre + " | " + apellido + " | " + movil + " | " + fijo + " | " + anotacion);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error en la lectura de los registros!","NO SE PUEDE LEER!!", JOptionPane.ERROR_MESSAGE);
					}
					

					
					
				}
			}
			
			
//          Eliminar Registros
			
			public static void EliminarRegistros(JTextField cid) throws SQLException {
				//Long resultado = Long.parseLong(cid.getText());
				Long resultado2 = (long) Integer.parseInt(JOptionPane.showInputDialog("Introduzca el Id"));
				PreparedStatement stml = connection.prepareStatement("DELETE FROM registro WHERE ID = ?");
				stml.setLong(1, resultado2);
				int row = stml.executeUpdate();
				if(row == 0) {
					JOptionPane.showMessageDialog(null, "No se borró el registro con id");
					System.out.println("No se borró el registro con id  " + resultado2);
				}else if( row != 0) {
					JOptionPane.showMessageDialog(null, "Se borró correctamente el registro");
					System.out.println("Se borró correctamente el registro con id: " + resultado2);
					
				}
			}
			
	
//          Modificar los registros -----
			public static void ModificarDatos(long id, String NuevoNom, String NuevoApe, String movil,String fijo,String anotacion) throws SQLException {
				PreparedStatement stml = connection.prepareStatement("UPDATE registro SET nombre = ?, apellido= ?, movil = ?, fijo =?, anotacion = ? WHERE id =?");
				stml.setString(1,NuevoNom);
				stml.setString(2, NuevoApe);
				stml.setString(3, movil);
				stml.setString(4, fijo);
				stml.setString(5, anotacion);
				
				
				
				stml.setLong(6, id);
				int row = stml.executeUpdate();
				if(row == 0) {
					JOptionPane.showMessageDialog(null, "No se modificó nada!","SIN MODIFICAR!!", JOptionPane.ERROR_MESSAGE);
					System.out.println("No se modificó nada");
				}else {
					JOptionPane.showMessageDialog(null, "Se modificó el registro con éxito");
					System.out.println("Se modificó el registro");
				}
				
			}
	
	private static final String user = "dani";
	private static final String pass = "1234";
	private static String url = "jdbc:postgresql://127.0.0.1:5432/base_datos_dani_db";
    private  static Connection connection;
    private JTextArea contenido;
    private JTextField cid;
}
