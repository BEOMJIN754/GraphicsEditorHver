package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public  class GLine extends GShape  {

private Graphics graphics;
	
	public GLine() {
		super(EDrawingStyle.e2PStyle);
		
	}
	public GLine clone() {
		return new GLine();
	}
	
	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.drawLine(x1, y1, ox2, oy2);
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		//erase old shape 
		graphics2D.drawLine(x1, y1, ox2, oy2);
		
		//draw new shape 
		graphics2D.drawLine(x1, y1, x2, y2);
		
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
