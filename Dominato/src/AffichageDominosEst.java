import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class AffichageDominosEst extends JPanel {
	
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
		if(this.partie.getNbJoueurs() !=1){
			init();
		}else{
			init1();
		}
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
	
	public void init1(){
		
		for (Domino d : this.partie.getJoueur(0).getJeu()){
			adh.add(new AffichageDominoHorizental(this.partie,d));
			dominos.add(d);
		}
		
		for(int i = 16;i<adh.size();i++){
			this.add(adh.get(i),i-16);
		}
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    this.paintComponents(g);
    }


}


