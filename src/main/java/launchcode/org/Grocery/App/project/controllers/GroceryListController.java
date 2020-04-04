package launchcode.org.Grocery.App.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

//    @GetMapping("grocerylist")
    @GetMapping
    public String displayGroceryList(Model model){
        List<String> items = new ArrayList<>();
        items.add("Brown Eggs");
        items.add("Oberweis Milk");
        items.add("Lemons");
        items.add("Orange Juice");
        model.addAttribute("items", items);
        return "groceryList/index";
    }
}
