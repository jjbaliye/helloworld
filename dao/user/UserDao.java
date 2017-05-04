package com.y26.smbms.dao.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.y26.smbms.pojo.User;

public interface UserDao {

	/**
	 * 新增用户
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public int addUser(Connection conn,User user) throws Exception;
	
	/**
	 * 删除用户
	 * @param conn
	 * @param id
	 * @return
	 */
	public int deleteUser(Connection conn,Integer id);
	
	/**
	 * 更新用户
	 * @param conn
	 * @param user
	 * @return
	 */
	public int modifyUser(Connection conn,User user);
	
	/**
	 * 通过条件查询
	 * @param conn
	 * @param userName
	 * @param userRole
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	public List<User> getUserList(Connection conn,String userName,int userRole,int currentPageNo,int pageSize) throws Exception;
	
	/**
	 * 通过Id获取用户
	 * @param conn
	 * @param id
	 * @return
	 */
	public User getUserById(Connection conn,int id);
	
	/**
	 * 通过UserCode获取用户；
	 * @param conn
	 * @param userCode
	 * @return
	 * @throws Exception 
	 */
	public User getUserByUserCode(Connection conn,String userCode) throws Exception;
	
	/**
	 * 修改用户密码；
	 * @param conn
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public int modifyPwd(Connection conn,int id,String password) throws Exception;
	
	/**
	 * 修改用户信息；
	 * @param conn
	 * @param user
	 * @return
	 */
	public int modifyUserInf(Connection conn,User user);
	
	public int getUserCount(Connection conn,String userName,int role) throws Exception;

	public User getLoginUser(Connection connection, String userCode) throws Exception;

	public User getUserById(Connection conn, String sql, Object[] params) throws InstantiationException, IllegalAccessException, SQLException;
}
