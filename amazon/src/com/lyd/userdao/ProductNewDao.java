package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.ProductNew;

public interface ProductNewDao {

	/**
	 * 得到新闻中的所有信息
	 * @return
	 * @throws Exception
	 */
	public List<ProductNew> getAllNew() throws Exception;
	
	/**
	 * 根据商品的id得到信息
	 * @return
	 * @throws Exception
	 */
	public ProductNew getNewById(int id) throws Exception;
}
