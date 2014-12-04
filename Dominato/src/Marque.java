import java.awt.Color;
import java.awt.Graphics;

public class Marque
{
	private int valeur;
	private Couleur couleur;
	
	public Marque()
	{
		this.valeur = -1;
		this.couleur = Couleur.VIDE;
	}
	
	public Marque(int valeur, Couleur couleur)
	{
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	public int getValeur()
	{
		return this.valeur;
	}
	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
	
	public void draw(Graphics g, int x, int y)
	{
		if (this.getCouleur().equals(Couleur.ROUGE))
			g.setColor(Color.RED);
		else if (this.getCouleur().equals(Couleur.BLEU))
			g.setColor(Color.BLUE);
		else
			g.setColor(Color.WHITE);

		g.fillRect(x, y, 39, 39);
		
		if (this.getCouleur().equals(Couleur.BLANC))
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.WHITE);
		
		g.drawString(Integer.toString(this.getValeur()), x+5, y+15);
	}
}