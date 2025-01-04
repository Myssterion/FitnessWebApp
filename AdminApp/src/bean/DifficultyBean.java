package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Difficulty;
import repository.DifficultyRepository;

public class DifficultyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2665082916823350235L;

	public DifficultyBean() {

	}

	public ArrayList<Difficulty> getDifficulties(){
		return DifficultyRepository.getDifficulties();
	}

	public boolean insert(Difficulty difficulty) {
		return DifficultyRepository.insert(difficulty);
	}

	public boolean update(Difficulty difficulty) {
		return DifficultyRepository.update(difficulty);
	}

	public boolean delete(int diffID) {
		return DifficultyRepository.delete(diffID);
	}
}
