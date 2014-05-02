package ep.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidatorLine {
	
	private String description;
	private String example;
	private boolean state;
	private List<String> errorList;

	public ValidatorLine(){
		this.errorList = new ArrayList<String>();
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
}