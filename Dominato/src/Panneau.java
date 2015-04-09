import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panneau extends JPanel 
{
	private static final long serialVersionUID = 1L;
	int compt=0;
	private Partie partie;
	private AffichageDominosEst affDominoEst;
	private AffichageDominosWest affDominoWest;
	private AffichageJoueurs affJoueurs;
	private Controleur controleur;
	private AffichagePlateau plateau;
	private JButton haut;
	private JButton bas;
	private JButton droit;
	private JButton gauche;
	
	public int getLargeur()
	{
		return this.gauche.getWidth();
	}
	
	public int getHauteur()
	{
		return this.bas.getHeight();
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	public Panneau(){
		super();
	}
	
	public Panneau(Partie partie, AffichageDominosEst affDominoEst, AffichageDominosWest affDominoWest, AffichageJoueurs affJoueurs)
	{
		haut = new BoutonHaut("Haut.jpg", this);
		bas = new BoutonBas("Bas.jpg", this);
		droit = new BoutonDroit("Droit.jpg", this);
		gauche = new BoutonGauche("Gauche.jpg", this);
		this.partie = partie;
		this.affDominoEst = affDominoEst;
		this.affDominoWest = affDominoWest;
		this.affJoueurs = affJoueurs;
		this.controleur = new Controleur(this, partie);
		this.addMouseListener(controleur);
		this.addMouseMotionListener(controleur);
		this.addMouseWheelListener(controleur);
		this.plateau = new AffichagePlateau(partie,this.compt);
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		haut.setBackground(new Color(245, 220, 180));
		bas.setBackground(new Color(245, 220, 180));
		gauche.setBackground(new Color(245, 220, 180));
		droit.setBackground(new Color(245, 220, 180));
		this.add(haut,BorderLayout.NORTH);
		this.add(bas,BorderLayout.SOUTH);
		this.add(gauche,BorderLayout.WEST);
		this.add(droit,BorderLayout.EAST);
		this.add(plateau,BorderLayout.CENTER);
		
	}
	
	public AffichageDominosEst getAffDominoEst()
	{
		return affDominoEst;
	}

	public AffichageDominosWest getAffDominoWest()
	{
		return affDominoWest;
	}

	public int getDebutX()
	{
		return this.plateau.getDebutX();
	}
	
	public int getDebutY()
	{
		return this.plateau.getDebutY();
	}
	
	public void bougerPlateauVersLeBas()
	{
		this.plateau.setDebutY(this.plateau.getDebutY()+1);
	}
	
	public void bougerPlateauVersLeHaut()
	{
		this.plateau.setDebutY(this.plateau.getDebutY()-1);
	}
	
	public void bougerPlateauVersLaGauche()
	{
		this.plateau.setDebutX(this.plateau.getDebutX()-1);
	}
	
	public void bougerPlateauVersLaDroite()
	{
		this.plateau.setDebutX(this.plateau.getDebutX()+1);
	}
	
	public AffichagePlateau getPlateau() {
		return plateau;
	}

	public AffichageJoueurs getAffJoueurs() {
		return affJoueurs;
	}

	public void paint(Graphics g)
	{
		this.paintComponents(g);		
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

		
}