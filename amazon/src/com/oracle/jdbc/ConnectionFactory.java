package com.oracle.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String UNAME;
	private static String UPASS;
	
	//鍦ㄧ被琚姞杞芥椂鎵ц,鍙細鎵ц涓�娆�
	static{
		//浠庨厤缃枃浠惰鍙栭敭鍊煎
		//瀛樺偍閿�煎--闆嗗悎
		Properties prop = new Properties();		
		try {
			//鎶妏roperties鏂囦欢涓殑鏁版嵁鍔犺浇鍒皃rop瀵硅薄涓�
			//瑕佹眰:ConnectionFactory绫诲拰jdbcinfo.properties鍦ㄥ悓涓�涓寘涓�
			prop.load(ConnectionFactory.class.
					getResourceAsStream(
					"jdbcinfo.properties"));
			//value=getProperty(key):閫氳繃閿幏鍙栧��
			DRIVER = prop.getProperty("driver");
			URL = prop.getProperty("url");
			UNAME = prop.getProperty("uname");
			UPASS = prop.getProperty("upass");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(
					URL,
					UNAME,
					UPASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(
				ConnectionFactory.getConnection());
	}
}
