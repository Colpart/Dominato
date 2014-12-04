public class Domino
{
	private Marque marque1;
	private Marque marque2;
	
	public Domino()
	{
		this.marque1 = new Marque(0, Couleur.BLANC);
		this.marque2 = new Marque(0, Couleur.BLANC);
	}
	
	public Domino(Marque marque1, Marque marque2)
	{
		this.marque1 = marque1;
		this.marque2 = marque2;
	}
	
	public Domino(int valeur1, Couleur couleur1, int valeur2, Couleur couleur2)
	{
		this.marque1 = new Marque(valeur1, couleur1);
		this.marque2 = new Marque(valeur2, couleur2);
	}
	
	public Marque getMarque1()
	{
		return this.marque1;
	}
	
	public Marque getMarque2()
	{
		return this.marque2;
	}
	
	public Marque getBilan()
	{
		Marque marque;
		
		if (this.marque1.getCouleur().equals(this.marque2.getCouleur()))
			marque = new Marque(this.marque1.getValeur()+this.marque2.getValeur(), this.marque1.getCouleur());
		else if (this.marque1.getValeur() == this.marque2.getValeur())
			marque = new Marque(0, Couleur.BLANC);
		else
			if (this.marque1.getValeur() > this.marque2.getValeur())
				marque = new Marque(this.marque1.getValeur()-this.marque2.getValeur(), this.marque1.getCouleur());
			else
				marque = new Marque(this.marque2.getValeur()-this.marque1.getValeur(), this.marque2.getCouleur());
		
		return marque;
	}
}