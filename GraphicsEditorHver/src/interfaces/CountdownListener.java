package interfaces;

public interface CountdownListener {
    void onTick(int seconds);
    void onFinish();
}
