package com.lyd.userserviceimp;

import com.lyd.entity.User;
import com.lyd.userdao.UserDao;
import com.lyd.userdaoimp.UserDaoImpl;
import com.lyd.userservicedao.UserServiceDao;
import com.lyd.util.Password;

public class UserServiceImpl implements UserServiceDao {
	//实例化数据访问层的对象
	private UserDao us=new UserDaoImpl();
	/**
	 * 注册业务
	 * @param user 注册的用户信息
	 * @return 是否注册成功
	 */
	@Override
	public boolean userRegister(User user) throws Exception {
		//对密码进行加密
		String password = user.getPassword();
		//加密之后放入用户实体类中
		user.setPassword(Password.MD5EncodePass(password));
		int ur = us.userRegist(user);
		if(ur>0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 登录的业务
	 * @param useName 用户名
	 * @param password  密码
	 * @return 查找到的用户，如果没有返回null,如果有，返回对应的对象
	 */
	@Override
	public User userLogin(String userName, String password) throws Exception {
		//通过传入的参数，查找是否有这样的用户
				User result=us.userLogin(userName, password);
				if(result!=null&&result.getUserName()!=null ) {
					//如果有则返回这个用户
					return result;
				}else {
					//如果没有，则返回为null;
					return null;
				}
	}

	
	/**
	 * 根据指定的用户名(唯一性的查询操作)
	 * @param userName
	 * @return 返回指定的用户信息
	 * @throws Exception
	 */
	@Override
	public User queryName(String UserName) throws Exception {
		return us.queryName(UserName);
	}
	
	/**
	 * 根据用户邮箱查询有无
	 * @param emial
	 * @return
	 * @throws Exception
	 */
	@Override
	public User queryEmail(String emial) throws Exception{
		return us.queryEmail(emial);
	}
	
	/**
	 * 根据用户身份证超找有无
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	@Override
	public User queryIdentity(String Identity) throws Exception{
		return us.queryIdentity(Identity);
	}
	
	
	/**
	 * 根据用户的电话号码进行查找
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	@Override
	public User queryMobile(String Mobile) throws Exception{
		return us.queryMobile(Mobile);
	}
	
	
}
