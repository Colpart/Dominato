import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel
{
	private static final long serialVersionUID = 1L;
	int compt=0;
	private Partie partie;
	private AffichageDominoEst affDominoEst;
	private AffichageDominoWest affDominoWest;
	private AffichageJoueurs affJoueurs;
	private Controleur controleur;
	private int debutX;
	private int debutY;
	
	public Controleur getControleur() {
		return controleur;
	}

	public Panneau(){
		super();
	}
	
	public Panneau(Partie partie, AffichageDominoEst affDominoEst, AffichageDominoWest affDominoWest, AffichageJoueurs affJoueurs)
	{
		this.partie = partie;
		this.affDominoEst = affDominoEst;
		this.affDominoWest = affDominoWest;
		this.affJoueurs = affJoueurs;
		this.controleur = new Controleur(this, partie);
		this.addMouseListener(controleur);
		this.addMouseMotionListener(controleur);
		this.addMouseWheelListener(controleur);
		this.debutX = 0;
		this.debutY = 0;
	}
	
	
	public AffichageDominoEst getAffDominoEst() {
		return affDominoEst;
	}

	public AffichageDominoWest getAffDominoWest() {
		return affDominoWest;
	}

	public int getDebutX()
	{
		return debutX;
	}
	
	public int getDebutY()
	{
		return debutY;
	}
	
	public AffichageJoueurs getAffJoueurs() {
		return affJoueurs;
	}

	public void paint(Graphics g)
	{
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(cursor);
		g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
		for (int i = debutX; i < this.partie.getPlateau().getTaille(); i++)
			for (int j = debutY; j < this.partie.getPlateau().getTaille(); j++)
				if (!this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.VIDE) && !this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.PLEIN))
					this.partie.getPlateau().get(i, j).draw(g, 20*(i-debutX), 20*(j-debutY));
			
		if (!this.partie.estTerminee() && this.compt ==0)
		{
			if (this.partie.dominoEstDansLeSens1())
				this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY());
			else
				this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY());
			
			if (this.partie.dominoEstHorizontal())
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX()+40, this.partie.getY());
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX()+40, this.partie.getY());
			else
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY()+40);
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY()+40);
	    }
		
	    
		
		
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}               
}