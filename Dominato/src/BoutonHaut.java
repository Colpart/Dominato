import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonHaut extends JButton implements MouseListener{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String info = "null";

	public BoutonHaut(){
		super();
		this.addMouseListener(this);
	}
	
	public BoutonHaut(String icone){
		super();
		info = icone;
		ImageIcon image = new ImageIcon(icone);
		this.setIcon(image);
		this.setBorderPainted(false);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setForeground(new Color(245, 220, 180));
		
	}


	public void mouseEntered(MouseEvent arg0) {
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(cursor);
		StringTokenizer str = new StringTokenizer(info,".");
		this.setToolTipText(str.nextToken());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setForeground(new Color(245, 220, 180));
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
