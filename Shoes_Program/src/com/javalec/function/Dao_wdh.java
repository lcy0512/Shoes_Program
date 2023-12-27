package com.javalec.function;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	FileInputStream file;
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
	int sp_seq1;
	int sp_seq2;
	int sdate;
	int sprice;
	// save
	int saseq;
	int sap_seq;
	String sacustomer_id;
	
	//
	int stock;

	// Constructor

	public Dao_wdh() {
		// TODO Auto-generated constructor stub
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

	// Method
	
	// p_seq를 가져와서 pname, pcolor, psize, pprice, qty, image의 정보를 띄워줌
	public Dto_wdh viewDetailInfo() {
		Dto_wdh dto_wdh = null;

		// sql에서 p_seq를 통해 이름, 색깔, 사이즈, 가격, 이미지를 가져옴
		String where = "select name, color, size, price, qty from product where p_seq = " + p_seq;

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

				// file을 만들어 주는 것..
//				ShareVar_wdh.image = ShareVar_wdh.image + 1;
//				File file = new File(Integer.toString(ShareVar_wdh.image));
//				FileOutputStream output = new FileOutputStream(file);
//				InputStream input = rs.getBinaryStream(5);
//				byte[] buffer = new byte[1024];
//				while (input.read(buffer) > 0) {
//					output.write(buffer);
//				}

				dto_wdh = new Dto_wdh(wkName, wkColor, wkSize, wkPrice, wkQty);  // Model에 적용
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
			
		}catch(Exception e) {
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
			
			String where1 = "insert into sale (name, telno, address, email, relation, file)";
			String where2 = " values (?, ?, ?, ?, ?, ?)";
			
			ps = conn_mysql.prepareStatement(where1 + where2);
			ps.setString(1, name);
			ps.setString(2, telno);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setString(5, relation);
			ps.setBinaryStream(6, file);
			ps.executeUpdate();			// 무조건 executeUpdate를 해줄것!!!!!!!!!!!!
			
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // End
