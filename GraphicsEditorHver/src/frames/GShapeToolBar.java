package frames;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.Constants.EShapeButtons;

public class GShapeToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	
	private JRadioButton rectangleButton;
	private JRadioButton ovalButton;
	private JRadioButton lineButton;
	private JRadioButton polygonButton;
	private GDrawingPanel drawingPanel;



	public GShapeToolBar(GMainFrame.ShapeActionHandler shapeActionHandler) {
	

		// add ActionHandler
		// add ActionHandler
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(EShapeButtons eShapeButtons : EShapeButtons.values()) {
			JRadioButton button = new JRadioButton(eShapeButtons.getText()); //eRac 바꿔야해!!
		
			button.addActionListener(shapeActionHandler);
			button.setActionCommand(eShapeButtons.toString());
			this.add(button);
			buttonGroup.add(button);
		}

	}
}
