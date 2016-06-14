package clientgui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FONPanel extends JPanel {

	BufferedImage icon;
	public FONPanel(){
		try {
			icon = ImageIO.read(new File("/src/clientgui/Logotip-FON-Negativ-Eng.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
