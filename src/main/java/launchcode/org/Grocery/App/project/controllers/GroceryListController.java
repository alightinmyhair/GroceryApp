package launchcode.org.Grocery.App.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    private static HashMap <String, String> items= new HashMap<>();

    @GetMapping
    public String displayGroceryList(Model model){
        model.addAttribute("items", items);
        return "groceryList/index";

    }
//    @GetMapping
//    public String renderCreateGroceryListForm(){
//        return "groceryList/index";
//    }

    @PostMapping
    public String addGroceryItem(Model model, @RequestParam String groceryName, String groceryCategory){
        items.put(groceryName, groceryCategory);
        model.addAttribute("items",items);
        return "groceryList/index";
    }
}
