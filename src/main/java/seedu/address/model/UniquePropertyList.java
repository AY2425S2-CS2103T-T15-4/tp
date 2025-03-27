package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class UniquePropertyList {
    private final ObservableList<Property> internalList = FXCollections.observableArrayList();
    private final ObservableList<Property> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

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