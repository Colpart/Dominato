public class Partie
{
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int joueurCourant;
	private Plateau plateau;
	private Domino dominoSelectionne;
	private boolean horizontal;
	private boolean sens;
	private int x;
	private int y;
	
	public Partie(int nbJoueurs)
	{
		this.nbJoueurs = nbJoueurs;
		this.joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs; i++)
			this.joueurs[i] = new Joueur("Joueur "+(i+1));
		this.joueurCourant = 0;
		this.dominoSelectionne = null;
		this.changerDomino();
		this.plateau = new Plateau();
		this.horizontal = true;
		this.sens = true;
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
	
	public Domino getDominoSelectionne()
	{
		return dominoSelectionne;
	}
	
	public void changerDomino()
	{
		int valeur1, valeur2;
		Couleur couleur1, couleur2;
		
		do
		{
			valeur1 = (int)(Math.random()*5);
			valeur2 = (int)(Math.random()*5);
		} while (valeur1 == 0 && valeur2 == 0);
		
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
		
		this.dominoSelectionne = new Domino(valeur1, couleur1, valeur2, couleur2);
	}
	
	public boolean dominoEstHorizontal()
	{
		return this.horizontal;
	}
	
	public boolean dominoEstDansLeSens1()
	{
		return this.sens;
	}
	
	public void changerSens()
	{
		if (this.horizontal)
		{
			this.horizontal = !this.horizontal;
		}
		else
		{
			this.horizontal = !this.horizontal;
			this.sens = !this.sens;
		}
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
	
	public Plateau getPlateau()
	{
		return this.plateau;
	}
	
	public boolean coupPossible(int x, int y)
	{
		return this.plateau.coupPossible(x, y, this.horizontal);
	}
	
	public boolean coupValide(int x, int y)
	{
		return this.plateau.coupValide(x, y, this.getDominoSelectionne(), this.dominoEstHorizontal(), this.dominoEstDansLeSens1());
	}
	
	public void poserDomino(int x, int y)
	{
		this.plateau.poserDomino(this.getDominoSelectionne(), this.dominoEstHorizontal(), this.dominoEstDansLeSens1(), x, y);
	}
}