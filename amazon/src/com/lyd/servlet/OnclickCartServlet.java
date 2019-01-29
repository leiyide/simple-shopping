package com.lyd.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/doBuy")
public class OnclickCartServlet extends HttpServlet{

	//购物车业务逻辑层的对象
	CarServiceDao csd=new CarServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//购物车实体类的对象
		ShopCar sc=new ShopCar();
		
		try {
			//得到当前用户的id
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			int userId = user.getUserId();
			List<ShopCar> queryUser = csd.queryUser(userId);
			req.setAttribute("queryUser", queryUser);
			
			
			//跳转页面
			req.getRequestDispatcher("/shopping.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
