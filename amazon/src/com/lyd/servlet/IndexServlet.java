package com.lyd.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.Product;
import com.lyd.entity.ProductNew;
import com.lyd.entity.SortProduct;
import com.lyd.userservicedao.ProductNewServiceDao;
import com.lyd.userservicedao.ProductServiceDao;
import com.lyd.userservicedao.QueryProductServiceDao;
import com.lyd.userservicedao.SortProductServiceDao;
import com.lyd.userserviceimp.ProductNewServiceImpl;
import com.lyd.userserviceimp.ProductServiceImpl;
import com.lyd.userserviceimp.QueryProductServiceImpl;
import com.lyd.userserviceimp.SortProductServicImpl;
import com.lyd.util.JsonMapper;
import com.lyd.util.PageView;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	//获取业务逻辑层中商品分类的对象
	SortProductServiceDao spd=new SortProductServicImpl();
	
	//商品列表的业务逻辑层对象；
	ProductServiceDao psd=new ProductServiceImpl();
	
	//新闻动态的业务逻辑层对象
	ProductNewServiceDao pns=new ProductNewServiceImpl();
	
	//根据商品名获取商品信息的业务逻辑层对象
	QueryProductServiceDao qps=new QueryProductServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=new HashMap<String,Object>();
		
		
		try {
			//商品分类
			Map<String, Object> allProduct = spd.getAllProduct();
			//得到父节点中的数据
			List<SortProduct> parent= (List<SortProduct>)allProduct.get("parentProduct");
			//得到子节点中的数据
			Map<Integer,List<SortProduct>> child = (Map<Integer,List<SortProduct>>)allProduct.get("childProduct");
			//将接收的数据放在attribute中方便在页面里面进行操作取值；
			req.setAttribute("parent", parent);
			req.setAttribute("child", child);
			
			//新闻动态
			List<ProductNew> pnew = pns.getNew();
			req.setAttribute("pnew",  pnew);

			
			
			//根据商品名查询商品的信息
			//得到页面中的传进来的name
			//		String name = req.getParameter("qname");
			//		List<Product> queryProduct = qps.getQueryProduct(name);
			//		map.put("queryProduct", queryProduct);
			//		map.put("result", "成功");
			//		String queryJson=JsonMapper.toJson(map);
			//		System.out.println(queryJson);
			//		resp.getWriter().write(queryJson);

			
			//搜索分页的操作
			String productName = req.getParameter("qname");
			
			//商品列表
			//得到从页面传进来的参数
			List recordList=new ArrayList();
			String cate = req.getParameter("cate");
			req.setAttribute("cate", cate);
			//得到cate来判断是父节点还是子节点
			if("parent".equals(cate)) {
				String pid = req.getParameter("hpcId");
				req.setAttribute("hpcId", pid);
				//将数字型的字符串转为int类型
				int parentid=Integer.parseInt(pid);
				//得到父节点的id
				List<Product> parentProduct = psd.getParentProduct(parentid);
				req.setAttribute("Product", parentProduct);

				//父节点下的分页
				PageView p=new PageView();
				int SortCount;
				SortCount =spd.getSortCount(parentid);
				int pageCount=p.getAllPageCount(SortCount);
				//得到记录页面的遍历
				for(int i=1;i<=pageCount;i++) {
					recordList.add(i);
				}
				//当前页面
				String currentPageStr=req.getParameter("currentPage");
				//当前默认为1
				int currentPage=1;
				if(currentPageStr!=null&&currentPageStr.length()>0) {
					currentPage=Integer.parseInt(currentPageStr);	
				}
				//如果大于总页数，就赋值到最后一页
				currentPage=currentPage>pageCount?pageCount:currentPage;
				//如果小于1，就赋值为1；
				currentPage=currentPage<1?1:currentPage;
				//数据表
				List<Product> sortList=spd.getSortList(currentPage,parentid);
				//分页对象
				PageView pageView=new PageView(currentPage,SortCount,recordList);
				//数据列表
				req.setAttribute("Product", sortList);
				//分页信息
				req.setAttribute("pageView", pageView);

			}else if("child".equals(cate)) {
				String cid = req.getParameter("hpcId");
				req.setAttribute("hpcId", cid);
				int childid=Integer.parseInt(cid);
				//得到子节点的id
				List<Product> childProduct = psd.getChildProduct(childid);
				req.setAttribute("Product", childProduct);
				
				//子节点下的分页
				PageView p=new PageView();
				int SortCount;
				SortCount =spd.cgetSortCount(childid);
				int pageCount=p.getAllPageCount(SortCount);
				//得到记录页面的遍历
				for(int i=1;i<=pageCount;i++) {
					recordList.add(i);
				}
				//当前页面
				String currentPageStr=req.getParameter("currentPage");
				//当前默认为1
				int currentPage=1;
				if(currentPageStr!=null&&currentPageStr.length()>0) {
					currentPage=Integer.parseInt(currentPageStr);	
				}
				//如果大于总页数，就赋值到最后一页
				currentPage=currentPage>pageCount?pageCount:currentPage;
				//如果小于1，就赋值为1；
				currentPage=currentPage<1?1:currentPage;
				//数据表
				List<Product> sortList=spd.cgetSortList(currentPage,childid);
				//分页对象
				PageView pageView=new PageView(currentPage,SortCount,recordList);
				//数据列表
				req.setAttribute("Product", sortList);
				//分页信息
				req.setAttribute("pageView", pageView);
				
				
			}else if(productName!=null&&productName.length()>0) {
				//实现分页所有信息的分页
				PageView p=new PageView();
				int productCount;
				productCount = qps.queryProductCount(productName);
				int pageCount=p.getAllPageCount(productCount);
				//得到记录页面的遍历
				for(int i=1;i<=pageCount;i++) {
					recordList.add(i);
				}
				//当前页面
				String currentPageStr=req.getParameter("currentPage");
				//当前默认为1
				int currentPage=1;
				if(currentPageStr!=null&&currentPageStr.length()>0) {
					currentPage=Integer.parseInt(currentPageStr);	
				}
				//如果大于总页数，就赋值到最后一页
				currentPage=currentPage>pageCount?pageCount:currentPage;
				//如果小于1，就赋值为1；
				currentPage=currentPage<1?1:currentPage;
				//数据表
				List<Product> productList=qps.queryProductList(currentPage,productName);
				//分页对象
				PageView pageView=new PageView(currentPage,productCount,recordList);
				//数据列表
				req.setAttribute("Product", productList);
				//分页信息
				req.setAttribute("pageView", pageView);
			}
			else {
			
			//实现分页所有信息的分页
			PageView p=new PageView();
			int productCount;
			productCount = psd.getProductCount();
			int pageCount=p.getAllPageCount(productCount);
			//得到记录页面的遍历
			for(int i=1;i<=pageCount;i++) {
				recordList.add(i);
			}
			//当前页面
			String currentPageStr=req.getParameter("currentPage");
			//当前默认为1
			int currentPage=1;
			if(currentPageStr!=null&&currentPageStr.length()>0) {
				currentPage=Integer.parseInt(currentPageStr);	
			}
			//如果大于总页数，就赋值到最后一页
			currentPage=currentPage>pageCount?pageCount:currentPage;
			//如果小于1，就赋值为1；
			currentPage=currentPage<1?1:currentPage;
			//数据表
			List<Product> productList=psd.getProductList(currentPage);
			//分页对象
			PageView pageView=new PageView(currentPage,productCount,recordList);
			//数据列表
			req.setAttribute("Product", productList);
			//分页信息
			req.setAttribute("pageView", pageView);
			}
			//实现页面的跳转
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//最近浏览
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
