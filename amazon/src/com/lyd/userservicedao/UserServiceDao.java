package com.lyd.userservicedao;

import com.lyd.entity.User;

public interface UserServiceDao {
	/**
	 * 注册业务
	 * @param user 注册的用户信息
	 * @return 是否注册成功
	 */
	public boolean userRegister(User user) throws Exception;

	/**
	 * 登录的业务
	 * @param useName 用户名
	 * @param password  密码
	 * @return 查找到的用户，如果没有返回null,如果有，返回对应的对象
	 */
	public User userLogin(String userName,String password) throws Exception;
	
	
	/**
	 * 根据指定的用户名(唯一性的查询操作)
	 * @param userName
	 * @return 返回指定的用户信息
	 * @throws Exception
	 */
	public User queryName(String UserName) throws Exception;
	
	/**
	 * 根据用户邮箱查询有无
	 * @param emial
	 * @return
	 * @throws Exception
	 */
	public User queryEmail(String emial) throws Exception;
	
	/**
	 * 根据用户身份证超找有无
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	public User queryIdentity(String UserName) throws Exception;
	
	
	/**
	 * 根据用户的电话号码进行查找
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	public User queryMobile(String UserName) throws Exception;
	
}
