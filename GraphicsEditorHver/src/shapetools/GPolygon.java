package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;

import global.Constants;

public class GPolygon extends GShape {

	private int xPoints[];
	private int yPoints[];
	private int nPoints;
	
	private int moveX;
	private int moveY;
	
	private int offsetX, offsetY;

	public GPolygon() {
		super(EDrawingStyle.eNPStyle, new Polygon());
		this.xPoints = new int[Constants.NUM_POINTS];
		this.yPoints = new int[Constants.NUM_POINTS];
		this.nPoints = 0;
	}

	public GPolygon clone() {
		return new GPolygon();
	}

//	public void printPoints(int[] points) {
//		for (int i = 0; i < points.length; i++) {
//			System.out.print(points[i] + ", ");
//		}
//		System.out.println();
//	}



	@Override
	public void draw(Graphics graphics) {
		// draw new shape
		graphics.drawPolygon(xPoints, yPoints, nPoints);

	}
	
	@Override
	public void drag(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void click(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		// erase old shape
		// nPoints include next points(extra points)
		graphics.drawPolyline(xPoints, yPoints, nPoints-1);
		// draw new shape
		graphics.drawPolygon(xPoints, yPoints, nPoints-1);

	}
	

	@Override
	public void setOrigin(int x, int y) {
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;

		this.nPoints++;
		
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
	}

	@Override
	public void movePoint(int x, int y) {
		this.moveX = x;
		this.moveY = y;
	}

	@Override
	public void addPoint(int x, int y) {
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		this.nPoints++;
		
//		printPoints(xPoints);
//		printPoints(yPoints);
	}

	@Override
	public void keepMove(Graphics graphics, int x, int y) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		Rectangle shape = (Rectangle) this.shape;
		
		// remove rectangle
		graphics2D.draw(shape);
		// redraw
		shape.setFrame(x - offsetX, y - offsetY, shape.getWidth(), shape.getHeight());
		graphics2D.draw(shape);
	}

	public void startMove(int x, int y) {
		Rectangle shape = (Rectangle) this.shape;
		this.offsetX = (int) (x - shape.getX());
        this.offsetY = (int) (y - shape.getY());
		
	}
	
}