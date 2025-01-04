package bean;

import java.io.Serializable;
import java.util.ArrayList;

import model.Attribute;
import repository.AttributeRepository;

public class AttributeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7147878075161565846L;


	public ArrayList<Attribute> getAttributes(){
		return AttributeRepository.getAttributes();
	}

	public boolean insert(Attribute attribute) {
		return AttributeRepository.insert(attribute);
	}

	public boolean update(Attribute attribute) {
		return AttributeRepository.update(attribute);
	}

	public boolean delete(int attributeID) {
		return AttributeRepository.delete(attributeID);
	}

}
