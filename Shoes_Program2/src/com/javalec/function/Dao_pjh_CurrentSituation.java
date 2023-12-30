package com.javalec.function;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_pjh_CurrentSituation {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;

	//product
	int p_seq;
	String pbrand;
	String pname;
	int pprice;
	String pcolor;
	int pqty;
	int psize;
	FileInputStream file;
	//customer
	String customer_id;
	String cname;
	String cpw;
	String telno;
	String email;
	//delivery
	int dseq;
	int dp_seq1;
	int p_seq2;
	int ddate;	//date도 int 인트로?
	int dqty;
	int dprice;
	int product_p_seq;
	//sale
	int sseq;
	int sp_seq1;
	int sp_seq2;
	int sdate;	
	int sprice;
	//save
	int saseq;	
	int sap_seq;
	String sacustomer_id;
	
	
	
	
	
	
	public Dao_pjh_CurrentSituation() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	//----------------------Method
	// 일자별 매출을 위한 qurry
	public ArrayList<Dto_pjh_CurrentSituation> selectList() {
		ArrayList<Dto_pjh_CurrentSituation> dtolist = new ArrayList<Dto_pjh_CurrentSituation>();
		String whereDefault = "select date,sum(price*qty) from sale group by date order by date";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();
//			Statement stmt_mysql= conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				String wkDate = rs.getString(1);
				int wkprice = rs.getInt(2);
				

				Dto_pjh_CurrentSituation dto = new Dto_pjh_CurrentSituation(wkDate, wkprice);
				dtolist.add(dto);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtolist;

	}
	
	// 월매출 Qurry
	public ArrayList<Dto_pjh_CurrentSituation> selecMonthList() {
		ArrayList<Dto_pjh_CurrentSituation> dtolist = new ArrayList<Dto_pjh_CurrentSituation>();
		String whereDefault = "SELECT DATE_FORMAT(date, '%Y-%m'), sum(price * qty) FROM sale group by DATE_FORMAT(date, '%Y-%m')";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();
//			Statement stmt_mysql= conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				String wkDate = rs.getString(1);
				int wkprice = rs.getInt(2);
				

				Dto_pjh_CurrentSituation dto = new Dto_pjh_CurrentSituation(wkDate, wkprice);
				dtolist.add(dto);

			}
			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtolist;

	}
	// 검색쿼리
//	public ArrayList<Dto_pjh_CurrentSituation> QueryAction(){
//		ArrayList<Dto_pjh_CurrentSituation> dtolist = new ArrayList<Dto_pjh_CurrentSituation>();
//		String whereDefault = "SELECT DATE_FORMAT(date, '%Y-%m'), sum(price * qty) FROM sale group by DATE_FORMAT(date, '%Y-%m')";
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
//
//			Statement stmt_mysql = conn_mysql.createStatement();
////			Statement stmt_mysql= conn_mysql.createStatement();
//
//			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
//
//			while (rs.next()) {
//				String wkDate = rs.getString(1);
//				int wkprice = rs.getInt(2);
//				
//
//				Dto_pjh_CurrentSituation dto = new Dto_pjh_CurrentSituation(wkDate, wkprice);
//				dtolist.add(dto);
//
//			}
//			conn_mysql.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return dtolist;
//		
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
}
