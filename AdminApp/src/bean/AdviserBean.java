package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Adviser;
import repository.AdviserRepository;

public class AdviserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7978916700862288937L;

	public ArrayList<Adviser> getAdvisors(){
		return AdviserRepository.getAdvisors();
	}
	
	public boolean insert(Adviser adviser) {
		return AdviserRepository.insert(adviser);
	}

	public boolean update(Adviser adviser) {
		return AdviserRepository.update(adviser);
	}
	
	public boolean delete(int adviserID) {
		return AdviserRepository.delete(adviserID);
	}
}
