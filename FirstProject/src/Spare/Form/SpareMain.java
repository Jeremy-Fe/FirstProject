package Spare.Form;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Spare.Form.SpareMain_01.SpareMain_01_01;

public class SpareMain extends WindowAdapter {
	private Frame Spare;
	private Button myHistory, scoreRecord, rank, myDetail, dayCheck, monthCheck;
	
	SpareMain(String id) {
		Spare = new Frame("메인 프레임"); // 로그인 성공시 생성되는 메인 프레임
		Spare.setBounds(500, 300, 1000, 600);
		Spare.setVisible(true);
		Spare.addWindowListener(this);
		Spare.setLayout(null);
		
		myHistory = new Button("나의 기록");
		myHistory.setBounds(150, 125, 250, 100);
		myHistory.setFont(new Font("고딕", Font.BOLD, 25));
		myHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDetail = new Button("나의 정보");
				myDetail.setBounds(600, 125, 250, 100);
				myDetail.setFont(new Font("고딕", Font.BOLD, 25));
				myDetail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new SpareMain_01_01(id);
					}
				});
				
				dayCheck = new Button("일별 조회");
				dayCheck.setBounds(600, 250, 250, 100);
				dayCheck.setFont(new Font("고딕", Font.BOLD, 25));
				
				monthCheck = new Button("월별 조회");
				monthCheck.setBounds(600, 375, 250, 100);
				monthCheck.setFont(new Font("고딕", Font.BOLD, 25));
				
				Spare.add(myDetail);
				Spare.add(dayCheck);
				Spare.add(monthCheck);
			}
		});
		
		scoreRecord = new Button("점수 기록");
		scoreRecord.setBounds(150, 250, 250, 100);
		scoreRecord.setFont(new Font("고딕", Font.BOLD, 25));
		
		
		rank = new Button("볼링장 내 순위");
		rank.setBounds(150, 375, 250, 100);
		rank.setFont(new Font("고딕", Font.BOLD, 25));
		
		Spare.add(myHistory);
		Spare.add(scoreRecord);
		Spare.add(rank);
		
		Spare.setVisible(true);
	}
	public void windowClosing(WindowEvent e) {
		Spare.dispose();
	}
	
	public static void main(String[] args) {
		new SpareMain("안녕");
	}
}
