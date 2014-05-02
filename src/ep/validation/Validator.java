package ep.validation;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.tidy.Tidy;

public class Validator {

	private FicheConfigurationReader ficheProperties = new FicheConfigurationReader();
	private int errors = 0;
		
	@SuppressWarnings("unchecked")
	public <T> Map<String,ValidatorLine> validateFiche(Object fiche, Class<T> classe) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Map<String, ValidatorLine> validationMap = new HashMap<String, ValidatorLine>();
		Map<String, DataType> ficheItems = new HashMap<String, DataType>();
		errors = 0;
		
		//System.out.println("Fiche : "+classe.getCanonicalName());
		
		ficheItems = ficheProperties.getFichesProperties().get(classe.getCanonicalName());
		fiche = (T)fiche;
		
		List<Method> ficheMethodList = Arrays.asList(classe.getDeclaredMethods());
		
		for(int i=0; i<ficheMethodList.size(); i++){
			DataType dataType = ficheItems.get(ficheMethodList.get(i).getName());
			if(dataType!=null){
				String result = ficheMethodList.get(i).invoke(fiche, (Object[]) null).toString();
				boolean state = result.matches(dataType.getExpression());
				ValidatorLine validatorLine = new ValidatorLine();
				validatorLine.setDescription(dataType.getDescription());
				validatorLine.setExample(dataType.getExample());
				validatorLine.setState(state);
				validatorLine.setErrorList(getErrorList(result));
				String column = ficheMethodList.get(i).getName().replaceFirst("get", "");
				String key = column.substring(0,1).toLowerCase()+column.substring(1);
				validationMap.put(key, validatorLine);
				if(!state)
					errors+= 1;
				//System.out.println("Method: "+ficheMethodList.get(i).getName()+" -> "+key+" Valid : "+state);
			}
		}
		return validationMap;
	}
	
	private List<String> getErrorList(String field){
		
		Tidy tidy = new Tidy();
		ArrayList<String> errorList = new ArrayList<String>();
		
		tidy.setXmlOut(true);
		tidy.setXHTML(true);
		tidy.setShowWarnings(true);
		tidy.setDocType("strict");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		tidy.setErrout(new PrintWriter(out, true));
		
		ByteArrayInputStream result = new ByteArrayInputStream(field.getBytes());
		tidy.parseDOM(new BufferedInputStream(result), null);
		
		String errors = (new String(out.toByteArray())).trim();
		if(errors.length()>0)
		{
			String[] errorsTab = errors.split("\n");
			for(int i=0;i< errorsTab.length;i++)
			{
				if(errorsTab[i].length()>5)
				{
					String subString = errorsTab[i].substring(0,5);
					if(subString.equals("ligne"))
						errorList.add(errorsTab[i]);
				}
			}
		}
		return errorList;
	}
	
	public int getErrors(){
		return this.errors;
	}
}