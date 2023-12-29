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
	 * Date : 2023. 12. 27
	 * Author : D Forrest Park
	 * Update : 
	 * 		1.  관리자용 페이지 버튼 입력되었을 경우???"????? 필요?
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
		setBounds(ShareVar.position_window_x	, ShareVar.position_window_y, 800,600);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBackground(Color.WHITE);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNotMember = new JButton("일반 고객 입장");
			btnNotMember.setForeground(Color.DARK_GRAY);
			btnNotMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					goToMainView();
					
					
				}
			});
			btnNotMember.setBackground(Color.WHITE);
			btnNotMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnNotMember.setBounds(25, 411, 150, 53);
			contentPanel.add(btnNotMember);
		}
		{
			JButton btnMember = new JButton("멤버쉽회원 입장");
			btnMember.setForeground(Color.BLUE);
			btnMember.setBackground(new Color(255, 255, 255));
			btnMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					normalCustomerLogin();
					
					
				}
			});
			btnMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnMember.setBounds(25, 279, 150, 120);
			contentPanel.add(btnMember);
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(FirstPage.class.getResource("/com/javalec/image/로고 .png")));
			lblNewLabel.setBounds(376, 47, 500, 500);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("신발가게");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 33));
			lblNewLabel_1.setBounds(125, 148, 271, 86);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JButton btnPremiumMember = new JButton("회원 가입");
			btnPremiumMember.setForeground(Color.ORANGE);
			btnPremiumMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					joinPageAction();	
					
					
					
				}
			});
			btnPremiumMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnPremiumMember.setBounds(195, 279, 166, 185);
			contentPanel.add(btnPremiumMember);
		}
	}
	
	private void normalCustomerLogin() {
		
		

		LoginPage login  = new LoginPage();
		login.setVisible(true);
		firstdialog.setVisible(false);
		this.setVisible(false);
		
		//dispose();
		
	}
	
	private void joinPageAction() {
		firstdialog.setVisible(false);

		JoinPage join  = new JoinPage();
		join.setVisible(true);
		dispose();
		this.setVisible(false);
		
	}
	
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
