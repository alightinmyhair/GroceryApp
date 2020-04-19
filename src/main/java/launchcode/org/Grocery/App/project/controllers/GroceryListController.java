package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    @GetMapping
    public String displayGroceryList(Model model){
        model.addAttribute("items", GroceryItemData.getAll());
        return "groceryList/index";

    }

    @PostMapping
    public String addGroceryItem(Model model, @RequestParam String groceryItemName, @RequestParam String groceryCategory){
        GroceryItemData.add(new GroceryItem(groceryItemName, groceryCategory));
        model.addAttribute("items",GroceryItemData.getAll());
        return "groceryList/index";
    }
}
