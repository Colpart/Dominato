import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class AffichageJoueurs extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partie partie;
	
	
	AffichageJoueurs(Partie partie){
		this.partie = partie;
		this.setPreferredSize(new Dimension(this.getWidth(),50));
	}
	
	
	
	public void paint(Graphics g){
		
		g.setColor(Color.DARK_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
		
		Font font = new Font("Ubuntu", Font.PLAIN, 15);
		g.setFont(font);
		
		for (int k = 0; k < this.partie.getNbJoueurs(); k++)
		{
			if (this.partie.getJoueur(k).equals(this.partie.getJoueurCourant()))
			{
				if (!this.partie.estTerminee()){
					g.setColor(Color.WHITE);
				}
				else
					g.setColor(Color.GREEN);
			}
		    else
		    	g.setColor(Color.BLACK);
		    
			g.drawString(this.partie.getJoueur(k).getNom()+" : "+this.partie.getJoueur(k).getScore()+" ("+this.partie.getJoueur(k).getJeu().size()+")",  this.getWidth()/this.partie.getNbJoueurs()*k +50  , this.getHeight()-20);
			
	}

}
	
}
