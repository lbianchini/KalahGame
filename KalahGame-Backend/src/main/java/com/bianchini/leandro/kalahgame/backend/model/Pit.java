package com.bianchini.leandro.kalahgame.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Abstract class responsible to represent a pit in the game.
 * 
 * @author lbianchini
 *
 */
@JsonPropertyOrder(value = {"number", "playerNumber", "seeds", "empty", "type"})
public abstract class Pit {
	
	private final int number;
	private final Player player;
	protected int seeds;
	
	/**
	 * Contructor
	 * 
	 * @param int number
	 * @param Player player
	 * @param int seeds
	 */
	public Pit(int number, Player player, int seeds) {
		this.number = number;
		this.player = player;
		this.seeds = seeds;
	}
	
	/**
	 * Get the number of the Pit
	 * 
	 * @return int
	 */
	@JsonProperty("number")
	public int getNumber() {
		return number;
	}

	/**
	 * Get the player who owns the pit.
	 * 
	 * @return Player
	 */
	@JsonIgnore
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Get the number of the player who owns the pit.
	 * 
	 * @return Integer
	 */
	@JsonProperty("playerNumber")
	public Integer getPlayerNumber() {
		return this.player.getNumber();
	}
	
	/**
	 * Get the amount of seeds currently in the Pit.
	 * 
	 * @return int
	 */
	@JsonProperty("seeds")
	public int getSeeds() {
		return this.seeds;
	}

	/**
	 * Add 1 seed to the amount of seeds 
	 */
	public void addSeed() {
		this.seeds++;
	}
	
	/**
	 * Add the amount of seeds informed in the current pit.
	 * 
	 * @param seeds
	 */
	public void addSeeds(int seeds) {
		this.seeds += seeds;
	}
	
	/**
	 * Check if the current pit is empty.
	 * 
	 * @return boolean
	 */
	@JsonProperty("empty")
	public boolean isEmpty() { 
		return this.seeds == 0;
	}
	
	/**
	 * Get the type of the Pit.
	 * 
	 * @return String
	 */
	@JsonProperty("type")
	public String getType() {
		return this.getClass().getSimpleName();
	}

	/**
	 * Return the String representation of a Pit. 
	 */
	@Override
	@JsonIgnore
	public String toString() {
		return String.format("[%s,%s]", this.number, this.seeds);
	}

}
