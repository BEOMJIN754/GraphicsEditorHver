import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GShapeToolBar shapeToolBar;

	private GDrawingPanel drawingPanel; 
	
	public GMainFrame() {
		this.setSize(400, 600);
		
		//	LayoutManager layoutManager = new FlowLayout();
			//LayoutManager layoutManager = new CardLayout();
			LayoutManager layoutManager = new BorderLayout();
			//자식들으 ㅣ배치를 정리해준 것 셋 레이아웃 이라는 관리자를 만듦
			//LayoutManager layoutManager = new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS);
			this.setLayout(layoutManager);
			
			this.menuBar = new GMenuBar();
			this.setJMenuBar(this.menuBar);
			
			this.drawingPanel = new GDrawingPanel();
			this.add(drawingPanel, BorderLayout.CENTER);

			ShapeActionHandler shapeActionHandler = new ShapeActionHandler();
			this.shapeToolBar = new GShapeToolBar(shapeActionHandler);
			this.add(shapeToolBar,BorderLayout.NORTH);
			
		
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			
	}
	
	public class ShapeActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			drawingPanel.setShapeText(((JRadioButton)e.getSource()).getText());
			
		}	
		
	}
}
