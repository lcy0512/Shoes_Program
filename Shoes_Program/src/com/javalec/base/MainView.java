package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_MainView;
import com.javalec.function.Dao_pjh_CurrentSituation;
import com.javalec.function.Dto_Mainview;
import com.javalec.function.Dto_pjh_CurrentSituation;
import com.javalec.function.ShareVar;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JScrollPane scrollPane;
	private JTable InnerTable;

	
	// table
			private final DefaultTableModel OuterTable = new DefaultTableModel();
//			private JLabel lblImage;
//			private JButton btnFilePath;
//			private JTextField tfFilePath;
			
			// -- file 정리
			ArrayList<Dto_Mainview> beanList = null;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
//				defaultAction();
				queryAction();          // <--***************************************************

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
			cbSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"Nike", "Adidas", "Newbalance"}));
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
		if (InnerTable == null) {
			InnerTable = new JTable() { 								// <--****************
				public Class getColumnClass(int column) { 				// <--****************
			        return (column == 0) ? Icon.class : Object.class; 	// <--****************
			      }
			};
			InnerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			InnerTable.setRowHeight(150); 		// <--***************************************************
			InnerTable.setModel(OuterTable); 	// <--***************************************************
		}
		return InnerTable;
	}
	
	private void tableInit() {
		// table column명 정하
		OuterTable.addColumn("상세사진");
		OuterTable.addColumn("브랜드");
		OuterTable.addColumn("상품이름");
		OuterTable.addColumn("가격");
		
		OuterTable.setColumnCount(4);
		// table column 크기 정하
		int colNo = 0;
		TableColumn col = InnerTable.getColumnModel().getColumn(colNo);
		int width = 300;
		col.setPreferredWidth(width);

		colNo = 1;
		col = InnerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		colNo = 2;
		col = InnerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		colNo = 3;
		col = InnerTable.getColumnModel().getColumn(colNo);
		width = 180;
		col.setPreferredWidth(width);
		

		

		InnerTable.setAutoResizeMode(InnerTable.AUTO_RESIZE_OFF); // 사이즈 고정이라서 스크롤바가 나온다

		int i = OuterTable.getRowCount();
		for (int j = 1; j <= i; j++) {
			OuterTable.removeRow(0);

		}

	}
	
	
	
	
	
	
	//초기 Table 이미지,브랜드,이름,가격 표시(윈도우 열릴때)
	private void queryAction(){
		Dao_MainView dbAction = new Dao_MainView();
		beanList = dbAction.dao_pjh_AllProductMainviews();
		
		int listCount = beanList.size();
		
		for (int index = 0; index < listCount; index++) {
			ImageIcon icon = new ImageIcon("./"+beanList.get(index).getFake_filename());
			Object[] tempData = {
					icon,
					beanList.get(index).getPbrand(),
					beanList.get(index).getPname(),
					beanList.get(index).getPprice()
					};
			OuterTable.addRow(tempData);
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//---------------------------end
