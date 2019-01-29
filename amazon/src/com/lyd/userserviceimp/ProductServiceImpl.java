package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.Product;
import com.lyd.userdao.ProductDao;
import com.lyd.userdaoimp.ProductDaoImpl;
import com.lyd.userservicedao.ProductServiceDao;

public class ProductServiceImpl implements ProductServiceDao{
	//创建dao层中获取数据的对象
	private ProductDao pd=new ProductDaoImpl();
	/**
	 * 处理父节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Product> getParentProduct(int parentid) throws Exception {
		return pd.getParentProduct(parentid);
	}
		
	/**
	 * 处理子节点得到所有的产品的信息
	 * @return
	 * @throws Exception
	 */

	@Override
	public List<Product> getChildProduct(int childid) throws Exception {
		return pd.getChildProduct(childid);
	}


	/**
	 * 处理根据商品id查询数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Product getProductInfo(int productid) throws Exception {
		return pd.getProductInfo(productid);
	}

	/**
	 * 处理分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
	@Override
	public List<Product> getProductList(int currentPage) throws Exception {
		return pd.getProductList(currentPage);
	}

	/**
	 * 处理查询数据总条数
	 * @return 数据总条数
	 * @throws Exception 异常
	 */
	@Override
	public int getProductCount() throws Exception {
		return pd.getProductCount();
	}


}
