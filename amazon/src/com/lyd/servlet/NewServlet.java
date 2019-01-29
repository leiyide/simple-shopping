package com.lyd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.ProductNew;
import com.lyd.userservicedao.ProductNewServiceDao;
import com.lyd.userserviceimp.ProductNewServiceImpl;

@WebServlet("/newServlet")
public class NewServlet extends HttpServlet {
	//得到业务逻辑层中的新闻的全部信息
	ProductNewServiceDao pns=new ProductNewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//接收从页面传进来的id
			String id = req.getParameter("nid");
			//把字符串转为int类型
			int nid=Integer.parseInt(id);
			ProductNew news = pns.getNewById(nid);
			req.setAttribute("news", news);
			
			//显示跳转页面中的新闻数据
			List<ProductNew> pnew = pns.getNew();
			req.setAttribute("pnew",  pnew);
			
			//实现页面的跳转
			req.getRequestDispatcher("/news_view.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
