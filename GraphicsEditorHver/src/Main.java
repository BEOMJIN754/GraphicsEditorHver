import javax.swing.JFrame;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);

		MainPanel panel = new MainPanel();
		
		mainFrame.add(panel) ;
		mainFrame.pack();
		
	}

}
