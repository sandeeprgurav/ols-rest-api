package in.ols.rest.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
	private final Properties configProp = new Properties();
	private static String propertyFileName = "";
	private static String propertyFileExtension = "";
	private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	
	/**
	 * Constructor
	 */
	private PropertyReader(){
		InputStream inputStream;
		String configFilePath = "";
		try {
			configFilePath = propertyFileName.concat(".").concat(propertyFileExtension);
			logger.info("Config file Name: "+ configFilePath);
			inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
			if(null != inputStream){
				configProp.load(inputStream);
			}
			else{
				throw new FileNotFoundException("Property File: '" + configFilePath + "' not found at classpath ");
			}
		} catch(FileNotFoundException fileNotFoundException){
			logger.debug("Property File: '" + configFilePath + "' not found. " + fileNotFoundException.getMessage(),fileNotFoundException);
		} catch (IOException ioException) {
			logger.debug("Error while reading properties file: "+ioException.getMessage(),ioException);
		} catch (Exception exception) {
			logger.debug("Error occured: "+exception.getMessage(),exception);
		}
	}
	private static class LazyHolder {
		private static final PropertyReader INSTANCE = new PropertyReader();
	}

	/**
	 * Call this method with property file name and file extension
	 * @param propFile
	 * @return
	 */
	public static PropertyReader getInstance(String propFile, String propFileExtension){
		propertyFileName = propFile;
		propertyFileExtension = propFileExtension;
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key){
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames(){
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key){
		return configProp.containsKey(key);
	}
}
