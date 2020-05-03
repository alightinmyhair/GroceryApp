package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;

@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    @GetMapping
    public String displayGroceryList(Model model){

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }
    // display a form in each row on groceryList
    //loop through the grocery items in a table,
    //with each table row being a form
    //in the form my input fields would be my th field
    @GetMapping("/editgrocery")
    public String displayEditForm(Model model, GroceryItem[] groceryItems, int[] itemIds, String edit){

        if (edit != null){
            System.out.println("edit button clicked");
            //is this really getting the id?
            GroceryItem groceryItem = GroceryItemData.getById(itemIds[0]);
//            GroceryItem groceryItem1 = GroceryItemData.getAll();
//            System.out.println(groceryItem, groceryItem1);
            return "edit";
        }
        for(int i = 0; i < groceryItems.length; i++){
            System.out.println(groceryItems[i]);
        }
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/edit";
    }

    @PostMapping("/editgrocery")
    public String editGroceryItem(int id, String name, String description, GroceryCategory category, Model model){
//
//        if(GroceryItemData.getAll() != null){
//            for (){
//                System.out.println(GroceryItemData.getById(id));
//                GroceryItemData.edit(id, name, description, category);
//            }
//        }

        System.out.println("hi");
        model.addAttribute("items",GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping("/add")
    public String addGroceryItem(@ModelAttribute @Valid GroceryItem newGroceryItem, Model model){

        GroceryItemData.add(newGroceryItem);
        model.addAttribute("items",GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping("/remove")
    public String removeGroceryItem(Model model, @RequestParam int[] itemIds, String delete, String edit){

        // TODO: RK - create new class to handle itemIds and edit/delete buttons?


        if (edit != null){
            System.out.println("edit button clicked");
            //is this really getting the id?
            GroceryItem groceryItem = GroceryItemData.getById(itemIds[0]);
            model.addAttribute("groceryItem", groceryItem);
            model.addAttribute("categories", GroceryCategory.values());
//            GroceryItem groceryItem1 = GroceryItemData.getAll();
//            System.out.println(groceryItem, groceryItem1);
            return "groceryList/edit";
        }

        if (delete != null){
            System.out.println("delete clicked!");
            if(itemIds != null){
                for (int id : itemIds){
                    System.out.println(GroceryItemData.getById(id));
                    GroceryItemData.remove(id);
                }
            }
        }

        model.addAttribute("items",GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";
    }
}
