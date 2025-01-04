package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AdvisorBean;
import utility.ConnectionPool;
import utility.DAOUtil;

public class AdvisorRepository {


	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String GET_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users JOIN advisors ON user_id=id WHERE username=? AND password=?";

	public static AdvisorBean getAdvisorWithUsernameAndPassword(String username, String password) {
		AdvisorBean advisor = null;
		Object values[] = {username,password};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, GET_BY_USERNAME_AND_PASSWORD, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				advisor = new AdvisorBean(rs.getInt("user_id"),rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password"));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return advisor;
	}
}
