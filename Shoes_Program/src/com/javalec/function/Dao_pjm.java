package com.javalec.function;


/*
* Descritipon : 관리자 페이지 Dao
* Author : PJM
* Date : 23.12.30
* update : 23.12.30 15:45
*/

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

import javax.swing.JComboBox;
import javax.swing.JTextField;




public class Dao_pjm {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;

	int p_seq;
	String brand;
	String name;
	int price;
	String color;
	int qty;
	int size;
	FileInputStream image;
	String searchAll;

	// Construct
	public Dao_pjm() {

	}

	public Dao_pjm(int p_seq) {
		super();
		this.p_seq = p_seq;
	}

	public Dao_pjm(int p_seq, String brand, String name, int price, String color, int qty, int size) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
	}

	public Dao_pjm(String brand, String name, int price, String color, int qty, int size, FileInputStream image) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
		this.image = image;
	}

	public Dao_pjm(String searchAll) {
		super();
		this.searchAll = searchAll;
	}

	// Method
	// 검색결과를 Table 로 보내자
	public ArrayList<Dto_pjm> selectList() {
		ArrayList<Dto_pjm> dtoList = new ArrayList<Dto_pjm>();
		String whereDefault = "select p_seq, brand, name, price, color, qty, size from product ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				dtoList.add(dto);

			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	public Dto_pjm tableClick() {
		Dto_pjm dto = null;

		String where1 = "select p_seq, brand, name, price, color, qty, size, image from product ";
		String where2 = " where p_seq = " + p_seq;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where1 + where2);

			if (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				// file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(8);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);

				}

				dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);

			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}

	public boolean insertAction() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn_mysql = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// Check for existing record with the same brand, name, price, color, and size
			String checkQuery = "SELECT * FROM product WHERE brand=? AND name=? AND price=? AND color=? AND size=? ";
			ps = conn_mysql.prepareStatement(checkQuery);
			ps.setString(1, brand);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setString(4, color);
			ps.setInt(5, size);
			rs = ps.executeQuery();

			if (rs.next()) {
				// Record already exists, update quantity
				int existingQty = rs.getInt("qty");
				int newQty = existingQty + (qty);

				// Update the quantity for the existing record
				String updateQuery = "UPDATE product SET qty=? WHERE brand=? AND name=? AND price=? AND color=? AND size=? ";
				ps = conn_mysql.prepareStatement(updateQuery);
				ps.setInt(1, newQty);
				ps.setString(2, brand);
				ps.setString(3, name);
				ps.setInt(4, price);
				ps.setString(5, color);
				ps.setInt(6, size);
				ps.executeUpdate();
			} else {
				// Record does not exist, insert a new record
				String insertQuery = "INSERT INTO product (brand, name, price, color, qty, size, image) VALUES (?, ?, ?, ?, ?, ?, ?) ";
				ps = conn_mysql.prepareStatement(insertQuery);
				ps.setString(1, brand);
				ps.setString(2, name);
				ps.setInt(3, price);
				ps.setString(4, color);
				ps.setInt(5, qty);
				ps.setInt(6, size);
				ps.setBinaryStream(7, image);
				ps.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn_mysql != null)
					conn_mysql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public boolean updateAction() {
		PreparedStatement ps = null;
		String A = "update product set brand = ?, name = ?, price = ?, color = ?, qty = ?, size = ? ";
		String B = " where p_seq = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, brand);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setString(4, color);
			ps.setInt(5, qty);
			ps.setInt(6, size);
			ps.setInt(7, p_seq);
			ps.executeUpdate();
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 제품명으로 검색
	public ArrayList<Dto_pjm> searchName() {
		ArrayList<Dto_pjm> dtoList = new ArrayList<Dto_pjm>();
		String whereName = "select p_seq, brand, name, price, color, qty, size from product where name like ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereName);
			// 처리 결과 보여주기
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				dtoList.add(dto);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	public ArrayList<Dto_pjm> brandSearch() {
		ArrayList<Dto_pjm> nowSearch = new ArrayList<Dto_pjm>();

		String query1 = "Select p_seq, brand, name, price, color, qty, size from product where ";
		String query2 = " brand like ? '%" + searchAll + "%'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql); // 연결한때 어느 id pw로할꺼
			Statement stmt_mysql = conn_mysql.createStatement(); // db에서 데이터 가져오는게 statement

			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);//
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				nowSearch.add(dto);
			}
			conn_mysql.close(); // 접속자가기본 5명이라 바로끊어줘야함
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowSearch;
	}

	public ArrayList<Dto_pjm> nameSearch() {
		ArrayList<Dto_pjm> nowSearch = new ArrayList<Dto_pjm>();

		String query1 = "Select p_seq, brand, name, price, color, qty, size from product where ";
		String query2 = " brand like '%" + searchAll + "%'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql); // 연결한때 어느 id pw로할꺼
			Statement stmt_mysql = conn_mysql.createStatement(); // db에서 데이터ㄱ가져오는게 statement

			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);//
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				nowSearch.add(dto);
			}
			conn_mysql.close(); // 접속자가기본 5명이라 바로끊어줘야함
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowSearch;
	}

//	public ArrayList<Dto_pjm> sizeSearch() {
//		ArrayList<Dto_pjm> nowSearch = new ArrayList<Dto_pjm>();
//		
//		
//		String query1 = "Select p_seq, brand, name, price, color, qty, size from product where ";
//		String query2 = " brand like '%" + searchAll + "%'";
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql); // 연결한때 어느 id pw로할꺼
//			Statement stmt_mysql = conn_mysql.createStatement(); // db에서 데이터ㄱ가져오는게 statement
//			
//			ResultSet rs = stmt_mysql.executeQuery(query1+ query2);//
//			while(rs.next()) {
//				int wkSeq = rs.getInt(1);
//				String wkBrand = rs.getString(2);
//				String wkName = rs.getString(3);
//				int wkPrice = Integer.parseInt(rs.getString(4));
//				String wkColor = rs.getString(5);
//				int wkQty = Integer.parseInt(rs.getString(6));
//				int wkSize = Integer.parseInt(rs.getString(7));
//				
//				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
//				nowSearch.add(dto);
//			}
//			conn_mysql.close(); // 접속자가기본 5명이라 바로끊어줘야함
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return nowSearch;
//	}
	public ArrayList<Dto_pjm> allSearchAction(String selectedProduct, String searchText) {
		ArrayList<Dto_pjm> resultList = new ArrayList<Dto_pjm>();

		String query = "SELECT p_seq, brand, name, price, color, qty, size FROM product WHERE ";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			if ("브랜드".equals(selectedProduct)) {
				query += "brand LIKE '%" + searchText + "%'";
			} else if ("제품명".equals(selectedProduct)) {
				query += "name LIKE '%" + searchText + "%'";
			} else if ("사이즈".equals(selectedProduct)) {
				query += "size like'%" + searchText + "%'";
			}

			stmt_mysql = conn_mysql.prepareStatement(query);


			ResultSet rs = stmt_mysql.executeQuery(query);
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = Integer.parseInt(rs.getString(4));
				String wkColor = rs.getString(5);
				int wkQty = Integer.parseInt(rs.getString(6));
				int wkSize = Integer.parseInt(rs.getString(7));

				Dto_pjm dto = new Dto_pjm(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				resultList.add(dto);
			}

			rs.close();
			stmt_mysql.close();
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

}
