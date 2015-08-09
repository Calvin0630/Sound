import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class Component extends JComponent {


	public Component() {
		super();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (Main.targetData != null) {
			byte[] soundArray = Main.targetData;
			Rectangle[] rects = new Rectangle[soundArray.length];
			int rectHeight;
			for (int i=0;i<17640;i+=9) {
				rectHeight = soundArray[i];
				if (rectHeight >= 0) rects[i] = new Rectangle(i/9, 500, 1, rectHeight);
				else rects[i] = new Rectangle(i/9, 500 + rectHeight, 1, -rectHeight);
				g2.draw(rects[i]);
			}
		}
		repaint();
	}

}
