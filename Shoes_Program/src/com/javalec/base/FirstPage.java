package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	
	
	/*
	 * Description : Fist page
	 * Date : 2023. 12. 27
	 * Author : D Forrest Park
	 * Update : 
	 * 		1.  관리자용 페이지 버튼 입력되었을 경우???"????? 필요?
	 */
	
	
	static FirstPage dialog = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new FirstPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FirstPage() {
		setBounds(100, 100, 800,600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(153, 204, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNotMember = new JButton("비회원 입장");
			btnNotMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			btnNotMember.setBackground(new Color(153, 153, 255));
			btnNotMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnNotMember.setBounds(25, 372, 150, 120);
			contentPanel.add(btnNotMember);
		}
		{
			JButton btnMember = new JButton("일반회원 입장");
			btnMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					normalCustomerLogin();
					
					
				}
			});
			btnMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnMember.setBounds(212, 372, 150, 120);
			contentPanel.add(btnMember);
		}
		{
			JButton btnPremiumMember = new JButton("프리미엄 입장");
			btnPremiumMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					premiumLogin();
					
				}
			});
			btnPremiumMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnPremiumMember.setBounds(415, 372, 150, 120);
			contentPanel.add(btnPremiumMember);
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon("/Users/tj/Desktop/ShoesMarketSemiProject/Shoes_Program/Shoes_Program/src/com/javalec/image/로고 .png"));
			lblNewLabel.setBounds(168, -50, 500, 500);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("JUMP");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 33));
			lblNewLabel_1.setBounds(158, 75, 271, 86);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JButton btnPremiumMember = new JButton("회원 가입");
			btnPremiumMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					joinPageAction();	
					
					
					
				}
			});
			btnPremiumMember.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			btnPremiumMember.setBounds(616, 372, 150, 120);
			contentPanel.add(btnPremiumMember);
		}
	}

	
	
	private void normalCustomerLogin() {
		
		
		dialog.setVisible(false);

		LoginPage login  = new LoginPage();
		login.setVisible(true);
		
		
	}
	
	private void joinPageAction() {
		dialog.setVisible(false);

		JoinPage join  = new JoinPage();
		join.setVisible(true);
		
	}
	private void premiumLogin() {
		dialog.setVisible(false);

		JoinPage join  = new JoinPage();
		join.setVisible(true);
		
		
	}
	
	
	
	
}// End
