package frames.draw;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import shapetools.GShape;

public class GMover extends GTransformer {

	public GMover(GShape currentShape) {
		super(currentShape);
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		
		graphics.setXORMode(graphics.getBackground());
		graphics.draw(this.currentShape.getShape());

		// 기존 점을 저장
		this.ox2 = this.x2;
		this.oy2 = this.y2;

		// 새로운 점 저장
		this.x2 = x;
		this.y2 = y;
		System.out.println("x2: "+x2+" y2: "+y2+"//"+" ox2: "+ox2+" oy2:"+oy2);
		// 도형을 이동할 변위 계산
		dx = x - ox2;
		dy = x - oy2;

		// AffineTransform을 사용하여 도형 이동
		affineTransform = AffineTransform.getTranslateInstance(dx, dy);
		Shape shape = this.currentShape.getShape();
		shape = affineTransform.createTransformedShape(this.currentShape.getShape());

		// 이동된 도형을 다시 그리기
		//graphics.draw(this.currentShape.getShape());
		graphics.draw(shape);

	}

	

}
