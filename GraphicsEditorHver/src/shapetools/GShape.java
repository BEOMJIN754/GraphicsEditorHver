package shapetools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import shapetools.GShape.EAnchors;

public abstract class GShape implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum EDrawingStyle {
		e2PStyle, eNPStyle
	}

	private EDrawingStyle eDrawingStyle;

	protected Shape shape;
	protected int x1, y1, x2, y2, ox2, oy2;

	public EDrawingStyle getEDrawingStyle() {
		return this.eDrawingStyle;
	}

	public enum EAnchors {
		eRR(new Cursor(Cursor.HAND_CURSOR)), eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)), eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)), eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)), eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)), eMM(new Cursor(Cursor.CROSSHAIR_CURSOR));

		private Cursor cursor;

		private EAnchors(Cursor cursor) {
			this.cursor = cursor;
		}

		public Cursor getCursor() {
			return this.cursor;
		}
	}

	private EAnchors eSelectedAnchor;

	protected Ellipse2D.Float[] anchors;

	// setters and getters
	public void setSelected(Graphics graphics) {
		this.drawAnchors(graphics);
	}

	public void clearSelected() {
		this.anchors = null;
	}

	public EAnchors getSelectedAnchor() {
		return this.eSelectedAnchor;
	}

	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return this.eSelectedAnchor.getCursor();
	};

	public GShape(EDrawingStyle eDrawingStyle, Shape shape) {
		this.eDrawingStyle = eDrawingStyle;
		this.shape = shape;
		
		this.anchors = null;
		this.eSelectedAnchor = null;
		
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.ox2 = 0;
		this.oy2 = 0;

	}

	public abstract GShape clone();

	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
	};

	private void drawAnchors(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		Rectangle rectangle = this.shape.getBounds();
		int x = rectangle.x -5;
		int y = rectangle.y -5;
		int w = rectangle.width;
		int h = rectangle.height;
		int ANCHOR_WIDTH = 10;
		int ANCHOR_HEIGHT = 10;

		this.anchors = new Ellipse2D.Float[EAnchors.values().length - 1];
		this.anchors[EAnchors.eRR.ordinal()] = new Ellipse2D.Float(x + w / 2, y - 30, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNN.ordinal()] = new Ellipse2D.Float(x + w / 2, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSS.ordinal()] = new Ellipse2D.Float(x + w / 2, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eEE.ordinal()] = new Ellipse2D.Float(x + w, y + h / 2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eWW.ordinal()] = new Ellipse2D.Float(x, y + h / 2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNW.ordinal()] = new Ellipse2D.Float(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNE.ordinal()] = new Ellipse2D.Float(x + w, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSW.ordinal()] = new Ellipse2D.Float(x, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSE.ordinal()] = new Ellipse2D.Float(x + w, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);

		for (Ellipse2D.Float anchor : this.anchors) {
			graphics2D.setColor(Color.WHITE);
			graphics2D.fill(anchor);
			graphics2D.setColor(Color.BLACK);
			graphics2D.draw(anchor);
			
		}

	}

	public void setOrigin(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;

		this.x2 = x1;
		this.y2 = y1;
		this.ox2 = x1;
		this.oy2 = y1;
	}

	public void movePoint(int x2, int y2) {

		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x2;
		this.y2 = y2;
	}

	private void updateAnchors() {
		 if (this.anchors != null) {
		        Rectangle bounds = this.shape.getBounds();
		        int x = bounds.x;
		        int y = bounds.y;
		        int w = bounds.width;
		        int h = bounds.height;
		        int ANCHOR_WIDTH = 10;
		        int ANCHOR_HEIGHT = 10;

		        this.anchors[EAnchors.eRR.ordinal()].setFrame(x + w / 2, y - 30, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eNN.ordinal()].setFrame(x + w / 2, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eSS.ordinal()].setFrame(x + w / 2, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eEE.ordinal()].setFrame(x + w, y + h / 2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eWW.ordinal()].setFrame(x, y + h / 2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eNW.ordinal()].setFrame(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eNE.ordinal()].setFrame(x + w, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eSW.ordinal()].setFrame(x, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		        this.anchors[EAnchors.eSE.ordinal()].setFrame(x + w, y + h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		    }
		 for (Ellipse2D.Float anchor : this.anchors) {
				Graphics2D graphics2D = null;
				graphics2D.draw(anchor);
	}
	}

	public abstract void drag(Graphics graphics);

	// ploygon 그리기용
	public abstract void click(Graphics graphics);

	
	public void addPoint(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean onShape(int x, int y) {
	    this.eSelectedAnchor = null;
	    if (this.anchors != null) {
	        for (int i = 0; i < EAnchors.values().length - 1; i++) {
	            if (anchors[i].contains(x, y)) {
	                this.eSelectedAnchor = EAnchors.values()[i];
	                return true;
	            }
	        }
	    }
	    boolean isOnShape = this.shape.contains(x, y)||  this.shape.getBounds2D().contains(x, y);
	    if (isOnShape) {
	        this.eSelectedAnchor = EAnchors.eMM;
	    }
	    return isOnShape;
	}

	public void startMove(Graphics graphics, int x, int y) {
		// 좌표 저장
				this.ox2 = x;
				this.oy2 = y;
				// 새로운 점 저장
				this.x2 = x;
				this.y2 = y;
	};

	public void keepMove(Graphics graphics, int x, int y) {
		// 기존 점을 저장
				this.ox2 = this.x2;
				this.oy2 = this.y2;
				// 새로운 점 저장
				this.x2 = x;
				this.y2 = y;

				
				Graphics2D graphics2D = (Graphics2D) graphics;
				graphics2D.setXORMode(graphics2D.getBackground());
				graphics2D.draw(this.shape);

				int dx = x2 - ox2;
				int dy = y2 - oy2;
				AffineTransform affineTransform = new AffineTransform();
				System.out.println("keepMove");
				affineTransform.setToTranslation(dx, dy);
				this.shape = affineTransform.createTransformedShape(this.shape);
				graphics2D.draw(this.shape);
	};

	public void stopMove(Graphics graphics, int x, int y) {
	}

	public void startResize(Graphics graphics, int x, int y) {

		this.ox2 = x;
		this.oy2 = y;
		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
	}

	public void keepResize(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void stopResize(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		
	};

}
