package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.domain.User;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private User user;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        if(userService.save(userForm)) {
            user = userService.getUserByEmail(userForm.getEmail());
            return "redirect:/home";
        }
        model.addAttribute("msg", "This email already exists!");
        return "registration";
    }

    @RequestMapping(value={"/", "login"}, method=RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        if(logout != null) {
            model.addAttribute("message", "You have been logged out");
        }
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, @AuthenticationPrincipal User user) {
        this.user = userService.getUserByEmail(user.getEmail());
        model.addAttribute("role", this.user.getRole());
        return "home";
    }
}
