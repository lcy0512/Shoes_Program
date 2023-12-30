package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.Dao_lcy;
import com.javalec.function.Dto_lcy;
import com.javalec.function.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class BuyList extends JDialog {

	/*
	 * Descritipon : 장바구니 탭을 Class. 장바구니에 담은 목록들은 기본적으로 Table에 나열하여 보여준다. 
	 * 				 만약 사용자가 어떤 목록을 클릭하였다면 그 목록에 따른 이미지 및 상세 내역을 Table 밑에서 보여준다. 
	 * 				 사용자는 기본적으로 한 번에 한 개의 물품만을 구매할 수 있다. -> [개선필요]
	 * 
	 * - [구매 확정] 버튼을 눌렀을 때 : 새로운 Dialog 생성 -> 총 가격을 보여주고, 구매를 확정할 것인지를 선택함.
	 * 							  만약 상세내역 Table 내에 아무런 Row가 없으면 OrderList로 이동.
	 * 
	 * - [목록 삭제] 버튼을 눌렀을 때 : 새로운 Dialog 생성 -> 삭제를 할 것인지 재확인
	 * 							  만약 상세내역 Table 내에 아무런 Row가 없으면 MainView로 이동.
	 * 
	 * Author : Lcy
	 * 
	 * Date : 2023-12-30 , 16:27
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JButton btnDeleteList;
	private JButton btnBuyConfirm;
	private JTextField tfSelectedProductSeqNo;
	private JLabel lblNewLabel_1;
	private JTextField tfSelectedProductRemainQty;
	private JLabel lblProductImage;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_3_1;
	private JTextField tfSelectedProductBrand;
	private JLabel lblNewLabel_3_1_1;
	private JLabel lblNewLabel_3_1_2;
	private JTextField tfSelectedProductPrice;
	private JLabel lblNewLabel_3_1_3;
	private JTextField tfSelectedProductColor;
	private JLabel lblNewLabel_3_1_4;
	private JTextField tfSelectedProductSize;
	private JLabel lblNewLabel_3_1_2_1;
	private JLabel lblNewLabel_3_1_2_2;
	private JTextField tfSelectedAllPrice;
	private JTextField tfSelectedProductName;
	private JLabel lblNewLabel_3_1_2_1_1;
	private JComboBox cbSelectedQty;
	private JButton btnMoveMainView;
	private JButton btnMoveLogOut;
	private JButton btnLogo;
	static ManagerPage_ managerDialog = new ManagerPage_();
	static BuyList buyDialog = new BuyList();

	public static void main(String[] args) {
		try {
			buyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			buyDialog.setLocation(ShareVar.position_window_x, ShareVar.position_window_y);
			buyDialog.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
			buyDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BuyList() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
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
		contentPanel.add(getBtnDeleteList());
		contentPanel.add(getBtnBuyConfirm());
		contentPanel.add(getTfSelectedProductSeqNo());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getTfSelectedProductRemainQty());
		contentPanel.add(getLblProductImage());
		contentPanel.add(getLblNewLabel_3());
		contentPanel.add(getLblNewLabel_3_1());
		contentPanel.add(getTfSelectedProductBrand());
		contentPanel.add(getLblNewLabel_3_1_1());
		contentPanel.add(getLblNewLabel_3_1_2());
		contentPanel.add(getTfSelectedProductPrice());
		contentPanel.add(getLblNewLabel_3_1_3());
		contentPanel.add(getTfSelectedProductColor());
		contentPanel.add(getLblNewLabel_3_1_4());
		contentPanel.add(getTfSelectedProductSize());
		contentPanel.add(getLblNewLabel_3_1_2_1());
		contentPanel.add(getLblNewLabel_3_1_2_2());
		contentPanel.add(getTfSelectedAllPrice());
		contentPanel.add(getTfSelectedProductName());
		contentPanel.add(getLblNewLabel_3_1_2_1_1());
		contentPanel.add(getCbSelectedQty());
		contentPanel.add(getBtnMoveMainView());
		contentPanel.add(getBtnMoveLogOut());
		contentPanel.add(getBtnLogo());
	}
	
	private JScrollPane getScrollPane() { // ScrollPane 구성
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 80, 742, 140);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() { // InnerTable 구성
		if (innerTable == null) {
			innerTable = new JTable();
			// Table내의 cell Data 오른쪽 정렬
			DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
			cellAlignRight.setHorizontalAlignment(SwingConstants.RIGHT);
			for(int i=0; i<innerTable.getColumnCount(); i++) {
				innerTable.getColumnModel().getColumn(i).setCellRenderer(cellAlignRight); 
			}
			innerTable.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						tableClick();
					}
				}

			});
			innerTable.setRowHeight(30);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}

	private JButton getBtnDeleteList() {
		if (btnDeleteList == null) {
			btnDeleteList = new JButton("목록 삭제");
			btnDeleteList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (tfSelectedProductSeqNo.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "목록이 선택되지 않았습니다!");
						} else {
							deleteReConfirm();
						}
					} catch (Exception q) {
						JOptionPane.showMessageDialog(null, "목록이 선택되지 않았습니다!");
					}
				}
			});

			btnDeleteList.setBounds(475, 515, 117, 29);
		}
		return btnDeleteList;
	}

	private JButton getBtnBuyConfirm() {
		if (btnBuyConfirm == null) {
			btnBuyConfirm = new JButton("구매 확정");
			btnBuyConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (tfSelectedProductSeqNo.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "목록이 선택되지 않았습니다!");
						} else if (cbSelectedQty.getSelectedItem().toString().equals("0")) {
							JOptionPane.showMessageDialog(null, "수량을 선택해주세요!");
						} else {
							buyReConfirm();
						}
					} catch (Exception q) {
						JOptionPane.showMessageDialog(null, "목록이 선택되지 않았습니다!");
					}
				}
			});

			btnBuyConfirm.setBounds(637, 515, 117, 29);
		}
		return btnBuyConfirm;
	}

	private JTextField getTfSelectedProductSeqNo() {
		if (tfSelectedProductSeqNo == null) {
			tfSelectedProductSeqNo = new JTextField();
			tfSelectedProductSeqNo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductSeqNo.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductSeqNo.setEditable(false);
			tfSelectedProductSeqNo.setBounds(393, 250, 71, 26);
			tfSelectedProductSeqNo.setColumns(10);
		}
		return tfSelectedProductSeqNo;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Shopping Cart");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			lblNewLabel_1.setBounds(292, 5, 300, 80);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfSelectedProductRemainQty() {
		if (tfSelectedProductRemainQty == null) {
			tfSelectedProductRemainQty = new JTextField();
			tfSelectedProductRemainQty.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductRemainQty.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductRemainQty.setEditable(false);
			tfSelectedProductRemainQty.setBounds(634, 348, 130, 26);
			tfSelectedProductRemainQty.setColumns(10);
		}
		return tfSelectedProductRemainQty;
	}

	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			lblProductImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductImage.setBounds(43, 220, 220, 250);
			lblProductImage.setIcon(new ImageIcon(BuyList.class.getResource(("/com/javalec/image/사진.png"))));
		}
		return lblProductImage;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("제품번호  :");
			lblNewLabel_3.setBounds(310, 255, 61, 16);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("브랜드명  :");
			lblNewLabel_3_1.setBounds(310, 308, 61, 16);
		}
		return lblNewLabel_3_1;
	}

	private JTextField getTfSelectedProductBrand() {
		if (tfSelectedProductBrand == null) {
			tfSelectedProductBrand = new JTextField();
			tfSelectedProductBrand.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductBrand.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductBrand.setEditable(false);
			tfSelectedProductBrand.setBounds(393, 303, 130, 26);
			tfSelectedProductBrand.setColumns(10);
		}
		return tfSelectedProductBrand;
	}

	private JLabel getLblNewLabel_3_1_1() {
		if (lblNewLabel_3_1_1 == null) {
			lblNewLabel_3_1_1 = new JLabel("제품명  :");
			lblNewLabel_3_1_1.setBounds(310, 353, 61, 16);
		}
		return lblNewLabel_3_1_1;
	}

	private JLabel getLblNewLabel_3_1_2() {
		if (lblNewLabel_3_1_2 == null) {
			lblNewLabel_3_1_2 = new JLabel("가격  :");
			lblNewLabel_3_1_2.setBounds(310, 405, 61, 16);
		}
		return lblNewLabel_3_1_2;
	}

	private JTextField getTfSelectedProductPrice() {
		if (tfSelectedProductPrice == null) {
			tfSelectedProductPrice = new JTextField();
			tfSelectedProductPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductPrice.setEditable(false);
			tfSelectedProductPrice.setColumns(10);
			tfSelectedProductPrice.setBounds(393, 397, 130, 26);
		}
		return tfSelectedProductPrice;
	}

	private JLabel getLblNewLabel_3_1_3() {
		if (lblNewLabel_3_1_3 == null) {
			lblNewLabel_3_1_3 = new JLabel("색상  :");
			lblNewLabel_3_1_3.setBounds(551, 255, 61, 16);
		}
		return lblNewLabel_3_1_3;
	}

	private JTextField getTfSelectedProductColor() {
		if (tfSelectedProductColor == null) {
			tfSelectedProductColor = new JTextField();
			tfSelectedProductColor.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductColor.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductColor.setEditable(false);
			tfSelectedProductColor.setColumns(10);
			tfSelectedProductColor.setBounds(634, 250, 130, 26);
		}
		return tfSelectedProductColor;
	}

	private JLabel getLblNewLabel_3_1_4() {
		if (lblNewLabel_3_1_4 == null) {
			lblNewLabel_3_1_4 = new JLabel("사이즈  :");
			lblNewLabel_3_1_4.setBounds(551, 303, 61, 16);
		}
		return lblNewLabel_3_1_4;
	}

	private JTextField getTfSelectedProductSize() {
		if (tfSelectedProductSize == null) {
			tfSelectedProductSize = new JTextField();
			tfSelectedProductSize.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductSize.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductSize.setEditable(false);
			tfSelectedProductSize.setColumns(10);
			tfSelectedProductSize.setBounds(634, 298, 130, 26);
		}
		return tfSelectedProductSize;
	}

	private JLabel getLblNewLabel_3_1_2_1() {
		if (lblNewLabel_3_1_2_1 == null) {
			lblNewLabel_3_1_2_1 = new JLabel("남은 재고  :");
			lblNewLabel_3_1_2_1.setBounds(551, 353, 61, 16);
		}
		return lblNewLabel_3_1_2_1;
	}

	private JLabel getLblNewLabel_3_1_2_2() {
		if (lblNewLabel_3_1_2_2 == null) {
			lblNewLabel_3_1_2_2 = new JLabel("총 가격  :");
			lblNewLabel_3_1_2_2.setBounds(506, 464, 61, 16);
		}
		return lblNewLabel_3_1_2_2;
	}

	private JTextField getTfSelectedAllPrice() {
		if (tfSelectedAllPrice == null) {
			tfSelectedAllPrice = new JTextField();
			tfSelectedAllPrice.setText("0");
			tfSelectedAllPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedAllPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedAllPrice.setEditable(false);
			tfSelectedAllPrice.setColumns(10);
			tfSelectedAllPrice.setBounds(589, 459, 130, 26);
		}
		return tfSelectedAllPrice;
	}

	private JTextField getTfSelectedProductName() {
		if (tfSelectedProductName == null) {
			tfSelectedProductName = new JTextField();
			tfSelectedProductName.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			tfSelectedProductName.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSelectedProductName.setEditable(false);
			tfSelectedProductName.setColumns(10);
			tfSelectedProductName.setBounds(393, 348, 130, 26);
		}
		return tfSelectedProductName;
	}

	private JLabel getLblNewLabel_3_1_2_1_1() {
		if (lblNewLabel_3_1_2_1_1 == null) {
			lblNewLabel_3_1_2_1_1 = new JLabel("선택 수량  :");
			lblNewLabel_3_1_2_1_1.setBounds(551, 405, 61, 16);
		}
		return lblNewLabel_3_1_2_1_1;
	}

	private JComboBox getCbSelectedQty() {
		if (cbSelectedQty == null) {
			cbSelectedQty = new JComboBox();
			cbSelectedQty.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}

				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					calcPrice();
				}

				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				}
			});
			cbSelectedQty.setBounds(645, 401, 75, 27);

		}
		return cbSelectedQty;
	}

	private JButton getBtnMoveMainView() {
		if (btnMoveMainView == null) {
			btnMoveMainView = new JButton("");
			btnMoveMainView.setIcon(new ImageIcon(BuyList.class.getResource("/com/javalec/image/메인가기.png")));
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
			btnLogo.setBounds(20, 19, 225, 45);
			btnLogo.setIcon(new ImageIcon(ManagerPage_.class.getResource("/com/javalec/image/JUNES_LOGO.png")));
		}
		return btnLogo;
	}
	
	// ========= Functions ============

	private void tableInit() { // Table 초기화 
		// Table Coulmn명 설정
		outerTable.addColumn("제품번호");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("색상");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("선택 수량");
		outerTable.setColumnCount(7);

		// Table Column 크기 설정
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 90;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 130;
		col.setPreferredWidth(width);

		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 121;
		col.setPreferredWidth(width);

		colNo = 4;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 105;
		col.setPreferredWidth(width);

		colNo = 5;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 110;
		col.setPreferredWidth(width);

		colNo = 6;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 83;
		col.setPreferredWidth(width);

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);

		// Table 내용 지우기
		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
	}

	private void searchAction() { // 검색(Database에서 Table로 불러오기) 
		Dao_lcy dao = new Dao_lcy();
		ArrayList<Dto_lcy> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getP_seq());
			String[] qTxt = { temp, dtoList.get(i).getBrand(), dtoList.get(i).getName(), dtoList.get(i).getPrice(),
					dtoList.get(i).getColor(), Integer.toString(dtoList.get(i).getSize()),
					Integer.toString(dtoList.get(i).getSaveQty()) };
			outerTable.addRow(qTxt);
		}
	}

	private void tableClick() { // Table에서 Row를 Click했을 경우 
		int i = innerTable.getSelectedRow(); // innerTable의 내가 선택한 행의 번호를 i로 선언
		String tkSequence = (String) innerTable.getValueAt(i, 0); // i행의 0번째 값 = p_seq를 tkSequence에 저장
		int wkSequence = Integer.parseInt(tkSequence);

		Dao_lcy dao = new Dao_lcy(wkSequence);
		Dto_lcy dto = dao.tableClick();

		tfSelectedProductSeqNo.setText(Integer.toString(dto.getP_seq()));
		tfSelectedProductBrand.setText(dto.getBrand());
		tfSelectedProductName.setText(dto.getName());
		tfSelectedProductColor.setText(dto.getColor());
		tfSelectedProductPrice.setText(dto.getPrice());
		tfSelectedProductSize.setText(Integer.toString(dto.getSize()));
		tfSelectedProductRemainQty.setText(Integer.toString(dto.getSaveQty()));
		tfSelectedAllPrice.setText("0");

		String filePath = Integer.toString(ShareVar.filename);

		lblProductImage.setIcon(new ImageIcon(filePath));
		lblProductImage.setHorizontalAlignment(SwingConstants.CENTER);

		File file = new File(filePath);
		file.delete();

		int saveSelectedQty = dto.getQty();
		int selectedProductRemainQty = Integer.parseInt(tfSelectedProductRemainQty.getText());
		cbSelectedQty.removeAllItems(); // cb remove
		for (int j = 0; j <= selectedProductRemainQty; j++) {
			cbSelectedQty.addItem(j); // cb item add
		}

		cbSelectedQty.setSelectedItem(saveSelectedQty);
		calcPrice();
	}

	private void listDeleteAction() { // 장바구니에서 목록 삭제 버튼을 누를때 
		Dao_lcy dao = new Dao_lcy(Integer.parseInt(tfSelectedProductSeqNo.getText()));
		boolean result = dao.deleteAction();

		if (result == true) {
			JOptionPane.showMessageDialog(null, "목록이 삭제되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "삭제 중 문제가 발생하였습니다.");
		}
	}

	private void buyAction() { // 장바구니에서 구매 확정 버튼을 눌렀을때 

		if (cbSelectedQty.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "수량이 입력되지 않았습니다.");
		} else {
//			product_p_seq, customer_customer_id, price , qty
			String inputString = tfSelectedProductPrice.getText().toString();
			String input = inputString.replaceAll(",", "");
			int x = Integer.parseInt(input);
			// x - ProductPrice
			// cbSelectedQty.getSelectedItem().toString() - 몇 개 구입하려는 수량
			Dao_lcy dao = new Dao_lcy(Integer.parseInt(tfSelectedProductSeqNo.getText()), ShareVar.userID, x,
					Integer.parseInt(cbSelectedQty.getSelectedItem().toString()));
			boolean result = dao.buyAction(); // sale로 insert

			if (result == true) {
				dao = new Dao_lcy(Integer.parseInt(tfSelectedProductSeqNo.getText()));
				dao.deleteAction(); // save에서 삭제
				dao = new Dao_lcy(Integer.parseInt(tfSelectedProductSeqNo.getText()),
						Integer.parseInt(tfSelectedProductRemainQty.getText()),
						Integer.parseInt(cbSelectedQty.getSelectedItem().toString()));
				dao.updateAction();
				JOptionPane.showMessageDialog(null, "선택한 제품이 구매되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "구매 중 문제가 발생하였습니다.");
			}
		}
	}

	private void clearColumn() { // textfield 초기화 
		tfSelectedProductBrand.setText("");
		tfSelectedProductColor.setText("");
		tfSelectedAllPrice.setText("");
		tfSelectedProductName.setText("");
		tfSelectedProductPrice.setText("");
		tfSelectedProductSize.setText("");
		tfSelectedProductSeqNo.setText("");
		tfSelectedProductRemainQty.setText("");
		cbSelectedQty.setSelectedItem("1");
		lblProductImage.setIcon(null);
		cbSelectedQty.removeAllItems();
	}

	private String calcPrice() { // 총 가격 계산 
		String inputString = tfSelectedProductPrice.getText().toString();
		inputString = inputString.replaceAll(",", ""); // 쉼표(,) 제거

		int price = Integer.parseInt(inputString);
		int qty = Integer.parseInt(cbSelectedQty.getSelectedItem().toString());
		int result = price * qty;

		String input = String.format("%,3d", result);
		tfSelectedAllPrice.setText(String.format("%,3d", result));

		return input;
	}

	private void buyReConfirm() { // 구매 확정 시 Dialog 
		int result = JOptionPane.showConfirmDialog(null, ("총 금액 : " + calcPrice() + "원\n정말로 구매 하시겠습니까?"), "구매확인",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			buyAction();
			searchAction();
			clearColumn();
			if (innerTable.getRowCount() == 0) {
				buyDialog.setVisible(false);
				buyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				OrderList orderList = new OrderList();
				orderList.setVisible(true);
			}
		}
	}

	private void deleteReConfirm() { // 목록 삭제 시 Dialog 
		int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "목록 삭제", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			listDeleteAction();
			searchAction();
			clearColumn();
			if (innerTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				buyDialog.setVisible(false);
				buyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				MainView mainView = new MainView();
				mainView.setVisible(true);
			}
		}
	}

	private void goHome() { // LogOut 시 FirstPage로 
		buyDialog.setVisible(false);
		buyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		FirstPage firstPage = new FirstPage();
		firstPage.setVisible(true);
	}

	private void goMain() { // MainView로 이동 
		buyDialog.setVisible(false);
		MainView mainView = new MainView();
		mainView.setVisible(true);
	}

} // End
