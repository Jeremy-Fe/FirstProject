package Spare.Garbage.TESTEST;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen1 extends JPanel {
	
	public Screen1() {
		this.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("화면1");
		
		this.add(label, "Center");
		
		this.setBackground(Color.yellow);
	}
}
