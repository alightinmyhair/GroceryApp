package launchcode.org.Grocery.App.project.data;

import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroceryItemData {
    //need a place to put our Grocery Items - this will be our main property
    private static final Map<Integer, GroceryItem> items = new HashMap<>();

    //need to get all items
    public static Collection<GroceryItem> getAll(){
        return items.values();
    }

    //need to get a single item
    public static GroceryItem getById(int id){
        return items.get(id);
    }

    //add an item
    public static void add(GroceryItem groceryItem){
        items.put(groceryItem.getId(), groceryItem);
    }

    public static void edit(GroceryItem groceryItem, GroceryItem newGroceryItem/*Integer id, String name, String description, GroceryCategory category*/){
        items.replace(groceryItem.getId(), newGroceryItem);
//        if(id != null){
//            groceryItem.setName(name);
//            groceryItem.setDescription(description);
//            groceryItem.setCategory(category);
//        }
    }

    //delete item
    public static void remove(int id){
        items.remove(id);
//        items.remove(items.get(id));

    }
}
