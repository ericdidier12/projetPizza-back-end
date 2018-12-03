package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(origins="*")
public class PizzaRestController {

    @Autowired
    private PizzaDAO pizzaDao;

    @GetMapping(value="")
    public List<Pizza> getAll(){
        return pizzaDao.listPizza();
    }
}
