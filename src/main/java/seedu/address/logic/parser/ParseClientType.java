package seedu.address.logic.parser;

import seedu.address.model.person.ClientType;

/**
 * Utility class for parsing client types from user input.
 */
public class ParseClientType {

    /**
     * Error message used when the input client type is invalid.
     * Valid inputs are "buyer", "seller", or their first letters ("b", "s").
     * Leaving the field blank results in the client type being set to UNKNOWN.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Client Type should be displayed either as buyer, seller, or their first letter. "
                    + "Do not enter anything into the field to keep it as UNKNOWN. "
                    + "The first letter can be used as a substitute.";

    /**
     * Returns true if the input string is a valid representation of a client type.
     * Valid values are "buyer", "b", "seller", or "s", case-insensitive.
     *
     * @param clientType The string to test.
     * @return true if the input is valid; false otherwise.
     */
    public static boolean isValidClientType(String clientType) {
        return clientType.equalsIgnoreCase("buyer") || clientType.equalsIgnoreCase("b")
                || clientType.equalsIgnoreCase("seller") || clientType.equalsIgnoreCase("s");
    }

    /**
     * Parses a valid client type string and returns the corresponding {@code ClientType}.
     * Assumes the input has already been validated using {@link #isValidClientType(String)}.
     *
     * @param clientType The input string representing a client type.
     * @return The corresponding {@code ClientType}.
     */
    public static ClientType assignClientType(String clientType) {
        assert isValidClientType(clientType) : "assignClientType called with invalid client type: " + clientType;
        if (clientType.equalsIgnoreCase("buyer") || clientType.equalsIgnoreCase("b")) {
            return ClientType.BUYER;
        }
        return ClientType.SELLER;
    }
}
