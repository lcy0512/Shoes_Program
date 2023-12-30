package com.javalec.function;

import java.io.FileInputStream;

public class Dto_pjh {
	//Field
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
	String ddate;	//date도 int 인트로?
	int dqty;
	int dprice;
	int product_p_seq;
	//sale
	int sseq;
	int sp_seq1;
	int sp_seq2;
	String sdate;	
	int sprice;
	int sqty;
	//save
	int saseq;	
	int sap_seq;
	
	//--------------------------------Constructor----------------------
	
	

	
	
	public int getP_seq() {
		return p_seq;
	}
	

	
	
	
	






	public Dto_pjh(String sdate, int sprice, int sqty) {
		super();
		this.sdate = sdate;
		this.sprice = sprice;
		this.sqty = sqty;
	}












	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public String getPbrand() {
		return pbrand;
	}
	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getPcolor() {
		return pcolor;
	}
	public void setPcolor(String pcolor) {
		this.pcolor = pcolor;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	public int getPsize() {
		return psize;
	}
	public void setPsize(int psize) {
		this.psize = psize;
	}
	public FileInputStream getFile() {
		return file;
	}
	public void setFile(FileInputStream file) {
		this.file = file;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCpw() {
		return cpw;
	}
	public void setCpw(String cpw) {
		this.cpw = cpw;
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
	public int getDseq() {
		return dseq;
	}
	public void setDseq(int dseq) {
		this.dseq = dseq;
	}
	public int getDp_seq1() {
		return dp_seq1;
	}
	public void setDp_seq1(int dp_seq1) {
		this.dp_seq1 = dp_seq1;
	}
	public int getP_seq2() {
		return p_seq2;
	}
	public void setP_seq2(int p_seq2) {
		this.p_seq2 = p_seq2;
	}
	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	public int getDqty() {
		return dqty;
	}
	public void setDqty(int dqty) {
		this.dqty = dqty;
	}
	public int getDprice() {
		return dprice;
	}
	public void setDprice(int dprice) {
		this.dprice = dprice;
	}
	public int getProduct_p_seq() {
		return product_p_seq;
	}
	public void setProduct_p_seq(int product_p_seq) {
		this.product_p_seq = product_p_seq;
	}
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
	}
	public int getSp_seq1() {
		return sp_seq1;
	}
	public void setSp_seq1(int sp_seq1) {
		this.sp_seq1 = sp_seq1;
	}
	public int getSp_seq2() {
		return sp_seq2;
	}
	public void setSp_seq2(int sp_seq2) {
		this.sp_seq2 = sp_seq2;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public int getSaseq() {
		return saseq;
	}
	public void setSaseq(int saseq) {
		this.saseq = saseq;
	}
	public int getSap_seq() {
		return sap_seq;
	}
	public void setSap_seq(int sap_seq) {
		this.sap_seq = sap_seq;
	}
	
	
	//--------------------------------method-------------------------
	
	
}
