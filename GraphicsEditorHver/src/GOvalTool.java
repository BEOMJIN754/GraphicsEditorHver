import java.awt.Graphics;

public class GOvalTool extends GShapeTool{

	private Graphics graphics; 
	@Override
	public void draw(int x, int y, Graphics graphics) {
		this.graphics = graphics;
		graphics.drawOval(x, y, 20, 30);		
	}

	
}
