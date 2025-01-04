package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import model.RegularUser;
import model.User;
import utility.ConnectionPool;
import utility.DAOUtil;

public class RegularUserRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT u.*, ru.* FROM users u JOIN regular_users ru ON u.id = ru.user_id";
	private static final String INSERT = "INSERT INTO regular_users (user_id,city,email,status,verification_token,token_creation_time,verified,activity_log_id) VALUES (?,?,?,?,?,?,?,?)";
	private static final String INSERT_ACTIVITY_LOG = "INSERT INTO activity_log () values ()";
	private static final String UPDATE = "UPDATE regular_users SET city=?, SET email=? WHERE user_id=?";
	private static final String DELETE = "DELETE FROM regular_users WHERE user_id=?";
	private static final String UPDATE_STATUS = "UPDATE regular_users SET status=? WHERE user_id=?";

	public static ArrayList<RegularUser> getRegularUsers(){
		ArrayList<RegularUser> regularUsers = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				boolean status = (rs.getInt("status")) != 0;
				regularUsers.add(new RegularUser(rs.getInt("user_id"),rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password"),
						rs.getString("city"),rs.getString("email"),status));
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return regularUsers;
	}
	
	public static boolean insert(RegularUser regularUser) {
		User user = regularUser;
		int id = UserRepository.insert(user);
		if(id != -1) {
			int activityLogId = createActivityLog();
			Object values[] = {id,regularUser.getCity(),regularUser.getEmail(),false, UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()),true, activityLogId};
			return performAction(INSERT,values);
		}
		return false;
	}

	public static boolean update(RegularUser regularUser) {
		User user = regularUser;
		boolean result = UserRepository.update(user);
		if(result) {
			Object values[] = {regularUser.getCity(),regularUser.getEmail(),regularUser.getId()};
			return performAction(UPDATE,values);
		}
		
		return false;
	}
	
	public static boolean updateStatus(RegularUser regularUser) {
		Object values[] = {regularUser.getStatus(),regularUser.getId()};
		return performAction(UPDATE_STATUS,values);
		
	}
	
	public static boolean delete(int regularUserID) {
		Object values[] = {regularUserID};
		boolean result = performAction(DELETE,values);
		if(result) {
			return UserRepository.delete(regularUserID);
		}
		return false;
	}
	
	public static boolean changeStatus(int regularUserID, boolean status) {
		int newStatus = 1;
		if(status)
			newStatus = 0;
		Object values[] = {newStatus, regularUserID};
		return performAction(UPDATE_STATUS,values);
	}
	
	private static boolean performAction(String statement, Object[] values) {
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
	
	
	private static int createActivityLog() {
		Object values[] = {};
		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, INSERT_ACTIVITY_LOG, false, values);
			result = ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return result;
	}
}
