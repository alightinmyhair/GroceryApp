package launchcode.org.Grocery.App.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class GroceryItem {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="Item name must not be blank.")
    @Size(min=3, max =50, message="Item name must be between 3-50 characters.")
    private String name;

    @Size(max=50, message="Description too long!")
    private String description;

    private GroceryCategory category;

    public GroceryItem(String name, String description, GroceryCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public GroceryItem(){
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public GroceryCategory getCategory() {
        return category;
    }

    public void setCategory(GroceryCategory category) {
        this.category = category;
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
