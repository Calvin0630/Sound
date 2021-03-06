import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFrame;

public class Main {
	public static byte[] targetData;
	public static void main(String[] args) {
		
		AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
		
		DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);

		try {
			Frame frame = new Frame();
			
			TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
			targetLine.open(format);
			targetLine.start();
			
			/*
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("D:\\Users\\Calvin\\workspace\\Sound\\music\\Gods_Robots-Break_the_spell.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			*/
			
			SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
			sourceLine.open(format);
			sourceLine.start();

			int numBytesRead;
			targetData = new byte[targetLine.getBufferSize() / 5];
			
			while (true) {
				numBytesRead = targetLine.read(targetData, 0, targetData.length);
				if (numBytesRead == -1)	break;

				sourceLine.write(targetData, 0, numBytesRead);
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}

}