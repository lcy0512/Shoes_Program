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
	 * Date : 2023.12.29 
	 * Author : 박지환 
	 * Update :
	 * 		1. page 중앙이동 및 색깔 통합  update by pdg
	 * 		2. 다른 화면 페이지 아이콘 추가 by pdg
	 * 		3. 뒤로가기 버튼  수정, exit 버튼 없앰. 
	 * 
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
				tableInit();
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
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "당일매출현황", "월별 매출현황" }));
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

		int i = outerTable.getRowCount();
		for (int j = 1; j <= i; j++) {
			outerTable.removeRow(0);

		}

	}

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

		} else if (cbSelection.getSelectedItem().toString().equals("월별 매출현황")) {
			Dao_pjh_CurrentSituation daoPjh = new Dao_pjh_CurrentSituation();
			ArrayList<Dto_pjh_CurrentSituation> dtoList = daoPjh.selecMonthList();

			int listCount = dtoList.size();
			for (int i = 0; i < listCount; i++) {
				String[] qTxt = { dtoList.get(i).getSdate(), Integer.toString(dtoList.get(i).getSprice()) };
				outerTable.addRow(qTxt);
			}
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
	
	
	private void backToManagerPage() {
		
		
		ManagerPage_ managerPage = new ManagerPage_();
		
		managerPage.setVisible(true);
		this.setVisible(false);
	}
}// end
