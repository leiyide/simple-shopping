package com.oracle.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	//设置占位符的值
	public void setValues(PreparedStatement pstmt)
			throws SQLException;
}
