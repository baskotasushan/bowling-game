package com.zebra.bowling.main;

import java.io.IOException;
/**
 * Main class to run the project
 * @author Sushan
 *
 */
public class Driver {

	/**
	 * Main method to instantiate the project
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DriverHelper helper = new DriverHelper();
		helper.playGame();
	}

}
