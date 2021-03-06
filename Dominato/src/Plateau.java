import java.io.Serializable;

public class Plateau implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int taille;
	private Marque[] cases;
	
	public Plateau()
	{
		this.taille = 50;
		this.cases = new Marque[this.taille*this.taille];
		for (int i = 0; i < this.taille*this.taille; i++)
			this.cases[i] = new Marque();
	}
	
	public int getTaille()
	{
		return this.taille;
	}
	
	public Marque get(int i, int j)
	{
		return this.cases[this.getTaille()*i+j];
	}
	
	public boolean estVide()
	{
		for (int i = 0; i < this.getTaille(); i++)
			for (int j = 0; j < this.getTaille(); j++)
				if (!this.get(i, j).getCouleur().equals(Couleur.VIDE))
					return false;
		
		return true;
	}
	
	public boolean positionExiste(int x, int y, boolean horizontal)
	{
		if (horizontal)
		{
			if (x >= 0 && y >= 0 && x+3 < this.getTaille() && y+1 < this.getTaille())
				return true;
		}
		else
		{
			if (x >= 0 && y >= 0 && x+1 < this.getTaille() && y+3 < this.getTaille())
				return true;
		}
		
		return false;
	}
	
	public boolean positionEstLibre(int x, int y, boolean horizontal)
	{
		if (this.positionExiste(x, y, horizontal))
			if (horizontal)
			{
				for (int i = x; i <= x+3; i++)
					for (int j = y; j <= y+1; j++)
						if (!this.get(i, j).getCouleur().equals(Couleur.VIDE))
							return false;
			
				return true;
			}
			else
			{
				for (int i = x; i <= x+1; i++)
					for (int j = y; j <= y+3; j++)
						if (!this.get(i, j).getCouleur().equals(Couleur.VIDE))
							return false;
			
				return true;
			}
		
		return false;
	}
	
	public boolean positionEstOccupee(int x, int y, boolean horizontal)
	{
		if (this.positionExiste(x, y, horizontal))
			if (horizontal)
			{
				for (int i = x; i <= x+3; i++)
					for (int j = y; j <= y+1; j++)
						if ((i == x || i == x+2) && j == y)
						{
							if (this.get(i, j).getCouleur().equals(Couleur.VIDE) || this.get(i, j).getCouleur().equals(Couleur.PLEIN))
								return false;
						}
						else
						{
							if (!this.get(i, j).getCouleur().equals(Couleur.PLEIN))
								return false;
						}
			
				return true;
			}
			else
			{
				for (int i = x; i <= x+1; i++)
					for (int j = y; j <= y+3; j++)
						if (i == x && (j == y || j == y+2))
						{
							if (this.get(i, j).getCouleur().equals(Couleur.VIDE) || this.get(i, j).getCouleur().equals(Couleur.PLEIN))
								return false;
						}
						else
						{
							if (!this.get(i, j).getCouleur().equals(Couleur.PLEIN))
								return false;
						}	
				
				return true;
			}
		
		return false;
	}
	
	public boolean positionEstVide(int x, int y)
	{
		if (x >= 0 && y >= 0 && x < this.getTaille() && y < this.getTaille())
			if (!this.get(x, y).getCouleur().equals(Couleur.VIDE))
				return false;
		
		return true;
	}
	
	public boolean coupPossible(int x, int y, boolean horizontal)
	{
		if (!this.positionEstLibre(x, y, horizontal))
			return false;
		
		if (this.estVide())
			return true;
		
		if (horizontal)
		{
			if ((this.positionEstOccupee(x+1, y-4, false) || this.positionEstOccupee(x-1, y-2, true) || this.positionEstOccupee(x+1, y-2, true) || (this.positionEstVide(x, y-1) && this.positionEstVide(x+1, y-1) && this.positionEstVide(x+2, y-1) && this.positionEstVide(x+3, y-1)))
			&& (this.positionEstOccupee(x+1, y+2, false) || this.positionEstOccupee(x-1, y+2, true) || this.positionEstOccupee(x+1, y+2, true) || (this.positionEstVide(x, y+2) && this.positionEstVide(x+1, y+2) && this.positionEstVide(x+2, y+2) && this.positionEstVide(x+3, y+2)))
			&& (this.positionEstOccupee(x-2, y-1, false) || (this.positionEstVide(x-1, y) && this.positionEstVide(x-1, y+1)))
			&& (this.positionEstOccupee(x+4, y-1, false) || (this.positionEstVide(x+4, y) && this.positionEstVide(x+4, y+1))))
				if (this.positionEstOccupee(x+1, y-4, false) || this.positionEstOccupee(x-1, y-2, true) || this.positionEstOccupee(x+1, y-2, true) || this.positionEstOccupee(x+1, y+2, false) || this.positionEstOccupee(x-1, y+2, true) || this.positionEstOccupee(x+1, y+2, true) || this.positionEstOccupee(x-2, y-1, false) || this.positionEstOccupee(x+4, y-1, false))
					return true;
		}
		else
		{
			if ((this.positionEstOccupee(x-4, y+1, true) || this.positionEstOccupee(x-2, y-1, false) || this.positionEstOccupee(x-2, y+1, false) || (this.positionEstVide(x-1, y) && this.positionEstVide(x-1, y+1) && this.positionEstVide(x-1, y+2) && this.positionEstVide(x-1, y+3)))
			&& (this.positionEstOccupee(x+2, y+1, true) || this.positionEstOccupee(x+2, y-1, false) || this.positionEstOccupee(x+2, y+1, false) || (this.positionEstVide(x+2, y) && this.positionEstVide(x+2, y+1) && this.positionEstVide(x+2, y+2) && this.positionEstVide(x+2, y+3)))
			&& (this.positionEstOccupee(x-1, y-2, true) || (this.positionEstVide(x, y-1) && this.positionEstVide(x+1, y-1)))
			&& (this.positionEstOccupee(x-1, y+4, true) || (this.positionEstVide(x, y+4) && this.positionEstVide(x+1, y+4))))
				if (this.positionEstOccupee(x-1, y-2, true) || this.positionEstOccupee(x-2, y-1, false) || this.positionEstOccupee(x-2, y+1, false) || this.positionEstOccupee(x-1, y+4, true) || this.positionEstOccupee(x+2, y-1, false) || this.positionEstOccupee(x+2, y+1, false) || this.positionEstOccupee(x-4, y+1, true) || this.positionEstOccupee(x+2, y+1, true))
					return true;
		}
		
		return false;
	}
	
	public boolean coupValide(int x, int y, Domino domino, boolean horizontal, boolean sens)
	{
		if (this.coupPossible(x, y, horizontal))
			if (horizontal)
			{
				if (this.positionEstOccupee(x+1, y-4, false))
					if (!this.get(x+1, y-2).equals(domino.bilan()))
						return false;
				
				if (this.positionEstOccupee(x+1, y+2, false))
					if (!this.get(x+1, y+2).equals(domino.bilan()))
						return false;
					
				if (this.positionEstOccupee(x-2, y-1, false))
					if (sens)
					{
						if (!domino.getMarque1().equals(new Domino(this.get(x-2, y-1), this.get(x-2, y+1)).bilan()))
							return false;
					}
					else
					{
						if (!domino.getMarque2().equals(new Domino(this.get(x-2, y-1), this.get(x-2, y+1)).bilan()))
							return false;
					}
					
				if (this.positionEstOccupee(x+4, y-1, false))
					if (sens)
					{
						if (!domino.getMarque2().equals(new Domino(this.get(x+4, y-1), this.get(x+4, y+1)).bilan()))
							return false;
					}
					else
					{
						if (!domino.getMarque1().equals(new Domino(this.get(x+4, y-1), this.get(x+4, y+1)).bilan()))
							return false;
					}
					
				if (this.positionEstOccupee(x-1, y-2, true))
					if (sens)
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x-1, y-2), this.get(x+1, y-2)).bilan()) && this.get(x+1, y-2).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x-1, y-2), this.get(x+1, y-2)).bilan()) && this.get(x+1, y-2).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x+1, y-2, true))
					if (sens)
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x+1, y-2), this.get(x+3, y-2)).bilan()) && this.get(x+1, y-2).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x+1, y-2), this.get(x+3, y-2)).bilan()) && this.get(x+1, y-2).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x-1, y+2, true))
					if (sens)
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x-1, y+2), this.get(x+1, y+2)).bilan()) && this.get(x+1, y+2).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x-1, y+2), this.get(x+1, y+2)).bilan()) && this.get(x+1, y+2).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x+1, y+2, true))
					if (sens)
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x+1, y+2), this.get(x+3, y+2)).bilan()) && this.get(x+1, y+2).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x+1, y+2), this.get(x+3, y+2)).bilan()) && this.get(x+1, y+2).equals(domino.bilan())))
							return false;
					}
				
				return true;
			}
			else
			{
				if (this.positionEstOccupee(x-1, y-2, true))
					if (sens)
					{
						if (!domino.getMarque1().equals(new Domino(this.get(x-1, y-2), this.get(x+1, y-2)).bilan()))
							return false;
					}
					else
					{
						if (!domino.getMarque2().equals(new Domino(this.get(x-1, y-2), this.get(x+1, y-2)).bilan()))
							return false;
					}
					
				if (this.positionEstOccupee(x-1, y+4, true))
					if (sens)
					{
						if (!domino.getMarque2().equals(new Domino(this.get(x-1, y+4), this.get(x+1, y+4)).bilan()))
							return false;
					}
					else
					{
						if (!domino.getMarque1().equals(new Domino(this.get(x-1, y+4), this.get(x+1, y+4)).bilan()))
							return false;
					}
					
				if (this.positionEstOccupee(x-4, y+1, true))
					if (!this.get(x-2, y+1).equals(domino.bilan()))
						return false;
						
				if (this.positionEstOccupee(x+2, y+1, true))
					if (!this.get(x+2, y+1).equals(domino.bilan()))
						return false;
					
				if (this.positionEstOccupee(x-2, y-1, false))
					if (sens)
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x-2, y-1), this.get(x-2, y+1)).bilan()) && this.get(x-2, y+1).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x-2, y-1), this.get(x-2, y+1)).bilan()) && this.get(x-2, y+1).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x-2, y+1, false))
					if (sens)
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x-2, y+1), this.get(x-2, y+3)).bilan()) && this.get(x-2, y+1).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x-2, y+1), this.get(x-2, y+3)).bilan()) && this.get(x-2, y+1).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x+2, y-1, false))
					if (sens)
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x+2, y-1), this.get(x+2, y+1)).bilan()) && this.get(x+2, y+1).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x+2, y-1), this.get(x+2, y+1)).bilan()) && this.get(x+2, y+1).equals(domino.bilan())))
							return false;
					}
					
				if (this.positionEstOccupee(x+2, y+1, false))
					if (sens)
					{
						if (!(domino.getMarque2().equals(new Domino(this.get(x+2, y+1), this.get(x+2, y+3)).bilan()) && this.get(x+2, y+1).equals(domino.bilan())))
							return false;
					}
					else
					{
						if (!(domino.getMarque1().equals(new Domino(this.get(x+2, y+1), this.get(x+2, y+3)).bilan()) && this.get(x+2, y+1).equals(domino.bilan())))
							return false;
					}
					
				return true;
			}
		
		return false;
	}
	
	public int nbLiaisons(int x, int y, boolean horizontal)
	{
		int c = 0;
		
		if (horizontal)
		{
			if (this.positionEstOccupee(x+1, y-4, false))
				c++;
			
			if (this.positionEstOccupee(x+1, y+2, false))
				c++;
				
			if (this.positionEstOccupee(x-2, y-1, false))
				c++;
				
			if (this.positionEstOccupee(x+4, y-1, false))
				c++;
				
			if (this.positionEstOccupee(x-1, y-2, true))
				c += 2;
				
			if (this.positionEstOccupee(x+1, y-2, true))
				c += 2;
				
			if (this.positionEstOccupee(x-1, y+2, true))
				c += 2;
				
			if (this.positionEstOccupee(x+1, y+2, true))
				c += 2;
		}
		else
		{
			if (this.positionEstOccupee(x-1, y-2, true))
				c++;
				
			if (this.positionEstOccupee(x-1, y+4, true))
				c++;

			if (this.positionEstOccupee(x-4, y+1, true))
				c++;
					
			if (this.positionEstOccupee(x+2, y+1, true))
				c++;
				
			if (this.positionEstOccupee(x-2, y-1, false))
				c += 2;
				
			if (this.positionEstOccupee(x-2, y+1, false))
				c += 2;

			if (this.positionEstOccupee(x+2, y-1, false))
				c += 2;

			if (this.positionEstOccupee(x+2, y+1, false))
				c += 2;
		}

		return c;
	}
	
	public void poserDomino(Domino domino, boolean horizontal, boolean sens, int x, int y)
	{
		Marque plein = new Marque(-1, Couleur.PLEIN, domino.getMarque1().getCouleurFond(),0);
		
		for (int i = x; i <= x+1; i++)
			for (int j = y; j <= y+1; j++)
				this.cases[this.getTaille()*i+j] = plein;
		
		if (horizontal)
			for (int i = x+2; i <= x+3; i++)
				for (int j = y; j <= y+1; j++)
					this.cases[this.getTaille()*i+j] = plein;
		else
			for (int i = x; i <= x+1; i++)
				for (int j = y+2; j <= y+3; j++)
					this.cases[this.getTaille()*i+j] = plein;
		
		if (sens)
			this.cases[this.getTaille()*x+y] = domino.getMarque1();
		else
			this.cases[this.getTaille()*x+y] = domino.getMarque2();
		
		if (horizontal)
			if (sens)
				this.cases[this.getTaille()*(x+2)+y] = domino.getMarque2();
			else
				this.cases[this.getTaille()*(x+2)+y] = domino.getMarque1();
		else
			if (sens)
				this.cases[this.getTaille()*x+(y+2)] = domino.getMarque2();
			else
				this.cases[this.getTaille()*x+(y+2)] = domino.getMarque1();
	}

	@Override
	public String toString() {
		String ch = "";
		for (int i = 0; i < this.getTaille(); i++)
			for (int j = 0; j < this.getTaille(); j++){
				if(!this.positionEstVide(i, j)){
					ch += "M ";
				}
			}
		return "Plateau [taille=" + taille + ", cases="
				+ ch + "]";
	}
}