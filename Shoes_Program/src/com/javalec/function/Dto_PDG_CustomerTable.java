package com.javalec.function;

public class Dto_PDG_CustomerTable {
	
	/*
	 * Description : Data transfer object
	 * Date :  2023.12.26
	 * Author : D. Forrest Park
	 */
	
	// Field 
	// variables in customer DB table
	String customer_id;
	String name;
	String pw;
	String telno;
	String email;
	
	
	//<Constructor>// 
	
	public Dto_PDG_CustomerTable() {
		// TODO Auto-generated constructor stub
	}

	// 1. constructor for customer insert + 회원 정보 조회를 위한생성자. 
	public Dto_PDG_CustomerTable(String customer_id, String name, String pw, String telno, String email) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.pw = pw;
		this.telno = telno;
		this.email = email;
	}

	// 2. constructor for customer id Overlap
	
	
	
	// 3.  회원정보 조회 를 위한 생성자
	
	
	
	
	//<Methods >//
	// Getter, Setter
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	

}
