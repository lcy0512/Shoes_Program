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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JPasswordField passwordField;
	private JLabel lblPassword_1;
	
	/*
	 * Description :  회원 가입 페이
	 * Date : 20203.12.27
	 * Author : Dong Geun Forrest Park
	 * Update : 
	 * 	o	1. GUI design
	 *  o	2. 입력 받은 것으로 db 에 등록하기
	 *  	3. id 길이가 너무 길면 쿼리가 안들어감. 
	 *  o	4. 아이디 중복확인.
	 *  	5. 아이디는 숫자와 영어로만 등록할수있습니다. 
	 * 
	 */
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JoinPage dialog = new JoinPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JoinPage() {
		setTitle("회원가입");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
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
		contentPanel.add(getLblPassword_1());
		contentPanel.add(getPasswordField());
	}

	private JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("아이디 : 필수 정보입니다.");
			lblID.setForeground(Color.GRAY);
			lblID.setBounds(246, 108, 185, 16);
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
			tfID.setBounds(230, 93, 259, 50);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("비밀번호");
			lblPassword.setForeground(Color.GRAY);
			lblPassword.setBounds(246, 163, 61, 16);
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
			pfPassword.setBounds(230, 146, 259, 50);
		}
		return pfPassword;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("");
			lblNewLabel_6.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/PW1.png"));
			lblNewLabel_6.setBounds(198, 150, 35, 35);
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
			tfEmail.setBounds(230, 274, 259, 50);
		}
		return tfEmail;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("[선택] 비밀번호 분실시 확인용 이메일");
			lblEmail.setForeground(Color.GRAY);
			lblEmail.setBounds(242, 289, 189, 16);
		}
		return lblEmail;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/email.png"));
			lblNewLabel_4.setBounds(198, 279, 35, 35);
		}
		return lblNewLabel_4;
	}
	private JLabel getNameImage() {
		if (nameImage == null) {
			nameImage = new JLabel("");
			nameImage.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/Name.png"));
			nameImage.setBounds(198, 343, 35, 35);
		}
		return nameImage;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("이름");
			lblName.setForeground(Color.GRAY);
			lblName.setBounds(246, 351, 61, 16);
		}
		return lblName;
	}
	private JLabel getMailImage() {
		if (mailImage == null) {
			mailImage = new JLabel("");
			mailImage.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/email.png"));
			mailImage.setBounds(240, 283, 35, 35);
		}
		return mailImage;
	}
	private JLabel getLblNewLabel_6_1_1() {
		if (lblNewLabel_6_1_1 == null) {
			lblNewLabel_6_1_1 = new JLabel("");
			lblNewLabel_6_1_1.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/Name.png"));
			lblNewLabel_6_1_1.setBounds(198, 100, 35, 35);
		}
		return lblNewLabel_6_1_1;
	}
	private JLabel getLblTelno() {
		if (lblTelno == null) {
			lblTelno = new JLabel("전화번호");
			lblTelno.setForeground(Color.GRAY);
			lblTelno.setBounds(246, 414, 61, 16);
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
			tfName.setBounds(230, 336, 259, 50);
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
			tfTelno.setBounds(230, 399, 259, 50);
		}
		return tfTelno;
	}

	//////  회원등록 버튼!
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("가입하기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					registrationAction();
				}
			});
			btnNewButton.setBounds(308, 471, 117, 35);
		}
		return btnNewButton;
	}

	
	
	private JLabel getLblNewLabel_6_1_2() {
		if (lblNewLabel_6_1_2 == null) {
			lblNewLabel_6_1_2 = new JLabel("");
			lblNewLabel_6_1_2.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/email.png"));
			lblNewLabel_6_1_2.setBounds(198, 399, 35, 35);
		}
		return lblNewLabel_6_1_2;
	}

	// Functions 
	
	
	
	
	private void registrationAction() {
		
		int i_chk = insertFieldCheck();
		
		if (i_chk ==0) {
			
			
			//화면에서 입력받은 값을 변수에 넣어 준다. 
			
			String customer_id = tfID.getText();
			String name = pfPassword.getText();
			String pw = tfName.getText();
			String telno = tfTelno.getText();
			String email = tfEmail.getText();
			
			
			// 중복확인 
			
			idOverlapCheck();
			
			// Register 를 위한 Dao 생성
			
			try {
				Dao_pdg dao = new Dao_pdg(customer_id, name, pw, telno, email);
				boolean isDaoOperated = dao.insertAction();
				
				if (isDaoOperated == true) {
					JOptionPane.showMessageDialog(null, "회원 등록이 완료 되었습니다. ");
					
				}else {
					
					JOptionPane.showMessageDialog(null ,"등록 중 문제가 생겼습니다. ");
				}
				
				
				
			}catch(Exception e ) {
				e.printStackTrace();
			}
			
			
			
			
			

			
		}else {
			// 정상이 아닌 경우
			JOptionPane.showMessageDialog(null,"데이터를 확인하세요1" );
		}
		
		
		
		
		
	}
	private int insertFieldCheck() {
		
		int i = 0;
		if(tfID.getText().trim().length()==0) {
			i++;
			tfID.requestFocus();
		}
		if(pfPassword.getText().trim().length()==0) {
			i++;
			pfPassword.requestFocus();
		}
		if(tfName.getText().trim().length()==0) {
			i++;
			tfName.requestFocus();
		}
		if(tfTelno.getText().trim().length()==0) {
			i++;
			tfTelno.requestFocus();
		}
		if(tfEmail.getText().trim().length()==0) {
			i++;
			tfEmail.requestFocus();
		}
		return i;
	}
	

	// ID 중복 체크 확인 
	private void idOverlapCheck() {
		
		String id = tfID.getText().trim();
		
		Dao_pdg dao = new Dao_pdg(id);
		
		
		int isIdOverlaped = dao.idOverlapAction();
		
		if(isIdOverlaped != 0) {
			
			JOptionPane.showMessageDialog(null,"중복된 아이디입니다.");
		}
		
		
		
	}
	
	
	
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("중복확인");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//중복확인 
					idOverlapCheck();
				}
			});
			btnNewButton_1.setBounds(501, 93, 88, 50);
		}
		return btnNewButton_1;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(230, 196, 259, 50);
		}
		return passwordField;
	}
	private JLabel getLblPassword_1() {
		if (lblPassword_1 == null) {
			lblPassword_1 = new JLabel("비밀번호 확인");
			lblPassword_1.setForeground(Color.GRAY);
			lblPassword_1.setBounds(246, 210, 88, 16);
		}
		return lblPassword_1;
	}
}// ENd
