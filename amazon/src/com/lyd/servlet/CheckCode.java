package com.lyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.userservicedao.UserServiceDao;
import com.lyd.userserviceimp.UserServiceImpl;

@WebServlet("/checkCode")
public class CheckCode extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取业务逻辑层的对象
		UserServiceDao usd=new UserServiceImpl();
		//获取用户类的对象
		resp.setContentType("text/html;charset=UTF-8");

		//验证码的验证
		String verCode = req.getParameter("veryCode");
		try {
			HttpSession session = req.getSession();
			String str=(String)session.getAttribute("validateCode");
			if(str.equals(verCode)) {
				resp.getWriter().print("0");
			}else {
				resp.getWriter().print("1");
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
