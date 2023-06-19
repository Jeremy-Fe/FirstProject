package Spare.Form.SpareMain_01;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SpareMain_01_SelectDate extends WindowAdapter {
	private Frame frame;
	private Choice y, m, d;
	private Button search;
	private Label instruction;
	String date = "";
	
	public SpareMain_01_SelectDate() {
		frame = new Frame("Spare Date Lookup");
		frame.setBounds(700, 300, 500, 500);
		frame.setLayout(null);
		frame.addWindowListener(this);

		instruction = new Label("조회하고 싶은 날짜를 선택하세요.");
		instruction.setBounds(115, 150, 400, 40);
		
		y = new Choice(); // 년도
		y.addItem("2021");
		y.addItem("2022");
		y.addItem("2023");
		y.setSize(190, 60);
		y.setBounds(80, 200, 200, 50);

		m = new Choice(); // 월
		for (int i = 1; i <= 12; i++) {
			m.addItem(String.valueOf(i));
		}
		m.setSize(190, 60);
		m.setBounds(80, 240, 200, 50);

		d = new Choice(); // 일
		for (int i = 1; i <= 31; i++) {
			d.addItem(String.valueOf(i));
		}
		d.setSize(190, 60);
		d.setBounds(80, 280, 200, 50);

		
		search = new Button("검색");
		search.setBounds(300, 200, 100, 100);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = y.getSelectedItem() + "-";
				date += m.getSelectedItem() + "-";
				date += d.getSelectedItem();
				choice();
			}
		});

		frame.add(instruction);
		frame.add(y);
		frame.add(m);
		frame.add(d);
		frame.add(search);

		frame.setVisible(true);
	}
	public String choice() {
		return date;
	}
	public static void main(String[] args) {
		new SpareMain_01_SelectDate();
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}
}
