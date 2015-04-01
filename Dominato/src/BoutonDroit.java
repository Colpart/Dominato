import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonDroit extends JButton implements MouseListener{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String info = "null";
	private Panneau panneau;

	public BoutonDroit(){
		super();
		this.addMouseListener(this);
	}
	
	public BoutonDroit(String icone, Panneau panneau){
		super();
		info = icone;
		ImageIcon image = new ImageIcon(icone);
		this.setIcon(image);
		this.setBackground(Color.DARK_GRAY);
		this.setBorderPainted(false);
		this.addMouseListener(this);
		this.panneau = panneau;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		this.panneau.bougerPlateauVersLaDroite();
		this.panneau.paintImmediately(0, 0, this.panneau.getWidth(), this.panneau.getHeight());
		this.panneau.getAffDominoEst().paintImmediately(0, 0, this.panneau.getAffDominoEst().getWidth(), this.panneau.getAffDominoEst().getHeight());
		this.panneau.getAffDominoWest().paintImmediately(0, 0, this.panneau.getAffDominoWest().getWidth(), this.panneau.getAffDominoEst().getHeight());
		this.panneau.getAffJoueurs().paintImmediately(0, 0, this.panneau.getAffJoueurs().getWidth(), this.panneau.getAffJoueurs().getHeight());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
