package com.zebra.bowling.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Util class to read several file path from properties file
 * @author Sushan
 *
 */
public class ReadPropertiesUtil {

	/**
	 * Read property from properties file and give value
	 * @param propKey
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getProperties(String propKey) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		String filePath = "application.properties";
		String propValue = "";
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
			props.load(inputStream);
			propValue = props.getProperty(propKey);
		}
		return propValue;
	}
}
