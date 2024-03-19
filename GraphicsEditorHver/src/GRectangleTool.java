import java.awt.Graphics;

public class GRectangleTool extends GShapeTool {

	private Graphics graphics;
	
	public void draw(int x, int y, Graphics graphics) {
		this.graphics = graphics;
		
		graphics.drawRect(x, y, 20, 30);
	}
}
