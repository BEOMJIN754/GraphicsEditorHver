package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

public  class GLine extends GShape  {

private Graphics graphics;
	
	public GLine() {
		super(EDrawingStyle.e2PStyle, new Line2D.Float());
		
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
		
		Line2D.Float shape = (Line2D.Float)this.shape;
		shape.setLine(x1, y1, ox2, oy2);
		graphics2D.draw(shape);
		shape.setLine(x1, y1, x2, y2);
		graphics2D.draw(shape);
		
	}
	
	@Override
	public void click(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	
	public void startMove(int x, int y) {
		super.startMove(graphics, x, y);
	}

	public void keepMove(Graphics graphics, int x, int y) {
		super.keepMove(graphics, x, y);
		
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		Line2D.Float shape = (Line2D.Float) this.shape;
		
		graphics2D.draw(shape);
		shape.setLine(shape.getX1()+x2-ox2,shape.getY1()+y2-oy2,shape.getX2()+x2-ox2,shape.getY2()+y2-oy2);
		graphics2D.draw(shape);
		
	}
	
}
