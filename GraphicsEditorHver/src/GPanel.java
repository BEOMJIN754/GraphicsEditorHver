import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Point startPoint; // 사각형의 시작 지점 저장
    private Point endPoint;   // 사각형의 끝 지점 저장
    private boolean isDragging;  // 드래그 여부

    public GPanel() {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(400, 600));

        // 마우스 이벤트 처리를 위한 마우스 리스너 추가
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint(); // 마우스가 눌린 지점을 시작 지점으로 설정
                isDragging = isInsideRectangle(startPoint);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();   // 마우스가 떼진 지점을 끝 지점으로 설정
                isDragging = false;
                repaint();  // 다시 그리기를 트리거하여 사각형을 그립니다.
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    endPoint = e.getPoint();   // 드래그 중일 때 끝 지점을 업데이트
                    repaint();  // 다시 그리기를 트리거하여 사각형을 그립니다.
                }
            }
        });
    }

    private boolean isInsideRectangle(Point point) {
        if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            return point.x >= x && point.x <= x + width && point.y >= y && point.y <= y + height;
        }
        return false;
    }
    public void drawRect() {
  		Graphics g = getGraphics();
  		
  		
  		g.setColor(Color.BLUE);
  		g.fillRect(50, 50, 100, 80);
  		
  	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startPoint != null && endPoint != null) {
            // 시작 지점과 끝 지점이 모두 설정되면 사각형을 그립니다.
            g.setColor(Color.BLUE);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            g.fillRect(x, y, width, height);
        }
    }
}