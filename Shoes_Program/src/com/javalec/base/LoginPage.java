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
	 * Author : Dong Geun Forrest Park
	 * Update : 
	 *  o   1. 아이디 패스워드를  커스터머ㄷ 디비에서 불러와서 있으면 -> 메인 페이지
	 * 			없으면 회원가입하십시요 
	 * 	o	1. 아이디 비밀번호 찾기 페이지 
	 *  o	2. 입력 받은 것으로 db 에 등록하기
	 *  o	3. 로그인창이 가운데로오게 share bar 에서 위치 가져옴. 
	 * 		4. 회원가입이랑 비슷하게 수
	 */
	
	
	
	
	static LoginPage login_dialog =new LoginPage();
	private JComboBox cbExample;
	private JLabel imageUser;
	private JLabel imagePass;
	private JLabel lblPasswrodPress;
	private JLabel lblPassword;
	private JRadioButton rbPattern1;
	private JRadioButton rbPattern2;
	private JRadioButton rbPattern3;
	private JRadioButton rbPattern4;
	
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
			btnNewButton_1.setBounds(723, 25, 35, 29);
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
		lblIdPress.setBounds(312, 212, 107, 16);
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
		contentPanel.add(getRbPattern2());
		contentPanel.add(getRbPattern3());
		contentPanel.add(getRbPattern4());
	
		}

	private void pressLogIn() {
		
		if(rbPattern1.isSelected()&&rbPattern2.isSelected()&&rbPattern3.isSelected()) {
			// Manager Page open!!
			
		}
		String id = tfID.getText();
		char[] pw = pfPassword.getPassword();
		String passString = new String(pw);
		Dao_pdg dao = new Dao_pdg(id,passString);
		boolean isExist = dao.idPwCheck();
		
		if(isExist) {
			// login pass 후 메인화면 으로 이동 !
//			JOptionPane.showMessageDialog(null, .");
			
			ShareVar.userID =id;
			
			if(id.equals("admin") && rbPattern1.isSelected()&&rbPattern2.isSelected()&&rbPattern3.isSelected() ) {
				ShareVar.userID =id;
				JOptionPane.showMessageDialog(null, "관리자 페이지로 이동합니다. ");
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
			lblPassword.setBounds(312, 259, 122, 16);
		}
		return lblPassword;
	}
	private JRadioButton getRbPattern1() {
		if (rbPattern1 == null) {
			rbPattern1 = new JRadioButton("");
			rbPattern1.setBounds(6, 6, 28, 23);
		}
		return rbPattern1;
	}
	private JRadioButton getRbPattern2() {
		if (rbPattern2 == null) {
			rbPattern2 = new JRadioButton("");
			rbPattern2.setBounds(6, 543, 28, 23);
		}
		return rbPattern2;
	}
	private JRadioButton getRbPattern3() {
		if (rbPattern3 == null) {
			rbPattern3 = new JRadioButton("");
			rbPattern3.setBounds(766, 543, 28, 23);
		}
		return rbPattern3;
	}
	private JRadioButton getRbPattern4() {
		if (rbPattern4 == null) {
			rbPattern4 = new JRadioButton("");
			rbPattern4.setBounds(770, 6, 28, 23);
		}
		return rbPattern4;
	}
} // END
