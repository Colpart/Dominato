import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class AffichageDominoEst extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partie partie;
	
	AffichageDominoEst(Partie partie){
		this.partie = partie;
		this.setPreferredSize(new Dimension(150,this.getHeight()));
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    g.setColor(Color.BLACK);
	    g.drawLine(0, 0, 0, this.getHeight());
	    
	    	int i = 10, j = 10, c = 0, j1 = j;
			
			for (Domino d : this.partie.getJoueur(1).getJeu())
			{
				if( j1 > this.getHeight()-40){
					if( c==0){
						g.setColor(Color.DARK_GRAY);
						g.fillRect(100, 0, 50, this.getHeight());
					    
						i = 100;
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
			}
	    	
	    }
	}


