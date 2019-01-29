package com.oracle.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler {
	public void handleRs(ResultSet rs) 
			throws SQLException;
}
