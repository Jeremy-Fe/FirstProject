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

public class SpareIdSrch extends WindowAdapter { // 아이디 비밀번호 찾기 대책 강구 바람
	private Frame findFrame;
	private TextField tfname;
	private Label linfo, lname, lclub;
	private Button find;
	private Choice tfBowlingClub;
	private SpareDAO DAO;

	String name = "", club = "";

	// 여기서 
	public SpareIdSrch() {
		findFrame = new Frame("Spare Find ID");
		findFrame.setBounds(470, 270, 500, 400);
		findFrame.setLayout(null);
		findFrame.addWindowListener(this);

		linfo = new Label("본인의 이름과 상주볼링장을 선택해주세요.");
		linfo.setBounds(60, 60, 300, 40);
		
		lname = new Label("본인의 이름 : ");
		lname.setBounds(60, 150, 100, 40);

		tfname = new TextField();
		tfname.setBounds(180, 150, 190, 40);

		
		lclub = new Label("본인의 상주 볼링장:");
		lclub.setBounds(60, 220, 117, 50);
		
		tfBowlingClub = new Choice();
		tfBowlingClub.addItem("위너스볼링장");
		tfBowlingClub.addItem("크로바볼링장");
		tfBowlingClub.addItem("하이미디어볼링장");
		tfBowlingClub.setBounds(180, 230, 190, 40);

		find = new Button("아이디 찾기");
		find.setBounds(390, 163, 90, 90);
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = tfname.getText();
				club = tfBowlingClub.getSelectedItem();
				
				DAO = new SpareDAO();
				String result = DAO.findId(name, club); 
				if(result.equals("에러")) {
					DialogFindError();
				} else {
					Dialog dl = new Dialog(findFrame, "아이디 찾기", true); // 다이얼로그 오류창
					dl.setBounds(560, 400, 300, 100);
					dl.setLayout(new FlowLayout());
					Label msg = new Label("당신의 아이디는 ((  " + result + "  )) 입니다.", Label.CENTER);
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
			}
		}); 

		findFrame.add(linfo);
		findFrame.add(lname);
		findFrame.add(tfname);
		findFrame.add(lclub);
		findFrame.add(tfBowlingClub);
		findFrame.add(find);

		findFrame.setVisible(true);
	}
	public void windowClosing(WindowEvent e) {
		findFrame.dispose();
	}
	public void DialogFindError() {
		Dialog dl = new Dialog(findFrame, "입력 오류", true); // 다이얼로그 오류창
		dl.setBounds(560, 400, 300, 100);
		dl.setLayout(new FlowLayout());
		Label msg = new Label("     검색결과가 없습니다. 다시 입력해주세요.", Label.CENTER);
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
}
