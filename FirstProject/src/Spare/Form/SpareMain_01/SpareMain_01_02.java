package Spare.Form.SpareMain_01;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class SpareMain_01_02 extends JFrame {
	protected static final double NaN = 0;
	private Frame frame;
	private Choice y, m, d;
	private Button search;
	private Label instruction;
	String date = "";

	JTable table;
	JScrollPane scroll;
	String[][] data; // 2차원 배열을 생성
	String[] title = { "\\", "내용" };
	SpareMemberDAO DAO;
	SpareMemberDAO_Score dao_Score;
	SpareMain_01_02_DateDAO select;

	public SpareMain_01_02(String id) {
		frame = new Frame("Spare Date Lookup");
		frame.setBounds(700, 300, 500, 500);
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		instruction = new Label("조회하고 싶은 날짜를 선택하세요.");
		instruction.setBounds(115, 150, 400, 40);

		y = new Choice(); // 년도
		y.add("년도");
		y.addItem("2023");
		y.addItem("2022");
		y.addItem("2021");
		y.setSize(190, 60);
		y.setBounds(80, 200, 200, 50);

		m = new Choice(); // 월
		m.add("월");
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				m.addItem("0" + String.valueOf(i));
			} else {
				m.addItem(String.valueOf(i));
			}
		}
		m.setSize(190, 60);
		m.setBounds(80, 240, 200, 50);

		d = new Choice(); // 일
		d.add("일");
		m.addItemListener(new ItemListener() { // 월 선택 시 해당 월 일수 변경하는 메소드
			public void itemStateChanged(ItemEvent e) {
				d.removeAll();
				inputDay(monthCheck(m.getSelectedIndex()));
				d.select(0);

			}
		});
		d.setBounds(80, 280, 200, 50);

		search = new Button("검색");
		search.setBounds(300, 200, 100, 100);

		frame.add(instruction);
		frame.add(y);
		frame.add(m);
		frame.add(d);
		frame.add(search);


		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = y.getSelectedItem() + "-" + m.getSelectedItem() + "-" + d.getSelectedItem();
				if (!(selectType(date))) {
					dialogSelectDateError();
				} else {
					frame.dispose();
					new SpareMain_01_02(id);

					select = new SpareMain_01_02_DateDAO();
					ArrayList<SpareMember> selectDay = select.list(id, date);

					String[] Gid = new String[selectDay.size()];
					String[] Gdate = new String[selectDay.size()];
					String[] Gscore = new String[selectDay.size()];

					for (int i = 0; i < selectDay.size(); i++) { // 게임 수 배열안에다 데이터 집어 넣기
						SpareMember getDataScore = (SpareMember) selectDay.get(i);
						Gid[i] = getDataScore.getGid();
						Gdate[i] = getDataScore.getGdate().toString();
						Gscore[i] = getDataScore.getGscore();
					}

					int sum = 0, cnt = 0;
					double avg;

					for (int i = 0; i < Gscore.length; i++) {
						sum += Integer.parseInt(Gscore[i]);
						cnt++;
					}
					if(sum == 0 || cnt == 0) {
						avg = 0;
					} else {
						avg = (double) sum / cnt;						
					}

					int max = 0;
					for (int i = 0; i < Gscore.length; i++) { // 최고점수 계산
						if (max < Integer.parseInt(Gscore[i])) {
							max = Integer.parseInt(Gscore[i]);
						}
					}
					
					
					data = new String[26][2];
					data[0][0] = "날짜";
					data[0][1] = date;

					data[1][0] = "게임 수";
					data[1][1] = selectDay.size() + "G";

					data[2][0] = "총점";
					data[2][1] = "총 " + sum + "점";

					data[3][0] = "최고";
					data[3][1] = max + "점";

					data[4][0] = "평균";
					data[4][1] = String.format("%.1f", avg) + "점";

					data[5][0] = "-";
					data[5][1] = "-";

					for (int i = 0; i < 20; i++) {
						try {
							data[i + 6][1] = Gscore[i] + "점";
							data[i + 6][0] = (i + 1) + "G";
						} catch (ArrayIndexOutOfBoundsException e1) {
							break;
						}
					}

					table = new JTable(data, title); // table = new JTable(데이터, 2차원배열, 컬럼배열)
					scroll = new JScrollPane(table);
					add(scroll);

					pack();
					setBounds(700, 400, 500, 300);
					table.setFont((new Font("고딕", Font.PLAIN, 13)));
					setVisible(true);

					DefaultTableCellRenderer tablecell = new DefaultTableCellRenderer();
					tablecell.setHorizontalAlignment(SwingConstants.CENTER);

					table.getColumn("\\").setPreferredWidth(100); // 셀 너비 조정
					table.getColumn("\\").setCellRenderer(tablecell);
					; // 셀 가운데 정렬
					table.getColumn("내용").setPreferredWidth(300);
					table.getColumn("내용").setCellRenderer(tablecell);
					
				}
			}
		});
		frame.setVisible(true);
	}
	public void dialogSelectDateError() {
		Dialog dl = new Dialog(frame, "오류", true); // 다이얼로그 오류창
		dl.setBounds(800, 400, 170, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("날짜를 선택해주세요.", Label.CENTER);
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
			return 31;
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
}
