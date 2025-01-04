package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AdvisorBean;
import utility.ConnectionPool;
import utility.DAOUtil;

public class UserRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String GET_BY_ID_EMAIL= "SELECT * FROM users JOIN regular_users ON user_id=id WHERE id=?";
	
	public static String getRegularUserEmail(int id) {
		String email = "";
		Object values[] = {id};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, GET_BY_ID_EMAIL, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				email = rs.getString("email");
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return email;
	}

}
