package mungyang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainHopm implements ActionListener {
	private MemberDAO dao;
	private ArrayList<MemberVo> list, search, flist;
	private JFrame f;
	private JPanel p1, pAnimals, pInfo, pLatter, pFind;
	private HintTextField aTf;
	private RoundedButton bt1, bt2, bt3, bt4, bt5;
	private RoundedButton2 aSearBt, logout;
	private JButton aBt, aBt2;
	private BufferedImage img;
	private JLabel aImgLabel, fImgLabel1, fImgLabel2, fImgLabel3, fImgLabel4;
	private ImageIcon pIcon, fBtimg, fBtimg2, bBtimg, bBtimg2, fIcon1, fIcon2, fIcon3, fIcon4;
	private String pImg, fImg, fImg2, fImg3, fImg4;
	private int cnt;

	public MainHopm() {
		dao = new MemberDAO();

		cnt = 1;

		f = new JFrame("메인창");
		f.setSize(1000, 800);
		f.getContentPane().setBackground(Color.white);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//		메뉴창
		p1 = new JPanel();
		p1.setBounds(0, 0, 300, 800);
		p1.setBackground(Color.white);
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

//		메뉴창 버튼들
		bt1 = new RoundedButton("보호중인 동물들");
		bt1.setBounds(40, 155, 220, 50);
		bt1.addActionListener(this);

		bt2 = new RoundedButton("입양 신청안내");
		bt2.setBounds(40, 265, 220, 50);
		bt2.addActionListener(this);

		bt3 = new RoundedButton("입양 신청서");
		bt3.setBounds(40, 375, 220, 50);
		bt3.addActionListener(this);

		bt4 = new RoundedButton("입양후기");
		bt4.setBounds(40, 485, 220, 50);
		bt4.addActionListener(this);

		bt5 = new RoundedButton("반려동물을 찾습니다!");
		bt5.setBounds(40, 595, 220, 50);
		bt5.addActionListener(this);

		logout = new RoundedButton2("로그아웃");
		logout.setBounds(20, 10, 100, 40);
		logout.addActionListener(this);

		p1.add(bt1);
		p1.add(bt2);
		p1.add(bt3);
		p1.add(bt4);
		p1.add(bt5);
		p1.add(logout);

//		보호중인 동물들
		pAnimals = new JPanel();
		pAnimals.setBounds(300, 0, 700, 800);
		pAnimals.setBackground(Color.white);
		pAnimals.setLayout(null);
		pAnimals.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		aImgLabel = new JLabel();
		aTf = new HintTextField("강아지 / 고양이");
		aTf.setBounds(380, 20, 200, 35);
		aSearBt = new RoundedButton2("검색");
		aSearBt.setBounds(600, 20, 50, 35);
		aSearBt.addActionListener(this);

//		앞으로가기버튼
		fBtimg = new ImageIcon("C:/yeeun/first/image/bt.png");
		fBtimg2 = new ImageIcon("C:/yeeun/first/image/bt2.png");
		aBt = new JButton(fBtimg);
		aBt.setRolloverIcon(fBtimg2);
		aBt.setBorderPainted(false);
		aBt.setBounds(610, 400, 42, 49);
		aBt.addActionListener(this);

//		뒤로가기버튼
		bBtimg = new ImageIcon("C:/yeeun/first/image/bt3.png");
		bBtimg2 = new ImageIcon("C:/yeeun/first/image/bt4.png");

		aBt2 = new JButton(bBtimg);
		aBt2.setRolloverIcon(bBtimg2);
		aBt2.setBorderPainted(false);
		aBt2.setBounds(10, 400, 42, 49);
		aBt2.addActionListener(this);
		aBt2.setVisible(false);

//		이미지불러오기
		list = dao.animal();
		MemberVo data = (MemberVo) list.get(0);
		pImg = data.getName();
		pIcon = new ImageIcon(pImg);
		aImgLabel.setIcon(pIcon);
		aImgLabel.setBounds(110, 100, 480, 650);
		aImgLabel.setHorizontalAlignment(JLabel.CENTER);

		pAnimals.add(aTf);
		pAnimals.add(aSearBt);
		pAnimals.add(aBt);
		pAnimals.add(aBt2);
		pAnimals.add(aImgLabel);
		pAnimals.setVisible(false);

//		입양신청안내 이미지넣기
		try {
			img = ImageIO.read(new File("C:\\yeeun\\first\\image\\info_image.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
		}

		myPanel panel = new myPanel();
		panel.setBounds(1, 1, 699, 766); // 가로, 세로

//		입양신청안내
		pInfo = new JPanel();
		pInfo.setBounds(300, 0, 700, 800);
		pInfo.setBackground(Color.white);
		pInfo.setLayout(null);
		pInfo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pInfo.add(panel);
		pInfo.setVisible(true);

//		입양후기
		pLatter = new JPanel();
		pLatter.setBounds(300, 0, 700, 800);
		pLatter.setBackground(Color.white);
		pLatter.setLayout(null);
		pLatter.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pLatter.setVisible(false);

//		반려동물을 찾습니다!
		pFind = new JPanel();
		pFind.setBounds(300, 0, 700, 800);
		pFind.setBackground(Color.white);
		pFind.setLayout(null);
		pFind.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		fImgLabel1 = new JLabel();
		fImgLabel1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.print("A");
			}
		});
		;

		fImgLabel2 = new JLabel();
		fImgLabel2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.print("B");
			}
		});
		;
		fImgLabel3 = new JLabel();
		fImgLabel3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.print("C");
			}
		});
		;
		fImgLabel4 = new JLabel();
		fImgLabel4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.print("D");
			}
		});
		;

//		첫번째 이미지
		flist = dao.find();
		MemberVo fdata = (MemberVo) flist.get(0);
		fImg = fdata.getId(); // info는 password에
		fIcon1 = new ImageIcon(fImg);
		fImgLabel1.setIcon(fIcon1);
		fImgLabel1.setBounds(50, 25, 270, 340);
		fImgLabel1.setHorizontalAlignment(JLabel.CENTER);

//		두번째 이미지
		fdata = flist.get(1);
		fImg2 = fdata.getId();
		fIcon2 = new ImageIcon(fImg2);
		fImgLabel2.setIcon(fIcon2);
		fImgLabel2.setBounds(370, 25, 270, 340);
		fImgLabel2.setHorizontalAlignment(JLabel.CENTER);

//		세번째 이미지
		fdata = flist.get(2);
		fImg3 = fdata.getId();
		fIcon3 = new ImageIcon(fImg3);
		fImgLabel3.setIcon(fIcon3);
		fImgLabel3.setBounds(50, 395, 270, 340);
		fImgLabel3.setHorizontalAlignment(JLabel.CENTER);

//		네번째 이미지
		fdata = flist.get(3);
		fImg4 = fdata.getId();
		fIcon4 = new ImageIcon(fImg4);
		fImgLabel4.setIcon(fIcon4);
		fImgLabel4.setBounds(370, 395, 270, 340);
		fImgLabel4.setHorizontalAlignment(JLabel.CENTER);

		pFind.add(fImgLabel1);
		pFind.add(fImgLabel2);
		pFind.add(fImgLabel3);
		pFind.add(fImgLabel4);
		pFind.setVisible(false);

		f.add(p1);
		f.add(pAnimals);
		f.add(pInfo);
		f.add(pLatter);
		f.add(pFind);
		f.setVisible(true);

	}

	@SuppressWarnings("serial")
	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String op = e.getActionCommand();
//		로그아웃
		if (op.equals("로그아웃")) {
			f.dispose();
			new Login();
		}

//		보호중인 동물들
		if (op.equals("보호중인 동물들")) {
			pInfo.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(false);
			pAnimals.setVisible(true);
		}
		if (e.getSource() == aBt) {
			String animal = aTf.getText();
			if (animal.equals("강아지") || animal.equals("고양이")) {
				aBt2.setVisible(true);
				MemberVo data = search.get(cnt);
				pImg = data.getName();
				pIcon = new ImageIcon(pImg);
				aImgLabel.setIcon(pIcon);
				cnt++;

				if (cnt == search.size()) {
					aBt.setVisible(false);
				}
			} else {

				if (cnt == list.size() - 1) {
					aBt.setVisible(false);
				}
				aBt2.setVisible(true);
				MemberVo data = list.get(cnt);
				pImg = data.getName();
				pIcon = new ImageIcon(pImg);
				aImgLabel.setIcon(pIcon);
				cnt++;
			}
		}

		if (e.getSource() == aBt2) {
			--cnt;
			String animal = aTf.getText();
			if (animal.equals("강아지") || animal.equals("고양이")) {
				aBt.setVisible(true);
				MemberVo data = search.get(--cnt);
				pImg = data.getName();
				pIcon = new ImageIcon(pImg);
				aImgLabel.setIcon(pIcon);
				cnt++;
				if (cnt == 1) {
					aBt2.setVisible(false);
				}
			} else {
				if (cnt == 1) {
					aBt2.setVisible(false);
				}
				aBt.setVisible(true);
				MemberVo data = list.get(--cnt);
				pImg = data.getName();
				pIcon = new ImageIcon(pImg);
				aImgLabel.setIcon(pIcon);
				cnt++;
			}
		}
//		검색기능
		if (e.getSource() == aSearBt) {
			String animal = aTf.getText();

			if (aTf.getText().equals("강아지") || aTf.getText().equals("고양이")) {
				search = dao.animal(animal);
				MemberVo data = search.get(0);
				pImg = data.getName();
				pIcon = new ImageIcon(pImg);
				aImgLabel.setIcon(pIcon);

			} else {
				aTf.setText("강아지 또는 고양이로 입력해주세요.");
				aTf.setForeground(Color.red);
			}

		}

//		입양신청안내
		if (op.equals("입양 신청안내")) {
			pAnimals.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(false);
			pInfo.setVisible(true);
		}

//		입양신청서
		if (op.equals("입양 신청서")) {
			new Apply();
		}

//		입양후기
		if (op.equals("입양후기")) {
			pAnimals.setVisible(false);
			pInfo.setVisible(false);
			pFind.setVisible(false);
			pLatter.setVisible(true);
		}

//		반려동물을 찾습니다!
		if (op.equals("반려동물을 찾습니다!")) {
			pAnimals.setVisible(false);
			pInfo.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(true);
		}

	}

}
