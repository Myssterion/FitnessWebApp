package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Exercise;
import repository.ExerciseRepository;

public class ExerciseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9020963898780646866L;

	public ExerciseBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Exercise> getExercises(){
		return ExerciseRepository.getExercises();
	}

	public boolean insert(Exercise exercise) {
		return ExerciseRepository.insert(exercise);
	}
	
	public boolean update(Exercise exercise) {
		return ExerciseRepository.update(exercise);
	}
	
	public boolean delete(int exerciseID) {
		return ExerciseRepository.delete(exerciseID);
	}
}
