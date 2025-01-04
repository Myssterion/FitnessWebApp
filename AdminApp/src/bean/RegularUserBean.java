package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.RegularUser;
import repository.RegularUserRepository;

public class RegularUserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919220430134111426L;

	public ArrayList<RegularUser> getRegularUsers(){
		return RegularUserRepository.getRegularUsers();
	}
	
	public boolean insert(RegularUser regularUser) {
		return RegularUserRepository.insert(regularUser);
	}
	
	public boolean update(RegularUser regularUser) {
		return RegularUserRepository.update(regularUser);
	}
	
	public boolean delete(int regularUserID) {
		return RegularUserRepository.delete(regularUserID);
	}

	public boolean changeStatus(int regularUserID, boolean status) {
		return RegularUserRepository.changeStatus(regularUserID, status);
	}

}
