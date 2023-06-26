package Spare.Form.SpareMain_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SpareScoreDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	String user = "c##himedia";
	String password = "himedia";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private boolean dup;
	private int cnt;


	public boolean duplication(String date, String id) { 
		try {
			connDB();

			String query = "SELECT * FROM(select ID, to_char(G_DATE, 'yyyy-mm-dd') as G_DATE from SCORE) ";
			if (date != null) {
				query += "WHERE G_DATE like '" + date + "' AND id = '" + id + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last(); 
			
			
			System.out.println("rs.getRow() : " + rs.getRow());

			if (!(rs.getRow() == 0)) {
				dup = true;
			} else {
				dup = false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			e.printStackTrace();
		}
		return dup;
	}

	public void deleteData(String date, String id) {
		try {
			connDB();

			String query = "DELETE FROM (select ID, to_char(G_DATE, 'yyyy-mm-dd') as G_DATE from SCORE)";
			if (date != null) {
				query += "WHERE G_DATE like '" + date + "%' AND ID = '" + id + "'";
			}
			System.out.println("SQL : " + query);

			int res = stmt.executeUpdate(query);
			if (res == 0) {
				System.out.println("삭제 실패");
			} else {
				System.out.println("삭제 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int duplication_S_ID() {
		try {
			connDB();

			String query = "SELECT S_ID FROM SCORE ORDER BY S_ID DESC";
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.next();
			System.out.println("rs.getInt(1) : " + rs.getInt(1));

			cnt = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			e.printStackTrace();
		}
		return cnt;
	}
	public void connDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
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
