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

import com.javalec.function.ShareVar;

public class Dao_test_imageOnTable {

	
	
    private final String url_mysql = ShareVar.dbName;
    private final String id_mysql = ShareVar.dbUser;
    private final String pw_mysql = ShareVar.dbPass;
    
    
    
	int p_seq;
	String brand;
	String name;
	int price;
	FileInputStream file;
	
	public Dao_test_imageOnTable() {
		// TODO Auto-generated constructor stub
	}

	public Dao_test_imageOnTable(int p_seq, String brand, String name, int price, FileInputStream file) {
		super();
		this.p_seq = p_seq;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.file = file;
	}
	
	
	// 검색 결과를 Array List 로 만든다. 
	
	
	
	public ArrayList<Dto_test_imageOnTable> allProductLsit(){
		
		ArrayList <Dto_test_imageOnTable> allProductList= new ArrayList<Dto_test_imageOnTable>();
		
		String fetchAllProductQeury= "select p_seq, brand, name, price , image from product ";
		
		
		try {
			
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(fetchAllProductQeury);
            
            
            while(resultSet.next()) {
            	
            	int currentRow_p_seq = resultSet.getInt(1);
            	
            	String currentRow_brand = resultSet.getString(2);
            	String currentRow_name = resultSet.getString(3);
            	int currentRow_price = resultSet.getInt(4);
            	String currentRow_fake_filename = resultSet.getString(3)+"_image";
            	
            	// File
            	
            	File image = new File("./"+ currentRow_fake_filename);
            	FileOutputStream writingFileOnHardware = new FileOutputStream(image);
            	InputStream loadOnTheFileWithFetchedImage = resultSet.getBinaryStream(5);
            	
            	byte[] buffer  =new byte[1024];
            	while (loadOnTheFileWithFetchedImage.read(buffer)>0) {
            		writingFileOnHardware.write(buffer);
            		
            	}
            	Dto_test_imageOnTable dto_imageOnTableWithoutImage = 
            			new Dto_test_imageOnTable(currentRow_p_seq, currentRow_brand, currentRow_name, currentRow_price, currentRow_fake_filename)
            			
            			;
            	
            	
            	
            	allProductList.add(dto_imageOnTableWithoutImage);
            }
            connection.close();
            
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return allProductList;
	}
	
	
	
	
	
	
	
	
	
	
	

    
    
	
	
	
	
	
}
