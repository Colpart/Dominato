import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fenetre extends JFrame 
{
	static final long serialVersionUID = 1;
	
	private JPanel container = new JPanel(); 
	private JButton bouton = new Bouton("pause.jpg");
	private JButton bouton2 = new Bouton("reprendre.jpg");
	private JButton bouton3 = new Bouton("rejouer.jpg");
	private JButton bouton4 = new Bouton("quitter.jpg");
	private JButton boutonM1 = new BoutonM("Partie Rapide");	
	private JButton boutonM2 = new BoutonM("Nouvelle Partie");	
	private JButton boutonM3 = new BoutonM("Quitter");
	private BoutonListener boutonL = new BoutonListener();
	private Bouton2Listener boutonL2 = new Bouton2Listener();
	private Bouton3Listener boutonL3 = new Bouton3Listener();
	private Bouton4Listener boutonL4 = new Bouton4Listener();
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
	    center.add(boutonM2);
	    center.add(boutonM3);
	    center.setBackground(Color.black);
	    this.container.add(center, BorderLayout.NORTH);
	    this.boutonM1.addActionListener(new BoutonListenerM1());
	    this.boutonM2.addActionListener(new BoutonListenerM2());
	    this.boutonM3.addActionListener(new BoutonListenerM3());
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
			int option = jop.showConfirmDialog(null,"Voulez-vous recommencer la partie?",null,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				bouton.removeActionListener(boutonL);
				bouton2.removeActionListener(boutonL2);
				bouton3.removeActionListener(boutonL3);
				bouton4.removeActionListener(boutonL4);
				
				boolean[] type = new boolean[partie.getNbJoueurs()];
				
				for(int i = 0; i<partie.getNbJoueurs(); i++){
					if(partie.getJoueur(i).estHumain())
						type[i] = true;
					else
						type[i] = false;
				}
				Partie p = new Partie(type.length, type);
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
				north.add(bouton4);
				north.setBackground(Color.DARK_GRAY);
				container.add(north, BorderLayout.NORTH);
				bouton2.setEnabled(false);
				bouton.setEnabled(true);
				bouton.addActionListener(boutonL);
				bouton2.addActionListener(boutonL2);
				bouton3.addActionListener(boutonL3);
				bouton4.addActionListener(boutonL4);
				modifierContainer(container);
			}	
		}
	}
			
	public class Bouton4Listener implements ActionListener{
				
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Souhaitez-vous quitter le jeu ?","Attention",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}
			
		}
	}
	
	public class BoutonListenerM1 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			bouton.removeActionListener(boutonL);
			bouton2.removeActionListener(boutonL2);
			bouton3.removeActionListener(boutonL3);
			bouton4.removeActionListener(boutonL4);
			
			boolean[] type = {true,false};
			partie = new Partie(2,type);
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
			north.add(bouton4);
			north.setBackground(Color.DARK_GRAY);
			container.add(north, BorderLayout.NORTH);
			bouton2.setEnabled(false);
			bouton.addActionListener(boutonL);
			bouton2.addActionListener(boutonL2);
			bouton3.addActionListener(boutonL3);
			bouton4.addActionListener(boutonL4);
		    setContentPane(container);
		    
		}
	}
	
	
	public void lancerPartie(boolean type[]){
		bouton.removeActionListener(boutonL);
		bouton2.removeActionListener(boutonL2);
		bouton3.removeActionListener(boutonL3);
		bouton4.removeActionListener(boutonL4);
		
		partie = new Partie(type.length,type);
		
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
		north.add(bouton4);
		north.setBackground(Color.DARK_GRAY);
		container.add(north, BorderLayout.NORTH);
		bouton2.setEnabled(false);
		bouton.addActionListener(boutonL);
		bouton2.addActionListener(boutonL2);
		bouton3.addActionListener(boutonL3);
		bouton4.addActionListener(boutonL4);
	    setContentPane(container);
	}
	
	public class BoutonListenerM2 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			int res = 0;
			Dialog d = new Dialog(null, "Choix Des Joueurs", true);
			
			try{
				res = Integer.parseInt(d.getResultat());
			}catch(NumberFormatException e){}
			
			if(res == 20){
				boolean[] type = {true,true};
				lancerPartie(type);
			}
			
			else if(res == 30){
				boolean[] type = {true,true,true};
				lancerPartie(type);
			}
			
			else if(res == 40){
				boolean[] type = {true,true,true,true};
				lancerPartie(type);
			}
			
			
			else if(res == 11){
				boolean[] type = {true,false};
				lancerPartie(type);
			}
			
			else if(res == 21){
				boolean[] type = {true,true,false};
				lancerPartie(type);
			}
			
			else if(res == 31){
				boolean[] type = {true,true,true,false};
				lancerPartie(type);
			}
			
			else if(res == 12){
				boolean[] type = {true,false,false};
				lancerPartie(type);
			}
			
			else if(res == 13){
				boolean[] type = {true,false,false,false};
				lancerPartie(type);
			}
		
			else if(res == 22){
				boolean[] type = {true,true,false,false};
				lancerPartie(type);
			}
		}
	}
	public class BoutonListenerM3 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Voulez-vous quittez ?","Attention",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}
			
		}
	}

	
}
