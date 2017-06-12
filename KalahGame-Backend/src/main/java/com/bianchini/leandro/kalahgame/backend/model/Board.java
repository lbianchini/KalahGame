package com.bianchini.leandro.kalahgame.backend.model;

import java.util.List;
import java.util.Random;

import com.bianchini.leandro.kalahgame.backend.controller.CircularLinkedNodeList;
import com.bianchini.leandro.kalahgame.backend.controller.Node;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Class that represents a board in the game.
 * 
 * @author lbianchini
 *
 */
@JsonPropertyOrder(value = {"playerOne", "playerTwo", "currentPlayer", 
		"isGameOver", "message", "winner", "pits"})
public class Board {
	
	private CircularLinkedNodeList<Pit> pitList = new CircularLinkedNodeList<>();
	private Node<Pit> currentNode = null;
	
	private final Player playerOne;
	private final Player playerTwo;

	private Player currentPlayer;
	private Player winnerPlayer = null;
	
	private String message;
	private boolean isGameOver = false;
	
	/**
	 * Contructor. Receives the name of players and choose the first to play randomly.
	 * 
	 * @param playerOne
	 * @param playerTwo
	 */
	public Board(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		int randomPlayer = new Random().nextInt(2) + 1;
		this.currentPlayer = getPlayerByNumber(randomPlayer);
	}
	
	/**
	 * Get the Player One of the current board.
	 * 
	 * @return Player
	 */
	@JsonProperty(value = "playerOne")
	public Player getPlayerOne() {
		return playerOne;
	}
	
	/**
	 * Get the Player Two of the current board.
	 * 
	 * @return Player
	 */
	@JsonProperty(value = "playerTwo")
	public Player getPlayerTwo() {
		return playerTwo;
	}

	/**
	 * Get the Player by the player's number informed.
	 * 
	 * @param playerNumber
	 * @return Player
	 */
	public Player getPlayerByNumber(int playerNumber) {
		return (playerNumber == 1 ? this.playerOne : this.playerTwo);
	}
	
	/**
	 * Get the Current Player on the game.
	 * 
	 * @return Player
	 */
	@JsonProperty(value = "currentPlayer")
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Set the Current Player on the game.
	 * 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * Get the opponent of the current player on the game.
	 * 
	 * @return Player
	 */
	@JsonIgnore
	public Player getOpponentPlayer() {
		return (this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne);
	}
	
	/**
	 * Check if the game is over.
	 * 
	 * @return boolean
	 */
	@JsonProperty(value = "isGameOver")
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Set that the game is over.
	 * 
	 * @param isGameOver
	 */
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * Get the message informed during the game.
	 * 
	 * @return String
	 */
	@JsonProperty(value = "message")
	public String getMessage() {
		return message;
	}

	/**
	 * Set a message regarding to current game.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Get the Winner of the current game.
	 * 
	 * @return Player
	 */
	@JsonProperty(value = "winner")
	public Player getWinnerPlayer() {
		return winnerPlayer;
	}

	/**
	 * Set the Winner of the current game.
	 * 
	 * @param winnerPlayer
	 */
	public void setWinnerPlayer(Player winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}

	/**
	 * Add a Pit into a Pit's list.
	 * 
	 * @param pit
	 */
	public void addPit(Pit pit) {
		Node<Pit> node = new Node<>(pit);
		this.pitList.addNode(node);
	}
	
	/**
	 * Get the Pit by the index informed.
	 * 
	 * @param index
	 * @return Pit
	 */
	@JsonIgnore
	public Pit getPitByIndex(int index) {
		this.currentNode = this.pitList.getNodeByIndex(index);
		return currentNode.getValue();
	}
	
	/**
	 * Get the Pit by the player and pit's number informed.
	 * 
	 * @param Player
	 * @param int
	 * @return Pit
	 */
	@JsonIgnore
	public Pit getPitByNumber(Player player, int number) {
		this.currentNode = this.pitList.getListNode().stream()
				.filter(p -> p.getValue().getPlayer().equals(player) && p.getValue().getNumber() == number) 
				.findAny()
				.orElse(null);

		return this.currentNode.getValue();
	}
	
	/**
	 * Get the next Pit referenced on the current Pit.
	 * 
	 * @return Pit
	 */
	@JsonIgnore
	public Pit getNextPit() {
		if (this.currentNode == null) {
			this.currentNode = this.pitList.getFirstNode();
			return this.currentNode.getValue();
		} else {
			this.currentNode = this.currentNode.getNextNode();
			return this.currentNode.getValue();
		}
	}
	
	/**
	 * Get the Pits list of the board game.
	 * 
	 * @return Pit
	 */
	@JsonProperty("pits")
	public List<Pit> getListNodeValues() {
		return this.pitList.getListNodeValues();
	}
	
	/**
	 * Return the String representation of a Board. 
	 */
	@Override
	public String toString() {
		return this.playerOne.toString() + this.playerTwo.toString() + this.message + "\n";
	}

}
