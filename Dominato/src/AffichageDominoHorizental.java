import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class AffichageDominoHorizental extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Domino domino;
	private boolean zoom;
	private boolean dominoPose;
	private Partie partie;
	
	AffichageDominoHorizental(Partie partie,Domino d){
		domino = d;
		zoom = false;
		this.partie = partie;
		dominoPose = false;
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(90,50));
	}
	
	public boolean appartientOrdinateur(Domino domino){
		if(!partie.getJoueur(1).estHumain()){
			for(Domino d : partie.getJoueur(1).getJeu()){
				if(d.equals(domino)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void paint(Graphics g){
		this.dominoPose = dominoPose(this.domino);
		if(this.appartientOrdinateur(domino)){
			domino.getMarque1().draw(g, 0, 0);
			domino.getMarque2().draw(g, 40, 0);
			this.removeMouseListener(this);
		}else{
			if(zoom == false && this.dominoPose == false){
				domino.getMarque1().draw(g, 0, 0);
				domino.getMarque2().draw(g, 40, 0);
				g.setColor(Color.DARK_GRAY);
			    g.fillRect(80, 0, 10, 40);
			}
			else if(zoom == true && this.dominoPose == false){
				domino.getMarque1().draw(g, 10, 0);
				domino.getMarque2().draw(g, 50, 0);
				g.setColor(Color.DARK_GRAY);
			    g.fillRect(0, 0, 10, 40);
			}
			else if( this.dominoPose == true){
				this.removeMouseListener(this);
				g.setColor(Color.DARK_GRAY);
			    g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}
	}

	public Domino getDomino() {
		return domino;
	}

	public boolean dominoPose(Domino d){
		if(this.partie.getjoueurcourant() == 0){
			if(this.partie.getJoueur(0).getJeu().contains(d)){
				return false;
			}
			else if(this.partie.getJoueur(1).getJeu().contains(d)){
				return false;
			}
			else{
				return true;
			}
		}
		else if(this.partie.getjoueurcourant() == 1 ){
			if(this.partie.getJoueur(1).getJeu().contains(d) ){
				return false;
			}
			else if(this.partie.getJoueur(0).getJeu().contains(d)){
				return false;
			}
			else{
				return true;
			}
		}else{
			return false;
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		this.partie.setDominoSelectionne(this.domino);
		this.repaint();	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		zoom = true;
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		zoom = false;
		this.repaint();	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
