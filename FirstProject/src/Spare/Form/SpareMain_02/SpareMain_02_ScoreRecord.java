package Spare.Form.SpareMain_02;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

public class SpareMain_02_ScoreRecord extends WindowAdapter {
	private Frame scoreRecord;
	private Label[] g;
	private TextField[] t;
	private Label info;
	private Button save;
	private Choice y, m, d;
	private String selectDate;

	int scoreCount = 0;
	boolean cntEr, typeEr, scoreEr;
	
	String id = "", pw = "", pwRe = "", name = "", club = "";

	public SpareMain_02_ScoreRecord() {
		scoreRecord = new Frame("Spare Join");
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
			g[i].setBounds(47, 142 + (i*40), 40, 20);
			t[i] = new TextField();
			t[i].setBounds(90, 140 + (i*40), 140, 25);
			scoreRecord.add(g[i]);
			scoreRecord.add(t[i]);
		}
		
		for (int i = 10; i < 20; i++) {
			g[i] = new Label((i + 1) + "G");
			g[i].setBounds(250, 142 + ((i-10)*40), 40, 20);
			t[i] = new TextField();
			t[i].setBounds(293, 140 + ((i-10)*40), 140, 25);
			scoreRecord.add(g[i]);
			scoreRecord.add(t[i]);
		}
		
		
		save = new Button("저장");
		save.setBounds(380, 65, 90, 40);
		save.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDate = y.getSelectedItem() + "-" + m.getSelectedItem() + "-" + d.getSelectedItem();
				
				
				if(!selectType(selectDate)) { // 날짜를 선택 하지 않았을 때 오류
					dialogSelectDateError();
				} else if (inputGapError()){ // 점수기록란이 전부 공백일 때 오류
					dialogGapError();
				} else if (inputTypeError()){ // 3. 점수기록란에 숫자외 문자가 입력 됐을 때
					dialogInputTypeError();
				} else if (inputScoreError()){
					System.out.println("0 ~ 300 사이에 점수를 입력하세요");
				} else {
					System.out.println("성공");
				}
				
			}
		});
		
		
		
		scoreRecord.add(save);
		
		scoreRecord.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		scoreRecord.dispose();
	}
	public boolean inputScoreError() {
		scoreEr = false;
		String gap = "";
		for (int i = 0; i < t.length; i++) {
			if(!(t[i].getText().equals(gap))){
				int temp = Integer.parseInt(t[i].getText());
				if(!(temp >= 0 && temp <= 300)) {
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
			if(!(t[i].getText().equals(""))) {
				scoreCount++;
			}
		}
		if(scoreCount == 0) { // 점수 입력칸이 0개일 경우 오류
			cntEr = true;
		}
		return cntEr;
	}
	public boolean inputTypeError() {
		typeEr = false;
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].getText().length(); j++) {
				char ch = t[i].getText().charAt(j);
				if(!(ch >= 48 && ch <= 57)){
					typeEr = true;
				} 
			}
		}
		return typeEr;
	}
	public int monthCheck(int s) {
		switch(s) {
			case 1: return 31;
			case 2: return 28;
			case 3: return 31;
			case 4: return 30;
			case 5: return 31;
			case 6: return 30;
			case 7: return 31;
			case 8: return 31;
			case 9: return 30;
			case 10: return 31;
			case 11: return 30;
			case 12: return 31;
			default : return 0;
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
	public void dialogInputScoreError() { // 다이얼로그 추가만 하자 우철아
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

	
//	public boolean scoreCheck(String[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			if(!(arr[i].length() <= 3 && arr[i].length() >= 1)) {
//				return true;
//			}
//		}
//	}

	public static void main(String[] args) {
		new SpareMain_02_ScoreRecord();
	}
	
}
