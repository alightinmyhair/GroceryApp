package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.models.User;
import launchcode.org.Grocery.App.project.data.UserRepository;
import launchcode.org.Grocery.App.project.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("test")
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Model model){

        User user = userRepository.findByUsername(loginFormDTO.getUsername());
        model.addAttribute("username", user);

        return "index";
    }
}