/**
 * 
 */
package com.microservice.userservice.exceptions;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
