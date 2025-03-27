public class PropertyListPanel extends UiPart<Region> {
    private static final String FXML = "PropertyListPanel.fxml";

    @FXML
    private ListView<Property> propertyListView;

    public PropertyListPanel(ObservableList<Property> propertyList) {
        super(FXML);
        propertyListView.setItems(propertyList);
        propertyListView.setCellFactory(listView -> new PropertyListViewCell());
    }

    class PropertyListViewCell extends ListCell<Property> {
        @Override
        protected void updateItem(Property property, boolean empty) {
            super.updateItem(property, empty);
            if (empty || property == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PropertyCard(property, getIndex() + 1).getRoot());
            }
        }
    }
}