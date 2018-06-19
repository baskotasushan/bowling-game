package com.zebra.bowling.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zebra.bowling.model.BowlingGame;
import com.zebra.bowling.model.BowlingGameImpl;
import com.zebra.bowling.util.FileReaderUtil;
import com.zebra.bowling.util.ReadPropertiesUtil;

public class DriverHelper {

	private BowlingGame game;
	private Map<String, List<String>> map;
	private List<String> nameList;

	public DriverHelper() {
		game = new BowlingGameImpl();
		map = new HashMap<>();
		nameList = new ArrayList<>();

	}

	// read file
	public String readFile() throws FileNotFoundException, IOException {
		FileReaderUtil readFile = new FileReaderUtil();
		ReadPropertiesUtil readProperty = new ReadPropertiesUtil();
		String filePath = readProperty.getProperties("input.file.path");
		return readFile.readFromInputFile(filePath);

	}

	// player as key and value as list of score
	public void populatePlayer(String file) {
		String[] players = file.split("\\r?\\n");
		String[] single = null;
		for (String p : players) { // "john 10"
			// System.out.println(p + " d");
			single = p.split(" ");// [0] = john, [1] = 10
			if (map.get(single[0]) == null) {
				map.put(single[0], new ArrayList<>());
				nameList.add(single[0]);
			}
			map.get(single[0]).add(single[1]);
		}
		System.out.println(map);
		System.out.println(nameList);
	}

	// send player score to game
	public void calculateScore() {
		 List<List<Integer>> collect = map.entrySet().stream()
			.filter(item -> Collections.replaceAll(item.getValue(), "F", "0")) //change F -> 0
			.map(item -> item.getValue().stream().map(Integer::valueOf).collect(Collectors.toList()))
			.collect(Collectors.toList());
			
		//collect.stream().map(item -> item.toArray())
		//.map(item -> (int) roll(item));
		
		
		System.out.println(collect.size());
			
	}

	
	//direct value check
	public int[] test() {
		int [] score = new int[] {3, 7, 6, 3, 10, 8, 1, 10, 10, 9, 0, 7, 3, 4, 4, 10, 9, 0};
		roll(score);
		return game.score();
		
	}
	// helper method
	public void roll(int... rolls) {
		for (int pinsDown : rolls)
			roll(pinsDown);
	}

	public void run() throws FileNotFoundException, IOException {
		populatePlayer(readFile());

	}
}
