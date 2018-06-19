package com.zebra.bowling.main;

import java.io.IOException;

public class Driver {
	
	public static final int LAST_POSITION = 9;

	public static void main(String[] args) throws IOException {
		DriverHelper helper = new DriverHelper();
		helper.run();
		helper.calculateScore();
	/*	
		String[] states = new String[10];
        int[] scores = new int[] {10, 0, 7, 3, 9, 0, 10, 0, 0, 8, 8, 2, 0, 6, 10, 0, 10, 0, 10, 8, 1};
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
        		System.out.print("\t\tX");
        	} else if (states[x].equals("SPR")) {
        		System.out.print("\t" + scores[x*2] + "\t/");
        	} else {
        		System.out.print("\t" + scores[x*2] + "\t" + scores[x*2+1]);
        	}
        }
        if (states[LAST_POSITION].equals("STR")) {
        	System.out.println("\t" + scores[LAST_POSITION * 2 + 1] + "\t" + scores[LAST_POSITION * 2 + 2]);
        } else if (states[LAST_POSITION].equals("SPR")) {
        	System.out.println("\t" + scores[LAST_POSITION * 2 + 2]);
        }
        
        
        // Static Data Print Layout
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
        
        String[] pinfalls2 = new String[]{"3", "/", "6", "3", "X", "8", "1", "X", "X", "9", "0", "7", "/","4", "4", "X", "9", "0"};
        String[] scores2 = new String[]{"16", "25", "44", "53", "82", "101", "110", "124", "132", "151"};
        
        System.out.println("John");
        System.out.print("Pinfalls");
        for (int i=0; i<pinfalls2.length; i++) {
            if (pinfalls2[i].equals("X")) {
                System.out.print("\t");
            }
            System.out.print("\t" + pinfalls2[i]);
        }
        System.out.println();
        System.out.print("Score\t");
        for (int i=0; i<scores2.length; i++) {
            System.out.print("\t\t" + scores2[i]);
        }
        System.out.println();
        
        
	}*/
	}

}
