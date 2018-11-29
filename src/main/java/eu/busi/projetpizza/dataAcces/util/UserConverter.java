package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.model.Oder;
import eu.busi.projetpizza.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Transformation entre le modèle des entités JPA et les objetsModel .
 * <br/>
 */
public class UserConverter {
    /**
     * Transforme une entité JPA {@link UserEntity} en objet Model {@link User}.
     *
     * @return Objet type User
     */
    public static User userEntityToUserModel(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException(" objet userEntity  ne peut pas être null ");
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername() );
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setAccountNonExpired(userEntity.isAccountNonExpired());
        user.setAccountNonLocked(userEntity.isAccountNonLocked());
        user.setCredentialsNonExpired(userEntity.isCredentialsNonExpired());
        user.setEnabled(userEntity.isEnabled());
        user.setBirth_date(userEntity.getBirth_date());
        user.setAuthorities(userEntity.getAuthorities());
        user.setBirth_date(userEntity.getBirth_date());
        user.setAdress(userEntity.getAdressEntity() );
        return user;
    }

    /**
     * Transforme un objet Model {@link User} en une entité JPA {@link UserEntity}.
     *
     * @param user type User
     * @return objet user
     */
    public static UserEntity userModelToUserEntity(User user) {
        if (user == null) {
            throw new IllegalArgumentException(" objet userEntity  ne peut pas être null ");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail() );
        userEntity.setBirth_date(user.getBirth_date());
        userEntity.setAccountNonExpired(user.isAccountNonExpired());
        userEntity.setAccountNonLocked(user.isAccountNonLocked());
        userEntity.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userEntity.setEnabled(user.isEnabled());
        userEntity.setAuthorities(user.getAuthorities());
        userEntity.setAdressEntity(user.getAdress());
        List<OderEntity> oderEntities = new ArrayList<>();
        if (user.getOders() != null) {
            for (Oder command : user.getOders()) {
                oderEntities.add(OderConverter.oderModelToOderrEntity(command));
            }
            userEntity.setOderEntities(oderEntities);
        }
        return userEntity;
    }
}
