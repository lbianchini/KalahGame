package com.bianchini.leandro.kalahgame.backend.config;

/**
 * Class responsible to gather common informations and messages about the game.
 * 
 * @author lbianchini
 *
 */
public class Config {
	
	public static final int HOUSES_PER_PLAYER = 6;
	public static final int STORE_PER_PLAYER = 1;
	public static final int SEEDS_PER_HOUSE = 6;
	
	public static final String MSG_INFO_PLAYER_TURN = "It is %s turn.";
	public static final String MSG_INFO_PLAY_AGAIN = "%s, you can play again.";
	public static final String MSG_INFO_GAME_RUNNING = "The game hasn't ended yet.";
	public static final String MSG_INFO_WINNER = "Game Over! The winner is: %s";
	public static final String MSG_INFO_DRAW = "Draw";
	public static final String MSG_WARNING_WRONG_PLAYER = "Wrong Player!";
	public static final String MSG_WARNING_SELECTING_STORE = "Cannot select the Store! Please, select a house.";
	public static final String MSG_WARNING_HOUSE_EMPTY = "House is empty!";
	
}
