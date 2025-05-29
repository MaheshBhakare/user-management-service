/**
 * 
 */
package com.microservice.userservice.exceptions;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
public class UsernameNotFoundException extends RuntimeException {

	public UsernameNotFoundException() {
		super();
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

}
