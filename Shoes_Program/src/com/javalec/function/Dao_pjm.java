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
	String price;
	String color;
	String qty;
	String size;
	FileInputStream image;

	// Construct
	public Dao_pjm() {

	}

	public Dao_pjm(int p_seq) {
		super();
		this.p_seq = p_seq;
	}

	public Dao_pjm(int p_seq, String brand, String name, String price, String color, String qty, String size) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
	}

	public Dao_pjm(String brand, String name, String price, String color, String qty, String size,
			FileInputStream image) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
		this.image = image;
	}

	// Method
	// 검색결과를 Table 로 보내자
	public ArrayList<Dto> selectList() {
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
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
				String wkPrice = String.format("%,3d",Integer.parseInt(rs.getString(4)));
				String wkColor = rs.getString(5);
				String wkQty = rs.getString(6);
				String wkSize = rs.getString(7);

				Dto dto = new Dto(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				dtoList.add(dto);

			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	public Dto tableClick() {
		Dto dto = null;

		String where1 = "select p_seq, brand, name, price, color, qty, size, image from product ";
		String where2 = " where p_seq = " + p_seq;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where1 + where2);

			if (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBarnd = rs.getString(2);
				String wkName = rs.getString(3);
				String wkPrice = String.format("%,3d",Integer.parseInt(rs.getString(4)));
				String wkColor = rs.getString(5);
				String wkQty = rs.getString(6);
				String wkSize = rs.getString(7);

				// file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(8);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);

				}

				dto = new Dto(wkSeq, wkBarnd, wkName, wkPrice, wkColor, wkQty, wkSize);

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
			ps.setString(3, price);
			ps.setString(4, color);
			ps.setString(5, size);
			rs = ps.executeQuery();

			if (rs.next()) {
				// Record already exists, update quantity
				int existingQty = rs.getInt("qty");
				int newQty = existingQty + Integer.parseInt(qty);

				// Update the quantity for the existing record
				String updateQuery = "UPDATE product SET qty=? WHERE brand=? AND name=? AND price=? AND color=? AND size=? ";
				ps = conn_mysql.prepareStatement(updateQuery);
				ps.setInt(1, newQty);
				ps.setString(2, brand);
				ps.setString(3, name);
				ps.setString(4, price);
				ps.setString(5, color);
				ps.setString(6, size);
				ps.executeUpdate();
			} else {
				// Record does not exist, insert a new record
				String insertQuery = "INSERT INTO product (brand, name, price, color, qty, size, image) VALUES (?, ?, ?, ?, ?, ?, ?) ";
				ps = conn_mysql.prepareStatement(insertQuery);
				ps.setString(1, brand);
				ps.setString(2, name);
				ps.setString(3, price);
				ps.setString(4, color);
				ps.setString(5, qty);
				ps.setString(6, size);
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

//	public boolean insertDelivery() {
//		PreparedStatement ps = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
//			Statement stmt_mysql = conn_mysql.createStatement();
//
//			String insertDelivey = "insert into delivery (p_seq, date, qty, price) values (?, NOW(), ?, ?) ";
//			ps = conn_mysql.prepareStatement(insertDelivey);
//			ps.setInt(1, p_seq);
//			ps.setInt(2, Integer.parseInt(qty));
//			ps.setInt(3, Integer.parseInt(price));
//			ps.executeUpdate();
//			conn_mysql.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	public boolean updateAction() {
		PreparedStatement ps = null;
		String A = "update product set brand = ?, name = ?, price = ?, color = ?, qty = ?, size = ?, image = ? ";
		String B = " where p_seq = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, brand);
			ps.setString(2, name);
			ps.setString(3, price);
			ps.setString(4, color);
			ps.setString(5, qty);
			ps.setString(6, size);
			ps.setInt(7, p_seq);
			ps.executeUpdate();
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteAction() {
		PreparedStatement ps = null;
		boolean result;

		String A = "delete from product where p_seq = " + p_seq; // 띄어쓰기 꼭 하기

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			// Insert Update delete 는 아래 코드만 바꾸면 가능

			ps = conn_mysql.prepareStatement(A);

			ps.executeUpdate();// 입력

			conn_mysql.close();// 연결종료
			// 입력 후 연결은 꼭 끊어준다.

		} catch (Exception e) {
			result = false;
		}
		result = true;
		return result;

	}
	//제품명으로 검색
	public ArrayList<Dto> searchName() {
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		String whereDefault = "select p_seq, brand, name, price, color, qty, size from product where like name = ? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				String wkPrice = String.format("%,3d",Integer.parseInt(rs.getString(4)));
				String wkColor = rs.getString(5);
				String wkQty = rs.getString(6);
				String wkSize = rs.getString(7);

				Dto dto = new Dto(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkQty, wkSize);
				dtoList.add(dto);

			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	

	
	

}
