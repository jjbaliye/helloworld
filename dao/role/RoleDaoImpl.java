package com.y26.smbms.dao.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.y26.smbms.dao.BaseDao;
import com.y26.smbms.pojo.Role;

public class RoleDaoImpl implements RoleDao {

	@Override
	public List<Role> getRoleList(Connection conn) throws Exception {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Role> roleList = new ArrayList<Role>();
		if(conn != null){
			String sql = "select * from smbms_role";
			Object[] params = {};
			rs = BaseDao.execute(conn, pstmt, rs, sql, params);
			while(rs.next()){
				Role _role = new Role();
				_role.setId(rs.getInt("id"));
				_role.setRoleCode(rs.getString("roleCode"));
				_role.setRoleName(rs.getString("roleName"));
				roleList.add(_role);
			}
		}
		
		return roleList;
	}

}
