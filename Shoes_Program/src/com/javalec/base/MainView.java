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
	private JTable innerTable;

	
	// table
			private final DefaultTableModel outerTable = new DefaultTableModel();
//			private JLabel lblImage;
//			private JButton btnFilePath;
//			private JTextField tfFilePath;
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
				defaultAction();
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
					brandSelectAction();
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
	
	private void tableInit() {
		// table column명 정하
		outerTable.addColumn("brand");
		outerTable.addColumn("name");
		outerTable.addColumn("price");
		outerTable.addColumn("image");
		
		outerTable.setColumnCount(4);
		// table column 크기 정하
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 300;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		

		

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF); // 사이즈 고정이라서 스크롤바가 나온다

		int i = outerTable.getRowCount();
		for (int j = 1; j <= i; j++) {
			outerTable.removeRow(0);

		}

	}
	
	private void defaultAction() {		//윈도우 킬때 액션
		
		
		
		
		//이미지 처리 : filename이틀려야 보여주기가 가능
//		String filePath = Integer.toString(ShareVar.image);
//		ImageIcon daumImg = new  ImageIcon(filePath);
//		
		
		
		
		//메인뷰에 대한 다오를 쓰겠다
		Dao_MainView daoPjhMainView = new Dao_MainView();
		ArrayList<Dto_Mainview> dtoAllProductTablelist = daoPjhMainView.dao_pjh_Mainviews();

		int listCount = dtoAllProductTablelist.size();
		for (int i = 0; i < listCount; i++) {
//			String temp = Integer.toString(dtoList.get(i).getSseq()); // 어레이 리스트에서시퀀스를 가져온다 그래서 get이 두
			String[] currentListElement = {dtoAllProductTablelist.get(i).getPbrand(),
					dtoAllProductTablelist.get(i).getPname(),
					Integer.toString(dtoAllProductTablelist.get(i).getPprice()),
					
			
			 
			};
			outerTable.addRow(currentListElement);
		
		}
//			InnerTable.setIcon(new ImageIcon(filePath));
//			
//			System.out.println("adsfasdf");
//			System.out.println(daumImg);
//		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void brandSelectAction() {
		tableInit();
		if (cbSelection.getSelectedItem().toString().equals("Nike")) {
			Dao_MainView daoPjhMainNikeView = new Dao_MainView();
			ArrayList<Dto_Mainview> dtoNikeProductTablelist = daoPjhMainNikeView.dao_pjh_MainNikeviews();
			int listCount = dtoNikeProductTablelist.size();
			for (int i = 0; i < listCount; i++) {
				String[] currentNikeListElement = {dtoNikeProductTablelist.get(i).getPbrand(),
						dtoNikeProductTablelist.get(i).getPname(),
						Integer.toString(dtoNikeProductTablelist.get(i).getPprice()) };
				outerTable.addRow(currentNikeListElement);
			}
		
		}
		else if(cbSelection.getSelectedItem().toString().equals("Adidas")) {
			Dao_MainView daoPjhMainAdidasView = new Dao_MainView();
			ArrayList<Dto_Mainview> dtoAdidasProductTablelist = daoPjhMainAdidasView.dao_pjh_MainNikeviews();
			
			int listCount = dtoAdidasProductTablelist.size();
			for (int i = 0; i < listCount; i++) {
				String[] currentAdidasListElement = {dtoAdidasProductTablelist.get(i).getPbrand(),
						dtoAdidasProductTablelist.get(i).getPname(),
						Integer.toString(dtoAdidasProductTablelist.get(i).getPprice()) };
				outerTable.addRow(currentAdidasListElement);
			}
			
		}else if (cbSelection.getSelectedItem().toString().equals("Newbalance")) {
			Dao_MainView daoPjhMainNewbalanceView = new Dao_MainView();
			ArrayList<Dto_Mainview> dtoNewbalanceProductTablelist = daoPjhMainNewbalanceView.dao_pjh_MainNikeviews();
			
			int listCount = dtoNewbalanceProductTablelist.size();
			for (int i = 0; i < listCount; i++) {
				String[] currentNewbalanceListElement = {dtoNewbalanceProductTablelist.get(i).getPbrand(),
						dtoNewbalanceProductTablelist.get(i).getPname(),
						Integer.toString(dtoNewbalanceProductTablelist.get(i).getPprice()) };
				outerTable.addRow(currentNewbalanceListElement);
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
