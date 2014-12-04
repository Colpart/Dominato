import javax.swing.JFrame;

public class Fenetre extends JFrame
{
	static final long serialVersionUID = 1;
	
	public Fenetre(Partie partie)
	{
		this.setTitle("Dominato");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(new Panneau(partie));
	    this.pack();
	    this.setVisible(true);
	}
}