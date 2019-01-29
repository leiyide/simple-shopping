package com.lyd.userserviceimp;

import java.util.List;

import com.lyd.entity.Comment;
import com.lyd.userdao.CommentDao;
import com.lyd.userdaoimp.CommentDaoImpl;
import com.lyd.userservicedao.CommentServiceDao;

public class CommentServiceImpl implements CommentServiceDao {

	CommentDao cd=new CommentDaoImpl();
	/**
	 * 处理提交留言的信息
	 * @return 留言的信息
	 * @throws Exception
	 */
	@Override
	public boolean submmitComment(Comment com) throws Exception {
		int submmitComment = cd.submmitComment(com);
		if(submmitComment>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 查看所有的留言信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Comment> queryComment() throws Exception {
		return cd.queryComment();
	}

}
