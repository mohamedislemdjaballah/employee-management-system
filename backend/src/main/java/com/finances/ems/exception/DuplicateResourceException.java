package com.finances.ems.exception;

/**
 * Exception levée lorsqu'un matricule ou un email existe déjà (HTTP 409 Conflict).
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
