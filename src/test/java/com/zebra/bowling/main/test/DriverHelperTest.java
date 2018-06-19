package com.zebra.bowling.main.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zebra.bowling.main.DriverHelper;
/**
 * Test class to check some DriverHelper methods
 * @author Sushan
 *
 */
public class DriverHelperTest {

	private DriverHelper helper;
	
	@Before
	public void setup() {
		helper = new DriverHelper();
	}
	/**
	 * test convert string to integer method
	 */

	@Test
	public void convertStringtoIntegerTest() {
		List<String> actual = Arrays.asList("10", "7", "3", "9", "0", "10", "F", "8", "8", "2", "0", "6", "10", "10", "10", "8", "1");
		List<Integer> expected = Arrays.asList(10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1);
		assertEquals(expected,helper.convertStringtoInteger(actual));
	}
	/**
	 * test converting to random size to fixed size array
	 */
	@Test
	public void convertToFixedSizeListTest() {
		List<Integer> actual = Arrays.asList(10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1);
		List<Integer> expected = Arrays.asList(10, 0, 7, 3, 9, 0, 10, 0, 0, 8, 8, 2, 0, 6, 10, 0, 10, 0, 10, 8, 1);
		assertEquals(expected, helper.convertToFixedSizeList(actual));
	}
}
