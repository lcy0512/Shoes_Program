package com.javalec.function;

import java.awt.Color;

public class ShareVar {

	public static String dbName = "jdbc:mysql://127.0.0.1:3306/shoe_program?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	public static String dbUser = "root";
	public static String dbPass = "qwer1234";
	
	
	
	public static String userID = "";
	/// Window setting
	public static int position_window_x = 600;
	public static int position_window_y = 250;
	public static int window_size_x = 800;
	public static int window_size_y = 600;
	
	// setBounds(ShareVar.position_window_x, ShareVar.position_window_y, ShareVar.window_size_x, ShareVar.window_size_y);
	// contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
	
	// Bulletin setting
	public static int bulletin_x = 800;
	public static int bulletin_y = 350;
	
	// Window color setting
	public static int RGB_red = 247;
	public static int RGB_green = 246;
	public static int RGB_blue= 248;
	
			

	/*
	 * Descritipon"500	, 300
	 * Author
	 * Date :
	 * update:
	 *  		1. db 에 접근할 것이다. 
	 *  		2. login 은 static 을 쓸것이다. 
	 *  		
	 */
	

	// image ( 만약 고객 이미지 업로드가 필요할 경우 사용!
	public static int filename = 0;
	
	
	
} // End

