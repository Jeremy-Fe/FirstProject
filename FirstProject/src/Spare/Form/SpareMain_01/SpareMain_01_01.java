package Spare.Form.SpareMain_01;

import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import Spare.SpareDAO;
import Spare.SpareVo;

public class SpareMain_01_01 extends JFrame {
	JTable table;
	JScrollPane scroll; 
	String[] [] data; // 2차원 배열을 생성
	String[] title = {"\\", "내용"};
	SpareMemberDAO DAO;
	
	public SpareMain_01_01 (String id) {
		DAO = new SpareMemberDAO();
		String sqlName = "SELECT NAME FROM SPAREMEMBER WHERE ID = ?";
		String sqlClub = "insert into SPAREMEMBER(ID, PW, NAME, CLUB) values(?,?,?,?)";
		
//		try {
//		SpareDAO sd = new SpareDAO();
//		sd.connDB();
//		Connection conn = sd.getCon();
//		PreparedStatement pstmt = conn.prepareStatement(sqlName);
//		System.out.println(rsName.getString("ID"));
//		pstmt.setString(1, id);
//		ResultSet rsName = pstmt.executeQuery(sqlName);
		ArrayList<SpareMember> list = DAO.list(id);
		SpareMember getData = (SpareMember) list.get(0);
		String Mid = getData.getMid();
		String Mpw = getData.getMpw();
		String Mname = getData.getMname();
		String Mclub = getData.getMclub();
		
		data = new String[5] [2];
		data[0][0] = "이름";
		data[0][1] = Mname;
		
		
		data[1][0] = "상주";
		data[1][1] = Mclub;
		
		
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
//		setLayout(null);
		setBounds(700, 400, 500, 200);
//		setSize(400, 150);
		setVisible(true);
//		} catch (SQLException e) {
//			System.out.println("SQLExeption");
//			System.out.println(id);
//		}
		
		
		
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
