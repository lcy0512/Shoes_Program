package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_pjm;
import com.javalec.function.Dto;
import com.javalec.function.ShareVar;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	private JScrollPane scrollPane;
	private JRadioButton rbInsert;
	private JTable inner_Table;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JButton btnSearch;
	private JTextField tfSelection;
	private JLabel lblImage;
	private JButton btnOK;
	private JLabel lblNewLabel_1;
	private JTextField tfSeqno;
	private JLabel lblNewLabel_1_1;
	private JTextField tfBrand;
	private JLabel lblNewLabel_1_2;
	private JTextField tfName;
	private JLabel lblNewLabel_1_3;
	private JTextField tfPrice;
	private JLabel lblNewLabel_1_4;
	private JTextField tfColor;
	private JLabel lblNewLabel_1_5;
	private JTextField tfQty;
	private JLabel lblNewLabel_1_6;
	private JComboBox cbSelection1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JRadioButton rbSearch;
	private AbstractButton tfSize;
	private JTextField tfFilePath;
	private JButton btnFilePath;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManagerPage dialog = new ManagerPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManagerPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
				screenPartition();
			}
		});
		setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setTitle("관리자 페이지");
		//setBounds(100, 100, 800, 600);
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y, ShareVar.window_size_x, ShareVar.window_size_y);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbSelection());
		contentPanel.add(getScrollPane());
		contentPanel.add(getRbInsert());
		contentPanel.add(getRbUpdate());
		contentPanel.add(getRbDelete());
		contentPanel.add(getBtnSearch());
		contentPanel.add(getTfSelection());
		contentPanel.add(getLblImage());
		contentPanel.add(getBtnOK());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getTfSeqno());
		contentPanel.add(getLblNewLabel_1_1());
		contentPanel.add(getTfBrand());
		contentPanel.add(getLblNewLabel_1_2());
		contentPanel.add(getTfName());
		contentPanel.add(getLblNewLabel_1_3());
		contentPanel.add(getTfPrice());
		contentPanel.add(getLblNewLabel_1_4());
		contentPanel.add(getTfColor());
		contentPanel.add(getLblNewLabel_1_5());
		contentPanel.add(getTfQty());
		contentPanel.add(getLblNewLabel_1_6());
		contentPanel.add(getCbSelection1());
		contentPanel.add(getRbSearch());
		contentPanel.add(getTfFilePath());
		contentPanel.add(getBtnFilePath());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnNewButton_1());
		contentPanel.add(getBtnNewButton_1_1());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectWhat();
				}

			});
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "모든품목", "브랜드", "제품명", "사이즈" }));
			cbSelection.setBounds(20, 55, 104, 27);
		}
		return cbSelection;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(19, 94, 490, 436);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}

	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("등록");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbInsert);
			rbInsert.setSelected(true);
			rbInsert.setBounds(20, 20, 62, 23);
		}
		return rbInsert;
	}

	private JTable getInner_Table() {
		if (inner_Table == null) {
			inner_Table = new JTable();
			inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			inner_Table.setModel(outerTable);
		}
		return inner_Table;
	}

	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(95, 20, 62, 23);
		}
		return rbUpdate;
	}

	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbDelete);
			rbDelete.setForeground(Color.RED);
			rbDelete.setBounds(169, 20, 62, 23);
		}
		return rbDelete;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnSearch.setBounds(302, 54, 117, 29);
		}
		return btnSearch;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(124, 54, 180, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon(ManagerPage.class.getResource("/com/javalec/image/사진.png")));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(523, 326, 255, 158);
		}
		return lblImage;
	}

	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("완료");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			btnOK.setBounds(585, 523, 117, 29);
		}
		return btnOK;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품번호");
			lblNewLabel_1.setBounds(523, 95, 61, 16);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfSeqno() {
		if (tfSeqno == null) {
			tfSeqno = new JTextField();
			tfSeqno.setEditable(false);
			tfSeqno.setBounds(598, 90, 80, 26);
			tfSeqno.setColumns(10);
		}
		return tfSeqno;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("브랜드명");
			lblNewLabel_1_1.setBounds(523, 128, 61, 16);
		}
		return lblNewLabel_1_1;
	}

	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setColumns(10);
			tfBrand.setBounds(598, 123, 152, 26);
		}
		return tfBrand;
	}

	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("제품명");
			lblNewLabel_1_2.setBounds(523, 161, 61, 16);
		}
		return lblNewLabel_1_2;
	}

	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(598, 156, 130, 26);
		}
		return tfName;
	}

	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("가격");
			lblNewLabel_1_3.setBounds(523, 194, 61, 16);
		}
		return lblNewLabel_1_3;
	}

	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setColumns(10);
			tfPrice.setBounds(598, 189, 104, 26);
		}
		return tfPrice;
	}

	private JLabel getLblNewLabel_1_4() {
		if (lblNewLabel_1_4 == null) {
			lblNewLabel_1_4 = new JLabel("색상");
			lblNewLabel_1_4.setBounds(523, 227, 61, 16);
		}
		return lblNewLabel_1_4;
	}

	private JTextField getTfColor() {
		if (tfColor == null) {
			tfColor = new JTextField();
			tfColor.setColumns(10);
			tfColor.setBounds(598, 222, 180, 26);
		}
		return tfColor;
	}

	private JLabel getLblNewLabel_1_5() {
		if (lblNewLabel_1_5 == null) {
			lblNewLabel_1_5 = new JLabel("재고");
			lblNewLabel_1_5.setBounds(523, 260, 61, 16);
		}
		return lblNewLabel_1_5;
	}

	private JTextField getTfQty() {
		if (tfQty == null) {
			tfQty = new JTextField();
			tfQty.setColumns(10);
			tfQty.setBounds(598, 255, 62, 26);
		}
		return tfQty;
	}

	private JLabel getLblNewLabel_1_6() {
		if (lblNewLabel_1_6 == null) {
			lblNewLabel_1_6 = new JLabel("사이즈");
			lblNewLabel_1_6.setBounds(523, 293, 61, 16);
		}
		return lblNewLabel_1_6;
	}

	private JComboBox getCbSelection1() {
		if (cbSelection1 == null) {
			cbSelection1 = new JComboBox();
			cbSelection1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			cbSelection1.setModel(new DefaultComboBoxModel(new String[] { "220", "225", "230", "235", "240", "245",
					"250", "255", "260", "265", "270", "275", "280", "285", "290", "295", "300" }));
			cbSelection1.setBounds(598, 287, 91, 27);
		}
		return cbSelection1;
	}

	private JRadioButton getRbSearch() {
		if (rbSearch == null) {
			rbSearch = new JRadioButton("검색");
			rbSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbSearch);
			rbSearch.setBounds(242, 20, 62, 23);
		}
		return rbSearch;
	}

	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			tfFilePath.setBounds(648, 496, 130, 26);
			tfFilePath.setColumns(10);
		}
		return tfFilePath;
	}

	private JButton getBtnFilePath() {
		if (btnFilePath == null) {
			btnFilePath = new JButton("파일 첨부");
			btnFilePath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filePath();
				}
			});
			btnFilePath.setToolTipText("");
			btnFilePath.setBounds(523, 496, 117, 29);
		}
		return btnFilePath;
	}

	// -----Function-----
	private void tableInit() {
		// 컬럼 정하기
		outerTable.addColumn("제품번호 ");
		outerTable.addColumn("브랜드명 ");
		outerTable.addColumn("제품명 ");
		outerTable.addColumn("가격 ");
		outerTable.addColumn("색상 ");
		outerTable.addColumn("재고 ");
		outerTable.addColumn("size ");

		outerTable.setColumnCount(7);
		// 컬럼 크기 정하기
		int colNo = 0;
		TableColumn col = inner_Table.getColumnModel().getColumn(colNo);
		int width = 50;
		col.setPreferredWidth(width);
		colNo = 1;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		colNo = 2;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		colNo = 3;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		colNo = 4;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 50;
		col.setPreferredWidth(width);
		colNo = 5;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 60;
		col.setPreferredWidth(width);
		colNo = 6;
		col = inner_Table.getColumnModel().getColumn(colNo);
		width = 50;

		inner_Table.setAutoResizeMode(inner_Table.AUTO_RESIZE_OFF);

		// Table 내용 지우기
		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);

		}

	}

	private void searchAction() {
		Dao_pjm dao = new Dao_pjm();
		ArrayList<Dto> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getP_seq());
			String[] qTxt = { temp, dtoList.get(i).getBrand(), dtoList.get(i).getName(), dtoList.get(i).getPrice(),
					dtoList.get(i).getColor(), dtoList.get(i).getQty(), dtoList.get(i).getSize() };
			outerTable.addRow(qTxt);
		}

	}

	private void actionPartition() {

		// 검색일 경우
		if (rbSearch.isSelected()) {
			screenPartition();
			tableInit();
			clearColumn();
		}

		// 입력일 경우
		if (rbInsert.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				tableInit();
				searchAction();
				insertAction();
				clearColumn();

			} else {
				JOptionPane.showMessageDialog(null, "데이터를 확인하세요1");
			}

			screenPartition();

		}
		// 수정일 경우
		if (rbUpdate.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				updateAction();
				tableInit();
				searchAction();
				clearColumn();

			} else {
				JOptionPane.showMessageDialog(null, "데이터를 확인하세요2");
			}

			screenPartition();
		}
		// 삭제일 경우
		if (rbDelete.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				deleteAction();
				tableInit();
				searchAction();
				clearColumn();
			} else {
				JOptionPane.showMessageDialog(null, "데이터를 확인하세요3");
			}
			screenPartition();
		}
	}

	private void screenPartition() {
		if (rbSearch.isSelected() == true) {
			btnOK.setVisible(false);
			tfBrand.setEditable(true);
			tfName.setEditable(true);
			tfPrice.setEditable(true);
			tfColor.setEditable(true);
			tfQty.setEditable(true);
			cbSelection1.setEditable(true);
			lblImage.setEnabled(true);

		}
		// 입력 버튼 선택후 클리어 컬럼

		if (rbInsert.isSelected()) {
			btnOK.setVisible(true);
			tfBrand.setEditable(true);
			tfName.setEditable(true);
			tfPrice.setEditable(true);
			tfColor.setEditable(true);
			cbSelection1.setEditable(true);
			lblImage.setEnabled(true);
			clearColumn();
		}
		// 수정 일 경우
		if (rbUpdate.isSelected()) {
			btnOK.setVisible(true);
			tfBrand.setEditable(true);
			tfName.setEditable(true);
			tfPrice.setEditable(true);
			tfColor.setEditable(true);
			cbSelection1.setEditable(true);
			lblImage.setEnabled(true);
		}
		// 삭제일 경우
		if (rbDelete.isSelected()) {
			btnOK.setVisible(true);
			tfBrand.setEditable(false);
			tfName.setEditable(false);
			tfPrice.setEditable(false);
			tfColor.setEditable(false);
			tfFilePath.setEditable(false);
			cbSelection1.setEditable(false);
		}

	}

	private void tableClick() {
		int i = inner_Table.getSelectedRow();
		String tkSequence = (String) inner_Table.getValueAt(i, 0);
		int wkSequence = Integer.parseInt(tkSequence);

		Dao_pjm dao = new Dao_pjm(wkSequence);
		Dto dto = dao.tableClick();

		tfSeqno.setText(Integer.toString(dto.getP_seq()));
		tfBrand.setText(dto.getBrand());
		tfName.setText(dto.getName());
		tfPrice.setText(dto.getPrice());
		tfColor.setText(dto.getColor());
		tfQty.setText(dto.getQty());
		cbSelection1.setSelectedItem(dto.getSize());
//		 Image 처리 : filename 이 틀려야 보여주기가 가능
		String filePath = Integer.toString(ShareVar.filename);
		tfFilePath.setText(filePath);
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);

		File file = new File(filePath);
		file.delete();

	}

	private void insertAction() {
		String brand = tfBrand.getText().trim();
		String name = tfName.getText().trim();
		String price = tfPrice.getText().trim();
		String color = tfColor.getText().trim();
		String qty = tfQty.getText().trim();
		String size = cbSelection1.getSelectedItem().toString();

		FileInputStream input = null;
		File file = new File(tfFilePath.getText());
		try {
			input = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Dao_pjm dao = new Dao_pjm(brand, name, price, color, qty, size, input);
		boolean result = dao.insertAction();

		if (result == true) {
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 입력되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "입력 중 문제가 발생하였습니다.");
			;
		}

	}

	private void updateAction() {
		int seqno = Integer.parseInt(tfSeqno.getText());
		String brand = tfBrand.getText().trim();
		String name = tfName.getText().trim();
		String price = tfPrice.getText().trim();
		String color = tfColor.getText().trim();
		String qty = tfQty.getText().trim();
		String size = cbSelection1.getSelectedItem().toString().trim();

		Dao_pjm dao = new Dao_pjm(seqno, brand, name, price, color, qty, size);
		boolean result = dao.updateAction();

		if (result == true) {
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 수정되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "수정 중 문제가 발생하였습니다.");
			;
		}

	}

	private void deleteAction() {

		Dao_pjm dao = new Dao_pjm(Integer.parseInt(tfSeqno.getText()));
		boolean result = dao.deleteAction();

		if (result == true) {
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 삭제되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "삭제 중 문제가 발생하였습니다.");

		}

	}

	private int insertFieldCheck() {
		int i = 0;
		if (tfBrand.getText().trim().length() == 0) {
			i++;
			tfBrand.requestFocus();
		}
		if (tfName.getText().trim().length() == 0) {
			i++;
			tfName.requestFocus();
		}
		if (tfPrice.getText().trim().length() == 0) {
			i++;
			tfPrice.requestFocus();
		}
		if (tfColor.getText().trim().length() == 0) {
			i++;
			tfColor.requestFocus();
		}
		if (tfQty.getText().trim().length() == 0) {
			i++;
			tfQty.requestFocus();
		}
		if (cbSelection1.getSelectedItem().toString().length() == 0) {
			i++;
			cbSelection1.requestFocus();
		}
		return i;
	}

	private void clearColumn() {
		tfSeqno.setText("");
		tfBrand.setText("");
		tfName.setText("");
		tfPrice.setText("");
		tfColor.setText("");
		tfQty.setText("");
		cbSelection1.setSelectedItem("");
		tfFilePath.setText("");
		lblImage.setIcon(new ImageIcon(ManagerPage.class.getResource("/com/javalec/image/사진.png")));

	}

	private void filePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "PNG", "BMP", "JPEG");
		chooser.setFileFilter(filter);

		int ret = chooser.showOpenDialog(null);

		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		tfFilePath.setText(filePath);
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);

	}

	private void searchName() {
		Dao_pjm dao = new Dao_pjm();
		ArrayList<Dto> dtoList = dao.searchName();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getP_seq());
			String[] qTxt = { temp, dtoList.get(i).getBrand(), dtoList.get(i).getName(), dtoList.get(i).getPrice(),
					dtoList.get(i).getColor(), dtoList.get(i).getQty(), dtoList.get(i).getSize() };
			outerTable.addRow(qTxt);
		}

	}

	private void selectWhat() {
		
			

	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(ManagerPage.class.getResource("/com/javalec/image/goToFirstPage.png")));
			btnNewButton.setBounds(635, 21, 45, 45);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(ManagerPage.class.getResource("/com/javalec/image/메인가기.png")));
			btnNewButton_1.setBounds(680, 21, 48, 45);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("");
			btnNewButton_1_1.setIcon(new ImageIcon(ManagerPage.class.getResource("/com/javalec/image/logout_new.png")));
			btnNewButton_1_1.setBounds(730, 20, 48, 45);
		}
		return btnNewButton_1_1;
	}
}// End
