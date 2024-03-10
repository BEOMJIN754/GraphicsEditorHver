import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class GToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	private JButton rButton;

	private JButton oButton;

	private JButton lButton;

	private JButton pButton;

	private GPanel gPanel;

	public String toolButton;

	public GToolBar(GPanel gPanel) {
		this.gPanel = gPanel;

		this.rButton = new JButton("rectangle");

		this.oButton = new JButton("oval");

		this.lButton = new JButton("line");

		this.pButton = new JButton("polygon");

		rButton.setActionCommand("rectangle");
		oButton.setActionCommand("oval");
		lButton.setActionCommand("line");
		pButton.setActionCommand("polygon");

		rButton.addActionListener(AC);

		oButton.addActionListener(AC);

		lButton.addActionListener(AC);

		pButton.addActionListener(AC);

		this.add(rButton);
		this.add(oButton);
		this.add(lButton);
		this.add(pButton);

	}

	private ActionListener AC = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String gToolBut = e.getActionCommand();
			if (gToolBut.equals("rectangle")) {
				gPanel.drawRect();

			}
		}
	};
}
