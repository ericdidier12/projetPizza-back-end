
package eu.busi.projetpizza.dataAcces.util;


import eu.busi.projetpizza.dataAcces.entity.OrderLineEntity;
import eu.busi.projetpizza.model.Order_Line;


public class OderLineConverter {

    public static Order_Line order_lineEntityToOderModel(OrderLineEntity orderLineEntity){
        if (orderLineEntity == null) {
            throw new IllegalArgumentException(" objet oderLineEntity  ne peut pas être null ");
        }
        Order_Line order_line = new Order_Line();
        //order_line.setPizza(PizzaConveter.pizzaEntityTopizzaModel(orderLineEntity.getPizzaOrderLineEntity()));
        order_line.setOder(OderConverter.oderEntityToOderModel(orderLineEntity.getOderEntity()));
        order_line.setNumber(orderLineEntity.getNumber_of_pizza());
        return  order_line;
    }

    public static OrderLineEntity oder_lineModelToOderrEntity (Order_Line order_line) {
        if (order_line == null) {
            throw new IllegalArgumentException(" objet oderLine  ne peut pas être null ");
        }
        OrderLineEntity orderLineEntity = new OrderLineEntity();
       // orderLineEntity.setPizzaOrderLineEntity(PizzaConveter.pizzaModelTopizzaEntity(order_line.getPizza()));
        orderLineEntity.setOderEntity(OderConverter.oderModelToOderrEntity(order_line.getOder()));
        orderLineEntity.setNumber_of_pizza((int)order_line.getNumber());
        return orderLineEntity;
    }
}