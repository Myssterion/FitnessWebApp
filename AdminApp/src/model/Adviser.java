package model;

public class Adviser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8170999600095626402L;

	public Adviser(User user) {
		super(user.getId(),user.getName(),user.getSurname(),user.getUsername(),user.getPassword());
	}

	public Adviser(int id) {
		super(id);
	}
	
	public Adviser(int id, String name, String surname, String username, String password) {
		super(id, name, surname, username, password);
	}

	public Adviser(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}

}
