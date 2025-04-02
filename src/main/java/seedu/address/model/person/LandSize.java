package seedu.address.model.person;

import java.util.Objects;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's house's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLandSize(String)}
 *
 * <p>Note: The price field is nullable. If it is not set, the price will be null.</p>
 */
public class LandSize {

    public static final String MESSAGE_CONSTRAINTS =
            "LandSize should be bigger than 0. Reminder that it is in square feet.";

    public final Float landsize;

    /**
     * Constructs a {@code LandSize}.
     *
     * @param landsize A valid landsize.
     * @throws NullPointerException if the landsize is provided as a non-null value but is invalid.
     */
    public LandSize(String landsize) {
        if (landsize != null) {
            assert(landsize != null);
            checkArgument(isValidLandSize(landsize), MESSAGE_CONSTRAINTS);
            this.landsize = Float.parseFloat(landsize);
            assert this.landsize > 0 : "Should be greater than 0";
        } else {
            this.landsize = null; // Set price to null if it's not provided
            assert(this.landsize == null) : "The landsize should be null";
        }
    }

    /**
     * Returns true if a given Float is a valid landsize.
     */
    public static boolean isValidLandSize(String test) {
        try {
            float test2 = Float.parseFloat(test);
            return 0 < test2;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return Float.toString(landsize);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LandSize)) {
            return false;
        }

        LandSize otherLandSize = (LandSize) other;
        return Objects.equals(landsize, otherLandSize.landsize);
    }

    @Override
    public int hashCode() {
        return landsize.hashCode();
    }

    public Float getValue() {
        return landsize;
    }
    public String getFormattedLandSize() {
        return getValue() + " sq ft";
    }
}
