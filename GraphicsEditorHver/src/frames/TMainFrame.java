package frames;

import javax.swing.*;
import java.awt.*;

public class TMainFrame extends JFrame {
    private JLabel timerLabel;

    public TMainFrame() {
        setTitle("TMainFrame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데에 표시

        // 타이머 레이블 초기화
        timerLabel = new JLabel("5", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 48));
        add(timerLabel);

        // 패널 설정
        JPanel panel = new JPanel();
        panel.add(timerLabel);
        add(panel);
    }

    public void updateTimerLabel(int seconds) {
        timerLabel.setText(String.valueOf(seconds));
    }
}
