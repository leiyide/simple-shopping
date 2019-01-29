package com.lyd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.User;
import com.lyd.userservicedao.UserServiceDao;
import com.lyd.userserviceimp.UserServiceImpl;
import com.lyd.util.Password;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到业务逻辑层的对象
		UserServiceDao usd=new UserServiceImpl();
		//得到用户的对象
		User user=new User();

		//设置字符编码
		resp.setContentType("text/html;charset=UTF-8");


		//得到jsp页面传来的参数
		String userName = req.getParameter("userName");
		String pwd = req.getParameter("passWord");
		String jiami = Password.MD5EncodePass(pwd);

		try {
			User login = usd.userLogin(userName,jiami);
			if(login!=null) {
				//将控制台上的数据提交显示在页面上
				PrintWriter out = resp.getWriter();
				out.println("登陆成功");
				//将建立会话请求，并设置参数，一进入登录就开始建立会话
				HttpSession session = req.getSession();
				//将页面传来的信息，放在实体类中，
				session.setAttribute("user", login);
				//将指定的页面覆盖头请求
				resp.setHeader("refresh","1;"+req.getContextPath()+"/indexServlet");

			}else {
				//将控制台上的数据提交显示在页面上
				PrintWriter out = resp.getWriter();
				out.println("登陆失败！请重新注册");
				//将指定的页面覆盖头请求
				resp.setHeader("refresh","1;"+req.getContextPath()+"/login.jsp");
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
