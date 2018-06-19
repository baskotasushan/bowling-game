package com.zebra.bowling.model;

/**
 * This interface calculate the total scores played by the player. 
 * @author Sushan
 *
 */
public interface BowlingGame {

	/**
     * Keeps track of pins knocked over
     * @param noOfPins knocked over per frame
     * @exception
     */
	void roll(int noOfPins);

	/**
    * This method calculate the total score based on the user frame score.
    * @return score of current frame only
    */
	int[] score();

}