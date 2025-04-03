package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.Location;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Status;
import seedu.address.model.property.Type;

/**
 * Jackson-friendly version of {@link Property}.
 */
public class JsonAdaptedProperty {
    private final String price;
    private final String location;
    private final String type;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given Property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("price") String price,
                               @JsonProperty("location") String location,
                               @JsonProperty("type") String type,
                               @JsonProperty("status") String status) {
        this.price = price;
        this.location = location;
        this.type = type;
        this.status = status;
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        price = source.getPrice().value;
        location = source.getLocation().value;
        type = source.getType().value;
        status = source.getStatus().value;
    }

    /**
     * Converts this Jackson-friendly adapted Property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Property.
     */
    public Property toModelType() throws IllegalValueException {
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        // Add similar validation for other fields

        return new Property(
                new Price(price),
                new Location(location),
                new Type(type),
                new Status(status)
        );
    }
}
