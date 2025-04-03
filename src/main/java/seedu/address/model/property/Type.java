package seedu.address.model.property;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Property's Type in the address book.
 */
public class Type {
    public static final String MESSAGE_CONSTRAINTS =
            "Property type must be one of: Condo, HDB, Landed, Commercial";
    public static final String VALIDATION_REGEX =
            "(?i)^(Condo|HDB|Landed|Commercial)$"; // Case-insensitive match

    public final String value;

    /**
     * Constructs an {@code type}.
     *
     * @param type A valid type.
     */
    public Type(String type) {
        requireAllNonNull(type);
        String trimmedType = type.trim();
        checkArgument(isValidType(trimmedType), MESSAGE_CONSTRAINTS);
        value = formatType(trimmedType);
    }

    private static String formatType(String type) {
        return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
    }

    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Type
                && value.equalsIgnoreCase(((Type) other).value));
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
