import java.awt.Color;
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
	private Controleur controleur;
	private int debutX;
	private int debutY;
	
	public Controleur getControleur() {
		return controleur;
	}

	public Panneau(){
		super();
	}
	
	public Panneau(Partie partie, AffichageDominoEst affDominoEst, AffichageDominoWest affDominoWest)
	{
		this.partie = partie;
		this.affDominoEst = affDominoEst;
		this.affDominoWest = affDominoWest;
		//this.setPreferredSize(new Dimension(20*30+100, 45+20*30));
		this.controleur = new Controleur(this, partie);
		this.addMouseListener(controleur);
		this.addMouseMotionListener(controleur);
		this.addMouseWheelListener(controleur);
		this.debutX = 50;
		this.debutY = 50;
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
	
	public void paint(Graphics g)
	{
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
		
	    
		
		Font font = new Font("Ubuntu", Font.PLAIN, 15);
		g.setFont(font);
		
		for (int k = 0; k < this.partie.getNbJoueurs(); k++)
		{
			if (this.partie.getJoueur(k).equals(this.partie.getJoueurCourant()))
			{
				if (!this.partie.estTerminee()){;
					g.setColor(Color.BLUE);
				}
				else
					g.setColor(Color.GREEN);
			}
		    else
		    	g.setColor(Color.BLACK);
		    
			g.drawString(this.partie.getJoueur(k).getNom()+" : "+this.partie.getJoueur(k).getScore()+" ("+this.partie.getJoueur(k).getJeu().size()+")", 20+k*(this.getWidth()-100)/this.partie.getNbJoueurs(), this.getHeight()-20);
		}
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}               
}