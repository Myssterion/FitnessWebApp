package model;

public class RegularUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -333432071343470763L;
	private String city;
	private String email;
	private boolean status;

	public RegularUser(User user) {
		super(user.getId(),user.getName(),user.getSurname(),user.getUsername(),user.getPassword());
	}

	public RegularUser(int id, String name, String surname, String username, String password, String city, String email, boolean status) {
		super(id, name, surname, username, password);
		this.city = city;
		this.email = email;
		this.status = status;

	}

	public RegularUser(String name, String surname, String username, String password, String city, String email, boolean status) {
		super(name, surname, username, password);
		this.city = city;
		this.email = email;
		this.status = status;
	}
	
	public RegularUser(String name, String surname, String username, String password, String city, String email) {
		super(name, surname, username, password);
		this.city = city;
		this.email = email;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
