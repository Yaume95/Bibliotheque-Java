import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
public class Panneau extends JPanel 
{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 8544534511093150594L;

public void paintComponent(Graphics g){
    try {
    	Image img = ImageIO.read(new File("bibliotheque1.jpg"));
    	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    	} 	catch (IOException e) {
    		e.printStackTrace();
    }                
  }               
}