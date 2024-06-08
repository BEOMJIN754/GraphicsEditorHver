package frames.main;

import frames.timer.CountdownListener;
import frames.timer.GTimer;
import frames.timer.TMainFrame;

public class GMain {

	public GMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		TMainFrame tMainFrame = new TMainFrame();
		tMainFrame.setVisible(true);

		// 백그라운드에서 생성
		GMainFrame mainFrame = new GMainFrame();

		// ----------------------
		// timer listener
		CountdownListener listener = new CountdownListener(tMainFrame, mainFrame);

		// ----------------------
		GTimer countdownTimer = new GTimer(3, listener);
		countdownTimer.start();

	}
}