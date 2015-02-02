import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class BoutonM extends JButton implements MouseListener{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public BoutonM(String name){
		super(name);
		this.addMouseListener(this);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorderPainted(false);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Color clr = new Color(102,0,0);
		this.setBackground(clr);
		this.setForeground(Color.white);
	}


	public void mouseEntered(MouseEvent arg0) {
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(cursor);
		Color clr = new Color(102,0,0);
		this.setBackground(clr);
		this.setForeground(Color.white);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Color clr = new Color(102,0,0);
		this.setBackground(clr);
		this.setForeground(Color.white);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
