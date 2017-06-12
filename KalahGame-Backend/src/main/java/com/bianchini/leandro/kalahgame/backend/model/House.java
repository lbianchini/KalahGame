package com.bianchini.leandro.kalahgame.backend.model;

/**
 * Class that represents a pit of House's type.
 * 
 * @author lbianchini
 *
 */
public class House extends Pit {
	
	/**
	 * Contructor
	 * 
	 * @param int number
	 * @param Player player
	 * @param int seeds
	 */
	public House(int number, Player player, int seeds) {
		super(number, player, seeds);
	}
	
	/**
	 * Get all the seeds of current house and set it to 0.
	 * 
	 * @return int
	 */
	public int takeAllSeeds() { 
		int seeds = this.getSeeds();
		this.seeds = 0;
		return seeds;
	}
	
}
