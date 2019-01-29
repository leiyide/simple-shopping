package com.lyd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.Order;
import com.lyd.userservicedao.ProductServiceDao;
import com.lyd.userserviceimp.ProductServiceImpl;

@WebServlet("/hostRecomentServlet")
public class HostRecomentServlet extends HttpServlet{

	//商品列表的业务逻辑层对象；
	ProductServiceDao psd=new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Order> hostRecomment = psd.hostRecomment();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
