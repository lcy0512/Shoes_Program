package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.Dao_pdg;
import com.javalec.function.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class LoginPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfID;
	private JPasswordField pfPassword ;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	
	/*
	 * Description :  로그인 페이지 
	 * Date : 20203.12.27
	 * Author : Dong Geun Forrest Park (PDG)
	 * Update 2023.12.29 by PDG : 
	 *  o   0. 아이디 패스워드를  커스터머ㄷ 디비에서 불러와서 있으면 -> 메인 페이지
	 * 			없으면 회원가입하십시요 
	 * 	o	1. 아이디 비밀번호 찾기 페이지 
	 *  o	2. 입력 받은 것으로 db 에 등록하기
	 *  o	3. 로그인창이 가운데로오게 share bar 에서 위치 가져옴. 
	 * 	o	4.  UI 회원가입이랑 비슷하게 수정 
	 *  Update 2023.12.30 by PDG:
	 *  	1.  winodow 로 키면 그림들이 안보임. 체크박스도 없어보임 -> 대거 수정
	 */
	
	
	
	
	static LoginPage login_dialog =new LoginPage();
	private JComboBox cbExample;
	private JLabel imageUser;
	private JLabel imagePass;
	private JLabel lblPasswrodPress;
	private JLabel lblPassword;
	private JRadioButton rbPattern1;
	private JButton btnNewButton_1_1;
	
	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					
					lblPassword.setVisible(false);
					
				}
			});
			
			pfPassword.setBounds(292, 242, 213, 50);
		}
		return pfPassword;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그인");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Login btn press
					
					pressLogIn();
				}
			});
			btnNewButton.setBounds(346, 311, 117, 47);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goHome();
				}
			});
			btnNewButton_1.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/goToFirstPage.png")));
			btnNewButton_1.setBounds(684, 31, 35, 29);
		}
		return btnNewButton_1;
	}
	private JLabel getImageUser() {
		if (imageUser == null) {
			imageUser = new JLabel("New label");
			imageUser.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/login_new.png")));
			imageUser.setBounds(247, 203, 35, 35);
		}
		return imageUser;
	}
	private JLabel getImagePass() {
		if (imagePass == null) {
			imagePass = new JLabel("");
			imagePass.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/Password_new.png")));
			imagePass.setBounds(250, 250, 35, 28);
		}
		return imagePass;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//login_dialog = new LoginPage();
			login_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			login_dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginPage() {
		setTitle("로그인");
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
			contentPanel.add(getLblPassword());
		
			contentPanel.add(getPfPassword());
		
		JLabel lblIdPress = new JLabel("아이디를 입력하세요.");
		lblIdPress.setForeground(Color.GRAY);
		lblIdPress.setBounds(312, 212, 135, 16);
		contentPanel.add(lblIdPress);
		
		{
			tfID = new JTextField();
			tfID.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					lblIdPress.setVisible(false);
				}
			});
			tfID.setBounds(292, 195, 213, 50);
			contentPanel.add(tfID);
			tfID.setColumns(10);
		}
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnNewButton_1());
		contentPanel.add(getImageUser());
		contentPanel.add(getImagePass());
		contentPanel.add(getRbPattern1());
		contentPanel.add(getBtnNewButton_1_1());
	
		}

	// Login 버튼 실행시 
	private void pressLogIn() {
		

		String id = tfID.getText();
		char[] pw = pfPassword.getPassword();
		String passString = new String(pw);
		Dao_pdg dao = new Dao_pdg(id,passString);
		boolean isExist = dao.idPwCheck();
		
		if(isExist) {
			// login pass 후 메인화면 으로 이동 !
//			JOptionPane.showMessageDialog(null, .");
			
			ShareVar.userID =id;
			
			if(id.equals("admin") && rbPattern1.isSelected() ) {
				ShareVar.userID =id;
				//JOptionPane.showMessageDialog(null, "관리자 페이지로 이동합니다. ");
				goManagerPage();
			}
			
			else goMainView();
			
		}else {
			JOptionPane.showMessageDialog(null, "아이디와 패스워드를 다시 입력해주세요.");
			//  text field 지움
			tfID.setText("");
			pfPassword.setText("");
		}
	}
	
	private void goHome() {
		FirstPage first = new FirstPage();
		login_dialog.setVisible(false);
		first.setVisible(true);
		dispose();
		this.setVisible(false);
	}

	private void goMainView() {
		MainView mainView = new MainView();
		login_dialog.setVisible(false);
		mainView.setVisible(true);
		dispose();
		this.setVisible(false);
//		
//		MainView mainView = new MainView();
//		
//		login_dialog.setVisible(false);
//
//		
//		mainView.setVisible(true);
//		
//		dispose();
//		this.setVisible(false);
		
	}
	
	private void goManagerPage() {
		ManagerPage_ managerPage_ = new ManagerPage_();
		login_dialog.setVisible(false);
		managerPage_.setVisible(true);
		dispose();
		this.setVisible(false);
		
		
	}






	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("패스워드를 입력하세요. ");
			lblPassword.setForeground(Color.GRAY);
			lblPassword.setBounds(312, 259, 151, 16);
		}
		return lblPassword;
	}
	private JRadioButton getRbPattern1() {
		if (rbPattern1 == null) {
			rbPattern1 = new JRadioButton("");
			rbPattern1.setBounds(735, 6, 28, 23);
		}
		return rbPattern1;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyBoardUpDown();
					
				}
			});
			btnNewButton_1_1.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/keyBoard.png")));
			btnNewButton_1_1.setBounds(386, 368, 35, 29);
		}
		return btnNewButton_1_1;
	}
	
	
	private void KeyBoardUpDown() {
		
		Keyboard keyboard = new Keyboard();
		keyboard.keyboadOnOff(!ShareVar.keyboard);
		
		if(tfID.isCursorSet()) {
			keyboard.keyReturn();
			
		}
		
		
		
	}
	
	
	
	
} // END
