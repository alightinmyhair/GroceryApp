package launchcode.org.Grocery.App.project.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class GroceryItem {

    private int id;
    private static int nextId=1;

    @NotBlank
    @Size(min=3, max =50, message="Item name must be between 3-50 characters.")
    private String name;

    @Size(max=100, message="Category too long!")
    private String category;

    public GroceryItem(String name, String category) {
        this.name = name;
        this.category = category;
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryItem that = (GroceryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
