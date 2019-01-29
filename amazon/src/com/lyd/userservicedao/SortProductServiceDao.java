package com.lyd.userservicedao;

import java.util.List;
import java.util.Map;

import com.lyd.entity.Product;
import com.lyd.entity.SortProduct;

public interface SortProductServiceDao {
	/**
	 * 使用map集合装父级节点和对应的子节点
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getAllProduct() throws Exception;
	
	
	/**
	 * 处理父节点分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
   public List<Product> getSortList(int currentPage,int parentid) throws Exception;
   
   /**
    * 处理父节点查询数据总条数
    * @return 数据总条数
    * @throws Exception 异常
    */
   public int getSortCount(int parentid) throws Exception;
   
   
   /**
    *子节点分页查询数据列表
    * @param currentPage 当前页
    * @return 数据列表
    * @throws Exception 异常
    */
   public List<Product> cgetSortList(int currentPage ,int childid) throws Exception;

   /**
    * 子节点查询数据总条数
    * @return 数据总条数
    * @throws Exception 异常
    */
   public int cgetSortCount(int childid) throws Exception;
}
