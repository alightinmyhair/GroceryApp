package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    @GetMapping
    public String displayGroceryList(Model model){
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute(new GroceryItem());
        return "groceryList/index";

    }
    // TODO: KEJ - change url? ("addGrocery")
    @PostMapping
    public String addGroceryItem(Model model, @ModelAttribute @Valid GroceryItem newGroceryItem, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("items", GroceryItemData.getAll());
            return "groceryList/index";
        }
        GroceryItemData.add(newGroceryItem);
        model.addAttribute("items",GroceryItemData.getAll());
        return "groceryList/index";
    }

//    @PostMapping
//    public String editGroceryItem(Model model, @ModelAttribute GroceryItem updateGroceryItem){
//        GroceryItemData.
//
//    }

    @PostMapping("grocerylist")
    public String removeGroceryItem(Model model, @RequestParam(required = false) int[] itemIds, String edit, String delete){
        if (edit.equals("Edit Category")){
            System.out.println("edit button clicked");
        }
        if(itemIds != null){
            for (int id : itemIds){
                System.out.println(GroceryItemData.getById(id));
                GroceryItemData.remove(id);
            }
        }
        model.addAttribute("items",GroceryItemData.getAll());
        return "groceryList/index";
    }
}
