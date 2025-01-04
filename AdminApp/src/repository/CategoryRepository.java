package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;
import utility.ConnectionPool;
import utility.DAOUtil;

public class CategoryRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String READ_ALL = "SELECT * FROM categories";
	private static final String READ_BY_ID = "SELECT * FROM categories WHERE id=?";
	private static final String INSERT = "INSERT INTO categories (category_name) VALUES (?)";
	private static final String UPDATE = "UPDATE categories SET category_name=? WHERE id=? ";
	private static final String DELETE= "DELETE FROM categories WHERE id=?";

	public static ArrayList<Category> getCategories(){
		ArrayList<Category> categories = new ArrayList<>();
		Object values[] = {};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				Category category = new Category(rs.getInt("id"),rs.getString("category_name"));
				category.setAttributes(AttributeRepository.getAllAttributesOfCategory(category.getId()));
				categories.add(category);
			}
				
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return categories;
	}

	public static boolean insert(Category category) {
		Object values[] = {category.getName()};//moram insert iz category_attributes
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, INSERT, true, values);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			ps.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return false;
		
	}

	public static boolean update(Category category) {
		Object values[] = {category.getName(), category.getId()};//moram update iz category_attributes
		boolean result = preformAction(UPDATE, values);
		 
		return result;
	}

	public static boolean delete(int categoryID) {
		AttributeRepository.deleteAttributesOfCategory(categoryID);
		Object values[] = {categoryID};
		System.out.println("HALLO");
		return preformAction(DELETE, values);
	}

	private static boolean preformAction(String statement, Object[] values) {
		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, statement, false, values);
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

	public static Category getCategoryById(int categoryId) {
		Category category = null;
		Object values[] = {categoryId};

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, READ_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next()) 
				category = new Category(rs.getInt("id"),rs.getString("category_name"));
				
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return category;
	}
}
