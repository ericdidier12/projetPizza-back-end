package eu.busi.projetpizza.controller;

import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.Constants;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.validation.Valid;

/**
 * created by  eric.nyandwi on Nov,07/11/2018
 */

@Controller
@RequestMapping("/user/register")
@SessionAttributes({Constants.CURRENT_USER})
public class InscriptionController {

    private final UserService userService;

    public InscriptionController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "integrated:registerUser";
    }

    @ModelAttribute(Constants.CURRENT_USER)
    public User user() {
        return new User();
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String saveNewUserRegrister(@Valid @ModelAttribute(value ="currentUser") User user, final BindingResult errors) {

        if (errors.hasErrors()) {
            return "integrated:registerUser";
        }
        userService.register(user, RoleEnum.USER);
        return "redirect:/login";
    }
}
