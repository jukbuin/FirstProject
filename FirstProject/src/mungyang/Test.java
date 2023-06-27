package mungyang;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import mungyang.MemberDAO;
import mungyang.MemberVo;

class TextAreaEx extends JFrame {
	TextAreaEx() {
		this.setTitle("텍스트영역 만들기 예제");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(new MyCenterPanel(), BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setSize(300, 300);
		this.setVisible(true);
	}

	class MyCenterPanel extends JPanel {
		JTextField tf;
		JButton btn;
		JTextArea ta;
		private MemberDAO dao;
		

		MyCenterPanel() {
			dao = new MemberDAO();
			
			
//			count변수를 두고 get(cnt) 버튼 클릭할때마다 count증가
//			댓글달기는 지금 로그인되어있는사람 닉네임가져와서 달아주기
			ArrayList<MemberVo> list = dao.animal();
			MemberVo data = (MemberVo) list.get(0);
			MemberVo data2 = (MemberVo) list.get(1);
			String a = data.getName();
			String b = data2.getName();
			String c = "";
			
			tf = new JTextField(20);
			btn = new JButton("추가");
			System.out.println(c);

			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ta.append("\n" + tf.getText() + " " + c);
				}
			});
			ta = new JTextArea("hello", 7, 20);
			add(tf);
			add(btn);
			add(new JScrollPane(ta));
			ta.setText(a + "\n" + b);
			ta.setFocusable(false);
		}
	}
}

public class Test {
	public static void main(String[] args) {
		new TextAreaEx();
	}
}