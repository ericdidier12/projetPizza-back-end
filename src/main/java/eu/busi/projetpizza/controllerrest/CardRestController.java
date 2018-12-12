package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.Oder_LineDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import eu.busi.projetpizza.dataAcces.entity.OrderLineEntity;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.dataAcces.util.OderLineConverter;
import eu.busi.projetpizza.dataAcces.util.PizzaConveter;
import eu.busi.projetpizza.model.Panier;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/card")
public class CardRestController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PizzaConveter pizzaConveter;

    @Autowired
    private Oder_LineDAO orderLineDAO;


// a mettre dans le service

    public List<Panier> getPizzaFromDb() {
        Optional<UserEntity> user = Optional.ofNullable(userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<OderEntity> order = user.get().getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();

        List<Panier> pTransit = new ArrayList<>();

        if (order.isPresent()) {
            OderEntity orderPresent = order.get();
            for (OrderLineEntity o : orderPresent.getOrderLineEntities()) {
                Pizza p = pizzaConveter.pizzaEntityTopizzaModel(o.getPizzaOrderLineEntity());
                int q = o.getNumber_of_pizza();
                pTransit.add(new Panier(p, q));
            }
        }
        return pTransit;
    }


    @PostMapping(value = "/remove")
    public void removeOrderLineInDB(@RequestBody Panier panier) {

        Optional<UserEntity> user = Optional.ofNullable(userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<OderEntity> orderOptional = user.get().getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();

        if (orderOptional.isPresent()) {
            OderEntity order = orderOptional.get();

            for (OrderLineEntity o:order.getOrderLineEntities()) {
                if (o.getPizzaOrderLineEntity().getName().equals(panier.getPizza().getName())){
                    orderLineDAO.delete(o);
                }
            }
        }


    }

    @PostMapping(value = "/deleteOne")
    public void deleteOneOrderLineInDB(@RequestBody Panier panier) {
        Optional<UserEntity> user = Optional.ofNullable(userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<OderEntity> orderOptional = user.get().getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();

        if (orderOptional.isPresent()) {
            OderEntity order = orderOptional.get();

            for (OrderLineEntity o:order.getOrderLineEntities()) {
                if (o.getPizzaOrderLineEntity().getName().equals(panier.getPizza().getName())){
                    o.setNumber_of_pizza(o.getNumber_of_pizza()-1);
                    orderLineDAO.saveO(o);
                }
            }
        }
    }

    @PostMapping(value = "/addOne")
    public void addOneOrderLineInDB(@RequestBody Panier panier) {
        Optional<UserEntity> user = Optional.ofNullable(userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<OderEntity> orderOptional = user.get().getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();
        boolean trouve=false;
        if (orderOptional.isPresent()) {
            OderEntity order = orderOptional.get();

            for (OrderLineEntity o:order.getOrderLineEntities()) {
                if (o.getPizzaOrderLineEntity().getName().equals(panier.getPizza().getName())){
                    o.setNumber_of_pizza(o.getNumber_of_pizza()+1);
                    orderLineDAO.saveO(o);
                    trouve=true;
                }
            }
            if (trouve==false){
                OrderLineEntity newOrderLine = new OrderLineEntity();
                newOrderLine.setOderEntity(order);
                newOrderLine.setPizzaOrderLineEntity(PizzaConveter.pizzaModelTopizzaEntity(panier.getPizza()));
                newOrderLine.setNumber_of_pizza(1);
                orderLineDAO.saveO(newOrderLine);
            }
        }

    }


    @PostMapping(value = "/getCard")
    public void mergeCart(@RequestBody Panier[] cart) {



        Optional<UserEntity> user = Optional.ofNullable(userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<OderEntity> orderOptional = user.get().getOderEntities().stream().filter(x -> x.isIs_paid() == false).findFirst();

        if (orderOptional.isPresent()) {
            OderEntity order = orderOptional.get();

            for (Panier entry : cart) {
                boolean trouve=false;
                for (OrderLineEntity ole : order.getOrderLineEntities()) {

                    if (ole.getPizzaOrderLineEntity().getName().equals(entry.getPizza().getName())) {
                        ole.setNumber_of_pizza(ole.getNumber_of_pizza() + entry.getQuantity());
                        orderLineDAO.saveO(ole);
                        trouve=true;
                    }
                }
                if (trouve==false){
                    OrderLineEntity newOrderLine = new OrderLineEntity();
                    newOrderLine.setOderEntity(order);
                    newOrderLine.setPizzaOrderLineEntity(PizzaConveter.pizzaModelTopizzaEntity(entry.getPizza()));
                    newOrderLine.setNumber_of_pizza(entry.getQuantity());
                    orderLineDAO.saveO(newOrderLine);
                }

            }

        }

    }


    @GetMapping(value = "/mergeCard")
    public List<Panier> getPanierInDB() {

        List<Panier> panierTransit = this.getPizzaFromDb();


        return panierTransit;
    }

}
