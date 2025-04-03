package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ClientType;

/**
 * Jackson-friendly version of {@link ClientType}.
 */
class JsonAdaptedClientType {
    private final String clientType;

    /**
     * Constructs a {@code JsonAdaptedClientType} with the given {@code clientType}.
     */
    @JsonCreator
    public JsonAdaptedClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * Converts a given {@code ClientType} into this class for Jackson use.
     */
    public JsonAdaptedClientType(ClientType source) {
        clientType = source.name();
    }

    @JsonValue
    public String getClientType() {
        return clientType;
    }

    /**
     * Converts this Jackson-friendly adapted client type object into the model's {@code ClientType} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client type.
     */
    public ClientType toModelType() throws IllegalValueException {
        try {
            return ClientType.valueOf(clientType);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException("Invalid client type: " + clientType
                    + ". Valid types are: BUYER, SELLER, UNKNOWN");
        }
    }
}
