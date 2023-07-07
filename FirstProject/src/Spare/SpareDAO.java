package Spare;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpareDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	String user = "c##himedia";
	String password = "himedia";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs; 
	
	public ArrayList<SpareVo> list(String id) {
		ArrayList<SpareVo> list = new ArrayList<SpareVo>();
		
		try {
			connDB();
			
			String query = "SELECT * FROM SPAREMEMBER";
			if(id != null) {
				query += " where id=TRIM('" + id + "')";
			}
			
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				rs.previous();
				while(rs.next()) {
					String sh = rs.getString("ID");
					String password = rs.getString("PW");
					
					SpareVo data = new SpareVo(sh, password);
					list.add(data);
				}
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String findId(String name, String club) {
		String id = "";
		try {
			connDB();
			
			String query = "SELECT ID FROM SPAREMEMBER WHERE NAME = '" + name + "' AND CLUB = '" + club + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() == 0) {
				id = "에러";
			} else {
				id = rs.getString("ID");				
			}
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
		return id;
	}
//	public void intee() {
//		String pwd = "asdf1234";
//		int num = 4, str = 4;
//		
//		for (int i = 0; i < pwd.length(); i++) {
//			char chr = pwd.charAt(i);
//			if (숫자) {
//				num++;
//			} else if (문자) {
//				str++;
//			} else (그외 문자){
//				에러
//			}
//		}
//	}
	
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
