package Spare.Form;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Spare.SpareDAO;
import Spare.SpareVo;

public class SpareLoginForm extends WindowAdapter {
	private Frame f;
	private TextField tfId, tfPwd, tfMsg;
	private Button bLogin, bJoin;
	private SpareDAO dao;
	
	
	public SpareLoginForm() {
		dao = new SpareDAO();
		f = new Frame("Spare Login");
		f.setBounds(450, 250, 500, 300);
		f.setLayout(null);
		f.addWindowListener(this);
		
		Label lid = new Label("ID : ");
		lid.setBounds(115, 60, 20, 40);
		
		tfId = new TextField();
		tfId.setBounds(160, 60, 190, 40);
		
		Label lpwd = new Label("PW : ");
		lpwd.setBounds(107, 120, 30, 40);
		
		tfPwd = new TextField();
		tfPwd.setBounds(160, 120, 190, 40);
		tfPwd.setEchoChar('*'); // 비밀번호 * 로 보이게 하는 것
		
		bLogin = new Button("로그인");
		bLogin.setBounds(370, 60, 100, 100);
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
		tfMsg.setBounds(50, 180, 420, 35);
		tfMsg.setEditable(false); // 수정이 안됨
		tfMsg.setFocusable(false); // 포커싱이 안됨
		
		bJoin = new Button("회원가입");
		bJoin.setBounds(370, 230, 100, 40);
		bJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SpareJoinForm();
			}
		});
		
		f.add(lid);
		f.add(tfId);
		f.add(lpwd);
		f.add(tfPwd);
		f.add(bLogin);
		f.add(tfMsg);
		f.add(bJoin);
		
		f.setVisible(true);
	}
	
//	public void actionPerformed(ActionEvent e) {}
	public void windowClosing(WindowEvent e) {
		f.dispose();
//		if(e.getComponent() == fMain) {
//			fMain.dispose();
//		} else if(e.getComponent() == f) {
//			f.dispose();
//			fMain.dispose(); // 로그인 창을 끄면 둘 다 꺼지게
//		}
	}
}
