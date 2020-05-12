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
    public String editGroceryItem(Model model, @ModelAttribute GroceryItem groceryItem, int [] groceryItemIds, String update) {


        System.out.println("hi");

        if (update != null) {
            ArrayList<GroceryItem> groceries = new ArrayList<>();

            for(int i=0; i<groceryItemIds.length; i++){
                System.out.println(groceryItemIds[i]);
                GroceryItem tempGroceryItem = GroceryItemData.getById(groceryItemIds[i]);
                System.out.println(tempGroceryItem);
                groceries.add(tempGroceryItem);
                System.out.println("hi2");
            }

            model.addAttribute("groceryItems", groceries);
            model.addAttribute("groceryItemIds", groceryItemIds);
            model.addAttribute("groceryItem", groceryItem);
            model.addAttribute("categories", GroceryCategory.values());
            }

        return "groceryList/index";
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

