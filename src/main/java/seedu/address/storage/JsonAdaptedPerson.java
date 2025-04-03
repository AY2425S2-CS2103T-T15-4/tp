package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClientType;
import seedu.address.model.person.District;
import seedu.address.model.person.Email;
import seedu.address.model.person.LandSize;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String district;
    private final String price;
    private final String landSize;
    private final String clientType;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("district") String district, @JsonProperty("price") String price,
            @JsonProperty("landSize") String landSize, @JsonProperty("clientType") String clientType,
            @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.district = district;
        this.price = price;
        this.landSize = landSize;
        this.clientType = clientType;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        district = source.getDistrict() != null ? source.getDistrict().districtNumber.toString() : null;
        price = source.getPrice() != null ? source.getPrice().price.toString() : null;
        landSize = source.getLandSize() != null ? source.getLandSize().landsize.toString() : null;
        clientType = source.getClientType().name();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        // District is optional, so we don't check for null
        final District modelDistrict = district == null ? null : new District(district);

        // Price is optional, so we don't check for null
        final Price modelPrice = price == null ? null : new Price(price);

        // LandSize is optional, so we don't check for null
        final LandSize modelLandSize =  landSize == null ? null : new LandSize(landSize);

        // ClientType handling - default to UNKNOWN if not specified
        ClientType modelClientType;
        if (clientType == null) {
            modelClientType = ClientType.UNKNOWN;
        } else {
            try {
                modelClientType = ClientType.valueOf(clientType);
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException("Invalid client type: " + clientType);
            }
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, 
                modelTags, null, modelClientType, modelDistrict, modelPrice, modelLandSize);
    }
}
