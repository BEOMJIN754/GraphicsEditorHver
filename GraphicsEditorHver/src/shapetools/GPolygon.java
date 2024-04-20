package shapetools;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class GPolygon extends GShape {

    private Polygon polygon;
    private ArrayList<Point> points;

    public GPolygon() {
        super(EDrawingStyle.eNPStyle);
        this.polygon = new Polygon();
        this.points = new ArrayList<>();
    }

    public GPolygon clone() {
        return new GPolygon();
    }

    @Override
    public void draw(Graphics graphics) {
        // 그리기 전에 다각형을 업데이트
        updatePolygon();
        // 다각형 그리기
        graphics.drawPolygon(polygon);
    }

    @Override
    public void drag(Graphics graphics) {
        // 드래그 시 동작 정의
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
        updatePolygon(); // 점 추가 후 다각형 업데이트
    }

    private void updatePolygon() {
        polygon.reset(); // 다각형 초기화
        for (Point point : points) {
            polygon.addPoint(point.x, point.y); // 모든 점을 다각형에 추가
        }
    }
}
