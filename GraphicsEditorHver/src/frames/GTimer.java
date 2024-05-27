package frames;

import javax.swing.JLabel;

public class GTimer extends Thread {

	private int countdown;
    private CountdownListener listener;

    public GTimer(int seconds, CountdownListener listener) {
        this.countdown = seconds;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            while (countdown > 0) {
                listener.onTick(countdown);
                Thread.sleep(1000);
                countdown--;
            }
            listener.onFinish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface CountdownListener {
        void onTick(int seconds);
        void onFinish();
    }

}
