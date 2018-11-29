package eu.busi.projetpizza.controller;

import eu.busi.projetpizza.dataAcces.dao.OderDAO;
import eu.busi.projetpizza.dataAcces.dao.Oder_LineDAO;
import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.service.CheckoutCartService;
import eu.busi.projetpizza.dataAcces.service.OderLineSaveService;
import eu.busi.projetpizza.dataAcces.util.CustomException;
import eu.busi.projetpizza.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping(value = "/cart")
@SessionAttributes({Constants.CURRENT_USER, Constants.CURRENT_PIZZA, Constants.CURRENT_MY_MAP_PIZZA, Constants.CURRENT_TOTAL, Constants.ORDER_ID, Constants.PROMO_ORDER})
public class CartController {

    @ModelAttribute(Constants.PIZZA_EDIT)
    public Pizza pizza() {
        return new Pizza();
    }

    @Autowired
    public OderDAO oderDAO;
    @Autowired
    public PizzaDAO pizzaDAO;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public Oder_LineDAO oder_lineDAO;

    @Autowired
    public OderLineSaveService oderLineSaveService;

    @Autowired
    public CheckoutCartService checkoutCartService;

    @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA)
    public Map<Long, Pizza> pizzaMap() {return new HashMap<>();}


    @RequestMapping(method = RequestMethod.GET)
    public String Cart(Model model,  @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA) Map<Long, Pizza> pizzaHashMap) {
        if(pizzaHashMap.isEmpty()){return "redirect:/pizza";}
        model.addAttribute("title", "Cart");
        Collection<Pizza> pizzaList = pizzaHashMap.values();
        float total = 0;
        float subtotal = 0;
        float delivery_price = 0;
        LocalDateTime a = LocalDateTime.now();

        for (Iterator<Pizza> i = pizzaList.iterator(); i.hasNext(); ) {
            Pizza item = i.next();
            if(item.isMonth_promo()){
                Pizza tempPizza = pizzaDAO.findPizzaById(item.getId());
                item.setPrice(tempPizza.getPrice());
                item.setPrice(item.getPrice()-((item.getPrice()/100)*5));
                subtotal += ((item.getPrice() * item.getNumber()));
            }
            else{subtotal += (item.getPrice() * item.getNumber());}
        }
        if (subtotal <= 15) {
            delivery_price = 5;
        }
        total = delivery_price + subtotal;

        model.addAttribute("Total", total);
        model.addAttribute("SubTotal", subtotal);
        model.addAttribute("Shipping", delivery_price);
        model.addAttribute("ContentCart", pizzaList);

        return "integrated:cart";
    }

    @PostMapping(value = "/sendAdd")
    public String AddNumberPizzaToCart(@RequestParam("id") long id, @ModelAttribute(Constants.PIZZA_EDIT) Pizza pizza,  @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA) Map<Long, Pizza> pizzaHashMap) {

        Pizza newPizza = pizzaHashMap.get(id);
        newPizza.setNumber(newPizza.getNumber() + 1);
        pizzaHashMap.replace(id, newPizza);

        return "redirect:/cart";
    }

    @PostMapping(value = "/sendSubstract")
    public String SubstractNumberPizzaToCart(@RequestParam("id") long id, @ModelAttribute(Constants.PIZZA_EDIT) Pizza pizza,  @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA) Map<Long, Pizza> pizzaHashMap) {

        Pizza newPizza = pizzaHashMap.get(id);
        if (newPizza.getNumber() <= 1) {
            pizzaHashMap.remove(id, newPizza);
        } else {
            newPizza.setNumber(newPizza.getNumber() - 1);
            pizzaHashMap.replace(id, newPizza);
        }
        return "redirect:/cart";
    }

    @PostMapping(value = "/sendDelete")
    public String DeletePizzaToCart(@RequestParam("id") long id, @ModelAttribute(Constants.PIZZA_EDIT) Pizza pizza, @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA) Map<Long, Pizza> pizzaHashMap) {

        Pizza newPizza = pizzaHashMap.get(id);
        pizzaHashMap.remove(id, newPizza);

        return "redirect:/cart";
    }

    @PostMapping(value = "/valider")
    public String createOrder(Model model, @ModelAttribute(Constants.PIZZA_EDIT) Pizza pizza, @ModelAttribute(Constants.CURRENT_MY_MAP_PIZZA) Map<Long, Pizza> pizzaHashMap, Principal principal) {
        try {
            if (pizzaHashMap.isEmpty()) {
                throw new CustomException("No Pizzas found in your cart");
            }
            checkoutCartService.CreateCompleteOrder(pizza, pizzaHashMap, principal.getName());
            model.addAttribute("total", checkoutCartService.getTotal());
            model.addAttribute("promoOrder", checkoutCartService.getToPromo());
            model.addAttribute("IdOrder", checkoutCartService.getIdOrder());

            return "redirect:/payement";
        } catch (CustomException ex) {
            return "redirect:/error";
        }
    }
}


