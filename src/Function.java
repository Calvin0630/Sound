import java.awt.Graphics2D;


public class Function {
	public int[] array;
	public int originX, originY;
	
	public Function(byte[] input, int x, int y) {
		array = new int[input.length];
		for (int i=0;i<input.length;i++) {
			array[i] = input[i]; 
		}
		originX = x;
		originY = y;
	}
	
	public void update(byte[] input) {
		array = new int[input.length];
		for (int i=0;i<input.length;i++) {
			array[i] = input[i]; 
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i= originX;  i<originX+array.length-1 ;  i++) {
			g2.drawLine(i, array[i], i+1, array[i+1]);
		}
	}
}
