package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.MessageBean;
import bean.User;
import utility.ConnectionPool;
import utility.DAOUtil;

public class MessageRepository {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String GET_UNREAD_MESSAGES_TO_ADVISORS = "SELECT messages.*, users.id AS user_id, users.* FROM messages JOIN users ON sender_id=users.id"
			+ " WHERE status=0 AND recipient_id IS NULL";
	private static final String UPDATE_STATUS = "UPDATE messages SET status=1 WHERE id=? ";

	public static List<MessageBean> getUnreadMessagesForAdvisors() {
		ArrayList<MessageBean> messages = new ArrayList<>();
		Object values[] = {};
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, GET_UNREAD_MESSAGES_TO_ADVISORS, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				MessageBean message = new MessageBean();
				message.setId(rs.getInt("id"));
				message.setContent(rs.getString("content"));
				Timestamp timestamp = rs.getTimestamp("sent_at");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String sentAt = localDateTime.format(formatter);
				message.setSentAt(sentAt);
				message.setStatus(rs.getBoolean("status"));
				message.setUser1(new User(rs.getInt("user_id"),rs.getString("name"),rs.getString("surname"),rs.getString("username")));
		
				messages.add(message);
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connectionPool.checkIn(c);
		}

		return messages;
	}

	public static boolean updateStatus(int messageId) {
		Object values[] = {messageId};
		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			c = connectionPool.checkOut();
			ps = DAOUtil.prepareStatement(c, UPDATE_STATUS, false, values);
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
}
