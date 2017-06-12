package com.bianchini.leandro.kalahgame.backend.tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import com.bianchini.leandro.kalahgame.backend.config.Config;
import com.bianchini.leandro.kalahgame.backend.controller.GameController;
import com.bianchini.leandro.kalahgame.backend.model.Board;
import com.bianchini.leandro.kalahgame.backend.model.House;

public class GameControllerTests {

	private Board board;
	private GameController gameController;

	@Test
	@Before
	// Must create a new game with the correct configuration of a game.
	public void mustCreateANewGame() {

		String playerOneName = "Player 1";
		String playerTwoName = "Player 2";

		gameController = new GameController();
		board = gameController.newGame(playerOneName, playerTwoName);

		// Check Board number and Players
		System.out.println("Check Board number and Players");
		assertEquals(playerOneName, board.getPlayerOne().getName());
		assertEquals(playerTwoName, board.getPlayerTwo().getName());

		// Check the number of houses
		System.out.println("Check the number of houses");
		assertEquals(Config.HOUSES_PER_PLAYER, board.getPlayerOne().getHouseList().size());
		assertEquals(Config.HOUSES_PER_PLAYER, board.getPlayerTwo().getHouseList().size());

		// Check the amount of seeds in houses and stores
		System.out.println("Check the amount of seeds in houses and stores");
		assertEquals(0, board.getPlayerOne().getStore().getSeeds());
		assertEquals(0, board.getPlayerTwo().getStore().getSeeds());

		// Check the amount of seeds of each house of player one
		for (House house : board.getPlayerOne().getHouseList()) {
			assertEquals(Config.SEEDS_PER_HOUSE, house.getSeeds());
		}

		// Check the amount of seeds of each house of player two
		for (House house : board.getPlayerTwo().getHouseList()) {
			assertEquals(Config.SEEDS_PER_HOUSE, house.getSeeds());
		}

	}

	@Test
	// Must finish a game with Player One as winner.
	public void mustPlayerTwoWin() {

		board.setCurrentPlayer(board.getPlayerOne());

		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		gameController.move(1, 1);
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		System.out.println(this.board);

		gameController.move(1, 2);
		assertEquals(board.getPlayerTwo(), board.getCurrentPlayer());
		System.out.println(this.board);

		gameController.move(2, 1);
		System.out.println(this.board);

		gameController.move(1, 1);
		System.out.println(this.board);

		gameController.move(2, 2);
		System.out.println(this.board);

		gameController.move(1, 2);
		System.out.println(this.board);

		gameController.move(2, 3);
		System.out.println(this.board);

		gameController.move(1, 1);
		System.out.println(this.board);

		gameController.move(2, 4);
		System.out.println(this.board);

		gameController.move(1, 2);
		System.out.println(this.board);

		gameController.move(2, 5);
		System.out.println(this.board);

		gameController.move(1, 1);
		System.out.println(this.board);

		gameController.move(2, 6);
		System.out.println(this.board);

		gameController.move(1, 1);
		System.out.println(this.board);

		gameController.move(2, 1);
		System.out.println(this.board);

		gameController.move(1, 2);
		System.out.println(this.board);

		gameController.move(2, 2);
		System.out.println(this.board);

		gameController.move(1, 4);
		System.out.println(this.board);

		gameController.move(2, 3);
		System.out.println(this.board);

		// Check if Player One is the winner
		assertEquals(board.getWinnerPlayer(), board.getPlayerTwo());

	}

	@Test
	// Must try to do a move choosing a house whose belong a wrong player's turn
	public void mustBeWrongPlayer() {

		board.setCurrentPlayer(board.getPlayerOne());

		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		gameController.move(1, 1);
		System.out.println(this.board);
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());

		gameController.move(2, 1);
		System.out.println(this.board);
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		assertEquals(board.getMessage(), Config.MSG_WARNING_WRONG_PLAYER);
	}

	@Test
	// Must try to select a Pit of Store type to move
	public void mustSelectStore() {
		board.setCurrentPlayer(board.getPlayerOne());
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		gameController.move(1, 7);
		System.out.println(this.board);
		assertEquals(board.getMessage(), Config.MSG_WARNING_SELECTING_STORE);
	}

	@Test
	// Must try to select an Empty House to move
	public void mustSelectAnEmptyHouse() {
		board.setCurrentPlayer(board.getPlayerOne());	
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		gameController.move(1, 1);
		System.out.println(this.board);
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());

		gameController.move(1, 1);
		System.out.println(this.board);
		assertEquals(board.getPlayerOne(), board.getCurrentPlayer());
		assertEquals(board.getMessage(), Config.MSG_WARNING_HOUSE_EMPTY);
	}

}
