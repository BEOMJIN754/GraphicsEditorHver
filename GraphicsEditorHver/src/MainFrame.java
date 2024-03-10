import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GPanel panel;
	
	public MainFrame() {
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.setLocation(200, 100);
		this.setSize(400, 600);
		
		this.panel = new GPanel();
		this.add(panel);

		this.toolBar = new GToolBar(panel);
		this.add(toolBar, BorderLayout.PAGE_START);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
