package com.lyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.ShopCar;
import com.lyd.entity.User;
import com.lyd.userservicedao.CarServiceDao;
import com.lyd.userserviceimp.CarServiceImpl;

@WebServlet("/dCart")
public class DeleteCart extends HttpServlet{
	//购物车业务逻辑层中的对象
	CarServiceDao csd=new CarServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//购物车实体类的对象
			ShopCar sc=new ShopCar();
			//得到当前用户的id
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			
			int userId = user.getUserId();
			System.out.println(userId);
			//得到页面传过来的cid（产品id）
			String c = req.getParameter("cid");
			
			int cid = Integer.parseInt(c);
			System.out.println(cid);
			//调用删除的方法
			csd.deleteCar(cid, userId);
			
			resp.getWriter().println();
			
			//实现页面的跳转
			req.getRequestDispatcher("/shoping.jsp").forward(req, resp);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
