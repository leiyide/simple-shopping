package com.lyd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyd.entity.Order;
import com.lyd.entity.User;
import com.lyd.userservicedao.OrderServiceDao;
import com.lyd.userserviceimp.OrderServiceImpl;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet{

	//订单业务逻辑层的对象
	OrderServiceDao osd=new OrderServiceImpl();
	
	//创建时间
	Date date=new Date(); 
	//时间格式化
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
	String format = sdf.format(date);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//得到当前用户的id
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			int userId = user.getUserId();
			
			
			//从页面得到标签
			String flag = req.getParameter("flag");
			
			if(flag.equals("")||flag==null) {
				
				req.getRequestDispatcher("/main.jsp").forward(req, resp);
			}
			//从页面获取商品id和商品的数量
			Order order1=null;
			List<Order> list=null;
			
			for(int i=1;i<=Integer.valueOf(flag);i++) {
				String pId = req.getParameter("pId"+i);
				String number = req.getParameter("number"+i);
				
				int pid = Integer.valueOf(pId);
				int num = Integer.valueOf(number);
				
				//order对象
				order1=new Order();
				order1.setPid(pid);
				order1.setQuantity(num);
				
				list=new ArrayList<Order>();
				list.add(order1);
			}
			
			
			Order ord=null;
			
			int count=0;
				//更改库存
				for (Order order2 : list) {
					//根据id得到原来的数据
					ord = osd.getNum(userId, order2.getPid());

					//得到库存
					int stock = ord.getStock();
					//得到单价
					int price = ord.getPrice();
					//设置创建时间
					ord.setCreateTime(format);
					//得到数量
					int quantity = order2.getQuantity();
					//更新库存
					osd.updateNum(order2.getPid(),stock- quantity);
					//得到总价
					count=count+(quantity*price);
				}

				//将数据存入订单表中
				osd.updateOrder(ord, count);

				for (Order order3 : list) {
					//查询订单信息
					Order order = osd.getOrder();
					//得到单价
					int price = ord.getPrice();

					//将数据存入商品详情中
					osd.updateOrderDetail(order, order3.getQuantity()*price);
				}

				//清空购物车
				osd.deleteCar(userId);

				//实现页面的跳转
				req.getRequestDispatcher("/shopping-result.jsp").forward(req, resp);
		
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
