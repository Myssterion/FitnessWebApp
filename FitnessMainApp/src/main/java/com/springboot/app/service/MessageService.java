package com.springboot.app.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.MessageDto;
import com.springboot.app.model.Message;
import com.springboot.app.model.User;
import com.springboot.app.repository.MessageRepository;
import com.springboot.app.utility.EntityToDto;

import static com.springboot.app.utility.EntityToDto.*;

@Service
@Transactional
public class MessageService {

	private final MessageRepository messageRepository;
	private final UserService userService;

	public MessageService(MessageRepository messageRepository, UserService userService) {
		this.messageRepository = messageRepository;
		this.userService = userService;
	}

	public List<Message> findAllMessages() {
		return messageRepository.findAll();
	}

	public List<MessageDto> findInboxForUser(Integer userId) {
		List<Message> messages = messageRepository.findLatestMessagesForUser(userId);
		return messages.stream().map(message -> EntityToDto.ConvertToDtoFixedSender(message, userId))
				.collect(Collectors.toList());
	}

	public MessageDto sendMessage(MessageDto message) {
		User sender = userService.findById(message.getSenderId());
		User recipient = userService.findById(message.getReceiverId());
		System.out.println(message.getSenderId());
		System.out.println(message.getReceiverId());
		System.out.println("PORUKA");
		System.out.println(sender);
		System.out.println(recipient);
		if (sender != null && recipient != null && sender.getId() != recipient.getId()) {
			System.out.println("ID OK");
			Message newMessage = new Message();
			newMessage.setContent(message.getContent());
			newMessage.setSentAt(new Timestamp(message.getSentAt()));
			newMessage.setUser1(sender);
			newMessage.setUser2(recipient);

			return ConvertToDto(messageRepository.save(newMessage));
		}

		return null;
	}

	public List<MessageDto> findConversationForUsers(Integer userId, Integer participantId) {
		List<Message> messages = messageRepository.findConversationForUsers(userId, participantId);
		return messages.stream().map(EntityToDto::ConvertToDto).collect(Collectors.toList());
	}

	public List<MessageDto> findConversationForUsersWithCustomerSupport(Integer userId) {
		List<Message> messages = messageRepository.findConversationForUserAndAdvisers(userId);
		return messages.stream().map(EntityToDto::ConvertToDto).collect(Collectors.toList());
	}

	public MessageDto sendMessageToCustomerSupport(MessageDto message) {
		User sender = userService.findById(message.getSenderId());
		if (sender != null) {
			System.out.println("CUSTOMER SUPPORT");
			Message newMessage = new Message();
			newMessage.setContent(message.getContent());
			newMessage.setSentAt(new Timestamp(message.getSentAt()));
			newMessage.setUser1(sender);
			newMessage.setUser2(null);

			return ConvertToDto(messageRepository.save(newMessage));
		}
		return null;
	}

}
