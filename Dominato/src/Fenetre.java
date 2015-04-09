import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fenetre extends JFrame 
{
	static final long serialVersionUID = 1;
	
	private JPanel container = new JPanel(); 
	private JButton bouton = new Bouton("Pause.jpg");
	private JButton bouton2 = new Bouton("Reprendre.jpg");
	private JButton bouton3 = new Bouton("Rejouer.jpg");
	private JButton bouton4 = new Bouton("Quitter.jpg");
	private JButton bouton5 = new Bouton("Menu_Principal.jpg");
	private JButton boutonM1 = new BoutonM("Partie rapide");	
	private JButton boutonM2 = new BoutonM("Nouvelle partie");	
	private JButton boutonM3 = new BoutonM("Quitter");
	private JButton boutonM4 = new BoutonM("Reprendre la partie");
	private BoutonListener boutonL = new BoutonListener();
	private Bouton2Listener boutonL2 = new Bouton2Listener();
	private Bouton3Listener boutonL3 = new Bouton3Listener();
	private Bouton4Listener boutonL4 = new Bouton4Listener();
	private Bouton5Listener boutonL5 = new Bouton5Listener();
	private Panneau pan;
	private AffichageDominosEst dominoEst;
	private AffichageDominosWest dominoWest;
	private AffichageJoueurs affJoueurs;
	private Menu menu;
	private Partie partie;
	
	
	public Fenetre(){
		this.setTitle("Dominato");
		this.setExtendedState(Fenetre.MAXIMIZED_BOTH);
	    this.setContentPane(container);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.menu = new Menu(new ImageIcon("dominato.jpg").getImage());
	    this.container.setLayout(new BorderLayout());
	    this.container.add(this.menu,BorderLayout.CENTER);
	    JPanel center = new JPanel();
	    center.add(boutonM1);
	    center.add(boutonM4);
	    center.add(boutonM2);
	    center.add(boutonM3);
	    center.setBackground(Color.black);
	    this.container.add(center, BorderLayout.NORTH);
	    this.boutonM1.addActionListener(new BoutonListenerM1());
	    this.boutonM2.addActionListener(new BoutonListenerM2());
	    this.boutonM3.addActionListener(new BoutonListenerM3());
	    this.boutonM4.addActionListener(new BoutonListenerM4());
	    this.pack();
	    this.setVisible(true);

	    
	}
	
	
	public void modifierContainer(Container content){
		this.setContentPane(content);
	}
	
	public class BoutonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent arg0) {
			bouton2.setEnabled(true);
			bouton.setEnabled(false);
			pan.removeMouseListener(pan.getControleur());
			pan.removeMouseMotionListener(pan.getControleur());
			pan.removeMouseWheelListener(pan.getControleur());
		}
	}
	
	public class Bouton2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			bouton.setEnabled(true);
			bouton2.setEnabled(false);
			pan.addMouseListener(pan.getControleur());
			pan.addMouseMotionListener(pan.getControleur());
			pan.addMouseWheelListener(pan.getControleur());
		}
	}
	
	public int getPositionFenetreX(){
		int a = (int) this.getBounds().getX();
		return a;
	}

	public int getPositionFenetreY(){
		int a = (int) this.getBounds().getY();
		return a;
	}
	
	public int getLargeurFenetre(){
		int a = (int) this.getBounds().getWidth();
		return a;
	}
	
	public int getHauteurFenetre(){
		int a = (int) this.getBounds().getHeight();
		return a;
	}
	
	public void fermerFenetre(){
		this.dispose();
	}
	
	public class Bouton3Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Voulez-vous recommencer la partie ?","Recommencer la partie",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				bouton.removeActionListener(boutonL);
				bouton2.removeActionListener(boutonL2);
				bouton3.removeActionListener(boutonL3);
				bouton4.removeActionListener(boutonL4);
				bouton5.removeActionListener(boutonL5);
				
				boolean[] type = new boolean[partie.getNbJoueurs()];
				
				for(int i = 0; i<partie.getNbJoueurs(); i++){
					if(partie.getJoueur(i).estHumain())
						type[i] = true;
					else
						type[i] = false;
				}
				Partie p = new Partie(type.length, type,partie.getAffichage(),partie.getNiveau());
				dominoWest = new AffichageDominosWest(p);
				dominoEst = new AffichageDominosEst(p);
				affJoueurs = new AffichageJoueurs(p);
				Panneau pa = new Panneau(p,dominoEst, dominoWest, affJoueurs );
				pan = pa;
				
				container.removeAll();
				
				container.setLayout(new BorderLayout());
				container.add(pan, BorderLayout.CENTER);
				container.add(dominoWest,BorderLayout.WEST);
				container.add(dominoEst,BorderLayout.EAST);
				container.add(affJoueurs, BorderLayout.SOUTH);
				JPanel north = new JPanel();
				north.add(bouton, BorderLayout.WEST);
				north.add(bouton2);
				north.add(bouton3);
				north.add(bouton5);
				north.add(bouton4);
				
				north.setBackground(Color.DARK_GRAY);
				container.add(north, BorderLayout.NORTH);
				bouton2.setEnabled(false);
				bouton.setEnabled(true);
				bouton.addActionListener(boutonL);
				bouton2.addActionListener(boutonL2);
				bouton3.addActionListener(boutonL3);
				bouton4.addActionListener(boutonL4);
				bouton5.addActionListener(boutonL5);
				modifierContainer(container);
			}	
		}
	}
			
	public class Bouton4Listener implements ActionListener{
				
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Enregistrer la partie ?","Quitter la partie",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				ObjectOutputStream oos = null;

			    try {
			      final FileOutputStream fichier = new FileOutputStream("partie.ser");
			      oos = new ObjectOutputStream(fichier);
			      oos.writeObject(partie);
			      oos.flush();
			    } catch (final java.io.IOException e) {
			      e.printStackTrace();
			    } finally {
			      try {
			        if (oos != null) {
			          oos.flush();
			          oos.close();
			        }
			      } catch (final IOException ex) {
			        ex.printStackTrace();
			      }
			    }
			    System.exit(0);
			}else if(option == JOptionPane.NO_OPTION){
				System.exit(0);
			}
			
		}
	}

	
	
	public class Bouton5Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Enregistrer la partie ?","Retour au menu principal",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				ObjectOutputStream oos = null;

			    try {
			      final FileOutputStream fichier = new FileOutputStream("partie.ser");
			      oos = new ObjectOutputStream(fichier);
			      oos.writeObject(partie);
			      oos.flush();
			    } catch (final java.io.IOException e) {
			      e.printStackTrace();
			    } finally {
			      try {
			        if (oos != null) {
			          oos.flush();
			          oos.close();
			        }
			      } catch (final IOException ex) {
			        ex.printStackTrace();
			      }
			    }
			    container.removeAll();
				container.add(menu,BorderLayout.CENTER);
			    JPanel center = new JPanel();
			    center.add(boutonM1);
			    center.add(boutonM4);
			    center.add(boutonM2);
			    center.add(boutonM3);
			    center.setBackground(Color.black);
			    container.add(center, BorderLayout.NORTH);
			    
			    modifierContainer(container); 
			
			}else if(option == JOptionPane.NO_OPTION){
				container.removeAll();
				container.add(menu,BorderLayout.CENTER);
			    JPanel center = new JPanel();
			    center.add(boutonM1);
			    center.add(boutonM4);
			    center.add(boutonM2);
			    center.add(boutonM3);
			    center.setBackground(Color.black);
			    container.add(center, BorderLayout.NORTH);
			    
			    modifierContainer(container); 
			
			}
			
		}
	}

	
	
	public class BoutonListenerM1 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			bouton.removeActionListener(boutonL);
			bouton2.removeActionListener(boutonL2);
			bouton3.removeActionListener(boutonL3);
			bouton4.removeActionListener(boutonL4);
			bouton5.removeActionListener(boutonL5);
			
			
			boolean[] type = {true,false};
			partie = new Partie(2,type,3,2);
			dominoWest = new AffichageDominosWest(partie);
			dominoEst = new AffichageDominosEst(partie);
			affJoueurs = new AffichageJoueurs(partie);
			pan = new Panneau(partie,dominoEst,dominoWest, affJoueurs);
			
			getContentPane().removeAll();
			container.setLayout(new BorderLayout());
			container.add(pan, BorderLayout.CENTER);
			container.add(dominoEst,BorderLayout.EAST);
			container.add(dominoWest,BorderLayout.WEST);
			container.add(affJoueurs, BorderLayout.SOUTH);
			JPanel north = new JPanel();
			north.add(bouton, BorderLayout.WEST);
			north.add(bouton2);
			north.add(bouton3);
			north.add(bouton5);
			north.add(bouton4);
			
			north.setBackground(Color.DARK_GRAY);
			container.add(north, BorderLayout.NORTH);
			bouton2.setEnabled(false);
			bouton.addActionListener(boutonL);
			bouton2.addActionListener(boutonL2);
			bouton3.addActionListener(boutonL3);
			bouton4.addActionListener(boutonL4);
			bouton5.addActionListener(boutonL5);
		    setContentPane(container);
		    
		}
	}
	
public class BoutonListenerM4 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			bouton.removeActionListener(boutonL);
			bouton2.removeActionListener(boutonL2);
			bouton3.removeActionListener(boutonL3);
			bouton4.removeActionListener(boutonL4);
			bouton5.removeActionListener(boutonL5);
			
			ObjectInputStream ois = null;

		    try {
		      final FileInputStream fichier = new FileInputStream("partie.ser");
		      ois = new ObjectInputStream(fichier);
		      final Partie p = (Partie) ois.readObject();
		      partie = p;
		    } catch (final java.io.IOException e) {
		      e.printStackTrace();
		    } catch (final ClassNotFoundException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (ois != null) {
		          ois.close();
		        }
		      } catch (final IOException ex) {
		        ex.printStackTrace();
		      }
		    }
			
			dominoWest = new AffichageDominosWest(partie);
			dominoEst = new AffichageDominosEst(partie);
			affJoueurs = new AffichageJoueurs(partie);
			pan = new Panneau(partie,dominoEst,dominoWest, affJoueurs);
			
			getContentPane().removeAll();
			container.setLayout(new BorderLayout());
			container.add(pan, BorderLayout.CENTER);
			container.add(dominoEst,BorderLayout.EAST);
			container.add(dominoWest,BorderLayout.WEST);
			container.add(affJoueurs, BorderLayout.SOUTH);
			JPanel north = new JPanel();
			north.add(bouton, BorderLayout.WEST);
			north.add(bouton2);
			north.add(bouton3);
			north.add(bouton5);
			north.add(bouton4);
			
			north.setBackground(Color.DARK_GRAY);
			container.add(north, BorderLayout.NORTH);
			bouton2.setEnabled(false);
			bouton.addActionListener(boutonL);
			bouton2.addActionListener(boutonL2);
			bouton3.addActionListener(boutonL3);
			bouton4.addActionListener(boutonL4);
			bouton5.addActionListener(boutonL5);
		    setContentPane(container);
		    
		}
	}
	
	
	public void lancerPartie(boolean type[], int affichage,int niveau){
		bouton.removeActionListener(boutonL);
		bouton2.removeActionListener(boutonL2);
		bouton3.removeActionListener(boutonL3);
		bouton4.removeActionListener(boutonL4);
		bouton5.removeActionListener(boutonL5);
		
		partie = new Partie(type.length,type,affichage,niveau);
		
		dominoWest = new AffichageDominosWest(partie);
		dominoEst = new AffichageDominosEst(partie);
		affJoueurs = new AffichageJoueurs(partie);
		pan = new Panneau(partie,dominoEst,dominoWest, affJoueurs );
		getContentPane().removeAll();
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		container.add(dominoEst, BorderLayout.EAST);
		container.add(dominoWest, BorderLayout.WEST);
		container.add(affJoueurs, BorderLayout.SOUTH);
		JPanel north = new JPanel();
		north.add(bouton, BorderLayout.WEST);
		north.add(bouton2);
		north.add(bouton3);
		north.add(bouton5);
		north.add(bouton4);
		
		north.setBackground(Color.DARK_GRAY);
		container.add(north, BorderLayout.NORTH);
		bouton2.setEnabled(false);
		bouton.addActionListener(boutonL);
		bouton2.addActionListener(boutonL2);
		bouton3.addActionListener(boutonL3);
		bouton4.addActionListener(boutonL4);
		bouton5.addActionListener(boutonL5);
	    setContentPane(container);
	}
	
	public class BoutonListenerM2 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			int res = 0;
			Dialog d = new Dialog(null, "Nouvelle partie", true);
			
			try{
				res = Integer.parseInt(d.getResultat());
				
			}catch(NumberFormatException e){}
			
			if(res == 20){
				boolean[] type = {true,true};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
				System.out.print(d.getAffichage());
			}
			
			else if(res == 30){
				boolean[] type = {true,true,true};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			else if(res == 40){
				boolean[] type = {true,true,true,true};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			
			else if(res == 11){
				boolean[] type = {true,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			else if(res == 21){
				boolean[] type = {true,true,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			else if(res == 31){
				boolean[] type = {true,true,true,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			else if(res == 12){
				boolean[] type = {true,false,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
			
			else if(res == 13){
				boolean[] type = {true,false,false,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
		
			else if(res == 22){
				boolean[] type = {true,true,false,false};
				lancerPartie(type,d.getAffichage(),d.getNiveau());
			}
		}
	}
	public class BoutonListenerM3 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Voulez-vous quitter ?","Attention",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}
			
		}
	}

	
}
