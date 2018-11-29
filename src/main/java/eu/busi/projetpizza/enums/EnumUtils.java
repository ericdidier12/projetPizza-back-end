package eu.busi.projetpizza.enums;

import eu.busi.projetpizza.dataAcces.util.EnumValuePizzaria;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */
public class EnumUtils {
    /**
     * Recherche un ?l?ment d'une ?num?ration ? partir de sa valeur.
     * La m?thode permet l'introduction de nombreux types de param?tres. Les seules restrictions sont :
     * <ul>
     * 		<li>la classe doit ?tre une classe ?tendant {@link Enum} et implementant l'interface {@link EnumValuePizzaria}</li>
     * 		<li>La type de la valeur recherch?e doit seulement ?tendre {@link Object}</li>
     * </ul>
     *
     * @param <V> La classe du param?tre value (cette classe doit étendre la classe {@link Object}).
     * @param <T> La classe de l'énumération. Elle doit donc étendre {@link Enum} mais également implémenter l'interface {@link EnumValuePizzaria}
     * du m?me type que le param?tre value.
     * @param classe La classe de l'énumération ? parcourir.
     * @param value La valeur à rechercher
     * @return L'?l?ment correspondant ? la valeur
     */
    public static <T extends Enum<?> & EnumValuePizzaria<V>, V>T findEnumByValue(Class<T> classe, V value) {
        T[] values = (T[])classe.getEnumConstants();

        for (T item : values) {
            if (value == null) {
                if (item.getValue() == null) {
                    return item;
                }
            } else if (value.equals(item.getValue())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Aucun élément de l'énumération " + classe.getName() + " ne correspond à la valeur " + value);
    }

    /**
     * Recherche un élément d'une ?num?ration à partir de son ordinal.
     * @param <T> La classe de l'énumération. Il faut que ce soit une ?num?ration et doit donc étendre {@link Enum}.
     * @param classe La classe de l'énumération à parcourir.
     * @param ordinal L'ordinal à rechercher
     * @return L'?l?ment correspondant à l'ordinal
     */
    public static <T extends Enum<?>>T findEnumByOrdinal(Class<T> classe, int ordinal) {
        T [] values = (T[])classe.getEnumConstants();
        if (ordinal < values.length) {
            return values[ordinal];
        }
        throw new IllegalArgumentException("Aucun élément de l'énumération " + classe.getName() + " ne correspond à l'ordinal " + ordinal);
    }

}
