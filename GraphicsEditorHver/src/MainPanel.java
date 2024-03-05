import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JButton;

public class MainPanel extends Panel {

	public  MainPanel() {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(400,600));
	
		
		Rectangle rectangle = new Rectangle(200,200);
		
	}	
	
	public void paint(Graphics g) {
		super.paint(g); 
		
		g.setColor(Color.yellow);
		g.fillRect(50, 50, 100, 100);
		g.setColor(Color.blue);
		g.drawOval(200, 200, 100, 100);
		
	}
	


}
