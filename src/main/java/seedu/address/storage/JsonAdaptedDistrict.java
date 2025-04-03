package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.District;

/**
 * Jackson-friendly version of {@link District}.
 */
class JsonAdaptedDistrict {
    private final String districtNumber;

    /**
     * Constructs a {@code JsonAdaptedDistrict} with the given {@code districtNumber}.
     */
    @JsonCreator
    public JsonAdaptedDistrict(String districtNumber) {
        this.districtNumber = districtNumber;
    }

    /**
     * Converts a given {@code District} into this class for Jackson use.
     */
    public JsonAdaptedDistrict(District source) {
        districtNumber = source.districtNumber != null ? source.districtNumber.toString() : null;
    }

    @JsonValue
    public String getDistrictNumber() {
        return districtNumber;
    }

    /**
     * Converts this Jackson-friendly adapted district object into the model's {@code District} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted district.
     */
    public District toModelType() throws IllegalValueException {
        if (districtNumber == null) {
            return new District(null);
        }
        
        if (!District.isValidDistrict(districtNumber)) {
            throw new IllegalValueException(District.MESSAGE_CONSTRAINTS);
        }
        return new District(districtNumber);
    }
}
