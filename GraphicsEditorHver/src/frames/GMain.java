package frames;

import javax.swing.SwingUtilities;

public class GMain {

	public GMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		TMainFrame tMainFrame = new TMainFrame();
		tMainFrame.setVisible(true);
		
		//백그라운드에서 생성
		GMainFrame mainFrame = new GMainFrame();
		GTimer gTimer = new GTimer(5, new GTimer.CountdownListener() {
            @Override
            public void onTick(int seconds) {
                // 타이머의 남은 시간을 AFrame에 업데이트
            	 SwingUtilities.invokeLater(new Runnable() {
                     @Override
                     public void run() {
                         tMainFrame.updateTimerLabel(seconds);
                     }
                 });
            }
		

            @Override
            public void onFinish() {
                SwingUtilities.invokeLater(() -> {
                    // AFrame을 닫고 BFrame을 표시
                    tMainFrame.dispose();
                    mainFrame.setVisible(true);
                });
            }
        });
        gTimer.start();
    }
		
//		mainFrame.setVisible(true);
//		mainFrame.initialize();

	}


