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

public class NoticeBoard extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNotice;

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
		setTitle("공지사항");

		setBounds(ShareVar.bulletin_x, ShareVar.bulletin_y, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(46, 139, 87));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTfNotice());
	}
	private JTextField getTfNotice() {
		if (tfNotice == null) {
			tfNotice = new JTextField();
			tfNotice.setEditable(false);
			tfNotice.setBackground(new Color(46, 139, 87));
			tfNotice.setHorizontalAlignment(SwingConstants.CENTER);
			tfNotice.setText("공지사항입니다.\n");
			tfNotice.setBounds(57, 36, 346, 187);
			tfNotice.setColumns(10);
		}
		return tfNotice;
	}
}
