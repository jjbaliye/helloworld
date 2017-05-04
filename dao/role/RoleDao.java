package com.y26.smbms.dao.role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.y26.smbms.pojo.Role;

public interface RoleDao {
	
	public List<Role> getRoleList(Connection conn) throws SQLException, Exception;
	
}
