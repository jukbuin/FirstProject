package mungyang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainHopm implements ActionListener {
	private JFrame f;
	private JPanel p1, pAnimals, pInfo, pLatter, pFind;
	private RoundedButton bt1, bt2, bt3, bt4, bt5;
	private BufferedImage img;

	public MainHopm() {
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

		p1.add(bt1);
		p1.add(bt2);
		p1.add(bt3);
		p1.add(bt4);
		p1.add(bt5);

//		보호중인 동물들
		pAnimals = new JPanel();
		pAnimals.setBounds(300, 0, 700, 800);
		pAnimals.setBackground(Color.white);
		pAnimals.setLayout(null);
		pAnimals.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pAnimals.setVisible(false);

//		이미지넣기
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
		if (op.equals("보호중인 동물들")) {
			pInfo.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(false);
			pAnimals.setVisible(true);
		}

		if (op.equals("입양 신청안내")) {
			pAnimals.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(false);
			pInfo.setVisible(true);
		}
		
		if(op.equals("입양 신청서")) {
			new Apply();
		}

		if (op.equals("입양후기")) {
			pAnimals.setVisible(false);
			pInfo.setVisible(false);
			pFind.setVisible(false);
			pLatter.setVisible(true);
		}

		if (op.equals("반려동물을 찾습니다!")) {
			pAnimals.setVisible(false);
			pInfo.setVisible(false);
			pLatter.setVisible(false);
			pFind.setVisible(true);
		}

	}

}
