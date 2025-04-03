package seedu.address.model.property;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Property's address in the address book.
 */
public class Location {
    public static final String MESSAGE_CONSTRAINTS =
            "Location should not be blank and must be a valid address.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\s,.-]+"; // Basic address validation

    public final String value;

    /**
     * Constructs an {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireAllNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        value = location.trim();
    }

    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Location
                && value.equals(((Location) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
