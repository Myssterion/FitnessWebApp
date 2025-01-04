package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Exercise;
import utility.ConnectionPool;
import utility.DAOUtil;

public class ExerciseRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM exercises";
	private static final String INSERT = "INSERT INTO exercises (exercise_name,type,muscle) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE exercises SET exercise_name=?, type=?, muscle=? WHERE id=? ";
	private static final String DELETE= "DELETE FROM exercises WHERE id=?";

	public static ArrayList<Exercise> getExercises(){
		ArrayList<Exercise> exercises = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next())
				exercises.add(new Exercise(rs.getInt("id"),rs.getString("exercise_name"), rs.getString("type"),rs.getString("muscle")));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return exercises;
	}

	public static boolean insert(Exercise exercise) {
		Object values[] = {exercise.getExerciseName(),exercise.getType(),exercise.getMuscle()};
		return performAction(INSERT,values);
	}

	public static boolean update(Exercise exercise) {
		Object values[] = {exercise.getExerciseName(),exercise.getType(),exercise.getMuscle(),exercise.getId()};
		return performAction(UPDATE,values);
	}

	public static boolean delete(int exerciseID) {
		Object values[] = {exerciseID};
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
