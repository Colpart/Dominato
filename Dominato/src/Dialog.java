import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;




public class Dialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	private JRadioButton tranche1, tranche2, tranche3, tranche4;
	private JRadioButton tranche12, tranche22, tranche32, tranche42;
	private String resultat;
	private JLabel icon;
	

	public Dialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(355, 154);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
		
	}
	
	public void showDialog(){
		this.setVisible(true);
	}
	
	
	public void initComponent(){
		
		
		/* Infos concernant les joueurs humains */
		JPanel panH = new JPanel();
		
		Font font = new Font("Serif", Font.BOLD, 13);
		panH.setBorder(BorderFactory.createTitledBorder(null, "Nombre Joueurs Humains", 0, 0, font));
		panH.setPreferredSize(new Dimension(250, 60));
		tranche1 = new JRadioButton("1");
		this.tranche1.setBackground(Color.LIGHT_GRAY);
		tranche1.setSelected(true);
		tranche2 = new JRadioButton("2");
		this.tranche2.setBackground(Color.LIGHT_GRAY);
		tranche3 = new JRadioButton("3");
		this.tranche3.setBackground(Color.LIGHT_GRAY);
		tranche4 = new JRadioButton("4");
		this.tranche4.setBackground(Color.LIGHT_GRAY);
		tranche1.addMouseListener(new SourisListener());
		tranche2.addMouseListener(new SourisListener());
		tranche3.addMouseListener(new SourisListener());
		tranche4.addMouseListener(new SourisListener());
		tranche1.addActionListener(new MyRadioListener());
		tranche2.addActionListener(new MyRadioListener());
		tranche3.addActionListener(new MyRadioListener());
		tranche4.addActionListener(new MyRadioListener());
		ButtonGroup bg = new ButtonGroup();
		bg.add(tranche1);
		bg.add(tranche2);
		bg.add(tranche3);
		bg.add(tranche4);
		panH.add(tranche1);
		panH.add(tranche2);
		panH.add(tranche3);
		panH.add(tranche4);
		
		/*  Infos concernant l'ordi */
		JPanel panO = new JPanel();
		Font font1 = new Font("Serif", Font.BOLD, 13);
		panO.setBorder(BorderFactory.createTitledBorder(null, "Nombre Joueurs Virtuels", 0, 0, font1));
		panO.setPreferredSize(new Dimension(250, 60));
		tranche12 = new JRadioButton("0");
		this.tranche12.setBackground(Color.LIGHT_GRAY);
		tranche22 = new JRadioButton("1");
		tranche22.setSelected(true);
		this.tranche22.setBackground(Color.LIGHT_GRAY);
		tranche32 = new JRadioButton("2");
		this.tranche32.setBackground(Color.LIGHT_GRAY);
		tranche42 = new JRadioButton("3");
		this.tranche42.setBackground(Color.LIGHT_GRAY);
		tranche12.addMouseListener(new SourisListener());
		tranche22.addMouseListener(new SourisListener());
		tranche32.addMouseListener(new SourisListener());
		tranche42.addMouseListener(new SourisListener());
		tranche12.addActionListener(new MyRadioListener());
		tranche22.addActionListener(new MyRadioListener());
		tranche32.addActionListener(new MyRadioListener());
		tranche42.addActionListener(new MyRadioListener());
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(tranche12);
		bg2.add(tranche22);
		bg2.add(tranche32);
		bg2.add(tranche42);
		panO.add(tranche12);
		panO.add(tranche22);
		panO.add(tranche32);
		panO.add(tranche42);
		
		JPanel content = new JPanel();
		content.setBackground(Color.DARK_GRAY);
		panH.setBackground(Color.LIGHT_GRAY);
		panO.setBackground(Color.LIGHT_GRAY);
		content.add(panH);
		content.add(panO);
		
		
		JPanel control = new JPanel();
		control.setBackground(Color.DARK_GRAY);
		JButton okBouton = new Bouton();
		okBouton.setText("Ok");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				resultat = this.getNbH()+this.getNbO();
				setVisible(false);
			}
			public String getNbH(){
				return (tranche1.isSelected()) ? tranche1.getText() :
						(tranche2.isSelected()) ? tranche2.getText() :
						(tranche3.isSelected()) ? tranche3.getText() :
						(tranche4.isSelected()) ? tranche4.getText() :
						tranche1.getText();
			}
			public String getNbO(){
				return (tranche12.isSelected()) ? tranche12.getText() :
						(tranche22.isSelected()) ? tranche22.getText() :
						(tranche32.isSelected()) ? tranche32.getText() :
						(tranche42.isSelected()) ? tranche42.getText() :
						tranche12.getText();
			}
		});
			
		/* Bonton Annuler */
		JButton cancelBouton = new Bouton();
		cancelBouton.setText("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control ,BorderLayout.SOUTH);
		
	}
	
	
	public class SourisListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
			setCursor(cursor);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(cursor);
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class MyRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(tranche2.isSelected()){
				tranche42.setEnabled(false);
				if(!tranche12.isEnabled()){
					tranche12.setEnabled(true);
				}
				if(!tranche32.isEnabled()){
					tranche32.setEnabled(true);
				}
				if(!tranche22.isEnabled()){
					tranche22.setEnabled(true);
				}
				if(tranche42.isSelected()){
					tranche12.setSelected(true);
				}
				
			}
			if(tranche1.isSelected()){
				tranche12.setEnabled(false);
				if(!tranche22.isEnabled()){
					tranche22.setEnabled(true);
				}
				if(!tranche32.isEnabled()){
					tranche32.setEnabled(true);
				}
				if(!tranche42.isEnabled()){
					tranche42.setEnabled(true);
				}
				if(tranche12.isSelected()){
					tranche22.setSelected(true);
				}
			}
			if(tranche3.isSelected()){
				tranche32.setEnabled(false);
				tranche42.setEnabled(false);
				if(!tranche12.isEnabled()){
					tranche12.setEnabled(true);
				}
				if(!tranche22.isEnabled()){
					tranche22.setEnabled(true);
				}
				if(tranche32.isSelected()){
					tranche12.setSelected(true);
				}
				if(tranche42.isSelected()){
					tranche12.setEnabled(true);
				}
			}
			if(tranche4.isSelected()){
				tranche22.setEnabled(false);
				tranche32.setEnabled(false);
				tranche42.setEnabled(false);
				if(!tranche12.isEnabled()){
					tranche12.setEnabled(true);
				}
				tranche12.setSelected(true);
			}
			
		}
		
	}
	
	
}