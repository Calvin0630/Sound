import javax.swing.JFrame;


public class Frame extends JFrame{

	public static int width = 2000;
	public static int height = 1000;
	
	public Frame() {
		super();
		setSize(width,height);
		getContentPane().add(new Component());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
