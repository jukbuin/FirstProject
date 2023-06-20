package mungyang;

import java.awt.Color;

import javax.swing.JFrame;

public class Apply {
	private JFrame f;
	private MemberDAO dao;

	public Apply() {
		dao = new MemberDAO();

		f = new JFrame("입양 신청서");
		f.getContentPane().setBackground(Color.white);
		f.setSize(500, 730);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		f.setVisible(true);

	}
}
