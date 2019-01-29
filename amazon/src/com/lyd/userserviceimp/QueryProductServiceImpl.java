package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.Product;
import com.lyd.userdao.QueryProductDao;
import com.lyd.userdaoimp.QueryProductImpl;
import com.lyd.userservicedao.QueryProductServiceDao;

public class QueryProductServiceImpl implements QueryProductServiceDao{
	
	//得到数据访问层中根据商品名来进行查询的对象
	QueryProductDao qpd=new QueryProductImpl();
	
	
	/**
	 *商品名分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> queryProductList(int currentPage, String productName) throws Exception {
		return qpd.queryProductList(currentPage, productName);
	}
	
	
	/**
	 * 商品名查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	@Override
	public int queryProductCount(String productName) throws Exception {
		return qpd.queryProductCount(productName);
	}

}
