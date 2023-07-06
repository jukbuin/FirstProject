package mungyang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Apply implements ActionListener {
	private JFrame f;
	private MemberDAO dao;
	private HintTextField tfName, tfTel, tfDate;
	private RoundedButton check, bt1, bt2, bt3, bt4;
	private RoundedButton2 btDate, apply;
	private JDialog info1, info2, info3, info4;
	private JLabel msg1, msg2, msg3, msg4;
	private BufferedImage img;
	private JCheckBox box;
	private int clickCheck = 0;

	public Apply() {
		dao = new MemberDAO();

		f = new JFrame("입양 신청서");
		f.getContentPane().setBackground(Color.white);
		f.setSize(500, 730);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//		텍스트필드
		tfName = new HintTextField("성함");
		tfName.setBounds(50, 50, 270, 40);

		tfTel = new HintTextField("전화번호 000-0000-0000");
		tfTel.setBounds(50, 120, 270, 40);

		tfDate = new HintTextField("방문일시");
		tfDate.setBounds(50, 190, 270, 40);
		tfDate.addActionListener(this);
		tfDate.setFocusable(false);

//		버튼
		check = new RoundedButton("회원확인");
		check.setBounds(350, 120, 100, 40);
		check.addActionListener(this);

		btDate = new RoundedButton2("♥");
		btDate.setBounds(350, 190, 40, 40);
		btDate.addActionListener(this);

		apply = new RoundedButton2("신청하기");
		apply.setBounds(185, 595, 130, 50);
		apply.addActionListener(this);

		bt1 = new RoundedButton("확인");
		bt1.setBounds(110, 80, 70, 50);
		bt1.addActionListener(this);

		bt2 = new RoundedButton("확인");
		bt2.setBounds(110, 80, 70, 50);
		bt2.addActionListener(this);

		bt3 = new RoundedButton("확인");
		bt3.setBounds(110, 80, 70, 50);
		bt3.addActionListener(this);

		bt4 = new RoundedButton("확인");
		bt4.setBounds(110, 80, 70, 50);
		bt4.addActionListener(this);

//		다이얼로그 라벨
		msg1 = new JLabel("등록되지 않은 전화번호 입니다.");
		msg1.setBounds(55, 30, 200, 50);

		msg2 = new JLabel("확인 완료되었습니다.");
		msg2.setBounds(85, 30, 150, 50);

		msg3 = new JLabel("체크리스트를 확인해주세요.");
		msg3.setBounds(70, 30, 200, 50);

		msg4 = new JLabel("입양신청이 완료되었습니다.");
		msg4.setBounds(70, 30, 200, 50);

//		다이얼로그
		info1 = new JDialog(f, "unregistered", true);
		info1.setSize(300, 200);
		info1.getContentPane().setBackground(Color.white);
		info1.setLayout(null);
		info1.setLocationRelativeTo(null);
		info1.add(bt1);
		info1.add(msg1);

		info2 = new JDialog(f, "success", true);
		info2.setSize(300, 200);
		info2.getContentPane().setBackground(Color.white);
		info2.setLayout(null);
		info2.setLocationRelativeTo(null);
		info2.add(bt2);
		info2.add(msg2);

		info3 = new JDialog(f, "check", true);
		info3.setSize(300, 200);
		info3.getContentPane().setBackground(Color.white);
		info3.setLayout(null);
		info3.setLocationRelativeTo(null);
		info3.add(bt3);
		info3.add(msg3);

		info4 = new JDialog(f, "apply", true);
		info4.setSize(300, 200);
		info4.getContentPane().setBackground(Color.white);
		info4.setLayout(null);
		info4.setLocationRelativeTo(null);
		info4.add(bt4);
		info4.add(msg4);

//		이미지넣기
		try {
			img = ImageIO.read(new File("C:\\yeeun\\first\\image\\check.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
		}

		myPanel panel = new myPanel();
		panel.setBounds(80, 260, 340, 270); // 가로, 세로

//		체크박스
		box = new JCheckBox("위의 내용을 확인하였습니다.");
		box.setBounds(150, 550, 200, 20);
		box.setBackground(Color.white);

		f.setVisible(true);
		f.add(tfName);
		f.add(tfTel);
		f.add(tfDate);
		f.add(check);
		f.add(btDate);
		f.add(panel);
		f.add(box);
		f.add(apply);

	}

	@SuppressWarnings("serial")
	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btDate) {
			new SwingCalendar();
		}

		if (e.getSource() == bt1) {
			info1.dispose();
		}

		if (e.getSource() == bt2) {
			info2.dispose();
		}

		if (e.getSource() == bt3) {
			info3.dispose();
		}

		if (e.getSource() == bt4) {
			info4.dispose();
			f.dispose();
		}

//		전화번호 등록확인
		if (e.getSource() == check) {

			String per_Tel = tfTel.getText();
			ArrayList<MemberVo> list = dao.list2(per_Tel);

			if (tfTel.getText().equals("전화번호 000-0000-0000") || tfTel.getText().equals("")) {
				tfTel.setText("전화번호를 입력하세요");
				tfTel.setForeground(Color.red);
				tfTel.setHorizontalAlignment(JTextField.CENTER);
			} else if (list.size() == 1) {
				info2.setVisible(true);
				tfTel.setHorizontalAlignment(JTextField.LEFT);
			} else {
				info1.setVisible(true);
				tfTel.setForeground(Color.red);
				tfTel.setHorizontalAlignment(JTextField.CENTER);
			}
		}

//		신청하기
		if (e.getSource() == apply) {
			if (!(box.isSelected())) {
				info3.setVisible(true);
			} else {
				info4.setVisible(true);
				String strName = tfName.getText();
				String strTel = tfTel.getText();
				String strDate = tfDate.getText();

				ArrayList<MemberVo> insert2 = dao.insert2(strName, strTel, strDate);

				if (insert2.size() != 0) {
					MemberVo data = (MemberVo) insert2.get(0);
					String name = data.getName();
					String tel = data.getTel();
					String day = data.getDay();

					System.out.println("DB ==> " + name + " : " + tel + " : " + day);
					System.out.println("입양 신청 성공 -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
				}
			}

		}
	}

////////////////////////////달력 ////////////////////////////////////////////
	@SuppressWarnings("serial")
	public class SwingCalendar extends JFrame implements ActionListener {

		// North

		JPanel topPane = new JPanel();

		JButton prevBtn = new JButton("◀");

		JButton nextBtn = new JButton("▶");

		JLabel yearLbl = new JLabel("년");

		JLabel monthLbl = new JLabel("월");

		JComboBox<Integer> yearCombo = new JComboBox<Integer>();

		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

		JComboBox<Integer> monthCombo = new JComboBox<Integer>();

		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

		// Center

		JPanel centerPane = new JPanel(new BorderLayout());

		JPanel titlePane = new JPanel(new GridLayout(1, 7));

		String titleStr[] = { "일", "월", "화", "수", "목", "금", "토" };

		JPanel datePane = new JPanel(new GridLayout(0, 7));

		Calendar now;

		int year, month, date;

		public SwingCalendar() {

			setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 자원 해제 후 종료

			setResizable(false);

			now = Calendar.getInstance(); // 현재 날짜

			year = now.get(Calendar.YEAR);

			month = now.get(Calendar.MONTH) + 1;

			date = now.get(Calendar.DATE);

			datePane.setBackground(Color.white);

			topPane.add(prevBtn);

			for (int i = year - 100; i <= year + 50; i++) {

				yearModel.addElement(i);

			}

			yearCombo.setModel(yearModel);

			yearCombo.setSelectedItem(year); // 현재 년도 선택

			topPane.add(yearCombo);

			topPane.add(yearLbl);

			for (int i = 1; i <= 12; i++) {

				monthModel.addElement(i);

			}

			monthCombo.setModel(monthModel);

			monthCombo.setSelectedItem(month); // 현재 월 선택

			topPane.add(monthCombo);

			topPane.add(monthLbl);

			topPane.add(nextBtn);

			topPane.setBackground(new Color(255, 220, 241));

			add(topPane, "North");

			// Center

			titlePane.setBackground(Color.white);
			titlePane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

			for (int i = 0; i < titleStr.length; i++) {

				JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER);

				if (i == 0) {

					lbl.setForeground(Color.red);

				} else if (i == 6) {

					lbl.setForeground(Color.blue);

				}

				titlePane.add(lbl);

			}

			centerPane.add(titlePane, "North");

			// 날짜 출력

			dayPrint(year, month);

			centerPane.add(datePane, "Center");

			add(centerPane, "Center");

			setSize(400, 300);

			setLocation(250, 200);

			setVisible(true);

			prevBtn.addActionListener(this);

			nextBtn.addActionListener(this);

			yearCombo.addActionListener(this);

			monthCombo.addActionListener(this);

		}

		// Overring

		public void actionPerformed(ActionEvent ae) {

			Object obj = ae.getSource();

			if (obj instanceof JButton) {

				JButton eventBtn = (JButton) obj;

				int yy = (Integer) yearCombo.getSelectedItem();

				int mm = (Integer) monthCombo.getSelectedItem();

				if (eventBtn.equals(prevBtn)) { // 전달

					if (mm == 1) {

						yy--;
						mm = 12;

					} else {

						mm--;

					}

				} else if (eventBtn.equals(nextBtn)) { // 다음달

					if (mm == 12) {

						yy++;
						mm = 1;

					} else {

						mm++;

					}

				}

				yearCombo.setSelectedItem(yy);

				monthCombo.setSelectedItem(mm);

			} else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시

				createDayStart();

			}

		}

		public void createDayStart() {

			datePane.setVisible(false); // 패널 숨기기

			datePane.removeAll(); // 날짜 출력한 라벨 지우기

			dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());

			datePane.setVisible(true); // 패널 재출력

		}

		public void dayPrint(int y, int m) {

			Calendar cal = Calendar.getInstance();

			cal.set(y, m - 1, 1); // 출력할 첫날의 객체 만든다.

			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일 일요일 : 0

			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 그 달의 마지막 날

			for (int i = 1; i < week; i++) { // 날짜 출력 전까지의 공백 출력

				datePane.add(new JLabel(" "));

			}

			for (int i = 1; i <= lastDate; i++) {

				JLabel lbl = new JLabel(String.valueOf(i), JLabel.CENTER);

				cal.set(y, m - 1, i);

				int outWeek = cal.get(Calendar.DAY_OF_WEEK);

				if (outWeek == 1) {

					lbl.setForeground(Color.red);

				} else if (outWeek == 7) {

					lbl.setForeground(Color.BLUE);

				}

				datePane.add(lbl);
				lbl.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel) me.getSource();
						String str = mouseClick.getText();
						String y = "" + yearCombo.getSelectedItem();
						String m = "" + monthCombo.getSelectedItem();

						// 받은 "요일"이 1자리면 0을 붙여라
						if (str.equals(""))
							;
						else if (str.length() == 1)
							str = "0" + str;

						// 받은 "월"이 1자리면 0을 붙여라
						if (m.length() == 1)
							m = "0" + m;

						if (clickCheck == 0) {
							tfDate.setText(y + "-" + m + "-" + str);
//							tfDate.setEnabled(false);
						}

					}
				});

			}

		}

	}
}
