public class Partie
{
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int joueurCourant;
	private Plateau plateau;
	private boolean horizontal;
	private boolean sens;
	private Domino dominoSelected;
	private int x;
	private int y;
	
	public Partie(int nbJoueurs)
	{
		this.nbJoueurs = nbJoueurs;
		this.joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs; i++)
			this.joueurs[i] = new Joueur("Joueur "+(i+1));
		this.joueurCourant = 0;
		this.plateau = new Plateau();
		this.horizontal = true;
		this.sens = true;
		this.dominoSelected = new Domino(1, Couleur.ROUGE, 2, Couleur.BLEU);
		this.x = 0;
		this.y = 0;
	}
	
	public Joueur getJoueur(int i)
	{
		return this.joueurs[i];
	}
	
	public Joueur getJoueurCourant()
	{
		return this.joueurs[this.joueurCourant];
	}
	
	public void joueurSuivant()
	{
		if (this.joueurCourant == this.nbJoueurs-1)
			this.joueurCourant = 0;
		else
			this.joueurCourant++;
	}
	
	public boolean getHorizontal()
	{
		return this.horizontal;
	}
	
	public boolean getSens()
	{
		return this.sens;
	}
	
	public void changerSens()
	{
		if (!this.sens)
			this.horizontal = !this.horizontal;
		
		this.sens = !this.sens;
	}
	
	public Domino getDominoSelected()
	{
		return dominoSelected;
	}
	
	public void changerDomino()
	{
		Couleur couleur1, couleur2;
		int valeur1 = (int)(Math.random()*5), valeur2 = (int)(Math.random()*5);
		
		if (valeur1 == 0)
			couleur1 = Couleur.BLANC;
		else
		{
			if ((int)(Math.random()*2) == 0)
				couleur1 = Couleur.ROUGE;
			else
				couleur1 = Couleur.BLEU;
		}
		
		if (valeur2 == 0)
			couleur2 = Couleur.BLANC;
		else
		{
			if ((int)(Math.random()*2) == 0)
				couleur2 = Couleur.ROUGE;
			else
				couleur2 = Couleur.BLEU;
		}
		
		this.dominoSelected = new Domino(valeur1, couleur1, valeur2, couleur2);
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getTaille()
	{
		return this.plateau.getTaille();
	}
	
	public Marque get(int i, int j)
	{
		return this.plateau.get(i, j);
	}
	
	public boolean positionEstLibre(int x, int y)
	{
		return this.plateau.positionEstLibre(x, y, this.horizontal);
	}
	
	public boolean positionEstOccupee(int x, int y)
	{
		return this.plateau.positionEstOccupee(x, y, this.horizontal);
	}
	
	public boolean coupPossible(int x, int y)
	{
		return this.plateau.coupPossible(x, y, this.horizontal);
	}
	
	public boolean coupValide(int x, int y)
	{
		return this.plateau.coupValide(x, y, this.getDominoSelected(), this.horizontal, this.sens);
	}
	
	public void poseDomino(int x, int y)
	{
		this.plateau.poseDomino(this.dominoSelected, this.horizontal, this.sens, x, y);
	}
}