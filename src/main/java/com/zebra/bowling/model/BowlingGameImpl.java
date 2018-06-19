package com.zebra.bowling.model;

public class BowlingGameImpl implements BowlingGame {

	int[] rolls;
	int currentRoll;

	public BowlingGameImpl() {
		this.rolls = new int[21];
	}

	/*
	 * play a game, i.e throw a ball to the sticks
	 */
	@Override
	public void roll(int pinsDown) {
		rolls[currentRoll++] = pinsDown;
	}

	/*
	 * score acheived in a throw
	 */
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

	private boolean isStrike(int frame) {
		return rolls[frame] == 10;
	}

	private boolean isSpare(int frame) {
		return sumOfRolls(frame) == 10;
	}

	private int strikeBonus(int frame) {
		return sumOfRolls(frame + 1);
	}

	private int spareBonus(int frame) {
		return rolls[frame + 2];
	}

	private int sumOfRolls(int frame) {
		return rolls[frame] + rolls[frame + 1];
	}
}
