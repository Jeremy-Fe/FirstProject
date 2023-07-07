package Spare.Form.SpareMain_03;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import Spare.Form.SpareMain_01.SpareMain_01_02_DateDAO;
import Spare.Form.SpareMain_01.SpareMain_01_03;
import Spare.Form.SpareMain_01.SpareMember;
import Spare.Form.SpareMain_01.SpareMemberDAO;
import Spare.Form.SpareMain_01.SpareMemberDAO_Score;

public class SpareMain_03 extends JFrame {
	private Frame frame;
	private Choice y, m, club, sortBy;
	private Button search;
	private Label instruction;
	String date = "", clubStr = "", sort = "";

	JTable table;
	JScrollPane scroll;
	String[][] data; // 2차원 배열을 생성
	String[] title = { "순위", "이름", "게임 수", "총점", "최고", "평균" };
	SpareMain_03DAO select;

	public SpareMain_03() {
		frame = new Frame("Spare Date Lookup");
		frame.setBounds(700, 300, 500, 500);
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		instruction = new Label("날짜와 볼링장, 정렬기준을 선택하여주세요.");
		instruction.setBounds(115, 120, 400, 40);

		y = new Choice(); // 년도
		y.add("년도");
		y.addItem("2023");
		y.addItem("2022");
		y.addItem("2021");
		y.setBounds(80, 170, 200, 50);

		m = new Choice(); // 월
		m.add("월");
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				m.addItem("0" + String.valueOf(i));
			} else {
				m.addItem(String.valueOf(i));
			}
		}
		m.setBounds(80, 210, 200, 50);

		club = new Choice(); // 상주 볼링장
		club.add("볼링장");
		club.addItem("위너스볼링장");
		club.addItem("크로바볼링장");
		club.addItem("하이미디어볼링장");
		club.setBounds(80, 250, 200, 50);

		sortBy = new Choice(); // 정렬 기준
		sortBy.add("정렬기준");
		sortBy.addItem("게임 수");
		sortBy.addItem("총점");
		sortBy.addItem("최고");
		sortBy.addItem("평균");
		sortBy.setBounds(80, 290, 200, 50);

		search = new Button("검색");
		search.setBounds(320, 215, 100, 100);

		frame.add(instruction);
		frame.add(y);
		frame.add(m);
		frame.add(club);
		frame.add(sortBy);
		frame.add(search);

		frame.setVisible(true);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SpareMain_03();
				date = y.getSelectedItem() + "-" + m.getSelectedItem();
				clubStr = club.getSelectedItem();
				sort = sortBy.getSelectedItem();
				if (sort.equals("게임 수")) {
					sort = "COUNT(*)";
					title[2] += "↓";
				} else if (sort.equals("총점")) {
					sort = "SUM(G_SCORE)";
					title[3] += "↓";
				} else if (sort.equals("최고")) {
					sort = "MAX(G_SCORE)";
					title[4] += "↓";
				} else if (sort.equals("평균")) {
					sort = "AVG(G_SCORE)";
					title[5] += "↓";
				}
				if (!(selectType(date)) || clubStr.equals("상주볼링장") || sort.equals("정렬기준")) {
					dialogSelectItemError();
				} else {

					select = new SpareMain_03DAO();
					ArrayList<SpareMember> ranking = select.list(clubStr, date, sort);

					String[] GName = new String[ranking.size()];
					String[] GCount = new String[ranking.size()];
					String[] GSum = new String[ranking.size()];
					String[] GMax = new String[ranking.size()];
					String[] GAvg = new String[ranking.size()];

					for (int i = 0; i < ranking.size(); i++) { // 게임 수 배열안에다 데이터 집어 넣기
						SpareMember getDataScore = (SpareMember) ranking.get(i);
						GName[i] = getDataScore.getRankName();
						GCount[i] = getDataScore.getRankCount();
						GSum[i] = getDataScore.getRankSum();
						GMax[i] = getDataScore.getRankMax();
						GAvg[i] = getDataScore.getRankAvg();
					}

					if (ranking.size() == 0) {
						data = new String[1][6];
						data[0][0] = "-";
						data[0][1] = "-";
						data[0][2] = "-";
						data[0][3] = "-";
						data[0][4] = "-";
						data[0][5] = "-";
					} else {
						data = new String[ranking.size()][6];
						for (int i = 0; i < ranking.size(); i++) {
							data[i][0] = String.valueOf(i + 1);
							data[i][1] = GName[i];
							data[i][2] = GCount[i];
							data[i][3] = GSum[i];
							data[i][4] = GMax[i];
							data[i][5] = GAvg[i];
						}
					}

					table = new JTable(data, title); // table = new JTable(데이터, 2차원배열, 컬럼배열)
					scroll = new JScrollPane(table);
					add(scroll);
					
					pack();
					setBounds(700, 400, 500, 200);
					table.setFont((new Font("고딕", Font.PLAIN, 13)));
					setTitle(date + " " + clubStr + " 순위");
					setVisible(true);

					
					DefaultTableCellRenderer tablecell = new DefaultTableCellRenderer();
					tablecell.setHorizontalAlignment(SwingConstants.CENTER);

					String G_Cnt = title[2]; // 정렬기준 선택하면 문자열이 달라져서
					String G_Sum = title[3];
					String G_Max = title[4];
					String G_Avg = title[5];
					table.getColumn("순위").setPreferredWidth(30); // 셀 너비 조정
					table.getColumn("순위").setCellRenderer(tablecell);// 셀 가운데 정렬
					table.getColumn("이름").setCellRenderer(tablecell);
					table.getColumn(G_Cnt).setCellRenderer(tablecell);
					table.getColumn(G_Sum).setCellRenderer(tablecell);
					table.getColumn(G_Max).setCellRenderer(tablecell);
					table.getColumn(G_Avg).setCellRenderer(tablecell);

				}
			}
		});
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
	public void dialogSelectItemError() {
		Dialog dl = new Dialog(frame, "오류", true); // 다이얼로그 오류창
		dl.setBounds(700, 400, 280, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("날짜, 볼링장, 정렬기준을 선택해주세요.", Label.CENTER);
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
