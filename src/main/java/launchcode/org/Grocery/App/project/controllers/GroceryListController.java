package launchcode.org.Grocery.App.project.controllers;

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

    private static List <String> items= new ArrayList<>();

    @GetMapping
    public String displayGroceryList(Model model){
        model.addAttribute("items", items);
        return "groceryList/index";

    }
    @GetMapping("add")
    public String renderCreateGroceryListForm(){
        return "groceryList/add";
    }

    @PostMapping("add")
    public String addGroceryItem(@RequestParam String groceryName){
        items.add(groceryName);
        return "redirect:";
    }
}
