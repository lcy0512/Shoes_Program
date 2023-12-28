package com.javalec.function;

public class Dto {
	// Field
	int p_seq;
	String brand;
	String name;
	String price;
	String color;
	String qty;
	String size;
	String date;

	// Construct
	public Dto() {

	}

	public Dto(int p_seq) {
		super();
		this.p_seq = p_seq;
	}

	public Dto(int p_seq, String brand, String name, String price, String color, String qty, String size, String date) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.color = color;
		this.qty = qty;
		this.size = size;
		this.date = date;
	}

	public Dto(int p_seq, String brand, String name, String price, String color, String qty, String size) {
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

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
