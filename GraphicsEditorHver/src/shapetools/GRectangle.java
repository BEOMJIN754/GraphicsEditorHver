package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

import shapetools.GShape.EDrawingStyle;

public class GRectangle extends GShape {

	private Graphics graphics;

	public GRectangle() {
		super(EDrawingStyle.e2PStyle, new Rectangle());
	}

	public GRectangle clone() {
		return new GRectangle();
	}

	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());

		RectangularShape shape = (RectangularShape) this.shape;
		shape.setFrame(x1, y1, ox2 - x1, oy2 - y1);
		graphics2D.draw(shape);
		shape.setFrame(x1, y1, x2 - x1, y2 - y1);
		graphics2D.draw(shape);
	}

	@Override
	public void click(Graphics graphics) {
	}

	public void startMove(int x, int y) {
		super.startMove(graphics, x, y);
	};

	public void keepMove(Graphics graphics, int x, int y) {
		super.keepMove(graphics, x, y);

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		RectangularShape shape = (RectangularShape) this.shape;

		// remove rectangle
		graphics2D.draw(shape);
		// redraw
		shape.setFrame(shape.getX() + x2 - ox2, shape.getY() + y2 - oy2, shape.getWidth(), shape.getHeight());
		graphics2D.draw(shape);

	};

	public void stopMove(int x, int y) {
	}

}
