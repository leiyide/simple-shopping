package com.oracle.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static void close(ResultSet rs,
			Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt,
			Connection conn){
		//调用上面重载的close方法即可,不需要传null
		close(null,stmt,conn);
	}
	
	public static void close(Statement stmt){
		close(null,stmt,null);
	}
	
	public static void close(ResultSet rs,Statement stmt){
		close(rs,stmt,null);
	}
	
	public static void close(Connection conn){
		close(null,null,conn);
	}
}
