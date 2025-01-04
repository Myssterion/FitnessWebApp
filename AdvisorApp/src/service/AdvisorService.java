package service;

import java.io.Serializable;

import bean.AdvisorBean;
import repository.AdvisorRepository;

public class AdvisorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151033921114031753L;

	public AdvisorService() {
		// TODO Auto-generated constructor stub
	}

	public AdvisorBean loginAdvisor(String username, String password) {
		return AdvisorRepository.getAdvisorWithUsernameAndPassword(username, password);

	}
}
