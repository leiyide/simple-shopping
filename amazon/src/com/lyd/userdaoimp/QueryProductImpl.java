package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.Product;
import com.lyd.userdao.QueryProductDao;
import com.lyd.util.PageView;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class QueryProductImpl extends JDBCTemplate implements QueryProductDao {

	//定义一个全局变量
	int count;

	/**
	 *商品名和描述分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> queryProductList(final int currentPage, final String productName) throws Exception {
		//定义返回值的集合
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from(select rownum a,hwua_product.* from (select * from hwua_product where hp_name like ? or hp_description like ?) hwua_product where rownum<=?) where a>?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//查询商品名称
				pstmt.setString(1, "%"+productName+"%");
				//商品描述
				pstmt.setString(2, "%"+productName+"%");
				//结束行， 每页显示行数*当前页
				pstmt.setInt(3, PageView.PAGE_SIZE*currentPage);
				//开始行，每页显示行数*（当前页—1）
				pstmt.setInt(4, PageView.PAGE_SIZE*(currentPage-1));
			}
		}, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//定义产品类的对象
						Product pro=new Product();
						pro.setId(rs.getInt("hp_id"));
						pro.setName(rs.getString("hp_name"));
						pro.setDescription(rs.getString("hp_description"));;
						pro.setPrice(rs.getString("hp_price"));
						pro.setStock(rs.getString("hp_stock"));
						pro.setHpcId(rs.getInt("hpc_id"));
						pro.setChildId(rs.getInt("hpc_child_id"));
						pro.setFile(rs.getString("hp_file_name"));
						list.add(pro);
					}
				}
			}
		});
		return list;
	}


	/**
	 * 商品名和描述查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	@Override
	public int queryProductCount(final String productName) throws Exception {
		//sql语句
		String sql="select count(*) from hwua_product where hp_name like ? or hp_description like ?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, "%"+productName+"%");
				pstmt.setString(2, "%"+productName+"%");
			}
		}, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//全局变量
						count = rs.getInt(1);
					}
				}
			}
		});
		return count;
	}
}

