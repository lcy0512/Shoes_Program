package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.Dao_pdg;

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
	 * 		1. 아이디 패스워드를  커스터머ㄷ 디비에서 불러와서 있으면 -> 메인 페이지
	 * 			없으면 회원가입하십시요 
	 * 	o	1. 아이디 비밀번호 찾기 페이지 
	 *  	2. 입력 받은 것으로 db 에 등록하기
	 *  	3. 
	 * 
	 */
	
	
	
	
	static LoginPage login_dialog =new LoginPage();
	private JComboBox cbExample;
	

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
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("회원 아이디 : ");
			lblNewLabel.setBounds(234, 202, 107, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			tfID = new JTextField();
			tfID.setBounds(330, 185, 213, 50);
			contentPanel.add(tfID);
			tfID.setColumns(10);
		}
		{
			JLabel lblMemberPassword = new JLabel("회원 패스워드 :");
			lblMemberPassword.setBounds(234, 247, 141, 21);
			contentPanel.add(lblMemberPassword);
		}
		contentPanel.add(getPfPassword());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnNewButton_1());
	
	}
	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.setBounds(330, 232, 213, 50);
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
			btnNewButton.setBounds(316, 304, 117, 47);
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
			
			JOptionPane.showMessageDialog(null, "회원이 입니다.");
			
			
			
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
			btnNewButton_1.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/home1.png"));
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
	

		
		

} // END
