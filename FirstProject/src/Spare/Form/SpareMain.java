package Spare.Form;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SpareMain extends WindowAdapter {
	private Frame main;
	
	SpareMain() {
		main = new Frame("메인 프레임"); // 로그인 성공시 생성되는 메인 프레임
		main.setBounds(500, 300, 1000, 500);
		main.setVisible(true);
		main.addWindowListener(this);
		
	}
	public void windowClosing(WindowEvent e) {
		main.dispose();
	}
}
