package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Intensity;
import utility.ConnectionPool;
import utility.DAOUtil;

public class IntensityRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM intensity";
	private static final String INSERT = "INSERT INTO intensity (intensity_name) VALUES (?)";
	private static final String UPDATE = "UPDATE intensity SET intensity_name=? WHERE id=? ";
	private static final String DELETE= "DELETE FROM intensity WHERE id=?";

	public static ArrayList<Intensity> getIntensities(){
		ArrayList<Intensity> intensities = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next())
				intensities.add(new Intensity(rs.getInt("id"),rs.getString("intensity_name")));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return intensities;
	}

	public static boolean insert(Intensity intensity) {
		Object values[] = {intensity.getIntensity()};
		return performAction(INSERT,values);
	}

	public static boolean update(Intensity intensity) {
		Object values[] = {intensity.getIntensity(), intensity.getId()};
		return performAction(UPDATE,values);
	}

	public static boolean delete(int intensityID) {
		Object values[] = {intensityID};
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
