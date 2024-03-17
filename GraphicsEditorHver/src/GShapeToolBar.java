import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GShapeToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	private JRadioButton rectangleButton;
	private JRadioButton ovalButton;
	private JRadioButton lineButton;
	private JRadioButton polygonButton;
	private GDrawingPanel drawingPanel;

	
	public GShapeToolBar(GDrawingPanel drawingPanel) {
	this.drawingPanel = drawingPanel;
	
	ButtonGroup buttonGroup = new ButtonGroup();

	this.rectangleButton = new JRadioButton("rectangle");
	this.add(rectangleButton);
	buttonGroup.add(rectangleButton);
	rectangleButton.addActionListener(ac);
	rectangleButton.setActionCommand("rectangle");
	
	this.ovalButton = new JRadioButton("oval");
	this.add(ovalButton);
	buttonGroup.add(ovalButton);
	ovalButton.addActionListener(ac);
	ovalButton.setActionCommand("oval");
	
	this.lineButton = new JRadioButton("line");
	this.add(lineButton);
	buttonGroup.add(lineButton);
	lineButton.addActionListener(ac);
	lineButton.setActionCommand("line");
	
	this.polygonButton = new JRadioButton("polygon");
	this.add(polygonButton);
	buttonGroup.add(polygonButton);
	polygonButton.addActionListener(ac);
	polygonButton.setActionCommand("polygon");
	}

	private ActionListener ac = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String com = e.getActionCommand();
			drawingPanel.Shape(com);
		}
	};
}
