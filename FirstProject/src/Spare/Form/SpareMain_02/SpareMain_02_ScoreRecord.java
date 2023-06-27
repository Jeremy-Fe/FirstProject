package Spare.Form.SpareMain_02;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SpareMain_02_ScoreRecord extends WindowAdapter {
	private Frame scoreRecord;
	private Label[] g;
	private TextField[] t;
	private Label info;
	private Button save;
	private Choice y, m, d;
	private String selectDate;
	private String[] ID, G_DATE, G_SCORE;
	private int[] S_ID;

	int scoreCount = 0;
	boolean cntEr, typeEr, scoreEr, gapDelete, scoreNull;

	String id = "", pw = "", pwRe = "", name = "", club = "";

	public SpareMain_02_ScoreRecord(String id) {
		scoreRecord = new Frame("Spare Score Record");
		scoreRecord.setBounds(1000, 270, 500, 600);
		scoreRecord.setLayout(null);
		scoreRecord.addWindowListener(this);

		y = new Choice(); // 년도
		y.add("년도");
		y.addItem("2023");
		y.addItem("2022");
		y.addItem("2021");
		y.setBounds(50, 80, 90, 40);

		m = new Choice(); // 월
		m.add("월");
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				m.addItem("0" + String.valueOf(i));
			} else {
				m.addItem(String.valueOf(i));
			}
		}
		m.setBounds(150, 80, 90, 40);

		d = new Choice(); // 일
		d.add("일");

		m.addItemListener(new ItemListener() { // 월 선택 시 해당 월 일수 변경하는 메소드
			public void itemStateChanged(ItemEvent e) { // 다른 초이스를 선택했을 때 생기는 이벤트 처리
				d.removeAll();
				inputDay(monthCheck(m.getSelectedIndex()));
				d.select(0);
			}
		});

		d.setBounds(250, 80, 90, 40);

		info = new Label("날짜를 선택하고 점수를 입력하세요.");
		info.setBounds(50, 40, 200, 40);

		scoreRecord.add(y);
		scoreRecord.add(m);
		scoreRecord.add(d);
		scoreRecord.add(info);

		g = new Label[20];
		t = new TextField[20];

		for (int i = 0; i < 10; i++) {
			g[i] = new Label((i + 1) + "G");
			g[i].setBounds(47, 142 + (i * 40), 40, 20);
			t[i] = new TextField();
			t[i].setBounds(90, 140 + (i * 40), 140, 25);
			scoreRecord.add(g[i]);
			scoreRecord.add(t[i]);
		}

		for (int i = 10; i < 20; i++) {
			g[i] = new Label((i + 1) + "G");
			g[i].setBounds(250, 142 + ((i - 10) * 40), 40, 20);
			t[i] = new TextField();
			t[i].setBounds(293, 140 + ((i - 10) * 40), 140, 25);
			scoreRecord.add(g[i]);
			scoreRecord.add(t[i]);
		}

		d.addItemListener(new ItemListener() { // 월 선택 시 해당 월 일수 변경하는 메소드
			public void itemStateChanged(ItemEvent e) { // 다른 초이스를 선택했을 때 생기는 이벤트 처리
				for (int i = 0; i < 20; i++) {
					t[i].setText("");
				}
			}
		});
		save = new Button("저장");
		save.setBounds(380, 65, 90, 40);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDate = y.getSelectedItem() + "-" + m.getSelectedItem() + "-" + d.getSelectedItem();
				SpareScoreDAO ssd = new SpareScoreDAO();
				S_ID = new int[scoreCount()];
				ID = new String[scoreCount()];
				G_DATE = new String[scoreCount()];
				G_SCORE = new String[scoreCount()];
				for (int i = 0; i < scoreCount(); i++) {
					ID[i] = id;
					G_DATE[i] = selectDate;
					G_SCORE[i] = t[i].getText();
				}
				for (int i = 0; i < scoreCount; i++) {
					for (int j = ssd.duplication_S_ID(); j < ssd.duplication_S_ID()+1; j++) {
						S_ID[i] = j+1+i;
					}
				}

				String sql = "insert into SCORE(S_ID, ID, G_DATE, G_SCORE) values(?,?,?,?)";

				if (!selectType(selectDate)) { // 날짜를 선택 하지 않았을 때 오류
					dialogSelectDateError();
				} else if (inputGapError()) { // 점수입력칸이 전부 공백일 때 오류
					dialogGapError();
				} else if (inputTypeError()) { // 점수입력칸에 숫자외 문자가 입력 됐을 때
					dialogInputTypeError();
				} else if (inputScoreError()) { // 점수입력칸에 0 ~ 300 사이 숫자가 아닐 때
					dialogInputScoreError();
				} else if (scoreNullCheck()){ // 점수 사이에 공백이 있을 때
					dialogScoreNullError();
				} else {
					if (ssd.duplication(selectDate, id)) { // 선택한 날짜에 이미 데이터가 있을 때
						dialogGapDeleteQ();
						if (gapDelete) {
							try {
								ssd.deleteData(selectDate, id);
								
								ssd.connDB();
								Connection conn = ssd.getCon();
								PreparedStatement pstmt = conn.prepareStatement(sql);

								for (int i = 0; i < scoreCount(); i++) {
									pstmt.setString(1, String.valueOf(S_ID[i]));
									pstmt.setString(2, ID[i]);
									pstmt.setString(3, G_DATE[i]);
									pstmt.setString(4, G_SCORE[i]);

									int r = pstmt.executeUpdate();
								}
								dialogSave();
							} catch (SQLException e2) {
								System.out.println("SQL error" + e2.getMessage());
							}
						}
					} else {
						try {
							ssd.connDB();
							Connection conn = ssd.getCon();
							PreparedStatement pstmt = conn.prepareStatement(sql);

							for (int i = 0; i < scoreCount(); i++) {
								pstmt.setString(1, String.valueOf(S_ID[i]));
								pstmt.setString(2, ID[i]);
								pstmt.setString(3, G_DATE[i]);
								pstmt.setString(4, G_SCORE[i]);

								int r = pstmt.executeUpdate();
								
							}
							dialogSave();
						} catch (SQLException e2) {
							System.out.println("SQL error" + e2.getMessage());
						}
					}
				}
			}
		});

		scoreRecord.add(save);
		scoreRecord.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		scoreRecord.dispose();
	}
	public boolean scoreNullCheck() {
		scoreNull = false;
		for (int i = 0; i < G_SCORE.length; i++) {
			if(G_SCORE[i].equals(null) || G_SCORE[i].equals("")) {
				scoreNull = true;
			}
		}
		
		return scoreNull;
	}
	public int scoreCount() {
		scoreCount = 0;
		cntEr = false;
		for (int i = 0; i < t.length; i++) {
			if (!(t[i].getText().equals(""))) {
				scoreCount++;
			}
		}
		return scoreCount;
	}
	
	public boolean inputScoreError() {
		scoreEr = false;
		String gap = "";
		for (int i = 0; i < t.length; i++) {
			if (!(t[i].getText().equals(gap))) {
				int temp = Integer.parseInt(t[i].getText());
				if (!(temp >= 0 && temp <= 300)) {
					scoreEr = true;
				}
			}
		}
		return scoreEr;
	}

	public boolean inputGapError() {
		scoreCount = 0;
		cntEr = false;
		for (int i = 0; i < t.length; i++) {
			if (!(t[i].getText().equals(""))) {
				scoreCount++;
			}
		}
		if (scoreCount == 0) { // 점수 입력칸이 0개일 경우 오류
			cntEr = true;
		}
		return cntEr;
	}

	public boolean inputTypeError() {
		typeEr = false;
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].getText().length(); j++) {
				char ch = t[i].getText().charAt(j);
				if (!(ch >= 48 && ch <= 57)) {
					typeEr = true;
				}
			}
		}
		return typeEr;
	}

	public int monthCheck(int s) {
		switch (s) {
		case 1:
			return 31;
		case 2:
			return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return 0;
		}

	}

	public void inputDay(int n) {
		d.add("일");
		for (int i = 1; i <= n; i++) {
			if (i < 10) {
				d.addItem("0" + String.valueOf(i));
			} else {
				d.addItem(String.valueOf(i));
			}
		}
	}

	public boolean selectType(String s1) {
		int index;
		boolean type = true;
		for (int i = 0; i < s1.length(); i++) {
			index = s1.charAt(i);
			if (!(index >= 48 && index <= 57 || index == 45)) {
				type = false;
				break;
			} else {
				type = true;
			}
		}

		return type;
	}

	public void dialogGapDeleteQ() {
		Dialog dl = new Dialog(scoreRecord, "점수 중복", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 400, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("선택한 날짜에 이미 점수가 기록 되어있습니다. 수정하시겠습니까?", Label.CENTER);
		Button ok = new Button("예");
		Button no = new Button("아니오");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
				gapDelete = true;
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
				gapDelete = false;
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.add(no);
		dl.setVisible(true);
	}
	public void dialogScoreNullError() {
		Dialog dl = new Dialog(scoreRecord, "입력 오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 250, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("점수 순서대로 입력하세요.", Label.CENTER);
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
	public void dialogInputScoreError() {
		Dialog dl = new Dialog(scoreRecord, "입력 오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 250, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("0 ~ 300 사이 점수를 입력하세요.", Label.CENTER);
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
	public void dialogSave() {
		Dialog dl = new Dialog(scoreRecord, "저장 성공", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 250, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("점수 저장에 성공하였습니다.", Label.CENTER);
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
	public void dialogGapError() {
		Dialog dl = new Dialog(scoreRecord, "입력 오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 250, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("점수 입력칸에 점수를 입력하세요.", Label.CENTER);
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

	public void dialogInputTypeError() {
		Dialog dl = new Dialog(scoreRecord, "입력 오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 380, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("입력칸에 숫자 외 문자가 입력되었습니다. 다시 입력하세요.", Label.CENTER);
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

	public void dialogSelectDateError() {
		Dialog dl = new Dialog(scoreRecord, "날짜 선택 오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 250, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("날짜를 선택하고 저장을 눌러주세요.", Label.CENTER);
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
