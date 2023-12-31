package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_pjh_CurrentSituation;
import com.javalec.function.Dto_pjh_CurrentSituation;
import com.javalec.function.ShareVar;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CurrentSituation extends JDialog {
	
	
	
	
	
	/*
	 * Description : 매출 현황 페이지  
<<<<<<< HEAD
	 * Date : 2023.12.29 
=======
	 * Date : 2023.12.31 
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	 * Author : 박지환 
	 * Update :
	 * 		1. page 중앙이동 및 색깔 통합  update by pdg
	 * 		2. 다른 화면 페이지 아이콘 추가 by pdg
	 * 		3. 뒤로가기 버튼  수정, exit 버튼 없앰. 
<<<<<<< HEAD
	 * 
=======
	 * 		4. 월별, 상품별, 브랜드별 매출현황 추가	
	 * 		5. innerTable Column갯수 변경
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	private JScrollPane scrollPane;
	private JTable innerTable;

	// table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnNewButton;

//		private JLabel lblImage;
//		private JButton btnFilePath;
//		private JTextField tfFilePath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CurrentSituation dialog = new CurrentSituation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CurrentSituation() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
<<<<<<< HEAD
				tableInit();
=======
				tableCulomn();
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
				brandSelectAction();
			}

			@Override
			public void windowClosing(WindowEvent e) {
		
			}
		});
		setBounds(ShareVar.position_window_x,ShareVar.position_window_y, ShareVar.window_size_x,ShareVar.window_size_x);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbSelection());
		contentPanel.add(getScrollPane());
		contentPanel.add(getBtnNewButton());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					brandSelectAction();
				}
			});
<<<<<<< HEAD
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "당일매출현황", "월별 매출현황" }));
=======
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"당일매출현황","일주일별 매출현황", "월별 매출현황",  "상품별 매출현황", "브랜드별 매출현황"}));
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
			cbSelection.setBounds(173, 46, 147, 27);
		}
		return cbSelection;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(52, 102, 672, 308);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
					}
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable); // 한세트로 움직이게

		}
		return innerTable;
	}

//-----------function--------------------

<<<<<<< HEAD
	private void tableInit() {
		// table column명 정하
		outerTable.addColumn("날짜");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(2);
//		TableColumn tc = outerTable.getColumnMo

		// table column 크기 정하
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 120;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF); // 사이즈 고정이라서 스크롤바가 나온다

=======
	private void tableCulomn() {
		tableInit();
		
		
	}
	
	//테이블 컬럼 만들기(코몹박스별로 컬럼값 다르게줌)
	private void tableInit() {
		
		if(cbSelection.getSelectedIndex() == 0) {
	    	String[] newColumnNames1 = {"날짜", "가격"};
            outerTable.setColumnIdentifiers(newColumnNames1);
	    }else if (cbSelection.getSelectedIndex() == 1) {
	    	String[] newColumnNames2 = {"날짜", "가격"};
            outerTable.setColumnIdentifiers(newColumnNames2);	    	
		} else if (cbSelection.getSelectedIndex() == 2) {
	    	String[] newColumnNames3 = {"날짜", "가격"};
            outerTable.setColumnIdentifiers(newColumnNames3);	    	
		}else if (cbSelection.getSelectedIndex() == 3) {
	    	String[] newColumnNames4 = {"브랜드", "상품이름","개당 가격","판매개수", "매출액"};
            outerTable.setColumnIdentifiers(newColumnNames4);	    	
		}else if (cbSelection.getSelectedIndex() == 4) {
	    	String[] newColumnNames5 = {"브랜드", "매출액"};
            outerTable.setColumnIdentifiers(newColumnNames5);	    	
		}
	    
	    
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		int i = outerTable.getRowCount();
		for (int j = 1; j <= i; j++) {
			outerTable.removeRow(0);

		}

	}

<<<<<<< HEAD
//	private void searchAction() { // 버튼 눌렀을때 액션
//		String QuerySearchName ="";
//		
//		if (cbSelection.getSelectedItem().toString().equals("당일매출현황")) {
//			QuerySearchName="date";
//		}else if (cbSelection.getSelectedItem().toString().equals("월별 매출현황")) {
//			QuerySearchName="date";
//		}
//		tableInit();
////		QueryAction(QuerySearchName);  //다오에서 계산 식 만들기
//		
//	}

=======
	//콤보박스 선택시 innerTable에 dao에서 불러온 값 넣기
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	private void brandSelectAction() { 
		tableInit();
		if (cbSelection.getSelectedItem().toString().equals("당일매출현황")) {
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selectList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { dtoList.get(i).getSdate(), Integer.toString(dtoList.get(i).getSprice()) };
				outerTable.addRow(qTxt);

			}

<<<<<<< HEAD
		} else if (cbSelection.getSelectedItem().toString().equals("월별 매출현황")) {
=======
		} else if (cbSelection.getSelectedItem().toString().equals("일주일별 매출현황")) {
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selectWeekList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { dtoList.get(i).getSdate(), Integer.toString(dtoList.get(i).getSprice()) };
				outerTable.addRow(qTxt);
			}
		}else if (cbSelection.getSelectedItem().toString().equals("월별 매출현황")) {
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selecMonthList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { dtoList.get(i).getSdate(), Integer.toString(dtoList.get(i).getSprice()) };
				outerTable.addRow(qTxt);
			}
<<<<<<< HEAD
=======
		}else if (cbSelection.getSelectedItem().toString().equals("상품별 매출현황")) {
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selectProductList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { 
						dtoList.get(i).getPbrand(), 
						dtoList.get(i).getPname(), 
						Integer.toString(dtoList.get(i).getPprice()),
						Integer.toString(dtoList.get(i).getSqty()),
						Integer.toString(dtoList.get(i).getTotalprice())
				};
				outerTable.addRow(qTxt);
			}
		}else if (cbSelection.getSelectedItem().toString().equals("브랜드별 매출현황")) {
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selectBrandList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { dtoList.get(i).getSdate(), Integer.toString(dtoList.get(i).getSprice()) };
				outerTable.addRow(qTxt);
			}
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		}

	}


	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("뒤로가기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				backToManagerPage();
				}
			});
			btnNewButton.setBounds(44, 45, 117, 29);
		}
		return btnNewButton;
	}
	
<<<<<<< HEAD
	
=======
	//뒤로가기버튼 클릭시 메니저 페이지로 이동
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	private void backToManagerPage() {
		
		
		ManagerPage_ managerPage = new ManagerPage_();
		
		managerPage.setVisible(true);
		this.setVisible(false);
	}
}// end
