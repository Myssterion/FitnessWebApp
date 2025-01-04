package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Attribute;
import utility.ConnectionPool;
import utility.DAOUtil;

public class AttributeRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM attributes";
	private static final String READ_ALL_WHERE_CATEGORY_IS = "SELECT * FROM attributes WHERE category_id=?";
	private static final String INSERT = "INSERT INTO attributes (attribute_name, category_id) VALUES (?,?)";
	private static final String UPDATE = "UPDATE attributes SET attribute_name=?, category_id=? WHERE id=? ";
	private static final String DELETE = "DELETE FROM attributes WHERE id=?";
	private static final String DELETE_ATTRIBUTES_OF_CATEGORY = "DELETE FROM attributes WHERE category_id=?";

	public static ArrayList<Attribute> getAttributes(){
		ArrayList<Attribute> attributes = new ArrayList<>();
		Object values[] = {};
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				Attribute attribute = new Attribute(rs.getInt("id"),rs.getString("attribute_name"));
				attribute.setCategory(CategoryRepository.getCategoryById(rs.getInt("category_id")));
				attributes.add(attribute);
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return attributes;
	}

	public static boolean insert(Attribute attribute) {
		Object values[] = {attribute.getName(),attribute.getCategory().getId()};
		return performAction(INSERT,values);
	}

	public static boolean update(Attribute attribute) {
		Object values[] = {attribute.getName(), attribute.getCategory().getId(), attribute.getId()};
		return performAction(UPDATE,values);
	}

	public static boolean delete(int attributeID) {
		Object values[] = {attributeID};
		return performAction(DELETE,values);
	}
	
	public static ArrayList<Attribute> getAllAttributesOfCategory(int category_ID) {
		ArrayList<Attribute> attributes = new ArrayList<>();
		Object values[] = {category_ID};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL_WHERE_CATEGORY_IS, false, values);
			rs = ps.executeQuery();
			while(rs.next())
				attributes.add(new Attribute(rs.getInt("id"),rs.getString("attribute_name")));
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return attributes;
	}
	
	public static boolean deleteAttributesOfCategory(int category_ID) {
		Object values[] = {category_ID};
		return performAction(DELETE_ATTRIBUTES_OF_CATEGORY,values);
	}

	private static boolean performAction(String statement, Object[] values) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, statement, true, values);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				result = rs.getInt(1);
			
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
