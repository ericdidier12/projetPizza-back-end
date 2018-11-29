package eu.busi.projetpizza.dataAcces.util;

/**
 * Interface � implémenter pour  les enums qui utilisent une valeur pour les représenter.
 * Typiquement, ce sera la valeur qui sera utilisée pour représenter l'enum dans une base de données.
 *
 * created by  eric.nyandwi on Nov,08/11/2018
 */
public interface EnumValuePizzaria<V> {
    /**
     * Retourne la valeur représentant l'enum.
     * @return
     */
    V getValue();
}
