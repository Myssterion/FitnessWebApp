package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Category;
import repository.CategoryRepository;

public class CategoryBean implements Serializable {

	private static final long serialVersionUID = -735417679366680667L;

	public CategoryBean() {

	}

	public ArrayList<Category> getCategories(){
		return CategoryRepository.getCategories();
	}
	
	public boolean insert(Category category) {
		return CategoryRepository.insert(category);
	}
	
	public boolean update(Category category) {
		return CategoryRepository.update(category);
	}
	
	public boolean delete(int categoryID) {
		return CategoryRepository.delete(categoryID);
	}
}
