package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's district in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDistrict(String)}
 *
 * <p>Note: The price field is nullable. If it is not set, the price will be null.</p>
 */
public class District {

    public static final String MESSAGE_CONSTRAINTS =
            "District should only contain numeric characters from 1-28, inclusive";


    public final Integer districtNumber;

    /**
     * Constructs a {@code District}.
     *
     * @param district A valid district.
     */
    public District(String district) {
        if (district != null) {
            checkArgument(isValidDistrict(district), MESSAGE_CONSTRAINTS);
            districtNumber = Integer.parseInt(district);
            assert(districtNumber > 0 && districtNumber < 29) : "Districts in singapore range from 1 to 28, inclusive";
            assert(districtNumber instanceof Integer) : "District numbers are made up of only integers";
        } else {
            this.districtNumber = null;
        }
    }

    /**
     * Returns true if a given Integer is a valid district.
     */
    public static boolean isValidDistrict(String test) {
        try {
            int test2 = Integer.parseInt(test);
            return 0 < test2 && test2 < 29;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return districtNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof District)) {
            return false;
        }

        District otherDistrict = (District) other;
        return districtNumber.equals(otherDistrict.districtNumber);
    }

    @Override
    public int hashCode() {
        return districtNumber.hashCode();
    }

    public Integer getValue() {
        return districtNumber;
    }

    public String getFormattedDistrict() {
        return "District " + getValue();
    }

}
