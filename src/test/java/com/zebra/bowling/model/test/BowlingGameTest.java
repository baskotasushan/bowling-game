package com.zebra.bowling.model.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.zebra.bowling.model.BowlingGame;
import com.zebra.bowling.model.BowlingGameImpl;

public class BowlingGameTest {

	private BowlingGame game;

	// create a new game
	@Before
	public void setUpGame() {
		game = new BowlingGameImpl();
	}

	// score zero in all frame -> gutter ball
	@Test
	public void scoreGutterGame() {
		roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		assertThat(game.score()[9], is(0));
	}

	// roll all 1's
	@Test
	public void scoreGameofOnes() {
		roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		assertThat(game.score()[9], is(20));
	}

	// roll one spare
	@Test
	public void scoreOneSpareGame() {
		roll(7, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		assertThat(game.score()[9], is(18));
	}

	// roll one strike
	@Test
	public void scoreOneStrikeGame() {
		roll(10, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		assertThat(game.score()[9], is(24));
	}

	// roll all strike, perfect game
	@Test
	public void scorePerfect() {
		roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		assertThat(game.score()[9], is(300));
	}

	// roll all strike, perfect game
	@Test
	public void testJeff() {
		roll(10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1);
		assertThat(game.score()[9], is(167));
	}

	// roll all strike, perfect game
	@Test
	public void testJohn() {
		int [] score = new int[] {3, 7, 6, 3, 10, 8, 1, 10, 10, 9, 0, 7, 3, 4, 4, 10, 9, 0};
		roll(score);
		assertThat(game.score()[9], is(151));
	}

	// helper method
		public void roll(int... rolls) {
			for (int pinsDown : rolls)
				game.roll(pinsDown);
		}
}
