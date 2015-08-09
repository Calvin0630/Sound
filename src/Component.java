import java.awt.Graphics;
import org.jtransforms.fft.DoubleFFT_1D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;


public class Component extends JComponent {
	DoubleFFT_1D fftDo = new DoubleFFT_1D(17460);
	public Component() {
		super();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (Main.targetData != null) {
			byte[] soundArray = Main.targetData;
			
			double[]wave = new double[soundArray.length];
			for (int i=0;i<soundArray.length;i++) {
				wave[i] = (double) soundArray[i];
			}
			fftDo.realForward(wave);
			Rectangle[] rects = new Rectangle[wave.length];
			int rectHeight;
			for (int i=0;i<17640;i+=9) {
				rectHeight = (int)  wave[i];
				if (rectHeight >= 0) rects[i] = new Rectangle(i/9, 500, 1, rectHeight);
				else rects[i] = new Rectangle(i/9, 500 + rectHeight, 1, -rectHeight);
				g2.draw(rects[i]);
			}
		}
		repaint();
	}

}
