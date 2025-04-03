package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Price;

/**
 * Jackson-friendly version of {@link Price}.
 */
class JsonAdaptedPrice {
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedPrice} with the given {@code price}.
     */
    @JsonCreator
    public JsonAdaptedPrice(String price) {
        this.price = price;
    }

    /**
     * Converts a given {@code Price} into this class for Jackson use.
     */
    public JsonAdaptedPrice(Price source) {
        price = source.price != null ? source.price.toString() : null;
    }

    @JsonValue
    public String getPrice() {
        return price;
    }

    /**
     * Converts this Jackson-friendly adapted price object into the model's {@code Price} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted price.
     */
    public Price toModelType() throws IllegalValueException {
        if (price == null) {
            return null;
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(price);
    }
}
