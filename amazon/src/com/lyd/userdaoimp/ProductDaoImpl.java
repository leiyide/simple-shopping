package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.Product;
import com.lyd.userdao.ProductDao;
import com.lyd.util.PageView;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class ProductDaoImpl extends JDBCTemplate implements ProductDao {

	//定义一个全局变量接收商品的条数
	int count;

	/**
	 * 父节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Product> getParentProduct(final int parentid) throws Exception {
		//定义一个list集合存放
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from hwua_product where hpc_id=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, parentid);
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
	 * 子节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Product> getChildProduct(final int chiledid) throws Exception {
		//定义一个list集合存放
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from hwua_product where hpc_child_id=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, chiledid);
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
	 * 根据商品id查询数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Product getProductInfo(final int productid) throws Exception {
		//定义产品类的对象
		final Product pro=new Product();
		//sql语句
		String sql="select * from hwua_product where hp_id=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, productid);
			}
		}, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						pro.setId(rs.getInt("hp_id"));
						pro.setName(rs.getString("hp_name"));
						pro.setDescription(rs.getString("hp_description"));;
						pro.setPrice(rs.getString("hp_price"));
						pro.setStock(rs.getString("hp_stock"));
						pro.setHpcId(rs.getInt("hpc_id"));
						pro.setChildId(rs.getInt("hpc_child_id"));
						pro.setFile(rs.getString("hp_file_name"));
					}
				}
			}
		});
		return pro;
	}


	
	/**
	 * 分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> getProductList(final int currentPage) throws Exception {
		//定义返回值的集合
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from(select rownum r,hwua_product.* from hwua_product where rownum<=?) where r>?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//结束行， 每页显示行数*当前页
				pstmt.setInt(1, PageView.PAGE_SIZE*currentPage);
				//开始行，每页显示行数*（当前页—1）
				pstmt.setInt(2, PageView.PAGE_SIZE*(currentPage-1));
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
	 * 查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */	
	@Override
	public int getProductCount() throws Exception {
		
		//sql语句
		String sql="select count(*) from hwua_product";
		query(sql, null, new ResultSetHandler() {
			
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
