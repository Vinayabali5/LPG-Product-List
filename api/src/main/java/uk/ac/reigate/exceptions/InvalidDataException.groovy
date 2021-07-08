package uk.ac.reigate.exceptions;

/**
 * This exception is used when the user provides the invalid data to the system.
 *
 * @author Vinaya Bali
 *
 */
public class InvalidDataException extends ApiException {
    
    public InvalidDataException() {
        super("Invalid Data Provided");
    }
    
    public InvalidDataException(String message) {
        super(message);
    }
}
