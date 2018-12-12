package eu.busi.projetpizza.model;

import java.util.Objects;

public class Panier {
    private Pizza pizza;
    private int quantity;

    public Panier() {
    }

    public Panier(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        Panier panier = (Panier) o;
        return Objects.equals(getPizza().getName(),panier.getPizza().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPizza());
    }
}
