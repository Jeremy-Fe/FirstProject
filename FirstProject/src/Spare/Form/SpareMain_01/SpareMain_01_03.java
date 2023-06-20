package Spare.Form.SpareMain_01;

import java.awt.Button;
import java.awt.Choice;
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

public class SpareMain_01_03 extends JFrame {
	private Frame frame;
	private Choice y, m;
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

	public SpareMain_01_03(String id) {
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
		y.addItem("2023");
		y.addItem("2022");
		y.addItem("2021");
		y.setSize(190, 60);
		y.setBounds(80, 200, 200, 50);

		m = new Choice(); // 월
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				m.addItem("0" + String.valueOf(i));
			} else {
				m.addItem(String.valueOf(i));
			}
		}
		m.setSize(190, 60);
		m.setBounds(80, 240, 200, 50);


		search = new Button("검색");
		search.setBounds(300, 200, 100, 100);

		frame.add(instruction);
		frame.add(y);
		frame.add(m);
		frame.add(search);

		frame.setVisible(true);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SpareMain_01_03(id);
				date = y.getSelectedItem() + "-" + m.getSelectedItem();

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
				avg = (double) sum / cnt;

				int max = 0;
				for (int i = 0; i < Gscore.length; i++) { // 최고점수 계산
					if (max < Integer.parseInt(Gscore[i])) {
						max = Integer.parseInt(Gscore[i]);
					}
				}

				data = new String[5][2];
				data[0][0] = "날짜";
				data[0][1] = date + "월 종합";

				data[1][0] = "게임 수";
				data[1][1] = selectDay.size() + "G";

				data[2][0] = "총점";
				data[2][1] = "총 " + sum + "점";

				data[3][0] = "최고";
				data[3][1] = max + "점";

				data[4][0] = "평균";
				data[4][1] = String.format("%.1f", avg) + "점";


				

				table = new JTable(data, title); // table = new JTable(데이터, 2차원배열, 컬럼배열)
				scroll = new JScrollPane(table);
				add(scroll);

				pack();
				setBounds(700, 400, 500, 200);
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

		});
	}
}
