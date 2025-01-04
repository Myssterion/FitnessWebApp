package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Adviser;
import model.User;
import utility.ConnectionPool;
import utility.DAOUtil;

public class AdviserRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM users JOIN advisors ON user_id=id";
	private static final String INSERT = "INSERT INTO advisors (user_id) VALUES (?)";
	private static final String DELETE = "DELETE FROM advisors WHERE user_id=?";
	
	
	public static ArrayList<Adviser> getAdvisors(){
		ArrayList<Adviser> advisors = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next())
				advisors.add(new Adviser(rs.getInt("user_id"),rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password")));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return advisors;
	}
	
	public static boolean insert(Adviser adviser) {
		User user = adviser;
		int id = UserRepository.insert(user);
		Object values[] = {id};

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, INSERT, false, values);
			result = ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}
		
		if(result > 0)
			return true;
		return false;
	}
	
	public static boolean update(Adviser adviser) {
		User user = adviser;
		return UserRepository.update(user);
	}
	
	public static boolean delete(int adviserID) {
		boolean result = UserRepository.delete(adviserID);
		if(result) {
			Object values[] = {adviserID};

			Connection c = null;
			PreparedStatement ps = null;
			int rs = 0;

			try {
				c = connectionPool.checkOut();
				ps = DAOUtil.prepareStatement(c, DELETE, false, values);
				rs = ps.executeUpdate();
				
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				connectionPool.checkIn(c);
			}
			
			if(rs > 0)
				return true;
		}
		
		return false;
	}
}
