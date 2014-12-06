import java.util.ArrayList;

public class Joueur
{
	private String nom;
	private int score;
	private ArrayList<Domino> jeu;
	
	public Joueur(String nom)
	{
		this.nom = nom;
		this.score = 0;
		this.jeu = new ArrayList<Domino>();
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
}
