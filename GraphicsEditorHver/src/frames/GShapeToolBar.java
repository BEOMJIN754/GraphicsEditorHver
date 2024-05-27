package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.Constants.EShapeButtons;

public class GShapeToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	GDrawingPanel drawingPanel;

	public GShapeToolBar() {

		// add ActionHandler
		ButtonGroup buttonGroup = new ButtonGroup();
		ShapeActionHandler shapeActionHandler = new ShapeActionHandler();

		for (EShapeButtons eShapeButtons : EShapeButtons.values()) {
			JRadioButton button = new JRadioButton(eShapeButtons.getText());

			button.addActionListener(shapeActionHandler);
			button.setActionCommand(eShapeButtons.toString());
			add(button);
			buttonGroup.add(button);
		}
	}

	public void initialize() {
		JRadioButton defaultButton = (JRadioButton) (this.getComponent(EShapeButtons.eRactangle.ordinal()));
		defaultButton.doClick();
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	private void setShapeTool(EShapeButtons eShapeButton) {
		drawingPanel.setShapeTool(eShapeButton.getShapeTool());
	}

	private class ShapeActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EShapeButtons eShapeButton = EShapeButtons.valueOf(e.getActionCommand());
			setShapeTool(eShapeButton);

		}

	}

}
