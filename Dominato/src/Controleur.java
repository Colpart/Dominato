import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

class Controleur extends MouseAdapter
{
	private Panneau panneau;
	private Partie partie;
	
	Controleur(Panneau panneau, Partie partie)
	{
		this.panneau = panneau;
		this.partie = partie;
	}

	public void mouseMoved(MouseEvent event)
	{
		int x = (int)event.getPoint().getX()-10;
		int y = (int)event.getPoint().getY()-10;
		
		if (x >= 0 && x < 20*this.partie.getPlateau().getTaille() && y >= 0 && y < 20*this.partie.getPlateau().getTaille())
		{
			this.partie.setX(x);
			this.partie.setY(y);
		}
		
		this.panneau.repaint();
	}
	
	public void mousePressed(MouseEvent event)
	{
		if (!this.partie.estTerminee())
		{
			if (SwingUtilities.isLeftMouseButton(event))
			{
				int x = (int)event.getPoint().getX();
				int y = (int)event.getPoint().getY();
				
				if (this.partie.coupPossible(x/20, y/20))
				{
					if (this.partie.coupValide(x/20, y/20))
					{
						this.partie.poserDomino(x/20, y/20);
						this.partie.checkIfTerminee(this.partie.nbLiaisons(x/20, y/20));
						
						if (!this.partie.estTerminee())
						{
							this.partie.dernierCoupPasse(false);
							if (this.partie.nbLiaisons(x/20, y/20) <= 1)
								this.partie.joueurSuivant();
							this.partie.changerDomino();
						}
						else
							this.partie.compterPoints();
					}
					else
					{
						this.partie.getJoueurCourant().penaliser();
						this.partie.dernierCoupPasse(false);
						this.partie.joueurSuivant();
						this.partie.changerDomino();
					}
				}
			}
			else if (SwingUtilities.isMiddleMouseButton(event))
			{
				this.partie.dernierCoupPasse(true);
				
				if (!this.partie.estTerminee())
				{
					this.partie.joueurSuivant();
					this.partie.changerDomino();
				}
				else
					this.partie.compterPoints();
			}
			else if (SwingUtilities.isRightMouseButton(event))
			{
				this.partie.changerSens();
			}
				
			this.panneau.repaint();
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent event)
	{
		if (!this.partie.estTerminee())
		{
	        if (event.getWheelRotation() < 0)
	        	this.partie.changerDomino(true);
	        else
	        	this.partie.changerDomino(false);
	        	
	        this.panneau.repaint();
		}
    } 
}