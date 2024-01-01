package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.Dao_pdg;
import com.javalec.function.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class JoinPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblID;
	private JTextField tfID;
	private JLabel lblPassword;
	private JPasswordField pfPassword;
	private JLabel lblNewLabel_6;
	private JTextField tfEmail;
	private JLabel lblEmail;
	private JLabel lblNewLabel_4;
	private JLabel nameImage;
	private JLabel lblName;
	private JLabel mailImage;
	private JLabel lblNewLabel_6_1_1;
	private JLabel lblTelno;
	private JTextField tfName;
	private JTextField tfTelno;
	private JButton btnNewButton;
	private JLabel lblNewLabel_6_1_2;
	private JButton btnNewButton_1;
	private JPasswordField pfPassword_Same;
	private JLabel lblPassword_2;
	private JButton btnNewButton_2;

	/*
	 * Description : 회원 가입 페이 Date : 20203.12.27 Author : Dong Geun Forrest Park
	 * Update 2023.12.29  by PDG: 
	 * o 		1. GUI design 
	 * o		2. 입력 받은 것으로 db 에 등록하기 
	 * o		3. id 길이가 너무 길면 쿼리가 안들어감.
	 * o		4. 아이디 중복확인.
	 * 			5. 아이디는 숫자와 영어로만 등록할수있습니다.
	 * o		6. 비밀번호 확인 기능 필요 
	 * o		7. 동의가 체크 되어야만 등록 되게 만들것 
	 * o		8. 이미지 업로드 기능 추가할것.
	 * 
	 * Update 2023.12.31 by PDG
	 * 
	 * 			1. 홈버튼 이미지 예전 것임 새로운것으로 바꿀것
	 * 			2. 팝업 위치 변경
	 */

	static JoinPage joindialog = new JoinPage();
	private JLabel lblNewLabel_6_1;
	private JRadioButton rbNormalMember;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbInformationOfferAgree;
	private JLabel lblNewLabel;
	private JButton btnCamera;
	private JButton btnImageUpload;
	private JButton btnNewButton_3;
	private JLabel lblPassWordCompare;

	
	
	//keyboard 관련 변수
	private JButton btnIdKeyboard;
	private JButton btnPWKeyboard;
	private JButton btnPW2Keyboard;
	private JButton btnEmailKeyboard;
	private JButton btnNameKeyboard;
	private JButton btnTellNokeyBoard;
	private JButton btnKeyboard;
	private static int keyboardStartHorizontalLine=50;
	private static int keyboardStartVerticalLine=5;
	private static boolean keyAlphabetLowerOrUpper =false; // false equal lower case, it is initial state
	private JButton btnKeyboard2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// joindialog = new JoinPage();
			joindialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			joindialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JoinPage() {
		setTitle("회원가입");
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y, ShareVar.window_size_x,  ShareVar.window_size_y);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getNameImage());
		contentPanel.add(getLblID());
		contentPanel.add(getLblPassword());
		contentPanel.add(getLblNewLabel_6());
		contentPanel.add(getLblEmail());
		contentPanel.add(getLblNewLabel_4());
		contentPanel.add(getTextField_1_1());
		contentPanel.add(getLblName());
		contentPanel.add(getPfPassword());
		contentPanel.add(getMailImage());
		contentPanel.add(getLblNewLabel_6_1_1());
		contentPanel.add(getTfID());
		contentPanel.add(getLblTelno());
		contentPanel.add(getTextField_1_2());
		contentPanel.add(getTextField_1_3());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getLblNewLabel_6_1_2());
		contentPanel.add(getBtnNewButton_1());
		contentPanel.add(getLblPassword_2());
		contentPanel.add(getPfPassword_Same());
		contentPanel.add(getBtnNewButton_2());
		contentPanel.add(getLblNewLabel_6_1());
		contentPanel.add(getRbNormalMember());
		contentPanel.add(getRbInformationOfferAgree());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getBtnCamera());
		contentPanel.add(getBtnImageUpload());
		contentPanel.add(getBtnNewButton_3());
		contentPanel.add(getLblPassWordCompare());
		contentPanel.add(getBtnIdKeyboard());
		contentPanel.add(getBtnPWKeyboard());
		contentPanel.add(getBtnPW2Keyboard());
		contentPanel.add(getBtnEmailKeyboard());
		contentPanel.add(getBtnNameKeyboard());
		contentPanel.add(getBtnTellNokeyBoard());
	}

	private JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("아이디 : 필수 정보입니다.");
			lblID.setForeground(Color.GRAY);
			lblID.setBounds(283, 85, 185, 16);
		}
		return lblID;
	}

	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {

					// id tf 에 타이핑 될때 라벨 사라짐.

					lblID.setVisible(false);

				}
			});
			tfID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			tfID.setBounds(267, 70, 259, 50);
			tfID.setColumns(10);
		}
		return tfID;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("비밀번호");
			lblPassword.setForeground(Color.GRAY);
			lblPassword.setBounds(283, 143, 61, 16);
		}
		return lblPassword;
	}

	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					lblPassword.setVisible(false);
				}
			});
			pfPassword.setBounds(267, 126, 259, 50);
		}
		return pfPassword;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("");
			lblNewLabel_6.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/pw_new2.png")));
			lblNewLabel_6.setBounds(230, 126, 35, 45);
		}
		return lblNewLabel_6;
	}

	private JTextField getTextField_1_1() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {

					lblEmail.setVisible(false);
				}
			});
			tfEmail.setColumns(10);
			tfEmail.setBounds(267, 254, 259, 50);
		}
		return tfEmail;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("[선택] 비밀번호 분실시 확인용 이메일");
			lblEmail.setForeground(Color.GRAY);
			lblEmail.setBounds(279, 272, 223, 16);
		}
		return lblEmail;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/email_new1.png")));
			lblNewLabel_4.setBounds(230, 259, 35, 35);
		}
		return lblNewLabel_4;
	}

	private JLabel getNameImage() {
		if (nameImage == null) {
			nameImage = new JLabel("");
			nameImage.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/Name_new.png")));
			nameImage.setBounds(230, 321, 35, 35);
		}
		return nameImage;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("이름 (가상키보드는 영어만 지원)");
			lblName.setForeground(Color.GRAY);
			lblName.setBounds(283, 331, 230, 16);
		}
		return lblName;
	}

	private JLabel getMailImage() {
		if (mailImage == null) {
			mailImage = new JLabel("");
			mailImage.setIcon(new ImageIcon("/com/javalec/image/email.png"));
			mailImage.setBounds(277, 263, 35, 35);
		}
		return mailImage;
	}

	private JLabel getLblNewLabel_6_1_1() {
		if (lblNewLabel_6_1_1 == null) {
			lblNewLabel_6_1_1 = new JLabel("");
			lblNewLabel_6_1_1.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/login_new.png")));
			lblNewLabel_6_1_1.setBounds(228, 77, 35, 35);
		}
		return lblNewLabel_6_1_1;
	}

	private JLabel getLblTelno() {
		if (lblTelno == null) {
			lblTelno = new JLabel("전화번호");
			lblTelno.setForeground(Color.GRAY);
			lblTelno.setBounds(283, 394, 61, 16);
		}
		return lblTelno;
	}

	private JTextField getTextField_1_2() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {

					lblName.setVisible(false);
				}
			});
			tfName.setColumns(10);
			tfName.setBounds(267, 316, 259, 50);
		}
		return tfName;
	}

	private JTextField getTextField_1_3() {
		if (tfTelno == null) {
			tfTelno = new JTextField();
			tfTelno.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					lblTelno.setVisible(false);
				}
			});
			tfTelno.setColumns(10);
			tfTelno.setBounds(267, 379, 259, 50);
		}
		return tfTelno;
	}

	////// 회원등록 버튼!
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("가입하기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					registrationAction();
				}
			});
			btnNewButton.setBounds(267, 480, 117, 35);
		}
		return btnNewButton;
	}

	private JLabel getLblNewLabel_6_1_2() {
		if (lblNewLabel_6_1_2 == null) {
			lblNewLabel_6_1_2 = new JLabel("");
			lblNewLabel_6_1_2.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/tell_new.png")));
			lblNewLabel_6_1_2.setBounds(230, 386, 35, 35);
		}
		return lblNewLabel_6_1_2;
	}

	private JLabel getLblNewLabel_6_1() {
		if (lblNewLabel_6_1 == null) {
			lblNewLabel_6_1 = new JLabel("");
			lblNewLabel_6_1.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/pw_new2.png")));
			lblNewLabel_6_1.setBounds(230, 188, 35, 45);
		}
		return lblNewLabel_6_1;
	}

	private JRadioButton getRbNormalMember() {
		if (rbNormalMember == null) {
			rbNormalMember = new JRadioButton("일반 멤버쉽");
			buttonGroup.add(rbNormalMember);
			rbNormalMember.setSelected(true);
			rbNormalMember.setBounds(277, 441, 99, 23);
		}
		return rbNormalMember;
	}

	private JRadioButton getRbInformationOfferAgree() {
		if (rbInformationOfferAgree == null) {
			rbInformationOfferAgree = new JRadioButton("정보 제공에 동의합니다.");
			rbInformationOfferAgree.setBounds(385, 441, 167, 23);
		}
		return rbInformationOfferAgree;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/no_image.png")));
			lblNewLabel.setBounds(69, 80, 115, 125);
		}
		return lblNewLabel;
	}

	private JButton getBtnCamera() {
		if (btnCamera == null) {
			btnCamera = new JButton("");
			btnCamera.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/camer_new.png")));
			btnCamera.setBounds(52, 217, 40, 40);
		}
		return btnCamera;
	}

	private JButton getBtnImageUpload() {
		if (btnImageUpload == null) {
			btnImageUpload = new JButton("이미지 업로드");
			btnImageUpload.setBounds(100, 225, 117, 29);
		}
		return btnImageUpload;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("다시쓰기");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					tfID.setText("");
					pfPassword.setText("");
					pfPassword_Same.setText("");
					tfEmail.setText("");
					tfName.setText("");
					tfTelno.setText("");
					tfID.setEditable(true);
					pfPassword.setEditable(true);
					pfPassword_Same.setEditable(true);
					tfEmail.setEditable(true);
					tfName.setEditable(true);
					tfTelno.setEditable(true);

				}
			});
			btnNewButton_3.setBounds(396, 480, 117, 35);
		}
		return btnNewButton_3;
	}

	private JLabel getLblPassWordCompare() {
		if (lblPassWordCompare == null) {
			lblPassWordCompare = new JLabel("비밀번호를 입력하세요.");
			lblPassWordCompare.setForeground(Color.LIGHT_GRAY);
			lblPassWordCompare.setBounds(283, 233, 219, 16);
		}
		return lblPassWordCompare;
	}

	// Functions
	//1.   등록 버튼 눌렀을 떄 
	private void registrationAction() {

		int i_chk = insertFieldCheck();

		// 중복확인

		boolean idOverlapCheck_whenOKbtnClicked = idOverlapCheckWhenOKClicked();

		// 정보제공 동의 확인
		boolean infocheck = informationOfferAgree();

		if (i_chk == 0 && idOverlapCheck_whenOKbtnClicked && infocheck) {

			// 화면에서 입력받은 값을 변수에 넣어 준다.

			String customer_id = tfID.getText();
			char[] nameArray = pfPassword.getPassword();
			String pw = new String(nameArray);
			String name = tfName.getText();

			String telno = tfTelno.getText();
			String email = tfEmail.getText();

			// Register 를 위한 Dao 생성

			try {
				Dao_pdg dao = new Dao_pdg(customer_id, name, pw, telno, email);
				boolean isDaoOperated = dao.insertAction();
				if (isDaoOperated == true) {
					JOptionPane.showMessageDialog(null, "회원 등록이 완료 되었습니다. ");
					
					
					gologin();
					
					
					
					
				} else {
					JOptionPane.showMessageDialog(null, "등록 중 문제가 생겼습니다. ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// 정상이 아닌 경우
			JOptionPane.showMessageDialog(null, "등록 정보를 다시 확인하세요. ");
		}
	}

	private int insertFieldCheck() {

		int i = 0;

		char[] passArray = pfPassword.getPassword();
		String passString = new String(passArray);

		if (tfID.getText().trim().length() == 0) {
			i++;
			tfID.requestFocus();
		}
		if (passString.trim().length() == 0) {
			i++;
			pfPassword.requestFocus();
		}
		if (tfName.getText().trim().length() == 0) {
			i++;
			tfName.requestFocus();
		}
		if (tfTelno.getText().trim().length() == 0) {
			i++;
			tfTelno.requestFocus();
		}
		if (tfEmail.getText().trim().length() == 0) {
			i++;
			tfEmail.requestFocus();
		}
		return i;
	}

	// ID 중복 체크 확인
	private boolean idOverlapCheck() {

		String id = tfID.getText().trim();
		Dao_pdg dao = new Dao_pdg(id);

		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .");
			tfID.requestFocus();
			return false;
		}

//		
//		if (Pattern.matches("^[a-zA-Z]*$", id) ){
//
//			JOptionPane.showMessageDialog(null,"영문과 숫자로만 아이디를 만들수 있습니다. ");
//			
//			tfID.requestFocus();
//			tfID.setEditable(true);
//			
//			return false;
//		}

		int isIdOverlaped = dao.isIdAlreadyExist();

		if (isIdOverlaped != 0) {
			JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
			tfID.requestFocus();
			tfID.setEditable(true);
			return false;
		} else {
			JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다. ");
			tfID.setEditable(false);

			return true;
		}
	}

	// ok 클릭시 ID 중복 확인 다시!
	private boolean idOverlapCheckWhenOKClicked() {

		String id = tfID.getText().trim();
		Dao_pdg dao = new Dao_pdg(id);

		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		String str = "abcd";

		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.find()); // true

//		if (!Pattern.matches("^[a-zA-Z]*$", id)) {
//
//			JOptionPane.showMessageDialog(null, "영문과 숫자로만 아이디를 만들수 있습니다. ");
//
//			tfID.requestFocus();
//			tfID.setEditable(true);
//
//			return false;
//		}

		int isIdOverlaped = dao.isIdAlreadyExist();

		if (isIdOverlaped != 0) {
			JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");

			tfID.requestFocus();
			tfID.setEditable(true);

			return false;
		} else {

			tfID.setEditable(false);

			return true;
		}
	}

	// 정보 제공 동의 확인

	private boolean informationOfferAgree() {

		if (rbInformationOfferAgree.isSelected() == false) {
			JOptionPane.showMessageDialog(null, " 가입하시려면  정보 제공 동의가 필요합니다. ");
			return false;
		} else {
			return true;
		}

	}

	// 비밀번호 확인

	private boolean isSameP1P2() {
		char[] pw1 = pfPassword.getPassword();
		String passString1 = new String(pw1);
		char[] pw2 = pfPassword_Same.getPassword();
		String passString2 = new String(pw2);

		System.out.println(passString1);
		System.out.println(passString2);

		if (passString1.equals(passString2)) {

			lblPassWordCompare.setText("비밀번호가 일치 합니다. ");
			lblPassWordCompare.setForeground(Color.BLUE);

		} else {

			lblPassWordCompare.setText("비밀번호가 일치 하지 않습니다.");
			lblPassWordCompare.setForeground(Color.RED);

		}

		return true;

	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("ID중복확인");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// 중복확인 버튼
					idOverlapCheck();
				}
			});
			btnNewButton_1.setBounds(585, 76, 99, 35);
		}
		return btnNewButton_1;
	}

	private JPasswordField getPfPassword_Same() {
		if (pfPassword_Same == null) {
			pfPassword_Same = new JPasswordField();
			pfPassword_Same.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					//// Caret 을쓰니까 가상키보드로 할때도 인식함!!!!
					lblPassword_2.setVisible(false);
					isSameP1P2();
					
				}
			});
			pfPassword_Same.addContainerListener(new ContainerAdapter() {
				@Override
				public void componentAdded(ContainerEvent e) {
					lblPassword_2.setVisible(false);
					isSameP1P2();
					
				}
			});
			pfPassword_Same.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {

					// 비밀번호 확인 용 두번째 비밀번호
					lblPassword_2.setVisible(false);
					isSameP1P2();
				}

				@Override
				public void keyTyped(KeyEvent e) {

					// 암호 키 제대로 했는지 확인하기
					// isSameP1P2();
					lblPassword_2.setVisible(false);
					isSameP1P2();

				}

				@Override
				public void keyReleased(KeyEvent e) {
					lblPassword_2.setVisible(false);
					isSameP1P2();

				}
			});
			pfPassword_Same.setBounds(267, 183, 259, 50);
		}
		return pfPassword_Same;
	}

	private JLabel getLblPassword_2() {
		if (lblPassword_2 == null) {
			lblPassword_2 = new JLabel("비밀번호 확인");
			lblPassword_2.setForeground(Color.GRAY);
			lblPassword_2.setBounds(283, 202, 88, 16);
		}
		return lblPassword_2;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setBackground(new Color(238, 238, 238));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					goHomeAction();

				}
			});
			btnNewButton_2.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/goToFirstPage.png")));
			btnNewButton_2.setBounds(713, 16, 35, 35);
		}
		return btnNewButton_2;
	}

	// Function

	private void goHomeAction() {
		joindialog.setVisible(false);

		FirstPage first = new FirstPage();

		first.setVisible(true);

		// this.setVisible(false);
		dispose();

	}
	
	private void gologin() {
		
		joindialog.setVisible(false);

		LoginPage login = new LoginPage();

		login.setVisible(true);

		// this.setVisible(false);
		dispose();
		
		
		
	}
	
	private JButton getBtnIdKeyboard() {
		if (btnIdKeyboard == null) {
			btnIdKeyboard = new JButton("");
			btnIdKeyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,tfID);
				}
			});
			btnIdKeyboard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnIdKeyboard.setBounds(538, 77, 35, 35);
		}
		return btnIdKeyboard;
	}
	private JButton getBtnPWKeyboard() {
		if (btnPWKeyboard == null) {
			btnPWKeyboard = new JButton("");
			btnPWKeyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,pfPassword);
				}
			});
			btnPWKeyboard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnPWKeyboard.setBounds(538, 133, 35, 35);
		}
		return btnPWKeyboard;
	}
	private JButton getBtnPW2Keyboard() {
		if (btnPW2Keyboard == null) {
			btnPW2Keyboard = new JButton("");
			btnPW2Keyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,pfPassword_Same);
				}
			});
			btnPW2Keyboard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnPW2Keyboard.setBounds(538, 195, 35, 35);
		}
		return btnPW2Keyboard;
	}
	private JButton getBtnEmailKeyboard() {
		if (btnEmailKeyboard == null) {
			btnEmailKeyboard = new JButton("");
			btnEmailKeyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,tfEmail);
				}
			});
			btnEmailKeyboard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnEmailKeyboard.setBounds(538, 258, 35, 35);
		}
		return btnEmailKeyboard;
	}
	private JButton getBtnNameKeyboard() {
		if (btnNameKeyboard == null) {
			btnNameKeyboard = new JButton("");
			btnNameKeyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,tfName);
				}
			});
			btnNameKeyboard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnNameKeyboard.setBounds(538, 321, 35, 35);
		}
		return btnNameKeyboard;
	}
	private JButton getBtnTellNokeyBoard() {
		if (btnTellNokeyBoard == null) {
			btnTellNokeyBoard = new JButton("");
			btnTellNokeyBoard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ID 가상키보드 
					KeyBoardUpDown(joindialog,tfTelno);
				}
			});
			btnTellNokeyBoard.setIcon(new ImageIcon(JoinPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnTellNokeyBoard.setBounds(538, 386, 35, 35);
		}
		return btnTellNokeyBoard;
	}


	/// Virtual keyboard call
	//id 옆에 있는 키보드 입력버튼
	private JButton getBtnKeyboard() {
		if (btnKeyboard == null) {
			btnKeyboard = new JButton("");
			btnKeyboard.setVisible(true);
			
			btnKeyboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//JTextField.getCaretPosition()
					//Cursor cursor = null; 
					//tfID.requestFocus();
					KeyBoardUpDown(joindialog,tfID);
					


					
				}
			});
			btnKeyboard.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnKeyboard.setBounds(517, 199, 35, 29);
		}
		return btnKeyboard;
	}
	// PW 옆에 있는 키보드입력버튼 
	private JButton getBtnKeyboard2() {
		if (btnKeyboard2 == null) {
			btnKeyboard2 = new JButton("");
			btnKeyboard2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					KeyBoardUpDown(joindialog,pfPassword);;
				}
			});
			btnKeyboard2.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnKeyboard2.setBounds(517, 257, 35, 35);
		}
		return btnKeyboard2;
	}
	// keyboard  띄우
	private static void KeyBoardUpDown(JDialog parentDialog, JTextField targetTextField ) {
		
		JDialog KeyWin = new JDialog(parentDialog, "가상 키보드", true);
		KeyWin.getContentPane().setLayout(new BorderLayout());
		KeyWin.setBounds(ShareVar.position_window_x, ShareVar.position_window_y+485,
        								ShareVar.window_size_x, 230);
		KeyWin.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));

        JPanel KeyboardPanel = new JPanel();
        KeyboardPanel.setBackground(new Color(0, ShareVar.RGB_green, ShareVar.RGB_blue));
        KeyWin.getContentPane().setLayout(new BorderLayout());
		KeyboardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		KeyWin.getContentPane().add(KeyboardPanel, BorderLayout.CENTER);
		KeyboardPanel.setLayout(null);
        
        // 가상 키보드 버튼 생성
		int key_x_gap =48;
		int key_y_gap =35;
		int line_indent_gap =10;
		
		// 첫번째 줄
        addButton(KeyWin,KeyboardPanel,"1",targetTextField,key_x_gap*0,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"2",targetTextField,key_x_gap*1,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"3",targetTextField,key_x_gap*2,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"4",targetTextField,key_x_gap*3,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"5",targetTextField,key_x_gap*4,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"6",targetTextField,key_x_gap*5,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"7",targetTextField,key_x_gap*6,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"8",targetTextField,key_x_gap*7,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"9",targetTextField,key_x_gap*8,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"0",targetTextField,key_x_gap*9,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"-",targetTextField,key_x_gap*10,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"+",targetTextField,key_x_gap*11,key_y_gap*0);
        addButton(KeyWin,KeyboardPanel,"back",targetTextField,key_x_gap*12,key_y_gap*0);
		
        // 두번째 줄
        addButton(KeyWin,KeyboardPanel,"Q",targetTextField,line_indent_gap+key_x_gap*0,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"W",targetTextField,line_indent_gap+key_x_gap*1,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"E",targetTextField,line_indent_gap+key_x_gap*2,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"R",targetTextField,line_indent_gap+key_x_gap*3,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"T",targetTextField,line_indent_gap+key_x_gap*4,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"Y",targetTextField,line_indent_gap+key_x_gap*5,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"U",targetTextField,line_indent_gap+key_x_gap*6,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"I",targetTextField,line_indent_gap+key_x_gap*7,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"O",targetTextField,line_indent_gap+key_x_gap*8,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"P",targetTextField,line_indent_gap+key_x_gap*9,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"{",targetTextField,line_indent_gap+key_x_gap*10,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"}",targetTextField,line_indent_gap+key_x_gap*11,key_y_gap*1);
        addButton(KeyWin,KeyboardPanel,"*",targetTextField,line_indent_gap+key_x_gap*12,key_y_gap*1);
        
        // 세번째 줄
        
        
        addButton(KeyWin,KeyboardPanel,"@",targetTextField,line_indent_gap*0-15+key_x_gap*0,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"A",targetTextField,line_indent_gap*2+key_x_gap*0,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"S",targetTextField,line_indent_gap*2+key_x_gap*1,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"D",targetTextField,line_indent_gap*2+key_x_gap*2,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"F",targetTextField,line_indent_gap*2+key_x_gap*3,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"G",targetTextField,line_indent_gap*2+key_x_gap*4,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"H",targetTextField,line_indent_gap*2+key_x_gap*5,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"J",targetTextField,line_indent_gap*2+key_x_gap*6,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"K",targetTextField,line_indent_gap*2+key_x_gap*7,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"L",targetTextField,line_indent_gap*2+key_x_gap*8,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,";",targetTextField,line_indent_gap*2+key_x_gap*9,key_y_gap*2);
        addButton(KeyWin,KeyboardPanel,"'",targetTextField,line_indent_gap*2+key_x_gap*10,key_y_gap*2);
        
        addButton(KeyWin,KeyboardPanel,"Enter",targetTextField,line_indent_gap*2+key_x_gap*11,key_y_gap*2);
  
        // 네번째 줄
        addButton(KeyWin,KeyboardPanel,"A/a",targetTextField,3+key_x_gap*0,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"Z",targetTextField,line_indent_gap*5+key_x_gap*0,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"X",targetTextField,line_indent_gap*5+key_x_gap*1,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"C",targetTextField,line_indent_gap*5+key_x_gap*2,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"V",targetTextField,line_indent_gap*5+key_x_gap*3,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"B",targetTextField,line_indent_gap*5+key_x_gap*4,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"N",targetTextField,line_indent_gap*5+key_x_gap*5,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"M",targetTextField,line_indent_gap*5+key_x_gap*6,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,",",targetTextField,line_indent_gap*5+key_x_gap*7,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,".",targetTextField,line_indent_gap*5+key_x_gap*8,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"?",targetTextField,line_indent_gap*5+key_x_gap*9,key_y_gap*3);
        
        addButton(KeyWin,KeyboardPanel,"space",targetTextField,line_indent_gap*5+key_x_gap*0,key_y_gap*4);

        
        addButton(KeyWin,KeyboardPanel,"Clear",targetTextField,line_indent_gap*5+key_x_gap*10,key_y_gap*3);
        
        
        
        
        
        
        //KeyboardPanel.add(getcloseButton(virtualKeyboardDialog));
        
        
        //virtualKeyboardDialog.add(KeyboardPanel);

        //virtualKeyboardDialog.setSize(200, 200);
        KeyWin.setVisible(true);
		
		
	}

	//특정다이얼로그와 라벨, 텍스트 필드를 입력받아서.  거기에 키를 입력시킴. 
    private static void addButton(JDialog keyboardWin, JPanel panel, String keyname, JTextField textField,int pos_x,int pos_y) {
        JButton button = new JButton(keyname);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyname.equals("Clear")) {
                	
                    textField.setText("");
                } 
                else if(keyname.equals("A/a")){
                	
                	keyAlphabetLowerOrUpper=!keyAlphabetLowerOrUpper;
                	//System.out.println(keyAlphabetLowerOrUpper);

                }
                
                else if(keyname.equals("Enter")){
                	
                	keyboardWin.dispose();
                }
                
                
                else if(keyname.equals("back")){
                	// Backspace 버튼을 클릭하면 텍스트 필드에서 마지막 글자를 지움
                	String currentText = textField.getText();
                	if (currentText.length() > 0) {
                		textField.setText(currentText.substring(0, currentText.length() - 1));
                	}
                }
                else if(keyname.equals("space")){
                	// Backspace 버튼을 클릭하면 텍스트 필드에서 마지막 글자를 지움
                
                	 textField.setText(textField.getText() + " ");
                }
                
                
                
                else {
                	if(keyAlphabetLowerOrUpper) {
                		;
                		textField.setText(textField.getText() + keyname.toUpperCase());
                	}else {
                		;
                		textField.setText(textField.getText() + keyname.toLowerCase());
                	}
                    //textField.setText(textField.getText() + keyname);
                }
            }
        });
        if (keyname.equals("Clear")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x, keyboardStartVerticalLine+pos_y, 100, 32);
            panel.add(button);
        	
        }
        else if (keyname.equals("Enter")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x, keyboardStartVerticalLine+pos_y, 85, 32);
            panel.add(button);
        	
        }
        else if (keyname.equals("A/a")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x-15, keyboardStartVerticalLine+pos_y, 60, 32);
            panel.add(button);
        	
        }
        else if (keyname.equals("back")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x, keyboardStartVerticalLine+pos_y, 65, 32);
            panel.add(button);
        	
        }
        else if (keyname.equals("@")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x-15, keyboardStartVerticalLine+pos_y, 47, 32);
            panel.add(button);
        	
        }
        else if (keyname.equals("space")) {
            button.setFont(new Font("굴림", Font.BOLD, 13));
            button.setBounds(keyboardStartHorizontalLine+pos_x-15, keyboardStartVerticalLine+pos_y, 500, 32);
            panel.add(button);
        	
        }
        
        
        else {
	        button.setFont(new Font("굴림", Font.BOLD, 13));
	        button.setBounds(keyboardStartHorizontalLine+pos_x, keyboardStartVerticalLine+pos_y, 46, 32);
	        panel.add(button);
        }
        //dialog.add(button);
    }
	







}// ENd
