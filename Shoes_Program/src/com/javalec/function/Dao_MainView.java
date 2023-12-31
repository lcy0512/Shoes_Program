package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_MainView {
	/*
	 * Description : 메인뷰 페이지
	 * Date : 2023.12.31
	 * Author : 박지환
	 * Update : 1. 브랜드, 이름별로 검색기능추가
	 * 			2. 테이블 눌렀을때 SEQ받는 메소드 추가 by wdh
	 *
	 */

	// Field

	// sql 설정
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	// product variable
	int p_seq;
	String pbrand;
	String pname;
	int pprice;
	String pcolor;
	int pqty;
	int psize;
	FileInputStream file;
	String searchText;
	// Constructor

	public Dao_MainView() {
		// TODO Auto-generated constructor stub
	}

	public Dao_MainView(String pbrand, String pname, int pprice, FileInputStream file) {
		super();
		this.pbrand = pbrand;
		this.pname = pname;
		this.pprice = pprice;
		this.file = file;

	}

	// Method
	// 1. 초기 Table에 사진,브랜드,이름,가격 넣기
	public ArrayList<Dto_Mainview> dao_pjh_AllProductMainviews() {
		ArrayList<Dto_Mainview> dtoAllProductTablelist = new ArrayList<Dto_Mainview>();
		String fetchAllProductQuery = "select brand, name, price,image from product";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

			while (rs.next()) {
				String sqlbrand = rs.getString(1);
				String sqlname = rs.getString(2);
				int sqlprice = rs.getInt(3);
				String currentRow_fake_filename = rs.getString(2) + "_image";

				// File

				File image = new File("./" + currentRow_fake_filename);
				FileOutputStream writingFileOnHardware = new FileOutputStream(image);
				InputStream loadOnTheFileWithFetchedImage = rs.getBinaryStream(4);

				byte[] buffer = new byte[1024];
				while (loadOnTheFileWithFetchedImage.read(buffer) > 0) {
					writingFileOnHardware.write(buffer);

				}

				Dto_Mainview dtoAllImageProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice,
						currentRow_fake_filename);

				dtoAllProductTablelist.add(dtoAllImageProduct);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoAllProductTablelist;

	}

	// 2. 브랜드 별로 검색하기(일요일날 브랜드, 이름도 메인에버 받아서 브랜드 이름 하나로 합치기)
	public ArrayList<Dto_Mainview> dao_pjh_BrandSearchProductMainviews(String searchText) {

		ArrayList<Dto_Mainview> dtoBrandProductTablelist = new ArrayList<Dto_Mainview>();
		String fetchAllProductQuery = "select brand, name, price,image \r\n" + "from product\r\n"
				+ "where brand like '%" + searchText + "%'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

			while (rs.next()) {
				String sqlbrand = rs.getString(1);
				String sqlname = rs.getString(2);
				int sqlprice = rs.getInt(3);
				String currentRow_fake_filename = rs.getString(2) + "_image";

				// File

				File image = new File("./" + currentRow_fake_filename);
				FileOutputStream writingFileOnHardware = new FileOutputStream(image);
				InputStream loadOnTheFileWithFetchedImage = rs.getBinaryStream(4);

				byte[] buffer = new byte[1024];
				while (loadOnTheFileWithFetchedImage.read(buffer) > 0) {
					writingFileOnHardware.write(buffer);

				}

				Dto_Mainview dtoAllImageProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice,
						currentRow_fake_filename);

				dtoBrandProductTablelist.add(dtoAllImageProduct);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoBrandProductTablelist;

	}

	// 3. 이름 별로 검색하기
	public ArrayList<Dto_Mainview> dao_pjh_NameSearchProductMainviews(String searchText) {
		ArrayList<Dto_Mainview> dtoNameProductTablelist = new ArrayList<Dto_Mainview>();
		String fetchAllProductQuery = "select brand, name, price,image \r\n" + "from product\r\n" + "where name like '%"
				+ searchText + "%'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

			while (rs.next()) {
				String sqlbrand = rs.getString(1);
				String sqlname = rs.getString(2);
				int sqlprice = rs.getInt(3);
				String currentRow_fake_filename = rs.getString(2) + "_image";

				// File

				File image = new File("./" + currentRow_fake_filename);
				FileOutputStream writingFileOnHardware = new FileOutputStream(image);
				InputStream loadOnTheFileWithFetchedImage = rs.getBinaryStream(4);

				byte[] buffer = new byte[1024];
				while (loadOnTheFileWithFetchedImage.read(buffer) > 0) {
					writingFileOnHardware.write(buffer);

				}

				Dto_Mainview dtoAllImageProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice,
						currentRow_fake_filename);

				dtoNameProductTablelist.add(dtoAllImageProduct);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoNameProductTablelist;

	}

	// p_seq가 들어있는 ArrayList 작성
	public ArrayList<Integer> p_seqArrayList() {
		ArrayList<Integer> p_seqArrayList = new ArrayList<Integer>();

		String p_seq = "select p_seq from product";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(p_seq);

			while (rs.next()) {
				int sqlprice = rs.getInt(1);

				p_seqArrayList.add(sqlprice);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return p_seqArrayList;

	}

}