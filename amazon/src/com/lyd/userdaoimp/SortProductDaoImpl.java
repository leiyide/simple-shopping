package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.Product;
import com.lyd.entity.SortProduct;
import com.lyd.userdao.SortProductDao;
import com.lyd.util.PageView;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class SortProductDaoImpl extends JDBCTemplate implements SortProductDao {

	//记录页数的变量
	int count;
	
	
	/**
	 * 查找产品分类的父节点
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SortProduct> parentProduct() throws Exception {
		//定义一个list集合用来存放父节点
		final List<SortProduct> parentList=new ArrayList<SortProduct>();
		//sql语句
		String sql="select * from hwua_product_category t where t.hpc_id=t.hpc_parent_id";
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//商品分类的对象
						SortProduct sp=new SortProduct();
						sp.setId(rs.getInt("hpc_id"));
						sp.setName(rs.getString("hpc_name"));
						sp.setParentId(rs.getInt("hpc_parent_id"));
						parentList.add(sp);
					}
				}
			}
		});
		return parentList;
	}

	/**
	 *查找产品分类中的子节点
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SortProduct> childProduct() throws Exception {
		//定义一个list集合用来存放父节点
		final List<SortProduct> parentList=new ArrayList<SortProduct>();
		//sql语句
		String sql="select * from hwua_product_category t where t.hpc_id!=t.hpc_parent_id";
		query(sql, null, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//商品分类的对象
						SortProduct sp=new SortProduct();
						sp.setId(rs.getInt("hpc_id"));
						sp.setName(rs.getString("hpc_name"));
						sp.setParentId(rs.getInt("hpc_parent_id"));
						parentList.add(sp);
					}
				}
			}
		});
		return parentList;
	}


	/**
	 * 父节点分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> getSortList(final int currentPage,final int parentid) throws Exception {
		//定义返回值的集合
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from(select rownum a,hwua_product.* from (select * from hwua_product where hpc_id=?) hwua_product where rownum<=?) where a>?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//得到哪一个父节点
				pstmt.setInt(1, parentid);
				//结束行， 每页显示行数*当前页
				pstmt.setInt(2, PageView.PAGE_SIZE*currentPage);
				//开始行，每页显示行数*（当前页—1）
				pstmt.setInt(3, PageView.PAGE_SIZE*(currentPage-1));
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
	 * 百货查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	@Override
	public int getSortCount(final int parentid) throws Exception {
		//sql语句
		String sql="select count(*) from hwua_product where hpc_id=?";
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
						//全局变量
						count = rs.getInt(1);
					}
				}
			}
		});
		return count;
	}
	  
	/**
	 *子节点分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> cgetSortList(final int currentPage, final int childid) throws Exception {
		//定义返回值的集合
		final List<Product> list=new ArrayList<Product>();
		//sql语句
		String sql="select * from(select rownum a,hwua_product.* from (select * from hwua_product where hpc_child_id=?) hwua_product where rownum<=?) where a>?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//得到哪一个父节点
				pstmt.setInt(1, childid);
				//结束行， 每页显示行数*当前页
				pstmt.setInt(2, PageView.PAGE_SIZE*currentPage);
				//开始行，每页显示行数*（当前页—1）
				pstmt.setInt(3, PageView.PAGE_SIZE*(currentPage-1));
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
	 * 子节点查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	@Override
	public int cgetSortCount(final int childid) throws Exception {
		//sql语句
		String sql="select count(*) from hwua_product where hpc_child_id=?";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, childid);
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
