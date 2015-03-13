import java.awt.Color;
import java.awt.Graphics;

public class Marque
{
	private int valeur;
	private Couleur couleur;
	private Color couleurFond;
	
	public Marque()
	{
		this.valeur = -1;
		this.couleur = Couleur.VIDE;
		this.couleurFond = Color.white;
	}
	
	public Marque(int valeur, Couleur couleur, Color color)
	{
		this.valeur = valeur;
		this.couleur = couleur;
		this.couleurFond = color;
	}
	
	public int getValeur()
	{
		return this.valeur;
	}
	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
	
	public Color getCouleurFond() {
		return couleurFond;
	}

	

	public boolean equals(Object object)
	{
		if (object instanceof Marque)
		{
			Marque marque = (Marque)object;
			
			if (this.getValeur() == marque.getValeur() && this.getCouleur().equals(marque.getCouleur()) )
				return true;
		}
		
		return false;
	}
	
	public void setCouleurFond(Color couleurFond) {
		this.couleurFond = couleurFond;
	}

	public void draw(Graphics g, int x, int y)
	{
		int r = 7;
		g.setColor(this.getCouleurFond());
		g.fillRect(x, y, 39, 39);
		if(this.getCouleurFond() != Color.BLACK){
			g.setColor(Color.BLACK);
		}
		else{
			g.setColor(Color.WHITE);
		}
		g.drawLine(x+39, y, x+39, y+39);
		g.drawLine(x, y+39, x+39, y+39);
		
		if (this.getCouleur().equals(Couleur.ROUGE))
			g.setColor(Color.RED);
		else if (this.getCouleur().equals(Couleur.BLEU))
			g.setColor(Color.GREEN.darker().darker());

		if (this.getValeur() == 1)
		{
			g.fillOval(x+15, y+15, r, r);
		}
		else if (this.getValeur() == 2)
		{
			g.fillOval(x+5, y+5, r, r);
			g.fillOval(x+25, y+25, r, r);
		}
		else if (this.getValeur() == 3)
		{
			g.fillOval(x+5, y+5, r, r);
			g.fillOval(x+15, y+15, r, r);
			g.fillOval(x+25, y+25, r, r);
		}
		else if (this.getValeur() == 4)
		{
			g.fillOval(x+5, y+5, r, r);
			g.fillOval(x+25, y+5, r, r);
			g.fillOval(x+25, y+25, r, r);
			g.fillOval(x+5, y+25, r, r);
		}
		else if (this.getValeur() == 5)
		{
			g.fillOval(x+5, y+5, r, r);
			g.fillOval(x+25, y+5, r, r);
			g.fillOval(x+25, y+25, r, r);
			g.fillOval(x+5, y+25, r, r);
			g.fillOval(x+15, y+15, r, r);
		}
		else if (this.getValeur() != 0)
			g.drawString(Integer.toString(this.getValeur()), x+5, y+15);
	}
}