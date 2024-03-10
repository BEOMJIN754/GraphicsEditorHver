import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GPanel extends JPanel {

	public GPanel() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(400, 600));
	}

	public void drawRect() {
		Graphics g = getGraphics();
		
		g.setColor(Color.BLUE);
		g.fillRect(50, 50, 100, 80);
		
	}
	
}
