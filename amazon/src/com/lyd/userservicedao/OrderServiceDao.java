package com.lyd.userservicedao;

import java.util.List;

import com.lyd.entity.Order;

public interface OrderServiceDao {
	/**
	 * 处理根据商品的id得到购物车里的所有商品（此方法可以得到购买的件数,单价）
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Order> getProduct(int userId) throws Exception;
	
	/**
	 * 处理清空购物车
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCar(int userId) throws Exception;
	
	/**
	 * 处理根据商品id和用户id得到库存量
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public Order getNum(int userId,int pid) throws Exception;
	
	/**
	 * 处理根据商品id和用户id修改stock(货存)
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public boolean updateNum(int pid,int stock) throws Exception;
	
	/**
	 * 处理将更新的数据写入订单中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrder(Order order,int allPrice) throws Exception;
	
	/**
	 * 处理查询订单
	 * @return
	 * @throws Exception
	 */
	public Order getOrder() throws Exception;
	
	
	/**
	 * 处理将更新的数据写入订单详情中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderDetail(Order order,int pidPrice) throws Exception;
	
	
	/**
	 * 处理得到订单信息
	 * @return
	 * @throws Exception
	 */
	public List<Order> getOrderList() throws Exception; 
	
	/**
	 * 处理得到商品的列表
	 * @return
	 * @throws Exception
	 */
	public List<Order> getShoppingList() throws Exception;
	
	 
}
