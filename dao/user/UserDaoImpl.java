package com.y26.smbms.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.y26.smbms.dao.BaseDao;
import com.y26.smbms.pojo.User;
import com.y26.smbms.tools.BeanHandler;
import com.y26.smbms.tools.JdbcTemplate;
import com.y26.smbms.tools.ResultHandler;

public class UserDaoImpl implements UserDao {

	@Override
	public int addUser(Connection conn, User user) throws Exception {
		PreparedStatement pstmt = null;
		int updateRows = 0;
		if(conn != null){
			String sql = "insert into smbms_user (userCode,userName,userPassword," +
					"userRole,gender,birthday,phone,address,creationDate,createdBy) " +
					"values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = {user.getUserCode(),user.getUserName(),user.getUserPassword(),
					user.getUserRole(),user.getGender(),user.getBirthday(),
					user.getPhone(),user.getAddress(),user.getCreationDate(),user.getCreatedBy()};
			updateRows = BaseDao.execute(conn, pstmt, sql, params);
			BaseDao.closeResource(conn, pstmt, null);
			
		}
		return 0;
	}

	@Override
	public int deleteUser(Connection conn, Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyUser(Connection conn, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getUserList(Connection conn, String userName,
			int userRole, int currentPageNo, int pageSize) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		if(conn != null){
			StringBuffer sql = new StringBuffer();
			sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
			List<Object> list = new ArrayList<Object>();
			if(!StringUtils.isNullOrEmpty(userName)){
				sql.append(" and u.userName like ?");
				list.add("%"+userName+"%");
			}
			if(userRole > 0){
				sql.append(" and u.userRole = ?");
				list.add(userRole);
			}
			sql.append(" order by creationDate DESC limit ?,?");
			currentPageNo = (currentPageNo-1)*pageSize;
			list.add(currentPageNo);
			list.add(pageSize);
			
			Object[] params = list.toArray();
			System.out.println("sql ----> " + sql.toString());
			rs = BaseDao.execute(conn, pstm, rs, sql.toString(), params);
			while(rs.next()){
				User _user = new User();
				_user.setId(rs.getInt("id"));
				_user.setUserCode(rs.getString("userCode"));
				_user.setUserName(rs.getString("userName"));
				_user.setGender(rs.getInt("gender"));
				_user.setBirthday(rs.getDate("birthday"));
				_user.setPhone(rs.getString("phone"));
				_user.setUserRole(rs.getInt("userRole"));
				_user.setUserRoleName(rs.getString("userRoleName"));
				userList.add(_user);
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return userList;
	}

	@Override
	public User getUserById(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserCode(Connection conn, String userCode) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		if(conn != null){
			String sql ="select * from smbms_user where userCode = ?";
			Object[] params = {userCode};
			rs = BaseDao.execute(conn, pstm, rs, sql, params);
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setGender(rs.getInt("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserRole(rs.getInt("userRole"));
				user.setCreatedBy(rs.getInt("createdBy"));
				user.setCreationDate(rs.getTimestamp("creationDate"));
				user.setModifyBy(rs.getInt("modifyBy"));
				user.setModifyDate(rs.getTimestamp("modifyDate"));
			}
			BaseDao.closeResource(conn, pstm, rs);
		}
		return user;
	}

	@Override
	public int modifyPwd(Connection conn, int id, String password) throws Exception {
		int flag = 0;
		PreparedStatement pstmt=null;
		if(conn != null){
			String sql = "update smbms_user set userPassword= ? where id = ?";
			Object[] params = {password,id};
			flag = BaseDao.execute(conn, pstmt, sql, params);
			BaseDao.closeResource(conn, pstmt, null);
		}
		return flag;
	}

	@Override
	public int modifyUserInf(Connection conn, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	//用户数量；
	@Override
	public int getUserCount(Connection conn, String userName, int userRole) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		
		sql.append("select count(1) as count from smbms_user u , smbms_role r where r.id = u.userRole");
		List<Object> list = new ArrayList<Object>();
		
		if(userName != null && userName != ""){
			sql.append(" and u.userName like ?");
			list.add("%" + userName + "%");
		}
		if(userRole >0){
			sql.append(" and u.userRole = ?");
			list.add(userRole);
		}
		
		//list转化为数组对象；
		Object[] params = list.toArray();
		rs = BaseDao.execute(conn, pstmt, rs, sql.toString(), params);
		
		if(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}

	@Override
	public User getLoginUser(Connection connection, String userCode) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		if(null != connection)
			try {
				{
					String sql = "select * from smbms_user where userCode=?";
					Object[] params = {userCode};
					rs = BaseDao.execute(connection, pstm, rs, sql, params);
					if(rs.next()){
						user = new User();
						user.setId(rs.getInt("id"));
						user.setUserCode(rs.getString("userCode"));
						user.setUserName(rs.getString("userName"));
						user.setUserPassword(rs.getString("userPassword"));
						user.setGender(rs.getInt("gender"));
						user.setBirthday(rs.getDate("birthday"));
						user.setPhone(rs.getString("phone"));
						user.setAddress(rs.getString("address"));
						user.setUserRole(rs.getInt("userRole"));
						user.setCreatedBy(rs.getInt("createdBy"));
						user.setCreationDate(rs.getTimestamp("creationDate"));
						user.setModifyBy(rs.getInt("modifyBy"));
						user.setModifyDate(rs.getTimestamp("modifyDate"));
					}
					BaseDao.closeResource(null, pstm, rs);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return user;
	}

	@Override
	public User getUserById(Connection conn, String sql, Object[] params) throws InstantiationException, IllegalAccessException, SQLException {
		PreparedStatement pstmt = null;
		BeanHandler<User> beanHandler = new BeanHandler<User>(User.class);
		User user = (User) JdbcTemplate.queryForObject(sql, params, conn, pstmt, beanHandler);
		return user;
	}

}
