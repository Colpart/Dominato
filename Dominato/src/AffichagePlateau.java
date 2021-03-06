import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class AffichagePlateau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int getCompt() {
		return compt;
	}

	public void setCompt(int compt) {
		this.compt = compt;
	}

	private int debutX;
	private int debutY;
	private Partie partie;
	private int compt;
	
	AffichagePlateau(Partie partie, int compt){
		this.partie = partie;
		this.compt = compt;
		this.debutX = 10;
		this.debutY = 10;
	}

	public int getDebutX()
	{
		return this.debutX;
	}
	
	public int getDebutY()
	{
		return this.debutY;
	}
	
	public void setDebutX(int x)
	{
		if (x >= 0 && x <= 50)
			this.debutX = x;
	}
	
	public void setDebutY(int y)
	{
		if (y >= 0 && y <= 50)
			this.debutY = y;
	}
	
	public void paint(Graphics g)
	{
		setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), null));
		g.setColor(new Color(245, 220, 180));
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
	    
		for (int i = debutX; i < this.partie.getPlateau().getTaille(); i++)
			for (int j = debutY; j < this.partie.getPlateau().getTaille(); j++)
				if (!this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.VIDE) && !this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.PLEIN))
					this.partie.getPlateau().get(i, j).draw(g, 20*(i-debutX), 20*(j-debutY),this.partie.getAffichage());
			
		if (!this.partie.estTerminee() && this.compt ==0)
		{
			if (this.partie.dominoEstDansLeSens1())
				this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY(),this.partie.getAffichage());
			else
				this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY(),this.partie.getAffichage());
			
			if (this.partie.dominoEstHorizontal())
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX()+40, this.partie.getY(),this.partie.getAffichage());
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX()+40, this.partie.getY(),this.partie.getAffichage());
			else
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY()+40,this.partie.getAffichage());
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY()+40,this.partie.getAffichage());
	    }
		
	}

	
}
