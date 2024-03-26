package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GOval extends GShape {

	private int prevX1, prevY1, prevOx2, prevOy2;

	public GOval() {

	}

	@Override
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		// erase old shape
		graphics2D.drawOval(prevX1, prevY1, prevOx2 - prevX1, prevOy2 - prevY1); // 이전 도형 지우기

		// draw new shape
		graphics2D.drawOval(x1, y1, x2 - x1, y2 - y1); // 새로운 도형 그리기

		// 이전 도형의 좌표를 현재 도형의 좌표로 갱신
		prevX1 = x1;
		prevY1 = y1;
		prevOx2 = x2;
		prevOy2 = y2;
	}
}
