package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.ShareVar;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FirstPage extends JDialog {
	
	private static int noticeNum = 0;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	
	
	/*
	 * Description : Fist page
	 * Date : 2023. 12. 27~
	 * Author : D Forrest Park
	 * Update : 
	 * 		1.  관리자용 페이지 버튼 입력되었을 경우
	 * 		
	 * Update 2023.12.29 by PDG
	 * 		1. share bar 를 통해 윈도우 이 동 
	 * 		2. 배경화면 바꿈 
	 * 
	 * Update 2023.12.30 by PDG
	 * 
	 * 		1. 첫 화면 윈도우 앱에서는 줄이 다 안나오고 짤림. => 한칸 띔 < html 사용
	 */
	
	
	static FirstPage firstdialog =  new FirstPage();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		
			firstdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			firstdialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FirstPage() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {
				
				goNoticeBoard();
				
				
			}
		});
		
		// ShareVar 에서 프로그램공통 위치를 가져온다. 
		setBounds(ShareVar.position_window_x	, ShareVar.position_window_y, ShareVar.window_size_x,ShareVar.window_size_y);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBackground(Color.WHITE);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNotMember = new JButton("<html><center>일반 고객<br> 입장</center></html>");
			btnNotMember.setForeground(Color.DARK_GRAY);
			btnNotMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					goToMainView();
					
					
				}
			});
			{
				JLabel lblNewLabel_2 = new JLabel("New label");
				lblNewLabel_2.setIcon(new ImageIcon(FirstPage.class.getResource("/com/javalec/image/JUNES_LOGO.png")));
				lblNewLabel_2.setBounds(296, 190, 230, 70);
				contentPanel.add(lblNewLabel_2);
			}
			btnNotMember.setBackground(Color.WHITE);
			btnNotMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnNotMember.setBounds(162, 307, 150, 120);
			contentPanel.add(btnNotMember);
		}
		{
			JButton btnMember = new JButton("<html><center>멤버쉽 회원 <br>  입장</center></html>");
			btnMember.setForeground(Color.BLACK);
			btnMember.setBackground(new Color(255, 255, 255));
			btnMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					memberLogin();
					
					
				}
			});
			btnMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnMember.setBounds(324, 307, 150, 120);
			contentPanel.add(btnMember);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("JUmp With NEw Shoes.");
			lblNewLabel_1.setFont(new Font("Libian SC", Font.ITALIC, 23));
			lblNewLabel_1.setBounds(290, 260, 244, 53);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JButton btnPremiumMember = new JButton("<html><center>회원 가입</center></html>");
			btnPremiumMember.setForeground(Color.BLACK);
			btnPremiumMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					joinPageAction();	
					
					
					
				}
			});
			btnPremiumMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnPremiumMember.setBounds(486, 305, 166, 120);
			contentPanel.add(btnPremiumMember);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(FirstPage.class.getResource("/com/javalec/image/FirstPageBack.jpeg")));
			lblNewLabel.setBounds(0, 24, 800, 515);
			contentPanel.add(lblNewLabel);
		}
	}
	
	
	//1. 멤버쉽 회원 입장 
	private void memberLogin() {
		
		

		LoginPage login  = new LoginPage();
		login.setVisible(true);
		firstdialog.setVisible(false);
		this.setVisible(false);
		
		//dispose();
		
	}
	//2. 회원가입 페이지로 
	private void joinPageAction() {
		firstdialog.setVisible(false);

		JoinPage join  = new JoinPage();
		join.setVisible(true);
		dispose();
		this.setVisible(false);
		
	}
	//3. 일반(비회원) 입장 => sql 에 비회원 아이디 있어야 함.
	private void goToMainView() {
		
		firstdialog.setVisible(false);
		MainView mainView  = new MainView();
		mainView.setVisible(true);
		dispose();
		this.setVisible(false);
	}

	private void goNoticeBoard() {
		
		if (noticeNum ==0) {
		final NoticeBoard noticeBoard  = new NoticeBoard();
		noticeBoard.setVisible(true);
		noticeNum++;
		
		}
		
	}
	
	
}// End
