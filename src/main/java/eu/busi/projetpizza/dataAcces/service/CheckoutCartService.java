package eu.busi.projetpizza.dataAcces.service;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.enums.StatusEnum;
import eu.busi.projetpizza.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CheckoutCartService {

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public OderLineSaveService oderLineSaveService;

    private  float total;
    private  long IdOrder;
    private int toPromo;

    public void CreateCompleteOrder(Pizza pizza, Map<Long, Pizza> pizzaMapCart, String userName) {

        Collection<Pizza> pizzaList = pizzaMapCart.values();
        List<Pizza> listPizza = new ArrayList<>();

        float subtotal = 0;
        float delivery_price = 0;
        LocalDateTime a = LocalDateTime.now();
        for (Iterator<Pizza> i = pizzaList.iterator(); i.hasNext(); ) {
            Pizza item = i.next();
            subtotal += (item.getPrice() * item.getNumber());
            listPizza.add(item);
        }
        if (subtotal <= 15) {
            delivery_price = 5;
        }
        this.total = delivery_price + subtotal;
        User user1 = userDAO.findByUsername(userName);

/**create promo random**/

        Random r = new Random();
        this.toPromo = r.nextInt((15 - 1) + 1) + 1;
        total -= ((total / 100) * toPromo);
        Oder oder = new Oder();
        oder.setUser(user1);
        oder.setTotal_price(total);
        oder.setStatusEnum(StatusEnum.IN_PROGRESS);
        oder.setIs_paid(false);
        oder.setDate_order(LocalDateTime.now());
        oder.setFull_price(subtotal);
        oder.setDelivery_price(delivery_price);

/**Test of save order_Line to DB**/

        Order_Line order_line = new Order_Line();
        order_line.setPizzaList(listPizza);
        this.IdOrder = oderLineSaveService.InsertListOrderLine(order_line, oder);
        pizzaMapCart.clear();

    }

    public float getTotal() {
        return total;
    }

    public long getIdOrder() {
        return IdOrder;
    }

    public int getToPromo() {
        return toPromo;
    }
}
