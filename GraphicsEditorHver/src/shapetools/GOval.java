package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shapetools.GShape.EDrawingStyle;

public  class GOval extends GShape {

private Graphics graphics;
	
	public GOval() {
		super(EDrawingStyle.e2PStyle);
	}
	public GOval clone() {
		return new GOval();
	}
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		//erase old shape 
		graphics2D.drawOval(x1, y1, ox2-x1, oy2-y1);
		
		//draw new shape 
		graphics2D.drawOval(x1, y1, x2-x1, y2-y1);
		
	}
	@Override
	public void draw(Graphics graphics) {
		//draw new shape 
		graphics.drawOval(x1, y1, x2-x1, y2-y1);
	}
}
