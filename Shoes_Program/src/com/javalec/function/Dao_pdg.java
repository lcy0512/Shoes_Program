package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_pdg {
	
	
	
	
	
	
	/*
	 * Description :  customer Tabel query Data Access Object
	 * Author : D Forrest Park 
	 * Date : 2023.12.27
	 * Update :
	 * 		1.  고객 회원 가입 Query  등록 
	 *
	 */
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	String customer_id;
	String name;
	String pw;
	String telno;
	String email;
	
	
	
	// Constructor
	public Dao_pdg() {
		// TODO Auto-generated constructor stub
	}

	// 1. Insert 를 위한 생성자  + 3. 회원 수정을 위하여 회원정보를 가져오기위한 생성자. 
	public Dao_pdg(String customer_id, String name, String pw, String telno, String email) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.pw = pw;
		this.telno = telno;
		this.email = email;
	}
	
	// 2.  중복확인을 위한 생성자 .
	
	public Dao_pdg(String customer_id) {
		super();
		this.customer_id = customer_id;
	}
	

	
	

	
	
	
	
	
	
	// < Methods> //

	// 1. Insert Action 
	public boolean insertAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement statement = connection.createStatement();
			
			String A = "insert into customer (customer_id, name, pw, telno, email";
			String B = " ) values(?,?,?,?,?)";
			
			ps = connection.prepareStatement(A+B);
			ps.setString(1,customer_id);
			ps.setString(2,name);
			ps.setString(3,pw);
			ps.setString(4,telno);
			ps.setString(5,email);
			
			ps.executeUpdate();
			
			connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			return false; // return 은 흘러가지않음. 
		}
		return true ; // 입력이 잘되면 true	
	}
	
	
	public Dao_pdg(String customer_id, String pw) {
		super();
		this.customer_id = customer_id;
		this.pw = pw;
	}

	// 2. 회원 가입시 아이디 중복확인 
	public int isIdAlreadyExist() {
		
		int isIdAlreadyExist = 0;
		
		String overlapQuery ="select count(name) from customer where customer_id = '" + customer_id + "'";
		
		System.out.println(1);
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(overlapQuery);
			
			if(resultSet.next()) {
				
				isIdAlreadyExist = resultSet.getInt(1);
						
				
				System.out.println(2);
			}
			
			System.out.println(isIdAlreadyExist);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(3);
		return isIdAlreadyExist;
		
	}
	
	
	// 3. 로그인 화면에서 아이디 패스워드 대조 
	
	public boolean idPwCheck() {
		
		boolean isExistUser = false;
		String pwOfSearchedUser;
		
		String overlapQuery ="select pw from customer where customer_id = '" + customer_id + "'";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(overlapQuery);
			
			if(resultSet.next()) {
				
				pwOfSearchedUser = resultSet.getString(1);
				
				if( pw.equals(pwOfSearchedUser)){
					
					isExistUser = true;
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isExistUser;
	}
	
	
	
	// 4. 회원정보수정을 위한 조회 및 수정 
	
	public ArrayList<Dto_PDG_CustomerTable> fetchUserInfo(String userId) {
		
		ArrayList<Dto_PDG_CustomerTable> userInfoList = new ArrayList<Dto_PDG_CustomerTable>();
		
		String customer_id;
		String name;
		String pw;
		String telno;
		String email;
		
		String fetchUserInfo =" select customer_id , name, pw, telno, email from customer  where customer_id = '"+ userId+"'";
		
		try {
			
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(fetchUserInfo);
			
			if(resultSet.next()) {
				
				String sql_customer_id = resultSet.getString(1);
				String sql_name = resultSet.getString(2);
				String sql_pw = resultSet.getString(3);
				String sql_telno = resultSet.getString(4);
				String sql_email = resultSet.getString(5);
				
				Dto_PDG_CustomerTable currentRow = new Dto_PDG_CustomerTable(sql_customer_id, sql_name, sql_pw, sql_telno, sql_email);
				userInfoList.add(currentRow);

			}
			
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return userInfoList;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
} // End
