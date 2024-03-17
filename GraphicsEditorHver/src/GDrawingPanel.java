import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GShapeToolBar gShapeToolBar;
	private String com;

	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void paint(Graphics g) {
	}
	
	public void Shape(String com) {
		this.com = com;
		
		}
	
	public void drawRec(int x ,int y) {
		Graphics graphics = this.getGraphics();
		graphics.drawRect(x, y, 20, 30);
	}
	
	public void drawO(int x ,int y) {
		Graphics graphics = this.getGraphics();
		graphics.drawOval(x, y, 20, 30);
	}
	
	public void drawL(int x ,int y, int z, int a) {
		Graphics graphics = this.getGraphics();
		graphics.drawLine(x, y, z, a);
	}
	
	public void drawHexagon(int x, int y) {
        Graphics graphics = this.getGraphics();
        int[] xPoints = {x, x + 20, x + 30, x + 20, x, x - 10};
        int[] yPoints = {y + 10, y, y + 10, y + 30, y + 40, y + 30};
        graphics.drawPolygon(xPoints, yPoints, 6);
	}
	
	
	private class MouseEventHandler implements MouseListener, MouseMotionListener{
		int startX;
		int startY;
		int endX;
		int endY; 

		@Override
		public void mouseClicked(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
			if(com.equals("rectangle")){
				drawRec(e.getX(), e.getY());
			}else if(com.equals("oval")) {
				drawO(e.getX(),e.getY());
			}else if(com.equals("polygon")) {
				drawHexagon(e.getX(),e.getY());
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
			startX = e.getX();
			startY = e.getY();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
			endX = e.getX();
			endY = e.getY();
			if(com.equals("line")) {
				drawL(startX, startY, endX, endY);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        System.out.println("methodName = " + methodName);
		}
		
	}
}