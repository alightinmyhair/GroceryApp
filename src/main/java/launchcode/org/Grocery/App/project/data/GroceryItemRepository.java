package launchcode.org.Grocery.App.project.data;

import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends CrudRepository <GroceryItem, Integer> {
}
