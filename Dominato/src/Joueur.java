import java.util.ArrayList;

public class Joueur
{
	private String nom;
	private boolean humain;
	private int score;
	private ArrayList<Domino> jeu;
	
	public Joueur(String nom, boolean humain)
	{
		this.humain = humain;
		this.nom = nom;
		this.score = 0;
		this.jeu = new ArrayList<Domino>();
	}
	
	public boolean estHumain()
	{
		return this.humain;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void penaliser()
	{
		this.score++;
	}
	
	public void ajouterAuScore(int nbPoints)
	{
		this.score += nbPoints;
	}
	
	public ArrayList<Domino> getJeu()
	{
		return this.jeu;
	}
	
	public boolean equals(Object object)
	{
		if (object instanceof Joueur)
		{
			Joueur joueur = (Joueur)object;
		
			if (joueur.getNom().equals(this.getNom()))
				return true;
		}
		
		return false;
	}
	
	public void jouer(Partie partie)
	{
		Domino domino = null;
		boolean horizontal = true, sens = true;
		int a = 0, b = 0, maximum = 0;
		
		for (Domino d : partie.getJoueurCourant().getJeu())
			for (int i = 0; i < partie.getPlateau().getTaille(); i++)
				for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
					if (partie.getPlateau().coupValide(i, j, d, true, true))
					{
						if (partie.getPlateau().nbLiaisons(i, j, true) > maximum)
						{
							domino = d;
							horizontal = true;
							sens = true;
							a = i;
							b = j;
							maximum = partie.getPlateau().nbLiaisons(i, j, true);
						}
					}
					else if (partie.getPlateau().coupValide(i, j, d, false, true))
					{
						if (partie.getPlateau().nbLiaisons(i, j, false) > maximum)
						{
							domino = d;
							horizontal = false;
							sens = true;
							a = i;
							b = j;
							maximum = partie.getPlateau().nbLiaisons(i, j, false);
						}
					}
					else if (partie.getPlateau().coupValide(i, j, d, true, false))
					{
						if (partie.getPlateau().nbLiaisons(i, j, true) > maximum)
						{
							domino = d;
							horizontal = true;
							sens = false;
							a = i;
							b = j;
							partie.getPlateau().nbLiaisons(i, j, true);
						}
					}
					else if (partie.getPlateau().coupValide(i, j, d, false, false))
					{
						if (partie.getPlateau().nbLiaisons(i, j, false) > maximum)
						{
							domino = d;
							horizontal = false;
							sens = false;
							a = i;
							b = j;
							maximum = partie.getPlateau().nbLiaisons(i, j, true);
						}
					}
		
		if (domino == null)
		{
			partie.joueurSuivant();
			partie.changerDomino();
		}
		else
		{
			partie.getPlateau().poserDomino(domino, horizontal, sens, a, b);
			partie.getJoueurCourant().getJeu().remove(domino);
			
			if (partie.getPlateau().nbLiaisons(a, b, horizontal) <= 1)
			{
				partie.joueurSuivant();
				partie.changerDomino();
			}
		}
	}
}
