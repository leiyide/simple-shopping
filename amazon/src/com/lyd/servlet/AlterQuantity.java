package com.lyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.User;
import com.lyd.userservicedao.CarServiceDao;
import com.lyd.userserviceimp.CarServiceImpl;

@WebServlet("/alterQuantity")
public class AlterQuantity extends HttpServlet{

	
	//购物车业务逻辑层中的对象
	CarServiceDao csd=new CarServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		try {
			//获取当前登录的用户
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			int userId = user.getUserId();
			System.out.println("用户id"+userId);
			
			//得到js上面传过来的参数
			String alterNum = req.getParameter("pidnum");
			System.out.println(alterNum);
			
			
			
			String[] alter = alterNum.split("_");
			//得到产品的id
			int pid = Integer.parseInt(alter[0]);
			
			System.out.println("产品id"+pid);
			//得到数量
			int num = Integer.parseInt(alter[1]);
			
			System.out.println("产品数量"+num);
			
			//将页面上的信息从新插入到数据库中
			//调用数据更新的方法
			if(alterNum!=null) {
				csd.updateShopCar(num, pid, userId);
			}
			resp.getWriter().println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
