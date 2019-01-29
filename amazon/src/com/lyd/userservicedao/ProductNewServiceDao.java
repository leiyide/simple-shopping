package com.lyd.userservicedao;

import java.util.List;

import com.lyd.entity.ProductNew;

public interface ProductNewServiceDao {
	/**
	 * 处理得到的信息
	 * @return
	 * @throws Exception
	 */
	public List<ProductNew> getNew() throws Exception;
	
	/**
	 * 根据商品的id处理得到信息
	 * @return
	 * @throws Exception
	 */
	public ProductNew getNewById(int id) throws Exception;
}
