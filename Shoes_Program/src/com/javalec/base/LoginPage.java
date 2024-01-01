package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import com.javalec.function.Dao_pdg;
import com.javalec.function.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPage extends JDialog /*implements KeyBoard_interface*/ {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfID;
	private JPasswordField pfPassword ;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	static LoginPage login_dialog =new LoginPage();
	private JComboBox cbExample;
	private JLabel imageUser;
	private JLabel imagePass;
	private JLabel lblPasswrodPress;
	private JLabel lblPassword;
	private JRadioButton rbPattern1;
//	private static JButton closeButton;
	
	
	
	//keyboard 관련 변수
	
	private JButton btnKeyboard;
	private static int keyboardStartHorizontalLine=50;
	private static int keyboardStartVerticalLine=5;
	private static boolean keyAlphabetLowerOrUpper =false; // false equal lower case, it is initial state
	private JButton btnKeyboard2;
	
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
	 *  O	1.  winodow 로 키면 그림들이 안보임. 체크박스도 없어보임 -> 대거 수정
	 *  
	 *  Update 2023.12.31 by PDG:
	 *  
	 *  		1. 키보드에서 친 글자들이 로그인 창의 ID 나 pw 창에 뜨게하고싶은데 share var 를 이용해도 실시간으로 글이 안써짐. 
	 *  		2. 아싸리 키보드를 인터페이스로 구현한뒤 키보드의 기능을 클래스에서 끌어와 쓰면 편할것 같음. 
	 *
	 * Update 2024.01.01 by PDG
	 * 
	 * 			1. VIRTUAL KEYBOARD 생성함. !!! 
	 * 
	 * 
	 * 
	 */
	
	
	

	
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
			tfID.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					ShareVar.currentTF =tfID;
				}
			});
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
		contentPanel.add(getBtnKeyboard());
		contentPanel.add(getBtnKeyboard2());
	
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
					KeyBoardUpDown(login_dialog,tfID);
					


					
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
					
					KeyBoardUpDown(login_dialog,pfPassword);;
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
		KeyWin.setBounds(ShareVar.position_window_x, ShareVar.position_window_y+435,
        								ShareVar.window_size_x, 210);
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
        addButton(KeyWin,KeyboardPanel,"|",targetTextField,line_indent_gap+key_x_gap*12,key_y_gap*1);
        
        // 세번째 줄
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
        addButton(KeyWin,KeyboardPanel,"<",targetTextField,line_indent_gap*5+key_x_gap*7,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,">",targetTextField,line_indent_gap*5+key_x_gap*8,key_y_gap*3);
        addButton(KeyWin,KeyboardPanel,"?",targetTextField,line_indent_gap*5+key_x_gap*9,key_y_gap*3);

        
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
        
        
        else {
	        button.setFont(new Font("굴림", Font.BOLD, 13));
	        button.setBounds(keyboardStartHorizontalLine+pos_x, keyboardStartVerticalLine+pos_y, 46, 32);
	        panel.add(button);
        }
        //dialog.add(button);
    }
	

} // END
