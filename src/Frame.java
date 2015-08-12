import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import com.sun.glass.events.KeyEvent;


public class Frame extends JFrame implements ItemListener{

	public static int width = 2560;
	public static int height = 500;
	Component c;
	
	public Frame() {
		super();
		setSize(width,height);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c = new Component();
		
		
		JButton button = new JButton("Refresh Wave");
		button.setSize(500, 500);
		button.setLocation(0, 0);
		button.setEnabled(true);
		button.addActionListener(new ButtonUpdateListener());
		button.setMnemonic(KeyEvent.VK_ALT);
		add(button, BorderLayout.NORTH);
		
		JCheckBox box = new JCheckBox("Is Updating Wave");
		box.setMnemonic(KeyEvent.VK_SPACE);
		box.addItemListener(this);
		box.setSelected(true);
		add(box, BorderLayout.SOUTH);
		getContentPane().add(c, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public class ButtonUpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			c.refreshWave();
			
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		c.setWaveActive(e.getStateChange());
	}
}
