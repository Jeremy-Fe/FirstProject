package Spare.Form.SpareMain_01.test;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Spare.Form.SpareMain_01.SpareMain_01_02_DateDAO;
import Spare.Form.SpareMain_01.SpareMemberDAO;
import Spare.Form.SpareMain_01.SpareMemberDAO_Score;

public class Test111 extends JFrame {
	private Frame frame;
	private Choice y, m, d;
	private Button search;
	private JLabel instruction;
	String date = "";

	DefaultTableModel dtm;
	JTable table;
	JPanel jp1, jp2;
	JScrollPane scroll;
	String[][] data; // 2차원 배열을 생성
	String[] title = { "\\", "내용" };
	SpareMemberDAO DAO;
	SpareMemberDAO_Score dao_Score;
	SpareMain_01_02_DateDAO select;

	public Test111() {
		setTitle("sd");
		data = new String[6][2];
		data[0][0] = "날짜";
		data[0][1] = "날짜입니다";

		data[1][0] = "게임 수";
		data[1][1] = "456G";

		data[2][0] = "총점";
		data[2][1] = "총 456점";

		data[3][0] = "최고";
		data[3][1] = "456점";

		data[4][0] = "평균";
		data[4][1] = "456.7점";

		data[5][0] = "-";
		data[5][1] = "-";

		
		dtm = new DefaultTableModel(data, title);
		table = new JTable(dtm); // table = new JTable(데이터, 2차원배열, 컬럼배열)
		scroll = new JScrollPane(table);
//		instruction = new JLabel("반겨우");
//		jp1 = new JPanel();
//		jp1.add(instruction);
		


		JButton btn1 = new JButton("안녕1");
		JButton btn2 = new JButton("안녕2");
		JButton btn3 = new JButton("안녕3");
		JButton btn4 = new JButton("안녕4");
		
		jp2 = new JPanel();
		jp2.add(btn1);
		jp2.add(btn2);
		jp2.add(btn3);
		jp2.add(btn4);
		jp2.setBackground(Color.darkGray);

		
//		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));

		add("Center", scroll);
//		add("North", jp1);
		add("South", jp2);
		
		setBounds(400,300,300,300);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
//		DefaultTableCellRenderer tablecell = new DefaultTableCellRenderer();
//		tablecell.setHorizontalAlignment(SwingConstants.CENTER);
//
//		table.getColumn("\\").setPreferredWidth(100); // 셀 너비 조정
//		table.getColumn("\\").setCellRenderer(tablecell);
//		; // 셀 가운데 정렬
//		table.getColumn("내용").setPreferredWidth(300);
//		table.getColumn("내용").setCellRenderer(tablecell);
	}
	public static void main(String[] args) {
		new Test111();
	}
}
