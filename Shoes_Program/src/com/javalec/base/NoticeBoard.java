package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.ShareVar;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NoticeBoard extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JCheckBox cbNoAnyMore;
	
	/*
	 * Description : 공지사항
	 * Author : Forrest D Park( PDG)
	 * Date : 2023.12.30
	 * 
	 * Update 2023.12.30 by PDG: 
	 * 		o	1. 일주일 간 안보기가 작아서 윈도우 앱에서는 짤림
	 * 		o	2. 게시글이 정렬이 안됨 
	 * 		o	3. 공지사항 수정, 체크박스 투명화 안되서 라벨 사용.
	 * 
	 * 
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NoticeBoard dialog = new NoticeBoard();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NoticeBoard() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				neverSeeAgain();
				
			}
		});
		setTitle("공지사항");

		setBounds(ShareVar.bulletin_x, ShareVar.bulletin_y, 600	, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("다시 보지 않기");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_2.setBounds(144, 197, 92, 15);
		contentPanel.add(lblNewLabel_2);
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getCbNoAnyMore());
		contentPanel.add(getLblNewLabel());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(NoticeBoard.class.getResource("/com/javalec/image/notice_bg.png")));
			lblNewLabel.setBounds(0, 0, 600, 350);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("<html><center>*****새해복 많이 받으세요.***** <br>"
					+ "  2024 신년 맞이 프리미엄 이벤트 <br>"
					+ " 1월 1일 에서 1월 2일까지  <br>전품목 20.24% 할인 </center> </html>");
			lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblNewLabel_1.setBounds(12, 49, 333, 149);
		}
		return lblNewLabel_1;
	}
	private JCheckBox getCbNoAnyMore() {
		if (cbNoAnyMore == null) {
			cbNoAnyMore = new JCheckBox("");
			cbNoAnyMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ShareVar.noticeSee =true;
					neverSeeAgain();
				}
			});
			cbNoAnyMore.setBounds(115, 192, 21, 23);
		}
		return cbNoAnyMore;
	}
	
	
	// Function
	// 1.일주일간 보지 않기 
	private void neverSeeAgain() {
		
		if(ShareVar.noticeSee== true) {
			
			this.setVisible(false);
			
			
		}
		
		
	}
}// End
