package launchcode.org.Grocery.App.project.controllers;
//package launchcode.org.Grocery.App.project.controllers.AuthenticationController;

import launchcode.org.Grocery.App.project.data.GroceryItemRepository;
import launchcode.org.Grocery.App.project.data.UserRepository;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import launchcode.org.Grocery.App.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";


    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    @GetMapping
    public String displayGroceryList(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        User user = getUserFromSession(session);
        model.addAttribute("items", user.getGroceryItemList());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @PostMapping("/add")
    public String addGroceryItem(HttpSession session, @ModelAttribute @Valid GroceryItem newGroceryItem, Errors errors, Model model) {
        User user = getUserFromSession(session);

        if (errors.hasErrors()) {
            model.addAttribute("categories", GroceryCategory.values());
            model.addAttribute("items", user.getGroceryItemList());
            return "groceryList/index";
        }

        newGroceryItem.setUser(user);
        groceryItemRepository.save(newGroceryItem);
        model.addAttribute("items", user.getGroceryItemList());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping
    public String updateGroceryList(HttpSession session,Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem, String name, String description, GroceryCategory category) {
        User user = getUserFromSession(session);

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

        model.addAttribute("items", user.getGroceryItemList());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    @GetMapping("/edit")
    public String editGroceryItem(HttpSession session, Model model, @RequestParam int itemId, @ModelAttribute GroceryItem groceryItem) {
        User user = getUserFromSession(session);

        Optional<GroceryItem> tempGroceryItem = groceryItemRepository.findById(itemId);

        if(tempGroceryItem.isEmpty()) {
            return "groceryList/edit";

        }
        else{
            GroceryItem groceryItem1 = tempGroceryItem.get();
            model.addAttribute("groceryItems", user.getGroceryItemList());
            model.addAttribute("groceryItem", groceryItem1);
            model.addAttribute("categories", GroceryCategory.values());
            return "groceryList/edit";
        }
    }

    @PostMapping("/delete")
    public String updateGroceryItem(HttpSession session, Model model, @RequestParam (required=false) int[] itemIds, @ModelAttribute GroceryItem groceryItem) {
        User user = getUserFromSession(session);

        if(itemIds != null)
            for (int id : itemIds) {
                groceryItemRepository.deleteById(id);
            }

        model.addAttribute("items", user.getGroceryItemList());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";
    }
}