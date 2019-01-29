package com.lyd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.Order;
import com.lyd.userservicedao.OrderServiceDao;
import com.lyd.userserviceimp.OrderServiceImpl;

@WebServlet("/getOrderServlet")
public class GetOrderServlet extends HttpServlet{
	
	//订单业务逻辑层的对象
	OrderServiceDao osd=new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("flag", "1");
			//订单详情
			List<Order> orderList = osd.getOrderList();
			req.setAttribute("orderList", orderList);
			//购物详情
			List<Order> shoppingList = osd.getShoppingList();
			req.setAttribute("shoppingList", shoppingList);
			
			req.getRequestDispatcher("orders_view.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
