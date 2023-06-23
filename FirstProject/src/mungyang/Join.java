package mungyang;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Join implements ActionListener {
	private MemberDAO dao;
	private JFrame f;
	private JDialog info1, info2, info3;
	private HintTextField tfId, tfPwd, tfTel, tfName;
	private RoundedButton check1, check2, join, iCheck1, iCheck2, iCheck3;
	private JLabel msg1, msg2, msg3;

	public Join() {
		dao = new MemberDAO();

		f = new JFrame("Join");
		f.getContentPane().setBackground(Color.white);
		f.setSize(500, 500);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);

//		텍스트필드
		tfId = new HintTextField("아이디");
		tfId.setBounds(40, 60, 270, 40);
		tfId.addActionListener(this);
		tfPwd = new HintTextField("비밀번호");
		tfPwd.setBounds(40, 130, 270, 40);
		tfTel = new HintTextField("전화번호 000-0000-0000");
		tfTel.setBounds(40, 200, 270, 40);
		tfName = new HintTextField("닉네임");
		tfName.setBounds(40, 270, 270, 40);

//		버튼
		check1 = new RoundedButton("중복확인");
		check1.setBounds(350, 60, 100, 40);
		check1.addActionListener(this);
		check2 = new RoundedButton("중복확인");
		check2.setBounds(350, 270, 100, 40);
		check2.addActionListener(this);
		join = new RoundedButton("회원가입");
		join.setBounds(150, 360, 200, 50);
		join.addActionListener(this);

//		중복확인 다이얼로그
		info1 = new JDialog(f, "Check", true);
		info1.setSize(300, 200);
		info1.getContentPane().setBackground(Color.white);
		info1.setLayout(null);
		info1.setLocationRelativeTo(null);

		msg1 = new JLabel("중복확인 되었습니다.");
		msg1.setBounds(80, 30, 150, 50);

//		중복확인 확인버튼
		iCheck1 = new RoundedButton("확인");
		iCheck1.setBounds(100, 80, 70, 50);
		iCheck1.addActionListener(this);

		info1.add(msg1);
		info1.add(iCheck1);

//		회원가입실패 다이얼로그
		info3 = new JDialog(f, "Fail", true);
		info3.setSize(300, 200);
		info3.getContentPane().setBackground(Color.white);
		info3.setLayout(null);
		info3.setLocationRelativeTo(null);

		msg3 = new JLabel("입력하지 않은 정보가 있는지 확인하세요.");
		msg3.setBounds(32, 30, 235, 50);

//		회원가입실패 확인버튼
		iCheck3 = new RoundedButton("확인");
		iCheck3.setBounds(100, 80, 70, 50);
		iCheck3.addActionListener(this);

		info3.add(msg3);
		info3.add(iCheck3);

//		회원가입성공 다이얼로그
		info2 = new JDialog(f, "Join", true);
		info2.setSize(300, 200);
		info2.getContentPane().setBackground(Color.white);
		info2.setLayout(null);
		info2.setLocationRelativeTo(null);

		msg2 = new JLabel("회원가입이 완료되었습니다.");
		msg2.setBounds(70, 30, 200, 50);

//		회원가입성공 확인버튼
		iCheck2 = new RoundedButton("확인");
		iCheck2.setBounds(100, 80, 70, 50);
		iCheck2.addActionListener(this);

		info2.add(msg2);
		info2.add(iCheck2);

		f.add(tfId);
		f.add(tfPwd);
		f.add(tfTel);
		f.add(tfName);
		f.add(check1);
		f.add(check2);
		f.add(join);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String op = e.getActionCommand();
//		아이디중복확인

		if (e.getSource() == check1) {

			String strId = tfId.getText();
			ArrayList<MemberVo> list = dao.list(strId);

			if (tfId.getText().equals("아이디") || tfId.getText().equals("")) {
				tfId.setText("아이디를 입력하세요.");
				tfId.setForeground(Color.red);
				tfId.setHorizontalAlignment(JTextField.CENTER);
			} else if (list.size() == 1) {
				tfId.setText("중복된 아이디입니다.");
				tfId.setForeground(Color.red);
				tfId.setHorizontalAlignment(JTextField.CENTER);
			} else {
				tfId.setHorizontalAlignment(JTextField.LEFT);
				info1.setVisible(true);
			}
		}

//		닉네임중복확인
		if (e.getSource() == check2) {
			String strName = tfName.getText();
			ArrayList<MemberVo> list = dao.list3(strName);

			if (tfName.getText().equals("닉네임") || tfName.getText().equals("")) {
				tfName.setText("닉네임을 입력하세요.");
				tfName.setForeground(Color.red);
				tfName.setHorizontalAlignment(JTextField.CENTER);
			} else if (list.size() == 1) {
				tfName.setText("중복된 닉네임입니다.");
				tfName.setForeground(Color.red);
				tfName.setHorizontalAlignment(JTextField.CENTER);
			} else {
				tfName.setHorizontalAlignment(JTextField.LEFT);
				info1.setVisible(true);
			}
		}
//		중복확인 다이얼로그
		if (e.getSource() == iCheck1) {
			info1.dispose();
		}

//		회원가입 다이얼로그
		if (e.getSource() == iCheck2) {
			info2.dispose();
			f.dispose();
		}

		if (e.getSource() == iCheck3) {
			info3.dispose();
		}

//		회원가입
		if (op.equals("회원가입")) {
	

			if (tfId.getText().equals("아이디") || tfId.getText().equals("") || tfPwd.getText().equals("비밀번호")
					|| tfPwd.getText().equals("") || tfTel.getText().equals("전화번호 000-0000-0000")
					|| tfTel.getText().equals("") || tfName.getText().equals("닉네임") || tfName.getText().equals("")) {
				info3.setVisible(true);
			} else {
				info2.setVisible(true);
				String strId = tfId.getText();
				String strPw = tfPwd.getText();
				String strTel = tfTel.getText();
				String strName = tfName.getText();

				ArrayList<MemberVo> insert = dao.insert(strId, strPw, strTel, strName);

				if (insert.size() == 1) {
					MemberVo data = (MemberVo) insert.get(0);
					String id = data.getId();
					String pw = data.getPassword();
					String tel = data.getTel();
					String name = data.getName();

					System.out.println("DB ==> " + id + " : " + pw + " : " + tel + " : " + name);
				}
				f.dispose();
			}
		}
	}
}
