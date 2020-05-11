package launchcode.org.Grocery.App.project.models;

public enum GroceryCategory {

    DAIRY("Dairy"),
    FRUITS("Fruits"),
    GRAINS("Grains"),
    MISCELLANEOUS("Miscellaneous"),
    PROTEINS("Proteins"),
    SPIRITS("Spirits"),
    SWEETS("Sweets"),
    VEGETABLES("Vegetables");

    private final String displayName;

    GroceryCategory(String displayName) {
        this.displayName = displayName;

    }
    public String getDisplayName() {
        return displayName;
    }
}
