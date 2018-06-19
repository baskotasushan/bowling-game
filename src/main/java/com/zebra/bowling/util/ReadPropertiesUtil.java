package com.zebra.bowling.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesUtil {

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
