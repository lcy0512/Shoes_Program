package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainView dialog = new MainView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainView() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("메인");
			btnNewButton.setBounds(660, 10, 30, 30);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton = new JButton("정보수정");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBounds(702, 11, 30, 30);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton = new JButton("장바구니");
			btnNewButton.setBounds(744, 11, 30, 30);
			contentPanel.add(btnNewButton);
		}
		contentPanel.add(getLblNewLabel());
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nike", "Adidas", "Newbalance"}));
			comboBox.setBounds(54, 88, 156, 27);
			contentPanel.add(comboBox);
		}
		{
			textField = new JTextField();
			textField.setBounds(222, 87, 283, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton btnNewButton_1 = new JButton("검색");
			btnNewButton_1.setBounds(537, 87, 146, 29);
			contentPanel.add(btnNewButton_1);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(59, 149, 673, 331);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("로그인이 필요합니다.");
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel.setBounds(502, 10, 146, 30);
		}
		return lblNewLabel;
	}
}
