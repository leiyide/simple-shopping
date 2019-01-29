package com.lyd.userdaoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lyd.entity.User;
import com.lyd.userdao.UserDao;
import com.oracle.jdbc.JDBCTemplate;
import com.oracle.jdbc.PreparedStatementSetter;
import com.oracle.jdbc.ResultSetHandler;

public class UserDaoImpl extends JDBCTemplate implements UserDao {
	/**
	 * 用户注册
	 * @return 用户信息放入数据库是否成功
	 * @throws Exception
	 */
	@Override
	public int userRegist(final User user) throws Exception {
		//sql语句
		String sql="insert into hwua_user(HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY," + 
				" HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS) "
				+ " values(SEQ_USER.NEXTVAL,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,1)";
		return update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getSex());
				pstmt.setString(4, user.getBirthday());
				pstmt.setString(5, user.getIdentity());
				pstmt.setString(6, user.getEmail());
				pstmt.setString(7, user.getMobile());
				pstmt.setString(8, user.getAddress());
			}
		});
	}

	/**
	 *用户登录
	 * @return 查看数据库中是否有该用户的信息
	 * @throws Exception
	 */
	@Override
	public User userLogin( final String userName, final String password) throws Exception {
		//创建一个用户对象
		final User user=new User();
		//语句
		String sql="select * from hwua_user where hu_user_name=? and hu_password=?";
		//调用模板中的query()方法
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userName);
				pstmt.setString(2, password);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						user.setUserId(rs.getInt("hu_user_id"));
						user.setUserName(rs.getString("hu_user_name"));
						user.setPassword(rs.getString("hu_password"));
						user.setSex(rs.getString("hu_sex"));
						user.setBirthday(rs.getString("hu_birthday"));
						user.setIdentity(rs.getString("hu_identity_code"));
						user.setEmail(rs.getString("hu_email"));
						user.setMobile(rs.getString("hu_mobile"));
						user.setAddress(rs.getString("hu_address"));
						user.setStatus(rs.getString("hu_status"));
					}
				}
			}
		});
		return user;
	}
	

	/**
	 * 根据指定的用户名(唯一性的查询操作)
	 * @param userName
	 * @return 返回指定的用户信息
	 * @throws Exception
	 */
	@Override
	public User queryName(final String userName) throws Exception {
		//创建用户实体类的对象
		final User user=new User();
		//sql语句
		String sql="select * from hwua_user where hu_user_name=? ";
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userName);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						user.setUserName(rs.getString("hu_user_name"));
					}
				}
			}
		});
		return user;
	}


	/**
	 * 根据用户邮箱查询有无
	 * @param emial
	 * @return
	 * @throws Exception
	 */
	@Override
	public User queryEmail(final String emial) throws Exception {
		//创建用户实体类的对象
		final User user=new User();
		//sql语句
		String sql="select * from hwua_user where hu_email=? ";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, emial);
			}
		}, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						user.setEmail(rs.getString("hu_email"));
					}
				}
			}
		});
		return user;
	}

	
	/**
	 * 根据用户身份证超找有无
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	@Override
	public User queryIdentity(final String Identity) throws Exception {
		//创建用户实体类的对象
		final User user=new User();
		//sql语句
		String sql="select * from hwua_user where hu_identity_cord=? ";
		query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, Identity);
			}
		}, new ResultSetHandler() {
			
			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						user.setIdentity(rs.getString("hu_identity_code"));
					}
				}
			}
		});
		return user;
	}

	
	/**
	 * 根据用户的电话号码进行查找
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public User queryMobile(final String Mobile) throws Exception {
		//创建用户实体类的对象
		final User user=new User();
		//sql语句
		String sql="select * from hwua_user where hu_mobile=? ";
		query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, Mobile);
			}
		}, new ResultSetHandler() {

			@Override
			public void handleRs(ResultSet rs) throws SQLException {
				if(rs!=null) {
					while(rs.next()) {
						user.setUserName(rs.getString("hu_user_name"));
						user.setPassword(rs.getString("hu_password"));
						user.setSex(rs.getString("hu-sex"));
						user.setBirthday(rs.getString("hu_birthday"));
						user.setIdentity(rs.getString("hu_identity_code"));
						user.setEmail(rs.getString("hu_email"));
						user.setMobile(rs.getString("hu_mobile"));
						user.setAddress(rs.getString("hu_address"));
						user.setStatus(rs.getString("hu_status"));
					}
				}
			}
		});
		return user;
	}
}
