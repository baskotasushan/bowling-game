package com.zebra.bowling.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zebra.bowling.model.BowlingGame;
import com.zebra.bowling.model.BowlingGameImpl;
import com.zebra.bowling.util.FileReaderUtil;
import com.zebra.bowling.util.ReadPropertiesUtil;

public class DriverHelper {

	private BowlingGame game;
	private Map<String, List<String>> map;
	private List<String> nameList;
	public static final int LAST_POSITION = 9;

	public DriverHelper() {
		
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
		
		calculate(map);
	}
	
	public List<String> pitFalls(List<Integer> list){
		System.out.println(list);
		List<Integer> convertedList = convertTo21(list);
		int[] scores = convertedList.stream().mapToInt(i->i).toArray();
		List<String> pitFallList = new ArrayList<>();
		
		String[] states = new String[10];
        //int[] scores = new int[] {10, 0, 7, 3, 9, 0, 10, 0, 0, 8, 8, 2, 0, 6, 10, 0, 10, 0, 10, 8, 1};
        for (int i=0; i<10; i++) {
          if (scores[i*2] == 10) {
            states[i] = "STR";
          } else if ((scores[i*2] + scores[i*2+1]) == 10) {
            states[i] = "SPR";
          } else {
            states[i] = "NONE";
          }
        }
        for (int x=0; x<10; x++) {
        	if (states[x].equals("STR")) {
        		pitFallList.add("\t\tX");
        		//System.out.print("\t\tX");
        	} else if (states[x].equals("SPR")) {
        		pitFallList.add("\t" + scores[x*2] + "\t/");
        		//System.out.print("\t" + scores[x*2] + "\t/");
        	} else {
        		pitFallList.add("\t" + scores[x*2] + "\t" + scores[x*2+1]);
        		//System.out.print("\t" + scores[x*2] + "\t" + scores[x*2+1]);
        	}
        }
        if (states[LAST_POSITION].equals("STR")) {
        	pitFallList.add("\t" + scores[LAST_POSITION * 2 + 1] + "\t" + scores[LAST_POSITION * 2 + 2]);
        	//System.out.println("\t" + scores[LAST_POSITION * 2 + 1] + "\t" + scores[LAST_POSITION * 2 + 2]);
        } else if (states[LAST_POSITION].equals("SPR")) {
        	pitFallList.add("\t" + scores[LAST_POSITION * 2 + 2]);
        	//System.out.println("\t" + scores[LAST_POSITION * 2 + 2]);
        }
        return pitFallList;
	}
	
	
	private List<Integer> convertTo21(List<Integer> list) {
		List<Integer> intList = new ArrayList<>(list);
		//Collections.copy(intList, list);
		for(int i = 0; i < list.size() - 3; i++) {
			if(list.get(i) == 10) {
				intList.add(i+1, 0);
			}
		}
		System.out.println(intList);
		return intList;
	}

	public void calculate(Map<String, List<String>> map) {
		
		Map<String, Map<String, List<String>>> finalScoreCard = new HashMap<>();
		
		for(Map.Entry<String, List<String>> m : map.entrySet()) {
			List<Integer> score = convertStringtoInteger(m.getValue());
			
			List<String> pitfalls = pitFalls(score);
			
			roll(score);
			List<String> scores = Arrays.stream(game.score()).boxed().map(String::valueOf).collect(Collectors.toList());
			
			Map<String, List<String>>  scorePinfallMap = new HashMap<>();
			scorePinfallMap.put("pinfall", pitfalls);
			scorePinfallMap.put("score", scores);
			
			finalScoreCard.put(m.getKey(), scorePinfallMap);
			
		}
		
		System.out.println(finalScoreCard);

	}
	
	public List<Integer> convertStringtoInteger(List<String> list){
		List<Integer> newList = new ArrayList<Integer>();
		for(String s : list) {
			if(s.equals("F")){
				newList.add(0);
			}else {
			newList.add(Integer.parseInt(s));
			}
		}
		return newList;
	}
	
	public void print() {
		System.out.print("Frame");
        for (int i=1; i<=10; i++) {
            System.out.print("\t\t" + i);
        }
        System.out.println();
        
        String[] pinfalls1 = new String[]{"X", "7", "/", "9", "0", "X", "0", "8", "8", "/", "F", "6", "X", "X", "X", "8", "1"};
        String[] scores1 = new String[]{"20", "39", "48", "66", "74", "84", "90", "120", "148", "167"};
        
        System.out.println("Jeff");
        System.out.print("Pinfalls");
        for (int i=0; i<pinfalls1.length; i++) {
            if (pinfalls1[i].equals("X")) {
                System.out.print("\t");
            }
            System.out.print("\t" + pinfalls1[i]);
        }
        System.out.println();
        System.out.print("Score\t");
        for (int i=0; i<scores1.length; i++) {
            System.out.print("\t\t" + scores1[i]);
        }
        System.out.println();
	}
	
		// helper method
	public void roll(List<Integer> list) {
		game = new BowlingGameImpl();
		System.out.println("::::::" + list);
		int[] rolls = list.stream().mapToInt(i->i).toArray();
		for (int pinsDown : rolls)
			game.roll(pinsDown);
	}

	public void run() throws FileNotFoundException, IOException {
		populatePlayer(readFile());
		

	}
}
