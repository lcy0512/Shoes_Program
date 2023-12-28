package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_wdh {

	// Field
	private final String url_mysql = ShareVar_wdh.dbName;
	private final String id_mysql = ShareVar_wdh.dbUser;
	private final String pw_mysql = ShareVar_wdh.dbPass;
	// product
	int p_seq;
	String pbrand;
	String pname;
	int pprice;
	String pcolor;
	int pqty;
	int psize;
	FileInputStream image;
	// customer
	String customer_id;
	String cname;
	String cpw;
	String telno;
	String email;
	// delivery
	int dseq;
	int dp_seq1;
	int p_seq2;
	String ddate; // date도 int 인트로?
	int dqty;
	int dprice;
	int product_p_seq;
	// sale
	int sseq;
	int sp_seq;
	String scustomer_id;
	String sdate;
	int sprice;
	int sqty;
	// save
	int saseq;
	int sap_seq;
	String sacustomer_id;

	//
	int stock;

	// Constructor

	public Dao_wdh() {
	}

	// p_seq를 가져오는 생성자
	public Dao_wdh(int p_seq) {
		super();
		this.p_seq = p_seq;
	}

	public Dao_wdh(int p_seq, int stock) {
		super();
		this.p_seq = p_seq;
		this.stock = stock;
	}

	public Dao_wdh(int sap_seq, String sacustomer_id) {
		super();
		this.sap_seq = sap_seq;
		this.sacustomer_id = sacustomer_id;
	}

	public Dao_wdh(String pname, String pcolor, int psize) {
		super();
		this.pname = pname;
		this.pcolor = pcolor;
		this.psize = psize;
	}

	public Dao_wdh(int sp_seq, String scustomer_id, String sdate, int sprice, int sqty) {
		super();
		this.sp_seq = sp_seq;
		this.scustomer_id = scustomer_id;
		this.sdate = sdate;
		this.sprice = sprice;
		this.sqty = sqty;
	}

	// Method
	// p_seq를 가져와서 pname, pcolor, psize, pprice, qty, image의 정보를 띄워줌
	public Dto_wdh viewDetailInfo() {
		Dto_wdh dto_wdh = null;

		// sql에서 p_seq를 통해 이름, 색깔, 사이즈, 가격, 이미지를 가져옴
		String where = "select name, color, size, price, qty, p_seq, image from product where p_seq = " + p_seq;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where);

			if (rs.next()) {
				String wkName = rs.getString(1);
				String wkColor = rs.getString(2);
				int wkSize = rs.getInt(3);
				int wkPrice = rs.getInt(4);
				int wkQty = rs.getInt(5);
				int wkP_seq = rs.getInt(6);

//				 file을 저장한 후 꺼내 쓸 수 있게 이미지 파일을 HDD에 저장
				ShareVar_wdh.image = ShareVar_wdh.image + 1;
				File file = new File(Integer.toString(ShareVar_wdh.image));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				dto_wdh = new Dto_wdh(wkName, wkColor, wkSize, wkPrice, wkQty, wkP_seq); // Model에 적용
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto_wdh;
	}

	// 바로구매를 눌렀을 때 수량만큼 재고에서 빼는 Method
	public boolean updateAction() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			String where = "update product set qty = ? where p_seq = ?";

			ps = conn_mysql.prepareStatement(where);
			ps.setInt(1, stock);
			ps.setInt(2, p_seq);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}
	
	// 바로구매를 눌렀을 때 sale Entity에 insert 하는 Method
	public boolean saleInsertAction() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			String insert = "insert into sale (product_p_seq, customer_customer_id, price, date, qty) values (?, ?, ?, ?, ?)";

			ps = conn_mysql.prepareStatement(insert);
			ps.setInt(1, sp_seq);
			ps.setString(2, scustomer_id);
			ps.setInt(3, sprice);
			ps.setString(4, sdate);
			ps.setInt(5, sqty);
			ps.executeUpdate(); // 무조건 executeUpdate를 해줄것!!!!!!!!!!!!

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}


	// 장바구니를 눌렀을 때 임시저장에 insert
	public boolean insertAction() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			String insert = "insert into save (p_seq, customer_id) values (?, ?)";

			ps = conn_mysql.prepareStatement(insert);
			ps.setInt(1, sap_seq);
			ps.setString(2, sacustomer_id);
			ps.executeUpdate(); // 무조건 executeUpdate를 해줄것!!!!!!!!!!!!

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	// sql에서 name을 통해 color를 가져옴
	public ArrayList<String> productColor() {
		String pname = viewDetailInfo().getPname();
		
		ArrayList<String> productColor = new ArrayList<String>();

		String where = "select color from product where name = '" + pname + "' group by color";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where);
			while (rs.next()) {
				String wkColor = rs.getString(1);
				productColor.add(wkColor);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productColor;
	}
	
	// sql에서 name, color를 통해 size를 가져옴
	public ArrayList<Integer> productSize() {
		String pname = viewDetailInfo().getPname();
		String pcolor = viewDetailInfo().getPcolor();
		
		ArrayList<Integer> productSize = new ArrayList<Integer>();

		String where = "select size from product where name = '" + pname + "' and color = '" + pcolor + "' group by size order by size asc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where);
			while (rs.next()) {
				int wkSize = rs.getInt(1);
				productSize.add(wkSize);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productSize;
	}
	
	// 제품명, 색깔, 사이즈를 통해 제품번호를 추적
	public int searchSeq() {
//		Dto_wdh dto_wdh = null;
		int wkSeq = 0;
		String where = "select p_seq from product where name = '" + pname + "' and color = '" + pcolor + "' and size = " + psize;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where);

			if (rs.next()) {
				wkSeq = rs.getInt(1);

//				dto_wdh = new Dto_wdh(wkSeq); // Model에 적용
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return wkSeq;
		
	}
	
	// 현재 제품명과 색깔을 통해 사이즈를 추적
	public ArrayList<Integer> productSizeSearch(String currentName, String currentColor) {
		
		ArrayList<Integer> productSize = new ArrayList<Integer>();

		String where = "select size from product where name = '" + currentName + "' and color = '" + currentColor + "' group by size order by size asc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where);
			while (rs.next()) {
				int wkSize = rs.getInt(1);
				productSize.add(wkSize);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productSize;
	}

	
	
	
	
	
	
	
	
	
	
	
} // End
