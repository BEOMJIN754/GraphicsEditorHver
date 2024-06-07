package shapetools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import javax.swing.UIManager;

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
	
	// 기준점 centerX
	private double cx, cy;
	// 변화율
	private double sx, sy;
	private double dx, dy;
	
	//앵커 fill할때 테두리가 흰색으로 남지 않게 하기위함
	private Color defaultBackground;
	// setters and getters
	public void setSelected(Graphics graphics) {
		this.drawAnchors(graphics);
	}

	public void clearSelected() {
		this.anchors = null;
		drawAnchors(null);
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
		//basic panel color=> UIManager가 알고있음 
		this.defaultBackground = UIManager.getColor("Panel.background");
		
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

	public abstract GShape clone();

	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
	};

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ앵커 그리기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void drawAnchors(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		Rectangle rectangle = this.shape.getBounds();
		int x = rectangle.x - 5;
		int y = rectangle.y - 5;
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
			graphics2D.setColor(defaultBackground);
			graphics2D.fill(anchor);
			graphics2D.setColor(Color.BLACK);
			graphics2D.draw(anchor);

		}

	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡeraseAnchorㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void eraseAnchors(Graphics graphics) {
		if (this.anchors != null) {
			Graphics2D graphics2D = (Graphics2D) graphics;
			graphics2D.setXORMode(graphics2D.getBackground());

			for (Ellipse2D.Float anchor : this.anchors) {
				graphics2D.setColor(defaultBackground);
				graphics2D.fill(anchor);
				graphics2D.setColor(Color.BLACK);
				graphics2D.draw(anchor);
			}
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
		boolean isOnShape = this.shape.contains(x, y) || this.shape.getBounds2D().contains(x, y);
		if (isOnShape) {
			this.eSelectedAnchor = EAnchors.eMM;
		}
		return isOnShape;
	}

	public void startMove(Graphics graphics, int x, int y) {
		this.eraseAnchors(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setPaintMode();
		graphics2D.draw(this.shape);
		// 좌표 저장
		this.ox2 = x;
		this.oy2 = y;
		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
	};

	public void keepMove(Graphics graphics, int x, int y) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		// 이전에 그려진 도형을 지우기 위해 XOR 모드로 설정
		graphics2D.setXORMode(graphics2D.getBackground());
		graphics2D.draw(this.shape);

		// 기존 점을 저장
		int ox2 = this.x2;
		int oy2 = this.y2;

		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;

		// 도형을 이동할 변위 계산
		dx = this.x2 - ox2;
		dy = this.y2 - oy2;

		// AffineTransform을 사용하여 도형 이동
		AffineTransform affineTransform = AffineTransform.getTranslateInstance(dx, dy);
		this.shape = affineTransform.createTransformedShape(this.shape);

		// 이동된 도형을 다시 그리기
		graphics2D.draw(this.shape);

	}

	public void stopMove(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
	}
	

	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡresizeㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void startResize(Graphics graphics, int x, int y) {
		this.eraseAnchors(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setPaintMode();
		graphics2D.draw(this.shape);
		
		this.ox2 = x2;
		this.oy2 = x2;
		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
	}
	
	public void keepResize(Graphics graphics, int x, int y) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		graphics2D.draw(this.shape);

		Rectangle bounds = this.shape.getBounds();
		double w = bounds.getWidth();
		double h = bounds.getHeight();

		AffineTransform affineTransform = new AffineTransform();

		switch (this.eSelectedAnchor) {
		case eEE:
			// Right edge anchor
			sx = (x - bounds.getX()) / w;
			cx = bounds.getX();
			//affineTransform.translate(cx, bounds.getY());
			affineTransform.scale(sx, 1);
			//affineTransform.translate(-cx, -bounds.getY());
			break;
		case eWW:
			// Left edge anchor
			sx = (bounds.getX() + w - x) / w;
			cx = bounds.getX() + w;
			affineTransform.translate(cx, bounds.getY());
			affineTransform.scale(sx, 1);
			affineTransform.translate(-cx, -bounds.getY());
			break;
		case eSS:
			// Bottom edge anchor
			sy = (y - bounds.getY()) / h;
			cy = bounds.getY();
			affineTransform.translate(bounds.getX(), cy);
			affineTransform.scale(1, sy);
			affineTransform.translate(-bounds.getX(), -cy);
			break;
		case eNN:
			// Top edge anchor
			sy = (bounds.getY() + h - y) / h;
			cy = bounds.getY() + h;
			affineTransform.translate(bounds.getX(), cy);
			affineTransform.scale(1, sy);
			affineTransform.translate(-bounds.getX(), -cy);
			break;
		case eNE:
			// Right top corner
			sx = (x - bounds.getX()) / w;
			sy = (bounds.getY() + h - y) / h;
			cx = bounds.getX();
			cy = bounds.getY() + h;
			affineTransform.translate(cx, cy);
			affineTransform.scale(sx, sy);
			affineTransform.translate(-cx, -cy);
			break;
		case eNW:
			// Left top corner
			sx = (bounds.getX() + w - x) / w;
			sy = (bounds.getY() + h - y) / h;
			cx = bounds.getX() + w;
			cy = bounds.getY() + h;
			affineTransform.translate(cx, cy);
			affineTransform.scale(sx, sy);
			affineTransform.translate(-cx, -cy);
			break;
		case eSE:
			// Right bottom corner
			sx = (x - bounds.getX()) / w;
			sy = (y - bounds.getY()) / h;
			cx = bounds.getX();
			cy = bounds.getY();
			affineTransform.translate(cx, cy);
			affineTransform.scale(sx, sy);
			affineTransform.translate(-cx, -cy);
			break;
		case eSW:
			// Left bottom corner
			sx = (bounds.getX() + w - x) / w;
			sy = (y - bounds.getY()) / h;
			cx = bounds.getX() + w;
			cy = bounds.getY();
			affineTransform.translate(cx, cy);
			affineTransform.scale(sx, sy);
			affineTransform.translate(-cx, -cy);
			break;
		default:
			return;
		}

		this.shape = affineTransform.createTransformedShape(this.shape);
		graphics2D.draw(this.shape);

	}

	public void stopResize(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);

	}
// ㅡㅡㅡㅡㅡㅡㅡㅡ얘로 앵커가 이미 있으면 false, 없으면 트루 반환하려 함.
//	public boolean anchorExist() {
//		if(eSelectedAnchor != null) {
//		return true;
//		}
//		else {
//			return false;
//		}
//	};
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡrotateㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public void startRotate(Graphics graphics, int x, int y) {
		this.eraseAnchors(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setPaintMode();
		graphics2D.draw(this.shape);
		
		this.ox2 = x2;
		this.oy2 = x2;
		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
		
	}
	
	public double getRotateFactor() {
		double startX = this.anchors[EAnchors.eWW.ordinal()].getCenterX();
		double startY = this.anchors[EAnchors.eWW.ordinal()].getCenterY();
		cx = this.shape.getBounds().getCenterX();
		cy = this.shape.getBounds().getCenterY();
		
		
		double basicAngle = Math.toDegrees(Math.atan2(cx-startX, cy-startY));
		double afterAngle = Math.toDegrees(Math.atan2(cx-x2, cy-y2));
		double rotateAngle = Math.toDegrees(basicAngle-afterAngle);
		
		if (rotateAngle < 0) {
			rotateAngle += 360;
		}
		
		return rotateAngle;
	}
	public void keepRotate(Graphics graphics, int x, int y) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		graphics2D.draw(this.shape);
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(getRotateFactor()), cx, cy);
		this.shape = affineTransform.createTransformedShape(this.shape);
		graphics2D.draw(this.shape);
	}
	
	public void stopRotate(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
	}
	
}
