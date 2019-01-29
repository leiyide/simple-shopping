package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.ShopCar;
import com.lyd.entity.User;
import com.lyd.userdao.CarDao;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class CarDaoImpl extends JDBCTemplate implements CarDao{
	
	/**
	 * 将页面上显示的信息插入到数据库中的购物车表中
	 * @return 成功的条数
	 * @throws Exception
	 */
	@Override
	public int addProduct(final ShopCar car) throws Exception {
		//sql语句
		String sql="insert into hwua_cart values(seq_car.nextval,?,?,?)";
		//调用模板中update方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, car.getPid());
				pstmt.setInt(2, car.getQuantity());
				pstmt.setInt(3, car.getUserId());
			}
		});
	}


	/**
	 * 根据当前登录的用户id进入当前的购物车
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ShopCar> queryUser(final int userid) throws Exception {
		//定义一个集合用来存放数据
		final List<ShopCar> list=new ArrayList<ShopCar>();
		//sql语句
		String sql="select * from hwua_cart,hwua_product where pid=hp_id and userid=?";
		//调用模板中的查询方法
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, userid);	
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//购物车实体类的对象
						ShopCar car=new ShopCar();
						car.setPid(rs.getInt("pid"));
						car.setQuantity(rs.getInt("quantity"));
						car.setUserId(rs.getInt("userid"));
						car.setId(rs.getInt("hp_id"));
						car.setName(rs.getString("hp_name"));
						car.setPrice(rs.getString("hp_price"));
						car.setStock(rs.getString("hp_stock"));
						car.setFile(rs.getString("hp_file_name"));
						list.add(car);
					}
				}
			}
		});
		return list;
	}


	/**
	 * 根据商品id进行判断，存在该商品则在数量上直接加上之前的数量
	 * @return
	 * @throws Exception
	 */
	@Override
	public ShopCar getShopCar(final int pid,final int userid) throws Exception {
		//用来存放查询的结果
		final ShopCar sc=new ShopCar();
		//sql语句
		String sql="select * from hwua_cart where pid=? and userid=?";
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, pid);
				pstmt.setInt(2,userid);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					if(rs.next()) {
						sc.setPid(rs.getInt("pid"));
						sc.setQuantity(rs.getInt("quantity"));
						sc.setUserId(rs.getInt("userid"));
					}
				}
			}
		});
		return sc;
	}

	/**
	 * 根据商品id修改数量
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateShopCar(final int quantity,final int pid,final int userid) throws Exception {
		//定义实体类的对象
		ShopCar sc=new ShopCar();
		//sql语句
		String sql="update hwua_cart set quantity=? where pid=? and userid=?";
		//调用模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, quantity);
				pstmt.setInt(2, pid);
				pstmt.setInt(3, userid);
			}
		});
		
	}


	/**
	 * 根据商品id和用户id删除购物车中指定的商品
	 * @param pid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteCar(final int pid, final int userid) throws Exception {
		//sql语句
		String sql="delete from hwua_cart where pid=? and userid=?";
		//调用模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, pid);
				pstmt.setInt(2, userid);
			}
		});
	}
	
}
