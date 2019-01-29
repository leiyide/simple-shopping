package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyd.entity.Order;
import com.lyd.userdao.OrderDao;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class OrderDaoImpl extends JDBCTemplate implements OrderDao {
	/**
	 * 根据商品的id得到购物车里的所有商品（此方法可以得到购买的件数）
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Order> getProduct(final int userId) throws Exception {
		//定义一个集合存放
		final List<Order> list=new ArrayList<Order>();
		//sql
		String sql="select* from hwua_cart where userid=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, userId);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//订单实体类的对象
						Order o=new Order();
						o.setPid(rs.getInt("pid"));
						o.setQuantity(rs.getInt("quantity"));
						o.setUserid(rs.getInt("userid"));
						list.add(o);
					}
				}
			}
		});
		return list;
	}
	
	
	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteCar(final int userId) throws Exception {
		//sql语句
		String sql="delete from hwua_cart where userid=?";
		//调用模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, userId);
			}
		});
	}

	/**
	 * 根据商品id和用户id得到库存量
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public Order getNum(final int userId,final int pid) throws Exception {
		//订单对象
		final Order o=new Order();
		//sql语句
		String sql="select * from hwua_cart,hwua_product,hwua_user where pid=hp_id and pid=? and userid=hu_user_id and userid=?";
		//调用模板中的方法
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, pid);
				pstmt.setInt(2, userId);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						o.setPid(rs.getInt("pid"));
						//o.setOid(rs.getInt("ho_id"));
						o.setQuantity(rs.getInt("quantity"));
						o.setUserid(rs.getInt("userid"));
						o.setPhid(rs.getInt("hp_id"));
						o.setName(rs.getString("hp_name"));
						o.setUserName(rs.getString("hu_user_name"));
						o.setAddress(rs.getString("hu_address"));
						o.setParentid(rs.getInt("hpc_id"));
						o.setChildid(rs.getInt("hpc_child_id"));
						o.setPrice(rs.getInt("hp_price"));
						o.setStock(rs.getInt("hp_stock"));
						o.setFile(rs.getString("hp_file_name"));
					}
				}
			}
		});
		return o;
	}

	
	/**
	 * 根据商品id和用户id修改stock(货存)
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateNum(final int pid,final int stock) throws Exception {
		//sql语句
		String sql="update hwua_product set hp_stock=? where hp_id=?";
		//调用模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, stock);
				pstmt.setInt(2, pid);
			}
		});
	}

	
	/**
	 * 将更新的数据写入订单中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateOrder(final Order order,final int allPrice) throws Exception {
		//sql语句
		String sql="insert into hwua_order values(seq_order.nextval,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,1,1)";
		//调用模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, order.getUserid());
				pstmt.setString(2, order.getUserName());
				pstmt.setString(3, order.getAddress());
				pstmt.setString(4, order.getCreateTime());
				pstmt.setInt(5, allPrice);
			}
		});
	}

	
	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	@Override
	public Order getOrder() throws Exception {
		//实体类订单对象
		final Order or=new Order();
		//sql语句
		String sql="select * from hwua_cart,hwua_order where userid=ho_user_id";
		//调用模板中的方法
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					if(rs.next()) {
						or.setOid(rs.getInt("ho_id"));
						or.setPid(rs.getInt("pid"));
						or.setUserid(rs.getInt("ho_user_id"));
						or.setQuantity(rs.getInt("quantity"));
						or.setUserName(rs.getString("ho_user_name"));
						or.setAddress(rs.getString("ho_user_address"));
						or.setCreateTime(rs.getString("ho_create_time"));
						or.setUserPrice(rs.getInt("ho_cost"));
						or.setState(rs.getInt("ho_status"));
						or.setType(rs.getInt("ho_type"));	
					}
				}
			}
		});
		return or;
	}
	
	/**
	 * 将更新的数据写入订单详情中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateOrderDetail(final Order order,final int pidPrice) throws Exception {
		//sql语句
		String sql="insert into hwua_order_detail values(seq_order_detail.nextval,?,?,?,?)";
		//模板中的方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, order.getOid());
				pstmt.setInt(2,order.getPid());
				pstmt.setInt(3, order.getQuantity());
				pstmt.setInt(4, pidPrice);
			}
		});


	}


	//得到订单详情
	@Override
	public List<Order> getOrderList() throws Exception {
		//集合对象
		final List<Order> list=new ArrayList<Order>();
		//sql语句
		String sql="select * from hwua_order a,hwua_order_detail b where a.ho_id=b.ho_id";
		//调用模板
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//定义一个实体类的对象
						Order or=new Order();
						or.setOid(rs.getInt("ho_id"));
						or.setUserid(rs.getInt("ho_user_id"));
						or.setPid(rs.getInt("hp_id"));
						or.setUserName(rs.getString("ho_user_name"));
						or.setAddress(rs.getString("ho_user_address"));
						or.setCreateTime(rs.getString("ho_create_time"));
						or.setPidPrice(rs.getInt("ho_cost"));
						or.setUserPrice(rs.getInt("hod_cost"));
						list.add(or);
					}
				}
			}
		});
		return list;
	}


	/**
	 * 得到商品的列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Order> getShoppingList() throws Exception {
		//将数据存放在集合中
		final List<Order> list=new ArrayList<Order>();
		//sql语句
		String sql="select * from hwua_product a,hwua_order_detail b where a.hp_id=b.hp_id";
		//调用模板中的方法
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						//实体类对象
						Order order=new Order();
						order.setPid(rs.getInt("hp_id"));
						order.setName(rs.getString("hp_name"));
						order.setPidPrice(rs.getInt("hp_price"));
						order.setStock(rs.getInt("hp_stock"));
						order.setParentid(rs.getInt("hpc_id"));
						order.setChildid(rs.getInt("hpc_child_id"));
						order.setFile(rs.getString("hp_file_name"));
						order.setOid(rs.getInt("ho_id"));
						order.setQuantity(rs.getInt("hod_quantity"));
						order.setUserPrice(rs.getInt("hod_cost"));
						list.add(order);
					}
				}
			}
		});
		return list;
	}
}
