package frames.timer;

import javax.swing.*;
import java.awt.*;

public class TMainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel timerLabel;
	private JLabel imageLabel;

	public TMainFrame() {
		setTitle("Loading ...");
		//setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 타이머 레이블 초기화
		this.timerLabel = new JLabel("5", SwingConstants.CENTER);
		this.timerLabel.setFont(new Font("Serif", Font.BOLD, 48));
		// -----------------------
		// 이미지 레이블 설정
		ImageIcon loading = new ImageIcon("assets\\animation\\soon.gif");
		this.imageLabel = new JLabel(loading);
		//this.imageLabel.setSize(new Dimension(50, 50));
		// -----------------------
		// 패널 설정
		JPanel panel = new JPanel(new BorderLayout()); 
		panel.add(timerLabel, BorderLayout.NORTH); 
		panel.add(imageLabel, BorderLayout.CENTER); 
		// -----------------------
		this.add(panel);
		this.pack();
		setLocationRelativeTo(null); // 화면 가운데에 표시
	}

	public void updateTimerLabel(int seconds) {
		timerLabel.setText(String.valueOf(seconds));
	}
}
