package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.ShopCar;
import com.lyd.entity.User;
import com.lyd.userdao.CarDao;
import com.lyd.userdaoimp.CarDaoImpl;
import com.lyd.userservicedao.CarServiceDao;

public class CarServiceImpl implements CarServiceDao{
	
	//数据访问层中获取购物车中的数据对象
	CarDao cd=new CarDaoImpl();
	
	
	/**
	 * 处理将页面上显示的信息插入到数据库中的购物车表中
	 * @throws Exception
	 */
	@Override
	public boolean addProduct(ShopCar car) throws Exception {
		if(cd.addProduct(car)>0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 处理根据当前登录的用户id进入当前的购物车
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ShopCar> queryUser(int userid) throws Exception {
		return cd.queryUser(userid);
	}

	
	/**
	 * 处理根据产品id和用户id进行判断是否存在这样的商品
	 */
	@Override
	public ShopCar getShopCar(int pid,int userid) throws Exception {
		return cd.getShopCar(pid,userid);
	}

	
	/**
	 * 根据商品id修改数量
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateShopCar(int quantity,int pid,int userid) throws Exception {
		if(cd.updateShopCar(quantity, pid,userid)>0) {
			return true;
		}else {
			return false;
		}
	}

	
	/**
	 * 处理根据商品id和用户id删除购物车中指定的商品
	 * @param pid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteCar(int pid, int userid) throws Exception {
		if(cd.deleteCar(pid, userid)>0) {
			return true;
		}else {
			return false;
		}
	}
	
}
