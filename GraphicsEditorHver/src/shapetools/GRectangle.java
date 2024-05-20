package shapetools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import shapetools.GShape.EDrawingStyle;

public class GRectangle extends GShape  {

	private Graphics graphics;
	
	public GRectangle() {
		super(EDrawingStyle.e2PStyle, new Rectangle());
	}
	public GRectangle clone() {
		return new GRectangle();
	}
	
	@Override
	public void draw(Graphics graphics) {
		//draw new shape 
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		
		Rectangle shape = (Rectangle)this.shape;
		shape.setBounds(x1, y1, ox2-x1, oy2-y1);
		graphics2D.draw(shape);
		shape.setBounds(x1, y1, x2-x1, y2-y1);
		graphics2D.draw(shape);
	}
	
	@Override
	public void click(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	public boolean onShape(int x, int y) {
		return false;
	}

	public  void startMove(int x, int y) {};

	public  void keepMove(int x, int y) {};

	public  void stopMove(int x, int y) {};
}

