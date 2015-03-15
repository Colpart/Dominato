import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;



public class AffichageDominosWest extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partie partie;
	private ArrayList<AffichageDominoHorizental> adh;
	
	
	AffichageDominosWest(Partie partie){
		this.partie = partie;
		this.adh = new ArrayList<AffichageDominoHorizental>();
		
		this.setPreferredSize(new Dimension(200,this.getHeight()));
		init();
	}
	
	public void init(){
		for (Domino d : this.partie.getJoueur(0).getJeu()){
			adh.add(new AffichageDominoHorizental(this.partie,d));
		}
		
		for(int i = 0;i<adh.size();i++){
			this.add(adh.get(i),i);
		}
	}
	
	
	
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    g.setColor(Color.BLACK);
	    g.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());
	    this.paintComponents(g);
	    
	    
	    /*
	      int i = 60, j = 10, c = 0, j1 = j;
			
			for (Domino d : this.partie.getJoueur(0).getJeu())
			{
				if( j1 > this.getHeight()-40){
					if( c==0){
						
					    
						i = 10;
						j = 10;
						c++;
						d.getMarque1().draw(g, i, j);
						j = j+40;
						d.getMarque2().draw(g, i, j);
						j = j+50;
					}
					else{
						d.getMarque1().draw(g, i, j);
						j = j+40;
						d.getMarque2().draw(g, i, j);
						j = j+50;
					}
				}
				else{
					d.getMarque1().draw(g, i, j);
					d.getMarque2().draw(g, i+40, j);
					j += 50;
					j1 = j;
				}
			}*/
	  }
	}


