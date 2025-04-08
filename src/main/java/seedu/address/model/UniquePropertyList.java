package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.DuplicatePropertyException;

/**
 * This is a class
 */
public class UniquePropertyList {
    private final ObservableList<Property> internalList = FXCollections.observableArrayList();
    private final ObservableList<Property> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * This is a method in said class
     * @param toAdd this is a parameter
     * @throws DuplicatePropertyException this is an exception
     */
    public void add(Property toAdd) throws DuplicatePropertyException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePropertyException();
        }
        internalList.add(toAdd);
    }

    public boolean contains(Property toCheck) {
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public ObservableList<Property> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
}
