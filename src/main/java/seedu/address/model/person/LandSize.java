package seedu.address.model.person;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's house's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLandSize(String)}
 */
public class LandSize {

    public static final String MESSAGE_CONSTRAINTS =
            "LandSize should be bigger than 0. Reminder that it is in square feet.";

    public final Float landsize;

    /**
     * Constructs a {@code LandSize}.
     *
     * @param landsize A valid landsize.
     */
    public LandSize(String landsize) {
        requireNonNull(landsize);
        checkArgument(isValidLandSize(landsize), MESSAGE_CONSTRAINTS);
        this.landsize = Float.parseFloat(landsize);
    }

    /**
     * Returns true if a given Float is a valid landsize.
     */
    public static boolean isValidLandSize(String test) {
        try {
            Float test2 = Float.parseFloat(test);
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
        if (!(other instanceof Price)) {
            return false;
        }

        LandSize otherLandSize = (LandSize) other;
        return Objects.equals(landsize, otherLandSize.landsize);
    }

    @Override
    public int hashCode() {
        return landsize.hashCode();
    }

}
