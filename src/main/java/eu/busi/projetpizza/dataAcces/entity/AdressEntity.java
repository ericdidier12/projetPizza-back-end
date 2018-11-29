package eu.busi.projetpizza.dataAcces.entity;

import javax.persistence.Embeddable;

/**
 * <b>AdressEntity est la classe représentant une classe integrable.</b>
 * <p>  c-a-d va stocker ses données dans la table de entitie mère @link UserEntity
 * <br> ce qui va créer des colonnes suplementaires
 * </p>
 * created by  eric.nyandwi on Nov,08/11/2018
 */
@Embeddable
public class AdressEntity {
    private String street ;
    private String number ;
    private String zip_code;
    private String country;
    private String box;

    public AdressEntity() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }
}
