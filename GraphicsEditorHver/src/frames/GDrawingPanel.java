package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState, eTransformation
	}

//	private enum EAnchor {
//		eMove, eResize, eRotate
//	}

	private EDrawingState eDrawingState;
//	private enum ETransformation{
//		eDraw,
//		eMove,
//		eResize,
//		eRotate
//	}
//	private ETransformation eTransformation;
	// component 부품
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;

	// constructors
	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);

		this.eDrawingState = EDrawingState.eIdle;

		this.shapes = new Vector<GShape>();
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	// setters and getters
	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	public Vector<GShape> getShapes() {
		return this.shapes;
	}

	public void setShapes(Object object) {
		this.shapes = (Vector<GShape>) object;
	}

	// methods

	public void paint(Graphics graphics) {
		for (GShape shape : shapes) {
			shape.draw(graphics);
		}
	}

	private void startDrawing(int x, int y) {
		currentShape = shapeTool.clone();
		currentShape.setOrigin(x, y);
	}

	private void keepDrawing(int x, int y) {
		currentShape.movePoint(x, y);
		currentShape.drag(getGraphics());
	}

//	private void moveDrawing(int x, int y) {
//		currentShape.movePoint(x, y);
//		// only polygon
//		currentShape.move(getGraphics());
//	}

	private void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
		// Only Polygon
		currentShape.click(getGraphics());
		// re-draw
		currentShape.draw(getGraphics());
	}

	private void stopDrawing(int x, int y) {
		currentShape.addPoint(x, y);
		shapes.add(currentShape);
	}
	
	
	public void keepMoving(int x, int y) {
		currentShape.keepMove(getGraphics(), x, y);
		
	}

	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapes) {
			System.out.println("Panel onShape");
			boolean isShape = shape.onShape(x, y);
			if (isShape) {
				return shape;
			}
		}
		return null;
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				if (eDrawingState == EDrawingState.eIdle) {
					// 처음 폴리곤 그릴 때
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.eNPState;
					}
					// 이미 폴리곤 그리는 중일 때, 폴리곤 점 추가
				} else if (eDrawingState == EDrawingState.eNPState) {
					continueDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPState;
				}

			} else if (e.getClickCount() == 2) {
				// 더블클릭이면 누르면 폴리곤 그리기 종료
				if (eDrawingState == EDrawingState.eNPState) {
					stopDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// 폴리곤 그릴 때 마지막 점 위치(마우스 포인터) 따라가면서 그리기
//			if (eDrawingState == EDrawingState.eNPState) {
//				moveDrawing(e.getX(), e.getY());
//				eDrawingState = EDrawingState.eNPState;
//			}
			

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				currentShape = onShape(e.getX(), e.getY());
				// 선택된 도형이 없을 때
				if (onShape(e.getX(), e.getY()) == null) {
					// 2개 점으로 그리는 것들
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.e2PState;
					} else {
						// ploygon drawing (click에서 진행중임)

					}
					// 도형 내 클릭했을 때 MOVE
				} else {
					currentShape.startMove(e.getX(), e.getY());
					eDrawingState = EDrawingState.eTransformation;
				}

			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// 2개 점으로 그리는 것들 그리기 진행중
			if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eTransformation) {
				keepMoving(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// 2개 점으로 그리는 것들 그리기 종료
			if (eDrawingState == EDrawingState.e2PState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
				// 도형 선택한 이후 변형이면 변형 종료
			} else if (eDrawingState == EDrawingState.eTransformation) {
				currentShape.stopMove(e.getX(), e.getY());
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
