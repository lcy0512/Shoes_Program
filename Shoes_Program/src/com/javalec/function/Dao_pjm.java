package com.javalec.function;

import java.io.File;
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

	public Dao_pjm(String brand, String name, String price, String color, String qty, String size) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
	}

	// Method
	// 검색결과를 Table 로 보내자
	public ArrayList<Dto> selectList() {
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		String whereDefault = "select p_seq, brand, name, price, color, qty, size from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				String wkPrice = rs.getString(4);
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

		String where1 = "select p_seq, brand, name, price, color, qty, size from product ";
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
				String wkPrice = rs.getString(4);
				String wkColor = rs.getString(5);
				String wkQty = rs.getString(6);
				String wkSize = rs.getString(7);
				
				//file
//				ShareVar.filename = ShareVar.filename + 1;
//				File file = new File(Integer.toString(ShareVar.filename));
//				FileOutputStream output = new FileOutputStream(file);
//				InputStream input = rs.getBinaryStream(7);
//				byte[] buffer = new byte[1024];
//				while(input.read(buffer) > 0) {
//					output.write(buffer);
//					
//				}
				
				
				
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
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "insert into product (brand, name, price, color, qty, size)";
			String B = " value (?,?,?,?,?,?)";
			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, brand);
			ps.setString(2, name);
			ps.setString(3, price);
			ps.setString(4, color);
			ps.setString(5, qty);
			ps.setString(6, size);
			ps.executeUpdate();

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateAction() {
		PreparedStatement ps = null;
		String A = "update product set brand = ?, name = ?, price = ?, color = ?, qty = ?, size = ?";
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

}
