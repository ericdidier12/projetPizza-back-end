package eu.busi.projetpizza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by  eric.nyandwi on Nov,07/11/2018
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public String error() {
        return "integrated:welcome";
    }
}
