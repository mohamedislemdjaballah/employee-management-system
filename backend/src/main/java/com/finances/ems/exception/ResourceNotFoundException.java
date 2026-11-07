package com.finances.ems.exception;

/**
 * Exception levée lorsqu'un employé ou une direction est introuvable (HTTP 404).
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
