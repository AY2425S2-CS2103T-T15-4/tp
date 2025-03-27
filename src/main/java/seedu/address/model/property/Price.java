package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Prices should start with $ and contain numbers with optional commas and decimals, e.g., $500,000 or $500000.50";
    public static final String VALIDATION_REGEX = "^\\$\\d{1,3}(?:,\\d{3})*(?:\\.\\d{2})?$";

    public final String value;

    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Price
                && value.equals(((Price) other).value));
    }
}