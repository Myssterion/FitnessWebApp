package bean;

import java.io.Serializable;



public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String content;
	private String sentAt;
	private boolean status;
	private User user1;


	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public MessageBean(int id, String content, String sentAt, boolean status, User user1) {
		super();
		this.id = id;
		this.content = content;
		this.sentAt = sentAt;
		this.status = status;
		this.user1 = user1;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", content=" + content + ", sentAt=" + sentAt + ", status=" + status
				+ ", user1=" + user1 + "]";
	}
	
}
