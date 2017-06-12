package com.bianchini.leandro.kalahgame.backend.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Class responsible to allow access regarding rest services.
 * 
 * @author lbianchini
 *
 */
@Provider
public class ContainerResponseFilterActivator implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext cres) throws IOException {
		 cres.getHeaders().add("Access-Control-Allow-Origin", "*");
		 cres.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, origin, authorization");
		 cres.getHeaders().add("Access-Control-Allow-Methods",  "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		 cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
	}

}
