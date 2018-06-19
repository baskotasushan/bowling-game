package com.zebra.bowling.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sushan
 *
 */
public class FileReaderUtil {

	public String readFromInputFile(String filePath) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		FileReader file = new FileReader(classLoader.getResource(filePath).getFile());
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new  BufferedReader(file)) {
			String line;
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		}
		return stringBuilder.toString();
	}
}
