
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
		int x = 0, y = 0;
		
		if (this.partie.dominoEstHorizontal() && this.partie.dominoEstDansLeSens1())
		{ 
			x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
			y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
		}
		else if (this.partie.dominoEstHorizontal() && !this.partie.dominoEstDansLeSens1())
		{
			x = (int)event.getPoint().getX()-50-this.panneau.getLargeur();
			y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
		}
		else if (!this.partie.dominoEstHorizontal() && this.partie.dominoEstDansLeSens1())
		{
			x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
			y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
		}
		else if (!this.partie.dominoEstHorizontal() && !this.partie.dominoEstDansLeSens1())
		{
			x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
			y = (int)event.getPoint().getY()-50-this.panneau.getHauteur();
		}
		
		// if (x >= 0 && x < 20*this.partie.getPlateau().getTaille() && y >= 0 && y < 20*this.partie.getPlateau().getTaille())
		// {
			this.partie.setX(x);
			this.partie.setY(y);
		// }
		
		this.panneau.repaint();
		this.panneau.getAffJoueurs().repaint();
	}
	
	public void mouseClicked(MouseEvent event)
	{	
		if (!this.partie.estTerminee())
		{
			if (SwingUtilities.isLeftMouseButton(event) || SwingUtilities.isMiddleMouseButton(event))
			{
				int x = -1, y = -1;
				boolean passer = false;
				
				if (SwingUtilities.isLeftMouseButton(event))
				{
					x = ((int)event.getPoint().getX()-this.panneau.getLargeur())/20+panneau.getDebutX();
					y = ((int)event.getPoint().getY()-this.panneau.getHauteur())/20+panneau.getDebutY();
					
					if (!this.partie.dominoEstDansLeSens1())
					{
						if (this.partie.dominoEstHorizontal())
							x -= 2;
						else
							y -= 2;
					}
					
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
						
						if(this.partie.getNiveau() == 1){
							for (Domino d : partie.getJoueurCourant().getJeu())
								for (int i = 0; i < partie.getPlateau().getTaille(); i++)
									for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
										if (partie.getPlateau().coupValide(i, j, d, true, true))
										{
											if (partie.getPlateau().nbLiaisons(i, j, true) == 1)
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == 1)
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
											if (partie.getPlateau().nbLiaisons(i, j, true) == 1)
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == 1)
											{
												maximum = partie.getPlateau().nbLiaisons(i, j, false);
												domino = d;
												horizontal = false;
												sens = false;
												x = i;
												y = j;
											}
										}
						}
						if(this.partie.getNiveau() == 2){
							
							for (Domino d : partie.getJoueurCourant().getJeu())
								for (int i = 0; i < partie.getPlateau().getTaille(); i++)
									for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
										if (partie.getPlateau().coupValide(i, j, d, true, true))
										{
											if (partie.getPlateau().nbLiaisons(i, j, true) == this.partie.getIndiceNiveauDeux())
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == this.partie.getIndiceNiveauDeux())
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
											if (partie.getPlateau().nbLiaisons(i, j, true) == this.partie.getIndiceNiveauDeux())
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == this.partie.getIndiceNiveauDeux())
											{
												maximum = partie.getPlateau().nbLiaisons(i, j, false);
												domino = d;
												horizontal = false;
												sens = false;
												x = i;
												y = j;
											}
										}
							if(this.partie.getIndiceNiveauDeux() == 2){
								this.partie.setIndiceNiveauDeux(1);
							}
							
							if(domino == null){
								for (Domino d : partie.getJoueurCourant().getJeu())
									for (int i = 0; i < partie.getPlateau().getTaille(); i++)
										for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
											if (partie.getPlateau().coupValide(i, j, d, true, true))
											{
												if (partie.getPlateau().nbLiaisons(i, j, true) == this.partie.getIndiceNiveauDeux())
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
												if (partie.getPlateau().nbLiaisons(i, j, false) == this.partie.getIndiceNiveauDeux())
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
												if (partie.getPlateau().nbLiaisons(i, j, true) == this.partie.getIndiceNiveauDeux())
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
												if (partie.getPlateau().nbLiaisons(i, j, false) == this.partie.getIndiceNiveauDeux())
												{
													maximum = partie.getPlateau().nbLiaisons(i, j, false);
													domino = d;
													horizontal = false;
													sens = false;
													x = i;
													y = j;
												}
											}
								
							}
						}	
						/* Niveau 3 */
						
						if(this.partie.getNiveau() == 3){
							
							for (Domino d : partie.getJoueurCourant().getJeu())
								for (int i = 0; i < partie.getPlateau().getTaille(); i++)
									for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
										if (partie.getPlateau().coupValide(i, j, d, true, true))
										{
											if (partie.getPlateau().nbLiaisons(i, j, true) == 2)
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == 2)
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
											if (partie.getPlateau().nbLiaisons(i, j, true) == 2)
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
											if (partie.getPlateau().nbLiaisons(i, j, false) == 2)
											{
												maximum = partie.getPlateau().nbLiaisons(i, j, false);
												domino = d;
												horizontal = false;
												sens = false;
												x = i;
												y = j;
											}
										}
							
							if(domino == null){
								for (Domino d : partie.getJoueurCourant().getJeu())
									for (int i = 0; i < partie.getPlateau().getTaille(); i++)
										for (int j = 0; j < partie.getPlateau().getTaille(); j++)			
											if (partie.getPlateau().coupValide(i, j, d, true, true))
											{
												if (partie.getPlateau().nbLiaisons(i, j, true) == 1)
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
												if (partie.getPlateau().nbLiaisons(i, j, false) == 1)
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
												if (partie.getPlateau().nbLiaisons(i, j, true) == 1)
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
												if (partie.getPlateau().nbLiaisons(i, j, false) == 1)
												{
													maximum = partie.getPlateau().nbLiaisons(i, j, false);
													domino = d;
													horizontal = false;
													sens = false;
													x = i;
													y = j;
												}
											}
								
							}
						}	
						
						/* Niveau 4 */
						if(this.partie.getNiveau() == 4){
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
							
						}
						
						if (domino == null)
						{
							passer = true;
						}
						else
						{
							passer = false;
							
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
									if(this.partie.getNbJoueurs() == 1){
										if (this.partie.nbLiaisons(x, y) == 1){
											this.partie.getJoueurCourant().ajouterAuScore(10);
										}
										else if (this.partie.nbLiaisons(x, y) == 2){
											this.partie.getJoueurCourant().ajouterAuScore(20);
										}
										else if (this.partie.nbLiaisons(x, y) == 3){
											this.partie.getJoueurCourant().ajouterAuScore(40);
										}
									}
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
						this.panneau.getPlateau().setCompt(0);
						this.panneau.paintImmediately(0, 0, this.panneau.getWidth(), this.panneau.getHeight());
						this.panneau.getAffDominoEst().paintImmediately(0, 0, this.panneau.getAffDominoEst().getWidth(), this.panneau.getAffDominoEst().getHeight());
						this.panneau.getAffDominoWest().paintImmediately(0, 0, this.panneau.getAffDominoWest().getWidth(), this.panneau.getAffDominoEst().getHeight());
						this.panneau.getAffJoueurs().paintImmediately(0, 0, this.panneau.getAffJoueurs().getWidth(), this.panneau.getAffJoueurs().getHeight());
						if(this.partie.getIndiceNiveauDeux() == 1 && this.partie.getNiveau() == 2){
							this.partie.setIndiceNiveauDeux(2);
						}
						
					}
					
					
					if (!this.partie.getJoueurCourant().estHumain())
					{
						
						this.panneau.compt = 1;
						this.panneau.getPlateau().setCompt(1);
						this.panneau.paintImmediately(0, 0, this.panneau.getWidth(), this.panneau.getHeight());
						this.panneau.getAffDominoEst().paintImmediately(0, 0, this.panneau.getAffDominoEst().getWidth(), this.panneau.getAffDominoEst().getHeight());
						this.panneau.getAffDominoWest().paintImmediately(0, 0, this.panneau.getAffDominoWest().getWidth(), this.panneau.getAffDominoEst().getHeight());
						this.panneau.getAffJoueurs().paintImmediately(0, 0, this.panneau.getAffJoueurs().getWidth(), this.panneau.getAffJoueurs().getHeight());
						
						try {
							Thread.sleep(1000);
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
				
				int x = 0, y = 0;
				
				if (this.partie.dominoEstHorizontal() && this.partie.dominoEstDansLeSens1())
				{
					x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
					y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
				}
				else if (this.partie.dominoEstHorizontal() && !this.partie.dominoEstDansLeSens1())
				{
					x = (int)event.getPoint().getX()-50-this.panneau.getLargeur();
					y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
				}
				else if (!this.partie.dominoEstHorizontal() && this.partie.dominoEstDansLeSens1())
				{
					x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
					y = (int)event.getPoint().getY()-10-this.panneau.getHauteur();
				}
				else if (!this.partie.dominoEstHorizontal() && !this.partie.dominoEstDansLeSens1())
				{
					x = (int)event.getPoint().getX()-10-this.panneau.getLargeur();
					y = (int)event.getPoint().getY()-50-this.panneau.getHauteur();
				}
				
				if (x >= 0 && x < 20*this.partie.getPlateau().getTaille() && y >= 0 && y < 20*this.partie.getPlateau().getTaille())
				{
					this.partie.setX(x);
					this.partie.setY(y);
				}
				
				this.panneau.repaint();
				this.panneau.getAffJoueurs().repaint();
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
	        this.panneau.getAffJoueurs().repaint();
	  }
    }

	
}