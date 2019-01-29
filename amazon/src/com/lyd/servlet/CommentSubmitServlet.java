package com.lyd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyd.entity.Comment;
import com.lyd.entity.SortProduct;
import com.lyd.userservicedao.CommentServiceDao;
import com.lyd.userservicedao.SortProductServiceDao;
import com.lyd.userserviceimp.CommentServiceImpl;
import com.lyd.userserviceimp.SortProductServicImpl;

@WebServlet("/commentSubmitServlet")
public class CommentSubmitServlet extends HttpServlet{

	//获取业务逻辑层中商品分类的对象
	SortProductServiceDao spd=new SortProductServicImpl();

	//评论的业务逻辑层中的对象
	CommentServiceDao csd=new CommentServiceImpl();

	//实体类提交的对象
	Comment com=new Comment();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		//商品分类
		Map<String, Object> allProduct = spd.getAllProduct();
		//得到父节点中的数据
		List<SortProduct> parent= (List<SortProduct>)allProduct.get("parentProduct");
		//得到子节点中的数据
		Map<Integer,List<SortProduct>> child = (Map<Integer,List<SortProduct>>)allProduct.get("childProduct");
		//将接收的数据放在attribute中方便在页面里面进行操作取值；
		req.setAttribute("parent", parent);
		req.setAttribute("child", child);
		
		//得到页面穿来的信息
		//昵称
		String name = req.getParameter("guestName");
		com.setName(name);
		//内容
		String content = req.getParameter("guestContent");
		com.setContent(content);
		System.out.println(content);
		//创建时间
		Date date=new Date(); 
		//时间格式化
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
		String format = sdf.format(date);
		com.setCreateTime(format);
		boolean temp;
		temp=csd.submmitComment(com);
		
		if(temp==true) {
			//查询所有的记录
			List<Comment> queryComment = csd.queryComment();
			req.setAttribute("queryComment", queryComment);		
			//实现页面的跳转
			req.getRequestDispatcher("/guestbook.jsp").forward(req, resp);
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
