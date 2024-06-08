package frames.draw;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import shapetools.GShape;

public abstract class GTransformer {
	
	protected GShape currentShape;
	protected int x1, y1, x2, y2, ox2, oy2;
	
	// 기준점 centerX
	protected double cx, cy;
		// 변화율
	protected double sx, sy;
	protected double dx, dy;
	
	protected AffineTransform affineTransform;
	
	public GTransformer(GShape currentShape) {
		this.currentShape = currentShape;
		
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.ox2 = 0;
		this.oy2 = 0;

		this.sx = 1.0;
		this.sy = 1.0;
		this.dx = 0.0;
		this.dy = 0.0;
	}
	
	public void startTransforming(Graphics2D graphics, int x, int y) {
		this.currentShape.eraseAnchors(graphics);
		
		graphics.setPaintMode();
		graphics.draw( this.currentShape.getShape());
		// 좌표 저장
		this.ox2 = x2;
		this.oy2 = y2;
		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
	}

	public abstract void keepTransforming(Graphics2D graphics, int x, int y);

	public void stopTransforming(Graphics2D graphics, int x, int y) {
		this.currentShape.drawAnchors(graphics);
		
	}

}
