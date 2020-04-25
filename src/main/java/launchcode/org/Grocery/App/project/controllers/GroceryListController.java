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
    // TODO: - change url? ("addGrocery")
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
    //Ryan suggests that it is my mapping
    //Post = update, need to differentiate b/w a post...I can't post empty info
//    @RequestMapping(method=DELETE)
    @PostMapping("grocerylist")
    public String removeGroceryItem(Model model, @RequestParam int[] itemIds, String edit, String delete){
        // TODO: KEJ - create new class to handle itemIds and edit/delete buttons?
        if (edit != null){
            System.out.println("edit button clicked");

        }
//        if (edit.equals("Edit Item")){
//            System.out.println("edit button clicked");
//            delete = "";
//
//        }

        if (delete != null){
            System.out.println("delete clicked!");
        }
//        if (delete.equals("Delete Item")){
//            System.out.println("delete button clicked");
//            edit = "";
//        }

//        if (delete.equals("Delete Item")){
//            System.out.println("delete button clicked!!! :D");
//        }

//        if(itemIds != null){
//            for (int id : itemIds){
//                System.out.println(GroceryItemData.getById(id));
//                GroceryItemData.remove(id);
//            }
//        }
        model.addAttribute("items",GroceryItemData.getAll());
        // TODO: KEJ - new GroceryItem added in 'groceryList/index', what was causing error?
        model.addAttribute(new GroceryItem());
        return "groceryList/index";
    }
}
