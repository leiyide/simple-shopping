package com.lyd.userservicedao;

import java.util.List;

import com.lyd.entity.Comment;

public interface CommentServiceDao {

	/**
	 * 处理提交留言的信息
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean submmitComment(Comment com) throws Exception;
	
	
	/**
	 * 查看所有的留言信息
	 * @return
	 * @throws Exception
	 */
	public List<Comment> queryComment() throws Exception;
}
