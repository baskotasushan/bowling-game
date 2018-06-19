package com.zebra.bowling.model;

/**
 * Implementation Class of the BowlingGame interface.
 * @author Sushan
 *
 */
public class BowlingGameImpl implements BowlingGame {

	int[] rolls;
	int currentRoll;

	public BowlingGameImpl() {
		this.rolls = new int[21];
	}

	@Override
	public void roll(int pinsDown) {
		rolls[currentRoll++] = pinsDown;
	}

	@Override
	public int[] score() {
		int[] scores = new int[10];
		int score = 0;
		int frameIndex = 0;
		for (int frame = 0; frame < 10; frame++) {
			if (isStrike(frameIndex)) {
				score += 10 + strikeBonus(frameIndex);
				scores[frame] = score;
				frameIndex++;
			} else if (isSpare(frameIndex)) {
				score += 10 + spareBonus(frameIndex);
				scores[frame] = score;
				frameIndex += 2;
			} else {
				score += sumOfRolls(frameIndex);
				scores[frame] = score;
				frameIndex += 2;
			}
		}
		return scores;
	}

	/**
	 * check is strike or not
	 * @param frame
	 * @return true or false
	 */
	private boolean isStrike(int frame) {
		return rolls[frame] == 10;
	}

	/**
	 * check is spare or not
	 * @param frame
	 * @return true or false
	 */
	private boolean isSpare(int frame) {
		return sumOfRolls(frame) == 10;
	}

	/**
	 * add required bonus to strike
	 * @param frame
	 * @return bonus
	 */
	private int strikeBonus(int frame) {
		return sumOfRolls(frame + 1);
	}

	/**
	 * add required bonus to spare
	 * @param frame
	 * @return bonus
	 */
	private int spareBonus(int frame) {
		return rolls[frame + 2];
	}

	/**
	 * sum the score
	 * @param frame
	 * @return score sum
	 */
	private int sumOfRolls(int frame) {
		return rolls[frame] + rolls[frame + 1];
	}
}
