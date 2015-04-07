import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

public class Marque implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int valeur;
	private Couleur couleur;
	private boolean horizontal;
	private Color couleurFond;
	private int affichage;
	
	public Marque()
	{
		this.valeur = -1;
		this.couleur = Couleur.VIDE;
		this.horizontal = true;
		this.couleurFond = Color.white;
		affichage = 0;
	}
	
	public Marque(int valeur, Couleur couleur, Color color, int affichage)
	{
		this.valeur = valeur;
		this.couleur = couleur;
		this.horizontal = true;
		this.couleurFond = color;
		this.affichage = affichage;
	}
	
	public Marque(int valeur, Couleur couleur, boolean horizontal, Color color, int affichage)
	{
		this.valeur = valeur;
		this.couleur = couleur;
		this.horizontal = horizontal;
		this.couleurFond = color;
		this.affichage = affichage;
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
	
	public void setHorizontal(boolean h)
	{
		horizontal = h;
	}
	
	public void draw(Graphics g, int x, int y, int affichage)
	{
		
		this.affichage = affichage;
		/* Dominos Avec Puissance de a */
		if(this.affichage == 3){
			int codeCouleur = 0;
			Font fonteBase = new Font("monospace",Font.BOLD,16);
			Font fontePuissance = new Font("monospace",Font.BOLD,10);
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
			
			
			if (this.getCouleur().equals(Couleur.ROUGE)){
				g.setColor(Color.RED);
			}
			else if (this.getCouleur().equals(Couleur.VERT)){
				g.setColor(Color.GREEN.darker().darker());
				codeCouleur = 1;
			}
	
			
			
			if (this.getValeur() == 0)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
			else if (this.getValeur() == 1)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
			else if (this.getValeur() == 2)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
			else if (this.getValeur() == 3)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
			else if (this.getValeur() == 4)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
			else if (this.getValeur() != 0){
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+23, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString("a", x+13, y+27);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+23, y+18);
				}
			}
		}
		
		
		/* Dominos Avec Puissance de 10 */
		if(this.affichage == 2){
			int codeCouleur = 0;
			Font fonteBase = new Font("monospace",Font.BOLD,16);
			Font fontePuissance = new Font("monospace",Font.BOLD,11);
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
			
			
			if (this.getCouleur().equals(Couleur.ROUGE)){
				g.setColor(Color.RED);
			}
			else if (this.getCouleur().equals(Couleur.VERT)){
				g.setColor(Color.GREEN.darker().darker());
				codeCouleur = 1;
			}
	
			
			
			if (this.getValeur() == 0)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
			else if (this.getValeur() == 1)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
			else if (this.getValeur() == 2)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
			else if (this.getValeur() == 3)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
			else if (this.getValeur() == 4)
			{
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
			else if (this.getValeur() != 0){
				if(codeCouleur == 0){
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.RED);
					g.setFont(fontePuissance);
					g.drawString(Integer.toString(this.getValeur()), x+25, y+18);
				}
				else{
					g.setFont(fonteBase);
					g.setColor(Color.black);
					g.drawString(Integer.toString(10), x+4, y+30);
					g.setColor(Color.GREEN.darker().darker());
					g.setFont(fontePuissance);
					g.drawString("-"+Integer.toString(this.getValeur()), x+25, y+18);
				}
			}
		}

		
		
		
	
		/* Dominos Avec Nombre */
		if(this.affichage==1){
			int codeCouleur = 0;
			Font fonte = new Font("monospace",Font.BOLD,16);
			g.setFont(fonte);
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
			
			
			if (this.getCouleur().equals(Couleur.ROUGE)){
				g.setColor(Color.RED);
			}
			else if (this.getCouleur().equals(Couleur.VERT)){
				g.setColor(Color.GREEN.darker().darker());
				codeCouleur = 1;
			}
	
			if (this.getValeur() == 1)
			{
				if(codeCouleur == 0)
					g.drawString(Integer.toString(this.getValeur()), x+14, y+25);
				else
					g.drawString("-"+Integer.toString(this.getValeur()), x+10, y+25);
			}
			else if (this.getValeur() == 2)
			{
				if(codeCouleur == 0)
					g.drawString(Integer.toString(this.getValeur()), x+14, y+25);
				else
					g.drawString("-"+Integer.toString(this.getValeur()), x+10, y+25);
			}
			else if (this.getValeur() == 3)
			{
				if(codeCouleur == 0)
					g.drawString(Integer.toString(this.getValeur()), x+14, y+25);
				else
					g.drawString("-"+Integer.toString(this.getValeur()), x+10, y+25);
			}
			else if (this.getValeur() == 4)
			{
				if(codeCouleur == 0)
					g.drawString(Integer.toString(this.getValeur()), x+14, y+25);
				else
					g.drawString("-"+Integer.toString(this.getValeur()), x+10, y+25);
			}
			else if (this.getValeur() != 0){
				if(codeCouleur == 0)
					g.drawString(Integer.toString(this.getValeur()), x+14, y+25);
				else
					g.drawString("-"+Integer.toString(this.getValeur()), x+10, y+25);
			}
		}
	
		
		
		
		/* Dominos Avec points  */
		if(affichage == 0){
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
			else if (this.getCouleur().equals(Couleur.VERT))
				g.setColor(Color.GREEN.darker().darker());
	
			if (this.getValeur() == 1)
			{
				g.fillOval(x+15, y+15, r, r);
			}
			else if (this.getValeur() == 2)
			{
				if (this.horizontal == false)
				{
					g.fillOval(x+25, y+5, r, r);
					g.fillOval(x+5, y+25, r, r);
				}
				else
				{
					g.fillOval(x+5, y+5, r, r);
					g.fillOval(x+25, y+25, r, r);
				}
			}
			else if (this.getValeur() == 3)
			{
				if (this.horizontal == false)
				{
					g.fillOval(x+5, y+25, r, r);
					g.fillOval(x+15, y+15, r, r);
					g.fillOval(x+25, y+5, r, r);
				}
				else
				{
					g.fillOval(x+5, y+5, r, r);
					g.fillOval(x+15, y+15, r, r);
					g.fillOval(x+25, y+25, r, r);
				}
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
}