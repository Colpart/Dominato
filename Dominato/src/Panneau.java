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
	private int debutX;
	private int debutY;
	private AffichagePlateau plateau;
	private JButton haut = new Bouton("haut.jpg");
	private JButton bas = new Bouton("bas.jpg");
	private JButton droit = new Bouton("droit.jpg");
	private JButton gauche = new Bouton("gauche.jpg");
	
	
	public Controleur getControleur() {
		return controleur;
	}

	public Panneau(){
		super();
	}
	
	public Panneau(Partie partie, AffichageDominosEst affDominoEst, AffichageDominosWest affDominoWest, AffichageJoueurs affJoueurs)
	{
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
		droit.setPreferredSize(new Dimension(40,40));
		this.add(haut,BorderLayout.NORTH);
		this.add(bas,BorderLayout.SOUTH);
		this.add(gauche,BorderLayout.WEST);
		this.add(droit,BorderLayout.EAST);
		this.add(plateau,BorderLayout.CENTER);
		
		this.debutX = 0;
		this.debutY = 0;
	}
	
	
	public AffichageDominosEst getAffDominoEst() {
		return affDominoEst;
	}

	public AffichageDominosWest getAffDominoWest() {
		return affDominoWest;
	}

	public int getDebutX()
	{
		return debutX;
	}
	
	public AffichagePlateau getPlateau() {
		return plateau;
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
		this.paintComponents(g);		
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

		
}