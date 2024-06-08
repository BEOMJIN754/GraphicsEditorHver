package frames.timer;

import frames.main.GMainFrame;

public class CountdownListener {

	private TMainFrame tMainFrame;
	private GMainFrame mainFrame;

	public CountdownListener(TMainFrame tMainFrame, GMainFrame gMainFrame) {
		this.tMainFrame = tMainFrame;
		this.mainFrame = gMainFrame;
	}

	// timer continue
	public void onTick(int seconds) {
		tMainFrame.updateTimerLabel(seconds);
	}

	// timer end
	public void onFinish() {
		tMainFrame.dispose();
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}
}