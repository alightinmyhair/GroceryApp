package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemRepository;
import launchcode.org.Grocery.App.project.data.UserRepository;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import launchcode.org.Grocery.App.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("grocerylist/results")
public class ResultsController {

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
    public String displayGroceryResults(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        User user = getUserFromSession(session);
//        model.addAttribute("items", user.getGroceryItemList());
//        model.addAttribute("categories", GroceryCategory.values());
//        model.addAttribute(new GroceryItem());

        return "groceryList/results";

    }
}
