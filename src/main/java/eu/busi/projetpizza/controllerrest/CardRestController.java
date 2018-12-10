package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import eu.busi.projetpizza.dataAcces.entity.OrderLineEntity;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.dataAcces.util.PizzaConveter;
import eu.busi.projetpizza.model.Panier;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController()
@RequestMapping( value = "/api/card")
@CrossOrigin(origins="*")
public class CardRestController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PizzaConveter pizzaConveter;

    List<Panier> panierTransit = new ArrayList<>();


    @PostMapping(value = "/getCard", headers = "Accept=application/json, text/html")
    public List<Panier> mergePanier(@RequestBody List<Panier> panier) {
        System.out.println("coucou");
        UserEntity user = userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<OderEntity> order = user.getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();

//----------On fait un panierTransit avec les commande non payer de l'order client--------------

        if (order.isPresent()) {
            OderEntity orderPresent = order.get();
            for (OrderLineEntity o : orderPresent.getOrderLineEntities()) {
                Pizza p = pizzaConveter.pizzaEntityTopizzaModel(o.getPizzaOrderLineEntity());
                int q = o.getNumber_of_pizza();
                panierTransit.add(new Panier(p, q));
            }

        }


//-----------On merge avec le panier en session---------------------

        for (Panier p : panier) {
            if (panierTransit.contains(p)) {
               int index= panierTransit.indexOf(p);
               int nouvelQuantite=panierTransit.get(index).getQuantity()+p.getQuantity();
               panierTransit.get(index).setQuantity(nouvelQuantite);

            } else {
                panierTransit.add(new Panier(p.getPizza(), p.getQuantity()));
            }
        }

        return panierTransit;
    }


    @GetMapping(value = "/mergeCard")
        public List<Panier> getMergePanier () {

            return panierTransit;
        }

}
