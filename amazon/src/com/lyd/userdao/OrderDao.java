package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.Order;

public interface OrderDao {
	/**
	 * 根据商品的id得到购物车里的所有商品（此方法可以得到购买的件数,单价）
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Order> getProduct(int userId) throws Exception;
	
	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int deleteCar(int userId) throws Exception;
	
	/**
	 * 根据商品id和用户id得到库存量
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public Order getNum(int userId,int pid) throws Exception;
	
	/**
	 * 根据商品id和用户id修改stock(货存)
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public int updateNum(int pid,int stock) throws Exception;
	
	/**
	 * 将更新的数据写入订单中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int updateOrder(Order order,int allPrice) throws Exception;
	
	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	public Order getOrder() throws Exception;
	
	
	/**
	 * 将更新的数据写入订单详情中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int updateOrderDetail(Order order,int pidPrice) throws Exception;
	
	
	/**
	 * 得到订单信息
	 * @return
	 * @throws Exception
	 */
	public List<Order> getOrderList() throws Exception; 
	
	
	/**
	 * 得到商品的列表
	 * @return
	 * @throws Exception
	 */
	public List<Order> getShoppingList() throws Exception;
}
