package com.zebra.bowling.model;

public interface BowlingGame {

	/**
     * Keeps track of pins knocked over
     * @param noOfPins knocked over per frame
     * @exception
     */
	void roll(int noOfPins);

	/**
    *
    * @return score of current frame only
    */
	int[] score();

}