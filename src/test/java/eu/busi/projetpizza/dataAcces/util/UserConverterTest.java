package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.UserEntity;

import eu.busi.projetpizza.model.User;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * created by  eric.nyandwi on Nov,15/11/2018
 */
public class UserConverterTest {

    public static final String NAME = "eric Nyandwi";
    public static final String USERNAME = "admin";
    public  static String EMAIL = "eric@hotmail";
    public  static final String  PASS_WORD  = "admin";

    @Test(expected = IllegalArgumentException.class)
    public void testUserModelNullToUserEntity() {
        UserConverter.userModelToUserEntity(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserEntityNullToUserModel() {
        UserConverter.userEntityToUserModel(null);
    }

    @Test
    public void testToUserEntityAllFields() {
         UserEntity userEntity = createUserEntitynAllFields();

        User user = UserConverter.userEntityToUserModel(userEntity);
        assertEquals(NAME, user.getName());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASS_WORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
    }


    @Test
    public void testToEntityAllFields() {
        User user = new User();
        user.setName(NAME);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASS_WORD);
        UserEntity userEntity   = UserConverter.userModelToUserEntity(user);
        validateUserEntity(userEntity);
    }

    /**
     * Valide les propriétés de l'entité {@link User} .
     * L'ensemble des propriétés communes est testé not null.
     *
     * @param userEntity
     */
    public static void validateUserEntity(UserEntity userEntity) {
        assertEquals(NAME, userEntity.getName());
        assertEquals(USERNAME, userEntity.getUsername());
        assertEquals(PASS_WORD, userEntity.getPassword());
        assertEquals(EMAIL, userEntity.getEmail());
    }


    /**
     * Création d'une entité {@link eu.busi.projetpizza.dataAcces.entity.UserEntity} de base commune au tests de cette classe et de {@link eu.busi.projetpizza.model.User}.
     * Toutes les propriétés sont remplies.
     *
     * @return
     */
    public static UserEntity createUserEntitynAllFields() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(NAME);
        userEntity.setUsername(USERNAME);
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASS_WORD);

        return userEntity;
    }
}