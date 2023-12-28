package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.cj.result.LocalDateTimeValueFactory;

public class Dao_lcy {
	
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	// [product]
	int p_seq;
	String brand;
	String name;
	String price;
	String color;
	String qty;
	String size;
//	FileInputStream file;
	
	// [customer]
	String customer_id;
	
	
	// Constructor
	public Dao_lcy() {
		// TODO Auto-generated constructor stub
	}
	
	public Dao_lcy(String qty) {
		super();
		this.qty = qty;
	}
	
	public Dao_lcy(int p_seq) {
		super();
		this.p_seq = p_seq;
	}
	
	
	public Dao_lcy(int p_seq, String brand, String name, String price, String color, String qty, String size,
			String customer_id) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
		this.customer_id = customer_id;
	}

	public Dao_lcy(int p_seq, String customer_id, String price, String qty) {
		super();
		this.p_seq = p_seq;
		this.customer_id = customer_id;
		this.price = price;
		this.qty = qty;
	}
	
	
	
	
	// Method 

	// 검색 결과를 Table로 보내기
	public ArrayList<Dto_lcy> selectList() { 
		ArrayList<Dto_lcy> Dto_lcyList = new ArrayList<Dto_lcy>();
		String whereDefault = "select p.p_seq, p.brand, p.name, p.price, p.color, p.size";
		String where1 = " from product as p, save as s";
		String where2 = " where p.p_seq = s.p_seq";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where1+where2);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				String wkPrice = rs.getString(4);
				String wkColor = rs.getString(5);
				String wkSize = rs.getString(6);
				
				Dto_lcy dto = new Dto_lcy(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkSize);
				Dto_lcyList.add(dto);
			}
			
			conn_mysql.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Dto_lcyList;
	} 
	
	// Table을 Click 했을 때
	public Dto_lcy tableClick() {
		Dto_lcy Dto_lcy = null;
		String where1 = "select p.p_seq, p.brand, p.name, p.price, p.color, p.size, p.qty";
		String where2 = " from product as p, save as s";
		String where3 = " where p.p_seq = s.p_seq and p.p_seq = " + p_seq;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where1+where2+where3);
			
			if(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				String wkPrice = rs.getString(4);
				String wkColor = rs.getString(5);
				String wkSize = rs.getString(6);
				String wkQty = rs.getString(7);
				
//				// file(Image)
//				ShareVar.filename = ShareVar.filename + 1;
//				File file = new File(Integer.toString(ShareVar.filename));
//				FileOutputStream output = new FileOutputStream(file);
//				InputStream input = rs.getBinaryStream(7);
//				byte[] buffer = new byte[1024];
//				
//				while(input.read(buffer) > 0) {
//					output.write(buffer);
//				}
				
				Dto_lcy = new Dto_lcy(wkSeq, wkBrand, wkName, wkPrice, wkColor, wkSize, wkQty);
			}
			
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Dto_lcy;
	} 
	
	// 구매확정
	public boolean buyAction() {
		PreparedStatement ps = null;
		boolean result;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "insert into sale (product_p_seq, customer_customer_id, price , date ,qty)";
			String B = " values (?,?,?,CAST(now() as date),?)";
			
			ps = conn_mysql.prepareStatement(A+B);
			// 
			ps.setInt(1,p_seq);
			ps.setString(2, customer_id);
			ps.setString(3, price);
			ps.setInt(4, Integer.parseInt(qty));
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
			result = false;
		}
		result = true;
		return result;
	}

	
	// 삭제
	public boolean deleteAction() {
		PreparedStatement ps = null;
		boolean result;
		
		String where1 = "delete from save where p_seq = " + p_seq;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ps = conn_mysql.prepareStatement(where1);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
			result = false;
		}
		result = true;
		return result;
	}


}
