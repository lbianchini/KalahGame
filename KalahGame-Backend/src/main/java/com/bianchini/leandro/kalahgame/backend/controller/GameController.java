package com.bianchini.leandro.kalahgame.backend.controller;

import javax.ejb.Singleton;

import com.bianchini.leandro.kalahgame.backend.config.Config;
import com.bianchini.leandro.kalahgame.backend.model.Board;
import com.bianchini.leandro.kalahgame.backend.model.House;
import com.bianchini.leandro.kalahgame.backend.model.Pit;
import com.bianchini.leandro.kalahgame.backend.model.Player;
import com.bianchini.leandro.kalahgame.backend.model.Store;

/**
 * Class responsible for the rules of the game and the control of the board.
 * 
 * @author lbianchini
 *
 */
@Singleton
public class GameController {

	private Board board;
	
	/**
	 * Creates a New Game and its components.
	 * 
	 * @param playerOneName
	 * @param playerTwoName
	 * @return Board
	 */
	public Board newGame(String playerOneName, String playerTwoName) {

		// Creating Players
		Player playerOne = new Player(1, playerOneName);
		Player playerTwo = new Player(2, playerTwoName);

		// Creating Board
		this.board = new Board(playerOne, playerTwo);

		//Creating player's pits
		createPitsForPlayer(playerOne, 1, Config.HOUSES_PER_PLAYER);
		createPitsForPlayer(playerTwo, 1, Config.HOUSES_PER_PLAYER);

		//Set the initial message
		this.board.setMessage(String.format(Config.MSG_INFO_PLAYER_TURN, this.board.getCurrentPlayer().getName()));
		
		return this.board;
	}

	/**
	 * Method responsible to do a move on the game. 
	 * 
	 * @param boardNumber
	 * @param houseNumber
	 * @return Board
	 */
	public Board move(int playerNumber, int houseNumber) {

		//Check if has to change player's turn
		boolean changePlayer = false;
		
		//Get the player of the move
		Player player = this.board.getPlayerByNumber(playerNumber);
		
		//Check if the player chosen is the current player
		if (!(player == this.board.getCurrentPlayer())) {
			this.board.setMessage(Config.MSG_WARNING_WRONG_PLAYER);
			return this.board;
		}
		
		// Get the pit asked to move
		Pit pit = this.board.getPitByNumber(player, houseNumber);

		// Check if it is a valid house
		if (pit instanceof Store) {
			this.board.setMessage(Config.MSG_WARNING_SELECTING_STORE);
			return this.board;
		}

		// Check if the house has seeds to move
		if (pit.isEmpty()) {
			this.board.setMessage(Config.MSG_WARNING_HOUSE_EMPTY);
			return this.board;
		}

		// Take the seeds of chosen house
		int seeds = ((House) pit).takeAllSeeds();

		// Move to the next house
		pit = this.board.getNextPit();
		
		//Distribute the seeds
		for (int x = 1; x <= seeds; x++) {

			// Check if it is the last seed of the move
			if (x == seeds) {

				if ((pit instanceof House) && (pit.isEmpty()) && (pit.getPlayer() == this.board.getCurrentPlayer())) {
					// Take all seeds of Parallel House of Opponent
					int opponentHouseSeeds = ((House) getParallelOpponentHouse(pit)).takeAllSeeds();

					// Add the regular seed to the current player store
					this.board.getCurrentPlayer().getStore().addSeed();

					// Add seeds took from Parallel Opponent's House
					this.board.getCurrentPlayer().getStore().addSeeds(opponentHouseSeeds);
					
					//Change player turn
					changePlayer = true;

					continue;
				}

				pit.addSeed();

				// Check if the last seed is not in the Current player´s Store and change the player.
				//If it is in the Current player´s Store, the current player can play again.
				if (!((pit instanceof Store) && (pit.getPlayer() == this.board.getCurrentPlayer()))) {
					changePlayer = true;
				}
				
			} else {
				pit.addSeed();
				pit = this.board.getNextPit();
			}
		}

		// Check if it is the end of game
		if (isEndOfGame()) {
			// If it is the End of Game, take the seeds remained and check the winner
			takeSeedsRemained();
			this.board.setGameOver(true);
			this.board.setMessage(checkForWinner());
			
		} else {
			//Check if has to change the player turn.
			if (changePlayer) {
				this.board.setCurrentPlayer(this.board.getOpponentPlayer());
				this.board.setMessage(String.format(Config.MSG_INFO_PLAYER_TURN, this.board.getCurrentPlayer().getName())); 
			} else {
				this.board.setMessage(String.format(Config.MSG_INFO_PLAY_AGAIN, this.board.getCurrentPlayer().getName()));
			}
		}
		
		return this.board;

	}

	/**
	 * Creates the Pits (houses and stores)
	 * 
	 * @param player
	 * @param startNumber
	 * @param quantity
	 */
	private void createPitsForPlayer(Player player, int startNumber, int quantity) {
		//Creates the player's houses
		for (int i = 1; i <= quantity; i++) {
			House house = new House(startNumber, player, Config.SEEDS_PER_HOUSE);
			this.board.addPit(house);
			player.getHouseList().add(house);
			startNumber++;
		}
		
		//Creates the player's Store
		Store store = new Store(startNumber, player, 0);
		this.board.addPit(store);
		player.setStore(store);
	}
	
	/**
	 * Get the Parallel Opponent's House
	 * 
	 * @param pit
	 * @return Pit
	 */
	private Pit getParallelOpponentHouse(Pit pit) {
		int pitNumber = ((Config.HOUSES_PER_PLAYER - pit.getNumber()) + 1);
		Pit parallelPit = this.board.getPitByNumber(this.board.getOpponentPlayer(), pitNumber);
		return parallelPit;
	}

	/**
	 * Check if it is the End of Game. 
	 * 
	 * @return boolean
	 */
	private boolean isEndOfGame() {
		int totalSeedsPlayer = this.board.getCurrentPlayer().getHouseList().stream().mapToInt(h -> h.getSeeds()).sum();
		return (totalSeedsPlayer == 0);
	}
	
	/**
	 * At the end of game, take the seeds that remained of each player 
	 * and put it into its store. 
	 **/
	private void takeSeedsRemained() {
		int seedsRemainedPlayerOne = this.board.getPlayerOne().getHouseList().stream().mapToInt(h -> h.takeAllSeeds()).sum();
		int seedsRemainedPlayerTwo = this.board.getPlayerTwo().getHouseList().stream().mapToInt(h -> h.takeAllSeeds()).sum();
		
		this.board.getPlayerOne().getStore().addSeeds(seedsRemainedPlayerOne);
		this.board.getPlayerTwo().getStore().addSeeds(seedsRemainedPlayerTwo);
	}

	/**
	 * Check for the winner. The winner is the player who has more seeds in
	 * their Store.
	 * 
	 * @return String
	 */
	private String checkForWinner() {
		
		//Check first if the game has already ended
		if (!this.board.isGameOver()) {
			return Config.MSG_INFO_GAME_RUNNING;
		}
	
		// Check for PlayerOne victory
		if (this.board.getPlayerOne().getStore().getSeeds() > this.board.getPlayerTwo().getStore().getSeeds()) {
			this.board.setWinnerPlayer(this.board.getPlayerOne());
			return String.format(Config.MSG_INFO_WINNER, this.board.getPlayerOne().getName());
		} else {
			// Check for Draw
			if (this.board.getPlayerOne().getStore().getSeeds() == this.board.getPlayerTwo().getStore().getSeeds()) {
				return Config.MSG_INFO_DRAW;
			} else { // PlayerTwo victory
				this.board.setWinnerPlayer(this.board.getPlayerTwo());
				return String.format(Config.MSG_INFO_WINNER, this.board.getPlayerTwo().getName());
			}
		}
	}

}
