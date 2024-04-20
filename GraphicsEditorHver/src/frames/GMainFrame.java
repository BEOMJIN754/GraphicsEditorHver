package frames;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

import global.Constants.EShapeButtons;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//component
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
		

		ShapeActionHandler shapeActionHandler = new ShapeActionHandler();
		this.shapeToolBar = new GShapeToolBar(shapeActionHandler);
		this.add(shapeToolBar,BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
			
		}
	//method
	public void initialize() {
		// 메뉴바 쉐입툴 바,드로잉 패널을 자식으로 가지고 있음 
		this.shapeToolBar.initialize();
		this.menuBar.initialize();
		this.drawingPanel.initialize();
		
		
		
	}
	
		
	public class ShapeActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EShapeButtons eShapeButton = EShapeButtons.valueOf(e.getActionCommand());
			drawingPanel.setShapeTool(eShapeButton.getShapeTool());
			
		}	
		
	}

	
}
