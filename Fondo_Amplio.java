package Aplicacion;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Fondo_Amplio extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		File miImagen = new File("/home/dani/eclipse-workspace/CRUD_WB/src/IMG/Fondo_800.png");
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




