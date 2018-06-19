package com.zebra.bowling.main;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zebra.bowling.model.BowlingGame;
import com.zebra.bowling.model.BowlingGameImpl;
import com.zebra.bowling.util.FileReaderUtil;
import com.zebra.bowling.util.ReadPropertiesUtil;

/**
 * Helper class for main. Retrieve the file, process it and display to user
 * 
 * @author Sushan
 *
 */
public class DriverHelper {

	private BowlingGame game;
	private Map<String, List<String>> map;
	public static final int LAST_POSITION = 9;

	public DriverHelper() {
		map = new HashMap<>();
	}

	/**
	 * This method 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void playGame() throws FileNotFoundException, IOException {
		// read the file
		String fileContent = readTextFile();
		// populate the content of file to map
		Map<String, List<String>> player = mapPlayerNameAndScore(fileContent);
		// process the data
		Map<String, Map<String, List<String>>> data = scoreChart(player);
		// print the processed data in required format
		printToConsole(data);
	}
	
	/**
	 * This method is used to read the players score text file.
	 * @return File data as String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * File path is retrieved from application.properties using ReadPropertiesUtil.
	 */
	public String readTextFile() throws FileNotFoundException, IOException {
		FileReaderUtil readFile = new FileReaderUtil();
		ReadPropertiesUtil readProperty = new ReadPropertiesUtil();
		String filePath = readProperty.getProperties("input.file.path");
		// String filePath = readProperty.getProperties("input.file.perfect");
		return readFile.readFromInputFile(filePath);

	}

	/**
	 * This method populates the individual player name and their score list. 
	 * @param fileContent
	 * @return player scores map where key as name of player and values as score list.
	 */
	public Map<String, List<String>> mapPlayerNameAndScore(String fileContent) {
		String[] players = fileContent.split("\\r?\\n");
		String[] single = null;
		for (String p : players) {
			single = p.split(" ");
			if (map.get(single[0]) == null) {
				map.put(single[0], new ArrayList<>());
			}
			map.get(single[0]).add(single[1]);
		}
		return map;
	}

	/**
	 * This method prepares the score chart for the user.
	 * @param playerScores
	 * @return accumulated player scores and pinfalls map where key is user name and value is map of user pinfalls and scores.
	 */
	public Map<String, Map<String, List<String>>> scoreChart(Map<String, List<String>> playerScores) {

		Map<String, Map<String, List<String>>> finalScoreCard = new HashMap<>();

		for (Map.Entry<String, List<String>> player : playerScores.entrySet()) {
			List<Integer> score = convertStringtoInteger(player.getValue());

			List<String> pinfalls = processPinFalls(score);

			roll(score);
			List<String> scores = Arrays.stream(game.score())
					.boxed()
					.map(String::valueOf)
					.collect(Collectors.toList());

			Map<String, List<String>> scorePinfallMap = new HashMap<>();
			scorePinfallMap.put("pinfall", pinfalls);
			scorePinfallMap.put("score", scores);

			finalScoreCard.put(player.getKey(), scorePinfallMap);

		}

		return finalScoreCard;

	}

	/**
	 * This method the calculated scores, pinfalls to console
	 * @param finalScoreCard
	 */
	
	public void printToConsole(Map<String, Map<String, List<String>>> finalScoreCard) {
		System.out.print("Frame");
		for (int i = 1; i <= 10; i++) {
			System.out.print("\t\t" + i);
		}
		System.out.println();

		for (Map.Entry<String, Map<String, List<String>>> fs : finalScoreCard.entrySet()) {
			System.out.println(fs.getKey());
			System.out.print("Pinfalls ");
			fs.getValue().get("pinfall").forEach(System.out::print);
			System.out.println();
			System.out.print("Score ");
			fs.getValue().get("score").forEach(i -> System.out.print("\t\t" + i));
			System.out.println();

		}

	}

	/**
	 * helper method of scoreChart to process the pin falls.
	 * @param scoreList
	 * @return pin falls list
	 */
	public List<String> processPinFalls(List<Integer> scoreList) {
		List<Integer> convertedList = convertToFixedSizeList(scoreList);
		int[] scores = convertedList.stream().mapToInt(i -> i).toArray();
		List<String> pitFallList = new ArrayList<>();

		String[] states = new String[12];
		for (int i = 0; i < 10; i++) {
			if (scores[i * 2] == 10) {
				states[i] = "STR";
			} else if ((scores[i * 2] + scores[i * 2 + 1]) == 10) {
				states[i] = "SPR";
			} else {
				states[i] = "NONE";
			}
		}
		if (scores[19] == 10) {
			states[10] = "STR";
		} else {
			states[10] = "";
		}
		if (scores.length > 20) {
			if (scores[20] == 10) {
				states[11] = "STR";
			} else {
				states[11] = "";
			}
		} else {
			states[11] = "";
		}

		for (int x = 0; x < 10; x++) {
			if (states[x].equals("STR")) {
				if (x == 9)
					pitFallList.add("\tX");
				else
					pitFallList.add("\t\tX");
			} else if (states[x].equals("SPR")) {
				pitFallList.add("\t" + scores[x * 2] + "\t/");
			} else {
				pitFallList.add("\t" + scores[x * 2] + "\t" + scores[x * 2 + 1]);
			}
		}
		if (states[LAST_POSITION].equals("STR") && states[LAST_POSITION + 1].equals("STR")
				&& states[LAST_POSITION + 2].equals("STR")) {
			pitFallList.add("\tX \tX");
		} else if (states[LAST_POSITION + 2].equals("STR")) {
			pitFallList.add("\tX");
		} else if (states[LAST_POSITION].equals("SPR")) {
			pitFallList.add("\t" + scores[LAST_POSITION * 2 + 2]);
		} else if (states[LAST_POSITION].equals("STR")) {
			pitFallList.add("\t" + scores[LAST_POSITION * 2 + 1] + "\t" + scores[LAST_POSITION * 2 + 2]);
		}
		System.out.println(pitFallList);
		return pitFallList;
	}

	/**
	 * helper method to convert list of String to Integer
	 * @param scoreList of string
	 * @return list of integer
	 */
	public List<Integer> convertStringtoInteger(List<String> scoreList) {
		List<Integer> intScoreList = new ArrayList<Integer>();
		for (String score : scoreList) {
			if (score.equals("F")) {
				intScoreList.add(0);
			} else {
				intScoreList.add(Integer.parseInt(score));
			}
		}
		return intScoreList;
	}

	/**
	 * helper method to convert random size list to fix size list of 21 for Pitfalls processing
	 * @param randomSizeList
	 * @return
	 */
	public List<Integer> convertToFixedSizeList(List<Integer> randomSizeList) {
		List<Integer> fixedSizeList = new ArrayList<>(randomSizeList);
		for (int i = 0; i < fixedSizeList.size() - 3; i++) {
			if (fixedSizeList.get(i) == 10) {
				fixedSizeList.add(i + 1, 0);
			}
		}
		return fixedSizeList;
	}

	/**
	 * This method is used to populate the scores as array to BowlingGame class. 
	 * @param scores
	 */
	public void roll(List<Integer> scores) {
		game = new BowlingGameImpl();
		int[] rolls = scores.stream().mapToInt(i -> i).toArray();
		for (int pinsDown : rolls)
			game.roll(pinsDown);
	}

}
