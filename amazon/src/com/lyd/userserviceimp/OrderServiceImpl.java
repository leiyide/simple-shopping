package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.Order;
import com.lyd.userdao.OrderDao;
import com.lyd.userdaoimp.OrderDaoImpl;
import com.lyd.userservicedao.OrderServiceDao;

public class OrderServiceImpl implements OrderServiceDao {

	//订单数据访问层中的对象
	OrderDao od=new OrderDaoImpl();

	/**
	 * 处理根据商品的id得到购物车里的所有商品（此方法可以得到购买的件数,单价）
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Order> getProduct(int userId) throws Exception {
		return od.getProduct(userId);
	}
	/**
	 * 处理清空购物车
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteCar(int userId) throws Exception {
		if(od.deleteCar(userId)>0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 处理根据商品id和用户id得到库存量
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public Order getNum(int userId, int pid) throws Exception {
		return od.getNum(userId, pid);
	}
	/**
	 * 处理根据商品id和用户id修改stock(货存)
	 * @param userId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateNum(int pid, int stock) throws Exception {
		if(od.updateNum(pid, stock)>0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 处理将更新的数据写入订单中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateOrder(Order order, int allPrice) throws Exception {
		if(od.updateOrder(order, allPrice)>0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 处理查询订单
	 * @return
	 * @throws Exception
	 */
	@Override
	public Order getOrder() throws Exception {
		return od.getOrder();
	}

	
	/**
	 * 处理将更新的数据写入订单详情中
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateOrderDetail(Order order, int pidPrice) throws Exception {
		if(od.updateOrderDetail(order, pidPrice)>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	/**
	 * 得到订单信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Order> getOrderList() throws Exception {
		return od.getOrderList();
	}
	
	/**
	 * 得到商品的列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Order> getShoppingList() throws Exception {
		return od.getShoppingList();
	}
	
	
}
