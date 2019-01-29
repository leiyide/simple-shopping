package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.ProductNew;
import com.lyd.userdao.ProductNewDao;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class ProductNewDaoImpl extends JDBCTemplate implements ProductNewDao {

	/**
	 * 得到新闻中的所有信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProductNew> getAllNew() throws Exception {
		//存放数据对象的集合
		final List<ProductNew> newList=new ArrayList<ProductNew>();
		//sql语句
		String sql="select * from hwua_news";
		//查询
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						ProductNew pn=new ProductNew();
						pn.setId(rs.getInt("hn_id"));
						pn.setTitle(rs.getString("hn_title"));
						pn.setContent(rs.getString("hn_content"));
						pn.setCreateTime(rs.getString("hn_create_time"));
						newList.add(pn);
					}
				}
			}
		});
		return newList;
	}

	
	/**
	 * 根据商品的id得到信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProductNew getNewById(final int id) throws Exception {
		//新闻实体类的对象
		final ProductNew pn=new ProductNew();
		//sql语句
		String sql="select * from hwua_news where hn_id=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, id);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						pn.setId(rs.getInt("hn_id"));
						pn.setTitle(rs.getString("hn_title"));
						pn.setContent(rs.getString("hn_content"));
						pn.setCreateTime(rs.getString("hn_create_time"));
					}
				}
			}
		});
		return pn;
	}

}
