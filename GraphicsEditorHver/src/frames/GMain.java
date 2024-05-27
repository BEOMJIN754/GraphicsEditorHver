package frames;

import interfaces.CountdownListener;

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
		CountdownListener listener = new CountdownListener() {

			// timer continue
			@Override
			public void onTick(int seconds) {
				tMainFrame.updateTimerLabel(seconds);
			}

			// timer end
			@Override
			public void onFinish() {
				tMainFrame.dispose();
				mainFrame.initialize();
				mainFrame.setVisible(true);
			}
		};

		// ----------------------
		GTimer countdownTimer = new GTimer(5, listener);
		countdownTimer.start();

	}
}
