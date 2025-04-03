package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.LandSize;

/**
 * Jackson-friendly version of {@link LandSize}.
 */
class JsonAdaptedLandSize {
    private final String landsize;

    /**
     * Constructs a {@code JsonAdaptedLandSize} with the given {@code landsize}.
     */
    @JsonCreator
    public JsonAdaptedLandSize(String landsize) {
        this.landsize = landsize;
    }

    /**
     * Converts a given {@code LandSize} into this class for Jackson use.
     */
    public JsonAdaptedLandSize(LandSize source) {
        landsize = source.landsize != null ? source.landsize.toString() : null;
    }

    @JsonValue
    public String getLandsize() {
        return landsize;
    }

    /**
     * Converts this Jackson-friendly adapted landsize object into the model's {@code LandSize} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted landsize.
     */
    public LandSize toModelType() throws IllegalValueException {
        if (landsize == null) {
            return null;
        }
        if (!LandSize.isValidLandSize(landsize)) {
            throw new IllegalValueException(LandSize.MESSAGE_CONSTRAINTS);
        }
        return new LandSize(landsize);
    }
}
