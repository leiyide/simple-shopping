package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.Product;

public interface ProductDao {

	
	/**
	 * 父节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */
	public List<Product> getParentProduct(int parentid) throws Exception;
	
	/**
	 * 子节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */
	public List<Product> getChildProduct(int childid) throws Exception;
	
	/**
	 * 根据商品id查询数据(点击)
	 * @return
	 * @throws Exception
	 */
	public Product getProductInfo(int productid) throws Exception;
	
	
	/**
	 * 分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
   public List<Product> getProductList(int currentPage) throws Exception;
   
   /**
    * 查询数据总条数
    * @return 数据总条数
    * @throws Exception 异常
    */
   public int getProductCount() throws Exception;
   
   
   
}
