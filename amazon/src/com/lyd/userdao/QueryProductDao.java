package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.Product;

public interface QueryProductDao {
	
	
	/**
	 *商品名分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	public List<Product> queryProductList(int currentPage ,String productName) throws Exception;

	/**
	 * 商品名查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	public int queryProductCount(String productName) throws Exception;
}
