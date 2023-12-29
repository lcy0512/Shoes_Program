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
			lblNewLabel_1 = new JLabel("<html>새해복 많이 받으세요. <br>  2024 신년 맞이 이벤트!! <br> 1월 1일 에서 1월 2일까지 (멤버쉽 고객한정 ) 전품목 20.24% 할인!  </html>");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblNewLabel_1.setBounds(59, 36, 282, 193);
		}
		return lblNewLabel_1;
	}
	private JCheckBox getCbNoAnyMore() {
		if (cbNoAnyMore == null) {
			cbNoAnyMore = new JCheckBox("일주일간 보지 않기");
			cbNoAnyMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ShareVar.noticeSee =true;
					neverSeeAgain();
				}
			});
			cbNoAnyMore.setBounds(85, 191, 128, 23);
		}
		return cbNoAnyMore;
	}
	
	
	// Function
	
	private void neverSeeAgain() {
		
		if(ShareVar.noticeSee== true) {
			
			this.setVisible(false);
			
			
		}
		
		
	}
	
	
	
	
}// End
