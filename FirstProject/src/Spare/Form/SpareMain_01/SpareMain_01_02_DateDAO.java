package Spare.Form.SpareMain_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class SpareMain_01_02_DateDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	String user = "c##himedia";
	String password = "himedia";
	private Connection con;
	private Statement stmt;
	private ResultSet rs; 
	
	
	public ArrayList<SpareMember> list(String id, String date) {
		ArrayList<SpareMember> list = new ArrayList<SpareMember>();
		
		try {
			connDB(); 
			
			String query = "SELECT * FROM(select S_ID, ID, G_SCORE, to_char(G_DATE, 'yyyy-mm-dd') as G_DATE from SCORE) ";
			if(date != null) {
				query += "WHERE G_DATE like '" + date + "%' AND id = '" + id + "'"; 
			}
			
			rs = stmt.executeQuery(query);
			rs.first(); // 커서를 맨 처음으로
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				rs.previous(); // 커서를 이전으로
				while(rs.next()) { // 커서를 다음으로
					String getSid = rs.getString("S_ID");
					String getGid = rs.getString("ID");
					Date getGdate = rs.getDate("G_DATE");
					String getGscore = rs.getString("G_SCORE");
						
					SpareMember data = new SpareMember(getSid, getGid, getGdate, getGscore);
						
					list.add(data);
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void connDB() {
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return con;
	}
	public Statement getStmt() {
		return stmt;
	}
	public ResultSet getRs() {
		return rs;
	}

}
