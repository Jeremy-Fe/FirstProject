package Spare.Form;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Spare.SpareDAO;

public class SpareJoinForm extends WindowAdapter{
	private Frame join;
	private TextField tfId, tfPwd, tfPwdRe, tfName;
	private Button bLogin;
	private Choice tfBowlingClub;
	
	String id = "", pw = "", pwRe = "", name = "", club = "";
	
	public SpareJoinForm() {
		join = new Frame("Spare Join");
		join.setBounds(470, 270, 500, 400);
		join.setLayout(null);
		join.addWindowListener(this);
		
		Label lid = new Label("새로운 ID : ");
		lid.setBounds(60, 60, 100, 40);
		
		tfId = new TextField();
		tfId.setBounds(160, 60, 190, 40);
		
		
		Label lpwdCheck1 = new Label("*비밀번호는 영문,");
		Label lpwdCheck2 = new Label(" 숫자, 특수문자로 구성");
//		Label lpwdCheck2 = new Label("*비밀번호는 영문, 숫자, 특수문자로 구성됩니다.");
		lpwdCheck1.setBounds(360, 130, 100, 20);
		lpwdCheck2.setBounds(360, 140, 120, 40);
		
		Label lpwd = new Label("새로운 PW : ");
		lpwd.setBounds(60, 120, 100, 40);
		
		tfPwd = new TextField();
		tfPwd.setBounds(160, 120, 190, 40);
		tfPwd.setEchoChar('*'); // 비밀번호 * 로 보이게 하는 것
		
		
		Label lpwdRe = new Label("PW 재확인 : ");
		lpwdRe.setBounds(60, 180, 100, 40);
		
		tfPwdRe = new TextField();
		tfPwdRe.setBounds(160, 180, 190, 40);
		tfPwdRe.setEchoChar('*'); // 비밀번호 * 로 보이게 하는 것
		
		
		Label lname = new Label("본인의 이름 : ");
		lname.setBounds(60, 240, 100, 40);
		
		tfName = new TextField();
		tfName.setBounds(160, 240, 190, 40);
		
		Label lClub = new Label("상주볼링장 : ");
		lClub.setBounds(60, 300, 100, 40);
		tfBowlingClub = new Choice();
		tfBowlingClub.addItem("위너스볼링장");
		tfBowlingClub.addItem("크로바볼링장");
		tfBowlingClub.addItem("하이미디어볼링장");
		tfBowlingClub.setSize(190, 60);
		tfBowlingClub.setBounds(160, 307, 190, 40);
		
		
		bLogin = new Button("회원가입");
		bLogin.setBounds(370, 240, 100, 100);
		bLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = tfId.getText();
				if(KoreanType(tfPwd.getText(), pw) && KoreanType(tfPwdRe.getText(), pwRe)) {
					pw = tfPwd.getText();
					pwRe = tfPwdRe.getText();
				} else {
					pw = "Kerror";
					pwRe = "Kerror";
				}
				name = tfName.getText();
				club = tfBowlingClub.getSelectedItem();
				
				String sql = "insert into SPAREMEMBER(ID, PW, NAME, CLUB) values(?,?,?,?)";
				
				
				if(!pw.equals(pwRe)) { // 비밀번호 재입력 틀림
					DialogPwReError();
				} else if (pw == "Kerror") { // 비밀번호에 한글이 입력됐을 경우
					DialogKorean();
				} else {
					try {
						SpareDAO sd = new SpareDAO();
						sd.connDB();
						Connection conn = sd.getCon();
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						pstmt.setString(1, id);
						pstmt.setString(2, pw);
						pstmt.setString(3, name);
						pstmt.setString(4, club);
						
						int r = pstmt.executeUpdate();
						System.out.println("변경된 row " + r);
						DialogId();
					} catch (SQLException e1){
						System.out.println("SQL error" + e1.getMessage());
						if(e1.getMessage().contains("ORA-00001")) { // 아이디 중복 됐을때
							DialogIdError();
						} else if (e1.getMessage().contains("ORA-01400")) { // 공백이 존재할 때
							System.out.println("SQL error" + e1.getMessage());
							DialogError();
						} else { 
							DialogError();
						}
					}
				}
			}
		});
		
		
		join.add(lid);
		join.add(tfId);
		join.add(lpwdCheck1);
		join.add(lpwdCheck2);
		join.add(lpwd);
		join.add(tfPwd);
		join.add(lpwdRe);
		join.add(tfPwdRe);		
		join.add(lname);
		join.add(tfName);
		join.add(lClub);		
		join.add(tfBowlingClub);
		join.add(bLogin);
		
		join.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		join.dispose();
	}
	public void DialogPwReError() {
		Dialog dl = new Dialog(join, "비밀번호 오류", true); // 다이얼로그 오류창
		dl.setSize(300, 100);
		dl.setLocation(300, 50);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("비밀번호가 서로 맞지 않습니다. 다시 입력하세요.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	public void DialogIdError() {
		Dialog dl = new Dialog(join, "아이디 오류", true); // 다이얼로그 오류창
		dl.setSize(300, 100);
		dl.setLocation(300, 50);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("아이디가 중복됩니다. 다시 입력하세요.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	public void DialogError() {
		Dialog dl = new Dialog(join, "입력 오류", true); // 다이얼로그 오류창
		dl.setSize(300, 100);
		dl.setLocation(300, 50);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("입력이 잘못 되었습니다. 다시 입력하세요.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	public void DialogId() {
		Dialog dl = new Dialog(join, "회원가입 완료!", true); // 다이얼로그 오류창
		dl.setSize(300, 100);
		dl.setLocation(300, 50);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("회원가입이 완료되었습니다.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
				join.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	
	public void DialogKorean() {
		Dialog dl = new Dialog(join, "비밀번호 입력 오류", true); // 다이얼로그 오류창
		dl.setSize(400, 100);
		dl.setLocation(300, 50);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("비밀번호에 한글 또는 공백이 포함되어 있습니다. 다시 입력하세요.", Label.CENTER);
		Button ok = new Button("확인");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.dispose();
			}
		});
		dl.add(msg);
		dl.add(ok);
		dl.setVisible(true);
	}
	public boolean KoreanType(String s1, String s2) {
		int index;
		boolean korean = true;
		for (int i = 0; i < s1.length(); i++) {
			index = s1.charAt(i);
			if(!(index >= 48 && index <= 126)) {
				korean = false;
				break;
			} else {
				korean = true;
			}
		}
		
		return korean;
	}
}
