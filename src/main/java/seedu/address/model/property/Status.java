package seedu.address.model.property;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Property's Status in the address book.
 */
public class Status {
    public static final String MESSAGE_CONSTRAINTS =
            "Status must be one of: Available, Pending, Sold";
    public static final String VALIDATION_REGEX =
            "(?i)^(Available|Pending|Sold)$"; // Case-insensitive match

    public final String value;

    /**
     * Constructs an {@code status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireAllNonNull(status);
        String trimmedStatus = status.trim();
        checkArgument(isValidStatus(trimmedStatus), MESSAGE_CONSTRAINTS);
        value = formatStatus(trimmedStatus);
    }

    private static String formatStatus(String status) {
        return status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Status
                && value.equalsIgnoreCase(((Status) other).value));
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
