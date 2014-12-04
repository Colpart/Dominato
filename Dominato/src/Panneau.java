import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel
{
	static final long serialVersionUID = 1;
	
	private Partie partie;
	
	public Panneau(Partie partie)
	{
		this.partie = partie;
		this.setPreferredSize(new Dimension(20*partie.getTaille(), 45+20*partie.getTaille()));
		Controleur controleur = new Controleur(this, this.partie);
		this.addMouseListener(controleur);
		this.addMouseMotionListener(controleur);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (int i = 0; i < this.partie.getTaille(); i++)
			for (int j = 0; j < this.partie.getTaille(); j++)
				if (!this.partie.get(i, j).getCouleur().equals(Couleur.VIDE) && !this.partie.get(i, j).getCouleur().equals(Couleur.PLEIN))
					this.partie.get(i, j).draw(g, 20*i, 20*j);
		
		if (this.partie.getSens())
			this.partie.getDominoSelected().getMarque1().draw(g, this.partie.getX(), this.partie.getY());
		else
			this.partie.getDominoSelected().getMarque2().draw(g, this.partie.getX(), this.partie.getY());
		
		if (this.partie.getHorizontal())
			if (this.partie.getSens())
				this.partie.getDominoSelected().getMarque2().draw(g, this.partie.getX()+40, this.partie.getY());
			else
				this.partie.getDominoSelected().getMarque1().draw(g, this.partie.getX()+40, this.partie.getY());
		else
			if (this.partie.getSens())
				this.partie.getDominoSelected().getMarque2().draw(g, this.partie.getX(), this.partie.getY()+40);
			else
				this.partie.getDominoSelected().getMarque1().draw(g, this.partie.getX(), this.partie.getY()+40);
		
		g.setColor(Color.BLACK);
		g.drawString(this.partie.getJoueur(0).getNom()+" : "+Integer.toString(this.partie.getJoueur(0).getScore()), 20, 25+20*this.partie.getTaille());
		g.drawString(this.partie.getJoueur(1).getNom()+" : "+Integer.toString(this.partie.getJoueur(1).getScore()), 20*this.partie.getTaille()-100, 25+20*this.partie.getTaille());
	}               
}