import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class AffichageDominosEst extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partie partie;
	private ArrayList<AffichageDominoHorizental> adh;
	private ArrayList<Domino> dominos;
	
	AffichageDominosEst(Partie partie){
		this.partie = partie;
		this.setPreferredSize(new Dimension(200,this.getHeight()));
		this.adh = new ArrayList<AffichageDominoHorizental>();
		this.dominos = new ArrayList<Domino>();
		init();
	}
	
	public void init(){
		
		for (Domino d : this.partie.getJoueur(1).getJeu()){
			adh.add(new AffichageDominoHorizental(this.partie,d));
			dominos.add(d);
		}
		
		for(int i = 0;i<adh.size();i++){
			this.add(adh.get(i),i);
		}
	}
	
	public void changement(){
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    this.changement();
	    this.paintComponents(g);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseMoved(MouseEvent arg0){
		this.repaint();
	}
}


