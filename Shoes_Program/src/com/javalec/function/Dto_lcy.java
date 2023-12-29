package com.javalec.function;

import java.io.FileInputStream;
import java.sql.Date;

public class Dto_lcy {
	
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	// Field
	// Database Column과 이름을 같게
	int p_seq;
	String brand;
	String name;
	String price;
	String color;
	int qty;
	int size;
	int saveqty;
	Date date;
//	FileInputStream file;
	
	
	// Constructor
	public Dto_lcy() {
		// TODO Auto-generated constructor stub
	}
	
//	wkSeq, wkBrand, wkName, wkPrice, wkColor, wkSize, wkQty, wkSaveQty
	
	public Dto_lcy(int p_seq, String brand, String name, String price, String color, int size) {
		// Dao_lcy,selectList()에서 사용
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.size = size;
	}
	
	public Dto_lcy(int p_seq, String brand, String name, String price, String color, int size, int qty) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.size = size;
		this.qty = qty;
	}

	
	public Dto_lcy(int p_seq, String brand, String name, String price, String color, int size, int saveqty, int qty) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
		this.saveqty = saveqty;
	}

	// OrderList - showOrderList
	// 
	public Dto_lcy(int p_seq, String brand, String name, String price, String color, int size, int saveqty, Date date) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.size = size;
		this.saveqty = saveqty;
		this.date = date;
	}
	
	
	
	// Method
	public int getP_seq() {
		return p_seq;
	}


	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getSaveQty() {
		return saveqty;
	}


	public void setSaveQty(int saveqty) {
		this.saveqty = saveqty;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
