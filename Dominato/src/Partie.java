import java.awt.Color;
import java.util.ArrayList;

public class Partie
{
	private Plateau plateau;
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int joueurCourant;
	private ArrayList<Domino> pioche;
	private Domino dominoSelectionne;
	private boolean horizontal;
	private boolean sens;
	private int x;
	private int y;
	private int terminee;
	
	public Partie(int nbJoueurs, boolean[] type)
	{
		this.plateau = new Plateau();
		this.nbJoueurs = nbJoueurs;
		this.joueurs = new Joueur[nbJoueurs];
		for (int k = 0; k < nbJoueurs; k++)
			this.joueurs[k] = new Joueur("Joueur "+(k+1), type[k]);
		this.joueurCourant = 0;
		this.pioche = this.initialiserPioche();
		this.distribuer();	
		this.dominoSelectionne = null;
		this.changerDomino();
		this.horizontal = true;
		this.sens = true;
		this.x = 0;
		this.y = 0;
		this.terminee = 0;
	}
	
	Joueur getJoueurNum(int i){
		return this.joueurs[i];
	}
	
	public boolean estTerminee()
	{
		if (this.terminee == 1)
			return true;
		else
			return false;
	}
	
	public void checkIfTerminee(int nbLiaisons)
	{
		if (nbLiaisons >= 3)
			this.terminee = 1;
		
		for (int k = 0; k < this.nbJoueurs; k++)
			if (this.getJoueur(k).getJeu().isEmpty())
				this.terminee = 1;
	}
	
	public int getjoueurcourant(){
		return this.joueurCourant;
	}
	
	public void dernierCoupPasse(boolean passe)
	{
		if (!passe)
			this.terminee = 0;
		else
			if (this.terminee == 0)
				this.terminee = 2;
			else if (this.terminee == 2)
				this.terminee = 1;
	}
	
	public void compterPoints()
	{
		this.compterPoints(false);
	}
	
	public void compterPoints(boolean triple)
	{
		if (triple)
		{
			for (int k = 0; k < this.getNbJoueurs(); k++)
				if (!this.getJoueur(k).equals(this.getJoueurCourant()))
					this.getJoueur(k).ajouterAuScore(this.getJoueur(k).getJeu().size()+10);
		}
		else
			for (int k = 0; k < this.getNbJoueurs(); k++)
				this.getJoueur(k).ajouterAuScore(this.getJoueur(k).getJeu().size());
		
		int l = 0;
		
		for (int k = 0; k < this.getNbJoueurs(); k++)
			if (this.getJoueur(k).getScore() < this.getJoueur(l).getScore())
				l = k;
		
		while (!this.getJoueurCourant().equals(this.getJoueur(l)))
			this.joueurSuivant();
	}
	
	public int getNbJoueurs()
	{
		return this.nbJoueurs;
	}
	
	public Joueur getJoueur(int k)
	{
		return this.joueurs[k];
	}
	
	public Joueur getJoueurCourant()
	{
		return this.joueurs[this.joueurCourant];
	}
	
	public void setJoueurCourant(int joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void joueurSuivant()
	{
		if (this.joueurCourant == this.nbJoueurs-1)
			this.joueurCourant = 0;
		else
			this.joueurCourant++;
	}
	
	
	public Color couleurCourante(){
		if(this.getjoueurcourant() == 0){
			return Color.WHITE;
		}
		if(this.getjoueurcourant() == 1){
			Color tmp = new Color(230, 230, 230);
			return tmp;
		}
		if(this.getjoueurcourant() == 2){
			Color tmp = new Color(210, 210, 210);
			return tmp;
		}
		if(this.getjoueurcourant() == 3){
			Color tmp = new Color(190,190,190);
			return tmp;
		}
		return null;
	}
	
	public Color couleurCourante(int i){
		if(i == 0){
			return Color.WHITE;
		}
		else if(i == 1){
			Color tmp = new Color(230, 230, 230);
			return tmp;
		}
		else if(i == 2){
			Color tmp = new Color(210, 210, 210);
			return tmp;
		}
		else if(i == 3){
			Color tmp = new Color(190,190,190);
			return tmp;
		}
		return null;
	}
	
	
	public ArrayList<Domino> initialiserPioche()
	{
		ArrayList<Domino> pioche = new ArrayList<Domino>();
		Domino domino;
		
		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (!(i == 0 && j == 0))
					if (i == 0 || j == 0)
					{
						domino = new Domino(i+j, Couleur.ROUGE, 0, Couleur.BLANC, this.couleurCourante());
						
						if (!pioche.contains(domino))
						pioche.add(domino);
				
						domino = new Domino(0, Couleur.BLANC, i+j, Couleur.VERT, this.couleurCourante());
				
						if (!pioche.contains(domino))
							pioche.add(domino);
					}
					else
					{
						domino = new Domino(i, Couleur.ROUGE, j, Couleur.VERT,this.couleurCourante());
				
						if (!pioche.contains(domino))
								pioche.add(domino);
				
						domino = new Domino(j, Couleur.ROUGE, i, Couleur.VERT,this.couleurCourante());
				
						if (!pioche.contains(domino))
								pioche.add(domino);
						
						domino = new Domino(i, Couleur.ROUGE, j, Couleur.ROUGE,this.couleurCourante());
					
						if (domino.bilan().getValeur() <= 4)
							if (!pioche.contains(domino))
								pioche.add(domino);
				
						domino = new Domino(i, Couleur.VERT, j, Couleur.VERT,this.couleurCourante());
				
						if (domino.bilan().getValeur() <= 4)
							if (!pioche.contains(domino))
								pioche.add(domino);
					}
		
		return pioche;
	}
	
	public void distribuer()
	{
		while (!pioche.isEmpty()){
			for (int i = 0; i < this.nbJoueurs; i++){
				if (!pioche.isEmpty())
				{
					Domino domino = pioche.get((int)(Math.random()*pioche.size()));
					domino.getMarque1().setCouleurFond(this.couleurCourante(i));
					domino.getMarque2().setCouleurFond(this.couleurCourante(i));
					this.joueurs[i].getJeu().add(domino);
					this.pioche.remove(domino);
				}
			}
		}
		for (int i = 0; i < this.nbJoueurs; i++){
			this.trierDominos1(this.joueurs[i].getJeu());
			this.trierDominos2(this.joueurs[i].getJeu());
		}
	}
	
	/* Tri des dominos pour la marque 1 */
	public void trierDominos1(ArrayList<Domino> al){
		int longueur = al.size();
		Domino tampon;
		boolean permut;
 
		do {
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				if (al.get(i).getMarque1().getValeur() > al.get(i+1).getMarque1().getValeur()){
					tampon = al.get(i);
					al.set(i, al.get(i+1));
					al.set(i+1, tampon);
					permut = true;
				}
			}
		} while (permut);
	}
	
	/* Retourne les dominos qui ont comme valeur dans la marque1  valeurMarque1 */
	public ArrayList<Domino> getDominosParValeur(ArrayList<Domino> al, int valeurMarque1){
		ArrayList<Domino> tmp = new ArrayList<Domino>();
		for(Domino d : al){
			if(d.getMarque1().getValeur() == valeurMarque1){
				tmp.add(d);
			}
		}
		return tmp;
	}
	

	/* Tri des dominos pour la marque 1 */
	public void trierDominos2Bis(ArrayList<Domino> al){
		int longueur = al.size();
		Domino tampon;
		boolean permut;
 
		do {
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				if (al.get(i).getMarque2().getValeur() > al.get(i+1).getMarque2().getValeur()) {
					tampon = al.get(i);
					al.set(i, al.get(i+1));
					al.set(i+1, tampon);
					permut = true;
				}
			}
		} while (permut);
	}

	
	/* Tri des dominos pour la marque 2 */
	public void trierDominos2(ArrayList<Domino> al){
		ArrayList<Domino> tmp0 = getDominosParValeur(al, 0);
		trierDominos2Bis(tmp0);
		ArrayList<Domino> tmp1 = getDominosParValeur(al, 1);
		trierDominos2Bis(tmp1);
		ArrayList<Domino> tmp2 = getDominosParValeur(al, 2);
		trierDominos2Bis(tmp2);
		ArrayList<Domino> tmp3 = getDominosParValeur(al, 3);
		trierDominos2Bis(tmp3);
		ArrayList<Domino> tmp4 = getDominosParValeur(al, 4);
		trierDominos2Bis(tmp4);
		al.clear();
		al.addAll(tmp0);
		al.addAll(tmp1);
		al.addAll(tmp2);
		al.addAll(tmp3);
		al.addAll(tmp4);
	}
	
	public Domino getDominoSelectionne()
	{
		return dominoSelectionne;
	}
	
	public void changerDomino(boolean up)
	{
		int k = 0;
		
		if (this.getJoueurCourant().getJeu().contains(this.getDominoSelectionne()))
		{
			k = this.getJoueurCourant().getJeu().indexOf(this.getDominoSelectionne());
			
			if (up)
			{
				if (k == this.getJoueurCourant().getJeu().size()-1)
					k = 0;
				else
					k++;
			}
			else
			{
				if (k == 0)
					k = this.getJoueurCourant().getJeu().size()-1;
				else
					k--;
			}
		}
		
		this.dominoSelectionne = this.getJoueurCourant().getJeu().get(k);
	}
	
	public void changerDomino()
	{
		this.changerDomino(true);
	}
	
	public void changerDominoAleatoire()
	{
		int valeur1, valeur2;
		Couleur couleur1, couleur2;
		
		do
		{
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
					couleur1 = Couleur.VERT;
			}
			
			if (valeur2 == 0)
				couleur2 = Couleur.BLANC;
			else
			{
				if ((int)(Math.random()*2) == 0)
					couleur2 = Couleur.ROUGE;
				else
					couleur2 = Couleur.VERT;
			}
			
			this.dominoSelectionne = new Domino(valeur1, couleur1, valeur2, couleur2, this.couleurCourante());
		} while (!(this.dominoSelectionne.bilan().getValeur() <= 4));
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
	
	public int nbLiaisons(int x, int y)
	{
		return this.getPlateau().nbLiaisons(x, y, this.dominoEstHorizontal());
	}
	
	public void poserDomino(int x, int y)
	{
		this.plateau.poserDomino(this.getDominoSelectionne(), this.dominoEstHorizontal(), this.dominoEstDansLeSens1(), x, y);
		this.getJoueurCourant().getJeu().remove(this.getDominoSelectionne());
	}

	public void setDominoSelectionne(Domino dominoSelectionne) {
		this.dominoSelectionne = dominoSelectionne;
	}
}