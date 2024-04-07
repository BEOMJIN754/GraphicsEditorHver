package shapetools;
import java.awt.Graphics;

public abstract class GShape {
	
	protected int x1, y1, x2, y2, ox2, oy2;
	
	public GShape() {
	
		
		
	}
	public void setP1(int x1,int y1) {
		this.x1= x1;
		this.y1 = y1;
		this.x2 = x1;
		this.y2 = y1;
		this.ox2 = x1;
		this.oy2 = y1;
		
	}
	public void setP2(int x2,int y2) {
		
		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public abstract  void draw( Graphics graphics);
		
	
}
