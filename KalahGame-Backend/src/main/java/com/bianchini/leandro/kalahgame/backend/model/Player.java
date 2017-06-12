package com.bianchini.leandro.kalahgame.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class that represents a Player in the game.
 * 
 * @author lbianchini
 *
 */
@JsonPropertyOrder(value = {"number", "name"})
public class Player {
	
	private final int number;
	private final String name;
	private List<House> houseList = new ArrayList<>();
	private Store store;
	
	/**
	 * Contructor. Receives the number and the name of the Player.
	 * 
	 * @param number
	 * @param name
	 */
	public Player(int number, String name) {
		this.number = number;
		this.name = name;
	}

	/**
	 * Get the number of the current player.
	 * 
	 * @return int
	 */
	@JsonProperty("number")
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * Get the name of the current player.
	 * 
	 * @return String
	 */
	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	/**
	 * Get the house's list of the current player.
	 * 
	 * @return List<House>
	 */
	@JsonIgnore
	public List<House> getHouseList() {
		return houseList;
	}

	/**
	 * Get the Store of the current player.
	 * 
	 * @return Store
	 */
	@JsonIgnore
	public Store getStore() {
		return store;
	}

	/**
	 * Set the Store of the current player.
	 */
	public void setStore(Store store) {
		this.store = store;
	}
	
	/**
	 * Return the String representation of a Player. 
	 */
	@Override
	@JsonIgnore
	public String toString() {
		return this.name + "=[" + this.houseList.toString() + "," + this.store.toString() + "]\n";
	}
}
