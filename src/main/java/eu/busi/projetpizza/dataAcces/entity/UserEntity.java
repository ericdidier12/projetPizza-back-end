package eu.busi.projetpizza.dataAcces.entity;
import eu.busi.projetpizza.dataAcces.util.converter.LocalDateConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user_client")
public class UserEntity extends BaseEntity implements UserDetails {

    @Basic
    @Column(nullable = false)
    @NotEmpty(message = "Please enter your email addresss.")
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 15, message = "Your username must between 4 and 15 characters")
    @Column(nullable = false, unique = true, length = 15)
    private String username;


    @NotNull
    @Size(min = 4, max = 15, message = "Your name must between 4 and 15 characters")
    private String name;

    @Basic
    @NotNull
//    @Size(min = 3, max = 15, message = "Your password must between 3 and 15 characters")
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birth_date;



    @Column(name = "ACOUNT_NOT_LOCKED")
    private boolean accountNonLocked;

    @Column(name = "ACOUNT_NOT_EXPIRED")
    private boolean accountNonExpired;

    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER) // EAGER c-à-d à chaque fois vous charge user vous chargerai aussi se Role
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Authority> authorities;

    private String adresse;

    @OneToMany(mappedBy = "userEntity")
    private List<OderEntity> oderEntities;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "best_pizza",
            joinColumns = @JoinColumn(name = "user_client_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<PizzaEntity> pizzasFavorites;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getAdressEntity() {
        return this.adresse;
    }

    public void setAdressEntity(String adressEntity) {
        this.adresse = adressEntity;
    }

    public Collection<OderEntity> getOderEntities() {
        return oderEntities;
    }

    public void setOderEntities(List<OderEntity> oderEntities) {
        this.oderEntities = oderEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PizzaEntity> getPizzasFavorites() {
        return pizzasFavorites;
    }

    public void setPizzasFavorites(List<PizzaEntity> pizzasFavorites) {
        this.pizzasFavorites = pizzasFavorites;
    }
}
