package Spare.Form.SpareMain_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SpareMemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	String user = "c##himedia";
	String password = "himedia";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs; 
	
	public ArrayList<SpareMember> list(String id) {
		ArrayList<SpareMember> list = new ArrayList<SpareMember>();
		
		try {
			connDB();
			
			String query = "SELECT * FROM SPAREMEMBER";
			if(id != null) {
				query += " where id=TRIM('" + id + "')";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while(rs.next()) {
					String getId = rs.getString("ID");
					String getPw = rs.getString("PW");
					String getName = rs.getString("NAME");
					String getClub = rs.getString("CLUB");
					
					SpareMember data = new SpareMember(getId, getPw, getName, getClub);
					list.add(data);
				}
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void connDB() {
		try {
		Class.forName(driver);
		System.out.println("jdbc driver loading success.");
		con = DriverManager.getConnection(url, user, password);
		System.out.println("oracle connection success.");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		System.out.println("statement create success.");
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
