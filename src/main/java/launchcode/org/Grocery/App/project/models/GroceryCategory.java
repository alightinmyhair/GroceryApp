package launchcode.org.Grocery.App.project.models;

public enum GroceryCategory {

    GRAINS("Grains"),
    VEGETABLES("Vegetables"),
    FRUIT("Fruit"),
    DAIRY("Dairy"),
    PROTEIN("Protein"),
    SWEETS("Sweets"),
    MISCELLANEOUS("Miscellaneous");

    private final String displayName;

    GroceryCategory(String displayName) {
        this.displayName = displayName;

    }
    public String getDisplayName() {
        return displayName;
    }
}
