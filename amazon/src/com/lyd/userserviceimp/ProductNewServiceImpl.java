package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.ProductNew;
import com.lyd.userdao.ProductNewDao;
import com.lyd.userdaoimp.ProductNewDaoImpl;
import com.lyd.userservicedao.ProductNewServiceDao;

public class ProductNewServiceImpl implements ProductNewServiceDao {

	ProductNewDao pnd=new ProductNewDaoImpl();
	/**
	 * 处理得到的信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProductNew> getNew() throws Exception {
		 return pnd.getAllNew();
		
	}
	
	/**
	 * 根据商品的id处理得到信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProductNew getNewById(int id) throws Exception {
		return pnd.getNewById(id);
	}

}
