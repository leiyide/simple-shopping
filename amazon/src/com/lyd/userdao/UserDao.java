package com.lyd.userdao;

import com.lyd.entity.User;

public interface UserDao {
	
	/**
	 * 用户注册
	 * @return 用户信息放入数据库是否成功
	 * @throws Exception
	 */
	public int userRegist(User user) throws Exception ;
	
	/**
	 *用户登录
	 * @return 查看数据库中是否有该用户的信息
	 * @throws Exception
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
	public User queryIdentity(String Identity) throws Exception;
	
	
	/**
	 * 根据用户的电话号码进行查找
	 * @param UserName
	 * @return
	 * @throws Exception
	 */
	public User queryMobile(String Mobile) throws Exception;
	
	
}










