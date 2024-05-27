package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape {

	private Graphics graphics;

	public GOval() {
		super(EDrawingStyle.e2PStyle, new Ellipse2D.Float());
	}

	public GOval clone() {
		return new GOval();
	}

	@Override
	public void draw(Graphics graphics) {
		// draw new shape
		graphics.drawOval(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		// erase old shape
		Ellipse2D.Float shape = (Ellipse2D.Float) this.shape;
		shape.setFrame(x1, y1, ox2 - x1, oy2 - y1);
		graphics2D.draw(shape);
		shape.setFrame(x1, y1, x2 - x1, y2 - y1);
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
		Ellipse2D.Float shape = (Ellipse2D.Float) this.shape;

		// remove rectangle
		graphics2D.draw(shape);
		// redraw
		shape.setFrame(shape.getX() + x2 - ox2, shape.getY() + y2 - oy2, shape.getWidth(), shape.getHeight());
		graphics2D.draw(shape);

	}

	
}
