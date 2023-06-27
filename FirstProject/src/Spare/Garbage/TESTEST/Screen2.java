package Spare.Garbage.TESTEST;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen2 extends JPanel {
	
	public Screen2() {
		this.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("화면2");
		
		this.add(label, "Center");
		
		this.setBackground(Color.GREEN);
	}
}
