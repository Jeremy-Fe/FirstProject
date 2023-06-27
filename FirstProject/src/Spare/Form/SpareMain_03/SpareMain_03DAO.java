package Spare.Form.SpareMain_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Spare.Form.SpareMain_01.SpareMember;

public class SpareMain_03DAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	String user = "c##himedia";
	String password = "himedia";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<SpareMember> list(String clubStr, String date, String sort) {
		ArrayList<SpareMember> list = new ArrayList<SpareMember>();

		try {
			connDB();

			String query = "SELECT NAME, COUNT(*) count, sum(G_SCORE) sum, MAX(G_SCORE) max, ROUND(AVG(G_SCORE),1) avg FROM ( select CLUB, NAME, G_SCORE, to_char(G_DATE, 'yyyy-mm-dd') as G_DATE from SCORE s , SPAREMEMBER a WHERE s.id = a.id)";
			query += "WHERE G_DATE like '" + date + "%' AND CLUB = '" + clubStr + "' GROUP BY NAME ORDER BY " + sort + " desc, ";
					if(!(sort.equals("SUM(G_SCORE)"))) {
						query += "SUM(G_SCORE) DESC";
					} else {
						query += "AVG(G_SCORE) DESC";
					}
			

			rs = stmt.executeQuery(query);
			rs.first();
			
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				rs.previous(); // 커서를 이전으로
				while (rs.next()) { // 다음 커서에 값이 있다면 true
					String getname = rs.getString("NAME");
					String getcount = rs.getString("COUNT");
					String getsum = rs.getString("SUM");
					String getmax = rs.getString("MAX");
					String getavg = rs.getString("AVG");

					SpareMember data = new SpareMember(getname, getcount, getsum, getmax, getavg);

					list.add(data);
				}
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
