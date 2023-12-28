package com.javalec.function;

public class Dto_test_imageOnTable {

	// F
	int p_seq;
	String brand;
	String name;
	int price;
	String image;
	String fake_filename;

	// Constructor

	public Dto_test_imageOnTable() {
		// TODO Auto-generated constructor stub
	}

	// table 에 상품목록을 띄우기 위한 생성자 .

	public Dto_test_imageOnTable(int p_seq, String brand, String name, int price, String fake_filename) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.fake_filename = fake_filename;

	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFake_filename() {
		return fake_filename;
	}

	public void setFake_filename(String fake_filename) {
		this.fake_filename = fake_filename;
	}

}
