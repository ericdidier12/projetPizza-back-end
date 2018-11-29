package eu.busi.projetpizza.controller;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping(value = "")
public class WelcomeController {

    @Autowired
    private MessageSource messageSource;

    private final UserDAO userDAO;

    public WelcomeController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("user", name);
        return "integrated:welcome";
    }



}
