package Area;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main_01 extends WindowAdapter {
	public String ID = "hiwoo";
	private Frame main;
	private Button myh, sr, rank, myDetail;
	
	Main_01() {
		main = new Frame("메인 프레임"); // 로그인 성공시 생성되는 메인 프레임
		main.setBounds(500, 300, 1000, 600);
		main.setVisible(true);
		main.addWindowListener(this);
		main.setLayout(null);
		
		myh = new Button("나의 기록");
		myh.setBounds(150, 125, 250, 100);
		myh.setFont(new Font("고딕", Font.BOLD, 25));
		myh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDetail = new Button("나의 정보");
				myDetail.setBounds(600, 125, 250, 100);
				myDetail.setFont(new Font("고딕", Font.BOLD, 25));
				main.add(myDetail);
			}
		});
		
		sr = new Button("점수 기록");
		sr.setBounds(150, 250, 250, 100);
		sr.setFont(new Font("고딕", Font.BOLD, 25));
		
		rank = new Button("볼링장 내 순위");
		rank.setBounds(150, 375, 250, 100);
		rank.setFont(new Font("고딕", Font.BOLD, 25));
		// 버튼 width 250까지 
		
		main.add(myh);
		main.add(sr);
		main.add(rank);
		
		main.setVisible(true);
	}
	
	
	public void windowClosing(WindowEvent e) {
		main.dispose();
	}
	
	public static void main(String[] args) {
		new Main_01();
	}
}
