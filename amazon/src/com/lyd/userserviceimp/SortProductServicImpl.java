package com.lyd.userserviceimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyd.entity.Product;
import com.lyd.entity.SortProduct;
import com.lyd.userdao.SortProductDao;
import com.lyd.userdaoimp.SortProductDaoImpl;
import com.lyd.userservicedao.SortProductServiceDao;

public class SortProductServicImpl implements SortProductServiceDao {
	//实例化数据访问层的的对象
	private SortProductDao spd=new SortProductDaoImpl();
	
	/**
	 * 处理数据访问层中得到的数据，将父节点对应的子节点整理在一起，
	 * 用map集合进行存放；
	 */
	@Override
	public Map<String, Object> getAllProduct() throws Exception {
		//定义返回类型的map集合
		Map<String, Object> returnMap=new HashMap<String, Object>();
		
		//定义一个map集合用于存放每一个父节点对应的子节点
		Map<Integer,List<SortProduct>> pcMap=new HashMap<Integer,List<SortProduct>>();
		//得到父节点的数据
		List<SortProduct> parentProduct = spd.parentProduct();
		//得到子节点中的数据
		List<SortProduct> childProduct = spd.childProduct();
		//进行遍历取出父级对应的子节点
		if(parentProduct!=null&&parentProduct.size()>0) {
			//遍历父节点
			for (SortProduct parent : parentProduct) {
				//定义一个list集合存放子节点
				List<SortProduct> childList=new ArrayList<SortProduct>();
				//嵌套子节点
				for (SortProduct child : childProduct) {
					//判断是否父子节点对应
					if(parent.getId()==child.getParentId()) {
						//将子节点的数据存入列表
						childList.add(child);
					}
				}
				//将存在的父子级关系的数据存放在map集合中
				pcMap.put(parent.getId(), childList);
			}
		}
		//将封装好的节点放在map集合中，用于在后台操作的使用；
		returnMap.put("parentProduct", parentProduct);
		returnMap.put("childProduct", pcMap);
		return returnMap;
	}
	
	
	/**
	 * 处理父节点分页查询数据列表
	 * @param currentPage 当前页
	 * @return 数据列表
	 * @throws Exception 异常
	 */
   public List<Product> getSortList(int currentPage,int parentid) throws Exception{
	   return spd.getSortList(currentPage,parentid);
   }
   
   /**
    * 处理父节点查询数据总条数
    * @return 数据总条数
    * @throws Exception 异常
    */
   public int getSortCount(int parentid) throws Exception{
	   return spd.getSortCount(parentid);
   }


@Override
public List<Product> cgetSortList(int currentPage, int childid) throws Exception {
	return spd.cgetSortList(currentPage, childid);
}


@Override
public int cgetSortCount(int childid) throws Exception {
	return spd.cgetSortCount(childid);
}
}
