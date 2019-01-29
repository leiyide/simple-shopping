package com.lyd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.Product;
import com.lyd.entity.SortProduct;
import com.lyd.userservicedao.ProductServiceDao;
import com.lyd.userservicedao.SortProductServiceDao;
import com.lyd.userserviceimp.ProductServiceImpl;
import com.lyd.userserviceimp.SortProductServicImpl;

@WebServlet("/productInfo")
public class ProductInfo extends HttpServlet{

	//商品列表的业务逻辑层对象；
	ProductServiceDao psd=new ProductServiceImpl();
	//获取业务逻辑层中商品分类的对象
	SortProductServiceDao spd=new SortProductServicImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			
			//从页面获取id
			String id = req.getParameter("pId");
			//将String数字类型转为int类型
			
			int pid=Integer.parseInt(id);
			Product productInfo = psd.getProductInfo(pid);
			req.setAttribute("ProductInfo", productInfo);
			
			
			//获取商品分类
			Map<String, Object> allProduct = spd.getAllProduct();
			//得到父节点中的数据
			List<SortProduct> parent= (List<SortProduct>)allProduct.get("parentProduct");
			//得到子节点中的数据
			Map<Integer,List<SortProduct>> child = (Map<Integer,List<SortProduct>>)allProduct.get("childProduct");
			//将接收的数据放在attribute中方便在页面里面进行操作取值；
			req.setAttribute("parent", parent);
			req.setAttribute("child", child);
			
			
			//实现页面的跳转
			req.getRequestDispatcher("/product_view.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
