package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import shapetools.GShape.EDrawingStyle;

public class GRectangle extends GShape {

	private Graphics graphics;
	private int offsetX, offsetY;

	public GRectangle() {
		super(EDrawingStyle.e2PStyle, new Rectangle());
	}

	public GRectangle clone() {
		return new GRectangle();
	}

	@Override
	public void draw(Graphics graphics) {
		// draw new shape
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
	}

	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());

		Rectangle shape = (Rectangle) this.shape;
		shape.setBounds(x1, y1, ox2 - x1, oy2 - y1);
		graphics2D.draw(shape);
		shape.setBounds(x1, y1, x2 - x1, y2 - y1);
		graphics2D.draw(shape);
	}

	@Override
	public void click(Graphics graphics) {
		// TODO Auto-generated method stub

	}


	public void startMove(int x, int y) {
		Rectangle shape = (Rectangle) this.shape;
		this.offsetX = (int) (x - shape.getX());
        this.offsetY = (int) (y - shape.getY());
	};

	@Override
	public void keepMove(Graphics graphics ,int x, int y) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		Rectangle shape = (Rectangle) this.shape;
		
		// remove rectangle
		graphics2D.draw(shape);
		// redraw
		shape.setFrame(x - offsetX, y - offsetY, shape.getWidth(), shape.getHeight());
		graphics2D.draw(shape);

	};

	public void stopMove(int x, int y) {
	}

}
