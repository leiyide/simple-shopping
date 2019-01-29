package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lyd.entity.Comment;
import com.lyd.userdao.CommentDao;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class CommentDaoImpl extends JDBCTemplate implements CommentDao {

	/**
	 * 提交留言的信息
	 * @return 留言的信息
	 * @throws Exception
	 */
	@Override
	public int submmitComment(final Comment com) throws Exception {
		//sql语句
		String sql="insert into hwua_comment(hc_id,hc_nick_name,hc_content,hc_create_time) values(seq_comment.nextval,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
		//调用模板中的update方法
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, com.getName());
				pstmt.setString(2, com.getContent());
				pstmt.setString(3, com.getCreateTime());
			}
		});
	}


	/**
	 * 查看所有的留言信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Comment> queryComment() throws Exception {
		//定义一个集合存放所有的信息
		final List<Comment> list=new ArrayList<Comment>();
		//sql语句
		String sql="select * from hwua_comment";
		//调用模板中的方法
		query(sql, null, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						Comment comm=new Comment();
						comm.setReply(rs.getString("hc_reply"));
						comm.setContent(rs.getString("hc_content"));
						comm.setCreateTime(rs.getString("hc_create_time"));
						comm.setReply(rs.getString("hc_reply_time"));
						comm.setName(rs.getString("hc_nick_name"));
						comm.setState(rs.getString("hc_state"));
						list.add(comm);
					}
				}
			}
		});
		return list;
	}

}
