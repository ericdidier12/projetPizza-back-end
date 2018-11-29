package eu.busi.projetpizza.dataAcces.service;


import eu.busi.projetpizza.dataAcces.dao.OderDAO;
import eu.busi.projetpizza.dataAcces.dao.Oder_LineDAO;
import eu.busi.projetpizza.dataAcces.util.CustomException;
import eu.busi.projetpizza.model.Oder;
import eu.busi.projetpizza.model.Order_Line;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OderLineSaveService {
    @Autowired
    private Oder_LineDAO oder_lineDAO;

    @Autowired
    private OderDAO oderDAO;

    @Autowired
    private StockService testStockService;

    public long InsertListOrderLine(Order_Line order_line, Oder oder) {
        try
        {
            boolean test = testStockService.booleanTestPizzaInStock(order_line.getPizzaList());
            if (test == true) {
                Oder oder1 = oderDAO.save(oder);
                order_line.setOder(oder1);
                List<Pizza> pizzas = order_line.getPizzaList();

                for (Pizza pizza : pizzas) {
                    Order_Line order_line1 = new Order_Line();
                    order_line1.setPizza(pizza);
                    order_line1.setOder(oder1);
                    order_line1.setNumber(pizza.getNumber());
                    Order_Line order_line2 = oder_lineDAO.save(order_line1);

                }
                return oder1.getId();
            }
            else{throw new CustomException("Pas assez dans le stock");}
        }
        catch (CustomException ex){return 0;}
    }
}
