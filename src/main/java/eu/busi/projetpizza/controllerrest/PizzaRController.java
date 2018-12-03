package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by  eric.nyandwi on Nov,30/11/2018
 */
@RestController
@RequestMapping("api/")
public class PizzaRController {

    private final PizzaDAO pizzaDAO ;

    public PizzaRController(PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }


    @GetMapping("/pizzas")
    public List<Pizza> getUsers(){
        return pizzaDAO.listPizza();
    }
}
