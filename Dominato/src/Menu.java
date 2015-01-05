import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;




public class Menu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;
	
	
	Menu(Image img){
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	}
	
	public void paint(Graphics g) {
		g.drawImage (img, 0, 0, null);
	}

		
}
