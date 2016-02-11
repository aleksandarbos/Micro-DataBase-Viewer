package gui.imagebackgrounds;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Predstavlja panel koji za pozadinu ima odredjeno zadatu sliku koja mu je
 * prosledjena kao parametar konstruktora.
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class ImageBackgroundPanel extends JPanel {
	private BufferedImage image = null;
	
	public ImageBackgroundPanel(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, 0,getSize().width, getSize().height, null);
		//super.paintComponent(g);
	}
}
