package Spare.Form;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Spare.Form.SpareMain_01.SpareMain_01_01;
import Spare.Form.SpareMain_01.SpareMain_01_02;
import Spare.Form.SpareMain_01.SpareMain_01_03;
import Spare.Form.SpareMain_02.SpareMain_02_ScoreRecord;
import Spare.Form.SpareMain_03.SpareMain_03;

public class SpareMain extends WindowAdapter {
	private Frame Spare;
	private Button myHistory, scoreRecord, rank, myDetail, dayCheck, monthCheck, logOut;

	SpareMain(String id) {
		Spare = new Frame("Spare Main"); // 로그인 성공시 생성되는 메인 프레임
		Spare.setBounds(500, 300, 1000, 600);
		Spare.setVisible(true);
		Spare.addWindowListener(this);
		Spare.setLayout(null);

		logOut = new Button("로그아웃");
		logOut.setBounds(900, 30, 80, 30);
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spare.dispose();
				new SpareLoginForm();
			}
		});

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
						try {
							new SpareMain_01_01(id);
						} catch (ArrayIndexOutOfBoundsException ae) {
							dialogGapScore();
						}
					}
				});

				dayCheck = new Button("일별 조회");
				dayCheck.setBounds(600, 250, 250, 100);
				dayCheck.setFont(new Font("고딕", Font.BOLD, 25));
				dayCheck.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new SpareMain_01_02(id);

					}
				});

				monthCheck = new Button("월별 조회");
				monthCheck.setBounds(600, 375, 250, 100);
				monthCheck.setFont(new Font("고딕", Font.BOLD, 25));
				monthCheck.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new SpareMain_01_03(id);
					}
				});

				Spare.add(myDetail);
				Spare.add(dayCheck);
				Spare.add(monthCheck);
			}
		});

		scoreRecord = new Button("점수 기록");
		scoreRecord.setBounds(150, 250, 250, 100);
		scoreRecord.setFont(new Font("고딕", Font.BOLD, 25));
		scoreRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SpareMain_02_ScoreRecord(id);
			}
		});

		rank = new Button("볼링장 내 순위");
		rank.setBounds(150, 375, 250, 100);
		rank.setFont(new Font("고딕", Font.BOLD, 25));
		rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SpareMain_03();
			}
		});

		Spare.add(logOut);
		Spare.add(myHistory);
		Spare.add(scoreRecord);
		Spare.add(rank);

		Spare.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		Spare.dispose();
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

}
