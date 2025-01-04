package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Intensity;
import repository.IntensityRepository;

public class IntensityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3651592784613653845L;

	public IntensityBean() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Intensity> getIntensities(){
		return IntensityRepository.getIntensities();
	}
	
	public boolean insert(Intensity intensity) {
		return IntensityRepository.insert(intensity);
	}
	
	public boolean update(Intensity intensity) {
		return IntensityRepository.update(intensity);
	}
	
	public boolean delete(int id) {
		return IntensityRepository.delete(id);
	}
}
