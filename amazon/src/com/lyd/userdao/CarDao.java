package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.ShopCar;
import com.lyd.entity.User;

public interface CarDao {
	
	/**
	 * 根据商品id进行判断，存在该商品则在数量上直接加上之前的数量
	 * @return
	 * @throws Exception
	 */
	public ShopCar getShopCar(int pid,int userid) throws Exception;
	
	/**
	 * 根据商品id修改数量
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public int updateShopCar(int quantity,int pid,int userid) throws Exception;
	
	
	/**
	 * 将页面上显示的信息插入到数据库中的购物车表中
	 * @throws Exception
	 */
	public int addProduct(ShopCar car) throws Exception;
	
	/**
	 * 根据当前登录的用户id进入当前的购物车
	 * @return
	 * @throws Exception
	 */
	public List<ShopCar> queryUser(int userid) throws Exception;
	
	/**
	 * 根据商品id和用户id删除购物车中指定的商品
	 * @param pid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int deleteCar(int pid,int userid) throws Exception; 
}
