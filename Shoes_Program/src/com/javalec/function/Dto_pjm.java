package com.javalec.function;


public class Dto_pjm {
	// Field
	int p_seq;
	String brand;
	String name;
	int price;
	String color;
	int qty;
	int size;

	// Construct
	public Dto_pjm() {

	}

	public Dto_pjm(int p_seq) {
		super();
		this.p_seq = p_seq;
	}

	public Dto_pjm(int p_seq, String brand, String name, int price, String color, int qty, int size ) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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





}
