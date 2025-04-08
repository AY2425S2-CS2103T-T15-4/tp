package seedu.address.model.person.exceptions;

/**
 * This is an exception for when the predicate is invalid
 */
public class InvalidPredicateException extends RuntimeException {
    public InvalidPredicateException(String message) {
        super(message);
    }
}
