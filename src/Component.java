import java.awt.Graphics;

import org.jtransforms.fft.DoubleFFT_1D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;


public class Component extends JComponent {
	DoubleFFT_1D fftDo = new DoubleFFT_1D(17460);
	public boolean isActive;
	int originY;
	int lineSpace, lines;
	public Component() {
		super();
		isActive = false;
		originY = 150;
		lines = 16;
		lineSpace = 20;
		
		repaint();
		
		
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		for (int i = -lines/2;i<lines/2;i++) {
			g2.drawLine(0, originY - i *lineSpace, 5000, originY - i * lineSpace);
		}
		g2.setColor(Color.RED);
		if (Main.targetData != null) {
			byte[] soundArray = Main.targetData;

			double[]wave = new double[soundArray.length];
			for (int i=0;i<soundArray.length;i++) {
				wave[i] = ((double) soundArray[i]);
			}
			//filter(wave);
			//fftDo.realForward(wave);
			drawWave(g2, wave, 1,  .25, 1);
		}
		if (isActive) 
			repaint();
	}

	public void drawWave(Graphics2D g2, double[] wave, int skipRate, double percent, int squareWidth) {
		Rectangle[] rects = new Rectangle[wave.length];
		int rectHeight;
		int numOfRectsDrawn = (int) (percent * 17640);
		for (int i=0;i<numOfRectsDrawn;i+=skipRate) {
			//colouring the wave
			if (i%2 == 0) g2.setColor(Color.BLUE);
			else g2.setColor(Color.RED);
			
			rectHeight = (int)  wave[i];
			if (rectHeight >= 0) rects[i] = new Rectangle((i/skipRate) * squareWidth, originY, squareWidth, rectHeight);
			else rects[i] = new Rectangle((i/skipRate) * squareWidth, originY + rectHeight, squareWidth, -rectHeight);
			g2.fill(rects[i]);
		}
	}
	
	public void filter(double[] wave) {
		boolean flipped = false;
		for (int i=1;i<17636;i+=2) {
			wave[i] = wave[i-1];
			/*
			if (!flipped) wave[i] = wave[i-1];   
			else wave[i + 1] = wave[i];
			if(Math.abs(wave[i] -wave[i+2]) > 10) flipped = true;
			*/
		}
	}
	
	public void setWaveActive(int i) {
		if (i == 1) isActive = true;
		else if (i==2) isActive = false;
		repaint();
	}
	public void refreshWave() {
		repaint();
		isActive = false;
	}

}
