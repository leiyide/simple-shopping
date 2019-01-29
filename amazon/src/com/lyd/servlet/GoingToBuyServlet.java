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

@WebServlet("/goingToBuyServlet")
public class GoingToBuyServlet extends HttpServlet {
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


			String addcart = req.getParameter("addcart");
			String[] cart = addcart.split("_");
			int pid1 = Integer.parseInt(cart[0]);

			//根据商品id进行判断，
			ShopCar shopCar = csd.getShopCar(pid1,userId);

			//查询商品id
			int pid = shopCar.getPid();
			//查询商品数量
			int a = shopCar.getQuantity();


			if(pid==0) {
				//获取userid
				sc.setUserId(userId);
				//获取ID
				sc.setPid(pid1);
				//获取产品数量
				sc.setQuantity(Integer.parseInt(cart[1]));
				//调用插入的信息
				csd.addProduct(sc);

			}else if(shopCar!=null&&pid!=0) {
				//更新数量
				csd.updateShopCar(a+Integer.parseInt(cart[1]), pid,userId);
			}
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
