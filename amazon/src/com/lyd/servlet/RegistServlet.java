package com.lyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.User;
import com.lyd.userservicedao.UserServiceDao;
import com.lyd.userserviceimp.UserServiceImpl;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取业务逻辑层的对象
		UserServiceDao usd=new UserServiceImpl();
		//获取用户类的对象
		User user=new User();
		//设置页面的编码格式
		resp.setContentType("text/html;charset=UTF-8");
		//获取注册页面传进来的值
		//用户的姓名
		String userName = req.getParameter("userName");
		user.setUserName(userName);
		//密码
		String password = req.getParameter("passWord");
		user.setPassword(password);
		//性别
		String sex = req.getParameter("sex");
		user.setSex(sex);
		//生日
		String birthday = req.getParameter("birthday");
		user.setBirthday(birthday);
		//身份证号
		String identity = req.getParameter("identity");
		user.setIdentity(identity);
		//邮箱
		String email = req.getParameter("email");
		user.setEmail(email);
		//手机
		String mobile = req.getParameter("mobile");
		user.setMobile(mobile);
		//地址 
		String address = req.getParameter("address");
		user.setAddress(address);
		
		//定义一个变量；
		boolean temp;
		try {
			temp=usd.userRegister(user);
			if(temp) {
				resp.getWriter().println("注册成功！");
				resp.setHeader("refresh","1;"+req.getContextPath()+"/index.jsp");
			}else {
				resp.getWriter().println("注册失败！请重新注册！");
				resp.setHeader("refresh","1;"+req.getContextPath()+"/register.jsp");	
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
