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
import com.javalec.function.Dto_pjm;
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
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

/*
* Descritipon : 관리자 페이지
* Author : PJM
* Date : 23.12.30
* update : 23.12.30 15:45
*/

public class ManagerPage_ extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	private JScrollPane scrollPane;
	private JRadioButton rbInsert;
	private JTable inner_Table;
	private JRadioButton rbUpdate;
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
	private JButton btnLogOut;
	private JButton btnMainView;
	static ManagerPage_ managerDialog = new ManagerPage_();
	private JButton btnCurrentSituation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			managerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			managerDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	/// PDG adding images and icon
	public ManagerPage_() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setTitle("관리자 페이지");
		// setBounds(100, 100, 800, 600);
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y, ShareVar.window_size_x,
				ShareVar.window_size_y);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbSelection());
		contentPanel.add(getScrollPane());
		contentPanel.add(getRbInsert());
		contentPanel.add(getRbUpdate());
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
		contentPanel.add(getBtnLogOut());
		contentPanel.add(getBtnMainView());
		contentPanel.add(getBtnCurrentSituation());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					allProduct();
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				}
			});
			cbSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					allSearchAction();
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
			lblImage.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/사진.png")));
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
			rbSearch.setBounds(169, 20, 62, 23);
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

	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton("");
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					goHome();
				}
			});
			btnLogOut.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/logout_new.png")));
			btnLogOut.setBounds(730, 33, 45, 45);
		}
		return btnLogOut;
	}

	private JButton getBtnMainView() {
		if (btnMainView == null) {
			btnMainView = new JButton("");
			btnMainView.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/메인가기.png")));
			btnMainView.setBounds(618, 33, 48, 45);
		}
		return btnMainView;
	}

	private JButton getBtnCurrentSituation() {
		if (btnCurrentSituation == null) {
			btnCurrentSituation = new JButton("");
			btnCurrentSituation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					goCurrentSituation();

				}
			});
			btnCurrentSituation.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/현황.png")));
			btnCurrentSituation.setBounds(673, 33, 45, 45);
		}
		return btnCurrentSituation;
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

	// Function ---- pdg add

	private void goHome() {
		FirstPage first = new FirstPage();
		managerDialog.setVisible(false);
		first.setVisible(true);
		dispose();
		this.setVisible(false);
	}

	private void goCurrentSituation() {

		CurrentSituation currentSituation = new CurrentSituation();
		currentSituation.setVisible(false);
		currentSituation.setVisible(true);
		dispose();
		this.setVisible(false);

	}

	// -----Function----- 박정민
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
	// 프로그램 실행시 기본적으로 윈도우에 보여주는 기능
	private void searchAction() {
		Dao_pjm dao = new Dao_pjm();
		ArrayList<Dto_pjm> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getP_seq());
			String[] qTxt = { temp, dtoList.get(i).getBrand(), dtoList.get(i).getName(),
					Integer.toString(dtoList.get(i).getPrice()), dtoList.get(i).getColor(),
					Integer.toString(dtoList.get(i).getQty()), Integer.toString(dtoList.get(i).getSize()) };
			outerTable.addRow(qTxt);
		}

	}
	// 라디오 버튼을 누르고 검색버튼을 눌렀을때 등록 수정 검색 테이블을 비워주는  기능
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
	}
	// 등록 수정 검색버튼을 눌렀을때 화면에 보여주는 기능
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

	}
	// innertable 클릭시 table 안에있는 데이터 부여주기
	private void tableClick() {
		int i = inner_Table.getSelectedRow();
		String tkSequence = (String) inner_Table.getValueAt(i, 0);
		int wkSequence = Integer.parseInt(tkSequence);

		Dao_pjm dao = new Dao_pjm(wkSequence);
		Dto_pjm dto = dao.tableClick();

		tfSeqno.setText(Integer.toString(dto.getP_seq()));
		tfBrand.setText(dto.getBrand());
		tfName.setText(dto.getName());
		tfPrice.setText(Integer.toString(dto.getPrice()));
		tfColor.setText(dto.getColor());
		tfQty.setText(Integer.toString(dto.getQty()));
		cbSelection1.setSelectedItem(dto.getSize());
//		 Image 처리 : filename 이 틀려야 보여주기가 가능
		String filePath = Integer.toString(ShareVar.filename);
		tfFilePath.setText(filePath);
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);

		File file = new File(filePath);
		file.delete();

	}
	// 등록 기능
	private void insertAction() {
		String brand = tfBrand.getText().trim();
		String name = tfName.getText().trim();
		int price = Integer.parseInt(tfPrice.getText());
		String color = tfColor.getText().trim();
		int qty = Integer.parseInt(tfQty.getText());
		int size = Integer.parseInt(cbSelection1.getSelectedItem().toString().trim());

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
	// 수정 기능
	private void updateAction() {
		int seqno = Integer.parseInt(tfSeqno.getText());
		String brand = tfBrand.getText().trim();
		String name = tfName.getText().trim();
		int price = Integer.parseInt(tfPrice.getText());
		String color = tfColor.getText().trim();
		int qty = Integer.parseInt(tfQty.getText());
		int size = Integer.parseInt(cbSelection1.getSelectedItem().toString().trim());

		Dao_pjm dao = new Dao_pjm(seqno, brand, name, price, color, qty, size);
		boolean result = dao.updateAction();
		if (result == true) {
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 수정되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "수정 중 문제가 발생하였습니다.");
			;
		}

	}
	// 등록할때 비어있으면 안돼게
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
	// 칼럼 클리어
	private void clearColumn() {
		tfSeqno.setText("");
		tfBrand.setText("");
		tfName.setText("");
		tfPrice.setText("");
		tfColor.setText("");
		tfQty.setText("");
		cbSelection1.setSelectedItem("");
		tfFilePath.setText("");
		lblImage.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/사진.png")));

	}
	// 파일 찾아오는 기능
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
	// 콤보박스 안에 있는아이템을 선택하고 그 아이템에 맞는 브랜드 품목 사이즈 검색 기능
	private void allSearchAction() {
			tableInit();
			String selectedProduct = cbSelection.getSelectedItem().toString();
			String searchText = tfSelection.getText();
			
			
			Dao_pjm dao = new Dao_pjm();
			ArrayList<Dto_pjm> dtoList = dao.allSearchAction(selectedProduct, searchText);

			int listCount = dtoList.size();

			for (int i = 0; i < listCount; i++) {
				String temp = Integer.toString(dtoList.get(i).getP_seq());
				String[] qTxt = { temp, dtoList.get(i).getBrand(), dtoList.get(i).getName(),
						Integer.toString(dtoList.get(i).getPrice()), dtoList.get(i).getColor(),
						Integer.toString(dtoList.get(i).getQty()), Integer.toString(dtoList.get(i).getSize()) };
				outerTable.addRow(qTxt);
			}

		}
	// 콤보 박스에서 모든 제품 선택시 모든 제품 보여주기
	private void allProduct() {
		if(cbSelection.getSelectedIndex() == 0) {
			tableInit();
			searchAction();
		}
		
	}


	}// End


