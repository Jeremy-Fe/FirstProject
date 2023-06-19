package Area;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Main_01_01 extends JFrame{
	JTable table;
	JScrollPane scroll; 
	String[] [] data; // 2차원 배열을 생성
	String[] title = {"\\", "내용"};
	public Main_01_01 () {
		data = new String[5] [2];
		data[0][0] = "이름";
		data[0][1] = "신우철";
		
		data[1][0] = "상주";
		data[1][1] = "위너스볼링장";
		
		data[2][0] = "총 게임";
		data[2][1] = "2023년도 총 207게임";
		
		data[3][0] = "평균 점수";
		data[3][1] = "2023년도 총 게임 평균 204.7점";
		
		data[4][0] = "최고 점수";
		data[4][1] = "2023.02.01 | 300점";
		
		table = new JTable(data,title); // table = new JTable(데이터, 2차원배열, 컬럼배열)
		scroll = new JScrollPane(table);
		add(scroll);

		
		pack();
		setLayout(null);
		setBounds(400, 150, 1000, 1000);
//		setSize(400, 150);
		setVisible(true);
		
		DefaultTableCellRenderer tablecell = new DefaultTableCellRenderer();
		tablecell.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumn("\\").setPreferredWidth(100);
		table.getColumn("\\").setCellRenderer(tablecell);;		
		table.getColumn("내용").setPreferredWidth(300);
		table.getColumn("내용").setCellRenderer(tablecell);;		
	}
	public void windowClosing(WindowEvent e) {
		
	}
}
