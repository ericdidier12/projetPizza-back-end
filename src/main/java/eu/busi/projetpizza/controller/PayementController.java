package eu.busi.projetpizza.controller;

import eu.busi.projetpizza.dataAcces.dao.OderDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.model.Constants;
import eu.busi.projetpizza.model.Oder;
import eu.busi.projetpizza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/payement")
@SessionAttributes({Constants.CURRENT_TOTAL, Constants.ORDER_ID, Constants.PROMO_ORDER})
public class PayementController {

    @Autowired
    private OderDAO oderDAO;

    @Autowired
    private UserDAO userDAO;

   @RequestMapping(method = RequestMethod.GET)
    public String Payement(Model model, @ModelAttribute(Constants.PROMO_ORDER) int promoOrder, @ModelAttribute(Constants.CURRENT_TOTAL) float total, @ModelAttribute(Constants.ORDER_ID) int IdOrder, Principal principal){
        model.addAttribute("title", "Payement");
        model.addAttribute("promoOrder", promoOrder);
        model.addAttribute("order_ID", IdOrder);
        model.addAttribute("total", total);
       User user = userDAO.findByUsername(principal.getName());
       model.addAttribute("nameCustomer", user.getName());
       model.addAttribute("addressCustomer", user.getAdress());

        return "integrated:payement";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setorderpaid")
    public String SetOrderPaid(@ModelAttribute(Constants.ORDER_ID) int IdOrder)
    {
        Oder oder = oderDAO.loadOrderById(IdOrder);
        oder.setIs_paid(true);
        Oder oder1 = oderDAO.save(oder);
        return "redirect:/home";
    }
}

