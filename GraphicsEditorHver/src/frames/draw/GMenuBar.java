package frames.draw;
import javax.swing.JMenuBar;

import menus.GFileMenu;
import menus.GMenuItem;

public class GMenuBar extends JMenuBar {

	private static final long serialVersionUID = 9000032843023099413L;

	public GFileMenu fileMenu;

	private GFileMenu editMenu;
	private GDrawingPanel drawingpanel;

	public GMenuBar() {

		this.fileMenu = new GFileMenu("File");
		this.editMenu = new GFileMenu("Edit");

		this.add(this.fileMenu);
		this.add(this.editMenu);
		
		fileMenu.add(new GMenuItem("tool"));
		fileMenu.add(new GMenuItem("quit"));
		fileMenu.add(new GMenuItem("setting"));
		
		editMenu.add(new GMenuItem("upload"));
		editMenu.add(new GMenuItem("download"));
		
		
	}
	public void associate(GDrawingPanel drawingpanel) {

		this.drawingpanel = drawingpanel;
		this.fileMenu.associate(drawingpanel);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}