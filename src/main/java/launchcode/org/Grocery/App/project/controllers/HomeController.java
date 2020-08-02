package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.models.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class HomeController {

    @GetMapping
    public String index(){

        // Kris testing
//        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "index";
    }
}