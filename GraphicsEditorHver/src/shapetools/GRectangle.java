package shapetools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

import shapetools.GShape.EDrawingStyle;

public class GRectangle extends GShape  {

	private Graphics graphics;
	
	public GRectangle() {
		super(EDrawingStyle.e2PStyle);
	}
	public GRectangle clone() {
		return new GRectangle();
	}
	
	@Override
	public void draw(Graphics graphics) {
		//draw new shape 
		graphics.drawRect(x1, y1, x2-x1, y2-y1);
		
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		//erase old shape 
		graphics2D.drawRect(x1, y1, ox2-x1, oy2-y1);
		//draw new shape 
		graphics2D.drawRect(x1, y1, x2-x1, y2-y1);
	}
	
	@Override
	public void click(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
}
