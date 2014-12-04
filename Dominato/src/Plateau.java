public class Plateau
{
	private int taille;
	private Marque[] cases;
	
	public Plateau()
	{
		this.taille = 20;
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
	
	public boolean positionEstlibre(int x, int y)
	{
		if (x >= 0 && y >= 0 && x < this.getTaille() && y < this.getTaille())
			if (this.get(x, y).getCouleur().equals(Couleur.VIDE))
				return true;
		
		return false;
	}
	
	public boolean positionEstLibre(int x, int y, boolean horizontal)
	{
		if (horizontal && x >= 0 && y >= 0 && x+3 < this.getTaille() && y+1 < this.getTaille())
		{
			for (int i = x; i <= x+3; i++)
				for (int j = y; j <= y+1; j++)
					if (!this.get(i, j).getCouleur().equals(Couleur.VIDE))
						return false;
			
			return true;
		}
		else if (!horizontal && x >= 0 && y >= 0 && x+1 < this.getTaille() && y+3 < this.getTaille())
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
		if (horizontal && x >= 0 && y >= 0 && x+3 < this.getTaille() && y+1 < this.getTaille())
		{
			for (int i = x; i <= x+3; i++)
				for (int j = y; j <= y+1; j++)
					if ((((i == x || i == x+2) && j == y) && (this.get(i, j).getCouleur().equals(Couleur.VIDE) || this.get(i, j).getCouleur().equals(Couleur.PLEIN))) || (!((i == x || i == x+2) && j == y) && !this.get(i, j).getCouleur().equals(Couleur.PLEIN)))
						return false;
			
			return true;
		}
		else if (!horizontal && x >= 0 & y >= 0 && x+1 < this.getTaille() && y+3 < this.getTaille())
		{
			for (int i = x; i <= x+1; i++)
				for (int j = y; j <= y+3; j++)
					if (((i == x && (j == y || j == y+2)) && (this.get(i, j).getCouleur().equals(Couleur.VIDE) || this.get(i, j).getCouleur().equals(Couleur.PLEIN))) || (!(i == x && (j == y || j == y+2)) && !this.get(i, j).getCouleur().equals(Couleur.PLEIN)))
						return false;
			
			return true;
		}
		
		return false;
	}
	
	public boolean coupPossible(int x, int y, boolean horizontal)
	{
		if (!this.positionEstLibre(x, y, horizontal))
			return false;
		
		if (this.estVide())
			return true;
		
		if (horizontal)
		{
			/* this.positionEstOccupee(x-1, y-2, true) || this.positionEstOccupee(x+1, y-2, true) || this.positionEstOccupee(x-1, y+2, true) || this.positionEstOccupee(x+1, y+2, true) || */
			if ((this.positionEstOccupee(x-2, y-1, false) || this.positionEstLibre(x-2, y-1, false)) && (this.positionEstOccupee(x+4, y-1, false) || this.positionEstLibre(x+4, y-1, false)) && (this.positionEstOccupee(x+1, y-4, false) || this.positionEstLibre(x+1, y-4, false)) && (this.positionEstOccupee(x+1, y+2, false) || this.positionEstLibre(x+1, y+2, false)))
				if (this.positionEstOccupee(x-2, y-1, false) || this.positionEstOccupee(x+4, y-1, false) || this.positionEstOccupee(x+1, y-4, false) || this.positionEstOccupee(x+1, y+2, false))
					return true;
		}
		else
		{
			if ((this.positionEstOccupee(x-4, y+1, true) || this.positionEstLibre(x-4, y+1, true)) && (this.positionEstOccupee(x+2, y+1, true) || this.positionEstLibre(x+2, y+1, true)) && (this.positionEstOccupee(x-1, y-2, true) || this.positionEstLibre(x-1, y-2, true)) && (this.positionEstOccupee(x-1, y+4, true) || this.positionEstLibre(x-1, y+4, true)))
				if (this.positionEstOccupee(x-4, y+1, true) || this.positionEstOccupee(x+2, y+1, true) || this.positionEstOccupee(x-1, y-2, true) || this.positionEstOccupee(x-1, y+4, true))
					return true;
		}
		
		return false;
	}
	
	public void poseDomino(Domino domino, boolean horizontal, boolean ordre, int x, int y)
	{
		Marque plein = new Marque(-1, Couleur.PLEIN);
		
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
		
		/* for (int i = x; i <= x+1; i++)
			for (int j = y; j <= y+1; j++)
				if (this.ordre)
					this.cases[this.taille*i+j] = domino.getMarque1();
				else
					this.cases[this.taille*i+j] = domino.getMarque2();
		
		if (this.horizontal)
			for (int i = x+2; i <= x+3; i++)
				for (int j = y; j <= y+1; j++)
					if (this.ordre)
						this.cases[this.taille*i+j] = domino.getMarque2();
					else
						this.cases[this.taille*i+j] = domino.getMarque1();
		else
			for (int i = x; i <= x+1; i++)
				for (int j = y+2; j <= y+3; j++)
					if (this.ordre)
						this.cases[this.taille*i+j] = domino.getMarque2();
					else
						this.cases[this.taille*i+j] = domino.getMarque1(); */
		
		if (ordre)
			this.cases[this.getTaille()*x+y] = domino.getMarque1();
		else
			this.cases[this.getTaille()*x+y] = domino.getMarque2();
		
		if (horizontal)
			if (ordre)
				this.cases[this.getTaille()*(x+2)+y] = domino.getMarque2();
			else
				this.cases[this.getTaille()*(x+2)+y] = domino.getMarque1();
		else
			if (ordre)
				this.cases[this.getTaille()*x+(y+2)] = domino.getMarque2();
			else
				this.cases[this.getTaille()*x+(y+2)] = domino.getMarque1();
	}
}