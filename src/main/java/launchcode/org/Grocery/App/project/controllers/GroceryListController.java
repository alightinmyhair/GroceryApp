package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
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

    @GetMapping
    public String displayGroceryList(Model model) {

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @PostMapping("/add")
    public String addGroceryItem(@ModelAttribute @Valid GroceryItem newGroceryItem, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("categories", GroceryCategory.values());
            model.addAttribute("items", GroceryItemData.getAll());
            return "groceryList/index";
        }
        GroceryItemData.add(newGroceryItem);
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping
    public String updateGroceryList(Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem, String name, String description, GroceryCategory category) {

        GroceryItem modifyGroceryItem = GroceryItemData.getById(itemId);
        modifyGroceryItem.setName(name);
        modifyGroceryItem.setDescription(description);
        modifyGroceryItem.setCategory(category);

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @GetMapping("/edit")
    public String editGroceryItem(Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem) {

        System.out.println("yo");

        GroceryItem tempGroceryItem = GroceryItemData.getById(itemId);
        System.out.println(tempGroceryItem);

        model.addAttribute("groceryItems", GroceryItemData.getAll());
//        model.addAttribute("groceryItemIds", groceryItemIds);
        model.addAttribute("groceryItem", tempGroceryItem);
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/edit";
    }

    @PostMapping("/delete")
    public String updateGroceryItem(Model model, @RequestParam (required=false) int[] itemIds, @ModelAttribute GroceryItem groceryItem) {

        if(itemIds != null)
            for (int id : itemIds) {
                System.out.println(GroceryItemData.getById(id));
                GroceryItemData.remove(id);
            }

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";
    }
}