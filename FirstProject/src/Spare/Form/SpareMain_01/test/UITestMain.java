package Spare.Form.SpareMain_01.test;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Spare.Form.SpareMain_02.SpareMain_02_ScoreRecord;
import Spare.Form.SpareMain_03.SpareMain_03;

public class UITestMain extends JFrame {
	private JFrame Spare;
	private Button myHistory, scoreRecord, rank, myDetail, dayCheck, monthCheck, logOut;
	
	JPanel SpareMain, MainPanel01, MainPanel02, MainPanel03;
	
	UITestMain(String id) {
		Spare = new JFrame("Spare Main"); // 로그인 성공시 생성되는 메인 프레임
		Spare.setBounds(500, 300, 1000, 600);
		
//		logOut = new Button("로그아웃");
//		logOut.setBounds(900, 30, 80, 30);
//		logOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Spare.dispose();
//				new SpareLoginForm();
//			}
//		});
		
		myHistory = new Button("나의 기록");
		myHistory.setBounds(10, 105, 250, 100);
		myHistory.setFont(new Font("고딕", Font.BOLD, 25));
		myHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		
		scoreRecord = new Button("점수 기록");
		scoreRecord.setBounds(10, 230, 250, 100);
		scoreRecord.setFont(new Font("고딕", Font.BOLD, 25));
		scoreRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		rank = new Button("볼링장 내 순위");
		rank.setBounds(10, 355, 250, 100);
		rank.setFont(new Font("고딕", Font.BOLD, 25));
		rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
//		Spare.add(logOut);
		
		SpareMain = new JPanel();
		SpareMain.add(myHistory);
		SpareMain.add(scoreRecord);
		SpareMain.add(rank);
		
		
		
		add("West", SpareMain);
		
		setBounds(500, 300, 1000, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void dialogGapScore() {
		Dialog dl = new Dialog(Spare, "오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 200, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("조회할 점수가 없습니다.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	public static void main(String[] args) {
		new UITestMain("hiwoo");
	}
}
