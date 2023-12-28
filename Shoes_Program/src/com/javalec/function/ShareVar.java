package com.javalec.function;

public class ShareVar {
	/*
	 * Descritipon"
	 * Author
	 * Date :
	 * update:
	 *  		1. db 에 접근할 것이다. 
	 *  		2. login 은 static 을 쓸것이다. 
	 *  		
	 */
	
	// Field
	public static String dbName =  "jdbc:mysql://127.0.0.1/shoe_program?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	public static String dbUser = "root";
	public static String dbPass = "qwer1234"; // final 은 써도 되고 안써도 되는데 share 니까 이렇게 쓰는데 배신자가 있을 경우 final 을 쓸것. 
	
	// image ( 만약 고객 이미지 업로드가 필요할 경우 사용!
	public static int filename = 0;
	
	
	
} // End
