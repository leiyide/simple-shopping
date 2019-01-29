package com.lyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.User;
import com.lyd.userservicedao.UserServiceDao;
import com.lyd.userserviceimp.UserServiceImpl;

@WebServlet("/CheckUserName")
public class CheckUserName extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取业务逻辑层的对象
		UserServiceDao usd=new UserServiceImpl();
		//获取用户类的对象
		resp.setContentType("text/html;charset=UTF-8");
		//获取注册页面传进来的值
		
		//验证用户名
		String userName = req.getParameter("userName");
		try {
			User user = usd.queryName(userName);
			if(user!=null&&user.getUserName()!=null) {
				resp.getWriter().print("1");
			}else {
				resp.getWriter().print("0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
