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

public class LoginPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfID;
	private JPasswordField pfPassword;
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
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	

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
		{
			JLabel lblNewLabel = new JLabel("아이디를 입력하세요.");
			lblNewLabel.setForeground(Color.GRAY);
			lblNewLabel.setBounds(312, 212, 107, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			tfID = new JTextField();
			tfID.setBounds(292, 195, 213, 50);
			contentPanel.add(tfID);
			tfID.setColumns(10);
		}
		{
			JLabel lblMemberPassword = new JLabel("패스워드를 입력하세요.");
			lblMemberPassword.setForeground(Color.GRAY);
			lblMemberPassword.setBounds(312, 257, 141, 21);
			contentPanel.add(lblMemberPassword);
		}
		contentPanel.add(getPfPassword());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnNewButton_1());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getLblNewLabel_2());
	
	}
	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.setBounds(292, 242, 213, 50);
		}
		return pfPassword;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그인");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					pressLogIn();
				}
			});
			btnNewButton.setBounds(346, 311, 117, 47);
		}
		return btnNewButton;
	}
	
	
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
			
			goMainView();
			
			
		}else {
			
			JOptionPane.showMessageDialog(null, "회원이 아닙니다.");
			
			//  text field 지움
			tfID.setText("");
			pfPassword.setText("");
			
		}
		
		
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
	
	
	private void goHome() {
		
		
		FirstPage first = new FirstPage();
		
		login_dialog.setVisible(false);

		
		first.setVisible(true);
		
		dispose();
		this.setVisible(false);
				
		
	}
	

	private void goMainView() {
		
		
		UserUpdatePage1 userupdate = new UserUpdatePage1();
		 
		login_dialog.setVisible(false);

		
		userupdate.setVisible(true);
		
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
	
	
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/login_new.png")));
			lblNewLabel_1.setBounds(247, 203, 35, 35);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(LoginPage.class.getResource("/com/javalec/image/Password_new.png")));
			lblNewLabel_2.setBounds(250, 250, 35, 28);
		}
		return lblNewLabel_2;
	}
} // END
