package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GPolygon;
import shapetools.GShape;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState
	}

	private EDrawingState eDrawingState;
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;

	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);

		this.eDrawingState = EDrawingState.eIdle;

		this.shapes = new Vector<GShape>();
	}

	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	public void paint(Graphics g) {
		System.out.println("paint");
		for (GShape shape : shapes) {
			shape.draw(g);
		}
	}

	 private void startDrawing(int x, int y) {
	        if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
	            currentShape = shapeTool.clone();
	            currentShape.setP1(x, y);
	        } else if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
	            // 다각형 도구를 사용하는 경우 새로운 GPolygon 생성
	            currentShape = shapeTool.clone();
	            GPolygon polygon = (GPolygon) currentShape;
	            polygon.addPoint(x, y);
	        }
	    }

	    private void keepDrawing(int x, int y) {
	        if (currentShape != null && currentShape.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
	            currentShape.setP2(x, y);
	            currentShape.drag(getGraphics());
	        } else if (currentShape != null && currentShape.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
	            GPolygon polygon = (GPolygon) currentShape;
	            polygon.addPoint(x, y);
	            repaint(); // 패널 다시 그리기 - 다각형이 업데이트됨
	        }
	    }

	    private void stopDrawing(int x, int y) {
	        if (currentShape != null && currentShape.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
	            GPolygon polygon = (GPolygon) currentShape;
	            polygon.addPoint(x, y);
	            shapes.add(polygon); // 완성된 다각형을 목록에 추가
	            currentShape = null;
	            repaint(); // 패널 다시 그리기 - 최종 다각형 표시
	        }
	    }

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
	        if (eDrawingState == EDrawingState.e2PState) {
	            if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
	                startDrawing(e.getX(), e.getY());
	                eDrawingState = EDrawingState.e2PState;
	            }
	        } else if (eDrawingState == EDrawingState.eNPState) {
	            if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
	                startDrawing(e.getX(), e.getY());
	                eDrawingState = EDrawingState.eNPState;
	            }
	        }
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
	        if (eDrawingState == EDrawingState.e2PState) {
	            keepDrawing(e.getX(), e.getY());
	        } else if (eDrawingState == EDrawingState.eNPState) {
	            keepDrawing(e.getX(), e.getY());
	        }
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	        if (eDrawingState == EDrawingState.e2PState) {
	            stopDrawing(e.getX(), e.getY());
	            eDrawingState = EDrawingState.eIdle;
	        } else if (eDrawingState == EDrawingState.eNPState) {
	            stopDrawing(e.getX(), e.getY());
	            eDrawingState = EDrawingState.eIdle;
	        }
	    }


		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
