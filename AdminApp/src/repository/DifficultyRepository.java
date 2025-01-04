package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Difficulty;
import utility.ConnectionPool;
import utility.DAOUtil;

public class DifficultyRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM difficulties";
	private static final String INSERT = "INSERT INTO difficulties (difficulty) VALUES (?)";
	private static final String UPDATE = "UPDATE difficulties SET difficulty=? WHERE id=? ";
	private static final String DELETE= "DELETE FROM difficulties WHERE id=?";

	public static ArrayList<Difficulty> getDifficulties(){
		ArrayList<Difficulty> difficulties = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next())
				difficulties.add(new Difficulty(rs.getInt("id"),rs.getString("difficulty")));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return difficulties;
	}

	public static boolean insert(Difficulty difficulty) {
		Object values[] = {difficulty.getDifficulty()};
		return performAction(INSERT,values);
	}

	public static boolean update(Difficulty difficulty) {
		Object values[] = {difficulty.getDifficulty(), difficulty.getId()};
		return performAction(UPDATE,values);
	}

	public static boolean delete(int diffID) {
		Object values[] = {diffID};
		return performAction(DELETE,values);
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

}
