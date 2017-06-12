package com.bianchini.leandro.kalahgame.backend.service;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bianchini.leandro.kalahgame.backend.controller.GameController;

/**
 * Class responsible to create and export the services 
 * that should be consumed by the clients, such as web interface, smartphone interface, etc.
 * 
 * @author lbianchini
 *
 */
@Path("services")
public class KalahGameService {

	@EJB
	private GameController gameController;
	
	/**
	 * Service responsible to create a new game. 
	 * 
	 * @param playerOneName
	 * @param playerTwoName
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/new/{playerOneName}/{playerTwoName}")
	public Response newGame(@PathParam("playerOneName") String playerOneName, 
			@PathParam("playerTwoName") String playerTwoName) {
		return Response.ok(gameController.newGame(playerOneName, playerTwoName)).build();
	}
	
	/**
	 * Service responsible to do a move in the game. 
	 * 
	 * @param playerNumber
	 * @param houseNumber
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/move/{playerNumber}/{houseNumber}")
	public Response move(@PathParam("playerNumber") int playerNumber, 
			@PathParam("houseNumber") int houseNumber) {
		return Response.ok(gameController.move(playerNumber, houseNumber)).build();
	}
	
}
