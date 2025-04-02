package seedu.address.model.person;

import java.text.NumberFormat;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's house's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 *
 * <p>Note: The price field is nullable. If it is not set, the price will be null.</p>
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should range from 1 to any value higher. Reminder that it is in thousand dollers.";

    public final Integer price;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price, or null if no price is set.
     * @throws NullPointerException if the price is provided as a non-null value but is invalid.
     */
    public Price(String price) {
        if (price != null) {
            assert(price != null);
            checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
            this.price = Integer.parseInt(price);
            assert this.price > 0 : "Should be greater than 0";
        } else {
            this.price = null; // Set price to null if it's not provided
            assert(this.price == null) : "This price should be null";
        }
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

    public Integer getValue() {
        return this.price;
    }

    public String getFormattedPrice() {
        return "$" + NumberFormat.getInstance().format(getValue()) + ",000";
    }
}

