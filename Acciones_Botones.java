package Aplicacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Acciones_Botones implements ActionListener {
	public Acciones_Botones(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5) {

		this.btn1 = btn1;

		this.btn2 = btn2;

		this.btn3 = btn3;

		this.btn4 = btn4;

		this.btn5 = btn5;

	}
	@Override
/*
 * 	
 * OJO !! Para que se pueda cerrar individualmente una ventana, se debe de poner después de instaciar el objeto ventana, el JFrame
 * Además en el archivo EMPEZAR debemos tb cambiar de EXIT_ON_CLOSE a DISPONSE
 */
	
	
	public void actionPerformed(ActionEvent e) {

		if(btn1 == e.getSource()) {

			Registros nuevo = new Registros();
			nuevo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			nuevo.setVisible(true);
		}else if(btn2 == e.getSource()) {
			try {
				Mostrar MostrarContactos = new Mostrar();
				MostrarContactos.setVisible(true);
				MostrarContactos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("No se ha podido mostrar los contactos");
			}
		}


		if(btn5 == e.getSource()) {

			System.exit(1);

		}


	}


	private JButton btn1,btn2,btn3,btn4,btn5;


}


