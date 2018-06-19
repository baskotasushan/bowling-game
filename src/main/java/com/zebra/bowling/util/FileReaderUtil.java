package com.zebra.bowling.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Util class to read the file from class path
 * @author Sushan
 *
 */
public class FileReaderUtil {

	/**
	 * Read the text file and return as string.
	 * @param filePath
	 * @return String of text file data
	 * @throws IOException
	 */
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
