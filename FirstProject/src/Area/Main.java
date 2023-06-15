package Area;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends WindowAdapter {
	private Frame main;
	
	Main() {
		main = new Frame("메인 프레임"); // 로그인 성공시 생성되는 메인 프레임
		main.setBounds(500, 300, 1000, 600);
		main.setVisible(true);
		main.addWindowListener(this);
		
	}
	public void windowClosing(WindowEvent e) {
		main.dispose();
	}
	
	
	
	
	public static void main(String[] args) {
		new Main();
	}
}
