package Spare.Form;

import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Spare.SpareDAO;
import Spare.SpareVo;

public class SpareLoginForm extends WindowAdapter {
	private JPanel p;
	private JFrame f;
	private TextField tfId, tfPwd, tfMsg;
	private Button bLogin, bJoin, bIdSrch;
	private SpareDAO dao;
	
	
	public SpareLoginForm() {
		dao = new SpareDAO();
		f = new JFrame("Spare Login");
		f.setBounds(450, 250, 500, 550);
		f.setLayout(null);
		f.addWindowListener(this);
		
		Label lid = new Label("ID : ");
		lid.setBounds(105, 280, 20, 40);
		
		tfId = new TextField();
		tfId.setBounds(150, 280, 190, 40);
		
		Label lpwd = new Label("PW : ");
		lpwd.setBounds(97, 340, 30, 40);
		
		tfPwd = new TextField();
		tfPwd.setBounds(150, 340, 190, 40);
		tfPwd.setEchoChar('*'); // 비밀번호 * 로 보이게 하는 것
		
		bLogin = new Button("로그인");
		bLogin.setBounds(360, 280, 100, 100);
		bLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String strId = tfId.getText();

				ArrayList<SpareVo> list = dao.list(strId);
				
				if(list.size() == 1) {
					SpareVo data = (SpareVo) list.get(0);
					String id = data.getId();
					String pwd = data.getPassword();
					
					
					if(tfPwd.getText().equals(pwd)) {
						tfMsg.setText("로그인이 되었습니다.!");
						
						new SpareMain(id);
						f.dispose();
					} else {
						System.out.println("다시 입력하세요.");
						tfMsg.setText("Password가 틀렸습니다. 다시 입력하세요.");
					}
				} else {
					tfMsg.setText("ID가 틀렸습니다. 다시 입력하세요.");
				}
			}
		});
		
		tfMsg = new TextField();
		tfMsg.setBounds(40, 400, 420, 35);
		tfMsg.setEditable(false); // 수정이 안됨
		tfMsg.setFocusable(false); // 포커싱이 안됨
		
		bJoin = new Button("회원가입");
		bJoin.setBounds(360, 450, 100, 40);
		bJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SpareJoinForm();
			}
		});
		
		bIdSrch = new Button("아이디 찾기");
		bIdSrch.setBounds(240, 450, 100, 40);
		bIdSrch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SpareIdSrch();
			}
		});
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\Shinwoocheol\\OneDrive\\바탕 화면\\프로젝트\\bowl.png");
		// 이미지를 실제로 갖고 있는 Image 객체 뽑아오기
		Image im = icon.getImage();
		Image im2 = im.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		// 새로 조절된 사이즈의 이미지(im2)를 가지는 ImageIcon 객체를 다시 생성
		ImageIcon icon2 = new ImageIcon(im2);
		JLabel img = new JLabel(icon2);
//		img.setIcon(icon2);
		
		img.setBounds(50, 25, 400, 230);
		f.add(img);
		

//		p = new JPanel();
		
		
		f.getContentPane().setBackground(Color.white);
		
		f.add(lid);
		f.add(tfId);
		f.add(lpwd);
		f.add(tfPwd);
		f.add(bLogin);
		f.add(tfMsg);
		f.add(bIdSrch);
		f.add(bJoin);
		
		
		f.setVisible(true);
	}
	
	
	public void windowClosing(WindowEvent e) {
		f.dispose();
	}
}
