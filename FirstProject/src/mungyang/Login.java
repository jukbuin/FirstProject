package mungyang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener {
	private JFrame f;
	private RoundedButton bLogin;
	private RoundedButton2 bJoin;
	private MemberDAO dao;
	private BufferedImage img;
	private HintTextField tfId, tfPwd;
	private JTextField tfMsg;
	private String id, pwd, tel, name;

	public String loginName() {
		return name;
	}

	public Login() {
		dao = new MemberDAO();

		f = new JFrame("Login");
		f.getContentPane().setBackground(Color.white);
		f.setSize(500, 730);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);

//		이미지넣기
		try {
			img = ImageIO.read(new File("C:\\yeeun\\first\\image\\login.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
		}

		myPanel panel = new myPanel();
		panel.setBounds(85, 50, 330, 300); // 가로, 세로

//		아이디, 비밀번호필드

		tfId = new HintTextField("아이디");
		tfId.setBounds(100, 380, 300, 40);

		tfPwd = new HintTextField("비밀번호");
		tfPwd.setBounds(100, 440, 300, 40);
//		tfPwd.setEchoChar('*');

//		오류메시지창
		tfMsg = new JTextField();
		tfMsg.setBounds(100, 500, 300, 40);
		tfMsg.setBorder(BorderFactory.createEmptyBorder()); // 투명하게
		tfMsg.setForeground(Color.red);
		tfMsg.setHorizontalAlignment(JTextField.CENTER);
		tfMsg.setFocusable(false);

//		버튼
		bLogin = new RoundedButton("회원가입");
		bLogin.setBounds(80, 550, 130, 50);
		bLogin.addActionListener(this);

		bJoin = new RoundedButton2("로그인");
		bJoin.setBounds(290, 550, 130, 50);
		bJoin.addActionListener(this);

		f.add(panel);
		f.add(tfId);
		f.add(tfPwd);
		f.add(tfMsg);
		f.add(bLogin);
		f.add(bJoin);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

//	이미지넣기
	@SuppressWarnings("serial")
	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String op = e.getActionCommand();
		if (op.equals("로그인")) {

			System.out.println(tfId.getText());
			System.out.println(tfPwd.getText());

			String strId = tfId.getText();

			ArrayList<MemberVo> list = dao.list(strId);

			if (list.size() == 1) {
				MemberVo data = (MemberVo) list.get(0);
				id = data.getId();
				pwd = data.getPassword();
				tel = data.getTel();
				name = data.getName();
				System.out.println("DB ==> " + id + " : " + pwd + " : " + tel + " : " + name);

				if (tfPwd.getText().equals(pwd)) {
//				f2.setVisible(true);
					f.dispose();
					new MainHopm();
				} else {
					tfMsg.setText("아이디나 비밀번호를 확인해주세요.");
				}
			} else {
				tfMsg.setText("아이디나 비밀번호를 확인해주세요.");
			}
		}

		if (op.equals("회원가입")) {
			new Join();
		}

	}

}
