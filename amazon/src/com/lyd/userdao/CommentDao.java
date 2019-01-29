package com.lyd.userdao;

import java.util.List;

import com.lyd.entity.Comment;

public interface CommentDao {

	/**
	 * 提交留言的信息
	 * @return 留言的信息
	 * @throws Exception
	 */
	public int submmitComment(Comment com) throws Exception;
	
	/**
	 * 查看所有的留言信息
	 * @return
	 * @throws Exception
	 */
	public List<Comment> queryComment() throws Exception;
}
