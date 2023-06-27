package Spare.Garbage.TESTEST;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	JPanel mainPanel;
	CardLayout card;
	
	public MainFrame() {
		super("CardLayout 연습");
		
		// 메뉴 생성
		init_menubar();
		
		// 카드 레이아웃 초기화();
		init_cardLayout();
		
		// 위치
		this.setLocation(200, 100);
		
		// 크기
		this.setSize(400, 400);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void init_cardLayout() {
		card = new CardLayout();
		
		mainPanel = new JPanel(card); // 적재방식이 카드레이아웃을 사용하겠다
		
		// 각각의 화면을 MainPanel 에 넣는다
		mainPanel.add(new Screen1(), "s1");
		mainPanel.add(new Screen2(), "s2");
		mainPanel.add(new Screen3(), "s3");
		
		// 현재 Frame의 Center를 넣는다.
		this.add(mainPanel, "Center");
		
	}
	public void init_menubar() {
		JPanel p = new JPanel(new GridLayout(3,1));
		
		JButton jbt1 = new JButton("화면1");
		JButton jbt2 = new JButton("화면2");
		JButton jbt3 = new JButton("화면3");
		
		// 패널에 버튼을 넣는다.
		p.add(jbt1);
		p.add(jbt2);
		p.add(jbt3);
		
		this.add(p,"West");
		
		// 버튼 이벤트 등록
		
		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "s1");
			}
		});
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "s2");
			}
		});
		jbt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "s3");
			}
		});
		
		
		
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
