package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's house's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should range from 1 to any value higher. Reminder that it is in thousand dollers.";

    public final Integer price;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = Integer.parseInt(price);
    }

    /**
     * Returns true if a given Integer is a valid price.
     */
    public static boolean isValidPrice(String test) {
        try {
            int test2 = Integer.parseInt(test);
            return 0 < test2;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return price.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return price.equals(otherPrice.price);
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }

}

