package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Property {
    private final Price price;
    private final Location location;
    private final Type type;
    private final Status status;

    public Property(Price price, Location location, Type type, Status status) {
        requireAllNonNull(price, location, type, status);
        this.price = price;
        this.location = location;
        this.type = type;
        this.status = status;
    }

    public Price getPrice() { return price; }
    public Location getLocation() { return location; }
    public Type getType() { return type; }
    public Status getStatus() { return status; }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Property)) return false;
        Property otherProperty = (Property) other;
        return otherProperty.getPrice().equals(getPrice())
                && otherProperty.getLocation().equals(getLocation())
                && otherProperty.getType().equals(getType())
                && otherProperty.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, location, type, status);
    }

    @Override
    public String toString() {
        return "Price: " + getPrice() + "\n"
                + "Location: " + getLocation() + "\n"
                + "Type: " + getType() + "\n"
                + "Status: " + getStatus();
    }
}