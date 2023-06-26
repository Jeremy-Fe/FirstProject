package Spare.Form.SpareMain_01;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class SpareMain_01_01 extends JFrame {
	JTable table;
	JScrollPane scroll; 
	String[] [] data; // 2차원 배열을 생성
	String[] title = {"\\", "내용"};
	SpareMemberDAO DAO;
	SpareMemberDAO_Score dao_Score;
	
	public SpareMain_01_01 (String id) {
		DAO = new SpareMemberDAO();

		ArrayList<SpareMember> listData = DAO.list(id);
		SpareMember getData = (SpareMember) listData.get(0);
		String Mid = getData.getMid();
		String Mpw = getData.getMpw();
		String Mname = getData.getMname();
		String Mclub = getData.getMclub();
		
		dao_Score = new SpareMemberDAO_Score();
		
		ArrayList<SpareMember> listGame = dao_Score.list(id);
		
		String[] Gid = new String[listGame.size()];
		String[] Gdate = new String[listGame.size()];
		String[] Gscore = new String[listGame.size()];
		
		for(int i = 0; i < listGame.size(); i++) { // 게임 수 배열안에다 데이터 집어 넣기
			SpareMember getDataScore = (SpareMember) listGame.get(i);
			Gid[i] = getDataScore.getGid();
			Gdate[i] = getDataScore.getGdate().toString();
			Gscore[i] = getDataScore.getGscore(); 
		}
		
		int sum = 0, cnt = 0;
		double avg;
		
		for (int i = 0; i < Gscore.length; i++) { // 총점, 평균점수 계산
			sum += Integer.parseInt(Gscore[i]);
			cnt++;
		}
		avg = (double) sum / cnt;
		
		int max = 0, count = 0;
		for (int i = 0; i < Gscore.length; i++) { // 최고점수 계산
			if(max < Integer.parseInt(Gscore[i])) {
				max = Integer.parseInt(Gscore[i]);
				count = i;
			}
		}
		
		
		data = new String[6] [2];
		data[0][0] = "이름";
		data[0][1] = Mname;
		
		
		data[1][0] = "상주";
		data[1][1] = Mclub;
		
		data[2][0] = "총 점수";
		data[2][1] = "총 " + sum + "점";
		
		data[3][0] = "총 게임";
		data[3][1] = listGame.size() + "G";
		
		
		data[4][0] = "평균 점수";
		data[4][1] = String.format("%.1f", avg) + "점";
		
		
		data[5][0] = "최고 점수";
		data[5][1] = Gdate[count] + "  |  " + max;
		
		
		
		table = new JTable(data,title); // table = new JTable(데이터, 2차원배열, 컬럼배열)
		scroll = new JScrollPane(table);
		add(scroll);

		
		pack();
		setBounds(700, 400, 500, 200);
		table.setFont((new Font("고딕", Font.PLAIN, 13)));
		setTitle("나의 정보");
		setVisible(true);
		
		DefaultTableCellRenderer tablecell = new DefaultTableCellRenderer();
		tablecell.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		table.getColumn("\\").setPreferredWidth(100); // 셀 너비 조정
		table.getColumn("\\").setCellRenderer(tablecell);; // 셀 가운데 정렬
		table.getColumn("내용").setPreferredWidth(300);
		table.getColumn("내용").setCellRenderer(tablecell);;		
	}
	
	
}
