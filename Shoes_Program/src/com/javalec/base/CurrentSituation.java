package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_pjh;
import com.javalec.function.Dto;
import com.javalec.function.Dto_pjh;

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

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JScrollPane scrollPane;
	private JTable innerTable;
	
	
	// table
		private final DefaultTableModel outerTable = new DefaultTableModel();
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
				searchAction();
			}
		});
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbSelection());
		contentPanel.add(getTfSelection());
		contentPanel.add(getBtnQuery());
		contentPanel.add(getScrollPane());
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
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"당일매출현황", "주차별 매출현황", "월별 매출현황", "상품별 매출현황", "재고현황"}));
			cbSelection.setBounds(41, 39, 134, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(221, 38, 303, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchAction();
					
				}
			});
			btnQuery.setBounds(607, 38, 117, 29);
		}
		return btnQuery;
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
					if(e.getButton()==1) {
					}
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable); // 한세트로 움직이게

		}
		return innerTable;
	}
//-----------function
	private void tableInit() {
		// table column명 정하
		outerTable.addColumn("날짜");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(2);
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

	
	
	
	private void searchAction() {		//버튼 눌렀을때 액션(여기에 이 들어가야)
		
		
		
		Dao_pjh daoPjh = new Dao_pjh();
		ArrayList<Dto_pjh> dtoList = daoPjh.selectList();

		int listCount = dtoList.size();
		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getSseq()); // 어레이 리스트에서시퀀스를 가져온다 그래서 get이 두
			String[] qTxt = {dtoList.get(i).getSdate(),Integer.toString(dtoList.get(i).getSprice())};
			outerTable.addRow(qTxt);
		}
	}
	
	
	
	
	
//	private void conditionQuery() {
//		int i = cbSelection.getSelectedIndex();
//		String conditionQueryName = "";
//		
//		if(i==0) {
//			conditionQueryName = "name";
//		}else if (i ==1) {
//			conditionQueryName = "name";
//			
//		}
//	}
		
	



//	private void conditionQuery() {
//		int i = cbSelection.getSelectedIndex();
//		String conditionQueryName = "";
//
//		switch (i) {
//		case 0:
//			conditionQueryName = "name";
//			break;
//		case 1:
//			conditionQueryName = "adress";
//			break;
//		case 2:
//			conditionQueryName = "relation";
//			break;
//		default:
//			break;
//
//		}
//		tableInit();
//		conditionQueryAction(conditionQueryName);
//	}





























}//end
