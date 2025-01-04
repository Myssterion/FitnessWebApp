package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionPool;
import utility.DAOUtil;

public class UserRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String INSERT = "INSERT INTO users (name,surname,username,password) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE users SET name=?, surname=?, username=?, password=? WHERE id=?";
	private static final String DELETE = "DELETE FROM users WHERE id=?";
	
	public static int insert(User user) {
		Object[] values = {user.getName(),user.getSurname(),user.getUsername(),user.getPassword()};
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, INSERT, true, values);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}
		
		return -1;
	}
	
	public static boolean update(User user) {
		Object[] values = {user.getName(),user.getSurname(),user.getUsername(),user.getPassword(),user.getId()};
		return performAction(user,UPDATE,values);
	}
	
	public static boolean delete(int userID) {
		User user = new User(userID);
		Object[] values = {user.getId()};
		return performAction(user,DELETE,values);
	}
	
	private static boolean performAction(User user, String statement,Object[] values) {
		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, statement, false, values);
			result = ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		if(result > 0)
			return true;
		return false;
	}
}
