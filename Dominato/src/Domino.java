import java.awt.Color;

public class Domino
{
	private Marque marque1;
	private Marque marque2;
	
	public Domino()
	{
		this.marque1 = new Marque(0, Couleur.BLANC, Color.WHITE);
		this.marque2 = new Marque(0, Couleur.BLANC, Color.WHITE);
	}
	
	public Domino(Marque marque1, Marque marque2)
	{
		this.marque1 = marque1;
		this.marque2 = marque2;
	}
	
	public Domino(int valeur1, Couleur couleur1, int valeur2, Couleur couleur2, Color color)
	{
		this.marque1 = new Marque(valeur1, couleur1, color);
		this.marque2 = new Marque(valeur2, couleur2, color);
	}
	
	public Marque getMarque1()
	{
		return this.marque1;
	}
	
	public Marque getMarque2()
	{
		return this.marque2;
	}
	
	public boolean equals(Object object)
	{
		if (object instanceof Domino)
		{
			Domino domino = (Domino)object;
			
			if (this.getMarque1().equals(domino.getMarque1()) && this.getMarque2().equals(domino.getMarque2()))
				return true;
			else if (this.getMarque1().equals(domino.getMarque2()) && this.getMarque2().equals(domino.getMarque1()))
				return true;
		}
			
		return false;
	}
	
	public Marque bilan()
	{
		Marque marque;
		
		if (this.marque1.getCouleur().equals(this.marque2.getCouleur()))
			marque = new Marque(this.marque1.getValeur()+this.marque2.getValeur(), this.marque1.getCouleur(),Color.white);
		else if (this.marque1.getValeur() == this.marque2.getValeur())
			marque = new Marque(0, Couleur.BLANC, Color.WHITE);
		else
			if (this.marque1.getValeur() > this.marque2.getValeur())
				marque = new Marque(this.marque1.getValeur()-this.marque2.getValeur(), this.marque1.getCouleur(), this.marque1.getCouleurFond());
			else
				marque = new Marque(this.marque2.getValeur()-this.marque1.getValeur(), this.marque2.getCouleur(), this.marque1.getCouleurFond());
		
		return marque;
	}

	@Override
	public String toString() {
		return "Domino [marque1=" + marque1 + ", marque2=" + marque2 + "]";
	}
}