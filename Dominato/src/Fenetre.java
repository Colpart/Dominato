
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame 
{
	static final long serialVersionUID = 1;
	
	private JPanel container = new JPanel(); 
	private JButton bouton = new JButton("Pause");
	private JButton bouton2 = new JButton("Reprendre");
	private JButton bouton3 = new JButton("Rejouer");
	private JButton bouton4 = new JButton("Quitter");
	private Panneau pan;
	private Partie partie;
	
	
	public Fenetre(Partie partie)
	{
		this.partie = partie;
		this.pan = new Panneau(partie);
		this.setTitle("Dominato");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.container.setLayout(new BorderLayout());
		this.container.add(pan, BorderLayout.CENTER);
		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		south.add(bouton3);
		south.add(bouton4);
		south.setBackground(Color.DARK_GRAY);
		container.add(south, BorderLayout.SOUTH);
		this.bouton2.setEnabled(false);
		bouton.addActionListener(new BoutonListener());
		bouton2.addActionListener(new Bouton2Listener());
		bouton3.addActionListener(new Bouton3Listener());
		bouton4.addActionListener(new Bouton4Listener());
	    this.setContentPane(container);
	    this.pack();
	    this.setVisible(true);
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
		
		/* à Modifier apres en remelangeant les dominos des joueurs */
		/* Trouver une solution pour evitez de fermer la fenetre pour rejouer*/
		public void actionPerformed(ActionEvent arg0) {
			boolean[] type = new boolean[partie.getNbJoueurs()];
			
			for(int i = 0; i<partie.getNbJoueurs(); i++){
				if(partie.getJoueur(i).estHumain())
					type[i] = true;
				else
					type[i] = false;
			}
			Partie p = new Partie(type.length, type);
			int x = getPositionFenetreX();
			int y = getPositionFenetreY();
			int w = getLargeurFenetre();
			int h = getHauteurFenetre();
			Fenetre f = new Fenetre(p);
			f.setBounds(x, y, w, h);
			fermerFenetre();
			
		}
	}
			
	public class Bouton4Listener implements ActionListener{
				
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane jop = new JOptionPane();
			int option = jop.showConfirmDialog(null,"Voulez-vous quitter la partie","Attention",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}
			
		}
	}
}
