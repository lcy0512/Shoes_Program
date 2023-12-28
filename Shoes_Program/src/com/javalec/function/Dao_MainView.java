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
	

	// Field
	
	//sql  설정
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

	// Constructor
	
	public Dao_MainView() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	//Method
	//1. 메인뷰 전체제품 보여주기
	public ArrayList<Dto_Mainview> dao_pjh_Mainviews() {
		ArrayList<Dto_Mainview> dtoAllProductTablelist = new ArrayList<Dto_Mainview>();
		String fetchAllProductQuery = "select brand, name, price,image from product";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();
//			Statement stmt_mysql= conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

			while (rs.next()) {
				String sqlbrand = rs.getString(1);
				String sqlname = rs.getString(2);
				int sqlprice = rs.getInt(3);
				
//				//file(이미지)
//				ShareVar.image = ShareVar.image +1;
//				File sqlImage =new File(Integer.toString(ShareVar.image));
//				FileOutputStream output = new FileOutputStream(sqlImage);
//				InputStream input =rs.getBinaryStream(4);
//				byte[] buffer =new byte[1024];
//				while(input.read(buffer)>0) {
//					output.write(buffer);
//					
//				}
//				System.out.println(ShareVar.image);

				Dto_Mainview dtoAllProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice);
				dtoAllProductTablelist.add(dtoAllProduct);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoAllProductTablelist;

	}
	
	//2. 메인뷰 nike제품 보여주기
		public ArrayList<Dto_Mainview> dao_pjh_MainNikeviews() {
			ArrayList<Dto_Mainview> dtoNikeProductTablelist = new ArrayList<Dto_Mainview>();
			String fetchAllProductQuery = "select brand, name, price,image from product where brand= 'Nike'";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

				Statement stmt_mysql = conn_mysql.createStatement();
//				Statement stmt_mysql= conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

				while (rs.next()) {
					String sqlbrand = rs.getString(1);
					String sqlname = rs.getString(2);
					int sqlprice = rs.getInt(3);
					
//					//file(이미지)
//					ShareVar.image = ShareVar.image +1;
//					File sqlImage =new File(Integer.toString(ShareVar.image));
//					FileOutputStream output = new FileOutputStream(sqlImage);
//					InputStream input =rs.getBinaryStream(4);
//					byte[] buffer =new byte[1024];
//					while(input.read(buffer)>0) {
//						output.write(buffer);
//						
//					}
//					System.out.println(ShareVar.image);

					Dto_Mainview dtoAllProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice);
					dtoNikeProductTablelist.add(dtoAllProduct);

				}
				conn_mysql.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return dtoNikeProductTablelist;

		}
	
		//3. 메인뷰 Adidas 보여주기
		public ArrayList<Dto_Mainview> dao_pjh_MainAdidasviews() {
			ArrayList<Dto_Mainview> dtoAdidasProductTablelist = new ArrayList<Dto_Mainview>();
			String fetchAllProductQuery = "select brand, name, price,image from product where brand= 'adidas'";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

				Statement stmt_mysql = conn_mysql.createStatement();
//				Statement stmt_mysql= conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

				while (rs.next()) {
					String sqlbrand = rs.getString(1);
					String sqlname = rs.getString(2);
					int sqlprice = rs.getInt(3);
					
//					//file(이미지)
//					ShareVar.image = ShareVar.image +1;
//					File sqlImage =new File(Integer.toString(ShareVar.image));
//					FileOutputStream output = new FileOutputStream(sqlImage);
//					InputStream input =rs.getBinaryStream(4);
//					byte[] buffer =new byte[1024];
//					while(input.read(buffer)>0) {
//						output.write(buffer);
//						
//					}
//					System.out.println(ShareVar.image);

					Dto_Mainview dtoAllProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice);
					dtoAdidasProductTablelist.add(dtoAllProduct);

				}
				conn_mysql.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return dtoAdidasProductTablelist;

		}
		
		//4. 메인뷰 newbalance 보여주기
		public ArrayList<Dto_Mainview> dao_pjh_MainNewbalanceviews() {
			ArrayList<Dto_Mainview> dtoNewbalanceProductTablelist = new ArrayList<Dto_Mainview>();
			String fetchAllProductQuery = "select brand, name, price,image from product where brand= 'Newbalance'";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

				Statement stmt_mysql = conn_mysql.createStatement();
//				Statement stmt_mysql= conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(fetchAllProductQuery);

				while (rs.next()) {
					String sqlbrand = rs.getString(1);
					String sqlname = rs.getString(2);
					int sqlprice = rs.getInt(3);
					
//					//file(이미지)
//					ShareVar.image = ShareVar.image +1;
//					File sqlImage =new File(Integer.toString(ShareVar.image));
//					FileOutputStream output = new FileOutputStream(sqlImage);
//					InputStream input =rs.getBinaryStream(4);
//					byte[] buffer =new byte[1024];
//					while(input.read(buffer)>0) {
//						output.write(buffer);
//						
//					}
//					System.out.println(ShareVar.image);

					Dto_Mainview dtoAllProduct = new Dto_Mainview(sqlbrand, sqlname, sqlprice);
					dtoNewbalanceProductTablelist.add(dtoAllProduct);

				}
				conn_mysql.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return dtoNewbalanceProductTablelist;

		}
	
	
	

}
