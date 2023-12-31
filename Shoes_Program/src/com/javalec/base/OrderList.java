package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;

<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
<<<<<<< HEAD
import javax.swing.table.DefaultTableCellRenderer;
=======
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_lcy;
import com.javalec.function.Dto_lcy;
import com.javalec.function.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

<<<<<<< HEAD
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
=======
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class OrderList extends JDialog {
	
	/*
<<<<<<< HEAD
	 * Descritipon : 주문 내역을 보여주는 Class. 사용자에게는 확인하는 곳이기에 Only Read.
	 * 				 Table을 통해 구매한 제품들의 상세내역을 보여준다.
	 * 
	 * Author : Lcy
	 * 
	 * Date : 2023-12-30 , 16:27
	 */
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
=======
	 * Descritipon : 
	 * 
	 * Author : Lcy
	 * 
	 * Date : 2023-12-28 , 17:21
	 *  		
	 *  		
	 */
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblNewLabel_1;
	private JButton btnMoveMainView;
<<<<<<< HEAD
	private JButton btnMoveLogOut;
	private JButton btnLogo;
	static ManagerPage_ managerDialog = new ManagerPage_();
=======
	
	
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	static OrderList buyDialog = new OrderList();
	
	public static void main(String[] args) {
		try {
			buyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			buyDialog.setLocation(ShareVar.position_window_x,ShareVar.position_window_y);
			buyDialog.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
			buyDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OrderList() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchActionInOrderList();
			}
		});
		setTitle("장바구니");
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getBtnMoveMainView());
<<<<<<< HEAD
		contentPanel.add(getBtnMoveLogOut());
		contentPanel.add(getBtnLogo());
=======
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
<<<<<<< HEAD
			scrollPane.setBounds(20, 105, 741, 350);
=======
			scrollPane.setBounds(43, 130, 711, 350);
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
<<<<<<< HEAD
			// Table내의 cell Data 오른쪽 정렬
			DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
			cellAlignRight.setHorizontalAlignment(SwingConstants.CENTER); 
			for(int i=0; i<innerTable.getColumnCount(); i++) {
				innerTable.getColumnModel().getColumn(i).setCellRenderer(cellAlignRight);
			}
=======
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
			innerTable.setRowHeight(70);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
<<<<<<< HEAD
			lblNewLabel_1 = new JLabel("History");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 40));
			lblNewLabel_1.setBounds(318, 10, 200, 80);
=======
			lblNewLabel_1 = new JLabel("주문 내역");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 40));
			lblNewLabel_1.setBounds(310, 6, 200, 80);
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		}
		return lblNewLabel_1;
	}
	
	private JButton getBtnMoveMainView() {
		if (btnMoveMainView == null) {
<<<<<<< HEAD
			btnMoveMainView = new JButton("");
			btnMoveMainView.setIcon(new ImageIcon(OrderList.class.getResource("/com/javalec/image/메인가기.png")));
			btnMoveMainView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goMain();
				}
			});
			btnMoveMainView.setBounds(665, 19, 45, 45);
		}
		return btnMoveMainView;
	}
	
	private JButton getBtnMoveLogOut() {
		if (btnMoveLogOut == null) {
			btnMoveLogOut = new JButton("");
			btnMoveLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goHome();
				}
			});
			btnMoveLogOut.setBounds(715, 19, 45, 45);
			btnMoveLogOut.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/logout_new.png")));
		}
		return btnMoveLogOut;
	}
	
	private JButton getBtnLogo() {
		if(btnLogo == null) {
			btnLogo = new JButton("");
			btnLogo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goMain();
				}
			});
			btnLogo.setBounds(20, 25, 225, 45);
			btnLogo.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/JUNES_LOGO.png")));
		}
		return btnLogo;
	}
	
=======
			btnMoveMainView = new JButton("Main");
			btnMoveMainView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyDialog.setVisible(false);
					MainView mainView  = new MainView();
					mainView.setVisible(true);
				}
			});
			btnMoveMainView.setBounds(678, 22, 61, 46);
		}
		return btnMoveMainView;
	}

>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
	// ========= Functions ============
	
	private void tableInit() { // Table 초기화 
		// Table Coulmn명 설정
		outerTable.addColumn("제품번호");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("색상");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("수량");
		outerTable.addColumn("구매일");
		outerTable.setColumnCount(8);
		
		// Table Column 크기 설정
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 60;
		col.setPreferredWidth(width);
		
		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 95;
		col.setPreferredWidth(width);
		
		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
<<<<<<< HEAD
		width = 127;
=======
		width = 115;
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		col.setPreferredWidth(width);
		
		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 85;
		col.setPreferredWidth(width);
		
		colNo = 4;
		col = innerTable.getColumnModel().getColumn(colNo);
<<<<<<< HEAD
		width = 85;
=======
		width = 75;
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		col.setPreferredWidth(width);
		
		colNo = 5;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 90;
		col.setPreferredWidth(width);
		
		colNo = 6;
		col = innerTable.getColumnModel().getColumn(colNo);
<<<<<<< HEAD
		width = 68;
=======
		width = 50;
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		col.setPreferredWidth(width);
		
		colNo = 7;
		col = innerTable.getColumnModel().getColumn(colNo);
<<<<<<< HEAD
		width = 128;
=======
		width = 120;
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		col.setPreferredWidth(width);
		
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
		// Table 내용 지우기 
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerTable.removeRow(0);
		}
	}
	
<<<<<<< HEAD
	private void searchActionInOrderList() { // 검색 (Database에서 Table로 불러오기) 
=======
	private void searchActionInOrderList() { // 검색(Database에서 Table로 불러오기) 
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
		Dao_lcy dao = new Dao_lcy();
		ArrayList<Dto_lcy> dtoList = dao.showOrderList(); 
		int listCount = dtoList.size();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0; i<listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getP_seq());
			String[] qTxt = {temp, dtoList.get(i).getBrand(), 
								   dtoList.get(i).getName(), 
								   dtoList.get(i).getPrice(), 
								   dtoList.get(i).getColor(), 
								   Integer.toString(dtoList.get(i).getSize()),
								   Integer.toString(dtoList.get(i).getSaveQty()),
								   dateFormat.format(dtoList.get(i).getDate())
			};
				outerTable.addRow(qTxt);
		}
	}
	
<<<<<<< HEAD
	private void goHome() { // FirstPage로 이동 
		FirstPage first = new FirstPage();
		managerDialog.setVisible(false);
		first.setVisible(true);
		dispose();
		this.setVisible(false);
	}
	
	private void goMain() { // MainView로 이동 
		buyDialog.setVisible(false);
		MainView mainView = new MainView();
		mainView.setVisible(true);
	}
	
=======
>>>>>>> b011fe67131965f76060f500d4de6f5f9e80d4db
} // End
