package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.app.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query(value = "SELECT m FROM Message m " + "WHERE (m.user1.id = :userId OR m.user2.id = :userId) "
			+ "AND m.sentAt = (SELECT MAX(sub.sentAt) FROM Message sub "
			+ "WHERE (sub.user1.id = m.user1.id AND sub.user2.id = m.user2.id) "
			+ "OR (sub.user1.id = m.user2.id AND sub.user2.id = m.user1.id)) " + "ORDER BY m.sentAt DESC")
	List<Message> findLatestMessagesForUser(@Param("userId") Integer userId);

	@Query(value = "SELECT m FROM Message m " + "WHERE (m.user1.id = :userId AND m.user2.id = :participantId) "
			+ "OR (m.user1.id = :participantId AND m.user2.id = :userId) " + "ORDER BY m.sentAt DESC")
	List<Message> findConversationForUsers(@Param("userId") Integer userId,
			@Param("participantId") Integer participantId);

	@Query(value = "SELECT m FROM Message m WHERE " +
            "(m.user1.id = :regularUserId AND (m.user2.id IN " +
             "(SELECT a.id FROM Advisor a) OR m.user2.id IS NULL)) OR " +
            "(m.user2.id = :regularUserId AND m.user1.id IN " +
             "(SELECT a.id FROM Advisor a))" + "ORDER BY m.sentAt DESC")
	List<Message> findConversationForUserAndAdvisers(@Param("regularUserId") Integer regularUserId);

	
	
}
