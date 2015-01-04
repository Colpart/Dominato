
import javax.swing.SwingUtilities;

import java.awt.AWTException;
import java.awt.Robot;
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
	
	
	public class BougerSouris extends Thread{
		private int xInit,yInit;
		BougerSouris(int xInit, int yInit){
			this.xInit = xInit;
			this.yInit = yInit;
		}
		
		public void run(){
			Robot r;
			try {
				r = new Robot();
				r.mouseMove(this.xInit, this.yInit);
			
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			if (SwingUtilities.isLeftMouseButton(event) || SwingUtilities.isMiddleMouseButton(event))
			{
				int x = -1, y = -1;
				boolean passer = false;
				
				if (SwingUtilities.isLeftMouseButton(event))
				{
					x = (int)event.getPoint().getX()/20;
					y = (int)event.getPoint().getY()/20;
					
				}
				else if (SwingUtilities.isMiddleMouseButton(event))
				{
					passer = true;
				}
				
				do
				{
					if (!this.partie.getJoueurCourant().estHumain())
					{
						Domino domino = null;
						boolean horizontal = true, sens = true;
						int maximum = 0;
						
						for (Domino d : partie.getJoueurCourant().getJeu())
							for (int i = 0; i < partie.getPlateau().getTaille(); i++)
								for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
									if (partie.getPlateau().coupValide(i, j, d, true, true))
									{
										if (partie.getPlateau().nbLiaisons(i, j, true) > maximum)
										{
											maximum = partie.getPlateau().nbLiaisons(i, j, true);
											domino = d;
											horizontal = true;
											sens = true;
											x = i;
											y = j;
										}
									}
									else if (partie.getPlateau().coupValide(i, j, d, false, true))
									{
										if (partie.getPlateau().nbLiaisons(i, j, false) > maximum)
										{
											maximum = partie.getPlateau().nbLiaisons(i, j, false);
											domino = d;
											horizontal = false;
											sens = true;
											x = i;
											y = j;
										}
									}
									else if (partie.getPlateau().coupValide(i, j, d, true, false))
									{
										if (partie.getPlateau().nbLiaisons(i, j, true) > maximum)
										{
											maximum = partie.getPlateau().nbLiaisons(i, j, true);
											domino = d;
											horizontal = true;
											sens = false;
											x = i;
											y = j;
										}
									}
									else if (partie.getPlateau().coupValide(i, j, d, false, false))
									{
										if (partie.getPlateau().nbLiaisons(i, j, false) > maximum)
										{
											maximum = partie.getPlateau().nbLiaisons(i, j, false);
											domino = d;
											horizontal = false;
											sens = false;
											x = i;
											y = j;
										}
									}
						
						if (domino == null)
						{
							passer = true;
						}
						else
						{
							while (!this.partie.getDominoSelectionne().equals(domino))
								this.partie.changerDomino();
							
							while (!(this.partie.dominoEstHorizontal() == horizontal && this.partie.dominoEstDansLeSens1() == sens))
								this.partie.changerSens();
						}
					}
					
					if (passer)
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
					else
					{
						if (this.partie.coupPossible(x, y))
						{
							if (this.partie.coupValide(x, y))
							{
								this.partie.poserDomino(x, y);
								this.partie.checkIfTerminee(this.partie.nbLiaisons(x, y));
							
								if (!this.partie.estTerminee())
								{
									this.partie.dernierCoupPasse(false);
									if (this.partie.nbLiaisons(x, y) <= 1)
										this.partie.joueurSuivant();					
									this.partie.changerDomino();
								}
								else
								{
									if (this.partie.nbLiaisons(x, y) > 2)
										this.partie.compterPoints(true);
									else
										this.partie.compterPoints();
								}
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
					
					if (this.partie.getJoueurCourant().estHumain()){
						this.panneau.compt = 0;
						this.panneau.paintImmediately(0, 0, this.panneau.getWidth(), this.panneau.getHeight());
					}
					
					
					if (!this.partie.getJoueurCourant().estHumain())
					{
						
						this.panneau.compt = 1;
						this.panneau.paintImmediately(0, 0, this.panneau.getWidth(), this.panneau.getHeight());
						
						BougerSouris b = new BougerSouris((int)event.getLocationOnScreen().getX()+150 , (int)event.getLocationOnScreen().getY());
						b.start();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
					
					
				} while (!this.partie.getJoueurCourant().estHumain() && !this.partie.estTerminee());
			}
			else if (SwingUtilities.isRightMouseButton(event))
			{
				this.partie.changerSens();
				this.panneau.repaint();
			}
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