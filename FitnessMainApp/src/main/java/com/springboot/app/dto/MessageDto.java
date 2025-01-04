package com.springboot.app.dto;

public class MessageDto {

		private String content;

		private long sentAt;

		private int senderId;

		private int receiverId;
		
		private String receiverUsername;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public long getSentAt() {
			return sentAt;
		}

		public void setSentAt(long sentAt) {
			this.sentAt = sentAt;
		}

		public int getSenderId() {
			return senderId;
		}

		public void setSenderId(int senderId) {
			this.senderId = senderId;
		}

		public int getReceiverId() {
			return receiverId;
		}

		public void setReceiverId(int receiverId) {
			this.receiverId = receiverId;
		}

		public String getReceiverUsername() {
			return receiverUsername;
		}

		public void setReceiverUsername(String receiverUsername) {
			this.receiverUsername = receiverUsername;
		}
}
