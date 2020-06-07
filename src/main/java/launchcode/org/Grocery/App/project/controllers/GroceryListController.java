package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemRepository;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @GetMapping
    public String displayGroceryList(Model model) {

        model.addAttribute("items", groceryItemRepository.findAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @PostMapping("/add")
    public String addGroceryItem(@ModelAttribute @Valid GroceryItem newGroceryItem, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("categories", GroceryCategory.values());
            model.addAttribute("items", groceryItemRepository.findAll());
            return "groceryList/index";
        }
        groceryItemRepository.save(newGroceryItem);
        model.addAttribute("items", groceryItemRepository.findAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping
    public String updateGroceryList(Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem, String name, String description, GroceryCategory category) {

        Optional<GroceryItem> modifyGroceryItem = groceryItemRepository.findById(itemId);

        if(modifyGroceryItem.isEmpty()){
            return "groceryList/edit";
        }
        else {
            GroceryItem groceryItem1 = modifyGroceryItem.get();
            groceryItem1.setName(name);
            groceryItem1.setDescription(description);
            groceryItem1.setCategory(category);
            groceryItemRepository.save(groceryItem1);
        }

        model.addAttribute("items", groceryItemRepository.findAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @GetMapping("/edit")
    public String editGroceryItem(Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem) {

        Optional<GroceryItem> tempGroceryItem = groceryItemRepository.findById(itemId);

        if(tempGroceryItem.isEmpty()) {
            return "groceryList/edit";

        }
        else{
            GroceryItem groceryItem1 = tempGroceryItem.get();
            model.addAttribute("groceryItems", groceryItemRepository.findAll());
            model.addAttribute("groceryItem", groceryItem1);
            model.addAttribute("categories", GroceryCategory.values());
            return "groceryList/edit";
        }
    }

    @PostMapping("/delete")
    public String updateGroceryItem(Model model, @RequestParam (required=false) int[] itemIds, @ModelAttribute GroceryItem groceryItem) {

        if(itemIds != null)
            for (int id : itemIds) {
                groceryItemRepository.deleteById(id);
            }

        model.addAttribute("items", groceryItemRepository.findAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";
    }
}