package ep.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class FicheConfigurationReader extends PropertyPlaceholderConfigurer {

	private static Map<String,Map<String,DataType>> fichesProperties;
		 
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException {
		
		super.processProperties(beanFactory, properties);
		
		fichesProperties =  new HashMap<String,Map<String,DataType>>();
		
		/*
		 * initialisation de la liste de types de donn�es
		 */
		
		List<DataType> dataTypeList = new ArrayList<DataType>();
		for(String currentType : Arrays.asList(properties.get("datatypes").toString().split(","))){
			DataType dataType = new DataType();
			dataType.setName(currentType);
			dataType.setExpression(properties.get("datatypes."+currentType+".expression").toString());
			dataType.setDescription(properties.get("datatypes."+currentType+".description").toString());
			dataType.setExample(properties.get("datatypes."+currentType+".example").toString());
			dataTypeList.add(dataType);
		}
		
		/*
		 * initialisation du hashmap de fiches, leurs accesseurs et types de donn�es des champs
		 */
		
		for(String currentFiche : Arrays.asList(properties.get("fiches").toString().split(","))){
			Map<String,DataType> map = new HashMap<String,DataType>();
			for(int i=0; i<dataTypeList.size(); i++){
				if(properties.containsKey(currentFiche+"."+dataTypeList.get(i).getName())){
					for(String currentAccessor : Arrays.asList(properties.get(currentFiche+"."+dataTypeList.get(i).getName()).toString().split(",")))
					    map.put(currentAccessor, dataTypeList.get(i));
				}
			}
		    fichesProperties.put(currentFiche, map);
		}
	}
	
	public Map<String,Map<String,DataType>> getFichesProperties(){
		return fichesProperties;
	}
}