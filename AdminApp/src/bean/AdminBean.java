package bean;

import java.io.Serializable;

import repository.AdminRepository;

public class AdminBean implements Serializable {

	private static final long serialVersionUID = -3758772606620811882L;
	private boolean loggedIn = false;
	
	public AdminBean() {
	}

	public boolean login(String username,String password) {
		if((AdminRepository.getAdminWithUsernameAndPassword(username, password)) != null) {
			loggedIn = true;
			return true;
		}
		return false;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

}
