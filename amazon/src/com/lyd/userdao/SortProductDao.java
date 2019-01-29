package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.Product;
import com.lyd.entity.SortProduct;

public interface SortProductDao {
	
	/**
	 * 查找产品分类的父节点
	 * @return
	 * @throws Exception
	 */
	public List<SortProduct> parentProduct() throws Exception;
	
	/**
	 *查找产品分类中的子节点
	 * @return
	 * @throws Exception
	 */
	public List<SortProduct> childProduct() throws Exception;
	
	/**
	 * 父节点分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
   public List<Product> getSortList(int currentPage ,int parentid) throws Exception;
   
   /**
    * 父节点百货查询数据总条数
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
