package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.MessageDto;
import com.springboot.app.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("/inbox/{userId}")
	public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable Integer userId){
		List<MessageDto> messagesDto = messageService.findInboxForUser(userId);
		return new ResponseEntity<>(messagesDto,HttpStatus.OK);
	}
	
	@GetMapping("/inbox/{userId}/conversation/{participantId}")
	public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable Integer userId, @PathVariable Integer participantId){
		List<MessageDto> messagesDto = messageService.findConversationForUsers(userId,participantId);
		return new ResponseEntity<>(messagesDto,HttpStatus.OK);
	}
	
	@GetMapping("/conversation/customersupport/{userId}")
	public ResponseEntity<List<MessageDto>> getAllMessagesWithCustomerSupport(@PathVariable Integer userId){
		List<MessageDto> messagesDto = messageService.findConversationForUsersWithCustomerSupport(userId);
		return new ResponseEntity<>(messagesDto,HttpStatus.OK);
	}
	
	@PostMapping("/send")
	public ResponseEntity<MessageDto> sendMessages(@RequestBody MessageDto message){
		MessageDto messageDto = messageService.sendMessage(message);
		if(messageDto != null)
			return new ResponseEntity<>(messageDto,HttpStatus.OK);
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/send/customersupport")
	public ResponseEntity<MessageDto> sendMessagesToCustomerSupport(@RequestBody MessageDto message){
		MessageDto messageDto = messageService.sendMessageToCustomerSupport(message);
		if(messageDto != null)
			return new ResponseEntity<>(messageDto,HttpStatus.OK);
		else
			return ResponseEntity.badRequest().build();
	}

}
