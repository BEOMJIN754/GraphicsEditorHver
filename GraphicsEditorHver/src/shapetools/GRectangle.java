package shapetools;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GRectangle extends GShape {

	private Graphics graphics;
	
	public GRectangle() {
		
	}
	
	
	@Override
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		//erase old shape 
		graphics.drawRect(x1, y1, ox2-x1, oy2-y1);
		//draw new shape 
		graphics.drawRect(x1, y1, x2-x1, y2-y1);
	}
}
