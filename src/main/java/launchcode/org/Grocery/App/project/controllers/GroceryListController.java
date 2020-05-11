package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

//    @Autowired(required = false)
//    ArrayList<GroceryItem> groceryItems1 = new ArrayList<>();

    @GetMapping
    public String displayGroceryList(Model model) {

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    // display a form in each row on groceryList
    //loop through the grocery items in a table,
    //with each table row being a form
    //in the form my input fields would be my th field

    @PostMapping
    public String editGroceryItem(@ModelAttribute GroceryItem groceryItem, Model model, Integer id, String name, String description, GroceryCategory category) {


//        GroceryItemData.edit(groceryItem, newGroceryItem);
        System.out.println("hi");
        GroceryItem modifyGrocery = GroceryItemData.getById(id);
        modifyGrocery.setName(name);
        modifyGrocery.setDescription(description);
        modifyGrocery.setCategory(category);
        GroceryItemData.add(modifyGrocery);
//        System.out.println(groceryItem);
//        model.addAttribute("groceryItems", groceryItems);
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "redirect:";
    }

    @PostMapping("/add")
    public String addGroceryItem(@ModelAttribute @Valid GroceryItem newGroceryItem, Model model) {

        GroceryItemData.add(newGroceryItem);
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping("/update")
    public String updateGroceryItem(Model model, @RequestParam int[] itemIds, @ModelAttribute GroceryItem groceryItem, String delete, String edit) {

        if (edit != null) {
            ArrayList<GroceryItem> groceryItems1 = new ArrayList<>();

            for (int i = 0; i < itemIds.length; i++) {
                System.out.println(itemIds[i]);
                GroceryItem tempGroceryItem = GroceryItemData.getById(itemIds[i]);
                System.out.println(tempGroceryItem);
                groceryItems1.add(tempGroceryItem);
            }

            model.addAttribute("groceryItems", groceryItems1);
            model.addAttribute("groceryItem", groceryItem);
            model.addAttribute("itemIds", itemIds);
            model.addAttribute("categories", GroceryCategory.values());

            return "groceryList/edit";
        }

            if (delete != null) {
                System.out.println("delete clicked!");
                if (itemIds != null) {
                    for (int id : itemIds) {
                        System.out.println(GroceryItemData.getById(id));
                        GroceryItemData.remove(id);
                    }
                }
            }

            model.addAttribute("items", GroceryItemData.getAll());
            model.addAttribute("categories", GroceryCategory.values());
            model.addAttribute(new GroceryItem());

            return "groceryList/index";
        }
    }

